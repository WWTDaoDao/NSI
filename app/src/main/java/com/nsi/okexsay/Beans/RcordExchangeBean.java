package com.nsi.okexsay.Beans;

import java.io.Serializable;
import java.util.List;

public class RcordExchangeBean implements Serializable {
    private List<String> xnbTotal;
    private List<VirtualtocptBean> VirtualtocptList2;
    private String goldexchangerate;

    public List<String> getXnbTotal() {
        return xnbTotal;
    }

    public void setXnbTotal(List<String> xnbTotal) {
        this.xnbTotal = xnbTotal;
    }

    public List<VirtualtocptBean> getVirtualtocptList2() {
        return VirtualtocptList2;
    }

    public void setVirtualtocptList2(List<VirtualtocptBean> virtualtocptList2) {
        VirtualtocptList2 = virtualtocptList2;
    }

    public String getGoldexchangerate() {
        return goldexchangerate;
    }

    public void setGoldexchangerate(String goldexchangerate) {
        this.goldexchangerate = goldexchangerate;
    }
}


