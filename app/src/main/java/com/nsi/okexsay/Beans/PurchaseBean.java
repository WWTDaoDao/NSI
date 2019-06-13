package com.nsi.okexsay.Beans;

import java.util.List;

/**
 * 广告实体对象
 */
public class PurchaseBean {

    /**
     * c2c : {"fId":134,"cptOrUsdt":"1","legalCurrency":{"remarks":"中国","legalName":"人民币","id":1,"areaName":"中国","currencySymbol":"CNY"},"limitMin":100,"payment":"0,1,","limitMinO":100,"agreeFlag":"0","user":{"fid":903163783,"fpostRealValidate":true,"fregIp":"","fintrolUserNo":"","fisTelValidate":false,"fpostRealValidateTime":{"month":3,"timezoneOffset":-480,"time":1556026634000,"minutes":37,"seconds":14,"nanos":0,"hours":21,"day":2,"date":23,"year":119},"fInvalidateIntroCount":0,"version":0,"fentrustplans":[],"emailvalidates":[],"fhasRealValidate":true,"fqrcodePath":"","femail":"","fvirtualaddressWithdraws":[],"fopenSecondValidate":false,"fuserNo":"","fidentityNo":"330719196804253671","fmobilMessageCode":"","fisMailValidate":false,"fbankinfos":[],"fentrusts":[],"fIdentityPath3":"","fhasRealValidateTime":{"month":3,"timezoneOffset":-480,"time":1556026661000,"minutes":37,"seconds":41,"nanos":0,"hours":21,"day":2,"date":23,"year":119},"fareaCode":"86","fidentityType_s":"身份证","flastLoginIp":"","fvirtualcaptualoperations":[],"fstatus_s":"","fstatus":0,"token":"","ftradePassword":"","fregtype_s":"手机注册","fisValid":true,"fopenTelValidate":false,"frealName":"恭喜发财","fisTelephoneBind":true,"ftelephone":"18995818826","floginName":"18995818826","fcapitaloperations":[],"fvirtualwallets":[],"fistiger":false,"fIdentityPath2":"","floginPassword":"","fgoogleValidate":false,"fgoogleAuthenticator":"","qqlogin":"","fopenGoogleValidate":false,"fidentityType":0,"fregType":0,"validateemailses":[],"fIdentityPath":"","fgoogleBind":false,"salt":"","fbankinfoWithdraws":[],"fvirtualaddresses":[],"fnickName":"18995818826","fgoogleurl":"","fidentityNo_s":"3307************3671"},"amount":1000,"amountO":1000,"limitMax":6590,"price":6.59,"status":"1","tradeType":"1","publishTime":{"month":4,"timezoneOffset":-480,"time":1556936374000,"minutes":19,"seconds":34,"nanos":0,"hours":10,"day":6,"date":4,"year":119}}
     */

    private C2cBean c2c;

    public C2cBean getC2c() {
        return c2c;
    }

    public void setC2c(C2cBean c2c) {
        this.c2c = c2c;
    }

    public static class C2cBean {
        /**
         * fId : 134
         * cptOrUsdt : 1
         * legalCurrency : {"remarks":"中国","legalName":"人民币","id":1,"areaName":"中国","currencySymbol":"CNY"}
         * limitMin : 100
         * payment : 0,1,
         * limitMinO : 100
         * agreeFlag : 0
         * user : {"fid":903163783,"fpostRealValidate":true,"fregIp":"","fintrolUserNo":"","fisTelValidate":false,"fpostRealValidateTime":{"month":3,"timezoneOffset":-480,"time":1556026634000,"minutes":37,"seconds":14,"nanos":0,"hours":21,"day":2,"date":23,"year":119},"fInvalidateIntroCount":0,"version":0,"fentrustplans":[],"emailvalidates":[],"fhasRealValidate":true,"fqrcodePath":"","femail":"","fvirtualaddressWithdraws":[],"fopenSecondValidate":false,"fuserNo":"","fidentityNo":"330719196804253671","fmobilMessageCode":"","fisMailValidate":false,"fbankinfos":[],"fentrusts":[],"fIdentityPath3":"","fhasRealValidateTime":{"month":3,"timezoneOffset":-480,"time":1556026661000,"minutes":37,"seconds":41,"nanos":0,"hours":21,"day":2,"date":23,"year":119},"fareaCode":"86","fidentityType_s":"身份证","flastLoginIp":"","fvirtualcaptualoperations":[],"fstatus_s":"","fstatus":0,"token":"","ftradePassword":"","fregtype_s":"手机注册","fisValid":true,"fopenTelValidate":false,"frealName":"恭喜发财","fisTelephoneBind":true,"ftelephone":"18995818826","floginName":"18995818826","fcapitaloperations":[],"fvirtualwallets":[],"fistiger":false,"fIdentityPath2":"","floginPassword":"","fgoogleValidate":false,"fgoogleAuthenticator":"","qqlogin":"","fopenGoogleValidate":false,"fidentityType":0,"fregType":0,"validateemailses":[],"fIdentityPath":"","fgoogleBind":false,"salt":"","fbankinfoWithdraws":[],"fvirtualaddresses":[],"fnickName":"18995818826","fgoogleurl":"","fidentityNo_s":"3307************3671"}
         * amount : 1000
         * amountO : 1000
         * limitMax : 6590
         * price : 6.59
         * status : 1
         * tradeType : 1
         * publishTime : {"month":4,"timezoneOffset":-480,"time":1556936374000,"minutes":19,"seconds":34,"nanos":0,"hours":10,"day":6,"date":4,"year":119}
         */

