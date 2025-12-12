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
import pages.Export_Control.Export_Control_Details.*;
import pages.Home.DashboardPage;
import pages.Home.LoginPage;
import pages.System_Admin_Flow.SystemAdminPage;
import utils.DriverManager;
import utils.JsonDataReader;

import java.time.Duration;

@Listeners(listeners.ExtentReportListener.class)

public class PBI_245943_Amend_Export_Control {

    WebDriver driver;
    WebDriverWait wait;
    BasePage basePage;
    NotesPage notesPage;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    AmendExportControlPage amendExportControlPage;
    MenuFlow menuFlow;
    SystemAdminPage systemAdminPage;
    ResponseToReviewPage responseToReviewPage;
    AddChecklistFlowPage addChecklistFlowPage;
    AgreementPage agreementPage;
    CreateExportControlPage createExportControlPage;
    DisplayChecklistFlowPage displayChecklistFlowPage;
    InitialReviewWorkflowPage initialReviewWorkflowPage;
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
        amendExportControlPage = new AmendExportControlPage(driver);
        menuFlow = new MenuFlow(driver);
        systemAdminPage = new SystemAdminPage (driver);
        responseToReviewPage =new ResponseToReviewPage (driver);
        addChecklistFlowPage = new AddChecklistFlowPage(driver);
        agreementPage = new AgreementPage(driver);
        createExportControlPage = new CreateExportControlPage(driver);
        displayChecklistFlowPage = new DisplayChecklistFlowPage (driver);
        initialReviewWorkflowPage = new InitialReviewWorkflowPage(driver);
        notesPage = new NotesPage(driver);
        myActionsPage = new MyActionsPage(driver);
    }

    @Test
    public void Amend_Export_control() {
        try
        {
            //********************_Test_Started_************
            String url = JsonDataReader.get(0, "URL");
            String userName = JsonDataReader.get(0, "Username");
            String password = JsonDataReader.get(0, "Password");

            String piSearchText      = JsonDataReader.get(3, "InitialReviewPiSearchText"); // "mohan"
            String piFullName        = JsonDataReader.get(3, "PIName");                    // "Chandra, Mohan"
            String linkText          = JsonDataReader.get(3, "InitialReviewLinkText");     // "New Test 07"
            String phoneNumber       = JsonDataReader.get(3, "InitialReviewPhoneNumber");  // "7550309189"
            String personName        = JsonDataReader.get(3, "InitialReviewName");         // "Test01"
            String amendCommentText  = JsonDataReader.get(3, "AmendCommentText");
            String positiveSearchText = JsonDataReader.get(1, "PositiveSearchText");// "Test_Auto"
            String initialNoteText     = JsonDataReader.get(3, "InitialReviewNoteText");     // "Initial_Review_Done"

            // User will open the login page of the Insight Portal application
            driver.get(url);
            ExtentReportListener.getExtentTest().info("Opened dashboard URL");

            // User will wait for the login screen to load completely before performing actions
            basePage.pause(20000);

            // Login into the application
            loginPage.LoginIntoApplication(userName, password);

            Assert.assertTrue(dashboardPage.VerifyUserLandsOnDashboardPage());
            ExtentReportListener.getExtentTest().pass("User logged into the application successfully and lands on the dashboard page.");

            dashboardPage.clickExportControlLink();
            ExtentReportListener.getExtentTest().pass("Clicked 'Export Control' module link successfully");

            createExportControlPage.clickCreateExportControl();
            ExtentReportListener.getExtentTest().pass("Clicked Actions → Create Export Control from left navigation successfully");
            Assert.assertTrue(createExportControlPage.isCreateExportControlHeaderDisplayed(), "'Create Export Control' header is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Create Export Control' header is displayed successfully");

            displayChecklistFlowPage.selectPersonnelExclusion();
            ExtentReportListener.getExtentTest().pass("Selected Personnel Exclusion radio button successfully");
            Assert.assertTrue(displayChecklistFlowPage.isPersonnelExclusionRadioDisplayed(), "'Personnel Exclusion' radio button is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Personnel Exclusion' radio button is displayed");

            // Step 2: Select PI Name (type from JSON and choose PI name from JSON)
            createExportControlPage.selectPiName(piSearchText, piFullName);
            ExtentReportListener.getExtentTest().pass("Typed '" + piSearchText + "' and selected PI as '" + piFullName + "' successfully");
            Assert.assertTrue(displayChecklistFlowPage.isSelectPINameDisabledDisplayed(), "'Select PI Name' disabled field is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Select PI Name' disabled field is displayed successfully");

            createExportControlPage.clickCreateButton();
            ExtentReportListener.getExtentTest().info("Clicked 'Create' button on Create Export Control sidebar successfully");

            // Step 2: Click Submit
            displayChecklistFlowPage.clickSubmitAction();
            ExtentReportListener.getExtentTest().info("Clicked Submit button successfully");

            initialReviewWorkflowPage.enterName(positiveSearchText);
            ExtentReportListener.getExtentTest().pass("Entered name from JSON (PositiveSearchText): '" + positiveSearchText + "' successfully");
            Assert.assertTrue(initialReviewWorkflowPage.isEnterNameDisabledDisplayed(), "'Enter Name' disabled field is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Enter Name' disabled field is displayed successfully");

            initialReviewWorkflowPage.selectGenderMale();
            ExtentReportListener.getExtentTest().pass("Selected gender as 'Male' successfully");
            Assert.assertTrue(initialReviewWorkflowPage.isSelectGenderDisabledDisplayed(), "'Select Gender' disabled field is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Select Gender' disabled field is displayed successfully");

            // Step 1: Click Save
            displayChecklistFlowPage.clickSaveAction();
            ExtentReportListener.getExtentTest().pass("Clicked Save button successfully");
            Assert.assertTrue(initialReviewWorkflowPage.isPersonnelExclusionValueDisplayed(), "'Personnel Exclusion' value is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Personnel Exclusion' value is displayed successfully");

            // Step 2: Click Submit
            displayChecklistFlowPage.clickSubmitAction();
            ExtentReportListener.getExtentTest().pass("Clicked Submit button successfully");
            Assert.assertTrue(initialReviewWorkflowPage.isPersonnelExclusionValueDisplayed(), "'Personnel Exclusion' value is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Personnel Exclusion' value is displayed successfully");

            initialReviewWorkflowPage.clickInitialReview();
            ExtentReportListener.getExtentTest().info("Clicked Initial Review (IR) successfully");

            ExtentReportListener.getExtentTest().info("Waited for 5 seconds after clicking Create button");

            //Login to System Admin page

            String sysAdminUrl = JsonDataReader.get(0, "SysAdminURL");
            String sysAdminBusinessUser = JsonDataReader.get(0, "SysAdminBusinessUser");

            driver.get(sysAdminUrl);
            ExtentReportListener.getExtentTest().pass("Opened SysAdmin public URL successfully");

            loginPage.enterSysAdminBusinessUser(sysAdminBusinessUser);
            ExtentReportListener.getExtentTest().pass("Entered SysAdmin Business User: " + sysAdminBusinessUser + " successfully");

            loginPage.clickSysAdminLoginButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Login' button on SysAdmin page successfully");
            Assert.assertTrue(dashboardPage.VerifyUserLandsOnDashboardPage());
            ExtentReportListener.getExtentTest().pass("User logged into the application successfully and lands on the dashboard page.");

            dashboardPage.clickExportControlLink();
            ExtentReportListener.getExtentTest().info("Clicked 'Export Control' module link successfully");
            Assert.assertTrue(initialReviewWorkflowPage.isActionRequiredLinkDisplayed(), "'Action Required' link is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Action Required' link is displayed successfully");

            createExportControlPage.clickActionRequired();
            ExtentReportListener.getExtentTest().pass("Clicked 'Action Required' menu successfully");
            Assert.assertTrue(myActionsPage.isActionRequiredBreadcrumbDisplayed(), "'Action Required' breadcrumb is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Action Required' breadcrumb is displayed successfully");

            systemAdminPage.clickFirstRecordNumber();
            ExtentReportListener.getExtentTest().info("Clicked first Record Number in Action Required grid successfully");

            Assert.assertTrue(initialReviewWorkflowPage.isPersonnelExclusionValueDisplayed(), "'Personnel Exclusion' value is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Personnel Exclusion' value is displayed successfully");

            systemAdminPage.clickApproveButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Approve' button successfully");
            Assert.assertTrue(systemAdminPage.isStatusUnderReviewDisplayed(), "'Status: Under Review' value is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Status' value is displayed as 'Under Review' successfully");

            notesPage.clickNotesSection();
            ExtentReportListener.getExtentTest().pass("Clicked Notes section successfully");
            Assert.assertTrue(systemAdminPage.isNotesSectionTitleDisplayed(), "'Notes' section title is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Notes' section title is displayed successfully");

            notesPage.clickAddNoteButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Add Note' button successfully");
            Assert.assertTrue(systemAdminPage.isNotesSectionTitleDisplayed(), "'Notes' section title is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Notes' section title is displayed successfully");

            String recordNum = systemAdminPage.getRecordNumber();
            ExtentReportListener.getExtentTest().info("Fetched Record Number: " + recordNum);

            notesPage.enterNotesAndClickAdd(initialNoteText);
            ExtentReportListener.getExtentTest().pass("Entered '" + initialNoteText + "' in Notes and clicked 'Add' successfully");
            Assert.assertTrue(initialReviewWorkflowPage.isPersonnelExclusionValueDisplayed(), "'Personnel Exclusion' value is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Personnel Exclusion' value is displayed successfully");

            systemAdminPage.clickApproveButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Approve' button successfully");
            Assert.assertTrue(myActionsPage.isPINameDisplayed(), "'PI: Chandra, Mohan' is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'PI' value is displayed as 'Chandra, Mohan' successfully");

            systemAdminPage.clickLogout();
            ExtentReportListener.getExtentTest().info("Clicked Logout successfully");

            //User login to main dashboard

            String url1 = JsonDataReader.get(0, "URL");
            String userName1 = JsonDataReader.get(0, "Username");
            String password1 = JsonDataReader.get(0, "Password");

            // User will open the login page of the Insight Portal application
            driver.get(url1);
            ExtentReportListener.getExtentTest().info("Opened dashboard URL");

            // User will wait for the login screen to load completely before performing actions
            basePage.pause(20000);

            // Login into the application
            loginPage.LoginIntoApplication(userName1, password1);

            Assert.assertTrue(dashboardPage.VerifyUserLandsOnDashboardPage());
            ExtentReportListener.getExtentTest().pass("User logged into the application successfully and lands on the dashboard page.");

            menuFlow.clickExportControlLink();
            ExtentReportListener.getExtentTest().pass("Clicked 'Export Control' module link successfully");

            menuFlow.clickSearchLink();
            ExtentReportListener.getExtentTest().pass("Clicked 'Search' link successfully from Export Control sidebar");
            Assert.assertTrue(menuFlow.isSearchBreadcrumbDisplayed(), "'Export Control > Search' breadcrumb is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Export Control > Search' breadcrumb is displayed successfully");

            systemAdminPage.enterValueField(recordNum);
            ExtentReportListener.getExtentTest().pass("Successfully entered dynamic record number: " + recordNum);
            Assert.assertTrue(myActionsPage.isReviewerLabelDisplayed(), "Reviewer label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Reviewer' label is displayed");

            menuFlow.clickSearchButton();
            ExtentReportListener.getExtentTest().pass("Clicked Search");
            Assert.assertTrue(menuFlow.isSearchBreadcrumbDisplayed(), "'Export Control > Search' breadcrumb is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Export Control > Search' breadcrumb is displayed successfully");

            // After you search and the grid is loaded:
            systemAdminPage.clickRecordNumber(recordNum);
            ExtentReportListener.getExtentTest().pass("Clicked Record Number link in grid: " + recordNum);

            amendExportControlPage.clickInitiateAmendment();
            ExtentReportListener.getExtentTest().info("Clicked 'Initiate Amendment' button successfully");

            amendExportControlPage.clickAmendmentOkButton();
            ExtentReportListener.getExtentTest().info("Clicked 'OK' button on amendment confirmation popup successfully");

            amendExportControlPage.clickSave();
            ExtentReportListener.getExtentTest().info("Clicked 'Save' button successfully");

            amendExportControlPage.clickSubmit();
            ExtentReportListener.getExtentTest().info("Clicked 'Submit' button successfully");

            // Use same JSON name & static gender selection
            amendExportControlPage.enterYourName(personName);
            ExtentReportListener.getExtentTest().pass("Entered Name as '" + personName + "' successfully");
            Assert.assertTrue(systemAdminPage.isYourNameDisplayed(), "'Your Name' label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Your Name' label is displayed successfully");

            // Step 2: Select gender = Male
            amendExportControlPage.selectGenderMale();
            ExtentReportListener.getExtentTest().pass("Selected Gender as 'Male' successfully");
            Assert.assertTrue(systemAdminPage.isGenderDisplayed(), "'Gender' label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Gender' label is displayed successfully");

            amendExportControlPage.clickSave();
            ExtentReportListener.getExtentTest().info("Clicked 'Save' button successfully");

            amendExportControlPage.clickSubmit();
            ExtentReportListener.getExtentTest().info("Clicked 'Submit' button successfully");

            //Login to System Admin page

            String sysAdminUrl2 = JsonDataReader.get(0, "SysAdminURL");
            String sysAdminBusinessUser2 = JsonDataReader.get(0, "SysAdminBusinessUser");

            driver.get(sysAdminUrl2);
            ExtentReportListener.getExtentTest().pass("Opened SysAdmin public URL successfully");

            loginPage.enterSysAdminBusinessUser(sysAdminBusinessUser2);
            ExtentReportListener.getExtentTest().pass("Entered SysAdmin Business User: " + sysAdminBusinessUser2 + " successfully");

            loginPage.clickSysAdminLoginButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Login' button on SysAdmin page successfully");

            dashboardPage.clickExportControlLink();
            ExtentReportListener.getExtentTest().info("Clicked 'Export Control' module link successfully");

            menuFlow.clickSearchLink();
            ExtentReportListener.getExtentTest().info("Clicked 'Search' link successfully from Export Control sidebar");

            systemAdminPage.enterValueField(recordNum);
            ExtentReportListener.getExtentTest().pass("Successfully entered dynamic record number: " + recordNum);
            Assert.assertTrue(myActionsPage.isReviewerLabelDisplayed(), "Reviewer label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Reviewer' label is displayed");

            menuFlow.clickSearchButton();
            ExtentReportListener.getExtentTest().pass("Clicked Search");
            Assert.assertTrue(myActionsPage.isReviewerLabelDisplayed(), "Reviewer label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Reviewer' label is displayed");

            // After you search and the grid is loaded:
            systemAdminPage.clickRecordNumber(recordNum);
            ExtentReportListener.getExtentTest().info("Clicked Record Number link in grid: " + recordNum);

            amendExportControlPage.clickAmendmentAMD1Tab();
            ExtentReportListener.getExtentTest().info("Opened 'Amendment (AMD1)' transaction tab successfully");
        } catch (Exception e) {
            // User will capture and log any exceptions that occur during the test
            ExtentReportListener.getExtentTest().fail("Test failed due to exception: " + e.getMessage());
        }
    }

    @AfterMethod
    public void tearDown()
    {
        DriverManager.quitDriver();
        // User will record browser closure in the test report
        ExtentReportListener.getExtentTest().info("Browser was successfully closed.");
    }
}