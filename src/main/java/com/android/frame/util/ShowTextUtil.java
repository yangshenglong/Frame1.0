package com.android.frame.util;

import android.content.Context;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

import com.android.frame.R;


public class ShowTextUtil extends ClickableSpan {

    private Context context;
    public ShowTextUtil(Context context) {
        this.context = context;
    }


    @Override
    public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);
        //设置文本的颜色
        ds.setColor(context.getResources().getColor(R.color.yellow));
        //超链接形式的下划线，false 表示不显示下划线，true表示显示下划线
        ds.setUnderlineText(false);
    }

    @Override
    public void onClick(View widget) {

    }
}
