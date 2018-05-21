package com.midainc.callassistant.wx;

/**
 * Created by wei_q on 2018/2/11.
 */

public class WxReFundBody {

    private String orderNum;
    private String money;

    public WxReFundBody(String orderNum, String money) {
        this.orderNum = orderNum;
        this.money = money;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
