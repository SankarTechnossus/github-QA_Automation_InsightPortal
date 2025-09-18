package tests.AdobeEsign.Sprint4;

import base.BasePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import listeners.ExtentReportListener;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.Adobe_Deliverables_page;
import pages.AgreementPage;
import pages.LoginPage;
import utils.DriverManager;

import java.time.Duration;


@Listeners(listeners.ExtentReportListener.class)
public class AdobeE_sign_Flow {

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
            driver.get("https://hollywood-insight4.partners.org/");
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
        //         *********______Alternative_Agreement number____**********
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


            basePage.pause(3000);
            agreementPage.clickAddNewDeliverable();
            ExtentReportListener.getExtentTest().pass("Clicked 'Add New Deliverable' button successfully");



            Adobe_Deliverables_page deliverablepage = new Adobe_Deliverables_page(driver);

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


            basePage.pause(3000);
            deliverablepage.clickDeliverableByEnteredName(enteredName01);
            ExtentReportListener.getExtentTest().pass("Opened deliverable link: " + enteredName01);

            // Click Adobe icon
            agreementPage.clickAdobeIcon();
            basePage.pause(5000);
            ExtentReportListener.getExtentTest().pass("Clicked Adobe integration icon");


            String agreementFileName01 = System.getProperty("user.dir") + "/Test_Data/Agreement Info 2025_03.pdf";
            agreementPage.uploadAgreementPdf(agreementFileName01);
            basePage.pause(5000); // Optional, for stability
            ExtentReportListener.getExtentTest().pass("Successfully uploaded 'Agreement Info 2025_03.pdf'");



            basePage.pause(1000);
            deliverablepage.selectReminderEveryDay();
            ExtentReportListener.getExtentTest().pass("Selected Reminders frequency: Every day");

            Adobe_Deliverables_page deliverableoage02 =new Adobe_Deliverables_page (driver);

            basePage.pause(5000);
            deliverableoage02.clickDeleteIconemail();
            ExtentReportListener.getExtentTest().pass("Clicked Delete icon successfully");

            basePage.pause(5000);
            deliverableoage02.clickCancelButtonfrom();
            ExtentReportListener.getExtentTest().pass("Clicked 'Cancel' button successfully");

            basePage.pause(5000);
            deliverableoage02.clickDeleteIcon();
            ExtentReportListener.getExtentTest().pass("Clicked Delete icon successfully");

            basePage.pause(5000);
            deliverableoage02.clickCloseButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Close' button successfully");



            // Click Adobe icon
            agreementPage.clickAdobeIcon();
            basePage.pause(5000);
            ExtentReportListener.getExtentTest().pass("Clicked Adobe integration icon");


            String agreementFileName = System.getProperty("user.dir") + "/Test_Data/Agreement Info 2025_03.pdf";
            agreementPage.uploadAgreementPdf(agreementFileName);
            basePage.pause(5000); // Optional, for stability
            ExtentReportListener.getExtentTest().pass("Successfully uploaded 'Agreement Info 2025_03.pdf'");



            basePage.pause(1000);
            deliverablepage.selectReminderEveryDay();
            ExtentReportListener.getExtentTest().pass("Selected Reminders frequency: Every day");



            // Click 'Add Recipient'
            agreementPage.clickAddRecipient();
            basePage.pause(5000);
            ExtentReportListener.getExtentTest().pass("Clicked 'Add Recipient' button");


            // Enter recipient email
            agreementPage.enterRecipientEmail("Sankar.Venkatesan@technossus.com");
            basePage.pause(5000);
            ExtentReportListener.getExtentTest().pass("Entered value in recipient input field");

            // Click Preview button
            agreementPage.clickPreviewButton();
            basePage.pause(10000);
            basePage.switchToFrame(By.xpath("//iframe[@class='sign-in-iframe']"), 20);

            basePage.waitAdobeFormToBeVisible();
            ExtentReportListener.getExtentTest().pass("Clicked 'Preview' button inside 'add-recipients-section'");

            basePage.pause(10000);
            ExtentReportListener.getExtentTest().pass("Successfully Switched to iframe");

            basePage.dragAndDrop(
                    By.xpath("//div[@data-testid='menu-item-signature-form-field']//button//span"),
                    By.xpath("//div[@data-testid='overlay-drop-target']"),
                    20);
            basePage.pause(5000);
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



            basePage.pause(2000);
            deliverableoage02.clickOnSendButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Send' button successfully");


            basePage.switchToDefaultContent();
            ExtentReportListener.getExtentTest().info("Switched to default content");


            basePage.pause(5000);
            deliverableoage02.scrollUpInPreviewModal();
            ExtentReportListener.getExtentTest().pass("Scrolled up inside the Preview modal successfully");



            basePage.pause(5000);
            deliverableoage02.clickDeleteIcon();
            ExtentReportListener.getExtentTest().pass("Clicked Delete icon successfully");


            basePage.pause(5000);
            deliverableoage02.clickSentForSignaturesButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Sent For Signatures' button successfully");


            basePage.pause(5000);
            deliverableoage02.clickEditRecipientButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Edit Recipient' button successfully");


            basePage.pause(5000);
            deliverableoage02.clickSaveButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Save' button successfully");



            basePage.pause(5000);
            deliverableoage02.clickEditRecipientButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Edit Recipient' button successfully");

            basePage.pause(5000);
            deliverableoage02.clickCancelButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Cancel' button successfully");


            basePage.pause(5000);
            deliverableoage02.clickEditRecipientButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Edit Recipient' button successfully");


            basePage.pause(5000);
            deliverableoage02.setRecipientEmail("abcd123@gmail.com");
            ExtentReportListener.getExtentTest().pass("Entered recipient email as 'abcd123@gmail.com' successfully");


            basePage.pause(5000);
            deliverableoage02.clickSaveButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Save' button successfully");


            basePage.pause(5000);
            deliverableoage02.clickCloseModalButton();
            ExtentReportListener.getExtentTest().pass("Closed Signature Status modal successfully");


            basePage.pause(5000);
            deliverableoage02.clickUnsignedDocDownloadButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Download' button for Unsigned Document successfully");


            basePage.pause(5000);
            deliverableoage02.clickESignOptionsMenu();
            ExtentReportListener.getExtentTest().pass("Opened E-Signature row Options menu (⋯) successfully");


            basePage.pause(5000);
            deliverableoage02.clickESignOptionsMenu();
            ExtentReportListener.getExtentTest().pass("Opened E-Signature row Options menu (⋯) successfully");




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

