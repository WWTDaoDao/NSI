package com.nsi.okexsay.utils;

import android.util.Log;

/**
 * log数据的工具类
 * Created by Liuhuan on 2016/9/1.
 */
public class LogUtil {
    private final static boolean LOG_FLAG = true;
    private final static String TAG = "JKY";
    private final static int LEVEL =  Log.WARN;

    /**
     * Get The Current Function Name
     * @return
     */
    private static String[] getLogInfo() {
        StackTraceElement[] sts = Thread.currentThread().getStackTrace();
        if (sts == null) {
            return null;
        }

        for (StackTraceElement st : sts) {
            if (st.isNativeMethod()) {
                continue;
            }

            if (st.getClassName().equals(Thread.class.getName())) {
                continue;
            }

            if (st.getClassName().equals(LogUtil.class.getName())) {
                continue;
            }
            
            String tag = st.getFileName();
            if (tag == null) {
                tag = TAG;
            }
            
            String msgPrefix = "[ " + Thread.currentThread().getName() + "::" + st.getMethodName() + ":" + st.getLineNumber() + " ]";
            
            return new String[] { tag, msgPrefix };
        }

        return new String[] { TAG, "" };
    }

    /**
     * The Log LEVEL:i
     * 
     * @param str
     */
    public static void i(Object str) {
        i(null, str);
    }
    
    @SuppressWarnings("unused")
    public static void i(String tag, Object str) {
        if (!LOG_FLAG || LEVEL > Log.INFO) {
            return;
        }
        String[] logInfo = getLogInfo();
        Log.i(tag == null ? logInfo[0] : tag, logInfo[1] + " -- " + str.toString());
    }


    /**
     * The Log LEVEL:d
     * 
     * @param str
     */
    public static void d(Object str) {
        d(null, str);
    }
    
    @SuppressWarnings("unused")
    public static void d(String tag, Object str) {
        if (!LOG_FLAG || LEVEL > Log.DEBUG) {
            return;
        }
        String[] logInfo = getLogInfo();
        Log.d(tag == null ? logInfo[0] : tag, logInfo[1] + " -- " + str.toString());
    }

    
    /**
     * The Log LEVEL:V
     * 
     * @param str
     */
    public static void v(Object str) {
        v(null, str);
    }
    
    @SuppressWarnings("unused")
    public static void v(String tag, Object str) {
        if (!LOG_FLAG || LEVEL > Log.VERBOSE) {
            return;
        }
        String[] logInfo = getLogInfo();
        Log.v(tag == null ? logInfo[0] : tag, logInfo[1] + " -- " + str.toString());
    }

    /**
     * The Log LEVEL:w
     * 
     * @param str
     */
    public static void w(Object str) {
        w(null, str);
    }
    
    @SuppressWarnings("unused")
    public static void w(String tag, Object str) {
        if (!LOG_FLAG || LEVEL > Log.WARN) {
            return;
        }
        String[] logInfo = getLogInfo();
        Log.w(tag == null ? logInfo[0] : tag, logInfo[1] + " -- " + str.toString());
    }

    /**
     * The Log LEVEL:e
     * 
     * @param str
     */
    public static void e(Object str) {
        e(null, str);
    }
    
    @SuppressWarnings("unused")
    public static void e(String tag, Object str) {
        if (!LOG_FLAG || LEVEL > Log.ERROR) {
            return;
        }
        String[] logInfo = getLogInfo();
        Log.e(tag == null ? logInfo[0] : tag, logInfo[1] + " -- " + str.toString());
    }

    /**
     * The Log LEVEL:e
     * 
     * @param tr
     */
    public static void e(Throwable tr) {
        e(null, "Exception: ", tr);
    }

    /**
     * The Log LEVEL:e
     * 
     * @param log
     * @param tr
     */
    public static void e(String log, Throwable tr) {
        e(null, log, tr);
    }
    
    @SuppressWarnings("unused")
    public static void e(String tag, String log, Throwable tr) {
        if (!LOG_FLAG || LEVEL > Log.ERROR) {
            return;
        }
        String[] logInfo = getLogInfo();
        Log.e(tag == null ? logInfo[0] : tag, logInfo[1] + " -- " + log, tr);
    }
}
