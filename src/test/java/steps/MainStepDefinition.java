package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import objects.SampleHomePage;
import org.apache.log4j.Logger;
import org.junit.Assert;

public class MainStepDefinition {

    public SampleHomePage homePage = Hooks.homePage;
    public Logger logger = Logger.getLogger(MainStepDefinition.class);

    @Given("The user is in Google homepage")
    public void navigateToHomePage() {
        try {
            homePage.navigateToHomePage();
            logger.info("Navigated to homepage");
        } catch (Exception e) {
            logger.error("Navigation to homepage failed: " + e);
            Assert.fail("ERROR: Navigation to homepage failed: " + e);
        }
    }

    @Then("The logo should be displayed")
    public void theLogoShouldBeDisplayed() {
        try {
            homePage.isLogoDisplayed();
            logger.info("Logo is not displayed");
        } catch (Exception e) {
            logger.error("Logo is not displayed: " + e);
            Assert.fail("ERROR: Logo is not displayed: " + e);
        }
    }
}
