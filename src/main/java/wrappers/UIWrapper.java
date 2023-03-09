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

    public void sendKeysById(String id, String text) {
        driver.findElement(By.id(id)).sendKeys(text);
    }

    public void checkDisplayById(String id) {
        driver.findElement(By.id(id)).isDisplayed();
    }

    public void checkDisplayByXpath(String xpath) {
        driver.findElement(By.xpath(xpath)).isDisplayed();
    }

    public void checkSelectedById(String id) {
        driver.findElement(By.id(id)).isSelected();
    }

    public void waitVisibilityByid(String id) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
    }

    public void waitInvisibilityByid(String id) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(id)));
    }

    public String getTextByXpath(String xpath) {
        return driver.findElement(By.xpath(xpath)).getText();
    }

    public String getTextById(String id) {
        return driver.findElement(By.id(id)).getText();
    }

    public double getDoubleById(String id) {
        return Double.parseDouble(driver.findElement(By.id(id)).getText());
    }
}
