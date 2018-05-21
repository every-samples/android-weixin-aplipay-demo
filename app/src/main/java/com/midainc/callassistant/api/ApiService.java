package com.midainc.callassistant.api;


import com.midainc.callassistant.alipay.AliReFundBody;
import com.midainc.callassistant.wx.WxReFundBody;
import com.midainc.callassistant.wx.WxPayData;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * 来电助手接口
 * <p>
 * status 状态码
 * 0：成功
 * 1：参数错误
 * 2：该用户已通过其他渠道发展，订购失败
 * 3：订购成功，（呼转设置失败，该用户已设置了其他的呼转号码）
 * 4：订购成功，（呼转设置失败，呼转接口本身失败）
 * 5：用户尚未订购，退订失败
 * 7：该用户已经订购，此次订购失败
 * 8：该用户已通过其他渠道发展，订购失败
 * 11：IP不合法
 * 12：业务平台代码不合法
 * 13：漏话通知消息流水号不存在
 * 99：系统错误，请联系管理员
 * 100：订购成功（设置成功，查询失败）
 */

public interface ApiService {



    @POST("http://115.159.26.196:8080/mida/WeChat/appPay")
    Observable<ResponseBody> payWx(
            @Body WxPayData wxPayData
    );

    @POST("http://115.159.26.196:8080/mida/aliPay/createOrder")
    Observable<ResponseBody> payAli(
            @Body WxPayData wxPayData
    );

    @POST("http://115.159.26.196:8080/mida/WeChat/refund")
    Observable<ResponseBody> refundWx(
            @Body WxReFundBody wxReFundBody
    );

    @POST("http://115.159.26.196:8080/mida/aliPay/refundOrder")
    Observable<ResponseBody> refundAli(
            @Body AliReFundBody aliReFundBody
    );
}
