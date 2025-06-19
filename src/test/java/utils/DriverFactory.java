package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverFactory {
    public static WebDriver getDriver() {
        System.setProperty("webdriver.chrome.driver", "src/test/java/drivers/chromedriver.exe"); // path to your chromedriver
//        System.setProperty("webdriver.edge.driver", "src/test/java/drivers/msedgedriver.exe");  // Use this line if you want to use Edge browser
        return new ChromeDriver();
//        return new EdgeDriver();
    }
}
