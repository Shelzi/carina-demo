package com.qaprosoft.carina.demo.allinstruents.android;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends AbstractPage {

    @FindBy(id = "com.notissimus.allinstruments.android:id/buttonSecondaryAction")
    private ExtendedWebElement addToBucketButton;

    @FindBy(id = "com.notissimus.allinstruments.android:id/action_compare")
    private ExtendedWebElement addToCompareButton;

    @FindBy(xpath = "//android.view.ViewGroup[@resource-id = 'com.notissimus.allinstruments.android:id/toolbar']//android.widget.ImageButton")
    private ExtendedWebElement goBackButton;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage clickOnInstantBuy() {
        assertElementPresent(addToBucketButton);
        addToBucketButton.click();
        return new CheckoutPage(getDriver());
    }

    public void clickOnAddToCompareButton() {
        assertElementPresent(addToCompareButton);
        addToCompareButton.click();
    }

    public void clickOnGoBackButton() {
        assertElementPresent(goBackButton);
        goBackButton.click();
    }
}
