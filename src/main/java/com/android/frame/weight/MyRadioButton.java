package com.android.frame.weight;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.RadioButton;

import com.android.frame.R;


/**
 * Created by YangShengLong
 * author:大魔王老昜
 * data:on 2018/10/24
 */

public class MyRadioButton extends RadioButton {
    private Drawable drawable;

    public MyRadioButton(Context context) {
        super(context);
    }


    public MyRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyRadioButton);//获取我们定义的属性
        drawable = typedArray.getDrawable(R.styleable.MyRadioButton_drawableTop);
        drawable.setBounds(0, 0, 60, 60);
        setCompoundDrawables(null, drawable, null, null);
    }

}
