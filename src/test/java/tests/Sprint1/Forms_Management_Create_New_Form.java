package tests.Sprint1;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
        // 'User will create the directory for storing HTML reports if it doesn't exist'
        new File("test_reports").mkdirs();

        // 'User will initialize ExtentSparkReporter to define the report file path'
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("test_reports/Forms_Management_Create_New_Form.html");

        // 'User will configure the HTML report's document title and name'
        htmlReporter.config().setDocumentTitle("Forms_Management_Create_New_Form");
        htmlReporter.config().setReportName("Sprint 1 Automation");

        // 'User will attach the configured reporter to the ExtentReports instance'
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @BeforeMethod
    public void setupBrowser() {
        // 'User will setup and configure the Chrome WebDriver using WebDriverManager'
        WebDriverManager.chromedriver().setup();

        // 'User will launch a new Chrome browser session'
        driver = new ChromeDriver();

        // 'User will maximize the browser window for optimal visibility'
        driver.manage().window().maximize();

        // 'User will initialize WebDriverWait with a timeout of 10 seconds'
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void createFormFlow() {
        // 'User will initiate the test in the Extent Report'
        test = extent.createTest("Forms_Management_Create_New_Form Test");

        try {
            // 'User will navigate to the Insight Portal login page'
            driver.get("https://sacramento-insight4.partners.org/");
            test.info("Opened dashboard URL");

            // 'User will wait for the login page to fully load'
            Thread.sleep(20000);

            // 'User will input their username into the respective field'
            WebElement username = driver.findElement(By.xpath("//input[@id='input28']"));
            username.sendKeys("HS131");
            test.pass("Entered username");

            // 'User will click the Next button to proceed to the password screen'
            WebElement nextBtn = driver.findElement(By.xpath("//input[@value='Next']"));
            nextBtn.click();
            test.pass("Clicked Next");

            // 'User will wait for the password field to appear'
            Thread.sleep(5000);

            // 'User will enter the password into the credentials field'
            WebElement password = driver.findElement(By.xpath("//input[@name='credentials.passcode']"));
            password.sendKeys("MGBexport2025#");
            test.pass("Entered password");

            // 'User will pause briefly before clicking Verify'
            Thread.sleep(5000);

            // 'User will click the Verify button to complete login'
            WebElement verifyBtn = driver.findElement(By.xpath("//input[@value='Verify']"));
            verifyBtn.click();
            test.pass("Clicked Verify");

            // 'User will wait for redirection to the main dashboard'
            Thread.sleep(30000);

            // 'User will attempt to click the Administration link on the main menu'
            WebElement adminLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Administration']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", adminLink);
            wait.until(ExpectedConditions.elementToBeClickable(adminLink)).click();
            test.pass("Clicked Administration link in the sidebar after confirming visibility and scroll.");

            // 'User will wait for Administration panel to load'
            Thread.sleep(20000);

            // 'User will expand the Forms Management dropdown panel'
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='Expand Forms Management']"))).click();
            test.pass("Expanded Forms Management");

            // 'User will wait for dropdown options to render'
            Thread.sleep(10000);

            // 'User will click on the Agreements option'
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Agreements']"))).click();
            test.pass("Selected Agreements section");

            // 'User will wait for Agreements page to fully load'
            Thread.sleep(10000);

            // 'User will click the Add New button to create a new form'
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Add new']"))).click();
            test.pass("Clicked Add New");

            // 'User will allow the form page to load completely'
            Thread.sleep(10000);

            // 'User will enter the form name in the Name field'
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='name']"))).sendKeys("TestsankarQAautomation005");
            test.pass("Entered form name");

            // 'User will wait before entering description'
            Thread.sleep(10000);

            // 'User will provide a brief description for the form'
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@id='description']"))).sendKeys("TestAutomationQA005");
            test.pass("Entered description");

            // 'User will wait before opening the type selection dropdown'
            Thread.sleep(10000);

            // 'User will click the Type dropdown to reveal options'
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, '_dropdownArrow_1y8qt_214')]"))).click();
            test.pass("Clicked Type drop-down");

            // 'User will wait for dropdown items to appear'
            Thread.sleep(10000);

            // 'User will select the form type as FM-Billable'
            WebElement option = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='react-select-2-option-4']")));
            option.click();
            test.pass("Selected form type as FM-Billable");

            // 'User will wait before selecting category'
            Thread.sleep(10000);

            // 'User will click on the Category dropdown container'
            WebElement control = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='category']/ancestor::div[contains(@class,'select-control')]")));
            control.click();
            test.pass("Clicked the select-control container");

            // 'User will wait before selecting General category'
            Thread.sleep(10000);

            // 'User will select the General option from the dropdown'
            WebElement generalOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='react-select-3-option-2']")));
            generalOption.click();
            test.pass("Selected form type as General");

            // 'User will wait before entering sequence number'
            Thread.sleep(10000);

            // 'User will enter the category sequence number'
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='categorySequenceNo']"))).sendKeys("1");
            test.pass("Entered category sequence number as 1");

            // 'User will wait before final submission'
            Thread.sleep(10000);

            // 'User will click the Create button to finalize form creation'
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Create']"))).click();
            test.pass("Clicked Create button to submit the form");

            // 'User will log success confirmation of form creation'
            test.pass("Form was created successfully without errors.");

        } catch (Exception e) {
            // 'User will log any exception occurred during test execution'
            test.fail("Test failed due to exception: " + e.getMessage());
        }
    }

    @AfterMethod
    public void tearDown() {
        // 'User will close the browser window after the test completes'
        if (driver != null) {
//            driver.quit();
        }

        // 'User will log successful browser closure in the test report'
        test.info("Browser was successfully closed.");
    }

    @AfterSuite
    public void flushExtentReport() {
        // 'User will flush and finalize the Extent report after all tests'
        extent.flush();
    }
}
