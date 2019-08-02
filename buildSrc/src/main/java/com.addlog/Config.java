package com.addlog;

public class Config {
    private static Config sInstance = new Config();

    public AddLogExtension extension;
    public static Config getInstance() {
        return sInstance;
    }
}
