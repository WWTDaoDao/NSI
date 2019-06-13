package com.nsi.okexsay.Beans;

/**
 * 控制我的订单是否显示
 */
public class EventOrderBean {
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public EventOrderBean(String msg) {
        this.msg = msg;
    }
}
