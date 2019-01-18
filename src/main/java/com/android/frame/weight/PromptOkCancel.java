package com.android.frame.weight;


import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.frame.R;
import com.android.frame.util.StringUtils;


public abstract class PromptOkCancel {

    private Context mContext;

    public PromptOkCancel(Context context) {
        mContext = context;
    }

    public void show(String message, boolean middleFlg) {
        final Dialog dialog = new Dialog(mContext, R.style.My_Dialog);
        dialog.setContentView(R.layout.dialog_normal);
        dialog.setCancelable(false);
        TextView tv_msg =  dialog.findViewById(R.id.tv_msg);

        if (middleFlg) {
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tv_msg.getLayoutParams();
            params.gravity = Gravity.CENTER;
            tv_msg.setLayoutParams(params);
        }


        if (!StringUtils.isEmpty(message.replace(" ",""))) {
            tv_msg.setText(message);
            tv_msg.setVisibility(View.VISIBLE);
        } else {
            tv_msg.setVisibility(View.GONE);
        }

        dialog.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                onCancel();
            }
        });
        dialog.findViewById(R.id.tv_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                onOk();
            }
        });

        dialog.show();
    }



    protected abstract void onOk();

    protected void onCancel() {

    }
}
