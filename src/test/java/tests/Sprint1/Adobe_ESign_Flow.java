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

public class Adobe_ESign_Flow {

    WebDriver driver;
    WebDriverWait wait;
    ExtentReports extent;
    ExtentTest test;

    @BeforeSuite
    public void setupExtentReport() {
        // 'Create the directory where the HTML report will be stored'
        new File("test_reports").mkdirs();

        // 'Initialize ExtentSparkReporter for HTML report generation'
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("test_reports/Adobe_ESign_Flow.html");

        // 'Set report document title and name'
        htmlReporter.config().setDocumentTitle("Adobe_ESign_Flow");
        htmlReporter.config().setReportName("Sprint 1 Automation");

        // 'Attach the configured reporter to ExtentReports instance'
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @BeforeMethod
    public void setupBrowser() {
        // 'Setup ChromeDriver using WebDriverManager'
        WebDriverManager.chromedriver().setup();

        // 'Launch Chrome browser'
        driver = new ChromeDriver();

        // 'Maximize browser window'
        driver.manage().window().maximize();

        // 'Set WebDriver wait timeout to 10 seconds'
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void createFormFlow() {
        // 'Start a new test case in Extent Report'
        test = extent.createTest("Forms_Management_Create_New_Form Test");

        try {
            // 'Navigate to the Insight portal URL'
            driver.get("https://sacramento-insight4.partners.org/");
            test.info("Opened dashboard URL");

            // 'Wait for login page to load'
            Thread.sleep(20000);

            // 'Enter username'
            WebElement username = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("input28")));
            username.sendKeys("HS131");
            test.pass("Entered username");

            // 'Click Next button'
            WebElement nextBtn = driver.findElement(By.xpath("//input[@value='Next']"));
            nextBtn.click();
            test.pass("Clicked Next");

            // 'Wait for password field to appear'
            Thread.sleep(5000);

            // 'Enter password'
            WebElement password = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("credentials.passcode")));
            password.sendKeys("MGBexport2025#");
            test.pass("Entered password");

            // 'Click Verify button'
            Thread.sleep(5000);
            WebElement verifyBtn = driver.findElement(By.xpath("//input[@value='Verify']"));
            verifyBtn.click();
            test.pass("Clicked Verify");

            // 'Wait for dashboard to load'
            Thread.sleep(30000);

            // 'Click on Agreements link from sidebar'
            WebElement agreementsLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/agreements' and contains(.,'Agreements')]")));
            agreementsLink.click();
            test.pass("Clicked Agreements link");

            // 'Wait for agreements page to load'
            Thread.sleep(20000);

