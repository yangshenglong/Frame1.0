package com.android.frame.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by yangshenglong on 2017/3/14.
 */

public class SpUtils {
    //token
    public final static String TOKEN = "TOKEN";
    //电话
    public final static String PARENTPHONE = "PARENTPHONE";
    //名字
    public final static String PARENTNAME = "PARENTNAME";
    //头像url
    public final static String AVATAR = "AVATAR";
    //用户名字
    public final static String NAME = "NAME";
    //等级level_key
    public final static String LEVELKEY = "LEVELKEY";
    //生日birth
    public final static String BIRTH = "BIRTH";
    //年龄
    public final static String AGE = "AGE";
    //性别
    public final static String SEX = "SEX";
    //初始体重current_weight
    public final static String CURRENTWEIGHT = "CURRENTWEIGHT";
    //目标体重target_weight
    public final static String TARGETWEIGHT = "TARGETWEIGHT";
    //上次称重last_weight
    public final static String LASTWEIGHT = "LASTWEIGHT";
    //身高height
    public final static String HEIGHT = "HEIGHT";
    //称重时间weight_time
    public final static String WEIGHTTIME = "WEIGHTTIME";
    //健康报告时间scale_weight_time
    public final static String SCALEWEIGHTTIME = "SCALEWEIGHTTIME";
    //用户手机号
    public final static String PHONE = "PHONE";
    //邀请人id    parent_id
    public final static String PARENTID = "PARENTID";
    //是否开启称重提醒
    public final static String SWITCH = "SWITCH";
    //是否开启语音播报
    public final static String VOICE = "VOICE";
    //BMI
    public final static String BMI = "BMI";
    //体重单位
    public final static String UNIT = "UNIT";
    //背景
    public final static String BACKGROUND = "BACKGROUND";
    //是否是第一次登陆
    public static final String ISFIRST = "ISFIRST";


