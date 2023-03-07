package com.qaprosoft.carina.demo.imarket.pages;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class CatalogThemeChoosePage extends AbstractPage {
    @FindBy(xpath = "//a[@href = '/catalog/komp-tehnika/']")
    private ExtendedWebElement computerTechThemeButton;

    @FindBy(xpath = "//a[@href = '/catalog/noutbuki/']//div[@class = 'section-name']")
    private ExtendedWebElement laptopThemeButton;

    public CatalogThemeChoosePage(WebDriver driver) {
        super(driver);
    }

    public void clickOnComputerTechThemeButton() {
        assertElementPresent(computerTechThemeButton);
        computerTechThemeButton.click();
    }

    public LaptopResultSearchPage clickOnLaptopThemeButton() {
        assertElementPresent(laptopThemeButton);
        laptopThemeButton.click();
        return new LaptopResultSearchPage(driver);
    }
}
