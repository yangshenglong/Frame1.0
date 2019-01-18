package com.android.frame.weight.popup;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.android.frame.R;
import com.android.frame.weight.interfaces.DataCallBack;


/**
 * Created by Administrator on 2018/3/16 0016.
 * 申请权限弹窗
 */

public class InstallPermissionPopup {
    private PopupWindow mPopupWindow;
    private Context mContext;

    private DataCallBack dataCallBack;

    public void setDataCallBack(DataCallBack dataCallBack) {
        this.dataCallBack = dataCallBack;
    }

    public InstallPermissionPopup(Context mContext) {
        this.mContext = mContext;
    }

    public void showPopup() {

        View contentView = LayoutInflater.from(mContext).inflate(R.layout.pop_permission, null);
        mPopupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mPopupWindow.setContentView(contentView);

        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());

        TextView mTextViewCacle = contentView.findViewById(R.id.tv_cancle);

        TextView mTextViewOk = contentView.findViewById(R.id.tv_ok);

        mTextViewCacle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canclePopwindow();
            }
        });
        mTextViewOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dataCallBack.getDataCallBack();

                canclePopwindow();
            }
        });


        backgroundAlpha(0.5f);
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
            }
        });
        mPopupWindow.showAtLocation(contentView, Gravity.CENTER, 0, 0);
    }




    public void canclePopwindow() {
        if (mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        }

    }

    public boolean getPopShowing() {
        return mPopupWindow.isShowing();
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = ((Activity) mContext).getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        ((Activity) mContext).getWindow().setAttributes(lp);
    }


}