    //保存背景
    public static void saveBackground(Context context, String background) {
        SharedPreferences sp = context.getSharedPreferences(BACKGROUND, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.putString(BACKGROUND, background);
        editor.commit();
    }

    //获取背景
    public static String getBackground(Context context) {
        Context ctx = context;
        SharedPreferences sp = ctx.getSharedPreferences(BACKGROUND, Context.MODE_PRIVATE);
        String background = sp.getString(BACKGROUND, "");
        return background;
    }

    //保存体重单位
    public static void saveUnit(Context context, String unit) {
        SharedPreferences sp = context.getSharedPreferences(UNIT, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.putString(UNIT, unit);
        editor.commit();
    }

    //获取体重单位
    public static String getUnit(Context context) {
        Context ctx = context;
        SharedPreferences sp = ctx.getSharedPreferences(UNIT, Context.MODE_PRIVATE);
        String unit = sp.getString(UNIT, "kg");
        return unit;
    }

    //保存BMI
    public static void saveBMI(Context context, String bmi) {
        SharedPreferences sp = context.getSharedPreferences(BMI, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.putString(BMI, bmi);
        editor.commit();
    }

    //获取BMI
    public static String getBMI(Context context) {
        Context ctx = context;
        SharedPreferences sp = ctx.getSharedPreferences(BMI, Context.MODE_PRIVATE);
        String bmi = sp.getString(BMI, "20");
        return bmi;
    }

    //保存是否第一次登陆
    public static void saveIsFirst(Context context, boolean flg) {
        SharedPreferences pref = context.getSharedPreferences(ISFIRST, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(ISFIRST, flg);
        editor.commit();
    }

    //获取是否第一次登陆
    public static boolean getIsFirst(Context context) {
        return context.getSharedPreferences(ISFIRST, Context.MODE_PRIVATE).getBoolean(ISFIRST, false);
    }

    //保存是否开启语音播报
    public static void saveVoice(Context context, boolean flg) {
        SharedPreferences pref = context.getSharedPreferences(VOICE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(VOICE, flg);
        editor.commit();
    }

    //获取是否开启语音播报
    public static boolean getVoice(Context context) {
        return context.getSharedPreferences(VOICE, Context.MODE_PRIVATE).getBoolean(VOICE, true);
    }

    //保存是否开启称重提醒
    public static void saveSwitch(Context context, boolean flg) {
        SharedPreferences pref = context.getSharedPreferences(SWITCH, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(SWITCH, flg);
        editor.commit();
    }

    //获取是否开启称重提醒
    public static boolean getSwitch(Context context) {
        return context.getSharedPreferences(SWITCH, Context.MODE_PRIVATE).getBoolean(SWITCH, false);
    }

    //*************************************************************//
    //保存邀请人id
    public static void saveParentId(Context context, String parentId) {
        SharedPreferences sp = context.getSharedPreferences(PARENTID, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.putString(PARENTID, parentId);
        editor.commit();
    }

    //获取邀请人id
    public static String getParentId(Context context) {
        Context ctx = context;
        SharedPreferences sp = ctx.getSharedPreferences(PARENTID, Context.MODE_PRIVATE);
        String locationArea = sp.getString(PARENTID, "");
        return locationArea;
    }

    //退出时清空邀请人id
    public static void clearParentId(Context context) {
        Context ctx = context;
        SharedPreferences sp = ctx.getSharedPreferences(PARENTID, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear().commit();
    }
    //*************************************************************//

    //*************************************************************//
    //保存用户手机号
    public static void savePhone(Context context, String phone) {
        SharedPreferences sp = context.getSharedPreferences(PHONE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.putString(PHONE, phone);
        editor.commit();
    }

    //获取用户手机号
    public static String getPhone(Context context) {
        Context ctx = context;
        SharedPreferences sp = ctx.getSharedPreferences(PHONE, Context.MODE_PRIVATE);
        String locationArea = sp.getString(PHONE, "");
        return locationArea;
    }

    //退出时清空用户手机号
    public static void clearPhone(Context context) {
        Context ctx = context;
        SharedPreferences sp = ctx.getSharedPreferences(PHONE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear().commit();
    }
    //*************************************************************//

    //*************************************************************//
    //保存健康报告时间
    public static void saveScaleWeightTime(Context context, String scale_weight_time) {
        SharedPreferences sp = context.getSharedPreferences(SCALEWEIGHTTIME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.putString(SCALEWEIGHTTIME, scale_weight_time);
        editor.commit();
    }

    //获取健康报告时间
    public static String getScaleWeightTime(Context context) {
        Context ctx = context;
        SharedPreferences sp = ctx.getSharedPreferences(SCALEWEIGHTTIME, Context.MODE_PRIVATE);
        String locationArea = sp.getString(SCALEWEIGHTTIME, "");
        return locationArea;
    }

    //退出时清空健康报告时间
    public static void clearScaleWeightTime(Context context) {
        Context ctx = context;
        SharedPreferences sp = ctx.getSharedPreferences(SCALEWEIGHTTIME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear().commit();
    }
    //*************************************************************//

    //*************************************************************//
    //保存称重时间
    public static void saveWeightTime(Context context, String weight_time) {
        if (StringUtils.isEmpty(weight_time)){
            return;
        }
        SharedPreferences sp = context.getSharedPreferences(WEIGHTTIME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.putString(WEIGHTTIME, weight_time);
        editor.commit();
    }

    //获取称重时间
    public static String getWeightTime(Context context) {
        Context ctx = context;
        SharedPreferences sp = ctx.getSharedPreferences(WEIGHTTIME, Context.MODE_PRIVATE);
        String locationArea = sp.getString(WEIGHTTIME, "");
        return locationArea;
    }

    //退出时清空称重时间
    public static void clearWeightTime(Context context) {
        Context ctx = context;
        SharedPreferences sp = ctx.getSharedPreferences(WEIGHTTIME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear().commit();
    }
    //*************************************************************//


    //*************************************************************//
    //保存身高
    public static void saveHeight(Context context, String height) {

        SharedPreferences sp = context.getSharedPreferences(HEIGHT, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.putString(HEIGHT, height);
        editor.commit();
    }

    //获取身高
    public static String getHeight(Context context) {
        Context ctx = context;
        SharedPreferences sp = ctx.getSharedPreferences(HEIGHT, Context.MODE_PRIVATE);
        String locationArea = sp.getString(HEIGHT, "");
        return locationArea;
    }

    //退出时清空身高
    public static void clearHeight(Context context) {
        Context ctx = context;
        SharedPreferences sp = ctx.getSharedPreferences(HEIGHT, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear().commit();
    }
    //*************************************************************//


    //*************************************************************//
    //保存用户上次称重
    public static void saveLastWeight(Context context, String last_weight) {
        if (StringUtils.isEmpty(last_weight)){
            return;
        }
        SharedPreferences sp = context.getSharedPreferences(LASTWEIGHT, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.putString(LASTWEIGHT, last_weight);
        editor.commit();
    }

    //获取用户上次称重
    public static String getLastWeight(Context context) {
        Context ctx = context;
        SharedPreferences sp = ctx.getSharedPreferences(LASTWEIGHT, Context.MODE_PRIVATE);
        String locationArea = sp.getString(LASTWEIGHT, "70");
        return locationArea;
    }

    //退出时清空用户上次称重
    public static void clearLastWeight(Context context) {
        Context ctx = context;
        SharedPreferences sp = ctx.getSharedPreferences(LASTWEIGHT, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear().commit();
    }
    //*************************************************************//


    //*************************************************************//
    //保存用户目标体重
    public static void saveTargetWeight(Context context, String target_weight) {
        if (StringUtils.isEmpty(target_weight)){
            return;
        }
        SharedPreferences sp = context.getSharedPreferences(TARGETWEIGHT, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.putString(TARGETWEIGHT, target_weight);
        editor.commit();
    }

    //获取用户目标体重
    public static String getTargetWeight(Context context) {
        Context ctx = context;
        SharedPreferences sp = ctx.getSharedPreferences(TARGETWEIGHT, Context.MODE_PRIVATE);
        String locationArea = sp.getString(TARGETWEIGHT, "60");
        return locationArea;
    }

    //退出时清空用户目标体重
    public static void clearTargetWeight(Context context) {
        Context ctx = context;
        SharedPreferences sp = ctx.getSharedPreferences(TARGETWEIGHT, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear().commit();
    }
    //*************************************************************//


    //*************************************************************//
    //保存用户初始体重
    public static void saveCurrentWeight(Context context, String current_weight) {
        if (StringUtils.isEmpty(current_weight)){
            return;
        }
        SharedPreferences sp = context.getSharedPreferences(CURRENTWEIGHT, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.putString(CURRENTWEIGHT, current_weight);
        editor.commit();
    }

    //获取用户初始体重
    public static String getCurrentWeight(Context context) {
        Context ctx = context;
        SharedPreferences sp = ctx.getSharedPreferences(CURRENTWEIGHT, Context.MODE_PRIVATE);
        String locationArea = sp.getString(CURRENTWEIGHT, "70");
        return locationArea;
    }

    //退出时清空用户初始体重
    public static void clearCurrentWeight(Context context) {
        Context ctx = context;
        SharedPreferences sp = ctx.getSharedPreferences(CURRENTWEIGHT, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear().commit();
    }
    //*************************************************************//


    //*************************************************************//
    //保存用户性别
    public static void saveSex(Context context, String sex) {
        SharedPreferences sp = context.getSharedPreferences(SEX, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.putString(SEX, sex);
        editor.commit();
    }

    //获取用户性别
    public static String getSex(Context context) {
        Context ctx = context;
        SharedPreferences sp = ctx.getSharedPreferences(SEX, Context.MODE_PRIVATE);
        String locationArea = sp.getString(SEX, "male");
        return locationArea;
    }

    //退出时清空用户性别
    public static void clearSex(Context context) {
        Context ctx = context;
        SharedPreferences sp = ctx.getSharedPreferences(SEX, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear().commit();
    }
    //*************************************************************//

    //*************************************************************//
    //保存用户年龄
    public static void saveAge(Context context, String age) {
        if (StringUtils.isEmpty(age)){
            return;
        }
        SharedPreferences sp = context.getSharedPreferences(AGE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.putString(AGE, age);
        editor.commit();
    }

    //获取用户年龄
    public static String getAge(Context context) {
        Context ctx = context;
        SharedPreferences sp = ctx.getSharedPreferences(AGE, Context.MODE_PRIVATE);
        String locationArea = sp.getString(AGE, "28");
        return locationArea;
    }

    //退出时清空用户年龄
    public static void clearAge(Context context) {
        Context ctx = context;
        SharedPreferences sp = ctx.getSharedPreferences(AGE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear().commit();
    }
    //*************************************************************//

    //*************************************************************//
    //保存用户生日
    public static void saveBirth(Context context, String birth) {
        if (StringUtils.isEmpty(birth)){
            return;
        }
        SharedPreferences sp = context.getSharedPreferences(BIRTH, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.putString(BIRTH, birth);
        editor.commit();
    }

    //获取用户生日
    public static String getBirth(Context context) {
        Context ctx = context;
        SharedPreferences sp = ctx.getSharedPreferences(BIRTH, Context.MODE_PRIVATE);
        String locationArea = sp.getString(BIRTH, "1990-01-01");
        return locationArea;
    }

    //退出时清空用户生日
    public static void clearBirth(Context context) {
        Context ctx = context;
        SharedPreferences sp = ctx.getSharedPreferences(BIRTH, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear().commit();
    }
    //*************************************************************//


    //*************************************************************//
    //保存用户等级
    public static void saveLevelKey(Context context, String level_key) {
        SharedPreferences sp = context.getSharedPreferences(LEVELKEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.putString(LEVELKEY, level_key);
        editor.commit();
    }

    //获取用户等级
    public static String getLevelKey(Context context) {
        Context ctx = context;
        SharedPreferences sp = ctx.getSharedPreferences(LEVELKEY, Context.MODE_PRIVATE);
        String locationArea = sp.getString(LEVELKEY, "");
        return locationArea;
    }

    //退出时清空用户等级
    public static void clearLevelKey(Context context) {
        Context ctx = context;
        SharedPreferences sp = ctx.getSharedPreferences(LEVELKEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear().commit();
    }

    //*************************************************************//


    //*************************************************************//
    //保存用户名字
    public static void saveName(Context context, String name) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.putString(NAME, name);
        editor.commit();
    }

    //获取用户名字
    public static String getName(Context context) {
        Context ctx = context;
        SharedPreferences sp = ctx.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        String locationArea = sp.getString(NAME, "");
        return locationArea;
    }

    //退出时清空用户名字
    public static void clearName(Context context) {
        Context ctx = context;
        SharedPreferences sp = ctx.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear().commit();
    }

    //*************************************************************//


    //*************************************************************//
    //保存token
    public static void saveToken(Context context, String token) {
        SharedPreferences sp = context.getSharedPreferences(TOKEN, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.putString(TOKEN, token);
        editor.commit();
    }

    //获取token
    public static String getToken(Context context) {
        Context ctx = context;
        SharedPreferences sp = ctx.getSharedPreferences(TOKEN, Context.MODE_PRIVATE);
        String locationArea = sp.getString(TOKEN, "");
        return locationArea;
    }

    //退出时清空token
    public static void clearToken(Context context) {
        Context ctx = context;
        SharedPreferences sp = ctx.getSharedPreferences(TOKEN, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear().commit();
    }
    //*************************************************************//


    //*************************************************************//
    //保存邀请人电话
    public static void saveParentTel(Context context, String parentPhone) {
        SharedPreferences sp = context.getSharedPreferences(PARENTPHONE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.putString(PARENTPHONE, parentPhone);
        editor.commit();
    }

    //获取邀请人电话
    public static String getParentTel(Context context) {
        Context ctx = context;
        SharedPreferences sp = ctx.getSharedPreferences(PARENTPHONE, Context.MODE_PRIVATE);
        String tel = sp.getString(PARENTPHONE, "");
        return tel;
    }

    //退出时清空邀请人电话
    public static void clearParentTel(Context context) {
        Context ctx = context;
        SharedPreferences sp = ctx.getSharedPreferences(PARENTPHONE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear().commit();
    }
    //*************************************************************//


    //*************************************************************//

    //保存邀请人name
    public static void saveParentNmae(Context context, String parentName) {
        SharedPreferences sp = context.getSharedPreferences(PARENTNAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.putString(PARENTNAME, parentName);
        editor.commit();
    }

    //获取邀请人name
    public static String getParentName(Context context) {
        Context ctx = context;
        SharedPreferences sp = ctx.getSharedPreferences(PARENTNAME, Context.MODE_PRIVATE);
        String locationArea = sp.getString(PARENTNAME, "");
        return locationArea;
    }

    //退出时清空邀请人name
    public static void clearParentName(Context context) {
        Context ctx = context;
        SharedPreferences sp = ctx.getSharedPreferences(PARENTNAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear().commit();
    }
    //*************************************************************//


    //*************************************************************//

    //保存头像url
    public static void saveAvatar(Context context, String url) {
        SharedPreferences sp = context.getSharedPreferences(AVATAR, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.putString(AVATAR, url);
        editor.commit();
    }

    //获取头像url
    public static String getAvatar(Context context) {
        Context ctx = context;
        SharedPreferences sp = ctx.getSharedPreferences(AVATAR, Context.MODE_PRIVATE);
        String locationArea = sp.getString(AVATAR, "");
        return locationArea;
    }

    //退出时清空头像url
    public static void clearAvatar(Context context) {
        Context ctx = context;
        SharedPreferences sp = ctx.getSharedPreferences(AVATAR, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear().commit();
    }
    //*************************************************************//


}
