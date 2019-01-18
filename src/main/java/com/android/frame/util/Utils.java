package com.android.frame.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.NotificationManagerCompat;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.Base64;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.android.frame.MyApp;
import com.android.frame.base.BaseActivity;
import com.android.frame.weight.CustomClipLoading;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;


/**
 * Created by Server on 2017/2/22.
 */

public class Utils {

    private static Toast toast;
    private static CustomClipLoading dialog;
    private static ThreadLocal<SimpleDateFormat> DateLocal = new ThreadLocal<SimpleDateFormat>();
    private static int[] colors;
    private static String COLOR_SPLIT = "_";
    private static DecimalFormat sDecimalFormat;


    /**
     * 为HttpGet 的 url 方便的添加多个name value 参数。
     *
     * @param url
     * @param params
     * @return
     */
    public static String attachHttpGetParams(String url, LinkedHashMap<String, String> params) {

        Iterator<String> keys = params.keySet().iterator();
        Iterator<String> values = params.values().iterator();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("?");

        for (int i = 0; i < params.size(); i++) {
            String value = null;
            try {
                value = URLEncoder.encode(values.next(), "utf-8");
            } catch (Exception e) {
                e.printStackTrace();
            }

            stringBuffer.append(keys.next() + "=" + value);
            if (i != params.size() - 1) {
                stringBuffer.append("&");
            }
            Log.d("Utils", stringBuffer.toString());
        }

        return url + stringBuffer.toString();
    }


    /**
     * 显示进度条
     *
     * @param context
     */
    public static void showLoadingDialog(Context context) {
        if (dialog == null) {
            dialog = new CustomClipLoading(context);
            dialog.setCancelable(false);
        }
        if (!(((BaseActivity) context).isFinishing())) {
            dialog.show();
        }
    }

    /**
     * 隐藏进度条
     *
     * @param
     */
    public static void hideLoadingDialog() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }

    /**
     * 监测蓝牙是否开启
     *
     * @return
     */
    public static boolean isBleEnabled() {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter != null) {
            return bluetoothAdapter.enable();
        }
        return false;
    }

    /**
     * 单纯的截取
     */
    public static String subStr(String str) {
        String a = str.substring(0, 10);
        return a;
    }

    /**
     * 关闭软键盘
     */
    public static void closeSoftKeyboard(Activity pActivity) {
        if (null != pActivity) {
            InputMethodManager im = (InputMethodManager) pActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
            View currentFocus = pActivity.getCurrentFocus();
            // 判断画面是否有获得焦点的项目
            if (null != currentFocus) {
                im.hideSoftInputFromWindow(currentFocus.getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    /**
     * 打卡软键盘
     *
     * @param mEditText 输入框
     * @param mContext  上下文
     */
    public static void openKeybord(EditText mEditText, Context mContext) {
        InputMethodManager imm = (InputMethodManager) mContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
                InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    /**
     * image to base64
     */
    public static String imgToBase64(Bitmap bitmap) {


        ByteArrayOutputStream out = null;
        try {
            out = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 70, out);

            out.flush();
            out.close();

            byte[] imgBytes = out.toByteArray();
            return Base64.encodeToString(imgBytes, Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                out.flush();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Base64 转 Bitmap
     */
    public static Bitmap base64ToBitmap(String base64Data) {
        Bitmap bitmap = null;
        try {
            byte[] bytes;
            bytes = Base64.decode(base64Data, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * MD5加密
     */
    public static String getMd5Value(String sSecret) {
        try {
            // 得到一个信息摘要器
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] result = digest.digest(sSecret.getBytes());
            StringBuffer buffer = new StringBuffer();
            // 把没一个byte 做一个与运算 0xff;
            for (byte b : result) {
                // 与运算
                int number = b & 0xff;// 加盐
                String str = Integer.toHexString(number);
                if (str.length() == 1) {
                    buffer.append("0");
                }
                buffer.append(str);
            }
            // 标准的md5加密后的结果
            return buffer.toString().toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 短时间显示  Toast
     *
     * @param sequence
     */
    public static void showShortToast(CharSequence sequence) {

        if (!StringUtils.isEmpty(String.valueOf(sequence))) {

            if (toast == null) {
                toast = Toast.makeText(MyApp.getmContext(), sequence, Toast.LENGTH_SHORT);


            } else {
                toast.setText(sequence);
            }
            toast.show();
        }

    }

    /**
     * 获取版本名
     *
     * @return 当前应用的版本名
     */
    public static String getVersion(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    //获取屏幕的宽度
    public static int getScreenWidth(Context context) {
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        return display.getWidth();
    }

    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public static String getVersionCode(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            int version = info.versionCode;
            return String.valueOf(version);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


    /*********************************************************************************************************
     * * 函数名称:  boolean isMobileNO(String mobiles)
     * * 功能说明：验证手机号
     * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
     * 联通：130、131、132、152、155、156、185、186
     * 电信：133、153、180、189、（1349卫通）
     * 物联：177、178
     * 总结起来就是第一位必定为1，第二位必定为3或5或7、8，其他位置的可以为0-9
     ********************************************************************************************************/
    public static boolean isMobileNum(String mobiles) {
        String telRegex = "[1][3578]\\d{9}";//"[1]"代表第1位为数字1，"[3578]"代表第二位可以为3、5、7、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobiles)) return false;
        else return mobiles.matches(telRegex);
    }


    public static String md5FromString(String str) {
        String res = "";
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("MD5");
            byte[] bytes = digest.digest(str.getBytes());
            for (byte b : bytes) {
                int temp = b & 0xff;
                String s = Integer.toHexString(temp);
                if (s.length() == 1) {
                    s = "0" + s;
                }
                res += s;
            }
            return res;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * @apiNote 将协议部分转为超链接
     */
    public static void initContent(Context context, String string, TextView view) {
        SpannableStringBuilder spannable = new SpannableStringBuilder(string);
        spannable.setSpan(new ShowTextUtil(context), string.length() - 9, string.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        view.setMovementMethod(LinkMovementMethod.getInstance());
        view.setText(spannable);
    }

    /**
     * float保留两位小数
     * 返回string
     */
    public static String float2(Float f) {
        //构造方法的字符格式这里如果小数不足2位,会以0补足.
        sDecimalFormat = new DecimalFormat(".00");
        String p = sDecimalFormat.format(f);//format 返回的是字符串
        return p;
    }










}
