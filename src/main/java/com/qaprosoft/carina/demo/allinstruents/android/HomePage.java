package com.qaprosoft.carina.demo.allinstruents.android;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import lombok.Getter;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

@Getter
public class HomePage extends AbstractPage {

    @FindBy(id = "com.notissimus.allinstruments.android:id/buttonNewBlockPreviewAction")
    private ExtendedWebElement agreePopUpButton;

    @FindBy(xpath = "//android.widget.LinearLayout[@resource-id = 'com.notissimus.allinstruments.android:id/layoutSearch']")
    private ExtendedWebElement searchInputButton;

    @FindBy(xpath = "//android.widget.EditText[@resource-id = 'com.notissimus.allinstruments.android:id/editTextSearch']")
    private ExtendedWebElement searchInput;

    @FindBy(xpath = "//android.widget.TextView[@text = 'Аккумуляторы для Makita']")
    private ExtendedWebElement searchResult;

    @FindBy(xpath = "//android.widget.TextView[@text = 'ВОЙТИ']")
    private ExtendedWebElement loginButton;

    @FindBy(xpath = "//androidx.recyclerview.widget.RecyclerView[@resource-id = 'com.notissimus.allinstruments.android:id/recyclerViewSpecialProducts' and @scrollable = 'true']//android.widget.FrameLayout[@clickable = 'true'][%d]")
    private ExtendedWebElement productToBuyTile;

    @FindBy(xpath = "//androidx.recyclerview.widget.RecyclerView[@resource-id = 'com.notissimus.allinstruments.android:id/recyclerViewSpecialProducts' and @scrollable = 'true']//android.widget.FrameLayout[@clickable = 'true'][1]//android.widget.ImageView[@resource-id = 'com.notissimus.allinstruments.android:id/imageViewFavoriteUnselected']")
    private ExtendedWebElement addToFavoritesButton;

    @FindBy(xpath = "//androidx.recyclerview.widget.RecyclerView[@resource-id = 'com.notissimus.allinstruments.android:id/recyclerViewSpecialProducts' and @scrollable = 'true']//android.widget.FrameLayout[@clickable = 'true'][1]//android.widget.TextView[@resource-id = 'com.notissimus.allinstruments.android:id/textViewName']")
    private ExtendedWebElement titleNameText;
    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isAgreePopUpButtonPresent() {
        productToBuyTile.format();
        return agreePopUpButton.isElementPresent();
    }

    public void clickOnAgreePopUpButton() {
        agreePopUpButton.click();
    }

    public boolean isSearchInputButtonPresent() {
        return searchInputButton.isElementPresent();
    }

    public void clickOnSearchInputButton() {
        searchInputButton.click();
    }

    public boolean isSearchInputPresent() {
        return searchInput.isPresent();
    }

    public void inputSearchRequest(String r) {
        inputText(searchInput, r);
        checkInput(searchInput, r);
    }

    public boolean isLoginButtonPresent() {
        return loginButton.isElementPresent();
    }

    public LoginPage clickOnLoginButton() {
        loginButton.click();
        return new LoginPage(getDriver());
    }

    public boolean isProductToBuyTilePresent(int tileNumber) {
        return productToBuyTile.format(tileNumber).isPresent();
    }

    public ProductPage clickOnProductToBuyTile(int tileNumber) {
        ExtendedWebElement productToBuyTileFormatted = productToBuyTile.format(tileNumber);
        assertElementPresent(productToBuyTileFormatted);
        productToBuyTileFormatted.click();
        return new ProductPage(getDriver());
    }

    public void clickOnAddToFavoritesButton() {
        assertElementPresent(addToFavoritesButton);
        addToFavoritesButton.click();
    }

    protected void inputText(ExtendedWebElement extendedWebElement, String text) {
        extendedWebElement.type(text);
    }

    private void checkInput(ExtendedWebElement extendedWebElement, String s) {
        Assert.assertTrue(extendedWebElement.getElement().getText().equalsIgnoreCase(s));
    }
}
