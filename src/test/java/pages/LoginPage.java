package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * PageObject für die Login-Seite
 */
public class LoginPage extends BasePage {

    private By usernameField = By.id("username");
    private By passwordField = By.id("password");
    private By loginButton = By.cssSelector("button[type='submit']");
    private By flashMessage = By.id("flash");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Öffnet die Login-Seite
     */
    public void open() {
        driver.get("https://the-internet.herokuapp.com/login");
    }

    /**
     * Loggt den User ein und gibt die SecurePage zurück
     */
    public SecurePage login(String username, String password) {
        type(usernameField, username);
        type(passwordField, password);
        click(loginButton);
        return new SecurePage(driver);
    }

    /**
     * Holt FlashMessage Text
     */
    public String getFlashMessage() {
        return getText(flashMessage);
    }

    /**
     * Prüft, ob Login-Seite korrekt geladen ist
     */
    public boolean isLoaded() {
        return waitForVisible(loginButton).isDisplayed();
    }

    /**
     * Prüft, ob aktuelle URL zur Login-Seite gehört
     */
    public boolean isAt() {
        return isUrlContains("/login");
    }
}
