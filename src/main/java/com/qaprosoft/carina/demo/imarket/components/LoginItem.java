package com.qaprosoft.carina.demo.imarket.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import com.qaprosoft.carina.demo.imarket.user.User;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.HashMap;

public class LoginItem extends AbstractUIObject {

    @FindBy(xpath = "//div[contains(@class, 'popup-reg-auth')]//a[contains(@href, '#AuthEmailTab')]")
    private ExtendedWebElement emailTab;

    @FindBy(id = "authEmail")
    private ExtendedWebElement emailInput;

    @FindBy(id = "authPassword")
    private ExtendedWebElement passwordInput;

    @FindBy(xpath = "//div[@id = 'AuthEmailTab']//button")
    private ExtendedWebElement submitLoginButton;

    public LoginItem(WebDriver driver) {
        super(driver);
    }

    public LoginItem(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public void clickOnEmailTab() {
        assertElementPresent(emailTab);
        emailTab.click();
    }

    public void inputUserCredentials(User user) {
        inputEmail(user.getLogin());
        inputPassword(user.getPassword());
    }

    public boolean isPasswordInputFieldPresent(long timeout){
        return passwordInput.isPresent(timeout);
    }

    public boolean isLoginInputFieldPresent(long timeout){
        return emailInput.isPresent(timeout);
    }

    public void inputEmail(String s) {
        inputText(emailInput, s);
    }

    public void inputPassword(String s) {
        inputText(passwordInput, s);
    }

    public void clickOnCaptcha() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//div[@id = 'AuthEmailTab']//iframe[starts-with(@title, 'reCAPTCHA')]")));
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.rc-anchor-content"))).click();
    }

    public void clickOnSubmitLoginButton() {
        assertElementPresent(submitLoginButton);
        submitLoginButton.click();
    }

    protected void inputText(ExtendedWebElement extendedWebElement, String text) {
        extendedWebElement.type(text);
    }
}
