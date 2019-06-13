package com.nsi.okexsay.http;

public class StaticField {
    public static String IsMerchant = "";
    // 测试环境
    public static String HOST_BASE = "http://www.51integral.com";
    // public static String HOST_BASE = "192.168.1.108:2181";
    public static String PROMOTE_URL = "https://zhongjian69970.oss-cn-hongkong.aliyuncs.com/upload/APPTEST/integral-test.apk";

    // 生产环境
//    public static String HOST_BASE= "http://www.integralbb.com";
//    public static String PROMOTE_URL = "https://zhongjian69970.oss-cn-hongkong.aliyuncs.com/upload/APP/Integral.apk";


    // 获取国家地区数据源
    public static String GET_INTERNATIONALS = HOST_BASE + "/m/user/reg/getInternationals.html";
    // 校验手机号重复
    public static String CHECK_REGNAME = HOST_BASE + "/m/user/reg/chcekregname.html";
    // 提交注册
    public static String SAVE_REGISTER = HOST_BASE + "/m/user/reg/index.html";
    // 短信发送
    public static String GET_VERIFY_CODE = HOST_BASE + "/m/user/sendMsg.html";
    // 登录
    public static String LOGIN = HOST_BASE + "/m/user/login/index.html";
    // 实名认证提交
    public static String VALIDATE_INDTITY_TEST = HOST_BASE + "/m/user/validateIdentity.html";
    // 验证手机号是否存在
    public static String PHONE_VALIDATION = HOST_BASE + "/m/user/validate/resetPhoneValidation.html";
    // 找回密码
    public static String RESET_PASSWORD = HOST_BASE + "/m/user/validate/resetPasswordPhone.html";

    // 兑换通用积分
    public static String TONG_YONG_INTEGRAL = HOST_BASE + "/m/financial/tongyongintegral.html";
    // 保存兑换数据
    public static String SAVE_RECORD = HOST_BASE + "/m/financial/saveVirtualtocptRecord.html";
    // 查询兑换记录
    public static String RECORD_HISTORY = HOST_BASE + "/m/financial/virtualtocptRecordList.html";
    // 预付款
    public static String PRE_PAY = HOST_BASE + "/m/financial/withdrawBtc.html";
    // 付款
    public static String PAY_ICON = HOST_BASE + "/m/financial/modifyWithdrawBtcAddr.html";
    // 收款
    public static String RECHARGE_BTC = HOST_BASE + "/m/financial/rechargeBtc.html";
    // 消费明细
    public static String PAY_COLLETION_HISTORY = HOST_BASE + "/m/financial/recordsConsumption.html";
    // 首页总资产
    public static String TOTAL_ICON = HOST_BASE + "/m/user/fassets.html";


    // 购买，出售广告列表
    public static String ADVERTISEMENT = HOST_BASE + "/m/c2c/c2c.html";
    // 个人广告列表
    public static String TOGRMMONE = HOST_BASE + "/m/c2c/toGrmmOne.html";
    // 个人广告列表
    public static String OWN_ADVERTISEMENT = HOST_BASE + "/m/c2c/toGrmm.html";
    // 加载下单页面数据（查询出选择的广告数据）
    public static String TO_XIADAN = HOST_BASE + "/m/c2c/c2c/toXiadan.html";
    // 下单
    public static String TO_ZHIFU = HOST_BASE + "/m/c2c/c2c/xiadan.html";
    // 撤销订单
    public static String TO_DEL = HOST_BASE + "/m/c2c/c2c/del.html";
    //发布广告页面加载法币类型下拉菜单
    public static String TO_FBGG = HOST_BASE + "/m/c2c/c2c/toFbgg.html";
    //交易页面加载数据
    public static String TO_ZHIFUS = HOST_BASE + "/m/c2c/c2c/toZhifu.html";
    //交易页面点击付款或者付币
    public static String YFK = HOST_BASE + "/m/c2c/c2c/yfk.html";
    //取消订单
    public static String CANCEL = HOST_BASE + "/m/c2c/c2c/cancel.html";
    //发布广告
    public static String FBGG = HOST_BASE + "/m/c2c/c2c/fbgg.html";
    //发布广告时判断是否超过质押
    public static String CHECKCG = HOST_BASE + "/m/c2c/c2c/checkCg.html";
    //获取操作列表数据
    public static String TO_MMCZ = HOST_BASE + "/m/c2c/c2c/toMmcz.html";
    //放币
    public static String UPDATEDATA = HOST_BASE + "/m/c2c/c2c/updateData.html";
    //获取订单列表
    public static String TO_DAJL = HOST_BASE + "/m/c2c/c2c/toDdjl.html";
    //获取银行卡所有数据
    public static String SHOW_BANK = HOST_BASE + "/m/c2c/c2c/showBank.html";
    //获取微信所有数据
    public static String SHOW_WX = HOST_BASE + "/m/c2c/c2c/showWx.html";
    //获取支付宝所有数据
    public static String SHOW_ZHIFB = HOST_BASE + "/m/c2c/c2c/showZfb.html";
    //买卖操作页面--右侧按钮时调用，用于加载弹出框内的信息
    public static String SEARCHDATA = HOST_BASE + "/m/c2c/c2c/searchData.html";
    //买卖操作页面--右侧按钮时调用，用于加载弹出框内的信息
    //   public static String SEARCHDATA= HOST_BASE + "/m/c2c/c2c/searchData.html";
    //删除收款方式银行卡
    public static String DELETE_BANK = HOST_BASE + "/m/c2c/c2c/deleteBankAddress.html";
    public static String DELETE_ZHIFUBAO_WEIXIN = HOST_BASE + "/m/c2c/c2c/deleteZfb_Wx.html";
    //获取添加收款方式验证码
    public static String GET_PHONE_CODE = HOST_BASE + "/m/c2c/zfbWxUser/sendMsg.html";
    //添加银行卡
    public static String ADD_BANK = HOST_BASE + "/m/c2c/c2c/updateOutAddress.html";
    //添加支付宝或微信
    public static String ADD_ALIPAY = HOST_BASE + "/m/c2c/zfbWxUser/sendMsg.html";
    //获取个人资产
    public static String FASSETS = HOST_BASE + "/m/user/fassets.html";
    //添加支付宝微信
    public static String UPDATEOUTALIPAY = HOST_BASE + "/m/c2c/c2c/updateOutAlipay.html";
    //所有的法币类型数据 +查询用户是否为商户+银行名称
    public static String SHOWFBLX = HOST_BASE + "/m/c2c/c2c/showFblx.html";
    //微信支付宝判断是否是商户
    public static String FMERCHAT = HOST_BASE + "/m/c2c/c2c/fmerchant.html";
    public static String HOME_NEWS_LIST = HOST_BASE + "/m/c2c/farticletypeErji.html";
    //币币交易行情实时数据获取(币种，最新价，24H涨跌幅，24H最低，24H最高，24H成交量)
    public static String INDEXMARKET = HOST_BASE + "/m/exchange/indexmarket.html";
}
