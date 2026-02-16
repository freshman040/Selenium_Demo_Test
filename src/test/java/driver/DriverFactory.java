package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    public static WebDriver createDriver(){

        // Hier kann später konfiguriert werden, welcher Browser gestartet wird
        String browser = System.getProperty("browser", "chrome");
        boolean isCI = "true".equals(System.getenv("CI"));

        switch (browser.toLowerCase()){
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                if (isCI) applyHeadless(chromeOptions);
                return new ChromeDriver(chromeOptions);

                //später:
            case "firefox":
                 return new FirefoxDriver();


            //case "edge":
            //    return new EdgeDriver();

            default:
                throw new RuntimeException("Browser not supported: " + browser);
        }

    }

    // Hilfsmethode speziell für Chrome/Edge
    private static void applyHeadless(ChromeOptions options){
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");
    }
}
