package com.nsi.okexsay.Beans;

import java.io.Serializable;
import java.util.List;

public class CPTExchangeFullBean implements Serializable {

    private List<VirtualtocptBean> VirtualtocptList2;
    private List<String> xnbTotal;

    public List<VirtualtocptBean> getVirtualtocptList2() {
        return VirtualtocptList2;
    }

    public void setVirtualtocptList2(List<VirtualtocptBean> virtualtocptList2) {
        VirtualtocptList2 = virtualtocptList2;
    }

    public List<String> getXnbTotal() {
        return xnbTotal;
    }

    public void setXnbTotal(List<String> xnbTotal) {
        this.xnbTotal = xnbTotal;
    }
}
