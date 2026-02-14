package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * BasePage ist die zentrale Klasse, die alle Pages erben.
 * Sie kapselt:
 * - Warten auf Elemente
 * - Klicken, Tippen, Text holen
 * - Sichtbarkeit & Klickbarkeit
 */
public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    // Timeout für alle expliziten Waits
    private final int TIMEOUT = 10;

    public BasePage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
    }

    /**
     * Wartet, bis das Element sichtbar ist, und gibt es zurück
     */
    protected WebElement waitForVisible(By locator){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Wartet, bis das Element klickbar ist, und gibt es zurück
     */
    protected WebElement waitForClickable(By locator){
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Klickt auf ein Element, nachdem es klickbar ist
     */
    protected void click(By locator){
        waitForClickable(locator).click();
    }

    /**
     * Tippt Text in ein Eingabefeld, nachdem es sichtbar ist
     */
    protected void type(By locator, String text){
        WebElement element = waitForVisible(locator);
        element.clear(); // vorher löschen
        element.sendKeys(text);
    }

    /**
     * Gibt den Text eines sichtbaren Elements zurück
     */
    protected String getText(By locator){
        return waitForVisible(locator).getText();
    }

    /**
     * Prüft, ob die aktuelle URL ein bestimmtes Muster enthält
     * Praktisch für Seitenprüfung ohne direkten driver-Aufruf in Tests
     */
    protected boolean isUrlContains(String partialUrl){
        return driver.getCurrentUrl().contains(partialUrl);
    }
}
