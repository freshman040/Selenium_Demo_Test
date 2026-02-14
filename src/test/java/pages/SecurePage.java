package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * PageObject für die Secure-Seite nach erfolgreichem Login
 */
public class SecurePage extends BasePage {

    private By logoutButton = By.cssSelector(".button.secondary.radius");

    public SecurePage(WebDriver driver) {
        super(driver);
    }

    /**
     * Klickt Logout und gibt die LoginPage zurück
     */
    public LoginPage clickLogout() {
        click(logoutButton);
        return new LoginPage(driver);
    }

    /**
     * Prüft, ob Secure-Seite geladen ist (Logout-Button sichtbar)
     */
    public boolean isLoaded() {
        return waitForVisible(logoutButton).isDisplayed();
    }

    /**
     * Prüft, ob die aktuelle URL zur Secure-Seite gehört
     */
    public boolean isAt() {
        return isUrlContains("/secure");
    }
}
