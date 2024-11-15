package com.saucedemo;

import static org.junit.jupiter.api.Assertions.assertTrue;
import com.saucedemo.utils.BrowserType;

public class BaseTests {

    protected BrowserType browser;
    protected void assertContainsString(String actual, String expected) {
        assertTrue(actual.contains(expected));
    }

    protected String getMethodName(int depth) {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        return stackTraceElements[depth + 2].getMethodName();
    }
}