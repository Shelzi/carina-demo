package com.qaprosoft.carina.demo.imarket.pages;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import com.qaprosoft.carina.demo.imarket.user.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;
import java.util.Optional;

public class CardPage extends AbstractPage {
    @FindBy(xpath = "//div[@id = 'block-contacts']//input[@data-item = 'FIO']")
    private ExtendedWebElement nameInput;
    @FindBy(xpath = "//div[@id = 'block-contacts']//input[@data-item = 'PHONE']")
    private ExtendedWebElement phoneInput;
    @FindBy(xpath = "//div[@id = 'block-contacts']//input[@data-item = 'EMAIL']")
    private ExtendedWebElement emailInput;
    @FindBy(xpath = "//div[contains(@class, 'self-delivery-location-block ')]")
    private ExtendedWebElement selfCheckoutTownListButton;
    @FindBy(xpath = "//div[@class = 'self-delivery-location']")
    private List<ExtendedWebElement> selfCheckoutLocationsList;
    @FindBy(xpath = "//div[@id = 'self-delivery-address']")
    private ExtendedWebElement selfCheckoutStreetListButton;
    @FindBy(xpath = "//div[contains(@class, 'our-pickup self-delivery-point-address')]")
    private List<ExtendedWebElement> selfCheckoutStreetsList;
    @FindBy(xpath = "//ul[@id='delivery-block']//a[@href = '#sam']")
    private ExtendedWebElement selfCheckoutButton;
    @FindBy(xpath = "//input[contains(@data-item, 'DELIVERY_DATE')]")
    private ExtendedWebElement selfCheckoutSupplyCalendarButton;
    @FindBy(xpath = "//input[contains(@data-item, 'DELIVERY_TIME')]")
    private ExtendedWebElement selfCheckoutSupplyTimeButton;
    @FindBy(xpath = "//div[contains(@class, 'w-day-num') and contains(@class, 'active')]")
    private List<ExtendedWebElement> availableDaysToSupplyList;
    @FindBy(xpath = "//div[@class = 'timer']")
    private List<ExtendedWebElement> availableTimeToSupplyList;
    @FindBy(xpath = "//a[@data-item='pri_poluchenii']")
    private ExtendedWebElement paymentOnDeliveryButton;
    @FindBy(xpath = "//label[@for = 'pri_poluchenii_payment7']")
    private ExtendedWebElement paymentByCashButton;

    public CardPage(WebDriver driver) {
        super(driver);
    }

    public void typeUserContactInfoWithoutEmail(User user) {
        clickOnNameInput();
        inputText(nameInput, user.getName());
        clickOnPhoneInput();
        inputText(phoneInput, user.getPhone());
    }

    private void clickOnPhoneInput() {
        assertElementPresent(phoneInput);
        phoneInput.click();
    }

    public void clickOnNameInput() {
        assertElementPresent(nameInput);
        nameInput.click();
    }

    public void clickOnSelfCheckoutButton() {
        assertElementPresent(selfCheckoutButton);
        selfCheckoutButton.click();
    }

    public void clickOnSelfCheckoutTownListButton() {
        assertElementPresent(selfCheckoutTownListButton);
        selfCheckoutTownListButton.click();
    }

    public void clickOnSelfCheckoutStreetListButton() {
        assertElementPresent(selfCheckoutStreetListButton);
        selfCheckoutStreetListButton.click();
    }

    private void clickOnSelfCheckoutSupplyCalendarButton() {
        assertElementPresent(selfCheckoutSupplyCalendarButton);
        selfCheckoutSupplyCalendarButton.click();
    }

    public void clickOnRandomDateToSelfCheckout() {
        clickOnSelfCheckoutSupplyCalendarButton();
        pause(2);
        Assert.assertFalse(availableDaysToSupplyList.isEmpty());
        availableDaysToSupplyList.get(0).click();
    }

    public void clickOnSelfCheckoutSupplyTimeButton() {
        assertElementPresent(selfCheckoutSupplyTimeButton);
        selfCheckoutSupplyTimeButton.click();
    }

    public void clickOnRandomTimeToSelfCheckout() {
        clickOnSelfCheckoutSupplyTimeButton();
        pause(2);
        Assert.assertFalse(availableTimeToSupplyList.isEmpty());
        availableTimeToSupplyList.get(0).click();
    }

    public void clickOnPaymentOnDeliveryButton() {
        assertElementPresent(paymentOnDeliveryButton);
        paymentOnDeliveryButton.click();
    }

    public void clickOnPaymentByCashButton() {
        assertElementPresent(paymentByCashButton);
        paymentByCashButton.click();
    }

    public void clickOnChosenSelfCheckoutTown(int id) {
        clickOnSelfCheckoutTownListButton();
        Assert.assertFalse(selfCheckoutLocationsList.isEmpty());
        Optional<ExtendedWebElement> optional = selfCheckoutLocationsList.stream()
                .filter(l -> l.getAttribute("data-id").equalsIgnoreCase(String.valueOf(id)))
                .findFirst();
        if (optional.isPresent()) {
            assertElementPresent(optional.get());
            optional.get().click();
        } else {
            Assert.assertNotNull(optional.get());
        }
    }


    public void clickOnChosenSelfCheckoutStreet(int id) {
        clickOnSelfCheckoutStreetListButton();
        Assert.assertFalse(selfCheckoutStreetsList.isEmpty());
        Optional<ExtendedWebElement> optional = selfCheckoutStreetsList.stream()
                .filter(l -> l.getAttribute("data-id").equalsIgnoreCase(String.valueOf(id)))
                .findFirst();
        if (optional.isPresent()) {
            assertElementPresent(optional.get());
            optional.get().click();
        } else {
            Assert.assertNotNull(optional.get());
        }
    }

    private void inputText(ExtendedWebElement extendedWebElement, String text) {
        extendedWebElement.type(text);
    }
}
