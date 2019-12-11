package configuration;


import io.cucumber.core.api.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import steps.Hooks;
import steps.BasePage;

import java.net.URL;
import java.util.Objects;

public class Driver extends BasePage {

    private static final Logger logger = Logger.getLogger(Driver.class);

    private static final String browser = Hooks.setValue("browser");

    public Driver() {

    }

    public static RemoteWebDriver getDriver() {
        try {
            setEnvironmentVariables();
            switch (browser.toLowerCase()) {
                case "firefox":
                    return new FirefoxDriver(firefoxOptions());
                case "ie":
                    return new InternetExplorerDriver(ieOptions());
                case "edge":
                    return new EdgeDriver(edgeOptions());
                case "safari":
                    return new SafariDriver(safariOptions());
                case "opera":
                    return new OperaDriver(operaOptions());
                default:
                    return new ChromeDriver(chromeOptions());
            }
        } catch (Throwable err) {
            logger.fatal("Driver is not initialized" + ": " + err);
            Assert.fail("ERROR : " + "Driver is not initialized" + err);
        }
        return null;
    }

    public static RemoteWebDriver getSauceLabsDriver(Scenario scenario) {
        try {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("platform", Hooks.setValue("sauce_os"));
            caps.setCapability("browserName", Hooks.setValue("sauce_browser"));
            caps.setCapability("version", Hooks.setValue("sauce_browser_version"));
            caps.setCapability("name", scenario.getName());
            return new RemoteWebDriver(new URL(Objects.requireNonNull(setURL())), caps);
        } catch (Throwable err) {
            logger.fatal("Sauce driver is not initialized" + ": " + err);
            Assert.fail("ERROR : " + "Sauce driver is not initialized" + err);
        }
        return null;
    }

    public static RemoteWebDriver getBrowserStackDriver(Scenario scenario) {
        try {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("browserstack.local", Hooks.setValue("bs_local_testing"));
            caps.setCapability("browser", Hooks.setValue("bs_browser"));
            caps.setCapability("browser_version", Hooks.setValue("bs_browser_version"));
            caps.setCapability("os", Hooks.setValue("bs_os"));
            caps.setCapability("os_version", Hooks.setValue("bs_os_version"));
            caps.setCapability("browserstack.selenium_version", Hooks.setValue("bs_selenium_version"));
            caps.setCapability("name", scenario.getName());
            return new RemoteWebDriver(new URL(Objects.requireNonNull(setURL())), caps);
        } catch (Throwable err) {
            logger.fatal("Browserstack driver is not initialized " + ": " + err);
            Assert.fail("ERROR : " + "Browserstack driver is not initialized " + err);
        }
        return null;
    }

    private static String setURL() {
        switch (Hooks.setValue("end_point").toLowerCase().trim()) {
            case "saucelabs":
                return "http://" + Hooks.readConfig("sauce_username") + ":" + Hooks.readConfig("sauce_accesskey")
                        + "@ondemand.eu-central-1.saucelabs.com/wd/hub";
            case "browserstack":
                return "https://" + Hooks.readConfig("browserstack_username") + ":" + Hooks.readConfig(
                        "browserstack_accesskey") + "@hub-cloud.browserstack.com/wd/hub";
        }
        return null;
    }

    private static void setEnvironmentVariables() {
        String endpoint = new Hooks().endpoint.toLowerCase().trim();
        System.setProperty("BrowserWidth", Hooks.readConfig(endpoint + "_browser_width"));
        System.setProperty("BrowserHeight", Hooks.readConfig(endpoint + "_browser_height"));
    }

    private static FirefoxOptions firefoxOptions() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions ffOptions = new FirefoxOptions();
        ffOptions.setCapability("marionette", true);
        ffOptions.addArguments("--width=" + Integer.parseInt(System.getProperty("BrowserWidth")));
        ffOptions.addArguments("--height=" + Integer.parseInt(System.getProperty("BrowserHeight")));
        return ffOptions;
    }

    private static ChromeOptions chromeOptions() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments(
                "window-size=" + Integer.parseInt(System.getProperty("BrowserWidth")) + "," + Integer
                        .parseInt(System.getProperty("BrowserHeight")));
        return chromeOptions;
    }

    private static InternetExplorerOptions ieOptions() {
        WebDriverManager.iedriver().setup();
        InternetExplorerOptions ieOptions = new InternetExplorerOptions();
        ieOptions.ignoreZoomSettings();
        ieOptions.requireWindowFocus();
        return ieOptions;
    }

    private static EdgeOptions edgeOptions() {
        WebDriverManager.edgedriver().setup();
        EdgeOptions edgeOptions = new EdgeOptions();
        return edgeOptions;
    }

    private static SafariOptions safariOptions() {
        SafariOptions safariOptions = new SafariOptions();
        return safariOptions;
    }

    private static OperaOptions operaOptions() {
        WebDriverManager.operadriver().setup();
        OperaOptions operaOptions = new OperaOptions();
        return operaOptions;
    }
}