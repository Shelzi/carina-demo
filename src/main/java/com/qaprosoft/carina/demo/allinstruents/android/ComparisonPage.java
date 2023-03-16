package com.qaprosoft.carina.demo.allinstruents.android;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class ComparisonPage extends AbstractPage {
    @FindBy(id = "com.notissimus.allinstruments.android:id/switchShowDifference")
    private ExtendedWebElement showOnlyDifferencesSlider;

    @FindBy(id = "com.notissimus.allinstruments.android:id/containerComparison")
    private ExtendedWebElement comparisonContainer;

    public ComparisonPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnShowOnlyDifferencesSlider() {
        assertElementPresent(showOnlyDifferencesSlider);
        showOnlyDifferencesSlider.click();
    }

    public boolean isComparisonContainerPresent() {
        return comparisonContainer.isPresent();
    }

    public boolean isShowOnlyDifferencesSliderPresent() {
        return showOnlyDifferencesSlider.isPresent();
    }
}
