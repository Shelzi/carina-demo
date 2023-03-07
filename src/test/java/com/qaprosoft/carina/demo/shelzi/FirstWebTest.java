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

import java.time.Duration;
import java.util.List;
import java.util.Optional;

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
        ResultSearchPage resultSearchPage = headerMenu.showGoodsBySearchQuery(searchQuery);
        Assert.assertFalse(CollectionUtils.isEmpty(resultSearchPage.getSearchResultItemLinksList()), "Search result is empty");
    }

    @Test(priority = 2)
    @MethodOwner(owner = "Shelzi")
    public void testSpecificSearch() {
        String searchQuery = "Монитор AOC 16T2";
        HeaderMenu headerMenu = homePage.getHeaderMenu();
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
        //resultSearchPage.clickOnListViewButton();
        List<ExtendedWebElement> result = resultSearchPage.getSearchResultItemLinksList();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(result.get(0).findExtendedWebElement(By.xpath(".//a[@class = 'item-title']"))
                .getAttribute("title")
                .contains(manufacturer));
        softAssert.assertTrue(result.get(0).findExtendedWebElement(By.xpath(".//div[@class = 'row list-info-price']//span[2]"))
                .getText().contains(cpu));

        /*Optional<ExtendedWebElement> optional = result.stream()
                .filter(r -> (r.findExtendedWebElement(By.xpath("//a[@class = 'item-title']"))
                                      .getAttribute("title")
                                      .contains(manufacturer)
                              &&
                              r.findExtendedWebElement(By.xpath("//div[@class = 'row list-info-price']//span[2]"))
                                      .getText().contains(cpu)))
                .findAny();
        Assert.assertTrue(optional.isPresent());*/
    }

    @Test(priority = 3)
    @MethodOwner(owner = "Shelzi")
    public void testAddToBucketSpecificGoods() {
        String searchQuery = "Смартфон Apple iPhone 11 64GB Black A2221 (MHDA3CN/A)";
        //String SelfCheckoutTown = R.TESTDATA.get("imarket_card_selfcheckouttown");
        int selfCheckoutTownId = 5416;
        int selfCheckoutStreetId = 998796;
        HeaderMenu headerMenu = homePage.getHeaderMenu();
        ResultSearchPage resultSearchPage = headerMenu.showGoodsBySearchQuery(searchQuery);
        Assert.assertFalse(resultSearchPage.getSingleItemName().getText().isEmpty());
        resultSearchPage.clickOnAddToBasketButtonSingleItemPage();
        Assert.assertTrue(resultSearchPage.getModalWindowItemName().getText().equalsIgnoreCase(searchQuery));
        CardPage cardPage = resultSearchPage.clickOnGoToBasketButtonModal();
        cardPage.typeUserContactInfoWithoutEmail(UserBuilder.getValidUser());
        pause(1);
        cardPage.clickOnSelfCheckoutButton();
        pause(1);
        cardPage.clickOnChosenSelfCheckoutTown(selfCheckoutTownId);
        pause(1);
        cardPage.clickOnChosenSelfCheckoutStreet(selfCheckoutStreetId);
        pause(1);
        cardPage.clickOnRandomDateToSelfCheckout();
        pause(1);
        cardPage.clickOnRandomTimeToSelfCheckout();
        pause(1);
        cardPage.clickOnPaymentOnDeliveryButton();
        pause(1);
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
        pause(1);
        loginItem.inputEmail(user.getLogin());
        loginItem.inputPassword(user.getPassword());
        //loginItem.clickOnCaptcha();
        pause(10);
        //loginItem.clickOnSubmitLoginButton();
        Assert.assertTrue(headerMenu.getLinkToPersonalPage().getText().equalsIgnoreCase(user.getLogin()));
    }
}
