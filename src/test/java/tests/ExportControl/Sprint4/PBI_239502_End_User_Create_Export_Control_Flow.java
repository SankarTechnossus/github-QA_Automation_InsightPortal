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
import pages.Export_Control.Actions.CreateExportControlPage;
import pages.Export_Control.Export_Control_Details.*;
import pages.Home.DashboardPage;
import pages.Home.LoginPage;
import pages.My_Profile.Security_Page.Organization_Level_Access;
import pages.System_Admin_Flow.SystemAdminPage;
import utils.DriverManager;
import utils.JsonDataReader;

import java.time.Duration;

@Listeners(listeners.ExtentReportListener.class)
public class PBI_239502_End_User_Create_Export_Control_Flow {

    WebDriver driver;
    WebDriverWait wait;
    BasePage basePage;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    CreateExportControlPage createExportControlPage;
    NotesPage notesPage;
    InitialReviewWorkflowPage initialReviewWorkflowPage;
    Organization_Level_Access ManagementAccessSecurityPage;
    SystemAdminPage systemAdminPage;
    DisplayChecklistFlowPage displayChecklistFlowPage;
    MenuFlow menuFlow;
    MyActionsPage myActionsPage;

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
        createExportControlPage = new CreateExportControlPage(driver);
        notesPage = new NotesPage(driver);
        initialReviewWorkflowPage = new InitialReviewWorkflowPage(driver);
        systemAdminPage = new SystemAdminPage(driver);
        displayChecklistFlowPage = new DisplayChecklistFlowPage(driver);
        menuFlow = new MenuFlow(driver);
        myActionsPage = new MyActionsPage(driver);
        ManagementAccessSecurityPage = new Organization_Level_Access(driver);
    }

    @Test
    public void PBI_239502_end_user_create_export_control_flow() {
        try
        {
            String url = JsonDataReader.get(0,"URL");
            String userName = JsonDataReader.get(0,"Username");
            String password = JsonDataReader.get(0,"Password");

            String piSearchText        = JsonDataReader.get(3, "InitialReviewPiSearchText"); // "mohan"
            String piFullName          = JsonDataReader.get(3, "PIName");                   // "Chandra, Mohan"
            String initialNoteText     = JsonDataReader.get(3, "InitialReviewNoteText");     // "Initial_Review_Done"
            String positiveSearchText = JsonDataReader.get(1, "PositiveSearchText");

            // User will open the login page of the Insight Portal application
            driver.get(url);
            ExtentReportListener.getExtentTest().info("Opened dashboard URL");

            // Login into the application
            loginPage.LoginIntoApplication(userName, password);

            Assert.assertTrue(dashboardPage.VerifyUserLandsOnDashboardPage());
            ExtentReportListener.getExtentTest().pass("User logged into the application successfully and lands on the dashboard page.");

            dashboardPage.clickExportControlLink();
            ExtentReportListener.getExtentTest().info("Clicked 'Export Control' module link successfully");

            ManagementAccessSecurityPage.clickActionsButton();
            ExtentReportListener.getExtentTest().info("Clicked 'Actions' button successfully");

            ManagementAccessSecurityPage.clickCreateExportControl();
            ExtentReportListener.getExtentTest().info("Clicked Create Export Control from left navigation successfully");
            Assert.assertTrue(ManagementAccessSecurityPage.isCreateNewExportControlHeaderDisplayed(), "'Create New Export Control Record' header is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Create New Export Control Record' header is displayed successfully");


            ManagementAccessSecurityPage.selectExportControlRequestRadioOption();
            ExtentReportListener.getExtentTest().pass("Selected 'Export Control Request' radio option successfully");
            Assert.assertTrue(ManagementAccessSecurityPage.isCreateNewExportControlHeaderDisplayed(), "'Create New Export Control Record' header is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Create New Export Control Record' header is displayed successfully");

            // Step 2: Select PI Name (type from JSON and choose PI name from JSON)
            createExportControlPage.selectPiName(piSearchText, piFullName);
            ExtentReportListener.getExtentTest().info("Typed '" + piSearchText + "' and selected PI as '" + piFullName + "' successfully");
            Assert.assertTrue(displayChecklistFlowPage.isSelectPINameDisabledDisplayed(), "'Select PI Name' disabled field is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Select PI Name' disabled field is displayed successfully");

            createExportControlPage.clickCreateButton();
            ExtentReportListener.getExtentTest().info("Clicked 'Create' button on Create Export Control sidebar successfully");

            ManagementAccessSecurityPage.selectEncryptionSourceCodeNo();
            ExtentReportListener.getExtentTest().pass("Selected 'No' for Encryption Source Code or Technology question");

            ManagementAccessSecurityPage.selectPublicationRestrictionNo();
            ExtentReportListener.getExtentTest().pass("Selected 'No' for Publication Restriction question");

            ManagementAccessSecurityPage.selectConfidentialityRequirementNo();
            ExtentReportListener.getExtentTest().pass("Selected 'No' for Confidentiality Requirement question");

            ManagementAccessSecurityPage.selectRestrictionOnForeignPersonNo();
            ExtentReportListener.getExtentTest().pass("Selected 'No' for Restriction on Foreign Person question");

            ManagementAccessSecurityPage.selectSponsorPermissionToClaimResultNo();
            ExtentReportListener.getExtentTest().pass("Selected 'No' for Sponsor Permission to Claim Result question");

            ManagementAccessSecurityPage.selectExportControlledOrITARControlledNo();
            ExtentReportListener.getExtentTest().pass("Selected 'No' for Export Controlled / ITAR Controlled question");

            ManagementAccessSecurityPage.selectTransfersControlsLicensingNo();
            ExtentReportListener.getExtentTest().pass("Selected 'No' for Transfers, Controls, and Licensing question");

            createExportControlPage.clickNext();
            ExtentReportListener.getExtentTest().pass("Clicked Next button successfully");

            ManagementAccessSecurityPage.clickConfirmSignOffCheckbox();
            ExtentReportListener.getExtentTest().pass("Clicked 'I have carefully reviewed this record and confirm my sign off' checkbox");
            Assert.assertTrue(ManagementAccessSecurityPage.VerifyConfirmSignOffCheckboxIsSelected(), "Confirm sign off checkbox is NOT selected");
            ExtentReportListener.getExtentTest().pass("Verified confirm sign off checkbox is selected successfully");

            displayChecklistFlowPage.clickSaveAction();
            ExtentReportListener.getExtentTest().info("Clicked Save button successfully");

            displayChecklistFlowPage.clickSubmitAction();
            ExtentReportListener.getExtentTest().info("Clicked Submit button successfully");

            initialReviewWorkflowPage.clickInitialReview();
            ExtentReportListener.getExtentTest().info("Clicked Initial Review (IR) successfully");

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