        private int fId;
        private String cptOrUsdt;
        private LegalCurrencyBean legalCurrency;
        private int limitMin;
        private String payment;
        private int limitMinO;
        private String agreeFlag;
        private UserBean user;
        private double amount;
        private int amountO;
        private double limitMax;
        private double price;
        private String status;
        private String tradeType;
        private PublishTimeBean publishTime;

        public int getFId() {
            return fId;
        }

        public void setFId(int fId) {
            this.fId = fId;
        }

        public String getCptOrUsdt() {
            return cptOrUsdt;
        }

        public void setCptOrUsdt(String cptOrUsdt) {
            this.cptOrUsdt = cptOrUsdt;
        }

        public LegalCurrencyBean getLegalCurrency() {
            return legalCurrency;
        }

        public void setLegalCurrency(LegalCurrencyBean legalCurrency) {
            this.legalCurrency = legalCurrency;
        }

        public int getLimitMin() {
            return limitMin;
        }

        public void setLimitMin(int limitMin) {
            this.limitMin = limitMin;
        }

        public String getPayment() {
            return payment;
        }

        public void setPayment(String payment) {
            this.payment = payment;
        }

        public int getLimitMinO() {
            return limitMinO;
        }

        public void setLimitMinO(int limitMinO) {
            this.limitMinO = limitMinO;
        }

        public String getAgreeFlag() {
            return agreeFlag;
        }

