package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {

        private By username = By.id("username");
        private By password = By.id("password");
        private By loginButton = By.cssSelector("button[type=submit]");
        private By flashMessage = By.id("flash");

        public LoginPage(WebDriver driver){
            super(driver);
        }

        public void open(){
            driver.get("https://the-internet.herokuapp.com/login");
        }

        public void login(String user, String pass){
            type(username, user);
            type(password, pass);
            click(loginButton);
        }

        public String getFlashMessage(){
            return getText(flashMessage);
        }

}
