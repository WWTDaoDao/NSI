package com.nsi.okexsay.Beans;

/**
 * 消息分发
 */
public class DistributeMSG {
    public String getStringmsg() {
        return stringmsg;
    }

    public void setStringmsg(String stringmsg) {
        this.stringmsg = stringmsg;
    }

    public DistributeMSG(String stringmsg) {
        this.stringmsg = stringmsg;
    }

    private String stringmsg;

}
