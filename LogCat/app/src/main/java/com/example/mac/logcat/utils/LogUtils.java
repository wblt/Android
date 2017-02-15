package com.example.mac.logcat.utils;

import android.util.Log;

/**
 * Created by mac on 17/2/15.
 */

public class LogUtils {
    private static boolean enablelog = false;
    public static void e(String tag , String msg){
        if (enablelog) {
            Log.e(tag,msg);
        }
    }

    public static void w(String tag , String msg){
        if (enablelog) {
            Log.w(tag,msg);
        }
    }

    public static void i(String tag , String msg){
        if (enablelog) {
            Log.i(tag,msg);
        }
    }

    public static void d(String tag , String msg){
        if (enablelog) {
            Log.d(tag,msg);
        }
    }

    public static void v(String tag , String msg){
        if (enablelog) {
            Log.v(tag,msg);
        }
    }
}