            // 'Enter Agreement Number'
            WebElement agreementNumberInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("agreementNumber")));
            agreementNumberInput.sendKeys("2025A011754");
            test.pass("Entered Agreement Number: 2025A011754");

            // 'Wait before clicking Search'
            Thread.sleep(10000);

            // 'Click Search button'
            WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Search']")));
            searchButton.click();
            test.pass("Clicked Search button");

            // 'Wait for search results to load'
            Thread.sleep(20000);

            // 'Click on Agreement ID from search results'
            WebElement agreementSpan = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='2025A011754']")));
            agreementSpan.click();
            test.pass("Clicked on Agreement span: 2025A011754");

            // 'Wait after clicking Agreement span'
            Thread.sleep(10000);

            // 'Click Deliverables tab from sidebar'
            WebElement deliverablesTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/agreements/2025A011754/latest/deliverables']//span[text()='Deliverables']")));
            deliverablesTab.click();
            test.pass("Clicked on correct Deliverables tab in sidebar");

            // 'Wait after clicking Deliverables tab'
            Thread.sleep(5000);

            // 'Minimize E-Sign section'
            WebElement eSignSection = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='title-text' and contains(., 'E-Sign')]")));
            eSignSection.click();
            test.pass("Clicked on E-Sign minimise");

            // 'Wait after minimizing E-Sign section'
            Thread.sleep(10000);

            // 'Maximize E-Sign section again'
            WebElement eSignSection1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='title-text' and contains(., 'E-Sign')]")));
            eSignSection1.click();
            test.pass("Clicked on E-Sign section Maximise");

            // 'Wait after expanding E-Sign section'
            Thread.sleep(5000);

            // 'Click specific E-Sign agreement link'
            WebElement eSignAgreementLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/agreements/2025A011754/latest/deliverables/1110376']//span[text()='For fully executed agreement']")));
            eSignAgreementLink.click();
            test.pass("Clicked 'For fully executed agreement' under E-Sign section");

            // 'Wait after clicking agreement link'
            Thread.sleep(10000);

            // 'Click Adobe integration icon'
            WebElement adobeIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@alt='adobe-icon']")));
            adobeIcon.click();
            test.pass("Clicked Adobe integration icon");

            // 'Wait after Adobe integration icon click'
            Thread.sleep(10000);

            // 'Upload agreement file'
            WebElement fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='file' and @accept='application/pdf']")));
            fileInput.sendKeys("C:\\Users\\SankarVenkatesan\\OneDrive - Technossus LLC\\Documents\\Agreement Details\\Agreement Info 2025_03.pdf");
            test.pass("Successfully uploaded 'Agreement Info 2025.pdf'");

            // 'Wait after file upload'
            Thread.sleep(10000);

            // 'Enter Agreement Name'
            WebElement inputField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='default-input _full-width text-input default-input' and @type='text']")));
            inputField.sendKeys("QA_Automation_Agreement_information3");
            test.pass("Entered text in Agreement name");

            // 'Wait before adding recipient'
            Thread.sleep(10000);

            // 'Click Add Recipient button'
            WebElement addRecipientButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Add Recipient']")));
            addRecipientButton.click();
            test.pass("Clicked 'Add Recipient' button");

            // 'Wait before entering recipient details'
            Thread.sleep(10000);

            // 'Enter recipient email address'
            WebElement fullWidthInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Email']/following::input[@type='text'][1]")));
            fullWidthInput.sendKeys("Sankar.Venkatesan@technossus.com");
            test.pass("Entered value in recipient input field");

            // 'Wait before previewing document'
            Thread.sleep(10000);

            // 'Click Preview Document button'
            WebElement previewButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Preview Document']")));
            previewButton.click();
            test.pass("Clicked 'Preview Document' button");

            // 'Wait for preview to load'
            Thread.sleep(50000);

//            // 'Click E-signature field'
//            WebElement eSignButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='E-signature']/ancestor::div[@class='sc-ejwLJJ gmtySD']")));
//            eSignButton.click();
//            test.pass("Clicked 'E-signature' field button");
//
//            // 'Wait after E-signature click'
//            Thread.sleep(10000);
//
//            // 'Click Send button'
//            WebElement sendButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Send' and @class='ntVziG_spectrum-Button-label']")));
//            sendButton.click();
//            test.pass("Clicked 'Send' button");
//
//            // 'Wait after clicking Send'
//            Thread.sleep(10000);
//
//            // 'Click Continue button'
//            WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Continue' and @class='ntVziG_spectrum-Button-label']")));
//            continueButton.click();
//            test.pass("Clicked 'Continue' button");
//
//            // 'Wait after clicking Continue'
//            Thread.sleep(10000);

            // 'Click Status tab'
            WebElement statusTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Status']")));
            statusTab.click();
            test.pass("Clicked 'Status' tab");

            // 'Wait after clicking Status tab'
            Thread.sleep(10000);

        } catch (Exception e) {
            // 'Log the exception in the test report'
            test.fail("Test failed due to exception: " + e.getMessage());
        }
    }

    @AfterMethod
    public void tearDown() {
        // 'Close the browser after test execution'
        if (driver != null) {
            driver.quit();
        }
        // 'Log browser closure in the test report'
        test.info("Browser was successfully closed.");
    }

    @AfterSuite
    public void flushExtentReport() {
        // 'Generate the final HTML test execution report'
        extent.flush();
    }
}
