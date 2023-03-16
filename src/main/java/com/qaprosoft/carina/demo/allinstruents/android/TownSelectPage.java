package com.qaprosoft.carina.demo.allinstruents.android;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class TownSelectPage extends AbstractPage {

    @FindBy(xpath = "//android.widget.TextView[@text = 'Москва']")
    private ExtendedWebElement townBtn;

    public TownSelectPage(WebDriver driver) {
        super(driver);
    }

    public boolean isTownBtnPresent() {
        return townBtn.isElementPresent();
    }

    public HomePage clickOnTownBtn() {
        townBtn.click();
        return new HomePage(getDriver());
    }
}
