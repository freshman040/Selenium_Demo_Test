package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    protected void click(By locator){
        wait.until(driver -> driver.findElement(locator).isDisplayed());
        driver.findElement(locator).click();
    }

    protected void type(By locator, String text){
        wait.until(driver -> driver.findElement(locator).isDisplayed());
        driver.findElement(locator).sendKeys(text);
    }

    protected String getText(By locator){
        wait.until(driver -> driver.findElement(locator).isDisplayed());
        return driver.findElement(locator).getText();
    }

}
