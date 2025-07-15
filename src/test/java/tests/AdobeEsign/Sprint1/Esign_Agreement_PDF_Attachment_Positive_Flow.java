package tests.AdobeEsign.Sprint1;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import listeners.ExtentReportListener;
import org.testng.annotations.Listeners;
import utils.DriverManager;

import java.io.File;
import java.time.Duration;

@Listeners(listeners.ExtentReportListener.class)
public class Esign_Agreement_PDF_Attachment_Positive_Flow {

    WebDriver driver;
    WebDriverWait wait;
//    ExtentReports extent;
//    ExtentTest test;
    BasePage basePage;

//    @BeforeSuite
//    public void setupExtentReport() {
        // User will create the directory where the HTML report will be stored, if it does not already exist
//        new File("test_reports").mkdirs();
//
//        // User will initialize ExtentSparkReporter to generate the HTML test execution report
//        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("test_reports/Esign_Agreement_PDF_Attachment_Positive_Flow.html");
//
//        // User will set the document title and report name for the test report
//        htmlReporter.config().setDocumentTitle("Esign_Agreement_PDF_Attachment_Positive_Flow");
//        htmlReporter.config().setReportName("Sprint 1 Automation");
//
//        // User will attach the reporter to the ExtentReports instance
//        extent = new ExtentReports();
//        extent.attachReporter(htmlReporter);
//    }

    @BeforeMethod
    public void setupBrowser() {
//         User will setup and configure the Chrome WebDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();

        // User will launch a new Chrome browser instance
        driver = new ChromeDriver();

        // Set driver to DriverManager for global access
        DriverManager.setDriver(driver);

        // User will maximize the browser window to ensure all UI elements are visible
        driver.manage().window().maximize();

        // User will initialize explicit wait with a timeout of 10 seconds for dynamic element handling
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        basePage = new BasePage (driver);
    }


