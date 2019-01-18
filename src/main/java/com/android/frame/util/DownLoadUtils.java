package com.android.frame.util;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.content.FileProvider;
import android.widget.RemoteViews;


import com.android.frame.MyApp;
import com.android.frame.R;
import com.android.frame.biz.EasyLocalTask;
import com.android.frame.net.ZhyConfig;
import com.android.frame.weight.interfaces.DataCallBack;
import com.android.frame.weight.popup.InstallPermissionPopup;

import java.io.File;
import java.io.IOException;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by YangShengLong
 * author:大魔王老昜
 * data:on 2018/11/29
 */

public class DownLoadUtils {
    private NotificationManager notificationManager;
    private Notification notification;
    private int progress;
    File mFile;
    RemoteViews view = null;
    private Context mContext;


    public DownLoadUtils(Context context) {
        mContext = context;
    }

    /**
     * 下载apk
     *
     * @param url
     */
    public void downloadApk(final String url) {
        new EasyLocalTask<Void, File>() {
            @Override
            protected File doInBackground(Void... params) {
                File file = new File(MyApp.CACHE_ROOT_CACHE_DIR +
                        File.separator + ZhyConfig.APK_NAME);
                try {

                    notification();
                    HttpUtil.downloadFile(url, file, new HttpUtil.IDownloadCallback() {
                        int i = 0;

                        @Override
                        public void onProgress(long currentSize, long totalSize) {

                            progress = (int) (((float) currentSize / totalSize) * 100);

                            if ((progress / 10) > i) {
                                i = (int) (progress / 10);
                                // 更改进度条

                                notification.contentView.setProgressBar(R.id.progress, (int) (totalSize / 1024 / 1000),
                                        (int) (currentSize / 1024 / 1000), false);
                                // 发送消息
                                notificationManager.notify(101, notification);
                            }
                        }
                    });
                } catch (IOException e) {
                    file = null;
                }
                return file;
            }

            @Override
            protected void onPostExecute(File result) {
                super.onPostExecute(result);
                //下载完成
                if (result != null) {
                    notificationManager.cancel(101);// notification关闭不显示
                    installProcession(result);
                }
            }
        }.execute();
    }


    /**
     * 校验安装未知来源应用的权限
     *
     * @param result
     */
    private void installProcession(File result) {
        mFile = result;
        boolean haveInstallPermission;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //先获取是否有安装未知来源应用的权限
            haveInstallPermission = mContext.getPackageManager().canRequestPackageInstalls();
            if (!haveInstallPermission) {
                //没有权限
                InstallPermissionPopup mPopup = new InstallPermissionPopup(mContext);
                mPopup.showPopup();
                mPopup.setDataCallBack(new DataCallBack() {
                    @Override
                    public void getDataCallBack() {
                        Uri packageURI = Uri.parse("package:" + mContext.getPackageName());
                        //注意这个是8.0新API
                        Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, packageURI);
                        ((Activity) (mContext)).startActivityForResult(intent, 10086);


                    }
                });
            } else {
                //安装
                install(mContext);
            }
        } else {
            //安装
            install(mContext);
        }
    }

    /**
     * @Description 安装APK
     */
    public void install(Context context) {

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 24) {
            //判读版本是否在7.0以上
            Uri apkUri = FileProvider.getUriForFile(mContext, "com.wityun.txy.fileprovider", mFile);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(mFile),
                    "application/vnd.android.package-archive");
        }
        context.startActivity(intent);
    }


    /**
     * 通知栏
     */
    private void notification() {
        String id = "my_channel_01";
        String name = "唐小腰";
        notificationManager = (NotificationManager) mContext.getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //适配8.0
            NotificationChannel mChannel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_LOW);
            notificationManager.createNotificationChannel(mChannel);
            notification = new Notification.Builder(mContext)
                    .setChannelId(id)
                    .setSmallIcon(R.mipmap.ic_launcher).build();
            if (view == null) {
                view = new RemoteViews(mContext.getPackageName(), R.layout.notification);
                notification.contentView = view;
                notification.contentView.setProgressBar(R.id.progress, 100, 0, false);
            }
            PendingIntent contentIntent = PendingIntent.getActivity(mContext, R.string.app_name, new Intent(),
                    PendingIntent.FLAG_UPDATE_CURRENT);
            notification.contentIntent = contentIntent;
            notification.flags |= Notification.FLAG_ONGOING_EVENT;// 滑动或者clear都不会清空

        } else {

            notification = new Notification(R.mipmap.ic_launcher, "下载新版本", System.currentTimeMillis());

            if (view == null) {
                view = new RemoteViews(mContext.getPackageName(), R.layout.notification);
                notification.contentView = view;
                notification.contentView.setProgressBar(R.id.progress, 100, 0, false);
            }
            PendingIntent contentIntent = PendingIntent.getActivity(mContext, R.string.app_name, new Intent(),
                    PendingIntent.FLAG_UPDATE_CURRENT);
            notification.contentIntent = contentIntent;
            notification.flags |= Notification.FLAG_ONGOING_EVENT;// 滑动或者clear都不会清空
        }
        notificationManager.notify(101, notification);
    }
}
