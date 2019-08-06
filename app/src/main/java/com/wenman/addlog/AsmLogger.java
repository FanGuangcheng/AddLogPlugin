package com.wenman.addlog;

import android.util.Log;

public class AsmLogger {
    public static final String TAG = "AsmLogger";

    public static void  logger(String methodInfo) {
        Log.d(TAG, "method info : " + methodInfo + "  timeStamp : " + System.currentTimeMillis());
    }
}
