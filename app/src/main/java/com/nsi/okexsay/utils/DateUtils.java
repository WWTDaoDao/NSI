package com.nsi.okexsay.utils;

import java.util.Date;

public class DateUtils {

    private DateUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static long getCurrentTime() {
        long d = new Date().getTime() / 1000;
        return d;
    }
}
