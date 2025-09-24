package tests.Exportcontrol.Sprint2;


import base.BasePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import listeners.ExtentReportListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.AgreementPage;
import pages.Exportcontrol_WorkflowManagement_AncillaryWorkflows_Page;
import pages.LoginPage;
import utils.DriverManager;

import java.time.Duration;


@Listeners(listeners.ExtentReportListener.class)
public class Exportcontrol_WorkflowManagement_Ancillary_workflow_flow {

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
    public void Exportcontrol_WorkflowManagement_Ancillary_workflow_Test () {
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
            ExtentReportListener.getExtentTest().pass("Entered username");

            // User will click the 'Next' button to proceed to the password entry screen
            loginPage.clickNext();
            ExtentReportListener.getExtentTest().pass("Clicked Next");

            // User will input the user's password into the password field
            loginPage.enterPassword("Devinivetha@1930");
            ExtentReportListener.getExtentTest().pass("Entered password");

            // User will click the 'Verify' button to authenticate the user
            loginPage.clickVerify();
            ExtentReportListener.getExtentTest().pass("Clicked Verify");

            // Optional: pause if any post-login actions needed
            basePage.pause(20000);

            // Agreement Page Actions
            AgreementPage agreementPage = new AgreementPage(driver);

            basePage.pause(10000);
            agreementPage.clickAdministrationLink();
            ExtentReportListener.getExtentTest().pass("Clicked Administration link");


            //Ancillary workflow
            Exportcontrol_WorkflowManagement_AncillaryWorkflows_Page ancillaryworkflow = new Exportcontrol_WorkflowManagement_AncillaryWorkflows_Page (driver);


            basePage.pause(2000);
            ancillaryworkflow.clickWorkflowManagement();
            ExtentReportListener.getExtentTest().pass("Opened 'Workflow Management'");


            basePage.pause(2000);
            ancillaryworkflow.clickAncillaryWorkflowsScope3();
            ExtentReportListener.getExtentTest().pass("Opened 'Ancillary Workflows' (scopeId=3, workflowType=2)");


            basePage.pause(2000);
            ancillaryworkflow.clickAddNew();
            ExtentReportListener.getExtentTest().pass("Clicked 'Add New' button");

            String uniqueName01 = "Test_" + System.currentTimeMillis(); // Timestamp-based unique name
            basePage.pause(5000);
            ancillaryworkflow.enterNameanc(uniqueName01);
            ExtentReportListener.getExtentTest().pass("Entered unique name '" + uniqueName01 + "' in the Name input field successfully");


//            basePage.pause(5000);
//            ancillaryworkflow.selectOptionFromDropdownancillary("Triggering Rule","testing");
//            ExtentReportListener.getExtentTest().pass("Selected 'Test' from Record Type dropdown successfully");



            basePage.pause(5000);
            ancillaryworkflow.selectOptionFromDropdownancillary("Email From","insighthelpdesk@partners.org");
            ExtentReportListener.getExtentTest().pass("Selected 'Test' from Record Type dropdown successfully");


            basePage.pause(5000);
            ancillaryworkflow.clickCancelButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Cancel' button successfully");

            basePage.pause(2000);
            ancillaryworkflow.clickAddNew();
            ExtentReportListener.getExtentTest().pass("Clicked 'Add New' button");


            String uniqueName02 = "Test_" + System.currentTimeMillis(); // Timestamp-based unique name
            basePage.pause(5000);
            ancillaryworkflow.enterNameanc(uniqueName01);
            ExtentReportListener.getExtentTest().pass("Entered unique name '" + uniqueName02 + "' in the Name input field successfully");


//            basePage.pause(5000);
//            ancillaryworkflow.selectOptionFromDropdownancillary("Triggering Rule","testing");
//            ExtentReportListener.getExtentTest().pass("Selected 'Test' from Record Type dropdown successfully");



            basePage.pause(5000);
            ancillaryworkflow.selectOptionFromDropdownancillary("Email From","insighthelpdesk@partners.org");
            ExtentReportListener.getExtentTest().pass("Selected 'Test' from Record Type dropdown successfully");



            basePage.pause(5000);
            ancillaryworkflow.clickSaveButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Save' button successfully");


            basePage.pause(1200);
            ancillaryworkflow.clickLastEdit();
            ExtentReportListener.getExtentTest().pass("Clicked last 'Edit' on Ancillary Workflows list");


            basePage.pause(3000);
            ancillaryworkflow.appendSanToNameanc();
            ExtentReportListener.getExtentTest().pass("Appended 'San' to Name field successfully");

            basePage.pause(3000);
            ancillaryworkflow.clickUpdateButtonanc();
            ExtentReportListener.getExtentTest().pass("Clicked 'Update' button successfully");





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
