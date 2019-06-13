package com.nsi.okexsay.Beans;

import java.util.List;

public class LawPurchaseBean {

    /**
     * agreeTime : null
     * amountReal : 1
     * c2c : {"agreeFlag":"0","agreeTime":null,"amount":7,"amountO":10,"cptOrUsdt":"0","fId":155,"legalCurrency":{"areaName":"中国","currencySymbol":"CNY","id":1,"legalName":"人民币","remarks":"中国"},"limitMax":7,"limitMin":1,"limitMinO":1,"payment":"2,","price":1,"publishTime":{"date":24,"day":5,"hours":11,"minutes":11,"month":4,"nanos":0,"seconds":1,"time":1558667461000,"timezoneOffset":-480,"year":119},"status":"0","tradeType":"1","user":{"emailvalidates":[],"fIdentityPath":"","fIdentityPath2":"","fIdentityPath3":"","fIntroUser_id":null,"fInvalidateIntroCount":0,"fapi":null,"fareaCode":"86","fbankinfoWithdraws":[],"fbankinfos":[],"fcapitaloperations":[],"femail":"","fentrustplans":[],"fentrusts":[],"fgoogleAuthenticator":"","fgoogleBind":false,"fgoogleValidate":false,"fgoogleurl":"","fhasRealValidate":true,"fhasRealValidateTime":{"date":17,"day":5,"hours":10,"minutes":20,"month":4,"nanos":0,"seconds":17,"time":1558059617000,"timezoneOffset":-480,"year":119},"fid":105433045,"fidentityNo":"412825198012127419","fidentityNo_s":"4128************7419","fidentityType":0,"fidentityType_s":"身份证","fintrolUserNo":"","fisMailValidate":false,"fisTelValidate":false,"fisTelephoneBind":true,"fisValid":true,"fistiger":false,"flastLoginIp":"","flastLoginTime":null,"flastUpdateTime":null,"floginName":"13505326567","floginPassword":"","fmobilMessageCode":"","fnickName":"次元妲己","fopenGoogleValidate":false,"fopenSecondValidate":false,"fopenTelValidate":false,"fpostRealValidate":true,"fpostRealValidateTime":{"date":17,"day":5,"hours":10,"minutes":18,"month":4,"nanos":0,"seconds":22,"time":1558059502000,"timezoneOffset":-480,"year":119},"fqrcodePath":"","frealName":"妲己","fregIp":"","fregType":0,"fregisterTime":null,"fregtype_s":"手机注册","fscore":null,"fstatus":0,"fstatus_s":"","ftelephone":"13505326567","ftradePassword":"","fuserNo":"","fusersetting":null,"fvirtualaddressWithdraws":[],"fvirtualaddresses":[],"fvirtualcaptualoperations":[],"fvirtualwallets":[],"qqlogin":"","salt":"","token":"","validateemailses":[],"version":0}}
     * fId : 318
     * orderNo : 201905241136624
     * payTime : null
     * shixiaoTime : {"date":24,"day":5,"hours":11,"minutes":51,"month":4,"nanos":0,"seconds":46,"time":1558669906000,"timezoneOffset":-480,"year":119}
     * sj : 0
     * sjPrice : 1
     * status : 1
     * sxf : 0
     * toPf : 0
     * totalPrice : 1
     * user : {"emailvalidates":[],"fIdentityPath":"","fIdentityPath2":"","fIdentityPath3":"","fIntroUser_id":null,"fInvalidateIntroCount":0,"fapi":null,"fareaCode":"86","fbankinfoWithdraws":[],"fbankinfos":[],"fcapitaloperations":[],"femail":"","fentrustplans":[],"fentrusts":[],"fgoogleAuthenticator":"","fgoogleBind":false,"fgoogleValidate":false,"fgoogleurl":"","fhasRealValidate":true,"fhasRealValidateTime":{"date":17,"day":5,"hours":10,"minutes":20,"month":4,"nanos":0,"seconds":17,"time":1558059617000,"timezoneOffset":-480,"year":119},"fid":105433045,"fidentityNo":"412825198012127419","fidentityNo_s":"4128************7419","fidentityType":0,"fidentityType_s":"身份证","fintrolUserNo":"","fisMailValidate":false,"fisTelValidate":false,"fisTelephoneBind":true,"fisValid":true,"fistiger":false,"flastLoginIp":"","flastLoginTime":null,"flastUpdateTime":null,"floginName":"13505326567","floginPassword":"","fmobilMessageCode":"","fnickName":"次元妲己","fopenGoogleValidate":false,"fopenSecondValidate":false,"fopenTelValidate":false,"fpostRealValidate":true,"fpostRealValidateTime":{"date":17,"day":5,"hours":10,"minutes":18,"month":4,"nanos":0,"seconds":22,"time":1558059502000,"timezoneOffset":-480,"year":119},"fqrcodePath":"","frealName":"妲己","fregIp":"","fregType":0,"fregisterTime":null,"fregtype_s":"手机注册","fscore":null,"fstatus":0,"fstatus_s":"","ftelephone":"13505326567","ftradePassword":"","fuserNo":"","fusersetting":null,"fvirtualaddressWithdraws":[],"fvirtualaddresses":[],"fvirtualcaptualoperations":[],"fvirtualwallets":[],"qqlogin":"","salt":"","token":"","validateemailses":[],"version":0}
     * xiadanTime : {"date":24,"day":5,"hours":11,"minutes":36,"month":4,"nanos":0,"seconds":46,"time":1558669006000,"timezoneOffset":-480,"year":119}
     */

    private Object agreeTime;
    private int amountReal;
    private C2cBean c2c;
    private int fId;
    private String orderNo;
    private Object payTime;
    private ShixiaoTimeBean shixiaoTime;
    private int sj;
    private int sjPrice;
    private String status;
    private int sxf;
    private int toPf;
    private int totalPrice;
    private UserBeanX user;
    private XiadanTimeBean xiadanTime;

    public Object getAgreeTime() {
        return agreeTime;
    }

    public void setAgreeTime(Object agreeTime) {
        this.agreeTime = agreeTime;
    }

    public int getAmountReal() {
        return amountReal;
    }

    public void setAmountReal(int amountReal) {
        this.amountReal = amountReal;
    }

    public C2cBean getC2c() {
        return c2c;
    }

    public void setC2c(C2cBean c2c) {
        this.c2c = c2c;
    }

    public int getFId() {
        return fId;
    }

