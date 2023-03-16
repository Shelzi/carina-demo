package com.qaprosoft.carina.demo.allinstruents.android;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class LoginPage extends AbstractPage {

    @FindBy(id = "com.notissimus.allinstruments.android:id/editTextEmail")
    private ExtendedWebElement emailInput;

    @FindBy(id = "com.notissimus.allinstruments.android:id/editTextPassword")
    private ExtendedWebElement passwordInput;

    @FindBy(id = "com.notissimus.allinstruments.android:id/buttonSignIn")
    private ExtendedWebElement loginBtn;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPasswordInputPresent() {
        return passwordInput.isPresent();
    }

    public boolean isEmailInputPresent() {
        return emailInput.isPresent();
    }

    public void inputEmail(String email) {
        inputText(emailInput, email);
        checkInput(emailInput, email);
    }

    public void inputPassword(String password) {
        inputText(passwordInput, password);
        checkInput(passwordInput, password);
    }

    public void clickOnLoginBtn() {
        assertElementPresent(loginBtn);
        loginBtn.click();
    }

    protected void inputText(ExtendedWebElement extendedWebElement, String text) {
        extendedWebElement.type(text);
    }

    private void checkInput(ExtendedWebElement extendedWebElement, String s) {
        Assert.assertTrue(extendedWebElement.getElement().getText().equalsIgnoreCase(s));
    }
}
