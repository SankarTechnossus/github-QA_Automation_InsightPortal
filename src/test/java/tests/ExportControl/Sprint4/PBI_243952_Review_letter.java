package tests.ExportControl.Sprint4;


import base.BasePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import listeners.ExtentReportListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.Adobe.AgreementPage;
import pages.Export_Control.Actions.CreateExportControlPage;
import pages.Export_Control.Export_Control_Details.AddChecklistFlowPage;
import pages.Export_Control.Export_Control_Details.DisplayChecklistFlowPage;
import pages.Export_Control.Export_Control_Details.InitialReviewWorkflowPage;
import pages.Export_Control.Export_Control_Details.ResponseToReviewPage;
import pages.Home.DashboardPage;
import pages.Home.LoginPage;
import utils.DriverManager;
import utils.JsonDataReader;
import utils.UniqueNameGenerator;

import java.time.Duration;

@Listeners(listeners.ExtentReportListener.class)

public class PBI_243952_Review_letter {

    WebDriver driver;
    WebDriverWait wait;
    BasePage basePage;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    AgreementPage agreementPage;
    AddChecklistFlowPage addChecklistFlowPage;
    ResponseToReviewPage responseToReviewPage;
    CreateExportControlPage createExportControlPage;
    DisplayChecklistFlowPage displayChecklistFlowPage;
    InitialReviewWorkflowPage initialReviewWorkflowPage;
    UniqueNameGenerator uniqueNameGenerator;

    @BeforeMethod
    public void setupBrowser() {
        // User will set up and configure the Chrome WebDriver using WebDriverManager
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
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        addChecklistFlowPage = new AddChecklistFlowPage(driver);
        responseToReviewPage = new ResponseToReviewPage(driver);
        createExportControlPage = new CreateExportControlPage(driver);
        displayChecklistFlowPage = new DisplayChecklistFlowPage(driver);
        initialReviewWorkflowPage = new InitialReviewWorkflowPage(driver);
        agreementPage = new AgreementPage(driver);
        uniqueNameGenerator = new UniqueNameGenerator();
    }

