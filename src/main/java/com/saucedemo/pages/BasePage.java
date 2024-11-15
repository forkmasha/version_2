package com.saucedemo.pages;

import org.openqa.selenium.WebDriver;

import com.saucedemo.utils.BrowserType;
import com.saucedemo.utils.WebDriverFactory;

public class BasePage {

    protected BrowserType browser;
    
    protected WebDriver getDriver() {
        return WebDriverFactory.getDriver();
    }
    
    public BasePage(BrowserType browser) {
        this.browser = browser;
        WebDriverFactory.setDriver(browser);
    }

    public BrowserType getBrowser() {
        return browser;
    }

    public void closeBrowser() {
        WebDriverFactory.closeBrowser();
    }
}