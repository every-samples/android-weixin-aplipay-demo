package com.midainc.callassistant.wx;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.tencent.mm.opensdk.constants.Build;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by wei_q on 2018/2/2.
 */

public class WXPay {

    public interface WXPayResultCallBack {

        void onSuccess(); //支付成功

        void onError(int error_code);   //支付失败

        void onCancel();    //支付取消
    }

    public static final int NO_OR_LOW_WX = 1;   //未安装微信或微信版本过低
    public static final int ERROR_PAY_PARAM = 2;  //支付参数错误
    public static final int ERROR_PAY = 3;  //支付失败

    private static WXPay wxPay;

    private IWXAPI mWxApi;
    private WXPayResultCallBack callback;

    public static void init(Context context, String app_id) {
        if (wxPay == null) {
            wxPay = new WXPay(context, app_id);
        }
    }

    public static WXPay getInstance() {
        return wxPay;
    }

    public IWXAPI getWxApi() {
        return mWxApi;
    }

    public WXPay(Context context, String app_id) {
        mWxApi = WXAPIFactory.createWXAPI(context, null);
        mWxApi.registerApp(app_id);
    }

    public void pay(String pay_param, WXPayResultCallBack callBack) {

        this.callback = callBack;

        if (!check()) {
            if (callBack != null) {
                callBack.onError(NO_OR_LOW_WX);
            }
            return;
        }

        JSONObject param;
        try {
            param = new JSONObject(pay_param);
        } catch (JSONException e) {
            e.printStackTrace();
            if (callBack != null) {
                callBack.onError(ERROR_PAY_PARAM);
            }
            return;
        }
        if (TextUtils.isEmpty(param.optString("appid"))
                || TextUtils.isEmpty(param.optString("partnerId"))
                || TextUtils.isEmpty(param.optString("prepayId"))
                || TextUtils.isEmpty(param.optString("packageValue"))
                || TextUtils.isEmpty(param.optString("nonceStr"))
                || TextUtils.isEmpty(param.optString("timeStamp"))
                || TextUtils.isEmpty(param.optString("sign"))) {
            if (callBack != null) {
                callBack.onError(ERROR_PAY_PARAM);
            }
            return;
        }

        StringBuilder builder = new StringBuilder();
        builder.append("appid").append("=").append(param.optString("appid"));
        builder.append("&");
        builder.append("noncestr").append("=").append(param.optString("nonceStr"));
        builder.append("&");
        builder.append("package").append("=").append(param.optString("packageValue"));
        builder.append("&");
        builder.append("partnerid").append("=").append(param.optString("partnerId"));
        builder.append("&");
        builder.append("prepayid").append("=").append(param.optString("prepayId"));
        builder.append("&");
        builder.append("timestamp").append("=").append(param.optString("timeStamp"));
        builder.append("&");
        builder.append("key=");
        builder.append("WkTdMeLOvoYmQr0xYA5zBMaJWbQjUZzw");
        String appSign = encodePassword(builder.toString()).toUpperCase();
        Log.e("weiqi_", "pay: " + builder.toString());
        Log.e("weiqi_", "pay: " + appSign);



        PayReq request = new PayReq();

        request.appId = param.optString("appid");

        request.partnerId = param.optString("partnerId");

        request.prepayId = param.optString("prepayId");

        request.packageValue = param.optString("packageValue");

        request.nonceStr = param.optString("nonceStr");

        request.timeStamp = param.optString("timeStamp");

        request.sign = param.optString("sign");
//        request.sign = appSign;

        mWxApi.sendReq(request);
    }

    //支付回调响应
    public void onResp(int error_code) {
        if (callback == null) {
            return;
        }

        if (error_code == 0) {   //成功
            callback.onSuccess();
        } else if (error_code == -1) {   //错误
            callback.onError(ERROR_PAY);
        } else if (error_code == -2) {   //取消
            callback.onCancel();
        }

        callback = null;
    }

    //检测是否支持微信支付
    private boolean check() {
        return mWxApi.isWXAppInstalled()
                && mWxApi.getWXAppSupportAPI() >= Build.PAY_SUPPORTED_SDK_INT;
    }

    public static String encodePassword(String pwd) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] bytes = digest.digest(pwd.getBytes());
            StringBuffer sb = new StringBuffer();
            for (byte aByte : bytes) {
                String s = Integer.toHexString(0xff & aByte);

                if (s.length() == 1) {
                    sb.append("0").append(s);
                } else {
                    sb.append(s);
                }
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException("buhuifasheng");
        }
    }

}
