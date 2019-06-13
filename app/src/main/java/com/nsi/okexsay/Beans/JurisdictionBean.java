package com.nsi.okexsay.Beans;

/**
 * 用于表示用户权限
 */
public class JurisdictionBean {
    public JurisdictionBean(String jurisdictionstr) {
        Jurisdictionstr = jurisdictionstr;
    }

    public String getJurisdictionstr() {
        return Jurisdictionstr;
    }

    public void setJurisdictionstr(String jurisdictionstr) {
        Jurisdictionstr = jurisdictionstr;
    }

    private String Jurisdictionstr;
}
