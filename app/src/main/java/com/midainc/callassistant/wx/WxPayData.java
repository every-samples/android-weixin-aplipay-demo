package com.midainc.callassistant.wx;

/**
 * Created by wei_q on 2018/2/5.
 */

public class WxPayData {


    /**
     * productName : 来电助手
     * payFee : 1234
     * accountId : test
     */

    private String productName;
    private String payFee;
    private String accountId;

    public WxPayData(String productName, String payFee, String accountId) {
        this.productName = productName;
        this.payFee = payFee;
        this.accountId = accountId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPayFee() {
        return payFee;
    }

    public void setPayFee(String payFee) {
        this.payFee = payFee;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
