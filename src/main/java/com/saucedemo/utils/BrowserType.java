package com.saucedemo.utils;

public enum BrowserType {
    Chrome("chrome"),
    Edge("edge");

    private final String browser;

    BrowserType(String browser) {
        this.browser = browser;
    }

    public String getBrowser() {
        return browser;
    }
}