        public void setAgreeFlag(String agreeFlag) {
            this.agreeFlag = agreeFlag;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public double getAmount() {
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

        public double getLimitMax() {
            return limitMax;
        }

        public void setLimitMax(int limitMax) {
            this.limitMax = limitMax;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
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

        public PublishTimeBean getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(PublishTimeBean publishTime) {
            this.publishTime = publishTime;
        }

        public static class LegalCurrencyBean {
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

        public static class UserBean {
            /**
             * fid : 903163783
             * fpostRealValidate : true
             * fregIp :
             * fintrolUserNo :
             * fisTelValidate : false
             * fpostRealValidateTime : {"month":3,"timezoneOffset":-480,"time":1556026634000,"minutes":37,"seconds":14,"nanos":0,"hours":21,"day":2,"date":23,"year":119}
             * fInvalidateIntroCount : 0
             * version : 0
             * fentrustplans : []
             * emailvalidates : []
             * fhasRealValidate : true
             * fqrcodePath :
             * femail :
             * fvirtualaddressWithdraws : []
             * fopenSecondValidate : false
             * fuserNo :
             * fidentityNo : 330719196804253671
             * fmobilMessageCode :
             * fisMailValidate : false
             * fbankinfos : []
             * fentrusts : []
             * fIdentityPath3 :
             * fhasRealValidateTime : {"month":3,"timezoneOffset":-480,"time":1556026661000,"minutes":37,"seconds":41,"nanos":0,"hours":21,"day":2,"date":23,"year":119}
             * fareaCode : 86
             * fidentityType_s : 身份证
             * flastLoginIp :
             * fvirtualcaptualoperations : []
             * fstatus_s :
             * fstatus : 0
             * token :
             * ftradePassword :
             * fregtype_s : 手机注册
             * fisValid : true
             * fopenTelValidate : false
             * frealName : 恭喜发财
             * fisTelephoneBind : true
             * ftelephone : 18995818826
             * floginName : 18995818826
             * fcapitaloperations : []
             * fvirtualwallets : []
             * fistiger : false
             * fIdentityPath2 :
             * floginPassword :
             * fgoogleValidate : false
             * fgoogleAuthenticator :
             * qqlogin :
             * fopenGoogleValidate : false
             * fidentityType : 0
             * fregType : 0
             * validateemailses : []
             * fIdentityPath :
             * fgoogleBind : false
             * salt :
             * fbankinfoWithdraws : []
             * fvirtualaddresses : []
             * fnickName : 18995818826
             * fgoogleurl :
             * fidentityNo_s : 3307************3671
             */

            private int fid;
            private boolean fpostRealValidate;
            private String fregIp;
            private String fintrolUserNo;
            private boolean fisTelValidate;
            private FpostRealValidateTimeBean fpostRealValidateTime;
            private int fInvalidateIntroCount;
            private int version;
            private boolean fhasRealValidate;
            private String fqrcodePath;
            private String femail;
            private boolean fopenSecondValidate;
            private String fuserNo;
            private String fidentityNo;
            private String fmobilMessageCode;
            private boolean fisMailValidate;
            private String fIdentityPath3;
            private FhasRealValidateTimeBean fhasRealValidateTime;
            private String fareaCode;
            private String fidentityType_s;
            private String flastLoginIp;
            private String fstatus_s;
            private int fstatus;
            private String token;
            private String ftradePassword;
            private String fregtype_s;
            private boolean fisValid;
            private boolean fopenTelValidate;
            private String frealName;
            private boolean fisTelephoneBind;
            private String ftelephone;
            private String floginName;
            private boolean fistiger;
            private String fIdentityPath2;
            private String floginPassword;
            private boolean fgoogleValidate;
            private String fgoogleAuthenticator;
            private String qqlogin;
            private boolean fopenGoogleValidate;
            private int fidentityType;
            private int fregType;
            private String fIdentityPath;
            private boolean fgoogleBind;
            private String salt;
            private String fnickName;
            private String fgoogleurl;
            private String fidentityNo_s;
            private List<?> fentrustplans;
            private List<?> emailvalidates;
            private List<?> fvirtualaddressWithdraws;
            private List<?> fbankinfos;
            private List<?> fentrusts;
            private List<?> fvirtualcaptualoperations;
            private List<?> fcapitaloperations;
            private List<?> fvirtualwallets;
            private List<?> validateemailses;
            private List<?> fbankinfoWithdraws;
            private List<?> fvirtualaddresses;

            public int getFid() {
                return fid;
            }

            public void setFid(int fid) {
                this.fid = fid;
            }

            public boolean isFpostRealValidate() {
                return fpostRealValidate;
            }

            public void setFpostRealValidate(boolean fpostRealValidate) {
                this.fpostRealValidate = fpostRealValidate;
            }

            public String getFregIp() {
                return fregIp;
            }

            public void setFregIp(String fregIp) {
                this.fregIp = fregIp;
            }

            public String getFintrolUserNo() {
                return fintrolUserNo;
            }

            public void setFintrolUserNo(String fintrolUserNo) {
                this.fintrolUserNo = fintrolUserNo;
            }

            public boolean isFisTelValidate() {
                return fisTelValidate;
            }

            public void setFisTelValidate(boolean fisTelValidate) {
                this.fisTelValidate = fisTelValidate;
            }

            public FpostRealValidateTimeBean getFpostRealValidateTime() {
                return fpostRealValidateTime;
            }

            public void setFpostRealValidateTime(FpostRealValidateTimeBean fpostRealValidateTime) {
                this.fpostRealValidateTime = fpostRealValidateTime;
            }

            public int getFInvalidateIntroCount() {
                return fInvalidateIntroCount;
            }

            public void setFInvalidateIntroCount(int fInvalidateIntroCount) {
                this.fInvalidateIntroCount = fInvalidateIntroCount;
            }

            public int getVersion() {
                return version;
            }

            public void setVersion(int version) {
                this.version = version;
            }

            public boolean isFhasRealValidate() {
                return fhasRealValidate;
            }

            public void setFhasRealValidate(boolean fhasRealValidate) {
                this.fhasRealValidate = fhasRealValidate;
            }

            public String getFqrcodePath() {
                return fqrcodePath;
            }

            public void setFqrcodePath(String fqrcodePath) {
                this.fqrcodePath = fqrcodePath;
            }

            public String getFemail() {
                return femail;
            }

            public void setFemail(String femail) {
                this.femail = femail;
            }

            public boolean isFopenSecondValidate() {
                return fopenSecondValidate;
            }

            public void setFopenSecondValidate(boolean fopenSecondValidate) {
                this.fopenSecondValidate = fopenSecondValidate;
            }

            public String getFuserNo() {
                return fuserNo;
            }

            public void setFuserNo(String fuserNo) {
                this.fuserNo = fuserNo;
            }

            public String getFidentityNo() {
                return fidentityNo;
            }

            public void setFidentityNo(String fidentityNo) {
                this.fidentityNo = fidentityNo;
            }

            public String getFmobilMessageCode() {
                return fmobilMessageCode;
            }

            public void setFmobilMessageCode(String fmobilMessageCode) {
                this.fmobilMessageCode = fmobilMessageCode;
            }

            public boolean isFisMailValidate() {
                return fisMailValidate;
            }

            public void setFisMailValidate(boolean fisMailValidate) {
                this.fisMailValidate = fisMailValidate;
            }

            public String getFIdentityPath3() {
                return fIdentityPath3;
            }

            public void setFIdentityPath3(String fIdentityPath3) {
                this.fIdentityPath3 = fIdentityPath3;
            }

            public FhasRealValidateTimeBean getFhasRealValidateTime() {
                return fhasRealValidateTime;
            }

            public void setFhasRealValidateTime(FhasRealValidateTimeBean fhasRealValidateTime) {
                this.fhasRealValidateTime = fhasRealValidateTime;
            }

            public String getFareaCode() {
                return fareaCode;
            }

            public void setFareaCode(String fareaCode) {
                this.fareaCode = fareaCode;
            }

            public String getFidentityType_s() {
                return fidentityType_s;
            }

            public void setFidentityType_s(String fidentityType_s) {
                this.fidentityType_s = fidentityType_s;
            }

            public String getFlastLoginIp() {
                return flastLoginIp;
            }

            public void setFlastLoginIp(String flastLoginIp) {
                this.flastLoginIp = flastLoginIp;
            }

            public String getFstatus_s() {
                return fstatus_s;
            }

            public void setFstatus_s(String fstatus_s) {
                this.fstatus_s = fstatus_s;
            }

            public int getFstatus() {
                return fstatus;
            }

            public void setFstatus(int fstatus) {
                this.fstatus = fstatus;
            }

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }

            public String getFtradePassword() {
                return ftradePassword;
            }

            public void setFtradePassword(String ftradePassword) {
                this.ftradePassword = ftradePassword;
            }

            public String getFregtype_s() {
                return fregtype_s;
            }

            public void setFregtype_s(String fregtype_s) {
                this.fregtype_s = fregtype_s;
            }

            public boolean isFisValid() {
                return fisValid;
            }

            public void setFisValid(boolean fisValid) {
                this.fisValid = fisValid;
            }

            public boolean isFopenTelValidate() {
                return fopenTelValidate;
            }

            public void setFopenTelValidate(boolean fopenTelValidate) {
                this.fopenTelValidate = fopenTelValidate;
            }

            public String getFrealName() {
                return frealName;
            }

            public void setFrealName(String frealName) {
                this.frealName = frealName;
            }

            public boolean isFisTelephoneBind() {
                return fisTelephoneBind;
            }

            public void setFisTelephoneBind(boolean fisTelephoneBind) {
                this.fisTelephoneBind = fisTelephoneBind;
            }

            public String getFtelephone() {
                return ftelephone;
            }

            public void setFtelephone(String ftelephone) {
                this.ftelephone = ftelephone;
            }

            public String getFloginName() {
                return floginName;
            }

            public void setFloginName(String floginName) {
                this.floginName = floginName;
            }

            public boolean isFistiger() {
                return fistiger;
            }

            public void setFistiger(boolean fistiger) {
                this.fistiger = fistiger;
            }

            public String getFIdentityPath2() {
                return fIdentityPath2;
            }

            public void setFIdentityPath2(String fIdentityPath2) {
                this.fIdentityPath2 = fIdentityPath2;
            }

            public String getFloginPassword() {
                return floginPassword;
            }

            public void setFloginPassword(String floginPassword) {
                this.floginPassword = floginPassword;
            }

            public boolean isFgoogleValidate() {
                return fgoogleValidate;
            }

            public void setFgoogleValidate(boolean fgoogleValidate) {
                this.fgoogleValidate = fgoogleValidate;
            }

            public String getFgoogleAuthenticator() {
                return fgoogleAuthenticator;
            }

            public void setFgoogleAuthenticator(String fgoogleAuthenticator) {
                this.fgoogleAuthenticator = fgoogleAuthenticator;
            }

            public String getQqlogin() {
                return qqlogin;
            }

            public void setQqlogin(String qqlogin) {
                this.qqlogin = qqlogin;
            }

            public boolean isFopenGoogleValidate() {
                return fopenGoogleValidate;
            }

            public void setFopenGoogleValidate(boolean fopenGoogleValidate) {
                this.fopenGoogleValidate = fopenGoogleValidate;
            }

            public int getFidentityType() {
                return fidentityType;
            }

            public void setFidentityType(int fidentityType) {
                this.fidentityType = fidentityType;
            }

            public int getFregType() {
                return fregType;
            }

            public void setFregType(int fregType) {
                this.fregType = fregType;
            }

            public String getFIdentityPath() {
                return fIdentityPath;
            }

            public void setFIdentityPath(String fIdentityPath) {
                this.fIdentityPath = fIdentityPath;
            }

            public boolean isFgoogleBind() {
                return fgoogleBind;
            }

            public void setFgoogleBind(boolean fgoogleBind) {
                this.fgoogleBind = fgoogleBind;
            }

            public String getSalt() {
                return salt;
            }

            public void setSalt(String salt) {
                this.salt = salt;
            }

            public String getFnickName() {
                return fnickName;
            }

            public void setFnickName(String fnickName) {
                this.fnickName = fnickName;
            }

            public String getFgoogleurl() {
                return fgoogleurl;
            }

            public void setFgoogleurl(String fgoogleurl) {
                this.fgoogleurl = fgoogleurl;
            }

            public String getFidentityNo_s() {
                return fidentityNo_s;
            }

            public void setFidentityNo_s(String fidentityNo_s) {
                this.fidentityNo_s = fidentityNo_s;
            }

            public List<?> getFentrustplans() {
                return fentrustplans;
            }

            public void setFentrustplans(List<?> fentrustplans) {
                this.fentrustplans = fentrustplans;
            }

            public List<?> getEmailvalidates() {
                return emailvalidates;
            }

            public void setEmailvalidates(List<?> emailvalidates) {
                this.emailvalidates = emailvalidates;
            }

            public List<?> getFvirtualaddressWithdraws() {
                return fvirtualaddressWithdraws;
            }

            public void setFvirtualaddressWithdraws(List<?> fvirtualaddressWithdraws) {
                this.fvirtualaddressWithdraws = fvirtualaddressWithdraws;
            }

            public List<?> getFbankinfos() {
                return fbankinfos;
            }

            public void setFbankinfos(List<?> fbankinfos) {
                this.fbankinfos = fbankinfos;
            }

            public List<?> getFentrusts() {
                return fentrusts;
            }

            public void setFentrusts(List<?> fentrusts) {
                this.fentrusts = fentrusts;
            }

            public List<?> getFvirtualcaptualoperations() {
                return fvirtualcaptualoperations;
            }

            public void setFvirtualcaptualoperations(List<?> fvirtualcaptualoperations) {
                this.fvirtualcaptualoperations = fvirtualcaptualoperations;
            }

            public List<?> getFcapitaloperations() {
                return fcapitaloperations;
            }

            public void setFcapitaloperations(List<?> fcapitaloperations) {
                this.fcapitaloperations = fcapitaloperations;
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

            public List<?> getFbankinfoWithdraws() {
                return fbankinfoWithdraws;
            }

            public void setFbankinfoWithdraws(List<?> fbankinfoWithdraws) {
                this.fbankinfoWithdraws = fbankinfoWithdraws;
            }

            public List<?> getFvirtualaddresses() {
                return fvirtualaddresses;
            }

            public void setFvirtualaddresses(List<?> fvirtualaddresses) {
                this.fvirtualaddresses = fvirtualaddresses;
            }

            public static class FpostRealValidateTimeBean {
                /**
                 * month : 3
                 * timezoneOffset : -480
                 * time : 1556026634000
                 * minutes : 37
                 * seconds : 14
                 * nanos : 0
                 * hours : 21
                 * day : 2
                 * date : 23
                 * year : 119
                 */

                private int month;
                private int timezoneOffset;
                private long time;
                private int minutes;
                private int seconds;
                private int nanos;
                private int hours;
                private int day;
                private int date;
                private int year;

                public int getMonth() {
                    return month;
                }

                public void setMonth(int month) {
                    this.month = month;
                }

                public int getTimezoneOffset() {
                    return timezoneOffset;
                }

                public void setTimezoneOffset(int timezoneOffset) {
                    this.timezoneOffset = timezoneOffset;
                }

                public long getTime() {
                    return time;
                }

                public void setTime(long time) {
                    this.time = time;
                }

                public int getMinutes() {
                    return minutes;
                }

                public void setMinutes(int minutes) {
                    this.minutes = minutes;
                }

                public int getSeconds() {
                    return seconds;
                }

                public void setSeconds(int seconds) {
                    this.seconds = seconds;
                }

                public int getNanos() {
                    return nanos;
                }

                public void setNanos(int nanos) {
                    this.nanos = nanos;
                }

                public int getHours() {
                    return hours;
                }

                public void setHours(int hours) {
                    this.hours = hours;
                }

                public int getDay() {
                    return day;
                }

                public void setDay(int day) {
                    this.day = day;
                }

                public int getDate() {
                    return date;
                }

                public void setDate(int date) {
                    this.date = date;
                }

                public int getYear() {
                    return year;
                }

                public void setYear(int year) {
                    this.year = year;
                }
            }

            public static class FhasRealValidateTimeBean {
                /**
                 * month : 3
                 * timezoneOffset : -480
                 * time : 1556026661000
                 * minutes : 37
                 * seconds : 41
                 * nanos : 0
                 * hours : 21
                 * day : 2
                 * date : 23
                 * year : 119
                 */

                private int month;
                private int timezoneOffset;
                private long time;
                private int minutes;
                private int seconds;
                private int nanos;
                private int hours;
                private int day;
                private int date;
                private int year;

                public int getMonth() {
                    return month;
                }

                public void setMonth(int month) {
                    this.month = month;
                }

                public int getTimezoneOffset() {
                    return timezoneOffset;
                }

                public void setTimezoneOffset(int timezoneOffset) {
                    this.timezoneOffset = timezoneOffset;
                }

                public long getTime() {
                    return time;
                }

                public void setTime(long time) {
                    this.time = time;
                }

                public int getMinutes() {
                    return minutes;
                }

                public void setMinutes(int minutes) {
                    this.minutes = minutes;
                }

                public int getSeconds() {
                    return seconds;
                }

                public void setSeconds(int seconds) {
                    this.seconds = seconds;
                }

                public int getNanos() {
                    return nanos;
                }

                public void setNanos(int nanos) {
                    this.nanos = nanos;
                }

                public int getHours() {
                    return hours;
                }

                public void setHours(int hours) {
                    this.hours = hours;
                }

                public int getDay() {
                    return day;
                }

                public void setDay(int day) {
                    this.day = day;
                }

                public int getDate() {
                    return date;
                }

                public void setDate(int date) {
                    this.date = date;
                }

                public int getYear() {
                    return year;
                }

                public void setYear(int year) {
                    this.year = year;
                }
            }
        }

        public static class PublishTimeBean {
            /**
             * month : 4
             * timezoneOffset : -480
             * time : 1556936374000
             * minutes : 19
             * seconds : 34
             * nanos : 0
             * hours : 10
             * day : 6
             * date : 4
             * year : 119
             */

            private int month;
            private int timezoneOffset;
            private long time;
            private int minutes;
            private int seconds;
            private int nanos;
            private int hours;
            private int day;
            private int date;
            private int year;

            public int getMonth() {
                return month;
            }

            public void setMonth(int month) {
                this.month = month;
            }

            public int getTimezoneOffset() {
                return timezoneOffset;
            }

            public void setTimezoneOffset(int timezoneOffset) {
                this.timezoneOffset = timezoneOffset;
            }

            public long getTime() {
                return time;
            }

            public void setTime(long time) {
                this.time = time;
            }

            public int getMinutes() {
                return minutes;
            }

            public void setMinutes(int minutes) {
                this.minutes = minutes;
            }

            public int getSeconds() {
                return seconds;
            }

            public void setSeconds(int seconds) {
                this.seconds = seconds;
            }

            public int getNanos() {
                return nanos;
            }

            public void setNanos(int nanos) {
                this.nanos = nanos;
            }

            public int getHours() {
                return hours;
            }

            public void setHours(int hours) {
                this.hours = hours;
            }

            public int getDay() {
                return day;
            }

            public void setDay(int day) {
                this.day = day;
            }

            public int getDate() {
                return date;
            }

            public void setDate(int date) {
                this.date = date;
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
