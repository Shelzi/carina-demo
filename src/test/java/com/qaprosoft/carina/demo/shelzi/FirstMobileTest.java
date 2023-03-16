package com.qaprosoft.carina.demo.shelzi;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.qaprosoft.carina.demo.allinstruents.android.CheckoutPage;
import com.qaprosoft.carina.demo.allinstruents.android.ComparisonPage;
import com.qaprosoft.carina.demo.allinstruents.android.FavoritesPage;
import com.qaprosoft.carina.demo.allinstruents.android.FooterComponent;
import com.qaprosoft.carina.demo.allinstruents.android.HomePage;
import com.qaprosoft.carina.demo.allinstruents.android.LoginPage;
import com.qaprosoft.carina.demo.allinstruents.android.ProductPage;
import com.qaprosoft.carina.demo.allinstruents.android.ProfilePage;
import com.qaprosoft.carina.demo.allinstruents.android.TownSelectPage;
import com.qaprosoft.carina.demo.allinstruents.user.User;
import com.qaprosoft.carina.demo.allinstruents.user.UserBuilder;
import com.zebrunner.carina.utils.mobile.IMobileUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FirstMobileTest implements IAbstractTest, IMobileUtils {

    @Test
    public void testSearch() {
        //WelcomePage welcomePage = new WelcomePage(getDriver());
        //Assert.assertTrue(welcomePage.isPageOpened());
        //Assert.assertTrue(welcomePage.isSkipBtnPresent());
        //TownSelectPage townSelectPage = welcomePage.clickSkipBtn();
        //TODO: кнопку есть, но он её не видит, пока я сам не коснусь экрана ¯\_(ツ)_/¯
        String searchRequest = "Makita";
        TownSelectPage townSelectPage = new TownSelectPage(getDriver());
        Assert.assertTrue(townSelectPage.isTownBtnPresent());
        HomePage homePage = townSelectPage.clickOnTownBtn();
        Assert.assertTrue(homePage.isAgreePopUpButtonPresent());
        homePage.clickOnAgreePopUpButton();
        Assert.assertTrue(homePage.isSearchInputButtonPresent());
        homePage.clickOnSearchInputButton();
        Assert.assertTrue(homePage.isSearchInputPresent());
        homePage.inputSearchRequest(searchRequest);
        Assert.assertTrue(homePage.getSearchResult().getText().contains(searchRequest));
    }

    @Test
    public void testLogin() {
        User user = UserBuilder.getValidUser();
        TownSelectPage townSelectPage = new TownSelectPage(getDriver());
        Assert.assertTrue(townSelectPage.isTownBtnPresent());
        HomePage homePage = townSelectPage.clickOnTownBtn();
        Assert.assertTrue(homePage.isAgreePopUpButtonPresent());
        homePage.clickOnAgreePopUpButton();
        Assert.assertTrue(homePage.isLoginButtonPresent());
        LoginPage loginPage = homePage.clickOnLoginButton();
        Assert.assertTrue(loginPage.isEmailInputPresent());
        Assert.assertTrue(loginPage.isPasswordInputPresent());
        loginPage.inputEmail(user.getLogin());
        loginPage.inputPassword(user.getPassword());
        loginPage.clickOnLoginBtn();
        FooterComponent footerComponent = new FooterComponent(getDriver());
        ProfilePage profilePage = footerComponent.clickOnProfileBtn();
        Assert.assertEquals(user.getName(), profilePage.getLoginNameLabel().getText().strip());
    }

    @Test
    public void testInstantBuy() {
        User user = UserBuilder.getValidUser();
        TownSelectPage townSelectPage = new TownSelectPage(getDriver());
        Assert.assertTrue(townSelectPage.isTownBtnPresent());
        HomePage homePage = townSelectPage.clickOnTownBtn();
        Assert.assertTrue(homePage.isAgreePopUpButtonPresent());
        homePage.clickOnAgreePopUpButton();
        int tileNumber = 1;
        ProductPage productPage = homePage.clickOnProductToBuyTile(tileNumber);
        CheckoutPage checkoutPage = productPage.clickOnInstantBuy();
        checkoutPage.clickOnCallMeRadioButton();
        Assert.assertTrue(checkoutPage.isNameInputPresent());
        Assert.assertTrue(checkoutPage.isPhoneInputPresent());
        checkoutPage.inputName(user.getName());
        checkoutPage.inputPhone(user.getPhone());
    }

    @Test
    public void testAddToFavorites() {
        TownSelectPage townSelectPage = new TownSelectPage(getDriver());
        Assert.assertTrue(townSelectPage.isTownBtnPresent());
        HomePage homePage = townSelectPage.clickOnTownBtn();
        Assert.assertTrue(homePage.isAgreePopUpButtonPresent());
        homePage.clickOnAgreePopUpButton();
        homePage.clickOnAddToFavoritesButton();
        String favoriteTitleName = homePage.getTitleNameText().getText();
        FooterComponent footerComponent = new FooterComponent(getDriver());
        FavoritesPage favoritesPage = footerComponent.clickOnFavoritesBtn();
        Assert.assertTrue(favoritesPage.isFavoriteProductNamePresent());
        Assert.assertEquals(favoritesPage.getFavoriteProductName().getText(), favoriteTitleName);
    }

    @Test
    public void testComparison() {
        TownSelectPage townSelectPage = new TownSelectPage(getDriver());
        Assert.assertTrue(townSelectPage.isTownBtnPresent());
        HomePage homePage = townSelectPage.clickOnTownBtn();
        Assert.assertTrue(homePage.isAgreePopUpButtonPresent());
        homePage.clickOnAgreePopUpButton();
        int titleNumber = 1;
        Assert.assertTrue(homePage.isProductToBuyTilePresent(titleNumber));
        ProductPage productPage = homePage.clickOnProductToBuyTile(titleNumber);
        productPage.clickOnAddToCompareButton();
        productPage.clickOnGoBackButton();
        titleNumber = 2;
        Assert.assertTrue(homePage.isProductToBuyTilePresent(titleNumber));
        productPage = homePage.clickOnProductToBuyTile(titleNumber);
        productPage.clickOnAddToCompareButton();
        productPage.clickOnGoBackButton();
        FooterComponent footerComponent = new FooterComponent(getDriver());
        ProfilePage profilePage = footerComponent.clickOnProfileBtn();
        ComparisonPage comparisonPage = profilePage.clickOnComparisonPageButton();
        Assert.assertTrue(comparisonPage.isShowOnlyDifferencesSliderPresent());
        comparisonPage.clickOnShowOnlyDifferencesSlider();
        Assert.assertTrue(comparisonPage.isComparisonContainerPresent());
    }
}
