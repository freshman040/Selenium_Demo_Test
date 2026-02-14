package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.SecurePage;
import testdata.TestData;

public class AuthenticationTest extends BaseTest {


    @Test
    public void loginWithInvalidCredentials(){

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();

        loginPage.login(TestData.INVALID_USERNAME, TestData.INVALID_PASSWORD);

        String error = loginPage.getFlashMessage();
        Assert.assertTrue(error.contains("Your username is invalid"));
    }

    @Test
    public void loginWithValidCredentials(){

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();

        loginPage.login(TestData.VALID_USERNAME, TestData.VALID_PASSWORD);

        loginPage.getFlashMessage();

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(
                currentUrl != null && currentUrl.contains("/secure"),
                "User is not on secure page. Current URL: " + currentUrl
        );


    }


    @Test
    public void logoutAfterLogin(){
        // Page initialisieren
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();

        // Login
        loginPage.login(TestData.VALID_USERNAME,TestData.VALID_PASSWORD);

        // Logout
        SecurePage securePage = new SecurePage(driver);
        securePage.clickLogout();

        // 1.Erst die URL prüfen (wir sollten zurück auf login sein)
        Assert.assertTrue(driver.getCurrentUrl().contains("/login"), "Logout did not redirect to login page.");


        String message = loginPage.getFlashMessage();
        Assert.assertTrue(message.contains("You logged out of the secure area!"));

        String currentUrl = driver.getCurrentUrl();

        Assert.assertTrue(currentUrl.contains("/login"));


    }

}
