package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.DriverFactory;

public class LoginTest {
    public static void main(String[] args) {
        WebDriver driver = DriverFactory.getDriver();

        try {
            // Step 1: Open the login page
            driver.get("http://austin-insight4.partners.org");

            // Step 2: Wait for 10 seconds
            Thread.sleep(20000);

            // Step 3: Enter Username
            WebElement username = driver.findElement(By.xpath("//input[@id='input28']"));
            username.sendKeys("HS131");

            // Step 4: Click "Next"
            WebElement nextBtn = driver.findElement(By.xpath("//input[@value='Next']"));
            nextBtn.click();

            // Step 5: Wait for password field to load
            Thread.sleep(5000);

            // Step 6: Enter Password
            WebElement password = driver.findElement(By.xpath("//input[@name='credentials.passcode']"));
            password.sendKeys("MBGexport2025#");

            // Step 7: Click "Verify"
            WebElement verifyBtn = driver.findElement(By.xpath("//input[@value='Verify']"));
            verifyBtn.click();

            // ✅ Step 8 (optional): Add wait or print to confirm success
            Thread.sleep(5000);
            System.out.println("Login steps completed.");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Step 9: Optional - Close the browser
             //driver.quit();
        }
    }
}
