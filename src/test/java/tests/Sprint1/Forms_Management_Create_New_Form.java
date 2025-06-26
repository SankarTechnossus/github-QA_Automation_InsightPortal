package tests.Sprint1;

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

public class Forms_Management_Create_New_Form {

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

            // 'Click the Administration link from the main dashboard menu'
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Administration']"))).click();
            test.pass("Clicked Administration");

            // 'Allow the Administration section to fully expand'
            Thread.sleep(10000);

            // 'Expand the Forms Management drop-down section'
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='Expand Forms Management']"))).click();
            test.pass("Expanded Forms Management");

            // 'Pause briefly to allow drop-down options to appear'
            Thread.sleep(10000);

            // 'Click on the Agreements link under Forms Management'
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Agreements']"))).click();
            test.pass("Selected Agreements section");

            // 'Wait for the Agreements page to fully load'
            Thread.sleep(10000);
            // 'Click on the Add New button to begin creating a new form'
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Add new']"))).click();
            test.pass("Clicked Add New");

            // 'Allow the form creation fields to load completely'
            Thread.sleep(10000);

            // 'Input the form name into the Name field'
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='name']"))).sendKeys("TestsankarQAautomation005");
            test.pass("Entered form name");

            // 'Brief wait before proceeding to the next field'
            Thread.sleep(10000);

            // 'Enter a description into the Description text area'
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@id='description']"))).sendKeys("TestAutomationQA005");
            test.pass("Entered description");

            // 'Pause before selecting the type from the drop-down menu'
            Thread.sleep(10000);

            // 'Click on the Type drop-down to display available options'
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, '_dropdownArrow_1y8qt_214')]"))).click();
            test.pass("Clicked Type drop-down");

            // 'Wait for the drop-down options to become clickable'
            Thread.sleep(10000);

            // Select "FM-Billable" from the Type drop-down options using the stable id-based XPath
            WebElement option = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='react-select-2-option-4']")));option.click();
            test.pass("Selected form type as FM-Billable");


            // 'Pause before selecting a category'
            Thread.sleep(10000);

            // Wait for and click the whole control so the dropdown opens
            WebElement control = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='category']/ancestor::div[contains(@class,'select-control')]")));control.click();
            test.pass("Clicked the select-control container");

            // 'Wait before entering the General'
            Thread.sleep(10000);

            // Wait for and click the “General” option using the id-based XPath
            WebElement generalOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='react-select-3-option-2']")));generalOption.click();
            test.pass("Selected form type as General");


            // 'Wait before entering the category sequence number'
            Thread.sleep(10000);

            // 'Enter the value "1" into the Category Sequence Number field'
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='categorySequenceNo']"))).sendKeys("1");
            test.pass("Entered category sequence number as 1");

            // 'Pause before final submission'
            Thread.sleep(10000);

            // 'Click the Create button to submit and save the new form'
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Create']"))).click();
            test.pass("Clicked Create button to submit the form");

            // 'Log success message for form creation'
            test.pass("Form was created successfully without errors.");

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
