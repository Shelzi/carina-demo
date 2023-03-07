package com.qaprosoft.carina.demo.imarket.pages;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class LaptopResultSearchPage extends ResultSearchPage {
    @FindBy(xpath = "//label[@for = 'filter-checkbox422']")
    private ExtendedWebElement manufacturerCheckboxChose;

    @FindBy(xpath = "//p[@blockid='9338' and contains(text(), 'Модель процессора')]")
    private ExtendedWebElement cpuModelMenuButton;

    @FindBy(xpath = "//label[@for = 'filter-checkbox879643']")
    private ExtendedWebElement cpuChose;

    @FindBy(className = "goods-item catalog-club-item")
    private List<ExtendedWebElement> resultSearchList;

    public LaptopResultSearchPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnManufacturerCheckboxChose() {
        assertElementPresent(manufacturerCheckboxChose);
        manufacturerCheckboxChose.click();
    }

    public void clickOnCpuModelMenuButton() {
        assertElementPresent(cpuModelMenuButton);
        cpuModelMenuButton.click();
    }

    public void clickOnCpuChose() {
        assertElementPresent(cpuChose);
        cpuChose.click();
    }
}
