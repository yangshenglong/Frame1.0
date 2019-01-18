package com.android.frame.net;

import android.os.Environment;

public class ZhyConfig {

    //测试账号13113113113 988731   15694111220
    //线上环境
    public final static String PRE = "http://shop.tangxiaoyao.com";

    //测试环境
//    public final static String PRE = "http://shop.txy.wityun.com";


    /**
     * 本地存储的根路径
     */
    public static final String EXT_STORAGE_ROOT = Environment.getExternalStorageDirectory().getAbsolutePath();

    /**
     * 本地存储根目录名
     */
    public static final String CACHE_ROOT_NAME = "TangXiaoYao";

    /**
     * 本地存储缓存根目录名
     */
    public static final String CACHE_ROOT_CACHE_NAME = "cache";

    /**
     * apk安装包名称
     */
    public static final String APK_NAME = "TangXiaoYao.apk";

    /**
     * 本地存储图片根目录名
     */
    public static final String CACHE_PIC_ROOT_NAME = "TangXiaoYao";
    public static final String ACTION_BASE_PREFIX = "App.action.";


    //------------------------------------------------------webview-------------------------------------------------------------//
    //商城页面
    public static final String MALLURL = PRE + "/products";
    //财富页面
    public static final String WEALTHURL = PRE + "/my/bills/new_bills";
    //减重记录
    public static final String WEIGHT_LOSS = PRE + "/my/weights/health_history";
    //饮食记录
    public static final String DIET = PRE + "/my/dietary_records";
    //BMI计算器
    public static final String BMI = PRE + "/my/weights/calculator";
    //购买称
    public static final String BUYWEIGHT = PRE + "/products/2";
    //收货地址
    public static final String ADDRESSES = PRE + "/my/receiving_addresses";
    //支付密码
    public static final String PAYPASSWORD = PRE + "/my/settings/password";
    //用户协议/pages/agreement
    public static final String AGREEMENT = PRE + "/pages/agreement";
    //消息列表
    public static final String MESSAGE = PRE + "/my/notifications";
    //购买订单
    public static final String ORDERLIST = PRE + "/my/orders";
    //健康报告
    public static final String SCALEWEIGHT = PRE + "/my/weights/health_report_g";
    //web登录
    public static final String WEBLOGIN = PRE + "/api/v1/identities/web_login?";
    //邀请
    public static final String PROMOTE = PRE + "/my/promote/product";

    //------------------------------------------------------end-------------------------------------------------------------//


    //------------------------------------------------------接口-------------------------------------------------------------//
    //微信支付
    public static final String WEIXINPAY = PRE + "payments/wechat_notify";
    //获取验证码
    public static final String GETCODE = PRE + "/api/v2/identities/get_code";
    //输入验证码登录
    public static final String INPUTCODE = PRE + "/api/v2/identities";
    //修改昵称 性别  姓名
    public static final String CHANGEUSER = PRE + "/api/v2/users";
    //版本校验
    public static final String VERSIONCODE = PRE + "/api/v2/app_clients/new_version";
    //减重计划
    public static final String LOSSWEIGHTPLAN = PRE + "/api/v2/body_infos";
    //上传健康报告
    public static final String UPLOADWEIGHT = PRE + "/api/v2/weights";
    //------------------------------------------------------end-------------------------------------------------------------//
}