    @Test
    public void createFormFlow() {
        // User will create a new test entry in the Extent Report for the form creation flow
//        test = extent.createTest("Forms_Management_Create_New_Form Test");
        ExtentReportListener.getExtentTest().info("your log message");
        try {
            // User will open the login page of the Insight Portal application
            driver.get("https://sacramento-insight4.partners.org/");
//            test.info("Opened dashboard URL");
            ExtentReportListener.getExtentTest().info("Opened dashboard URL");
            // User will wait for the login screen to load completely before performing actions
            basePage.pause(20000);


            // User will enter the username into the username input field
            WebElement username = basePage.waitForElement(By.xpath("//input[@id='input28']"));
            username.sendKeys("SV1179");
            basePage.pause(10000);
//            test.pass("Entered username");
            ExtentReportListener.getExtentTest().pass("Entered username");


            // User will click the 'Next' button to proceed to the password entry screen
            WebElement nextBtn = basePage.waitForElement(By.xpath("//input[@value='Next']"));
            nextBtn.click();
            basePage.pause(10000);
//            test.pass("Clicked Next");
            ExtentReportListener.getExtentTest().pass("Clicked Next");


            // User will input the user's password into the password field
            WebElement password = basePage.waitForElement(By.xpath("//input[@name='credentials.passcode']"));
            password.sendKeys("Devinivetha@1930");
            basePage.pause(10000);
//            test.pass("Entered password");
            ExtentReportListener.getExtentTest().pass("Entered password");


            // User will click the 'Verify' button to authenticate the user
            WebElement verifyBtn = basePage.waitForElement(By.xpath("//input[@value='Verify']"));
            verifyBtn.click();
            basePage.pause(10000);
//            test.pass("Clicked Verify");
            ExtentReportListener.getExtentTest().pass("Clicked Verify");


            // Wait and click the 'Agreements' link from the sidebar
            WebElement agreementsLink = basePage.waitForElement50(By.xpath("//a[contains(text(),'Agreements')]"));
            Assert.assertTrue(agreementsLink.isDisplayed(), "Agreements link is not visible after login");
            agreementsLink.click();
            basePage.pause(10000);
//            test.pass("Clicked 'Agreements' link from sidebar");
            ExtentReportListener.getExtentTest().pass("Clicked 'Agreements' link from sidebar");

            // User will enter the agreement number into the corresponding input field
            WebElement agreementNumberInput = basePage.waitForElement50(By.xpath("//input[@id='agreementNumber']"));
            Assert.assertTrue(agreementNumberInput.isDisplayed(), "AgreementNumberInput is not visible after login");
            agreementNumberInput.sendKeys("2025A012368");
            basePage.pause(10000);
//            test.pass("Entered Agreement Number: 2025A012368");
            ExtentReportListener.getExtentTest().pass("Entered Agreement Number: 2025A012368");

            // User will click the 'Search' button to search for the agreement
            WebElement searchButton = basePage.waitForElement(By.xpath("//button[text()='Search']"));
            searchButton.click();
            basePage.pause(10000);
//            test.pass("Clicked Search button");
            ExtentReportListener.getExtentTest().pass("Clicked Search button");



            // User will click on the agreement number span to open agreement details
            WebElement agreementSpan = basePage.waitForElement(By.xpath("//span[text()='2025A012368']"));
            agreementSpan.click();
            basePage.pause(10000);
//            test.pass("Clicked on Agreement span: 2025A012368");
            ExtentReportListener.getExtentTest().pass("Clicked Search button");


            basePage.scrollAndJsClick(By.xpath("//a[@href='/agreements/2025A012368/latest/deliverables']//span[text()='Deliverables']"), 30);
            basePage.pause(10000);
//            test.pass("Clicked 'Deliverables' tab using JavaScript after scroll");
            ExtentReportListener.getExtentTest().pass("Clicked 'Deliverables' tab using JavaScript after scroll");


            WebElement toggleButton = basePage.waitForElement(By.xpath("//button[@type='button' and @aria-label='Expand/collapse' and contains(@class, 'toggle-button')]"));
            toggleButton.click();
            basePage.pause(10000);
//            test.pass("Clicked the expand/collapse toggle button");
            ExtentReportListener.getExtentTest().pass("Clicked the expand/collapse toggle button");



            // User will click the 'Testing 03' link under E-Sign section
            WebElement eSignTesting03Link = basePage.waitForElement(By.xpath("//a[@href='/agreements/2025A012368/latest/deliverables/1113382']//span[text()='Test']"));
            eSignTesting03Link.click();
            basePage.pause(10000);
//            test.pass("Clicked 'Test' link under E-Sign section");
            ExtentReportListener.getExtentTest().pass("Clicked 'Test' link under E-Sign section");


            // User will click the Adobe integration icon to start the e-sign process
            WebElement adobeIcon = basePage.waitForElement(By.xpath("//img[@alt='adobe-icon']"));
            adobeIcon.click();
            basePage.pause(10000);
//            test.pass("Clicked Adobe integration icon");
            ExtentReportListener.getExtentTest().pass("Clicked Adobe integration icon");

//            // User will wait after clicking the Adobe icon
//            Thread.sleep(10000);

            // User will upload the agreement PDF file via file input
            String AgreementFileName = System.getProperty("user.dir")+"/Test_Data/Agreement Info 2025_03.pdf";
            WebElement fileInput =basePage.waitForPresence(By.xpath("//input[@type='file' and @accept='application/pdf']"));
            fileInput.sendKeys(AgreementFileName);
            basePage.pause(10000);
//            test.pass("Successfully uploaded 'Agreement Info 2025.pdf'");
            ExtentReportListener.getExtentTest().pass("Successfully uploaded 'Agreement Info 2025.pdf'");



            // User will click the 'Add Recipient' button
            WebElement addRecipientButton = basePage.waitForElement(By.xpath("//button[text()='Add Recipient']"));
            addRecipientButton.click();
            basePage.pause(10000);
//            test.pass("Clicked 'Add Recipient' button");
            ExtentReportListener.getExtentTest().pass("Clicked 'Add Recipient' button");



            // User will enter the recipient email in the input field
            WebElement fullWidthInput =  basePage.waitForElement(By.xpath("//span[text()='Email']/following::input[@type='text'][1]"));
            fullWidthInput.sendKeys("Sankar.Venkatesan@technossus.com");
            basePage.pause(10000);
//            test.pass("Entered value in recipient input field");
            ExtentReportListener.getExtentTest().pass("Entered value in recipient input field");


//            // User will click the 'Preview Document' button
//            WebElement previewButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Preview Document']")));
//            previewButton.click();
//            test.pass("Clicked 'Preview Document' button");


            // User will click the 'Preview Document' button inside 'add-recipients-section'
            WebElement previewButton = basePage.waitForElement(By.xpath("//div[contains(@class, 'add-recipients-section')]//button[text()='Preview Document']"));
            previewButton.click();
            basePage.pause(10000);
//            test.pass("Clicked 'Preview Document' button inside 'add-recipients-section'");
            ExtentReportListener.getExtentTest().pass("Clicked 'Preview Document' button inside 'add-recipients-section'");

            //Uncomment the below lines if Once the bug is fixed


//            // User will wait after previewing the document
//            Thread.sleep(50000);
//
//            driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='sign-in-iframe']")));
//            test.pass("Successfully Switched to iframe");
//
//            System.out.println("Successfully Switched to iframe");
//
//            WebElement sourceEle  = driver.findElement(By.xpath("//div[@data-testid='menu-item-signature-form-field']//button//span"));
//            WebElement targetEle= driver.findElement(By.xpath("//div[@data-testid='overlay-drop-target']"));
//
//            System.out.println("source =>"+sourceEle.getText());
//
//            Actions actions = new Actions(driver);
//            actions.dragAndDrop(sourceEle,targetEle).build().perform();
//
//            Thread.sleep(30000);
//
//            System.out.println("drag and drop was successfully");
//            test.pass("drag and drop was successfully");
//
//
//            // User will wait after clicking E-signature field
//            Thread.sleep(10000);
//
//            // Step 1: Ensure you're back to default content (especially if you used switchTo().frame earlier)
//            driver.switchTo().defaultContent();
//            test.info("Switched to default content");
//
//            // Step 2: Locate the scrollable modal div using XPath
//            WebElement scrollableDiv = new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'modal-content-wrapper')]")));
//            test.pass("Located scrollable modal-content-wrapper");
//
//            // Step 3: Perform scroll using JavaScript
//            JavascriptExecutor js = (JavascriptExecutor) driver;
//            js.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight;", scrollableDiv);
//            test.pass("Successfully scrolled the modal-content-wrapper");
//
//            // User will wait until user is scroll down
//            Thread.sleep(10000);
//
//
//            driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='sign-in-iframe']")));
//            test.pass("Successfully Switched to iframe");
//
//            // User will wait until user is scroll down
//            Thread.sleep(10000);
//
//
//            // User will click the 'Send' button to send the document for signature
//            WebElement sendButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Send' and @class='ntVziG_spectrum-Button-label']")));
//            sendButton.click();
//            test.pass("Clicked 'Send' button");
//
//            // User will wait after clicking send button
//            Thread.sleep(20000);
//
//
//            driver.switchTo().defaultContent();
//            test.pass("Switched back to default content");



//            Thread.sleep(10000);

            // User will click the 'Status' tab to verify the current document status
            WebElement statusTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Status']")));
            statusTab.click();
            basePage.pause(10000);
//            test.pass("Clicked 'Status' tab");
            ExtentReportListener.getExtentTest().pass("Clicked 'Status' tab");

//            // User will wait after navigating to the status tab
//            Thread.sleep(10000);

        } catch (Exception e) {
            // User will capture and log any exceptions that occur during the test
            ExtentReportListener.getExtentTest().fail("Test failed due to exception: " + e.getMessage());
        }
    }

    @AfterMethod
    public void tearDown() {
        // User will close the browser instance after test execution is complete
//        if (driver != null) {
//            driver.quit();
//        }

        // Quit the WebDriver instance managed by DriverManager
        DriverManager.quitDriver();

        // User will record browser closure in the test report
//        test.info("Browser was successfully closed.");
        ExtentReportListener.getExtentTest().info("Browser was successfully closed.");

    }

//    @AfterSuite
//    public void flushExtentReport() {
//        // User will flush all the test information and generate the final HTML report
//        extent.flush();
//    }
}
