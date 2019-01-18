package com.android.frame.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.frame.weight.CustomClipLoading;


/**
 * Created by yangshenglong on 16/11/22.
 */

public abstract class BaseFragment extends Fragment {
    public View rootView;
    private Context context;
    // 加载
    private CustomClipLoading dialog;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (rootView == null) {
            rootView=inflater.inflate(setLayout(),container,false);
            initView(rootView);
            initData();
        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        return rootView;
    }



    //绑定布局
    public abstract int setLayout();
    //初始化
    public abstract void initView(View view);
    //逻辑代码
    public abstract void initData();

    public <T extends View>  T bindView(int id){
        return (T)getActivity().findViewById(id);
    }


    /**
     * 显示进度条
     */
    protected void showLoadingDialog() {
        if (dialog == null) {
            dialog = new CustomClipLoading(getActivity());
        }
        if (!getActivity().isFinishing()) {
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
    public void onDestroy() {
        super.onDestroy();
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }
}
