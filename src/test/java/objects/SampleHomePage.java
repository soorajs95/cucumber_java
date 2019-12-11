package objects;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import steps.BasePage;
import steps.Hooks;

public class SampleHomePage extends BasePage {

    @FindBy(id = "hplogo")
    WebElement logo;

    public SampleHomePage(RemoteWebDriver driver) {
        BasePage.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void navigateToHomePage() {
        driver.get(Hooks.readConfig("BASE_URL"));
    }

    public void isLogoDisplayed() {
        Assert.assertTrue(logo.isDisplayed());
    }
}
