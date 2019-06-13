package com.nsi.okexsay.Beans;

import java.io.Serializable;

public class PrePayBean implements Serializable {
    private String fid;
    private String cptFtotal;
    private String sxf;
    private String flevel;
    private Virtualcointype fvirtualcointype;

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getCptFtotal() {
        return cptFtotal;
    }

    public void setCptFtotal(String cptFtotal) {
        this.cptFtotal = cptFtotal;
    }

    public String getSxf() {
        return sxf;
    }

    public void setSxf(String sxf) {
        this.sxf = sxf;
    }

    public String getFlevel() {
        return flevel;
    }

    public void setFlevel(String flevel) {
        this.flevel = flevel;
    }

    public Virtualcointype getFvirtualcointype() {
        return fvirtualcointype;
    }

    public void setFvirtualcointype(Virtualcointype fvirtualcointype) {
        this.fvirtualcointype = fvirtualcointype;
    }
}
