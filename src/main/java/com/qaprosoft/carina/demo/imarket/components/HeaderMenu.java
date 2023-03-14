package com.qaprosoft.carina.demo.imarket.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import com.qaprosoft.carina.demo.imarket.pages.ResultSearchPage;
import lombok.Getter;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

@Getter
public class HeaderMenu extends AbstractUIObject {
    @FindBy(xpath = "//input[@id='search_input']")
    private ExtendedWebElement inputSearchField;

    @FindBy(id = "main-search-submit")
    private ExtendedWebElement searchButton;

    @FindBy(xpath = "//div[@class = 'col-md-5 col-sm-3 col-xs-3 topmenu-right']//a")
    private ExtendedWebElement loginButton;

    @FindBy(xpath = "//div[@class = 'top-menu']//a[@href='/personal/']")
    private ExtendedWebElement linkToPersonalPage;

    @FindBy(id = "topMenuToggle")
    private ExtendedWebElement topCatalogMenuButton;

    public HeaderMenu(WebDriver driver) {
        super(driver);
    }

    public HeaderMenu(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public void clickOnSearchField() {
        assertElementPresent(inputSearchField);
        inputSearchField.click();
    }

    public boolean isLinkToPersonalPagePresent() {
        return linkToPersonalPage.isPresent();
    }

    public LoginItem clickOnLoginButton() {
        assertElementPresent(loginButton);
        loginButton.click();
        return new LoginItem(driver);
    }

    public CatalogMenu clickOnTopCatalogMenuButton() {
        assertElementPresent(topCatalogMenuButton);
        topCatalogMenuButton.click();
        return new CatalogMenu(driver);
    }

    public void inputTextInSearchField(String text) {
        assertElementPresent(inputSearchField);
        inputSearchField.type(text);
    }

    public boolean isInputTextInSearchFieldPresent(long timeout) {
        return inputSearchField.isPresent(timeout);
    }


    public ResultSearchPage clickOnSearchButton() {
        assertElementPresent(searchButton);
        searchButton.click();
        return new ResultSearchPage(driver);
    }

    public ResultSearchPage showGoodsBySearchQuery(String query) {
        clickOnSearchField();
        inputTextInSearchField(query);
        checkInput(inputSearchField, query);
        return clickOnSearchButton();
    }

    public ExtendedWebElement getLinkToPersonalPage() {
        waitUntil(ExpectedConditions.visibilityOf(linkToPersonalPage.getElement()), EXPLICIT_TIMEOUT);
        return linkToPersonalPage;
    }

    private void checkInput(ExtendedWebElement extendedWebElement, String s) {
        Assert.assertTrue(extendedWebElement.getElement().getAttribute("value").equalsIgnoreCase(s));
    }
}
