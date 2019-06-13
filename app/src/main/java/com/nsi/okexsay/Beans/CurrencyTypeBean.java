package com.nsi.okexsay.Beans;

import java.util.List;

public class CurrencyTypeBean {

    private List<FbListBean> fbList;

    public List<FbListBean> getFbList() {
        return fbList;
    }

    public void setFbList(List<FbListBean> fbList) {
        this.fbList = fbList;
    }

    public static class FbListBean {
        /**
         * remarks : 中国
         * legalName : 人民币
         * id : 1
         * areaName : 中国
         * currencySymbol : CNY
         */

        private String remarks;
        private String legalName;
        private int id;
        private String areaName;
        private String currencySymbol;

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getLegalName() {
            return legalName;
        }

        public void setLegalName(String legalName) {
            this.legalName = legalName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

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
    }
}