    public void setFId(int fId) {
        this.fId = fId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Object getPayTime() {
        return payTime;
    }

    public void setPayTime(Object payTime) {
        this.payTime = payTime;
    }

    public ShixiaoTimeBean getShixiaoTime() {
        return shixiaoTime;
    }

    public void setShixiaoTime(ShixiaoTimeBean shixiaoTime) {
        this.shixiaoTime = shixiaoTime;
    }

    public int getSj() {
        return sj;
    }

    public void setSj(int sj) {
        this.sj = sj;
    }

    public int getSjPrice() {
        return sjPrice;
    }

    public void setSjPrice(int sjPrice) {
        this.sjPrice = sjPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getSxf() {
        return sxf;
    }

    public void setSxf(int sxf) {
        this.sxf = sxf;
    }

    public int getToPf() {
        return toPf;
    }

    public void setToPf(int toPf) {
        this.toPf = toPf;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public UserBeanX getUser() {
        return user;
    }

    public void setUser(UserBeanX user) {
        this.user = user;
    }

    public XiadanTimeBean getXiadanTime() {
        return xiadanTime;
    }

    public void setXiadanTime(XiadanTimeBean xiadanTime) {
        this.xiadanTime = xiadanTime;
    }

    public static class C2cBean {
        /**
         * agreeFlag : 0
         * agreeTime : null
         * amount : 7
         * amountO : 10
         * cptOrUsdt : 0
         * fId : 155
         * legalCurrency : {"areaName":"中国","currencySymbol":"CNY","id":1,"legalName":"人民币","remarks":"中国"}
         * limitMax : 7
         * limitMin : 1
         * limitMinO : 1
         * payment : 2,
         * price : 1
         * publishTime : {"date":24,"day":5,"hours":11,"minutes":11,"month":4,"nanos":0,"seconds":1,"time":1558667461000,"timezoneOffset":-480,"year":119}
         * status : 0
         * tradeType : 1
         * user : {"emailvalidates":[],"fIdentityPath":"","fIdentityPath2":"","fIdentityPath3":"","fIntroUser_id":null,"fInvalidateIntroCount":0,"fapi":null,"fareaCode":"86","fbankinfoWithdraws":[],"fbankinfos":[],"fcapitaloperations":[],"femail":"","fentrustplans":[],"fentrusts":[],"fgoogleAuthenticator":"","fgoogleBind":false,"fgoogleValidate":false,"fgoogleurl":"","fhasRealValidate":true,"fhasRealValidateTime":{"date":17,"day":5,"hours":10,"minutes":20,"month":4,"nanos":0,"seconds":17,"time":1558059617000,"timezoneOffset":-480,"year":119},"fid":105433045,"fidentityNo":"412825198012127419","fidentityNo_s":"4128************7419","fidentityType":0,"fidentityType_s":"身份证","fintrolUserNo":"","fisMailValidate":false,"fisTelValidate":false,"fisTelephoneBind":true,"fisValid":true,"fistiger":false,"flastLoginIp":"","flastLoginTime":null,"flastUpdateTime":null,"floginName":"13505326567","floginPassword":"","fmobilMessageCode":"","fnickName":"次元妲己","fopenGoogleValidate":false,"fopenSecondValidate":false,"fopenTelValidate":false,"fpostRealValidate":true,"fpostRealValidateTime":{"date":17,"day":5,"hours":10,"minutes":18,"month":4,"nanos":0,"seconds":22,"time":1558059502000,"timezoneOffset":-480,"year":119},"fqrcodePath":"","frealName":"妲己","fregIp":"","fregType":0,"fregisterTime":null,"fregtype_s":"手机注册","fscore":null,"fstatus":0,"fstatus_s":"","ftelephone":"13505326567","ftradePassword":"","fuserNo":"","fusersetting":null,"fvirtualaddressWithdraws":[],"fvirtualaddresses":[],"fvirtualcaptualoperations":[],"fvirtualwallets":[],"qqlogin":"","salt":"","token":"","validateemailses":[],"version":0}
         */

        private String agreeFlag;
        private Object agreeTime;
        private int amount;
        private int amountO;
        private String cptOrUsdt;
        private int fId;
        private LegalCurrencyBean legalCurrency;
        private int limitMax;
        private int limitMin;
        private int limitMinO;
        private String payment;
        private int price;
        private PublishTimeBean publishTime;
        private String status;
        private String tradeType;
        private UserBean user;

        public String getAgreeFlag() {
            return agreeFlag;
        }

        public void setAgreeFlag(String agreeFlag) {
            this.agreeFlag = agreeFlag;
        }

        public Object getAgreeTime() {
            return agreeTime;
        }

        public void setAgreeTime(Object agreeTime) {
            this.agreeTime = agreeTime;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public int getAmountO() {
            return amountO;
        }

        public void setAmountO(int amountO) {
            this.amountO = amountO;
        }

        public String getCptOrUsdt() {
            return cptOrUsdt;
        }

        public void setCptOrUsdt(String cptOrUsdt) {
            this.cptOrUsdt = cptOrUsdt;
        }

        public int getFId() {
            return fId;
        }

        public void setFId(int fId) {
            this.fId = fId;
        }

        public LegalCurrencyBean getLegalCurrency() {
            return legalCurrency;
        }

        public void setLegalCurrency(LegalCurrencyBean legalCurrency) {
            this.legalCurrency = legalCurrency;
        }

        public int getLimitMax() {
            return limitMax;
        }

        public void setLimitMax(int limitMax) {
            this.limitMax = limitMax;
        }

        public int getLimitMin() {
            return limitMin;
        }

        public void setLimitMin(int limitMin) {
            this.limitMin = limitMin;
        }

        public int getLimitMinO() {
            return limitMinO;
        }

        public void setLimitMinO(int limitMinO) {
            this.limitMinO = limitMinO;
        }

        public String getPayment() {
            return payment;
        }

        public void setPayment(String payment) {
            this.payment = payment;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public PublishTimeBean getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(PublishTimeBean publishTime) {
            this.publishTime = publishTime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTradeType() {
            return tradeType;
        }

        public void setTradeType(String tradeType) {
            this.tradeType = tradeType;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class LegalCurrencyBean {
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

        public static class PublishTimeBean {
            /**
             * date : 24
             * day : 5
             * hours : 11
             * minutes : 11
             * month : 4
             * nanos : 0
             * seconds : 1
             * time : 1558667461000
             * timezoneOffset : -480
             * year : 119
             */

            private int date;
            private int day;
            private int hours;
            private int minutes;
            private int month;
            private int nanos;
            private int seconds;
            private long time;
            private int timezoneOffset;
            private int year;

            public int getDate() {
                return date;
            }

            public void setDate(int date) {
                this.date = date;
            }

            public int getDay() {
                return day;
            }

            public void setDay(int day) {
                this.day = day;
            }

            public int getHours() {
                return hours;
            }

            public void setHours(int hours) {
                this.hours = hours;
            }

            public int getMinutes() {
                return minutes;
            }

            public void setMinutes(int minutes) {
                this.minutes = minutes;
            }

            public int getMonth() {
                return month;
            }

            public void setMonth(int month) {
                this.month = month;
            }

            public int getNanos() {
                return nanos;
            }

            public void setNanos(int nanos) {
                this.nanos = nanos;
            }

            public int getSeconds() {
                return seconds;
            }

            public void setSeconds(int seconds) {
                this.seconds = seconds;
            }

            public long getTime() {
                return time;
            }

            public void setTime(long time) {
                this.time = time;
            }

            public int getTimezoneOffset() {
                return timezoneOffset;
            }

            public void setTimezoneOffset(int timezoneOffset) {
                this.timezoneOffset = timezoneOffset;
            }

            public int getYear() {
                return year;
            }

            public void setYear(int year) {
                this.year = year;
            }
        }

        public static class UserBean {
            /**
             * emailvalidates : []
             * fIdentityPath :
             * fIdentityPath2 :
             * fIdentityPath3 :
             * fIntroUser_id : null
             * fInvalidateIntroCount : 0
             * fapi : null
             * fareaCode : 86
             * fbankinfoWithdraws : []
             * fbankinfos : []
             * fcapitaloperations : []
             * femail :
             * fentrustplans : []
             * fentrusts : []
             * fgoogleAuthenticator :
             * fgoogleBind : false
             * fgoogleValidate : false
             * fgoogleurl :
             * fhasRealValidate : true
             * fhasRealValidateTime : {"date":17,"day":5,"hours":10,"minutes":20,"month":4,"nanos":0,"seconds":17,"time":1558059617000,"timezoneOffset":-480,"year":119}
             * fid : 105433045
             * fidentityNo : 412825198012127419
             * fidentityNo_s : 4128************7419
             * fidentityType : 0
             * fidentityType_s : 身份证
             * fintrolUserNo :
             * fisMailValidate : false
             * fisTelValidate : false
             * fisTelephoneBind : true
             * fisValid : true
             * fistiger : false
             * flastLoginIp :
             * flastLoginTime : null
             * flastUpdateTime : null
             * floginName : 13505326567
             * floginPassword :
             * fmobilMessageCode :
             * fnickName : 次元妲己
             * fopenGoogleValidate : false
             * fopenSecondValidate : false
             * fopenTelValidate : false
             * fpostRealValidate : true
             * fpostRealValidateTime : {"date":17,"day":5,"hours":10,"minutes":18,"month":4,"nanos":0,"seconds":22,"time":1558059502000,"timezoneOffset":-480,"year":119}
             * fqrcodePath :
             * frealName : 妲己
             * fregIp :
             * fregType : 0
             * fregisterTime : null
             * fregtype_s : 手机注册
             * fscore : null
             * fstatus : 0
             * fstatus_s :
             * ftelephone : 13505326567
             * ftradePassword :
             * fuserNo :
             * fusersetting : null
             * fvirtualaddressWithdraws : []
             * fvirtualaddresses : []
             * fvirtualcaptualoperations : []
             * fvirtualwallets : []
             * qqlogin :
             * salt :
             * token :
             * validateemailses : []
             * version : 0
             */

            private String fIdentityPath;
            private String fIdentityPath2;
            private String fIdentityPath3;
            private Object fIntroUser_id;
            private int fInvalidateIntroCount;
            private Object fapi;
            private String fareaCode;
            private String femail;
            private String fgoogleAuthenticator;
            private boolean fgoogleBind;
            private boolean fgoogleValidate;
            private String fgoogleurl;
            private boolean fhasRealValidate;
            private FhasRealValidateTimeBean fhasRealValidateTime;
            private int fid;
            private String fidentityNo;
            private String fidentityNo_s;
            private int fidentityType;
            private String fidentityType_s;
            private String fintrolUserNo;
            private boolean fisMailValidate;
            private boolean fisTelValidate;
            private boolean fisTelephoneBind;
            private boolean fisValid;
            private boolean fistiger;
            private String flastLoginIp;
            private Object flastLoginTime;
            private Object flastUpdateTime;
            private String floginName;
            private String floginPassword;
            private String fmobilMessageCode;
            private String fnickName;
            private boolean fopenGoogleValidate;
            private boolean fopenSecondValidate;
            private boolean fopenTelValidate;
            private boolean fpostRealValidate;
            private FpostRealValidateTimeBean fpostRealValidateTime;
            private String fqrcodePath;
            private String frealName;
            private String fregIp;
            private int fregType;
            private Object fregisterTime;
            private String fregtype_s;
            private Object fscore;
            private int fstatus;
            private String fstatus_s;
            private String ftelephone;
            private String ftradePassword;
            private String fuserNo;
            private Object fusersetting;
            private String qqlogin;
            private String salt;
            private String token;
            private int version;
            private List<?> emailvalidates;
            private List<?> fbankinfoWithdraws;
            private List<?> fbankinfos;
            private List<?> fcapitaloperations;
            private List<?> fentrustplans;
            private List<?> fentrusts;
            private List<?> fvirtualaddressWithdraws;
            private List<?> fvirtualaddresses;
            private List<?> fvirtualcaptualoperations;
            private List<?> fvirtualwallets;
            private List<?> validateemailses;

            public String getFIdentityPath() {
                return fIdentityPath;
            }

            public void setFIdentityPath(String fIdentityPath) {
                this.fIdentityPath = fIdentityPath;
            }

            public String getFIdentityPath2() {
                return fIdentityPath2;
            }

            public void setFIdentityPath2(String fIdentityPath2) {
                this.fIdentityPath2 = fIdentityPath2;
            }

            public String getFIdentityPath3() {
                return fIdentityPath3;
            }

            public void setFIdentityPath3(String fIdentityPath3) {
                this.fIdentityPath3 = fIdentityPath3;
            }

            public Object getFIntroUser_id() {
                return fIntroUser_id;
            }

            public void setFIntroUser_id(Object fIntroUser_id) {
                this.fIntroUser_id = fIntroUser_id;
            }

            public int getFInvalidateIntroCount() {
                return fInvalidateIntroCount;
            }

            public void setFInvalidateIntroCount(int fInvalidateIntroCount) {
                this.fInvalidateIntroCount = fInvalidateIntroCount;
            }

            public Object getFapi() {
                return fapi;
            }

            public void setFapi(Object fapi) {
                this.fapi = fapi;
            }

            public String getFareaCode() {
                return fareaCode;
            }

            public void setFareaCode(String fareaCode) {
                this.fareaCode = fareaCode;
            }

            public String getFemail() {
                return femail;
            }

            public void setFemail(String femail) {
                this.femail = femail;
            }

            public String getFgoogleAuthenticator() {
                return fgoogleAuthenticator;
            }

            public void setFgoogleAuthenticator(String fgoogleAuthenticator) {
                this.fgoogleAuthenticator = fgoogleAuthenticator;
            }

            public boolean isFgoogleBind() {
                return fgoogleBind;
            }

            public void setFgoogleBind(boolean fgoogleBind) {
                this.fgoogleBind = fgoogleBind;
            }

            public boolean isFgoogleValidate() {
                return fgoogleValidate;
            }

            public void setFgoogleValidate(boolean fgoogleValidate) {
                this.fgoogleValidate = fgoogleValidate;
            }

            public String getFgoogleurl() {
                return fgoogleurl;
            }

            public void setFgoogleurl(String fgoogleurl) {
                this.fgoogleurl = fgoogleurl;
            }

            public boolean isFhasRealValidate() {
                return fhasRealValidate;
            }

            public void setFhasRealValidate(boolean fhasRealValidate) {
                this.fhasRealValidate = fhasRealValidate;
            }

            public FhasRealValidateTimeBean getFhasRealValidateTime() {
                return fhasRealValidateTime;
            }

            public void setFhasRealValidateTime(FhasRealValidateTimeBean fhasRealValidateTime) {
                this.fhasRealValidateTime = fhasRealValidateTime;
            }

            public int getFid() {
                return fid;
            }

            public void setFid(int fid) {
                this.fid = fid;
            }

            public String getFidentityNo() {
                return fidentityNo;
            }

            public void setFidentityNo(String fidentityNo) {
                this.fidentityNo = fidentityNo;
            }

            public String getFidentityNo_s() {
                return fidentityNo_s;
            }

            public void setFidentityNo_s(String fidentityNo_s) {
                this.fidentityNo_s = fidentityNo_s;
            }

            public int getFidentityType() {
                return fidentityType;
            }

            public void setFidentityType(int fidentityType) {
                this.fidentityType = fidentityType;
            }

            public String getFidentityType_s() {
                return fidentityType_s;
            }

            public void setFidentityType_s(String fidentityType_s) {
                this.fidentityType_s = fidentityType_s;
            }

            public String getFintrolUserNo() {
                return fintrolUserNo;
            }

            public void setFintrolUserNo(String fintrolUserNo) {
                this.fintrolUserNo = fintrolUserNo;
            }

            public boolean isFisMailValidate() {
                return fisMailValidate;
            }

            public void setFisMailValidate(boolean fisMailValidate) {
                this.fisMailValidate = fisMailValidate;
            }

            public boolean isFisTelValidate() {
                return fisTelValidate;
            }

            public void setFisTelValidate(boolean fisTelValidate) {
                this.fisTelValidate = fisTelValidate;
            }

            public boolean isFisTelephoneBind() {
                return fisTelephoneBind;
            }

            public void setFisTelephoneBind(boolean fisTelephoneBind) {
                this.fisTelephoneBind = fisTelephoneBind;
            }

            public boolean isFisValid() {
                return fisValid;
            }

            public void setFisValid(boolean fisValid) {
                this.fisValid = fisValid;
            }

            public boolean isFistiger() {
                return fistiger;
            }

            public void setFistiger(boolean fistiger) {
                this.fistiger = fistiger;
            }

            public String getFlastLoginIp() {
                return flastLoginIp;
            }

            public void setFlastLoginIp(String flastLoginIp) {
                this.flastLoginIp = flastLoginIp;
            }

            public Object getFlastLoginTime() {
                return flastLoginTime;
            }

            public void setFlastLoginTime(Object flastLoginTime) {
                this.flastLoginTime = flastLoginTime;
            }

            public Object getFlastUpdateTime() {
                return flastUpdateTime;
            }

            public void setFlastUpdateTime(Object flastUpdateTime) {
                this.flastUpdateTime = flastUpdateTime;
            }

            public String getFloginName() {
                return floginName;
            }

            public void setFloginName(String floginName) {
                this.floginName = floginName;
            }

            public String getFloginPassword() {
                return floginPassword;
            }

            public void setFloginPassword(String floginPassword) {
                this.floginPassword = floginPassword;
            }

            public String getFmobilMessageCode() {
                return fmobilMessageCode;
            }

            public void setFmobilMessageCode(String fmobilMessageCode) {
                this.fmobilMessageCode = fmobilMessageCode;
            }

            public String getFnickName() {
                return fnickName;
            }

            public void setFnickName(String fnickName) {
                this.fnickName = fnickName;
            }

            public boolean isFopenGoogleValidate() {
                return fopenGoogleValidate;
            }

            public void setFopenGoogleValidate(boolean fopenGoogleValidate) {
                this.fopenGoogleValidate = fopenGoogleValidate;
            }

            public boolean isFopenSecondValidate() {
                return fopenSecondValidate;
            }

            public void setFopenSecondValidate(boolean fopenSecondValidate) {
                this.fopenSecondValidate = fopenSecondValidate;
            }

            public boolean isFopenTelValidate() {
                return fopenTelValidate;
            }

            public void setFopenTelValidate(boolean fopenTelValidate) {
                this.fopenTelValidate = fopenTelValidate;
            }

            public boolean isFpostRealValidate() {
                return fpostRealValidate;
            }

            public void setFpostRealValidate(boolean fpostRealValidate) {
                this.fpostRealValidate = fpostRealValidate;
            }

            public FpostRealValidateTimeBean getFpostRealValidateTime() {
                return fpostRealValidateTime;
            }

            public void setFpostRealValidateTime(FpostRealValidateTimeBean fpostRealValidateTime) {
                this.fpostRealValidateTime = fpostRealValidateTime;
            }

            public String getFqrcodePath() {
                return fqrcodePath;
            }

            public void setFqrcodePath(String fqrcodePath) {
                this.fqrcodePath = fqrcodePath;
            }

            public String getFrealName() {
                return frealName;
            }

            public void setFrealName(String frealName) {
                this.frealName = frealName;
            }

            public String getFregIp() {
                return fregIp;
            }

            public void setFregIp(String fregIp) {
                this.fregIp = fregIp;
            }

            public int getFregType() {
                return fregType;
            }

            public void setFregType(int fregType) {
                this.fregType = fregType;
            }

            public Object getFregisterTime() {
                return fregisterTime;
            }

            public void setFregisterTime(Object fregisterTime) {
                this.fregisterTime = fregisterTime;
            }

            public String getFregtype_s() {
                return fregtype_s;
            }

            public void setFregtype_s(String fregtype_s) {
                this.fregtype_s = fregtype_s;
            }

            public Object getFscore() {
                return fscore;
            }

            public void setFscore(Object fscore) {
                this.fscore = fscore;
            }

            public int getFstatus() {
                return fstatus;
            }

            public void setFstatus(int fstatus) {
                this.fstatus = fstatus;
            }

            public String getFstatus_s() {
                return fstatus_s;
            }

            public void setFstatus_s(String fstatus_s) {
                this.fstatus_s = fstatus_s;
            }

            public String getFtelephone() {
                return ftelephone;
            }

            public void setFtelephone(String ftelephone) {
                this.ftelephone = ftelephone;
            }

            public String getFtradePassword() {
                return ftradePassword;
            }

            public void setFtradePassword(String ftradePassword) {
                this.ftradePassword = ftradePassword;
            }

            public String getFuserNo() {
                return fuserNo;
            }

            public void setFuserNo(String fuserNo) {
                this.fuserNo = fuserNo;
            }

            public Object getFusersetting() {
                return fusersetting;
            }

            public void setFusersetting(Object fusersetting) {
                this.fusersetting = fusersetting;
            }

            public String getQqlogin() {
                return qqlogin;
            }

            public void setQqlogin(String qqlogin) {
                this.qqlogin = qqlogin;
            }

            public String getSalt() {
                return salt;
            }

            public void setSalt(String salt) {
                this.salt = salt;
            }

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }

            public int getVersion() {
                return version;
            }

            public void setVersion(int version) {
                this.version = version;
            }

            public List<?> getEmailvalidates() {
                return emailvalidates;
            }

            public void setEmailvalidates(List<?> emailvalidates) {
                this.emailvalidates = emailvalidates;
            }

            public List<?> getFbankinfoWithdraws() {
                return fbankinfoWithdraws;
            }

            public void setFbankinfoWithdraws(List<?> fbankinfoWithdraws) {
                this.fbankinfoWithdraws = fbankinfoWithdraws;
            }

            public List<?> getFbankinfos() {
                return fbankinfos;
            }

            public void setFbankinfos(List<?> fbankinfos) {
                this.fbankinfos = fbankinfos;
            }

            public List<?> getFcapitaloperations() {
                return fcapitaloperations;
            }

            public void setFcapitaloperations(List<?> fcapitaloperations) {
                this.fcapitaloperations = fcapitaloperations;
            }

            public List<?> getFentrustplans() {
                return fentrustplans;
            }

            public void setFentrustplans(List<?> fentrustplans) {
                this.fentrustplans = fentrustplans;
            }

            public List<?> getFentrusts() {
                return fentrusts;
            }

            public void setFentrusts(List<?> fentrusts) {
                this.fentrusts = fentrusts;
            }

            public List<?> getFvirtualaddressWithdraws() {
                return fvirtualaddressWithdraws;
            }

            public void setFvirtualaddressWithdraws(List<?> fvirtualaddressWithdraws) {
                this.fvirtualaddressWithdraws = fvirtualaddressWithdraws;
            }

            public List<?> getFvirtualaddresses() {
                return fvirtualaddresses;
            }

            public void setFvirtualaddresses(List<?> fvirtualaddresses) {
                this.fvirtualaddresses = fvirtualaddresses;
            }

            public List<?> getFvirtualcaptualoperations() {
                return fvirtualcaptualoperations;
            }

            public void setFvirtualcaptualoperations(List<?> fvirtualcaptualoperations) {
                this.fvirtualcaptualoperations = fvirtualcaptualoperations;
            }

            public List<?> getFvirtualwallets() {
                return fvirtualwallets;
            }

            public void setFvirtualwallets(List<?> fvirtualwallets) {
                this.fvirtualwallets = fvirtualwallets;
            }

            public List<?> getValidateemailses() {
                return validateemailses;
            }

            public void setValidateemailses(List<?> validateemailses) {
                this.validateemailses = validateemailses;
            }

            public static class FhasRealValidateTimeBean {
                /**
                 * date : 17
                 * day : 5
                 * hours : 10
                 * minutes : 20
                 * month : 4
                 * nanos : 0
                 * seconds : 17
                 * time : 1558059617000
                 * timezoneOffset : -480
                 * year : 119
                 */

                private int date;
                private int day;
                private int hours;
                private int minutes;
                private int month;
                private int nanos;
                private int seconds;
                private long time;
                private int timezoneOffset;
                private int year;

                public int getDate() {
                    return date;
                }

                public void setDate(int date) {
                    this.date = date;
                }

                public int getDay() {
                    return day;
                }

                public void setDay(int day) {
                    this.day = day;
                }

                public int getHours() {
                    return hours;
                }

                public void setHours(int hours) {
                    this.hours = hours;
                }

                public int getMinutes() {
                    return minutes;
                }

                public void setMinutes(int minutes) {
                    this.minutes = minutes;
                }

                public int getMonth() {
                    return month;
                }

                public void setMonth(int month) {
                    this.month = month;
                }

                public int getNanos() {
                    return nanos;
                }

                public void setNanos(int nanos) {
                    this.nanos = nanos;
                }

                public int getSeconds() {
                    return seconds;
                }

                public void setSeconds(int seconds) {
                    this.seconds = seconds;
                }

                public long getTime() {
                    return time;
                }

                public void setTime(long time) {
                    this.time = time;
                }

                public int getTimezoneOffset() {
                    return timezoneOffset;
                }

                public void setTimezoneOffset(int timezoneOffset) {
                    this.timezoneOffset = timezoneOffset;
                }

                public int getYear() {
                    return year;
                }

                public void setYear(int year) {
                    this.year = year;
                }
            }

            public static class FpostRealValidateTimeBean {
                /**
                 * date : 17
                 * day : 5
                 * hours : 10
                 * minutes : 18
                 * month : 4
                 * nanos : 0
                 * seconds : 22
                 * time : 1558059502000
                 * timezoneOffset : -480
                 * year : 119
                 */

                private int date;
                private int day;
                private int hours;
                private int minutes;
                private int month;
                private int nanos;
                private int seconds;
                private long time;
                private int timezoneOffset;
                private int year;

                public int getDate() {
                    return date;
                }

                public void setDate(int date) {
                    this.date = date;
                }

                public int getDay() {
                    return day;
                }

                public void setDay(int day) {
                    this.day = day;
                }

                public int getHours() {
                    return hours;
                }

                public void setHours(int hours) {
                    this.hours = hours;
                }

                public int getMinutes() {
                    return minutes;
                }

                public void setMinutes(int minutes) {
                    this.minutes = minutes;
                }

                public int getMonth() {
                    return month;
                }

                public void setMonth(int month) {
                    this.month = month;
                }

                public int getNanos() {
                    return nanos;
                }

                public void setNanos(int nanos) {
                    this.nanos = nanos;
                }

                public int getSeconds() {
                    return seconds;
                }

                public void setSeconds(int seconds) {
                    this.seconds = seconds;
                }

                public long getTime() {
                    return time;
                }

                public void setTime(long time) {
                    this.time = time;
                }

                public int getTimezoneOffset() {
                    return timezoneOffset;
                }

                public void setTimezoneOffset(int timezoneOffset) {
                    this.timezoneOffset = timezoneOffset;
                }

                public int getYear() {
                    return year;
                }

                public void setYear(int year) {
                    this.year = year;
                }
            }
        }
    }

    public static class ShixiaoTimeBean {
        /**
         * date : 24
         * day : 5
         * hours : 11
         * minutes : 51
         * month : 4
         * nanos : 0
         * seconds : 46
         * time : 1558669906000
         * timezoneOffset : -480
         * year : 119
         */

        private int date;
        private int day;
        private int hours;
        private int minutes;
        private int month;
        private int nanos;
        private int seconds;
        private long time;
        private int timezoneOffset;
        private int year;

        public int getDate() {
            return date;
        }

        public void setDate(int date) {
            this.date = date;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public int getHours() {
            return hours;
        }

        public void setHours(int hours) {
            this.hours = hours;
        }

        public int getMinutes() {
            return minutes;
        }

        public void setMinutes(int minutes) {
            this.minutes = minutes;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public int getNanos() {
            return nanos;
        }

        public void setNanos(int nanos) {
            this.nanos = nanos;
        }

        public int getSeconds() {
            return seconds;
        }

        public void setSeconds(int seconds) {
            this.seconds = seconds;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public int getTimezoneOffset() {
            return timezoneOffset;
        }

        public void setTimezoneOffset(int timezoneOffset) {
            this.timezoneOffset = timezoneOffset;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }
    }

    public static class UserBeanX {
        /**
         * emailvalidates : []
         * fIdentityPath :
         * fIdentityPath2 :
         * fIdentityPath3 :
         * fIntroUser_id : null
         * fInvalidateIntroCount : 0
         * fapi : null
         * fareaCode : 86
         * fbankinfoWithdraws : []
         * fbankinfos : []
         * fcapitaloperations : []
         * femail :
         * fentrustplans : []
         * fentrusts : []
         * fgoogleAuthenticator :
         * fgoogleBind : false
         * fgoogleValidate : false
         * fgoogleurl :
         * fhasRealValidate : true
         * fhasRealValidateTime : {"date":17,"day":5,"hours":10,"minutes":20,"month":4,"nanos":0,"seconds":17,"time":1558059617000,"timezoneOffset":-480,"year":119}
         * fid : 105433045
         * fidentityNo : 412825198012127419
         * fidentityNo_s : 4128************7419
         * fidentityType : 0
         * fidentityType_s : 身份证
         * fintrolUserNo :
         * fisMailValidate : false
         * fisTelValidate : false
         * fisTelephoneBind : true
         * fisValid : true
         * fistiger : false
         * flastLoginIp :
         * flastLoginTime : null
         * flastUpdateTime : null
         * floginName : 13505326567
         * floginPassword :
         * fmobilMessageCode :
         * fnickName : 次元妲己
         * fopenGoogleValidate : false
         * fopenSecondValidate : false
         * fopenTelValidate : false
         * fpostRealValidate : true
         * fpostRealValidateTime : {"date":17,"day":5,"hours":10,"minutes":18,"month":4,"nanos":0,"seconds":22,"time":1558059502000,"timezoneOffset":-480,"year":119}
         * fqrcodePath :
         * frealName : 妲己
         * fregIp :
         * fregType : 0
         * fregisterTime : null
         * fregtype_s : 手机注册
         * fscore : null
         * fstatus : 0
         * fstatus_s :
         * ftelephone : 13505326567
         * ftradePassword :
         * fuserNo :
         * fusersetting : null
         * fvirtualaddressWithdraws : []
         * fvirtualaddresses : []
         * fvirtualcaptualoperations : []
         * fvirtualwallets : []
         * qqlogin :
         * salt :
         * token :
         * validateemailses : []
         * version : 0
         */

        private String fIdentityPath;
        private String fIdentityPath2;
        private String fIdentityPath3;
        private Object fIntroUser_id;
        private int fInvalidateIntroCount;
        private Object fapi;
        private String fareaCode;
        private String femail;
        private String fgoogleAuthenticator;
        private boolean fgoogleBind;
        private boolean fgoogleValidate;
        private String fgoogleurl;
        private boolean fhasRealValidate;
        private FhasRealValidateTimeBeanX fhasRealValidateTime;
        private int fid;
        private String fidentityNo;
        private String fidentityNo_s;
        private int fidentityType;
        private String fidentityType_s;
        private String fintrolUserNo;
        private boolean fisMailValidate;
        private boolean fisTelValidate;
        private boolean fisTelephoneBind;
        private boolean fisValid;
        private boolean fistiger;
        private String flastLoginIp;
        private Object flastLoginTime;
        private Object flastUpdateTime;
        private String floginName;
        private String floginPassword;
        private String fmobilMessageCode;
        private String fnickName;
        private boolean fopenGoogleValidate;
        private boolean fopenSecondValidate;
        private boolean fopenTelValidate;
        private boolean fpostRealValidate;
        private FpostRealValidateTimeBeanX fpostRealValidateTime;
        private String fqrcodePath;
        private String frealName;
        private String fregIp;
        private int fregType;
        private Object fregisterTime;
        private String fregtype_s;
        private Object fscore;
        private int fstatus;
        private String fstatus_s;
        private String ftelephone;
        private String ftradePassword;
        private String fuserNo;
        private Object fusersetting;
        private String qqlogin;
        private String salt;
        private String token;
        private int version;
        private List<?> emailvalidates;
        private List<?> fbankinfoWithdraws;
        private List<?> fbankinfos;
        private List<?> fcapitaloperations;
        private List<?> fentrustplans;
        private List<?> fentrusts;
        private List<?> fvirtualaddressWithdraws;
        private List<?> fvirtualaddresses;
        private List<?> fvirtualcaptualoperations;
        private List<?> fvirtualwallets;
        private List<?> validateemailses;

        public String getFIdentityPath() {
            return fIdentityPath;
        }

        public void setFIdentityPath(String fIdentityPath) {
            this.fIdentityPath = fIdentityPath;
        }

        public String getFIdentityPath2() {
            return fIdentityPath2;
        }

        public void setFIdentityPath2(String fIdentityPath2) {
            this.fIdentityPath2 = fIdentityPath2;
        }

        public String getFIdentityPath3() {
            return fIdentityPath3;
        }

        public void setFIdentityPath3(String fIdentityPath3) {
            this.fIdentityPath3 = fIdentityPath3;
        }

        public Object getFIntroUser_id() {
            return fIntroUser_id;
        }

        public void setFIntroUser_id(Object fIntroUser_id) {
            this.fIntroUser_id = fIntroUser_id;
        }

        public int getFInvalidateIntroCount() {
            return fInvalidateIntroCount;
        }

        public void setFInvalidateIntroCount(int fInvalidateIntroCount) {
            this.fInvalidateIntroCount = fInvalidateIntroCount;
        }

        public Object getFapi() {
            return fapi;
        }

        public void setFapi(Object fapi) {
            this.fapi = fapi;
        }

        public String getFareaCode() {
            return fareaCode;
        }

        public void setFareaCode(String fareaCode) {
            this.fareaCode = fareaCode;
        }

        public String getFemail() {
            return femail;
        }

        public void setFemail(String femail) {
            this.femail = femail;
        }

        public String getFgoogleAuthenticator() {
            return fgoogleAuthenticator;
        }

        public void setFgoogleAuthenticator(String fgoogleAuthenticator) {
            this.fgoogleAuthenticator = fgoogleAuthenticator;
        }

        public boolean isFgoogleBind() {
            return fgoogleBind;
        }

        public void setFgoogleBind(boolean fgoogleBind) {
            this.fgoogleBind = fgoogleBind;
        }

        public boolean isFgoogleValidate() {
            return fgoogleValidate;
        }

        public void setFgoogleValidate(boolean fgoogleValidate) {
            this.fgoogleValidate = fgoogleValidate;
        }

        public String getFgoogleurl() {
            return fgoogleurl;
        }

        public void setFgoogleurl(String fgoogleurl) {
            this.fgoogleurl = fgoogleurl;
        }

        public boolean isFhasRealValidate() {
            return fhasRealValidate;
        }

        public void setFhasRealValidate(boolean fhasRealValidate) {
            this.fhasRealValidate = fhasRealValidate;
        }

        public FhasRealValidateTimeBeanX getFhasRealValidateTime() {
            return fhasRealValidateTime;
        }

        public void setFhasRealValidateTime(FhasRealValidateTimeBeanX fhasRealValidateTime) {
            this.fhasRealValidateTime = fhasRealValidateTime;
        }

        public int getFid() {
            return fid;
        }

        public void setFid(int fid) {
            this.fid = fid;
        }

        public String getFidentityNo() {
            return fidentityNo;
        }

        public void setFidentityNo(String fidentityNo) {
            this.fidentityNo = fidentityNo;
        }

        public String getFidentityNo_s() {
            return fidentityNo_s;
        }

        public void setFidentityNo_s(String fidentityNo_s) {
            this.fidentityNo_s = fidentityNo_s;
        }

        public int getFidentityType() {
            return fidentityType;
        }

        public void setFidentityType(int fidentityType) {
            this.fidentityType = fidentityType;
        }

        public String getFidentityType_s() {
            return fidentityType_s;
        }

        public void setFidentityType_s(String fidentityType_s) {
            this.fidentityType_s = fidentityType_s;
        }

        public String getFintrolUserNo() {
            return fintrolUserNo;
        }

        public void setFintrolUserNo(String fintrolUserNo) {
            this.fintrolUserNo = fintrolUserNo;
        }

        public boolean isFisMailValidate() {
            return fisMailValidate;
        }

        public void setFisMailValidate(boolean fisMailValidate) {
            this.fisMailValidate = fisMailValidate;
        }

        public boolean isFisTelValidate() {
            return fisTelValidate;
        }

        public void setFisTelValidate(boolean fisTelValidate) {
            this.fisTelValidate = fisTelValidate;
        }

        public boolean isFisTelephoneBind() {
            return fisTelephoneBind;
        }

        public void setFisTelephoneBind(boolean fisTelephoneBind) {
            this.fisTelephoneBind = fisTelephoneBind;
        }

        public boolean isFisValid() {
            return fisValid;
        }

        public void setFisValid(boolean fisValid) {
            this.fisValid = fisValid;
        }

        public boolean isFistiger() {
            return fistiger;
        }

        public void setFistiger(boolean fistiger) {
            this.fistiger = fistiger;
        }

        public String getFlastLoginIp() {
            return flastLoginIp;
        }

        public void setFlastLoginIp(String flastLoginIp) {
            this.flastLoginIp = flastLoginIp;
        }

        public Object getFlastLoginTime() {
            return flastLoginTime;
        }

        public void setFlastLoginTime(Object flastLoginTime) {
            this.flastLoginTime = flastLoginTime;
        }

        public Object getFlastUpdateTime() {
            return flastUpdateTime;
        }

        public void setFlastUpdateTime(Object flastUpdateTime) {
            this.flastUpdateTime = flastUpdateTime;
        }

        public String getFloginName() {
            return floginName;
        }

        public void setFloginName(String floginName) {
            this.floginName = floginName;
        }

        public String getFloginPassword() {
            return floginPassword;
        }

        public void setFloginPassword(String floginPassword) {
            this.floginPassword = floginPassword;
        }

        public String getFmobilMessageCode() {
            return fmobilMessageCode;
        }

        public void setFmobilMessageCode(String fmobilMessageCode) {
            this.fmobilMessageCode = fmobilMessageCode;
        }

        public String getFnickName() {
            return fnickName;
        }

        public void setFnickName(String fnickName) {
            this.fnickName = fnickName;
        }

        public boolean isFopenGoogleValidate() {
            return fopenGoogleValidate;
        }

        public void setFopenGoogleValidate(boolean fopenGoogleValidate) {
            this.fopenGoogleValidate = fopenGoogleValidate;
        }

        public boolean isFopenSecondValidate() {
            return fopenSecondValidate;
        }

        public void setFopenSecondValidate(boolean fopenSecondValidate) {
            this.fopenSecondValidate = fopenSecondValidate;
        }

        public boolean isFopenTelValidate() {
            return fopenTelValidate;
        }

        public void setFopenTelValidate(boolean fopenTelValidate) {
            this.fopenTelValidate = fopenTelValidate;
        }

        public boolean isFpostRealValidate() {
            return fpostRealValidate;
        }

        public void setFpostRealValidate(boolean fpostRealValidate) {
            this.fpostRealValidate = fpostRealValidate;
        }

        public FpostRealValidateTimeBeanX getFpostRealValidateTime() {
            return fpostRealValidateTime;
        }

        public void setFpostRealValidateTime(FpostRealValidateTimeBeanX fpostRealValidateTime) {
            this.fpostRealValidateTime = fpostRealValidateTime;
        }

        public String getFqrcodePath() {
            return fqrcodePath;
        }

        public void setFqrcodePath(String fqrcodePath) {
            this.fqrcodePath = fqrcodePath;
        }

        public String getFrealName() {
            return frealName;
        }

        public void setFrealName(String frealName) {
            this.frealName = frealName;
        }

        public String getFregIp() {
            return fregIp;
        }

        public void setFregIp(String fregIp) {
            this.fregIp = fregIp;
        }

        public int getFregType() {
            return fregType;
        }

        public void setFregType(int fregType) {
            this.fregType = fregType;
        }

        public Object getFregisterTime() {
            return fregisterTime;
        }

        public void setFregisterTime(Object fregisterTime) {
            this.fregisterTime = fregisterTime;
        }

        public String getFregtype_s() {
            return fregtype_s;
        }

        public void setFregtype_s(String fregtype_s) {
            this.fregtype_s = fregtype_s;
        }

        public Object getFscore() {
            return fscore;
        }

        public void setFscore(Object fscore) {
            this.fscore = fscore;
        }

        public int getFstatus() {
            return fstatus;
        }

        public void setFstatus(int fstatus) {
            this.fstatus = fstatus;
        }

        public String getFstatus_s() {
            return fstatus_s;
        }

        public void setFstatus_s(String fstatus_s) {
            this.fstatus_s = fstatus_s;
        }

        public String getFtelephone() {
            return ftelephone;
        }

        public void setFtelephone(String ftelephone) {
            this.ftelephone = ftelephone;
        }

        public String getFtradePassword() {
            return ftradePassword;
        }

        public void setFtradePassword(String ftradePassword) {
            this.ftradePassword = ftradePassword;
        }

        public String getFuserNo() {
            return fuserNo;
        }

        public void setFuserNo(String fuserNo) {
            this.fuserNo = fuserNo;
        }

        public Object getFusersetting() {
            return fusersetting;
        }

        public void setFusersetting(Object fusersetting) {
            this.fusersetting = fusersetting;
        }

        public String getQqlogin() {
            return qqlogin;
        }

        public void setQqlogin(String qqlogin) {
            this.qqlogin = qqlogin;
        }

        public String getSalt() {
            return salt;
        }

        public void setSalt(String salt) {
            this.salt = salt;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public List<?> getEmailvalidates() {
            return emailvalidates;
        }

        public void setEmailvalidates(List<?> emailvalidates) {
            this.emailvalidates = emailvalidates;
        }

        public List<?> getFbankinfoWithdraws() {
            return fbankinfoWithdraws;
        }

        public void setFbankinfoWithdraws(List<?> fbankinfoWithdraws) {
            this.fbankinfoWithdraws = fbankinfoWithdraws;
        }

        public List<?> getFbankinfos() {
            return fbankinfos;
        }

        public void setFbankinfos(List<?> fbankinfos) {
            this.fbankinfos = fbankinfos;
        }

        public List<?> getFcapitaloperations() {
            return fcapitaloperations;
        }

        public void setFcapitaloperations(List<?> fcapitaloperations) {
            this.fcapitaloperations = fcapitaloperations;
        }

        public List<?> getFentrustplans() {
            return fentrustplans;
        }

        public void setFentrustplans(List<?> fentrustplans) {
            this.fentrustplans = fentrustplans;
        }

        public List<?> getFentrusts() {
            return fentrusts;
        }

        public void setFentrusts(List<?> fentrusts) {
            this.fentrusts = fentrusts;
        }

        public List<?> getFvirtualaddressWithdraws() {
            return fvirtualaddressWithdraws;
        }

        public void setFvirtualaddressWithdraws(List<?> fvirtualaddressWithdraws) {
            this.fvirtualaddressWithdraws = fvirtualaddressWithdraws;
        }

        public List<?> getFvirtualaddresses() {
            return fvirtualaddresses;
        }

        public void setFvirtualaddresses(List<?> fvirtualaddresses) {
            this.fvirtualaddresses = fvirtualaddresses;
        }

        public List<?> getFvirtualcaptualoperations() {
            return fvirtualcaptualoperations;
        }

        public void setFvirtualcaptualoperations(List<?> fvirtualcaptualoperations) {
            this.fvirtualcaptualoperations = fvirtualcaptualoperations;
        }

        public List<?> getFvirtualwallets() {
            return fvirtualwallets;
        }

        public void setFvirtualwallets(List<?> fvirtualwallets) {
            this.fvirtualwallets = fvirtualwallets;
        }

        public List<?> getValidateemailses() {
            return validateemailses;
        }

        public void setValidateemailses(List<?> validateemailses) {
            this.validateemailses = validateemailses;
        }

        public static class FhasRealValidateTimeBeanX {
            /**
             * date : 17
             * day : 5
             * hours : 10
             * minutes : 20
             * month : 4
             * nanos : 0
             * seconds : 17
             * time : 1558059617000
             * timezoneOffset : -480
             * year : 119
             */

            private int date;
            private int day;
            private int hours;
            private int minutes;
            private int month;
            private int nanos;
            private int seconds;
            private long time;
            private int timezoneOffset;
            private int year;

            public int getDate() {
                return date;
            }

            public void setDate(int date) {
                this.date = date;
            }

            public int getDay() {
                return day;
            }

            public void setDay(int day) {
                this.day = day;
            }

            public int getHours() {
                return hours;
            }

            public void setHours(int hours) {
                this.hours = hours;
            }

            public int getMinutes() {
                return minutes;
            }

            public void setMinutes(int minutes) {
                this.minutes = minutes;
            }

            public int getMonth() {
                return month;
            }

            public void setMonth(int month) {
                this.month = month;
            }

            public int getNanos() {
                return nanos;
            }

            public void setNanos(int nanos) {
                this.nanos = nanos;
            }

            public int getSeconds() {
                return seconds;
            }

            public void setSeconds(int seconds) {
                this.seconds = seconds;
            }

            public long getTime() {
                return time;
            }

            public void setTime(long time) {
                this.time = time;
            }

            public int getTimezoneOffset() {
                return timezoneOffset;
            }

            public void setTimezoneOffset(int timezoneOffset) {
                this.timezoneOffset = timezoneOffset;
            }

            public int getYear() {
                return year;
            }

            public void setYear(int year) {
                this.year = year;
            }
        }

        public static class FpostRealValidateTimeBeanX {
            /**
             * date : 17
             * day : 5
             * hours : 10
             * minutes : 18
             * month : 4
             * nanos : 0
             * seconds : 22
             * time : 1558059502000
             * timezoneOffset : -480
             * year : 119
             */

            private int date;
            private int day;
            private int hours;
            private int minutes;
            private int month;
            private int nanos;
            private int seconds;
            private long time;
            private int timezoneOffset;
            private int year;

            public int getDate() {
                return date;
            }

            public void setDate(int date) {
                this.date = date;
            }

            public int getDay() {
                return day;
            }

            public void setDay(int day) {
                this.day = day;
            }

            public int getHours() {
                return hours;
            }

            public void setHours(int hours) {
                this.hours = hours;
            }

            public int getMinutes() {
                return minutes;
            }

            public void setMinutes(int minutes) {
                this.minutes = minutes;
            }

            public int getMonth() {
                return month;
            }

            public void setMonth(int month) {
                this.month = month;
            }

            public int getNanos() {
                return nanos;
            }

            public void setNanos(int nanos) {
                this.nanos = nanos;
            }

            public int getSeconds() {
                return seconds;
            }

            public void setSeconds(int seconds) {
                this.seconds = seconds;
            }

            public long getTime() {
                return time;
            }

            public void setTime(long time) {
                this.time = time;
            }

            public int getTimezoneOffset() {
                return timezoneOffset;
            }

            public void setTimezoneOffset(int timezoneOffset) {
                this.timezoneOffset = timezoneOffset;
            }

            public int getYear() {
                return year;
            }

            public void setYear(int year) {
                this.year = year;
            }
        }
    }

    public static class XiadanTimeBean {
        /**
         * date : 24
         * day : 5
         * hours : 11
         * minutes : 36
         * month : 4
         * nanos : 0
         * seconds : 46
         * time : 1558669006000
         * timezoneOffset : -480
         * year : 119
         */

        private int date;
        private int day;
        private int hours;
        private int minutes;
        private int month;
        private int nanos;
        private int seconds;
        private long time;
        private int timezoneOffset;
        private int year;

        public int getDate() {
            return date;
        }

        public void setDate(int date) {
            this.date = date;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public int getHours() {
            return hours;
        }

        public void setHours(int hours) {
            this.hours = hours;
        }

        public int getMinutes() {
            return minutes;
        }

        public void setMinutes(int minutes) {
            this.minutes = minutes;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public int getNanos() {
            return nanos;
        }

        public void setNanos(int nanos) {
            this.nanos = nanos;
        }

        public int getSeconds() {
            return seconds;
        }

        public void setSeconds(int seconds) {
            this.seconds = seconds;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public int getTimezoneOffset() {
            return timezoneOffset;
        }

        public void setTimezoneOffset(int timezoneOffset) {
            this.timezoneOffset = timezoneOffset;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }
    }
}
