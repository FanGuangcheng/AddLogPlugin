package com.addlog;

import java.util.List;
import java.util.Map;

public class AddLogExtension {

    public Map<String, List<String>> hookPoint;
    public Map<String, String> logPrinter;

    @Override
    public String toString() {
        return "addLog { hookPoint = " + hookPoint + ", logHandler = " + logPrinter + " }" ;
    }
}
