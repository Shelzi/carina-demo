package com.qaprosoft.carina.demo.imarket.pages;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import com.qaprosoft.carina.demo.imarket.components.CatalogMenu;
import com.qaprosoft.carina.demo.imarket.components.HeaderMenu;
import com.zebrunner.carina.utils.Configuration;
import com.zebrunner.carina.utils.R;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@Getter
public class HomePage extends AbstractPage {
    @FindBy(className = "table-cell")
    private ExtendedWebElement tableCells;

    @FindBy(xpath = "//div[contains(@class, 'header header-top')]")
    private HeaderMenu headerMenu;

    public HomePage(WebDriver driver) {
        super(driver);
        setUiLoadedMarker(tableCells);
        setPageAbsoluteURL(R.CONFIG.get(Configuration.Parameter.URL.getKey()));
    }
}
