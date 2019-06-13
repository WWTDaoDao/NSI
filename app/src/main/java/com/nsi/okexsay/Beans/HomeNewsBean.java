package com.nsi.okexsay.Beans;

import java.util.List;

public class HomeNewsBean {


    /**
     * farticleFtitle : ["币安孵化项目\u201c新套路\u201d：50万美元换早期阶段区块链创企10%股份","价值抵1000个比特币 带你了解世界最贵硬币","区块链对供应链金融最大的价值是营销核心企业"]
     * farticleFcontent_short : ["金色财经 比特币8月27日讯&nbsp;全球最大的加密货币交易所币安（Binance）宣布推出最新的创业孵化计划，他们会选择一些加密行业的初创公司，并提供50万...","2017年12月的比特币价格高点让许多投资者变得富有,但这都比不上这四位比特币持有者,他们每人拥有一枚价值1000BTC的实体黄金制作的比特币。6枚硬币=600...","近年来供应链金融与区块链技术逐渐渗透到我们对金融及大数据的常规认知体系中，受到了人们广泛的关注。然而经过多年的发展，供应链金融却一直不温不火，这其中有太多的痛点..."]
     * farticleFid : ["48","49","50"]
     * farticlefurl : ["/upload/images/201904092322016_zuglZ.jpg","/upload/images/201904092322027_umP7s.jpg","/upload/images/201904092322045_kIre2.png"]
     * farticleList : ["2019-04-09 23:22:16.0","2019-04-09 23:22:27.0","2019-04-09 23:22:45.0"]
     * code : 0
     */

    private String code;
    private List<String> farticleFtitle;
    private List<String> farticleFcontent_short;
    private List<String> farticleFid;
    private List<String> farticlefurl;
    private List<String> farticleList;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<String> getFarticleFtitle() {
        return farticleFtitle;
    }

    public void setFarticleFtitle(List<String> farticleFtitle) {
        this.farticleFtitle = farticleFtitle;
    }

    public List<String> getFarticleFcontent_short() {
        return farticleFcontent_short;
    }

    public void setFarticleFcontent_short(List<String> farticleFcontent_short) {
        this.farticleFcontent_short = farticleFcontent_short;
    }

    public List<String> getFarticleFid() {
        return farticleFid;
    }

    public void setFarticleFid(List<String> farticleFid) {
        this.farticleFid = farticleFid;
    }

    public List<String> getFarticlefurl() {
        return farticlefurl;
    }

    public void setFarticlefurl(List<String> farticlefurl) {
        this.farticlefurl = farticlefurl;
    }

    public List<String> getFarticleList() {
        return farticleList;
    }

    public void setFarticleList(List<String> farticleList) {
        this.farticleList = farticleList;
    }
}
