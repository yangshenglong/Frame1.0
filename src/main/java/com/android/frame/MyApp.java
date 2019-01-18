package com.android.frame;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;


import com.android.frame.net.ZhyConfig;

import java.io.File;

public class MyApp extends Application {
    private static final String CACHE_ROOT_DIR = ZhyConfig.EXT_STORAGE_ROOT + File.separator + ZhyConfig.CACHE_ROOT_NAME;
    public static final String CACHE_PIC_ROOT_DIR = CACHE_ROOT_DIR + File.separator + ZhyConfig.CACHE_PIC_ROOT_NAME;
    public static final String CACHE_ROOT_CACHE_DIR = CACHE_ROOT_DIR + File.separator + ZhyConfig.CACHE_ROOT_CACHE_NAME;
    private static Context mContext;



    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }
        buildCacheDir();


    }
    //对外提供一个获取Context对象的方法
    public static Context getmContext() {
        return mContext;
    }

    public static void buildCacheDir() {
        File rootDir = new File(CACHE_ROOT_DIR);
        if (!rootDir.exists()) {
            rootDir.mkdir();
        }

        File cacheDir = new File(CACHE_ROOT_CACHE_DIR);
        if (!cacheDir.exists()) {
            cacheDir.mkdir();
        }

        File picRootDir = new File(CACHE_PIC_ROOT_DIR);
        if (!picRootDir.exists()) {
            picRootDir.mkdir();
        }
    }


}
