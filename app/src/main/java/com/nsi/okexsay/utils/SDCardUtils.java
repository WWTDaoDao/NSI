package com.nsi.okexsay.utils;

import android.os.Environment;

import java.io.File;


public class SDCardUtils {

    public static String getSdcardPath() {
        String pathTemp = Environment.getExternalStorageDirectory().getPath();
        if (android.os.Build.DEVICE.contains("Samsung") || android.os.Build.MANUFACTURER.contains("Samsung")) {
            pathTemp = pathTemp + "/external_sd/";
        }
        return pathTemp;
    }


}
