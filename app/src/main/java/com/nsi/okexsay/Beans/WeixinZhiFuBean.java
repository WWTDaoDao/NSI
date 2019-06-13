package com.nsi.okexsay.Beans;

public class WeixinZhiFuBean {

    /**
     * accountnumber : 15315136958
     * fCreateTime : {"date":18,"day":6,"hours":18,"minutes":20,"month":4,"nanos":0,"seconds":40,"time":1558174840000,"timezoneOffset":-480,"year":119}
     * fId : 69
     * fName : 刘永华
     * fimgpath : /upload/images/201905181820040_69zoZ.png
     * fremark :
     * fstate : 2
     * fuser : null
     * otherName : 刘永华
     * state : 0
     */

    private String accountnumber;
    private FCreateTimeBean fCreateTime;
    private int fId;
    private String fName;
    private String fimgpath;
    private String fremark;
    private String fstate;
    private Object fuser;
    private String otherName;
    private int state;

    public String getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(String accountnumber) {
        this.accountnumber = accountnumber;
    }

    public FCreateTimeBean getFCreateTime() {
        return fCreateTime;
    }

    public void setFCreateTime(FCreateTimeBean fCreateTime) {
        this.fCreateTime = fCreateTime;
    }

    public int getFId() {
        return fId;
    }

    public void setFId(int fId) {
        this.fId = fId;
    }

    public String getFName() {
        return fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public String getFimgpath() {
        return fimgpath;
    }

    public void setFimgpath(String fimgpath) {
        this.fimgpath = fimgpath;
    }

    public String getFremark() {
        return fremark;
    }

    public void setFremark(String fremark) {
        this.fremark = fremark;
    }

    public String getFstate() {
        return fstate;
    }

    public void setFstate(String fstate) {
        this.fstate = fstate;
    }

    public Object getFuser() {
        return fuser;
    }

    public void setFuser(Object fuser) {
        this.fuser = fuser;
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

    public static class FCreateTimeBean {
        /**
         * date : 18
         * day : 6
         * hours : 18
         * minutes : 20
         * month : 4
         * nanos : 0
         * seconds : 40
         * time : 1558174840000
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
