package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.saucedemo.utils.BrowserType;

public class LoginPage extends BasePage {
    
    private static final String SAUCE_DEMO_URL = "https://www.saucedemo.com/";

    @FindBy(id = "user-name")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(xpath = "//div[@class='login_credentials']")
    private WebElement loginCredentials; 

    @FindBy(xpath = "//div[@class='login_password']")
    private WebElement loginPassword; 

    private By errorMessage = By.xpath("//h3[@data-test='error']");
    
    private WebDriverWait wait;

    public LoginPage(BrowserType browser) {
        super(browser);
        this.wait = new WebDriverWait(getDriver(), 10);
        PageFactory.initElements(getDriver(), this);
    }

    public LoginPage open() {
        getDriver().get(SAUCE_DEMO_URL);
        return this;
    }

    public LoginPage clickLogin(){
        loginButton.click();
        return this;
    }

    public LoginPage clearLoginInfo() {
        clearFieldText(usernameField);
        clearFieldText(passwordField);
        return this;
    }

    public LoginPage clearPassword() {
        clearFieldText(passwordField);
        return this;
    }

    public LoginPage enterAnyCredentials() {
        enterUsername("anyUsername");
        enterPassword("anyPassword");
        return this;
    }

    public LoginPage enterCorrectCredentials() {
        enterUsername(loginCredentials.getText().split("\n")[1]);
        enterPassword(loginPassword.getText().split("\n")[1]);
        return this;
    }   

    public String getErrorMessage() {
        var condition = ExpectedConditions.visibilityOfElementLocated(errorMessage);
        WebElement errorMessage = wait.until(condition);
        return errorMessage.getText();
    }

    public String getTitle(String title) {
        wait.until(ExpectedConditions.titleIs(title));
        return getDriver().getTitle();
    }
     
    private void enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOf(usernameField));
        usernameField.sendKeys(username);
    }

    private void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordField));
        passwordField.sendKeys(password);
    }

    private void clearFieldText(WebElement textField) {
        wait.until(ExpectedConditions.visibilityOf(textField));
        textField.sendKeys(Keys.CONTROL + "a");
        textField.sendKeys(Keys.DELETE);
    }
}