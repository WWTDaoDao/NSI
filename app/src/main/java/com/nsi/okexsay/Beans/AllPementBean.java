package com.nsi.okexsay.Beans;
//解析所有的支付选项
public class AllPementBean {

    /**
     * bankInfoopen : 无
     * faddress : 山东济南历下区
     * fbankNumber : 6212260200102164148
     * fbankType : 1
     * fcreateTime : {"date":6,"day":4,"hours":9,"minutes":47,"month":5,"nanos":0,"seconds":27,"time":1559785647000,"timezoneOffset":-480,"year":119}
     * fid : 63
     * fname : 工商银行
     * fothers : 软件园
     * fstatus : 1
     * fuser : null
     * legalCurrency : null
     * otherName :
     * state : 0
     * version : 0
     */

    private String bankInfoopen;
    private String faddress;
    private String fbankNumber;
    private int fbankType;
    private FcreateTimeBean fcreateTime;
    private int fid;
    private String fname;
    private String fothers;
    private int fstatus;
    private Object fuser;
    private Object legalCurrency;
    private String otherName;
    private int state;
    private int version;

    public String getBankInfoopen() {
        return bankInfoopen;
    }

    public void setBankInfoopen(String bankInfoopen) {
        this.bankInfoopen = bankInfoopen;
    }

    public String getFaddress() {
        return faddress;
    }

    public void setFaddress(String faddress) {
        this.faddress = faddress;
    }

    public String getFbankNumber() {
        return fbankNumber;
    }

    public void setFbankNumber(String fbankNumber) {
        this.fbankNumber = fbankNumber;
    }

    public int getFbankType() {
        return fbankType;
    }

    public void setFbankType(int fbankType) {
        this.fbankType = fbankType;
    }

    public FcreateTimeBean getFcreateTime() {
        return fcreateTime;
    }

    public void setFcreateTime(FcreateTimeBean fcreateTime) {
        this.fcreateTime = fcreateTime;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getFothers() {
        return fothers;
    }

    public void setFothers(String fothers) {
        this.fothers = fothers;
    }

    public int getFstatus() {
        return fstatus;
    }

    public void setFstatus(int fstatus) {
        this.fstatus = fstatus;
    }

    public Object getFuser() {
        return fuser;
    }

    public void setFuser(Object fuser) {
        this.fuser = fuser;
    }

    public Object getLegalCurrency() {
        return legalCurrency;
    }

    public void setLegalCurrency(Object legalCurrency) {
        this.legalCurrency = legalCurrency;
    }

    public String getOtherName() {
        return otherName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public static class FcreateTimeBean {
        /**
         * date : 6
         * day : 4
         * hours : 9
         * minutes : 47
         * month : 5
         * nanos : 0
         * seconds : 27
         * time : 1559785647000
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
