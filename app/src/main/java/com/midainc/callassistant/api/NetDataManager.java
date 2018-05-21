package com.midainc.callassistant.api;


import com.midainc.callassistant.alipay.AliReFundBody;
import com.midainc.callassistant.wx.WxPayData;
import com.midainc.callassistant.wx.WxReFundBody;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by wei_q on 2018/1/22.
 */

public class NetDataManager {


    private ApiService mApiService;

    public NetDataManager(ApiService apiService) {
        this.mApiService = apiService;
    }


    public Observable<ResponseBody> wxPay(String name,String price,String userName) {
        return mApiService.payWx(new WxPayData(name, price, userName))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io());
    }

    public Observable<ResponseBody> wxRefund(String orderNum,String price){
        return mApiService.refundWx(new WxReFundBody(orderNum, price))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io());
    }

    public Observable<ResponseBody> aliRefund(String orderNum, String price) {
        return mApiService.refundAli(new AliReFundBody(orderNum, price))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io());
    }



    public Observable<ResponseBody> aliPay(String name,String price,String userName) {
        return mApiService.payAli(new WxPayData(name, price, userName))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io());
    }
}