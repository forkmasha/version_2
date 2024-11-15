package com.saucedemo.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import java.util.Objects;
import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverFactory {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void setDriver(BrowserType browser) {
        WebDriver webDriver;
        switch (browser) {
            case Chrome:
                WebDriverManager.chromedriver().setup();
                webDriver = new ChromeDriver();
                break;
            case Edge:
                WebDriverManager.edgedriver().setup();
                webDriver = new EdgeDriver();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + browser);
        }
        driver.set(Objects.requireNonNull(webDriver));
    }

    public static WebDriver getDriver(){
        return Objects.requireNonNull(driver.get());
    }

    public static void closeBrowser() {
         getDriver().close();
         getDriver().quit();
    }
}