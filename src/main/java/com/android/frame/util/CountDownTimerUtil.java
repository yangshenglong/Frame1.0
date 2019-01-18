package com.android.frame.util;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.widget.Button;

import com.android.frame.R;


/**
 * @author yangshenglong on 2018/8/12
 * @version 1.0
 * @apiNote 倒计时工具类
 */
public class CountDownTimerUtil extends CountDownTimer {
    /**显示倒计时的组件*/
    private Button getCode;
    private Context mContext;
    /**
     * @author sunLei on 2018/8/12
     * @apiNote 倒计时参数设置,从start()开始倒计时,onFinish()监听倒计时完成
     * @param getCode 倒计时按钮
     * @param millisInFuture 倒计时时间毫秒值
     * @param countDownInterval onTick()回调的间隔
     * */
    public CountDownTimerUtil(Button getCode, long millisInFuture, long countDownInterval, Context context){
        super(millisInFuture,countDownInterval);
        this.getCode=getCode;
        this.mContext = context;
    }

    @Override
    public void onTick(long l) {
        //按钮不可点击
        getCode.setClickable(false);
        //显示倒计时
        getCode.setText(l/1000+"秒后重新发送");
        getCode.setTextColor(ContextCompat.getColor(mContext, R.color.textqiangray));
    }

    @Override
    public void onFinish() {
        //重置
        getCode.setText("重新发送");
        getCode.setClickable(true);
        getCode.setTextColor(ContextCompat.getColor(mContext, R.color.textqiangray));
    }
}
