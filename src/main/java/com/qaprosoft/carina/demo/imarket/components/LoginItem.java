package com.qaprosoft.carina.demo.imarket.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    public void inputEmail(String s) {
        assertElementPresent(emailInput);
        inputText(emailInput, s);
    }

    public void inputPassword(String s) {
        assertElementPresent(passwordInput);
        inputText(passwordInput, s);
    }

    public void clickOnCaptcha() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//div[@id = 'AuthEmailTab']//iframe[starts-with(@title, 'reCAPTCHA')]")));
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.rc-anchor-content"))).click();
        pause(7);
    }

    public void clickOnSubmitLoginButton() {
        //waitUntil(ExpectedConditions.visibilityOf(submitLoginButton.getElement()), EXPLICIT_TIMEOUT);
        //System.out.println(submitLoginButton.getElement().isEnabled());
        //System.out.println(getDriver().getPageSource());
        //doubleTapElement(submitLoginButton);
        /*JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].tap();", submitLoginButton.getElement());*/
        //new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("authEmailSubmit")));
        //new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.id("authEmailSubmit"))).click();
        submitLoginButton.click();
    }

    public boolean doubleTapElement(ExtendedWebElement el) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            HashMap<String, String> tapObject = new HashMap<>();
            tapObject.put("x", String.valueOf(el.getLocation().getX() + el.getSize().getWidth() / 2));
            tapObject.put("y", String.valueOf(el.getLocation().getY() + el.getSize().getHeight() / 2));
            js.executeScript("mobile:doubleTap", tapObject);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    protected void inputText(ExtendedWebElement extendedWebElement, String text) {
        extendedWebElement.type(text);
    }

}
