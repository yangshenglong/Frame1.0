package com.android.frame.net;

import android.content.Context;

import java.io.File;
import java.util.Map;

/**
 * Created by yangshenglong on 2017/2/10.
 */
public class NetTool implements NetInterface {

    private static NetTool sNetTool;
    private NetInterface mInterface;

    public static NetTool getInstance() {
        //双重校验锁单例模式
        if (sNetTool == null) {
            synchronized (NetTool.class) {
                if (sNetTool == null) {
                    sNetTool = new NetTool();
                }
            }
        }
        return sNetTool;
    }

    private NetTool() {
        mInterface = new NetOkTool();
    }


    @Override
    public <T> void startPostRequest(Context context, String url, Map map, Class<T> tClass, NetCallBack<T> callBack) {
        mInterface.startPostRequest(context, url, map, tClass, callBack);
    }


    @Override
    public <T> void startGetRequest(Context context, String url, Class<T> tClass, NetCallBack<T> callBack) {
        mInterface.startGetRequest(context, url, tClass, callBack);

    }

    @Override
    public <T> void startFileRequest(Context context, String url, File file, Map map, Class<T> tClass, NetCallBack<T> callBack) {
        mInterface.startFileRequest(context, url, file, map, tClass, callBack);
    }


}
