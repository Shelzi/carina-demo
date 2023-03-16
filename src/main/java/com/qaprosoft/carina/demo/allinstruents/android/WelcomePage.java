package com.qaprosoft.carina.demo.allinstruents.android;

import com.qaprosoft.carina.core.gui.AbstractPage;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@Getter
public class WelcomePage extends AbstractPage {
    @FindBy(xpath = "//android.widget.TextView[@resource-id='com.notissimus.allinstruments.android:id/action_skip']")
    private ExtendedWebElement skipBtn;

    public WelcomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageOpened() {
        return skipBtn.isElementPresent();
    }

    public boolean isSkipBtnPresent() {
        return skipBtn.isPresent();
    }

    public TownSelectPage clickSkipBtn() {
        skipBtn.click();
        return new TownSelectPage(getDriver());
    }
}
