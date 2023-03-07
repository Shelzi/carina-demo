package com.qaprosoft.carina.demo.imarket.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import com.qaprosoft.carina.demo.imarket.pages.CatalogThemeChoosePage;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class CatalogMenu extends AbstractUIObject {
    @FindBy(xpath = "//a[@href = '/catalog/elektronika/' and @class='title']")
    private ExtendedWebElement electronicCatalogChoseButton;
    public CatalogMenu(WebDriver driver) {
        super(driver);
    }

    public CatalogMenu(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public CatalogThemeChoosePage clickToElectronicCatalogChoseButton() {
        assertElementPresent(electronicCatalogChoseButton);
        electronicCatalogChoseButton.click();
        return new CatalogThemeChoosePage(driver);
    }
}
