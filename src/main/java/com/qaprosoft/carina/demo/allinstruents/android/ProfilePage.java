package com.qaprosoft.carina.demo.allinstruents.android;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@Getter
public class ProfilePage extends AbstractPage {

    @FindBy(id = "com.notissimus.allinstruments.android:id/textViewUserName")
    private ExtendedWebElement loginNameLabel;

    @FindBy(xpath = "//androidx.recyclerview.widget.RecyclerView[@resource-id =" +
                    " 'com.notissimus.allinstruments.android:id/recyclerViewMoreMenu']//android.view.ViewGroup[7]")
    private ExtendedWebElement comparisonButton;

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public ComparisonPage clickOnComparisonPageButton() {
        assertElementPresent(comparisonButton);
        comparisonButton.click();
        return new ComparisonPage(getDriver());
    }
}
