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

public class Forms_Management_Create_New_Form {

    WebDriver driver;
    WebDriverWait wait;
    ExtentReports extent;
    ExtentTest test;

    @BeforeSuite
    public void setupExtentReport() {
        // Create test_reports folder if it doesn't exist
        new File("test_reports").mkdirs();

        // Setup ExtentReports
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("test_reports/Forms_Management_Create_New_Form.html");
        htmlReporter.config().setDocumentTitle("Forms_Management_Create_New_Form");
        htmlReporter.config().setReportName("Sprint 1 Automation");

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @BeforeMethod
    public void setupBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void createFormFlow() {
        test = extent.createTest("Forms_Management_Create_New_Form Test");

        try {
            driver.get("https://austin-insight4.partners.org/");
            test.info("Opened dashboard URL");
            Thread.sleep(20000);

            WebElement username = driver.findElement(By.xpath("//input[@id='input28']"));
            username.sendKeys("HS131");
            test.pass("Entered username");

            WebElement nextBtn = driver.findElement(By.xpath("//input[@value='Next']"));
            nextBtn.click();
            test.pass("Clicked Next");

            Thread.sleep(5000);

            WebElement password = driver.findElement(By.xpath("//input[@name='credentials.passcode']"));
            password.sendKeys("MBGexport2025#");
            test.pass("Entered password");

            Thread.sleep(5000);

            WebElement verifyBtn = driver.findElement(By.xpath("//input[@value='Verify']"));
            verifyBtn.click();
            test.pass("Clicked Verify");

            Thread.sleep(5000);

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Administration']"))).click();
            test.pass("Clicked Administration");

            Thread.sleep(2000);

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='Expand Forms Management']"))).click();
            test.pass("Clicked Forms Management");

            Thread.sleep(2000);

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Agreements']"))).click();
            test.pass("Clicked Agreements");

            Thread.sleep(2000);

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Add new']"))).click();
            test.pass("Clicked Add New");

            Thread.sleep(2000);

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='name']"))).sendKeys("TestsankarQAautomation1");
            test.pass("Entered name");

            Thread.sleep(1000);

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@id='description']"))).sendKeys("TestAutomationQA");
            test.pass("Entered description");

            Thread.sleep(1000);

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, '_dropdownArrow_1y8qt_214')]"))).click();
            test.pass("Clicked Type dropdown");

            Thread.sleep(1000);

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(), 'FM-Billable')]"))).click();
            test.pass("Selected 'FM-Billable'");

            Thread.sleep(1000);

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='react-select-9-placeholder']"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(), 'General')]"))).click();
            test.pass("Selected category General");

            Thread.sleep(1000);

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='categorySequenceNo']"))).sendKeys("1");
            test.pass("Entered sequence number");

            Thread.sleep(1000);

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Create']"))).click();
            test.pass("Clicked Create");

            test.pass("Form created successfully");

        } catch (Exception e) {
            test.fail("Test failed: " + e.getMessage());
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        test.info("Browser closed");
    }

    @AfterSuite
    public void flushExtentReport() {
        extent.flush();
    }
}
