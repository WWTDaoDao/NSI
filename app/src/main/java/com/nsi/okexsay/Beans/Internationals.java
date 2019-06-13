package com.nsi.okexsay.Beans;

import java.io.Serializable;

public class Internationals implements Serializable {
    private String chinesename;
    private String countrycode;
    private String countrymark;
    private String status;
    private String englishname;
    private String fid;
    private String listsort;

    public String getChinesename() {
        return chinesename;
    }

    public void setChinesename(String chinesename) {
        this.chinesename = chinesename;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    public String getCountrymark() {
        return countrymark;
    }

    public void setCountrymark(String countrymark) {
        this.countrymark = countrymark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEnglishname() {
        return englishname;
    }

    public void setEnglishname(String englishname) {
        this.englishname = englishname;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getListsort() {
        return listsort;
    }

    public void setListsort(String listsort) {
        this.listsort = listsort;
    }
}
