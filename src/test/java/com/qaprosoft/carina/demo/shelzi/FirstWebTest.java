package com.qaprosoft.carina.demo.shelzi;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.demo.imarket.components.CatalogMenu;
import com.qaprosoft.carina.demo.imarket.components.HeaderMenu;
import com.qaprosoft.carina.demo.imarket.components.LoginItem;
import com.qaprosoft.carina.demo.imarket.pages.CardPage;
import com.qaprosoft.carina.demo.imarket.pages.CatalogThemeChoosePage;
import com.qaprosoft.carina.demo.imarket.pages.HomePage;
import com.qaprosoft.carina.demo.imarket.pages.LaptopResultSearchPage;
import com.qaprosoft.carina.demo.imarket.pages.ResultSearchPage;
import com.qaprosoft.carina.demo.imarket.user.User;
import com.qaprosoft.carina.demo.imarket.user.UserBuilder;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import com.zebrunner.carina.utils.R;
import org.apache.commons.collections.CollectionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class FirstWebTest implements IAbstractTest {
    HomePage homePage;

    @BeforeSuite
    public void startDriver() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\pushk\\Desktop\\110\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        homePage = new HomePage(driver);
        homePage.open();
    }

    @Test
    @MethodOwner(owner = "Shelzi")
    public void testOpenPage() {
        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened");
    }

    @Test(priority = 2)
    @MethodOwner(owner = "Shelzi")
    public void testSearch() {
        String searchQuery = "IPhone";
        HeaderMenu headerMenu = homePage.getHeaderMenu();
        Assert.assertTrue(headerMenu.isInputTextInSearchFieldPresent(2));
        ResultSearchPage resultSearchPage = headerMenu.showGoodsBySearchQuery(searchQuery);
        Assert.assertFalse(CollectionUtils.isEmpty(resultSearchPage.getSearchResultItemLinksList()), "Search result is empty");
    }

    @Test(priority = 2)
    @MethodOwner(owner = "Shelzi")
    public void testSpecificSearch() {
        String searchQuery = "Монитор AOC 16T2";
        HeaderMenu headerMenu = homePage.getHeaderMenu();
        Assert.assertTrue(headerMenu.isInputTextInSearchFieldPresent(2));
        ResultSearchPage resultSearchPage = headerMenu.showGoodsBySearchQuery(searchQuery);
        Assert.assertFalse(resultSearchPage.getSingleItemName().getText().isEmpty());
        Assert.assertTrue(resultSearchPage.getSingleItemName().getText()
                .equalsIgnoreCase(searchQuery), "Search result is empty");
    }

    @Test(priority = 2)
    @MethodOwner(owner = "Shelzi")
    public void testCatalogMenuSpecificSearch() {
        String theme = "Ноутбуки";
        String manufacturer = "ASUS";
        String cpu = "Intel Core i5 10300H";
        HeaderMenu headerMenu = homePage.getHeaderMenu();
        CatalogMenu catalogMenu = headerMenu.clickOnTopCatalogMenuButton();
        CatalogThemeChoosePage catalogThemeChoosePage = catalogMenu.clickToElectronicCatalogChoseButton();
        catalogThemeChoosePage.clickOnComputerTechThemeButton();
        LaptopResultSearchPage resultSearchPage = catalogThemeChoosePage.clickOnLaptopThemeButton();
        Assert.assertTrue(resultSearchPage.getH1ThemeTitle().getText().equalsIgnoreCase(theme));
        resultSearchPage.clickOnManufacturerCheckboxChose();
        resultSearchPage.clickOnCpuModelMenuButton();
        resultSearchPage.clickOnCpuChose();
        if (resultSearchPage.getListViewButton().isClickable() && !resultSearchPage.isListViewButtonActive()) {
            resultSearchPage.clickOnListViewButton();
        }
        pause(3);
        List<ExtendedWebElement> result = resultSearchPage.getResultSearchList();
        SoftAssert softAssert = new SoftAssert();
        for (ExtendedWebElement e : result) {
            softAssert.assertTrue(e.findExtendedWebElement(By.xpath(".//a[@class = 'item-title']"))
                    .getAttribute("title")
                    .contains(manufacturer));
            softAssert.assertTrue(e.findExtendedWebElement(By.xpath(".//div[contains(@class, 'row list-info-price')]//div//span[contains(text(), '" + cpu + "')]"))
                    .getText().equalsIgnoreCase(cpu));
        }
        softAssert.assertAll();
    }

    @Test(priority = 3)
    @MethodOwner(owner = "Shelzi")
    public void testAddToBucketSpecificGoods() {
        //testLogin();
        String searchQuery = "Смартфон Apple iPhone 11 64GB Black A2221 (MHDA3CN/A)";
        int selfCheckoutTownId = 5416;
        int selfCheckoutStreetId = 998796;
        HeaderMenu headerMenu = homePage.getHeaderMenu();
        ResultSearchPage resultSearchPage = headerMenu.showGoodsBySearchQuery(searchQuery);
        Assert.assertFalse(resultSearchPage.getSingleItemName().getText().isEmpty());
        resultSearchPage.clickOnAddToBasketButtonSingleItemPage();
        Assert.assertTrue(resultSearchPage.getModalWindowItemName().getText().equalsIgnoreCase(searchQuery));
        CardPage cardPage = resultSearchPage.clickOnGoToBasketButtonModal();
        Assert.assertTrue(cardPage.isNameInputPresent());
        Assert.assertTrue(cardPage.isPhoneInputPresent());
        cardPage.typeUserContactInfoWithoutEmail(UserBuilder.getValidUser());
        cardPage.clickOnSelfCheckoutButton();
        cardPage.clickOnChosenSelfCheckoutTown(selfCheckoutTownId);
        pause(2);
        cardPage.clickOnChosenSelfCheckoutStreet(selfCheckoutStreetId);
        cardPage.clickOnRandomDateToSelfCheckout();
        pause(2);
        cardPage.clickOnRandomTimeToSelfCheckout();
        cardPage.clickOnPaymentOnDeliveryButton();
        cardPage.clickOnPaymentByCashButton();
        //Fake order and(/or) payment method, that assert a success
    }

    @Test(priority = 1)
    @MethodOwner(owner = "Shelzi")
    public void testLogin() {
        User user = UserBuilder.getValidUser();
        HeaderMenu headerMenu = homePage.getHeaderMenu();
        LoginItem loginItem = headerMenu.clickOnLoginButton();
        pause(1);
        loginItem.clickOnEmailTab();
        Assert.assertTrue(loginItem.isLoginInputFieldPresent(2));
        Assert.assertTrue(loginItem.isPasswordInputFieldPresent(2));
        loginItem.inputUserCredentials(user);
        //loginItem.clickOnCaptcha();
        //pause(10);
        //loginItem.clickOnSubmitLoginButton();
        Assert.assertTrue(headerMenu.isLinkToPersonalPagePresent());
        Assert.assertTrue(headerMenu.getLinkToPersonalPage().getText().equalsIgnoreCase(user.getLogin()));
    }
}
