package com.android.frame.net;

import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.android.frame.Constant;
import com.android.frame.MyApp;
import com.android.frame.R;
import com.android.frame.util.SpUtils;
import com.android.frame.util.Utils;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

import static java.lang.String.valueOf;


/**
 * Created by VolleyYang on 2017/2/10.
 */

public class NetOkTool implements NetInterface {
    private OkHttpClient mOkHttpClient;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private Gson mGson;
    public static final MediaType JSON =
            MediaType.parse("application/json; charset=utf-8");
    private String token = "token";
    private String user_client_token = "user_client_token";
    String rstError;
    //接口回调错误码
    int rstCode;

    public NetOkTool() {
        //初始化Gson对象
        mGson = new Gson();
        //初始化对象

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        mOkHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .retryOnConnectionFailure(true)
                .addInterceptor(new TokenInterceptor())//Token拦截
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .cache(new Cache(Environment.getExternalStorageDirectory(), 10 * 1024 * 1024))
                .build();
    }


    /**
     * 调用接口，有错误码判断
     */

    @Override
    public <T> void startPostRequest(final Context context, final String url, final Map map, final Class<T> tClass, final NetCallBack<T> callBack) {

        final String jsonStr = mGson.toJson(map);

        //接口回调错误信息
        RequestBody requestBody = RequestBody.create(JSON, jsonStr);
        Request request = new Request.Builder().url(url)
                .post(requestBody)
                .build();
        Utils.showLoadingDialog(context);
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                Utils.hideLoadingDialog();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onError(MyApp.getmContext().getResources().getString(R.string.opreation_failure));
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Utils.hideLoadingDialog();
                String str = response.body().string();
                Log.d("NetOkTool", str);
                final T result;
                try {
                    JSONObject jsonObject = new JSONObject(str);

                    rstCode = jsonObject.getInt("code");

                    if (Constant.KEY_SUCCESS == rstCode) {
                        result = mGson.fromJson(str, tClass);
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                callBack.onSuccess(result);
                            }
                        });
                    } else {
                        rstError = jsonObject.getString("message");
                        callBack.onError(rstError);
                    }
                } catch (final Throwable e) {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.onError(rstError);
                        }
                    });
                }
            }
        });
    }


    /**
     * 上传文件并携带其他参数
     * @param context
     * @param url
     * @param file
     * @param map
     * @param tClass
     * @param callBack
     * @param <T>
     */
    @Override
    public <T> void startFileRequest(Context context, String url, File file, Map map, final Class<T> tClass, final NetCallBack<T> callBack) {
        MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if (file != null) {
            // MediaType.parse() 里面是上传的文件类型。
            RequestBody body = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            // 参数分别为， 请求key ，文件名称 ， RequestBody
            requestBody.addFormDataPart("file", file.getName(), body);
        }
        if (map != null) {
            Set<Map.Entry<String, String>> entries = map.entrySet();
            for (Map.Entry entry : entries) {
                String key = valueOf(entry.getKey());
                String value = valueOf(entry.getValue());
                requestBody.addFormDataPart(key, value);
            }
        }
        Request request = new Request.Builder().url(url).post(requestBody.build()).build();
//        RequestBody requestBody = new MultipartBody.Builder()
//                .setType(MultipartBody.FORM)
//                .addFormDataPart("file", file.getName(),
//                        RequestBody.create(MediaType.parse("multipart/form-data"), file))
//                .build();
//
//        Request request = new Request.Builder()
//                .url(url)
//                .post(requestBody)
//                .build();


        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Utils.hideLoadingDialog();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onError(MyApp.getmContext().getResources().getString(R.string.opreation_failure));
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Utils.hideLoadingDialog();
                String str = response.body().string();
                final T result;
                try {
                    JSONObject jsonObject = new JSONObject(str);

                    rstCode = jsonObject.getInt("code");

                    if (Constant.KEY_SUCCESS == rstCode) {
                        result = mGson.fromJson(str, tClass);
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                callBack.onSuccess(result);
                            }
                        });
                    } else {
                        rstError = jsonObject.getString("message");
                        callBack.onError(rstError);
                    }
                } catch (final Throwable e) {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.onError(rstError);
                        }
                    });
                }
            }
        });
    }


    /**
     * get 请求 有返回值判断
     *
     * @param context
     * @param url
     * @param tClass
     * @param callBack
     * @param <T>
     */
    @Override
    public <T> void startGetRequest(final Context context, final String url, final Class<T> tClass, final NetCallBack<T> callBack) {

        Request request = new Request.Builder().url(url)
                .get()
                .addHeader(token, Constant.DEFAULTTOKEN)
                .addHeader(user_client_token, SpUtils.getToken(context))
                .build();

        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {

                        Utils.hideLoadingDialog();
                        callBack.onError(MyApp.getmContext().getResources().getString(R.string.opreation_failure));
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();

                final T result;
                Utils.hideLoadingDialog();
                try {
                    JSONObject jsonObject = new JSONObject(str);

                    rstCode = jsonObject.getInt("code");
                    rstError = jsonObject.getString("message");
                    if (Constant.KEY_SUCCESS == rstCode) {
                        result = mGson.fromJson(str, tClass);

                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                callBack.onSuccess(result);
                            }
                        });
                    } else {
                        rstError = jsonObject.getString("message");
                        callBack.onError(rstError);
                    }
                } catch (final Throwable e) {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.onError(rstError);
                        }
                    });
                }
            }
        });
    }

}
