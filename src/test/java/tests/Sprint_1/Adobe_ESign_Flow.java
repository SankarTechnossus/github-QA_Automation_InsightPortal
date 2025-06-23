package tests.Sprint_1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;
import java.time.Duration;

public class Adobe_ESign_Flow {

    WebDriver driver;
    WebDriverWait wait;
    ExtentReports extent;
    ExtentTest test;

    @BeforeSuite
    public void setupExtentReport() {
        // 'Create the directory where the HTML report will be stored, if it does not already exist'
        new File("test_reports").mkdirs();

        // 'Initialize ExtentSparkReporter to generate the HTML test execution report'
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("test_reports/Forms_Management_Create_New_Form.html");

        // 'Set the document title and report name for the test report'
        htmlReporter.config().setDocumentTitle("Forms_Management_Create_New_Form");
        htmlReporter.config().setReportName("Sprint 1 Automation");

        // 'Attach the reporter to the ExtentReports instance'
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @BeforeMethod
    public void setupBrowser() {
        // 'Setup and configure the Chrome WebDriver using WebDriverManager'
        WebDriverManager.chromedriver().setup();

        // 'Launch a new Chrome browser instance'
        driver = new ChromeDriver();

        // 'Maximize the browser window to ensure all UI elements are visible'
        driver.manage().window().maximize();

        // 'Initialize explicit wait with a timeout of 10 seconds for dynamic element handling'
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void createFormFlow() {
        // 'Create a new test entry in the Extent Report for the form creation flow'
        test = extent.createTest("Forms_Management_Create_New_Form Test");

        try {
            // 'Open the login page of the Insight Portal application'
            driver.get("https://austin-insight4.partners.org/");
            test.info("Opened dashboard URL");

            // 'Wait for the login screen to load completely before performing actions'
            Thread.sleep(20000);

            // 'Enter the username into the username input field'
            WebElement username = driver.findElement(By.xpath("//input[@id='input28']"));
            username.sendKeys("HS131");
            test.pass("Entered username");

            // 'Click the Next button to proceed to the password entry screen'
            WebElement nextBtn = driver.findElement(By.xpath("//input[@value='Next']"));
            nextBtn.click();
            test.pass("Clicked Next");

            // 'Allow time for the password field to appear'
            Thread.sleep(5000);

            // 'Input the user's password into the password field'
            WebElement password = driver.findElement(By.xpath("//input[@name='credentials.passcode']"));
            password.sendKeys("MGBexport2025#");
            test.pass("Entered password");

            // 'Pause briefly before attempting to click the Verify button'
            Thread.sleep(5000);

            // 'Click the Verify button to authenticate the user'
            WebElement verifyBtn = driver.findElement(By.xpath("//input[@value='Verify']"));
            verifyBtn.click();
            test.pass("Clicked Verify");

            // 'Wait for the user to be redirected to the dashboard'
            Thread.sleep(10000);

            // Click the Agreements link from the main dashboard menu
            WebElement agreementsLink = driver.findElement(By.xpath("//a[@class='module-link -with-icon' and @href='/agreements' and contains(text(),'Agreements')]"));
            agreementsLink.click();
            test.pass("Clicked Agreements");

            // 'Wait for the user to be redirected to the dashboard'
            Thread.sleep(10000);

            // Click the Select... dropdown placeholder
            WebElement selectPlaceholder = driver.findElement(By.xpath("//div[@id='react-select-7-placeholder' and contains(text(),'Select...')]"));
            selectPlaceholder.click();
            test.pass("Clicked Select... placeholder");

            // 'Wait for the user to be redirected to the dashboard'
            Thread.sleep(10000);

            // Click the Draft option from the dropdown
            WebElement draftOption = driver.findElement(By.xpath("//div[@id='react-select-7-option-0' and text()='Draft']"));
            draftOption.click();
            test.pass("Clicked Draft option");

            // 'Wait for the user to be redirected to the dashboard'
            Thread.sleep(10000);

            // Click the Search button to submit the form
            WebElement searchButton = driver.findElement(By.xpath("//button[@type='submit' and contains(text(),'Search')]"));
            searchButton.click();
            test.pass("Clicked Search button");

            // 'Wait for the user to be redirected to the dashboard'
            Thread.sleep(10000);

            // Click the span with ID 2025A011342
            WebElement studentIdSpan = driver.findElement(By.xpath("//span[text()='2025A011342']"));
            studentIdSpan.click();
            test.pass("Clicked span with text 2025A011342");

            // 'Wait for the user to be redirected to the dashboard'
            Thread.sleep(10000);

        } catch (Exception e) {
            // 'Capture and log any exceptions that occur during the test'
            test.fail("Test failed due to exception: " + e.getMessage());
        }
    }

    @AfterMethod
    public void tearDown() {
        // 'Close the browser instance after test execution is complete'
        if (driver != null) {
//            driver.quit();
        }

        // 'Record browser closure in the test report'
        test.info("Browser was successfully closed.");
    }

    @AfterSuite
    public void flushExtentReport() {
        // 'Flush all the test information and generate the final HTML report'
        extent.flush();
    }
}
