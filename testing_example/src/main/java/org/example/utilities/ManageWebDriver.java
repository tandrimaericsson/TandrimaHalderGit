package org.example.utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class ManageWebDriver {

    private WebDriver driver;
    private Properties configProperties;

    public void initializeDriver() {
        configProperties = PropertiesFileReader.readPropertiesFile(ResourcePaths.configFilePath);
        String browserName = configProperties.getProperty("browser");
        switch (browserName) {
            case "chrome" -> {
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("start-maximized");
                chromeOptions.addArguments("--disable-infobars");
                chromeOptions.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(chromeOptions);
            }
            case "firefox" -> {
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
            }
            default -> System.out.println("Invalid browser name : ".concat(browserName));
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void openUrl(String url) {
        System.out.println(configProperties.getProperty(url));
        driver.get(configProperties.getProperty(url));
    }

    public void quit() {
        getDriver().quit();
    }

    public WebElement waitForElement(By elem, WebElement parent) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
            wait.until(driver ->
                    parent==null ? ExpectedConditions.visibilityOfElementLocated(elem):ExpectedConditions.presenceOfNestedElementLocatedBy(parent, elem)
            );
            return parent==null ? getDriver().findElement(elem):parent.findElement(elem);
        } catch (Exception ex) {
            System.out.println("Exception occurred while getting element : ".concat(ex.toString()));
            return null;
        }
    }

    public List<WebElement> waitForElements(By elem, WebElement parent) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
        wait.until(driver ->
                parent==null ? ExpectedConditions.visibilityOfAllElementsLocatedBy(elem):ExpectedConditions.visibilityOfNestedElementsLocatedBy(parent, elem)
        );
        return parent==null ? getDriver().findElements(elem):parent.findElements(elem);
    }

    public void click(WebElement element) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public void click(By element, WebElement parent) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(waitForElement(element, parent))).click();
    }

    public void waitForPageTitleToLoad(String expectedTitle) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
        wait.until(ExpectedConditions.titleIs(expectedTitle));
    }

    public byte[] takeScreenshot() {
        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
