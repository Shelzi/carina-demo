package com.qaprosoft.carina.demo.allinstruents.android;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@Getter
public class FavoritesPage extends AbstractPage {
    @FindBy(id = "com.notissimus.allinstruments.android:id/textViewProduct")
    private ExtendedWebElement favoriteProductName;

    public FavoritesPage(WebDriver driver) {
        super(driver);
    }

    public boolean isFavoriteProductNamePresent() {
        return favoriteProductName.isPresent();
    }
}
