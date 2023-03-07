package com.qaprosoft.carina.demo.imarket.pages;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Getter
public class ResultSearchPage extends AbstractPage {
    @FindBy(xpath = "//div[contains(@class, 'goods-item')]")
    private List<ExtendedWebElement> searchResultItemLinksList;

    @FindBy(xpath = "//div[@id = 'item-cart']//h1")
    private ExtendedWebElement singleItemName;

    @FindBy(xpath = "//a[contains(@class, 'btn btn-red-new to-basket ')]")
    private ExtendedWebElement addToBasketButtonSingleItemPage;

    @FindBy(xpath = "//div[@id = 'modal']//a[contains(@href, '/personal/cart/')]")
    private ExtendedWebElement goToBasketButtonModal;


    @FindBy(xpath = "//div[@id = 'popupBasket']//a[@class = 'basket-title']")
    private ExtendedWebElement modalWindowItemName;

    @FindBy(xpath = "//table//h1")
    private ExtendedWebElement h1ThemeTitle;

    @FindBy(xpath = "//li[@data-view='list']")
    private ExtendedWebElement listViewButton;

    public ResultSearchPage(WebDriver driver) {
        super(driver);
    }

    public ExtendedWebElement getSingleItemName() {
        assertElementPresent(singleItemName);
        return singleItemName;
    }

    public void clickOnListViewButton() {
        assertElementPresent(listViewButton);
        if (listViewButton.isClickable()){
            listViewButton.click();
        }
    }

    public void clickOnAddToBasketButtonSingleItemPage() {
        assertElementPresent(addToBasketButtonSingleItemPage);
        addToBasketButtonSingleItemPage.click();
    }

    public CardPage clickOnGoToBasketButtonModal() {
        assertElementPresent(goToBasketButtonModal);
        goToBasketButtonModal.click();
        return new CardPage(driver);
    }
}
