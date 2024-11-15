package com.saucedemo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import com.saucedemo.pages.LoginPage;
import com.saucedemo.utils.BrowserType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Execution(ExecutionMode.CONCURRENT)
public class LoginFormTests extends BaseTests {
    private static final Logger logger = LogManager.getLogger(LoginFormTests.class);
    private LoginPage loginPage;

    @DisplayName("Login with empty credentials on ")
    @ParameterizedTest(name = "{displayName} {0}")
    @EnumSource(BrowserType.class)
    public void testLoginWithEmptyCredentials(BrowserType browser) {
        initTestPage(browser);

        String errorMessage =
        loginPage.open()
                 .enterAnyCredentials()
                 .clearLoginInfo()
                 .clickLogin()
                 .getErrorMessage();
        
        assertContainsString(errorMessage, "Username is required");
        
        loginPage.closeBrowser();
        logEndTest();
    }

    @DisplayName("Login with empty password on ")
    @ParameterizedTest(name = "{displayName} {0}")
    @EnumSource(BrowserType.class)
    public void testLoginWithEmptyPassword(BrowserType browser) {
        initTestPage(browser);

        var errorMessage = 
        loginPage.open()
                 .enterAnyCredentials()
                 .clearPassword()
                 .clickLogin()
                 .getErrorMessage();
              
        assertContainsString(errorMessage, "Password is required");
        
        loginPage.closeBrowser();
        logEndTest();
    }

    @DisplayName("Login with valid credentials on ")
    @ParameterizedTest(name = "{displayName} {0}")
    @EnumSource(BrowserType.class)
    public void testLoginWithValidCredentials(BrowserType browser) {
        initTestPage(browser);
 
        String expectedTitle = "Swag Labs";
        var actualTitle =
        loginPage.open()
                 .enterCorrectCredentials()
                 .clickLogin()
                 .getTitle(expectedTitle);

        assertEquals(actualTitle, expectedTitle);
        
        loginPage.closeBrowser();
        logEndTest();
    }

    private void initTestPage(BrowserType browser) {
        loginPage = new LoginPage(browser);
        logStartTest();
    }

    private void logStartTest() {
        logger.info(String.format("Starting %s on %s", getMethodName(2), loginPage.getBrowser()));
    }

    private void logEndTest() {
        logger.info(String.format("Completed %s on %s", getMethodName(1), loginPage.getBrowser()));
    }    
}