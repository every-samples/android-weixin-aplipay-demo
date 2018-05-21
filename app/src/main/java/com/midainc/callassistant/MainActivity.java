package com.midainc.callassistant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.midainc.callassistant.alipay.AliPay;
import com.midainc.callassistant.api.ApiModule;
import com.midainc.callassistant.wx.WXPay;
import com.midainc.callassistant.wx.WxPayResponseData;

import org.json.JSONObject;

import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity {

    private String USER_ID = "31";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void payAli(View view) {
        ApiModule.provideNetDataManager().aliPay("名称", "1", USER_ID)
                .map(new Function<ResponseBody, String>() {
                    @Override
                    public String apply(ResponseBody responseBody) throws Exception {
                        return responseBody.string();
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        new AliPay(MainActivity.this, s, new AliPay.AlipayResultCallBack() {
                            @Override
                            public void onSuccess() {
                                Toast.makeText(MainActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onDealing() {

                            }

                            @Override
                            public void onError(int error_code) {
                                Toast.makeText(MainActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCancel() {
                                Toast.makeText(MainActivity.this, "支付取消", Toast.LENGTH_SHORT).show();
                            }
                        }).doPay();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });
    }

    public void payWechat(View view) {
        ApiModule.provideNetDataManager().wxPay("名称", "1", USER_ID)
                .map(new Function<ResponseBody, String>() {

                    @Override
                    public String apply(ResponseBody responseBody) throws Exception {
                        return responseBody.string();
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        WxPayResponseData data = new Gson().fromJson(s, WxPayResponseData.class);
                        JSONObject object = new JSONObject();
                        object.put("appid", data.getAppid());
                        object.put("partnerId", data.getPartnerid());
                        object.put("prepayId", data.getPrepayid());
                        object.put("packageValue", data.getPackageX());
                        object.put("nonceStr", data.getNoncestr());
                        object.put("timeStamp", data.getTimestamp());
                        object.put("sign", data.getSign());

                        Log.e("weiqi_", "accept: " + object.toString());
                        WXPay.getInstance();
                        WXPay.init(MainActivity.this, "wx4c92ac44328c5783");
                        WXPay.getInstance().pay(object.toString(), new WXPay.WXPayResultCallBack() {
                            @Override
                            public void onSuccess() {
                                Toast.makeText(MainActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(int error_code) {
                                Toast.makeText(MainActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCancel() {
                                Toast.makeText(MainActivity.this, "支付取消", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });
    }
}
