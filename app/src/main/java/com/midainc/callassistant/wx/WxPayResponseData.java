package com.midainc.callassistant.wx;


import com.google.gson.annotations.SerializedName;

/**
 * Created by wei_q on 2018/2/5.
 */

public class WxPayResponseData {


    /**
     * package : Sign=WXPay
     * out_trade_no : 1517819862310309-15b77542ebe3
     * appid : wx4c92ac44328c5783
     * sign : F6B1D1C61D6754EC7F472A152810991C
     * partnerid : 1498449752
     * prepayid : wx20180205163742ad6a71e7180931640778
     * noncestr : 56cis3tjj3blchdtgkiic0islsa03lb5
     * timestamp : 1517819862
     */

    @SerializedName("package")
    private String packageX;
    private String out_trade_no;
    private String appid;
    private String sign;
    private String partnerid;
    private String prepayid;
    private String noncestr;
    private String timestamp;

    public String getPackageX() {
        return packageX;
    }

    public void setPackageX(String packageX) {
        this.packageX = packageX;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
