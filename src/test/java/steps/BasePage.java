package steps;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class BasePage {

    public static WebDriver driver;

    public BasePage() {

    }

    public BasePage(WebDriver driver) {
        BasePage.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void userWaitsForSecond(int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, seconds);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.id("justForWait")));
        } catch (TimeoutException | NoSuchElementException ignored) {

        }
    }

    public void userClicksOnID(String ID) {
        WebElement locateID = driver.findElement(By.id(ID));
        waitUntilElementIsVisible(locateID);
        locateID.click();
    }

    protected void waitUntilElementIsVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 40);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitUntilElementDisappear(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 40);
        wait.until(ExpectedConditions.invisibilityOf(element));

    }

    public void userEntersText(String text) {
        WebElement inputField = driver.switchTo().activeElement();
        inputField.sendKeys(text);
    }

    public void switchToStoryIframe() {
        driver.switchTo().defaultContent();
        driver.switchTo().frame("iframe");
    }

    public void getscreenshot() throws Exception {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        int random = (int) (Math.random() * 50 + 1);
        FileUtils.copyFile(scrFile, new File("target/screenshotPage" + random + ".png"));
    }

    public void userClicksOnLinkTitleWitAction(String title) {
        WebElement locateTitle = driver.findElement(By.cssSelector("a[title='" + title + "']"));
        waitUntilElementIsVisible(locateTitle);
        Actions actions = new Actions(driver);
        actions.moveToElement(locateTitle).click().build().perform();
        userWaitsForSecond(2);
    }

    protected void userClicksOnWebElementWithAction(WebElement element) {
        waitUntilElementIsVisible(element);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().build().perform();
        userWaitsForSecond(2);
    }
}