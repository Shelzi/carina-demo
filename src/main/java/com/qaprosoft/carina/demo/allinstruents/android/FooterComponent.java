package com.qaprosoft.carina.demo.allinstruents.android;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class FooterComponent extends AbstractPage {

    @FindBy(id = "com.notissimus.allinstruments.android:id/action_more")
    private ExtendedWebElement profileBtn;

    @FindBy(id = "com.notissimus.allinstruments.android:id/action_favorites")
    private ExtendedWebElement favoritesBtn;

    public FooterComponent(WebDriver driver) {
        super(driver);
    }

    public ProfilePage clickOnProfileBtn() {
        assertElementPresent(profileBtn);
        profileBtn.click();
        return new ProfilePage(getDriver());
    }

    public FavoritesPage clickOnFavoritesBtn() {
        assertElementPresent(favoritesBtn);
        favoritesBtn.click();
        return new FavoritesPage(getDriver());
    }
}
