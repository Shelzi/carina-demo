package com.qaprosoft.carina.demo.imarket.pages;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class LaptopResultSearchPage extends ResultSearchPage {
    @FindBy(xpath = "//label[@for = 'filter-checkbox422']")
    private ExtendedWebElement manufacturerCheckboxChose;

    @FindBy(xpath = "//p[@blockid='9338' and contains(text(), 'Модель процессора')]")
    private ExtendedWebElement cpuModelMenuButton;

    @FindBy(xpath = "//label[@for = 'filter-checkbox879643']")
    private ExtendedWebElement cpuChose;

    @FindBy(xpath = "//div[contains(@class, 'goods-item catalog-club-item')]")
    private List<ExtendedWebElement> resultSearchList;

    @FindBy(xpath = "//div[@data-id = '916']//div[@class = 'goods-item catalog-club-item'][1]")
    private ExtendedWebElement resultSearchListFirst;

    public LaptopResultSearchPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnManufacturerCheckboxChose() {
        assertElementPresent(manufacturerCheckboxChose);
        waitUntil(ExpectedConditions.visibilityOf(manufacturerCheckboxChose.getElement()), EXPLICIT_TIMEOUT);
        pause(5);
        waitUntil(ExpectedConditions.elementToBeClickable(manufacturerCheckboxChose.getElement()), EXPLICIT_TIMEOUT);
        manufacturerCheckboxChose.click();
    }

    public void clickOnCpuModelMenuButton() {
        assertElementPresent(cpuModelMenuButton);
        waitUntil(ExpectedConditions.visibilityOf(cpuModelMenuButton.getElement()), EXPLICIT_TIMEOUT);
        pause(5);
        waitUntil(ExpectedConditions.elementToBeClickable(cpuModelMenuButton.getElement()), EXPLICIT_TIMEOUT);
        cpuModelMenuButton.click();
    }

    public void clickOnCpuChose() {
        assertElementPresent(cpuChose);
        cpuChose.click();
    }

    public List<ExtendedWebElement>
    getResultSearchList() {
        assertElementPresent(resultSearchListFirst);
        return resultSearchList;
    }
}
