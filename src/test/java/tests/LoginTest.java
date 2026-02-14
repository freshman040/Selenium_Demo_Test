package tests;

import base.BaseTest;
import net.bytebuddy.asm.MemberSubstitution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.SecurePage;
import pages.BasePage;
import testdata.TestData;

public class LoginTest extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(LoginTest.class);

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
        Assert.assertTrue(driver.getCurrentUrl().contains("/secure"));
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


        loginPage.getFlashMessage();
        String message = loginPage.getFlashMessage();
        Assert.assertTrue(message.contains("You logged out of the secure area!"));

        String currentUrl = driver.getCurrentUrl();

        loginPage.getFlashMessage();
        Assert.assertTrue(currentUrl.contains("/login"));


    }

}
