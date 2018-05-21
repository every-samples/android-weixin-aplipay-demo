package com.midainc.callassistant.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.midainc.callassistant.wx.WXPay;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(WXPay.getInstance() != null) {
            WXPay.getInstance().getWxApi().handleIntent(getIntent(), this);
        } else {
            finish();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        if(WXPay.getInstance() != null) {
            WXPay.getInstance().getWxApi().handleIntent(intent, this);
        }
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        Log.e("weiqi_", "onResp: " + baseResp.getType());
        Log.e("weiqi_", "onResp: " + baseResp.errStr);
        Log.e("weiqi_", "onResp: " + baseResp.errCode);
        Log.e("weiqi_", "onResp: " + baseResp.openId);
        Log.e("weiqi_", "onResp: " + baseResp.transaction);
        if(baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            if(WXPay.getInstance() != null) {
                if(baseResp.errStr != null) {
                    Log.e("wxpay", "errstr=" + baseResp.errStr);
                }

                WXPay.getInstance().onResp(baseResp.errCode);
                finish();
            }
        }
    }

}