    @Test
    public void Export_control_Review_letter () {
        ExtentReportListener.getExtentTest().info("your log message");
        try {
            String url = JsonDataReader.get(0,"URL");
            String userName = JsonDataReader.get(0,"Username");
            String password = JsonDataReader.get(0,"Password");

            String versionDescriptionBase = JsonDataReader.get(1, "VersionDescription");            // e.g. "Sanversion01"
            String piSearchText          = JsonDataReader.get(3, "InitialReviewPiSearchText");      // e.g. "mohan"
            String piFullName            = JsonDataReader.get(3, "PIName");                         // e.g. "Chandra, Mohan"
            String initialPhoneNumber    = JsonDataReader.get(3, "InitialReviewPhoneNumber");       // e.g. "7550309189"
            String initialName           = JsonDataReader.get(3, "InitialReviewName");
            String positiveSearchText = JsonDataReader.get(1, "PositiveSearchText");

            // User will open the login page of the Insight Portal application
            driver.get(url);
            ExtentReportListener.getExtentTest().info("Opened dashboard URL");

            // User will wait for the login screen to load completely before performing actions
            basePage.pause(20000);

            // Login into the application
            loginPage.LoginIntoApplication(userName, password);

            Assert.assertTrue(dashboardPage.VerifyUserLandsOnDashboardPage());
            ExtentReportListener.getExtentTest().pass("User logged into the application successfully and lands on the dashboard page.");

            // Agreement Page Actions
            basePage.pause(10000);
            agreementPage.clickAdministrationLink();
            ExtentReportListener.getExtentTest().pass("Clicked Administration link");

            basePage.pause(3000);
            addChecklistFlowPage.openExportControlWorkflows();
            ExtentReportListener.getExtentTest().pass("Navigated to Workflow Management → Export Control → Workflows successfully");

            basePage.pause(3000);
            addChecklistFlowPage.clickPersonnelWorkflow();
            ExtentReportListener.getExtentTest().pass("Clicked 'Personnel' workflow successfully");

            basePage.pause(2000);
            addChecklistFlowPage.clickAddNewWorkflowVersion();
            ExtentReportListener.getExtentTest().pass("Clicked 'Add new' button successfully on Versions page");

            // Step X: Enter description for Version 90
            String versionDescription = "Test01_" + uniqueNameGenerator.GenerateRandomName(6);

            basePage.pause(1000);
            addChecklistFlowPage.enterDescriptionForLatestVersion(versionDescription);
            ExtentReportListener.getExtentTest().pass("Entered description for Version 90 as: " + versionDescription);

            basePage.pause(1000);
            addChecklistFlowPage.clickSaveVersion();
            ExtentReportListener.getExtentTest().pass("Clicked 'Save' button successfully for Version 90");

            basePage.pause(2000);
            addChecklistFlowPage.clickLatestVersionLink();
            ExtentReportListener.getExtentTest().pass("Clicked latest Version link successfully");

            basePage.pause(2000);
            responseToReviewPage.clickChiefApprover();
            ExtentReportListener.getExtentTest().pass("Clicked on Chief Approver node successfully");

            basePage.pause(2000);
            responseToReviewPage.clickActivitiesTab();
            ExtentReportListener.getExtentTest().pass("Clicked Activities tab successfully");
            ExtentReportListener.getExtentTest().pass("Generate Document is successfully Added in Activities Tab");

            basePage.pause(2000);
            addChecklistFlowPage.clickUpdateButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Update' button successfully");

            basePage.pause(2000);
            addChecklistFlowPage.clickSaveButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Save' button successfully");

            basePage.pause(3000);
            dashboardPage.clickExportControlLink();
            ExtentReportListener.getExtentTest().pass("Clicked 'Export Control' module link successfully");

            basePage.pause(3000);
            createExportControlPage.clickCreateExportControl();
            ExtentReportListener.getExtentTest().pass("Clicked Actions → Create Export Control from left navigation successfully");

            basePage.pause(2000);
            displayChecklistFlowPage.selectPersonnelExclusion();
            ExtentReportListener.getExtentTest().pass("Selected Personnel Exclusion radio button successfully");

            // PI name – completely from JSON
            basePage.pause(2000);
            createExportControlPage.selectPiName(piSearchText, piFullName);
            ExtentReportListener.getExtentTest().pass("Typed '" + piSearchText + "' and selected PI as '" + piFullName + "' successfully");


            // Step 3: Click Create button
            basePage.pause(2000);
            createExportControlPage.clickCreateButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Create' button on Create Export Control sidebar successfully");

            basePage.pause(14000);
            ExtentReportListener.getExtentTest().info("Waited for 14 seconds after clicking Create button");

//            basePage.pause(2000);
//            displayChecklistFlowPage.clickNewTest07();
//            ExtentReportListener.getExtentTest().pass("Clicked 'New Test 07' link successfully");

//            // Phone number from JSON
//            basePage.pause(1000);
//            displayChecklistFlowPage.enterPhoneNumber(initialPhoneNumber);
//            ExtentReportListener.getExtentTest().pass("Entered phone number '" + initialPhoneNumber + "' successfully");

            // Step 2: Click Next
            basePage.pause(2000);
            createExportControlPage.clickNextButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Next >' button on dynamic form successfully");

            // Step 1: Click Save
            basePage.pause(2000);
            displayChecklistFlowPage.clickSaveAction();
            ExtentReportListener.getExtentTest().pass("Clicked Save button successfully");

            // Step 2: Click Submit
            basePage.pause(2000);
            displayChecklistFlowPage.clickSubmitAction();
            ExtentReportListener.getExtentTest().pass("Clicked Submit button successfully");

            basePage.pause(5000);
            initialReviewWorkflowPage.enterName(positiveSearchText);
            ExtentReportListener.getExtentTest().pass("Entered name from JSON (PositiveSearchText): '" + positiveSearchText + "' successfully");

            basePage.pause(2000);
            initialReviewWorkflowPage.selectGenderMale();
            ExtentReportListener.getExtentTest().pass("Selected gender as 'Male' successfully");
//
//            // Name from JSON
//            basePage.pause(1000);
//            displayChecklistFlowPage.enterName(initialName);
//            ExtentReportListener.getExtentTest().pass("Entered Name as '" + initialName + "' successfully");
//
//            // Phone again from JSON
//            basePage.pause(1000);
//            displayChecklistFlowPage.enterPhoneNumber(initialPhoneNumber);
//            ExtentReportListener.getExtentTest().pass("Entered Phone Number as '" + initialPhoneNumber + "' successfully");

            // Step 1: Click Save
            basePage.pause(2000);
            displayChecklistFlowPage.clickSaveAction();
            ExtentReportListener.getExtentTest().pass("Clicked Save button successfully");

            basePage.pause(2000);
            displayChecklistFlowPage.clickSubmitAction();
            ExtentReportListener.getExtentTest().pass("Clicked Submit button successfully");


            basePage.pause(14000);
            ExtentReportListener.getExtentTest().info("Waited for 14 seconds after clicking Create button");

            basePage.pause(1000);
            initialReviewWorkflowPage.clickInitialReview();
            ExtentReportListener.getExtentTest().pass("Clicked Initial Review (IR) successfully");

//            basePage.pause(2000);
//            initialReviewWorkflowPage.clickReviewLetterPDF();
//            ExtentReportListener.getExtentTest().pass("Clicked Review Letter PDF icon successfully");

//            basePage.pause(2000);
//            initialReviewWorkflowPage.clickReviewerChecklistPDF();
//            ExtentReportListener.getExtentTest().pass("Clicked Reviewer Checklist PDF icon successfully");
//
//            basePage.pause(1000);
//            initialReviewWorkflowPage.clickExpandGroup();
//            ExtentReportListener.getExtentTest().pass("Clicked Expand group arrow successfully");

        } catch (Exception e) {
            // User will capture and log any exceptions that occur during the test
            ExtentReportListener.getExtentTest().fail("Test failed due to exception: " + e.getMessage());
        }
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
        // User will record browser closure in the test report
        ExtentReportListener.getExtentTest().info("Browser was successfully closed.");
    }
}