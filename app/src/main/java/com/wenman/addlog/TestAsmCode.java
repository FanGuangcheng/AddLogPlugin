package com.wenman.addlog;

import android.util.Log;
import android.widget.Toast;

public class TestAsmCode {

    public void testAsmCode() {
        AsmLogger.log("testAsmCode", "start");
        int index = 0;
        index += 1;
        AsmLogger.log("testAsmCode", "end");
    }
}
