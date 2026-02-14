package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverFactory {

    public static WebDriver createDriver(){

        // Hier kann später konfiguriert werden, welcher Browser gestartet wird
        String browser = "chrome";

        switch (browser){
            case "chrome":
                return new ChromeDriver();

                //später:
            // case "firefox":
            // return new FirefoxDriver();


            //case "edge":
            //    return new EdgeDriver();

            default:
                throw new RuntimeException("Browser not supported: " + browser);
        }

    }
}
