package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {
    public static WebDriver getDriver() {
        System.setProperty("webdriver.chrome.driver", "src/test/java/drivers/chromedriver.exe"); // path to your chromedriver
        return new ChromeDriver();
    }
}
