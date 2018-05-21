package com.midainc.callassistant.alipay;

/**
 * Created by wei_q on 2018/2/11.
 */

public class AliReFundBody {

    private String orderNum;
    private String payFee;

    public AliReFundBody(String orderNum, String payFee) {
        this.orderNum = orderNum;
        this.payFee = payFee;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getPayFee() {
        return payFee;
    }

    public void setPayFee(String payFee) {
        this.payFee = payFee;
    }
}
