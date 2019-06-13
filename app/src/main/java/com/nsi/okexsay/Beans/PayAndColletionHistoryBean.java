package com.nsi.okexsay.Beans;

import java.io.Serializable;

public class PayAndColletionHistoryBean implements Serializable {
    private String famount;
    private String withdraw_virtual_address;
    private IconExchangeDate fcreateTime;
    private String ftype;
    private String ftype_s;

    public String getFamount() {
        return famount;
    }

    public void setFamount(String famount) {
        this.famount = famount;
    }

    public String getWithdraw_virtual_address() {
        return withdraw_virtual_address;
    }

    public void setWithdraw_virtual_address(String withdraw_virtual_address) {
        this.withdraw_virtual_address = withdraw_virtual_address;
    }

    public IconExchangeDate getFcreateTime() {
        return fcreateTime;
    }

    public void setFcreateTime(IconExchangeDate fcreateTime) {
        this.fcreateTime = fcreateTime;
    }

    public String getFtype() {
        return ftype;
    }

    public void setFtype(String ftype) {
        this.ftype = ftype;
    }

    public String getFtype_s() {
        return ftype_s;
    }

    public void setFtype_s(String ftype_s) {
        this.ftype_s = ftype_s;
    }
}
