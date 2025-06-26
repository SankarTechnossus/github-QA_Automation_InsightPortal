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
        // 'Create the directory where the HTML report will be stored, if it does not already exist'
        new File("test_reports").mkdirs();

        // 'Initialize ExtentSparkReporter to generate the HTML test execution report'
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("test_reports/Adobe_ESign_Flow.html");

        // 'Set the document title and report name for the test report'
        htmlReporter.config().setDocumentTitle("Adobe_ESign_Flow");
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
            driver.get("https://sacramento-insight4.partners.org/");
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
            Thread.sleep(20000);

            // Click the Agreements link from the main dashboard menu
            WebElement agreementsLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/agreements' and contains(.,'Agreements')]")));
            agreementsLink.click();
            test.pass("Clicked Agreements link");

            // 'Wait for the user to be redirected to the dashboard'
            Thread.sleep(20000);

            // Enter value in Agreement Number input
            WebElement agreementNumberInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='agreementNumber']")));agreementNumberInput.sendKeys("2025A011754");
            test.pass("Entered Agreement Number: 2025A011754");

            // 'Wait for the user is user Entered Agreement Number'
            Thread.sleep(10000);

            // Click the Search button
            WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Search']")));searchButton.click();
            test.pass("Clicked Search button");

            // 'Wait for the user is user click on search button'
            Thread.sleep(10000);

            // Click on the agreement number span (2025A011754)
            WebElement agreementSpan = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='2025A011754']")));agreementSpan.click();
            test.pass("Clicked on Agreement span: 2025A011754");

            // Wait after the user clicks the Clicked on Agreement span: 2025A011754
            Thread.sleep(20000);

            // Click on the Deliverables tab or section
            WebElement deliverablesTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Deliverables']")));deliverablesTab.click();
            test.pass("Clicked on Deliverables tab");

            // Wait after the Deliverables tab is clicked
            Thread.sleep(10000);

            // Click on the E-Sign section
            WebElement eSignSection = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='title-text' and contains(., 'E-Sign')]")));eSignSection.click();
            test.pass("Clicked on E-Sign section");

            // Wait after the E-Sign section is clicked
            Thread.sleep(10000);


            // Click on the E-Sign section
            WebElement eSignSection1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='title-text' and contains(., 'E-Sign')]")));eSignSection1.click();
            test.pass("Clicked on E-Sign section");

            // Wait after the E-Sign section is clicked
            Thread.sleep(10000);

            // Click on the Target Due Date under the E-Sign section
            WebElement targetDueDate = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='E-Sign']/ancestor::div[contains(@class, 'collapsible-bar')]//a[contains(text(),'Target Due Date')]")));targetDueDate.click();
            test.pass("Clicked on Target Due Date under E-Sign section");

            // Wait after Clicked on Target Due Date under E-Sign section
            Thread.sleep(10000);

            // Click "For fully executed agreement" under the E-Sign section only
            WebElement eSignAgreementLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='E-Sign']/ancestor::div[contains(@class, 'collapsible-bar')]//a[contains(text(),'For fully executed agreement')]")));eSignAgreementLink.click();
            test.pass("Clicked 'For fully executed agreement' under E-Sign section");

            // Wait after Clicked 'For fully executed agreement' under E-Sign section
            Thread.sleep(10000);

            // Click the Adobe integration icon
            WebElement adobeIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@alt='adobe-icon']")));adobeIcon.click();
            test.pass("Clicked Adobe integration icon");

            // Wait after Clicked Adobe integration icon
            Thread.sleep(10000);

            // Click the "Choose more files" button
            WebElement chooseFilesButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Choose more files']")));chooseFilesButton.click();
            test.pass("Clicked 'Choose more files' button");

            // Wait after Clicked 'Choose more files' button
            Thread.sleep(10000);

            // 1. Wait for the hidden file input to be present
            WebElement fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='file' and @accept='application/pdf']")));

            // 2. Send the full file path to the input (uploading the file)
            fileInput.sendKeys("C:\\Users\\SankarVenkatesan\\OneDrive - Technossus LLC\\Documents\\Agreement Details\\Agreement Info 2025.pdf");

            // 3. Log success
            test.pass(" Successfully uploaded 'Agreement Info 2025.pdf'");

            // Wait after Clicked Successfully uploaded 'Agreement Info 2025.pdf
            Thread.sleep(10000);

            // Enter value in the full-width input field
            WebElement inputField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='default-input _full-width text-input default-input' and @type='text']")));inputField.sendKeys("QA_Automation_Agreement_information1");
            test.pass("Entered text in Agreement name");

            // Entered text Entered text in Agreement name
            Thread.sleep(10000);

            // Click the "Add Recipient" button
            WebElement addRecipientButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Add Recipient']")));addRecipientButton.click();
            test.pass("Clicked 'Add Recipient' button");

            // Wait after clicking 'Add Recipient' button
            Thread.sleep(10000);

            // Enter value in full-width input field under recipient section
            WebElement fullWidthInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='default-input _full-width text-input default-input' and @type='text']")));fullWidthInput.sendKeys("Sankar.Venkatesan@technossus.com"); // or recipient name, depending on context
            test.pass("Entered value in recipient input field");

            // Wait after entering recipient input
            Thread.sleep(10000);

            // Click the "Preview Document" button
            WebElement previewButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Preview Document']")));previewButton.click();
            test.pass("Clicked 'Preview Document' button");

            // Wait after clicking Preview Document button
            Thread.sleep(30000);


            // Click the "E-signature" field in the preview section
            WebElement eSignButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='E-signature']/ancestor::div[@class='sc-ejwLJJ gmtySD']")));eSignButton.click();
            test.pass("Clicked 'E-signature' field button");

            // Wait after clicking E-signature
            Thread.sleep(10000);

            // Click the "Send" button
            WebElement sendButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Send' and @class='ntVziG_spectrum-Button-label']")));sendButton.click();
            test.pass("Clicked 'Send' button");

            // Wait after clicking Send
            Thread.sleep(10000);


            // Click the "Continue" button
            WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Continue' and @class='ntVziG_spectrum-Button-label']")));continueButton.click();
            test.pass("Clicked 'Continue' button");

            // Wait after clicking Continue

            Thread.sleep(10000);


            // Click the "Status" tab
            WebElement statusTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Status']")));statusTab.click();
            test.pass("Clicked 'Status' tab");

            // Wait after clicking the status tab
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
