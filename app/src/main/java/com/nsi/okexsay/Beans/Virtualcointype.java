package com.nsi.okexsay.Beans;

import java.io.Serializable;

public class Virtualcointype implements Serializable{

    private String furl;
    private String ftype_s;
    private String ftype;
    private String fstatus_s;
    private String fname;
    private String fid;
    private String total;

    public String getFurl() {
        return furl;
    }

    public void setFurl(String furl) {
        this.furl = furl;
    }

    public String getFtype_s() {
        return ftype_s;
    }

    public void setFtype_s(String ftype_s) {
        this.ftype_s = ftype_s;
    }

    public String getFtype() {
        return ftype;
    }

    public void setFtype(String ftype) {
        this.ftype = ftype;
    }

    public String getFstatus_s() {
        return fstatus_s;
    }

    public void setFstatus_s(String fstatus_s) {
        this.fstatus_s = fstatus_s;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
