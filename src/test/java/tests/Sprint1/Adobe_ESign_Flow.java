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
        // User will create the directory where the HTML report will be stored, if it does not already exist
        new File("test_reports").mkdirs();

        // User will initialize ExtentSparkReporter to generate the HTML test execution report
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("test_reports/Adobe_ESign_Flow.html");

        // User will set the document title and report name for the test report
        htmlReporter.config().setDocumentTitle("Adobe_ESign_Flow");
        htmlReporter.config().setReportName("Sprint 1 Automation");

        // User will attach the reporter to the ExtentReports instance
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @BeforeMethod
    public void setupBrowser() {
        // User will setup and configure the Chrome WebDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();

        // User will launch a new Chrome browser instance
        driver = new ChromeDriver();

        // User will maximize the browser window to ensure all UI elements are visible
        driver.manage().window().maximize();

        // User will initialize explicit wait with a timeout of 10 seconds for dynamic element handling
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void createFormFlow() {
        // User will create a new test entry in the Extent Report for the form creation flow
        test = extent.createTest("Forms_Management_Create_New_Form Test");

        try {
            // User will open the login page of the Insight Portal application
            driver.get("https://sacramento-insight4.partners.org/");
            test.info("Opened dashboard URL");

            // User will wait for the login screen to load completely before performing actions
            Thread.sleep(20000);

            // User will enter the username into the username input field
            WebElement username = driver.findElement(By.xpath("//input[@id='input28']"));
            username.sendKeys("HS131");
            test.pass("Entered username");

            // User will click the 'Next' button to proceed to the password entry screen
            WebElement nextBtn = driver.findElement(By.xpath("//input[@value='Next']"));
            nextBtn.click();
            test.pass("Clicked Next");

            // User will wait briefly for the password field to appear
            Thread.sleep(5000);

            // User will input the user's password into the password field
            WebElement password = driver.findElement(By.xpath("//input[@name='credentials.passcode']"));
            password.sendKeys("MGBexport2025#");
            test.pass("Entered password");

            // User will pause briefly before attempting to click the 'Verify' button
            Thread.sleep(5000);

            // User will click the 'Verify' button to authenticate the user
            WebElement verifyBtn = driver.findElement(By.xpath("//input[@value='Verify']"));
            verifyBtn.click();
            test.pass("Clicked Verify");

            // User will wait for the user to be redirected to the dashboard
            Thread.sleep(30000);

            // User will click the 'Agreements' link from the sidebar menu
            WebElement agreementsLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/agreements' and contains(.,'Agreements')]")));
            agreementsLink.click();
            test.pass("Clicked Agreements link");

            // User will wait for the Agreements page to load
            Thread.sleep(20000);

            // User will enter the agreement number into the corresponding input field
            WebElement agreementNumberInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='agreementNumber']")));
            agreementNumberInput.sendKeys("2025A011754");
            test.pass("Entered Agreement Number: 2025A011754");

            // User will wait after entering the agreement number
            Thread.sleep(10000);

            // User will click the 'Search' button to search for the agreement
            WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Search']")));
            searchButton.click();
            test.pass("Clicked Search button");

            // User will wait for search results to appear
            Thread.sleep(20000);

            // User will click on the agreement number span to open agreement details
            WebElement agreementSpan = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='2025A011754']")));
            agreementSpan.click();
            test.pass("Clicked on Agreement span: 2025A011754");

            // User will wait after opening the agreement
            Thread.sleep(10000);

            // User will click the 'Deliverables' tab in the sidebar for the selected agreement
            WebElement deliverablesTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/agreements/2025A011754/latest/deliverables']//span[text()='Deliverables']")));
            deliverablesTab.click();
            test.pass("Clicked on correct Deliverables tab in sidebar");

            // User will wait after navigating to the Deliverables tab
            Thread.sleep(5000);

            // User will click the E-Sign section to minimize it
            WebElement eSignSection = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='title-text' and contains(., 'E-Sign')]")));
            eSignSection.click();
            test.pass("Clicked on E-Sign minimise");

            // User will wait after minimizing the E-Sign section
            Thread.sleep(10000);

            // User will click the E-Sign section to maximize it
            WebElement eSignSection1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='title-text' and contains(., 'E-Sign')]")));
            eSignSection1.click();
            test.pass("Clicked on E-Sign section Maximise");

            // User will wait after maximizing the E-Sign section
            Thread.sleep(5000);

            // User will click the 'For fully executed agreement' link under E-Sign section
            WebElement eSignAgreementLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/agreements/2025A011754/latest/deliverables/1110376']//span[text()='For fully executed agreement']")));
            eSignAgreementLink.click();
            test.pass("Clicked 'For fully executed agreement' under E-Sign section");

            // User will wait after clicking the agreement link
            Thread.sleep(10000);

            // User will click the Adobe integration icon to start the e-sign process
            WebElement adobeIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@alt='adobe-icon']")));
            adobeIcon.click();
            test.pass("Clicked Adobe integration icon");

            // User will wait after clicking the Adobe icon
            Thread.sleep(10000);

            // User will upload the agreement PDF file via file input
            WebElement fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='file' and @accept='application/pdf']")));
            fileInput.sendKeys("C:\\Users\\SankarVenkatesan\\OneDrive - Technossus LLC\\Documents\\Agreement Details\\Agreement Info 2025_03.pdf");
            test.pass("Successfully uploaded 'Agreement Info 2025.pdf'");

            // User will wait after uploading the file
            Thread.sleep(10000);

            // User will enter the name of the agreement into the input field
            WebElement inputField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='default-input _full-width text-input default-input' and @type='text']")));
            inputField.sendKeys("QA_Automation_Agreement_information3");
            test.pass("Entered text in Agreement name");

            // User will wait after entering agreement name
            Thread.sleep(10000);

            // User will click the 'Add Recipient' button
            WebElement addRecipientButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Add Recipient']")));
            addRecipientButton.click();
            test.pass("Clicked 'Add Recipient' button");

            // User will wait after adding recipient section
            Thread.sleep(10000);

            // User will enter the recipient email in the input field
            WebElement fullWidthInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Email']/following::input[@type='text'][1]")));
            fullWidthInput.sendKeys("Sankar.Venkatesan@technossus.com");
            test.pass("Entered value in recipient input field");

            // User will wait after entering recipient email
            Thread.sleep(10000);

            // User will click the 'Preview Document' button
            WebElement previewButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Preview Document']")));
            previewButton.click();
            test.pass("Clicked 'Preview Document' button");

            // User will wait after previewing the document
            Thread.sleep(50000);

            //*comment following lines

