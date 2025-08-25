package tests.AdobeEsign.Sprint1;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import listeners.ExtentReportListener;
import org.testng.annotations.Listeners;
import pages.Adobe_Deliverables_page;
import pages.LoginPage;
import pages.AgreementPage;
import utils.DriverManager;
import java.time.Duration;

@Listeners(listeners.ExtentReportListener.class)
public class Esign_Agreement_PDF_Attachment_Flow {

    WebDriver driver;
    WebDriverWait wait;
    BasePage basePage;

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
        ExtentReportListener.getExtentTest().info("your log message");
        try {
            // User will open the login page of the Insight Portal application
            driver.get("https://sacramento-insight4.partners.org/");
            ExtentReportListener.getExtentTest().info("Opened dashboard URL");

            // User will wait for the login screen to load completely before performing actions
            basePage.pause(20000);

            // Create an instance of LoginPage
            LoginPage loginPage = new LoginPage(driver);

            // User will enter the username into the username input field
            loginPage.enterUsername("SV1179");
            basePage.pause(2000);
            ExtentReportListener.getExtentTest().pass("Entered username");

            // User will click the 'Next' button to proceed to the password entry screen
            loginPage.clickNext();
            basePage.pause(2000);
            ExtentReportListener.getExtentTest().pass("Clicked Next");

            // User will input the user's password into the password field
            loginPage.enterPassword("Devinivetha@1930");
            basePage.pause(2000);
            ExtentReportListener.getExtentTest().pass("Entered password");

            // User will click the 'Verify' button to authenticate the user
            loginPage.clickVerify();
            ExtentReportListener.getExtentTest().pass("Clicked Verify");

            // Optional: pause if any post-login actions needed
            basePage.pause(10000);

            // Agreement Page Actions
            AgreementPage agreementPage = new AgreementPage(driver);

            // Wait and click the 'Agreements' link from the sidebar
            agreementPage.clickAgreementsLink();
            basePage.pause(5000);
            ExtentReportListener.getExtentTest().pass("Clicked 'Agreements' link from sidebar");

            // Enter Agreement Number ****** This record has access issue ***********
             // agreementPage.enterAgreementNumber("2025A012368");

            //*********______Alternative_Agreement number____**********
//            agreementPage.enterAgreementNumber("2025A015018");
//            basePage.pause(5000);
//            ExtentReportListener.getExtentTest().pass("Entered Agreement Number: 2025A015018");



//            basePage.pause(3000);
//            agreementPage.clickAdvanced();
//            ExtentReportListener.getExtentTest().pass("Clicked 'Advanced' button successfully");
//
//
//
//            basePage.pause(5000);
//            agreementPage.clickCloseButton();
//            ExtentReportListener.getExtentTest().pass("Clicked on 'Close' button successfully");


//            basePage.pause(5000);
//            agreementPage.clickClearSelectionsButton();
//            ExtentReportListener.getExtentTest().pass("Clicked on 'Clear Selections' button successfully");


            agreementPage.enterAgreementNumber("2025A015018");
            basePage.pause(5000);
            ExtentReportListener.getExtentTest().pass("Entered Agreement Number: 2025A015018");


            // Click Search
            agreementPage.clickSearch();
            basePage.pause(5000);
            ExtentReportListener.getExtentTest().pass("Clicked Search button");

            // Click on Agreement Span
            agreementPage.clickAgreementSpan();
            basePage.pause(5000);
            ExtentReportListener.getExtentTest().pass("Clicked Agreement span");

            // Click Deliverables Tab
            agreementPage.clickDeliverablesTab();
            basePage.pause(5000);
            ExtentReportListener.getExtentTest().pass("Clicked 'Deliverables' tab using JavaScript after scroll");


//            basePage.pause(3000);
//            agreementPage.clickAddNewDeliverable();
//            ExtentReportListener.getExtentTest().pass("Clicked 'Add New Deliverable' button successfully");



            Adobe_Deliverables_page deliverablepage = new Adobe_Deliverables_page(driver);


//            basePage.pause(1000);

//                // 1) Set text with unique generator
//            String enteredName = deliverablepage.typeDeliverableNameUnique("Testsan01");
//            ExtentReportListener.getExtentTest().pass("Entered Deliverable Name: " + enteredName);
//
//            // 2) Deliverable category = "CTO - Agreement"
//            basePage.pause(1000);
//            deliverablepage.selectDeliverableCategory("CTO - Agreement");
//            ExtentReportListener.getExtentTest().pass("Selected Deliverable Category: CTO - Agreement");
//
//            // 3) Cancel button
//            basePage.pause(1000);
//            deliverablepage.clickCancelOnOverlay();
//            ExtentReportListener.getExtentTest().pass("Clicked Cancel on Add New Deliverable overlay");


            basePage.pause(3000);
            agreementPage.clickAddNewDeliverable();
            ExtentReportListener.getExtentTest().pass("Clicked 'Add New Deliverable' button successfully");


            // 1) Set text with unique generator
            basePage.pause(1000);
            String enteredName01 = deliverablepage.typeDeliverableNameUnique("Testsan01");
            ExtentReportListener.getExtentTest().pass("Entered Deliverable Name: " + enteredName01);

            // 2) Deliverable category = "CTO - Agreement"
            basePage.pause(1000);
            deliverablepage.selectDeliverableCategory("CTO - Agreement");
            ExtentReportListener.getExtentTest().pass("Selected Deliverable Category: CTO - Agreement");

            basePage.pause(1000);
            deliverablepage.clickSubmitOnOverlay();
            ExtentReportListener.getExtentTest().pass("Clicked 'Submit' on Add New Deliverable overlay");




//            basePage.pause(3000);
//            deliverablepage.typeDeliverablesSearch("Test");
//            ExtentReportListener.getExtentTest().pass("Typed 'Test' in Deliverables search box");
//
//
//            // 4) Search button (top)
//            basePage.pause(3000);
//            deliverablepage.clickTopSearch();
//            ExtentReportListener.getExtentTest().pass("Clicked top 'Search' button on Deliverables page");
//
//
//            basePage.pause(1000);
//            deliverablepage.clickClearSelections();
//            ExtentReportListener.getExtentTest().pass("Clicked 'Clear Selections' successfully");
//


//            // Expand toggle button
//            basePage.pause(3000);
//            agreementPage.clickToggleButton();
//            ExtentReportListener.getExtentTest().pass("Clicked the expand/collapse toggle button");

//
//            basePage.pause(3000);
//            deliverablepage.clickClone();
//            ExtentReportListener.getExtentTest().pass("Clicked 'Clone' successfully");
//
//
//
//            basePage.pause(3000);
//            deliverablepage.tickFirstCheckbox();
//            ExtentReportListener.getExtentTest().pass("Ticked the first checkbox successfully");
//
//
//
//            basePage.pause(3000);
//            deliverablepage.clickDownloadSelected();
//            ExtentReportListener.getExtentTest().pass("Clicked 'Download Selected' successfully");
////
//
//            basePage.pause(5000);
//            deliverablepage.tickFirstCheckbox();
//            ExtentReportListener.getExtentTest().pass("Ticked the first checkbox successfully");
//
//
//
//            basePage.pause(2000);
//            deliverablepage.clickDeleteSelected();
//            ExtentReportListener.getExtentTest().pass("Clicked 'Delete Selected' successfully");
//
//
//
//            basePage.pause(3000);
//            deliverablepage.acceptDeleteAlert();
//            ExtentReportListener.getExtentTest().pass("Accepted delete confirmation alert successfully");



            basePage.pause(3000);
            deliverablepage.clickDeliverableByEnteredName(enteredName01);
            ExtentReportListener.getExtentTest().pass("Opened deliverable link: " + enteredName01);


            // Click Adobe icon
            agreementPage.clickAdobeIcon();
            basePage.pause(5000);
            ExtentReportListener.getExtentTest().pass("Clicked Adobe integration icon");



            String agreementFileName = System.getProperty("user.dir") + "/Test_Data/Agreement Info 2025_03.pdf";
            agreementPage.uploadAgreementPdf(agreementFileName);
            basePage.pause(5000); // Optional, for stability
            ExtentReportListener.getExtentTest().pass("Successfully uploaded 'Agreement Info 2025_03.pdf'");


//             Upload Agreement PDF
//            String AgreementFileName = System.getProperty("user.dir") + "/Test_Data/Agreement Info 2025_03.pdf";
//            agreementPage.uploadAgreementPdf(AgreementFileName);
//            basePage.pause(5000);
//            ExtentReportListener.getExtentTest().pass("Successfully uploaded 'Agreement Info 2025.pdf'");

//            String filePath = System.getProperty("user.dir") + "/testfiles/AgreementInfo_2025_03.pdf";
//            agreementPage.uploadPDFToAgreement(filePath);
//            agreementPage.waitForUploadedFileToAppear();

            basePage.pause(1000);
            deliverablepage.selectReminderEveryDay();
            ExtentReportListener.getExtentTest().pass("Selected Reminders frequency: Every day");



//            basePage.pause(5000);
//            agreementPage.selectReminderAsEveryDay();
//            ExtentReportListener.getExtentTest().pass("Selected 'Every day' reminder option");


            // Click 'Add Recipient'
            agreementPage.clickAddRecipient();
            basePage.pause(5000);
            ExtentReportListener.getExtentTest().pass("Clicked 'Add Recipient' button");

            // Enter recipient email
            agreementPage.enterRecipientEmail("Sankar.Venkatesan@technossus.com");
            basePage.pause(5000);
            ExtentReportListener.getExtentTest().pass("Entered value in recipient input field");


            basePage.pause(1000);
            deliverablepage.clickFirstRecipientDelete();
            ExtentReportListener.getExtentTest().pass("Clicked recipient delete button successfully");

            // Click 'Add Recipient'
            agreementPage.clickAddRecipient();
            basePage.pause(5000);
            ExtentReportListener.getExtentTest().pass("Clicked 'Add Recipient' button");

            // Enter recipient email
            agreementPage.enterRecipientEmail("Sankar.Venkatesan@technossus.com");
            basePage.pause(5000);
            ExtentReportListener.getExtentTest().pass("Entered value in recipient input field");






            //***************   Flow stops ***********



            // Click Preview button
            agreementPage.clickPreviewButton();
            basePage.pause(10000);
            ExtentReportListener.getExtentTest().pass("Clicked 'Preview' button inside 'add-recipients-section'");







//            driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='sign-in-iframe']")));
//            basePage.pause(10000);
//            ExtentReportListener.getExtentTest().pass("Successfully Switched to iframe");
//
//
//            WebElement sourceEle  = driver.findElement(By.xpath("//div[@data-testid='menu-item-signature-form-field']//button//span"));
//            WebElement targetEle= driver.findElement(By.xpath("//div[@data-testid='overlay-drop-target']"));
//
//
//            Actions actions = new Actions(driver);
//            actions.dragAndDrop(sourceEle,targetEle).build().perform();
//
//            basePage.pause(30000);
//            ExtentReportListener.getExtentTest().pass("drag and drop was successfully");
//            // User will wait after clicking E-signature field
//            basePage.pause(10000);
//
//            // Step 1: Ensure you're back to default content (especially if you used switchTo().frame earlier)
//            driver.switchTo().defaultContent();
//            ExtentReportListener.getExtentTest().info("Switched to default content");

            // ********* End here **********

            basePage.switchToFrame(By.xpath("//iframe[@class='sign-in-iframe']"), 15);
            basePage.pause(10000);
            ExtentReportListener.getExtentTest().pass("Successfully Switched to iframe");

            basePage.dragAndDrop(
                    By.xpath("//div[@data-testid='menu-item-signature-form-field']//button//span"),
                    By.xpath("//div[@data-testid='overlay-drop-target']"),
                    15);
            basePage.pause(30000);
            ExtentReportListener.getExtentTest().pass("Drag and drop was successful");

            basePage.switchToDefaultContent();
            ExtentReportListener.getExtentTest().info("Switched to default content");


            // Step 1: Scroll the modal
            By modalLocator = By.xpath("//div[contains(@class, 'modal-content-wrapper')]");
            basePage.scrollToBottomOfModal(modalLocator, 20);
            ExtentReportListener.getExtentTest().pass("Successfully scrolled the modal-content-wrapper");

            basePage.pause(10000);

            // Step 2: Switch to iframe
            By iframeLocator = By.xpath("//iframe[@class='sign-in-iframe']");
            basePage.switchToFrame(iframeLocator, 20);
            ExtentReportListener.getExtentTest().pass("Successfully Switched to iframe");

            basePage.pause(10000);

            // Step 3: Click the 'Send' button
            By sendButton = By.xpath("//span[text()='Send' and @class='ntVziG_spectrum-Button-label']");
            basePage.clickElement(sendButton, 20);
            ExtentReportListener.getExtentTest().pass("Clicked 'Send' button");

            basePage.pause(20000);

            // Step 4: Switch back to default content
            basePage.switchToDefaultContent();
            ExtentReportListener.getExtentTest().pass("Switched back to default content");

            // Step 5: Click the 'Status' tab
            By statusTab = By.xpath("//a[text()='Status']");
            basePage.clickElement(statusTab, 20);
            basePage.pause(10000);
            ExtentReportListener.getExtentTest().pass("Clicked 'Status' tab");







            // *********(Please ignore the below commented lines) **********











//            // Step 2: Locate the scrollable modal div using XPath
//            WebElement scrollableDiv = new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'modal-content-wrapper')]")));
//            ExtentReportListener.getExtentTest().pass("Located scrollable modal-content-wrapper");
//
//            // Step 3: Perform scroll using JavaScript
//            JavascriptExecutor js = (JavascriptExecutor) driver;
//            js.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight;", scrollableDiv);
//            ExtentReportListener.getExtentTest().pass("Successfully scrolled the modal-content-wrapper");
//
//            // User will wait until user is scroll down
//            basePage.pause(10000);
//
//
//            driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='sign-in-iframe']")));
//            ExtentReportListener.getExtentTest().pass("Successfully Switched to iframe");
//
//            // User will wait until user is scroll down
//            basePage.pause(10000);
//
//
//            // User will click the 'Send' button to send the document for signature
//            WebElement sendButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Send' and @class='ntVziG_spectrum-Button-label']")));
//            sendButton.click();
//            ExtentReportListener.getExtentTest().pass("Clicked 'Send' button");
//
//            // User will wait after clicking send button
//            basePage.pause(20000);
//
//
//            driver.switchTo().defaultContent();
//            ExtentReportListener.getExtentTest().pass("Switched back to default content");
//
//            // User will click the 'Status' tab to verify the current document status
//            WebElement statusTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Status']")));
//            statusTab.click();
//            basePage.pause(10000);
//            ExtentReportListener.getExtentTest().pass("Clicked 'Status' tab");
            // User will wait after navigating to the status tab

            // ********* End here **********

        } catch (Exception e) {
            // User will capture and log any exceptions that occur during the test
            ExtentReportListener.getExtentTest().fail("Test failed due to exception: " + e.getMessage());
        }
    }

    @AfterMethod
    public void tearDown() {

//        DriverManager.quitDriver();
        // User will record browser closure in the test report
        ExtentReportListener.getExtentTest().info("Browser was successfully closed.");

    }

}
