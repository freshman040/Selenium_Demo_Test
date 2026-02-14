package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.SecurePage;
import testdata.TestData;

/**
 * Alle Authentifizierungs-Tests
 * Keine direkten driver-Aufrufe mehr
 */
public class AuthenticationTest extends BaseTest {

    @Test
    public void loginWithInvalidCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();

        loginPage.login(TestData.INVALID_USERNAME, TestData.INVALID_PASSWORD);

        String error = loginPage.getFlashMessage();
        Assert.assertTrue(
                error.contains("Your username is invalid"),
                "Expected error message not displayed. Actual: " + error
        );

        // Prüft, dass LoginPage noch geladen ist
        Assert.assertTrue(loginPage.isLoaded(), "Login page not loaded after failed login");
        Assert.assertTrue(loginPage.isAt(), "User is not on /login page after failed login");
    }

    @Test
    public void loginWithValidCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();

        SecurePage securePage = loginPage.login(TestData.VALID_USERNAME, TestData.VALID_PASSWORD);

        Assert.assertTrue(securePage.isLoaded(), "Secure page did not load after login");
        Assert.assertTrue(securePage.isAt(), "User is not on /secure page after login");
    }

    @Test
    public void logoutAfterLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();

        SecurePage securePage = loginPage.login(TestData.VALID_USERNAME, TestData.VALID_PASSWORD);

        LoginPage loginPageAfterLogout = securePage.clickLogout();

        Assert.assertTrue(loginPageAfterLogout.isLoaded(), "Login page not loaded after logout");
        Assert.assertTrue(loginPageAfterLogout.isAt(), "User is not on /login page after logout");

        String message = loginPageAfterLogout.getFlashMessage();
        Assert.assertTrue(
                message.contains("You logged out of the secure area!"),
                "Logout flash message not displayed. Actual: " + message
        );
    }
}
