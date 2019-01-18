package com.android.frame.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.frame.R;
import com.android.frame.util.AppUtil;
import com.android.frame.weight.CustomClipLoading;


/**
 * Created by yangshenglong on 16/11/22.
 */

public abstract class BaseActivity extends AppCompatActivity {
    //返回键
    public RelativeLayout mIv_back;

    //title文字
    public TextView mTv_title;
    // 加载
    private CustomClipLoading dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //单实例作用
        AppUtil.INSTANCE.addActivity(this);
        //禁止横屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(setLayout());
        initView();
        initData();
    }

    //设置布局的
    public abstract int setLayout();

    //初始化组件
    public abstract void initView();

    //初始化数据的方法
    public abstract void initData();

    public <T extends View> T bindView(int id) {
        return (T) findViewById(id);
    }

    //初始化title
    public void initTitle(String title, boolean isShowFinish) {
        mIv_back = bindView(R.id.iv_back);
        mTv_title = bindView(R.id.tv_title);
        //title文字
        if (mTv_title != null) {
            mTv_title.setText(title);
        }
        //是否显示左侧返回键
        if (mIv_back != null) {
            //返回键
            finish();
        }
    }


    /**
     * 显示进度条
     */
    protected void showLoadingDialog() {
        if (dialog == null) {
            dialog = new CustomClipLoading(this);
            dialog.setOnOutSide(true);
        }
        if (!isFinishing()) {
            dialog.show();
        }
    }

    /**
     * 隐藏进度条
     */
    protected void hideLoadingDialog() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }


    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //防止dialog所依附的activity已经销毁,在调用dialog.dismiss会抛出IllegalArgumentException异常
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }
}
