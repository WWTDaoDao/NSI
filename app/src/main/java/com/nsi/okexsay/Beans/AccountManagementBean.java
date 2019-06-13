package com.nsi.okexsay.Beans;

public class AccountManagementBean {

    private int type = 123;
private String Id;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getTypefrom() {
        return typefrom;
    }

    public void setTypefrom(String typefrom) {
        this.typefrom = typefrom;
    }

    private String Number;
private String typefrom;
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
