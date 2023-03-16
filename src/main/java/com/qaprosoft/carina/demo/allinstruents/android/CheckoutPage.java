package com.qaprosoft.carina.demo.allinstruents.android;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class CheckoutPage extends AbstractPage {

    @FindBy(id = "com.notissimus.allinstruments.android:id/radioButtonCallMe")
    private ExtendedWebElement callMeRadioButton;

    @FindBy(id = "com.notissimus.allinstruments.android:id/editTextName")
    private ExtendedWebElement nameInput;

    @FindBy(id = "com.notissimus.allinstruments.android:id/editTextPhone")
    private ExtendedWebElement phoneInput;


    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnCallMeRadioButton() {
        assertElementPresent(callMeRadioButton);
        callMeRadioButton.click();
    }

    public boolean isPhoneInputPresent() {
        return phoneInput.isPresent();
    }

    public boolean isNameInputPresent() {
        return nameInput.isPresent();
    }

    public void inputName(String name) {
        inputText(nameInput, name);
        checkInput(nameInput, name);
    }

    public void inputPhone(String phone) {
        inputText(phoneInput, phone);
        checkPhoneInput (phoneInput, phone);
    }

    protected void inputText(ExtendedWebElement extendedWebElement, String text) {
        extendedWebElement.type(text);
    }

    private void checkInput(ExtendedWebElement extendedWebElement, String s) {
        Assert.assertTrue(extendedWebElement.getElement().getText().equalsIgnoreCase(s));
    }

    private void checkPhoneInput(ExtendedWebElement extendedWebElement, String s) {
        Assert.assertTrue(extendedWebElement.getElement().getText()
                .replaceAll(" ","").replaceAll("-", "").replace(")","").contains(s));
    }
}
