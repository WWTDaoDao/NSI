package com.nsi.okexsay.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {

    public static String getDateTimeFromMillisecond(Long millisecond){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(millisecond);
        String dateStr = simpleDateFormat.format(date);
        return dateStr;
    }
}