//            // User will click on the E-signature field in the preview section
//            WebElement eSignButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='E-signature']/ancestor::div[@class='sc-ejwLJJ gmtySD']")));
//            eSignButton.click();
//            test.pass("Clicked 'E-signature' field button");
//
//            // User will wait after clicking E-signature field
//            Thread.sleep(10000);
//
//            // User will click the 'Send' button to send the document for signature
//            WebElement sendButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Send' and @class='ntVziG_spectrum-Button-label']")));
//            sendButton.click();
//            test.pass("Clicked 'Send' button");
//
//            // User will wait after clicking send button
//            Thread.sleep(10000);
//
//            // User will click the 'Continue' button to confirm sending
//            WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Continue' and @class='ntVziG_spectrum-Button-label']")));
//            continueButton.click();
//            test.pass("Clicked 'Continue' button");
//
//            // User will wait after clicking continue button
//            Thread.sleep(10000);

            //**comment above lines

            // User will click the 'Status' tab to verify the current document status
            WebElement statusTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Status']")));
            statusTab.click();
            test.pass("Clicked 'Status' tab");

            // User will wait after navigating to the status tab
            Thread.sleep(10000);

        } catch (Exception e) {
            // User will capture and log any exceptions that occur during the test
            test.fail("Test failed due to exception: " + e.getMessage());
        }
    }

    @AfterMethod
    public void tearDown() {
        // User will close the browser instance after test execution is complete
        if (driver != null) {
            driver.quit();
        }

        // User will record browser closure in the test report
        test.info("Browser was successfully closed.");
    }

    @AfterSuite
    public void flushExtentReport() {
        // User will flush all the test information and generate the final HTML report
        extent.flush();
    }
}
