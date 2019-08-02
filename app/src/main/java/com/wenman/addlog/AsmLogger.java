package com.wenman.addlog;

import android.util.Log;

public class AsmLogger {
    public static final String TAG = "AsmLogger";

    public static void  log(String methodName, String desc) {
        Log.d(TAG, "method : " + methodName + "  desc : " +desc + "  timeStamp : " + System.currentTimeMillis());
    }
}
