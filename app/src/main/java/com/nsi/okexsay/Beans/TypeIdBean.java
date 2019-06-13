package com.nsi.okexsay.Beans;

/**
 * 获取类型
 */
public class TypeIdBean {

    /**
     * areaName : 中国
     * currencySymbol : CNY
     * id : 1
     * legalName : 人民币
     * remarks : 中国
     */

    private String areaName;
    private String currencySymbol;
    private int id;
    private String legalName;
    private String remarks;

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
