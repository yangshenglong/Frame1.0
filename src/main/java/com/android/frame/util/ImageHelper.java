package com.android.frame.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.widget.ImageView;


import com.android.frame.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by YangShengLong on 2018/5/22 0022.
 */

public class ImageHelper {
    // LRUCahce 池子
    private static LruCache<String, Bitmap> mCache;
    private static Handler mHandler;
    private static ExecutorService mThreadPool;
    private static Map<ImageView, Future<?>> mTaskTags = new LinkedHashMap<ImageView, Future<?>>();
    private Context mContext;

    public ImageHelper(Context context) {
        this.mContext = context;
        if (mCache == null) {
            // 最大使用的内存空间
            int maxSize = (int) (Runtime.getRuntime().freeMemory() / 4);
            if (maxSize > 0) {
                mCache = new LruCache<String, Bitmap>(maxSize) {
                    @Override
                    protected int sizeOf(String key, Bitmap value) {
                        return value.getRowBytes() * value.getHeight();
                    }
                };
            }

            if (mHandler == null) {
                mHandler = new Handler();
            }

            if (mThreadPool == null) {
                // 最多同时允许的线程数为3个
                mThreadPool = Executors.newFixedThreadPool(3);
            }
        }

    }

    public void display(ImageView iv, String url) {
        // 1.去内存中取
        Bitmap bitmap = mCache.get(url);
        if (bitmap != null) {
            // 直接显示
            iv.setImageBitmap(bitmap);
            return;
        }

        // 2.去硬盘上取
        bitmap = loadBitmapFromLocal(url);
        if (bitmap != null) {
            // 直接显示
            iv.setImageBitmap(bitmap);
            return;
        }
        if (bitmap == null) {
            iv.setImageResource(R.mipmap.ic_launcher);
        }
        // 3. 去网络获取图片
        new Thread(new ImageLoadTask(iv, url)).start();
    }


    class ImageLoadTask implements Runnable {

        private String mUrl;
        private ImageView iv;

        public ImageLoadTask(ImageView iv, String url) {
            this.mUrl = url;
            this.iv = iv;
        }

        @Override
        public void run() {
            // HttpUrlconnection
            try {
                // 获取连接
                HttpURLConnection conn = (HttpURLConnection) new URL(mUrl).openConnection();

                conn.setConnectTimeout(30 * 1000);// 设置连接服务器超时时间
                conn.setReadTimeout(30 * 1000);// 设置读取响应超时时间

                // 连接网络
                conn.connect();

                // 获取响应码
                int code = conn.getResponseCode();

                Log.d("ImageLoadTask", "code:" + code);
                if (200 == code) {
                    InputStream is = conn.getInputStream();

                    // 将流转换为bitmap
                    Bitmap bitmap = BitmapFactory.decodeStream(is);

                    // 存储到本地
                    write2Local(mUrl, bitmap);

                    // 存储到内存
                    mCache.put(mUrl, bitmap);
                    mHandler.post(new Runnable() {

                        @Override
                        public void run() {
                            display(iv, mUrl);
                        }
                    });
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block0
                e.printStackTrace();
            }
        }
    }

    /**
     * 本地种去去图片
     *
     * @param url
     */
    private Bitmap loadBitmapFromLocal(String url) {
        // 去找文件，将文件转换为bitmap
        String name;
        try {
            name = MD5Encoder.encode(url);

            File file = new File(getCacheDir(), name);
            if (file.exists()) {

                Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());

                // 存储到内存
                mCache.put(url, bitmap);
                return bitmap;
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    private void write2Local(String url, Bitmap bitmap) {
        String name;
        FileOutputStream fos = null;
        try {
            name = MD5Encoder.encode(url);
            File file = new File(getCacheDir(), name);
            fos = new FileOutputStream(file);

            // 将图像写到流中
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                    fos = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String getCacheDir() {
        String state = Environment.getExternalStorageState();
        File dir = null;
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            // 有sd卡
            dir = new File(Environment.getExternalStorageDirectory(), "/Android/data/" + mContext.getPackageName()
                    + "/icon");
        } else {
            // 没有sd卡
            dir = new File(mContext.getCacheDir(), "/icon");

        }

        if (!dir.exists()) {
            dir.mkdirs();
        }

        return dir.getAbsolutePath();
    }
}
