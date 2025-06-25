package tests.Sprint1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import utils.DriverFactory;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;

public class LoginTest {

    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeSuite
    public void setupReport() {
        // 'Create a folder for storing the test report if it does not exist'
        new File("test_reports").mkdirs();

        // 'Configure the ExtentSparkReporter to generate HTML reports'
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("test_reports/LoginTest_Report.html");
        sparkReporter.config().setDocumentTitle("Login Test Report");
        sparkReporter.config().setReportName("Sprint 1 - Login Automation");

        // 'Initialize the ExtentReports instance and attach the reporter'
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
    }

    @BeforeMethod
    public void initializeDriver() {
        // 'Obtain WebDriver instance from DriverFactory'
        driver = DriverFactory.getDriver();
    }

    @Test
    public void verifyLoginFunctionality() {
        // 'Create a test entry in the report for login verification'
        test = extent.createTest("LoginTest - Verify login functionality");

        try {
            // 'Navigate to the Austin Insight Portal login page'
            driver.get("http://austin-insight4.partners.org");
            test.info("Navigated to Insight Portal URL");


                         // Step 1: Open the URL with embedded credentials for Basic Auth
//
//            String basicAuthUsername = "sankar.venkatesan@technossus.com";  // Replace with actual username if different
//
//            String basicAuthPassword = "7550309189";  // Replace with actual password
//
//            String url = "https://" + basicAuthUsername + "." + basicAuthPassword + "@partnershealthcare.kerberos.oktapreview.com";
//
//            driver.get("http://austin-insight4.partners.org");

//              Step 2: Open the actual login page
//            driver.get("https://sankar.venkatesan%40technossus.com:7550309189@partnershealthcare.kerberos.oktapreview.com/");


            // 'Wait for the login screen to load completely'
            Thread.sleep(20000);



            // 'Locate the username field and enter the provided username'
            WebElement username = driver.findElement(By.xpath("//input[@id='input28']"));
            username.sendKeys("HS131");
            test.pass("Entered username successfully");

            // 'Click the Next button to proceed to the password entry'
            WebElement nextBtn = driver.findElement(By.xpath("//input[@value='Next']"));
            nextBtn.click();
            test.pass("Clicked Next button");

            // 'Allow time for the password field to appear'
            Thread.sleep(5000);

            // 'Input the correct password into the password field'
            WebElement password = driver.findElement(By.xpath("//input[@name='credentials.passcode']"));
            password.sendKeys("MGBexport2025#");
            test.pass("Entered password successfully");

            // 'Wait before clicking Verify to ensure all fields are loaded'
            Thread.sleep(5000);

            // 'Click the Verify button to attempt login'
            WebElement verifyBtn = driver.findElement(By.xpath("//input[@value='Verify']"));
            verifyBtn.click();
            test.pass("Clicked Verify button");

            // 'Wait for post-login redirection to complete'
            Thread.sleep(5000);

            // 'Log that login steps have been completed successfully'
            test.pass("Login steps completed successfully");

        } catch (Exception e) {
            // 'Capture any exceptions during the test and log them as failed'
            test.fail("Login test failed due to exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @AfterMethod
    public void tearDown() {
        // 'Close the WebDriver session after test execution'
        if (driver != null) {
            driver.quit();
        }

        // 'Log that the browser has been closed'
        test.info("Browser session closed");
    }

    @AfterSuite
    public void flushReport() {
        // 'Flush the Extent report to finalize the test results'
        extent.flush();
    }
}
