package wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UIWrapper {
    private static WebDriver driver;
    private static WebDriverWait wait;

    public UIWrapper(String browser, int timeout) {
        switch (browser) {
            default:
                //Change path to your chromedriver
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
                driver = new ChromeDriver();
        }
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, timeout);
    }

    public void closeBrowser() {
        driver.quit();
    }

    public void goToLink(String link) {
        driver.get(link);
    }

    public void clickOnByXpath(String xpath) {
        driver.findElement(By.xpath(xpath)).click();
    }
    public void clickOn(By element) {
        driver.findElement(element).click();
    }

    public void sendKeys(By element, String text) {
        driver.findElement(element).sendKeys(text);
    }

    public void checkDisplay(By element) {
        driver.findElement(element).isDisplayed();
    }

    public void checkSelected(By element) {
        driver.findElement(element).isSelected();
    }

    public void waitVisibility(By element) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public void waitInvisibility(By element) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
    }

    public String getText(By element) {
        return driver.findElement(element).getText();
    }

    public double getDouble(By element) {
        return Double.parseDouble(driver.findElement(element).getText());
    }
}
