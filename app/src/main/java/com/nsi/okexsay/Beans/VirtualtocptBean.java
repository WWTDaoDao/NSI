package com.nsi.okexsay.Beans;

import java.io.Serializable;

public class VirtualtocptBean implements Serializable {
    private String id;
    private String fVi_fId;
    private String virtualname;
    private String shortname;
    private String goldnum;
    private String tyjfnum;
    private String exchangerate;
    private Virtualcointype fvirtualcointype;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getfVi_fId() {
        return fVi_fId;
    }

    public void setfVi_fId(String fVi_fId) {
        this.fVi_fId = fVi_fId;
    }

    public String getVirtualname() {
        return virtualname;
    }

    public void setVirtualname(String virtualname) {
        this.virtualname = virtualname;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public String getGoldnum() {
        return goldnum;
    }

    public void setGoldnum(String goldnum) {
        this.goldnum = goldnum;
    }

    public String getTyjfnum() {
        return tyjfnum;
    }

    public void setTyjfnum(String tyjfnum) {
        this.tyjfnum = tyjfnum;
    }

    public String getExchangerate() {
        return exchangerate;
    }

    public void setExchangerate(String exchangerate) {
        this.exchangerate = exchangerate;
    }

    public Virtualcointype getFvirtualcointype() {
        return fvirtualcointype;
    }

    public void setFvirtualcointype(Virtualcointype fvirtualcointype) {
        this.fvirtualcointype = fvirtualcointype;
    }
}