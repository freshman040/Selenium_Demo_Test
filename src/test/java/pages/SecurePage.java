package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SecurePage extends  BasePage {

    private By logoutButton = By.cssSelector(".button.secondary.radius");

    public SecurePage(WebDriver driver){
        super(driver);
    }

   public void clickLogout(){
        click(logoutButton);
    }


}
