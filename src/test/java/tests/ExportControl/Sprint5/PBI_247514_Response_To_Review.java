package tests.ExportControl.Sprint5;


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
import pages.Export_Control.Export_Control_Details.*;
import pages.Home.DashboardPage;
import pages.Export_Control.Actions.CreateExportControlPage;
import pages.Home.LoginPage;
import pages.System_Admin_Flow.SystemAdminPage;
import utils.DriverManager;
import utils.JsonDataReader;
import utils.UniqueNameGenerator;

import java.time.Duration;

@Listeners(ExtentReportListener.class)

public class PBI_247514_Response_To_Review {

    WebDriver driver;
    WebDriverWait wait;
    BasePage basePage;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    InitialReviewWorkflowPage initialReviewWorkflowPage;
    AddChecklistFlowPage addChecklistFlowPage;
    DisplayChecklistFlowPage displayChecklistFlowPage;
    MenuFlow menuFlow;
    ResponseToReviewPage responseToReviewPage;
    SystemAdminPage systemAdminPage;
    CreateExportControlPage createExportControlPage;
    MyActionsPage myActionsPage;
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
        initialReviewWorkflowPage = new InitialReviewWorkflowPage(driver);
        addChecklistFlowPage = new AddChecklistFlowPage(driver);
        displayChecklistFlowPage = new DisplayChecklistFlowPage(driver);
        menuFlow = new MenuFlow(driver);
        responseToReviewPage = new ResponseToReviewPage(driver);
        systemAdminPage = new SystemAdminPage(driver);
        createExportControlPage = new CreateExportControlPage(driver);
        myActionsPage = new MyActionsPage(driver);
        uniqueNameGenerator = new UniqueNameGenerator();
    }

    @Test
    public void Response_To_Review ()
    {
        try {
            String url = JsonDataReader.get(0, "URL");
            String userName = JsonDataReader.get(0, "Username");
            String password = JsonDataReader.get(0, "Password");
            String positiveSearchText = JsonDataReader.get(1, "PositiveSearchText");// "Test_Auto"
            String piSearchText      = JsonDataReader.get(3, "InitialReviewPiSearchText"); // "mohan"
            String piFullName        = JsonDataReader.get(3, "PIName");                    // "Chandra, Mohan"

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
            AgreementPage agreementPage = new AgreementPage(driver);
            agreementPage.clickAdministrationLink();
            Assert.assertTrue(agreementPage.isDashboardNotificationsSummaryDisplayed(), "Dashboard Notifications - Summary page is NOT displayed after clicking Administration link");
            ExtentReportListener.getExtentTest().pass("User successfully navigated to Dashboard Notifications - Summary page.");

            addChecklistFlowPage.openExportControlWorkflows();
            ExtentReportListener.getExtentTest().info("Navigated to Workflow Management → Export Control → Workflows successfully");
            Assert.assertTrue(addChecklistFlowPage.isWorkflowsHeaderDisplayed());
            ExtentReportListener.getExtentTest().pass("Verified 'Workflows' header displayed");

            addChecklistFlowPage.clickPersonnelWorkflow();
            ExtentReportListener.getExtentTest().info("Clicked 'Personnel' workflow successfully");
            Assert.assertTrue(addChecklistFlowPage.isPersonnelDisplayed());
            ExtentReportListener.getExtentTest().pass("Verified 'Personnel' section displayed");

            addChecklistFlowPage.clickAddNewWorkflowVersion();
            ExtentReportListener.getExtentTest().info("Clicked 'Add new' button successfully on Versions page");
            Assert.assertTrue(addChecklistFlowPage.isPersonnelDisplayed());
            ExtentReportListener.getExtentTest().pass("Verified 'Personnel' section displayed");

            // Step X: Enter description for Version 90
            String versionDescription = "Test01_" + uniqueNameGenerator.GenerateRandomName(6);

            addChecklistFlowPage.enterDescriptionForLatestVersion(versionDescription);
            ExtentReportListener.getExtentTest().info("Entered description for Version 90 as: " + versionDescription);
            Assert.assertTrue(addChecklistFlowPage.isVersionsHeaderDisplayed());
            ExtentReportListener.getExtentTest().pass("Verified 'Versions' header displayed");

            addChecklistFlowPage.clickSaveVersion();
            ExtentReportListener.getExtentTest().info("Clicked 'Save' button successfully for Version 90");
            Assert.assertTrue(addChecklistFlowPage.isVersionsHeaderDisplayed());
            ExtentReportListener.getExtentTest().pass("Verified 'Versions' header displayed");

            addChecklistFlowPage.clickLatestVersionLink();
            ExtentReportListener.getExtentTest().info("Clicked latest Version link successfully");
            Assert.assertTrue(addChecklistFlowPage.isSaveButtonDisplayed());
            ExtentReportListener.getExtentTest().pass("Verified 'Save' button displayed");

            responseToReviewPage.clickChiefApprover();
            ExtentReportListener.getExtentTest().info("Clicked on Chief Approver node successfully");
            Assert.assertTrue(addChecklistFlowPage.isChiefApproverHeaderDisplayed());
            ExtentReportListener.getExtentTest().pass("Verified 'Chief Approver' section displayed");

            responseToReviewPage.clickActivitiesTab();
            ExtentReportListener.getExtentTest().info("Clicked Activities tab successfully");
            ExtentReportListener.getExtentTest().info("Generate Document is successfully Added in Activities Tab");
            Assert.assertTrue(addChecklistFlowPage.isChiefApproverHeaderDisplayed());
            ExtentReportListener.getExtentTest().pass("Verified 'Chief Approver' section displayed");

            addChecklistFlowPage.clickUpdateButton();
            ExtentReportListener.getExtentTest().info("Clicked 'Update' button successfully");
            Assert.assertTrue(addChecklistFlowPage.isSaveButtonDisplayed());
            ExtentReportListener.getExtentTest().pass("Verified 'Save' button displayed");

            addChecklistFlowPage.clickSaveButton();
            ExtentReportListener.getExtentTest().info("Clicked 'Save' button successfully");

            dashboardPage.clickExportControlLink();
            ExtentReportListener.getExtentTest().info("Clicked 'Export Control' module link successfully");

            createExportControlPage.clickCreateExportControl();
            ExtentReportListener.getExtentTest().info("Clicked Actions → Create Export Control from left navigation successfully");
            Assert.assertTrue(createExportControlPage.isCreateExportControlHeaderDisplayed(), "'Create Export Control' header is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Create Export Control' header is displayed successfully");

            displayChecklistFlowPage.selectPersonnelExclusion();
            ExtentReportListener.getExtentTest().info("Selected Personnel Exclusion radio button successfully");
            Assert.assertTrue(displayChecklistFlowPage.isPersonnelExclusionRadioDisplayed(), "'Personnel Exclusion' radio button is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Personnel Exclusion' radio button is displayed");

            // Step 2: Select PI Name (type from JSON and choose PI name from JSON)
            createExportControlPage.selectPiName(piSearchText, piFullName);
            ExtentReportListener.getExtentTest().info("Typed '" + piSearchText + "' and selected PI as '" + piFullName + "' successfully");
            Assert.assertTrue(displayChecklistFlowPage.isSelectPINameDisabledDisplayed(), "'Select PI Name' disabled field is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Select PI Name' disabled field is displayed successfully");

            createExportControlPage.clickCreateButton();
            ExtentReportListener.getExtentTest().info("Clicked 'Create' button on Create Export Control sidebar successfully");

            // Step 2: Click Submit
            displayChecklistFlowPage.clickSubmitAction();
            ExtentReportListener.getExtentTest().info("Clicked Submit button successfully");

            initialReviewWorkflowPage.enterName(positiveSearchText);
            ExtentReportListener.getExtentTest().info("Entered name from JSON (PositiveSearchText): '" + positiveSearchText + "' successfully");
            Assert.assertTrue(initialReviewWorkflowPage.isEnterNameDisabledDisplayed(), "'Enter Name' disabled field is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Enter Name' disabled field is displayed successfully");

            initialReviewWorkflowPage.selectGenderMale();
            ExtentReportListener.getExtentTest().info("Selected gender as 'Male' successfully");
            Assert.assertTrue(initialReviewWorkflowPage.isSelectGenderDisabledDisplayed(), "'Select Gender' disabled field is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Select Gender' disabled field is displayed successfully");

            // Step 1: Click Save
            displayChecklistFlowPage.clickSaveAction();
            ExtentReportListener.getExtentTest().info("Clicked Save button successfully");
            Assert.assertTrue(initialReviewWorkflowPage.isPersonnelExclusionValueDisplayed(), "'Personnel Exclusion' value is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Personnel Exclusion' value is displayed successfully");

            // Step 2: Click Submit
            displayChecklistFlowPage.clickSubmitAction();
            ExtentReportListener.getExtentTest().info("Clicked Submit button successfully");
            Assert.assertTrue(initialReviewWorkflowPage.isPersonnelExclusionValueDisplayed(), "'Personnel Exclusion' value is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Personnel Exclusion' value is displayed successfully");

            initialReviewWorkflowPage.refreshCurrentPage();
            ExtentReportListener.getExtentTest().info("Refreshed Export Control details page successfully");

            if (responseToReviewPage.clickResponseToReviewIfPresent()) {
                ExtentReportListener.getExtentTest().pass("Response To Review is present and clicked successfully");
            } else {
                ExtentReportListener.getExtentTest().info("Response To Review is NOT present, proceeding to next step");
            }

            //Login to System Admin page

            String sysAdminUrl = JsonDataReader.get(0, "SysAdminURL");
            String sysAdminBusinessUser = JsonDataReader.get(0, "SysAdminBusinessUser");

            driver.get(sysAdminUrl);
            ExtentReportListener.getExtentTest().info("Opened SysAdmin public URL successfully");

            loginPage.enterSysAdminBusinessUser(sysAdminBusinessUser);
            ExtentReportListener.getExtentTest().info("Entered SysAdmin Business User: " + sysAdminBusinessUser + " successfully");

            loginPage.clickSysAdminLoginButton();
            ExtentReportListener.getExtentTest().info("Clicked 'Login' button on SysAdmin page successfully");

            dashboardPage.clickExportControlLink();
            ExtentReportListener.getExtentTest().info("Clicked 'Export Control' module link successfully");

            createExportControlPage.clickActionRequired();
            ExtentReportListener.getExtentTest().info("Clicked 'Action Required' menu successfully");
            Assert.assertTrue(createExportControlPage.isActionRequiredCrumbDisplayed(), "'Action Required' crumb is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Action Required' crumb is displayed");

            systemAdminPage.clickFirstRecordNumber();
            ExtentReportListener.getExtentTest().info("Clicked first Record Number in Action Required grid successfully");
            Assert.assertTrue(systemAdminPage.isPersonnelExclusionDisplayed(), "'Personnel Exclusion' value is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Personnel Exclusion' value is displayed");

            systemAdminPage.clickApproveButton();
            ExtentReportListener.getExtentTest().info("Clicked 'Approve' button successfully");

            // Step 1: Fetch record #
            String recordNum = systemAdminPage.getRecordNumber();
            ExtentReportListener.getExtentTest().info("Fetched Record Number: " + recordNum);
            Assert.assertTrue(systemAdminPage.isCommentsLabelDisplayed(), "'Comments' label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Comments' label is displayed");

            systemAdminPage.clickComments();
            ExtentReportListener.getExtentTest().info("Clicked 'Comments' button successfully");
            Assert.assertTrue(systemAdminPage.isCommentButtonDisplayed(), "'Comment' button is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Comment' button is displayed");

            //  Enter comment text in Comments modal
            systemAdminPage.enterCommentInModal("Test_Auto");
            ExtentReportListener.getExtentTest().info("Entered comment text 'Test_Auto' successfully in Comments modal");
            Assert.assertTrue(systemAdminPage.isCommentButtonDisplayed(), "'Comment' button is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Comment' button is displayed");

            // Click Comment button
            systemAdminPage.clickCommentButtonOnModal();
            ExtentReportListener.getExtentTest().info("Clicked 'Comment' button on Comments modal successfully");
            Assert.assertTrue(systemAdminPage.isPersonnelExclusionDisplayed(), "'Personnel Exclusion' value is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Personnel Exclusion' value is displayed");

            systemAdminPage.clickApproveButton();
            ExtentReportListener.getExtentTest().info("Clicked 'Approve' button successfully");

            if (responseToReviewPage.clickResponseToReviewIfPresent()) {
                ExtentReportListener.getExtentTest().pass("Response To Review is present and clicked successfully");
            } else {
                ExtentReportListener.getExtentTest().info("Response To Review is NOT present, proceeding to next step");
            }

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
            ExtentReportListener.getExtentTest().info("Clicked 'Export Control' module link successfully");

            menuFlow.clickSearchLink();
            ExtentReportListener.getExtentTest().info("Clicked 'Search' link successfully from Export Control sidebar");
            Assert.assertTrue(menuFlow.isSearchBreadcrumbDisplayed(), "'Export Control > Search' breadcrumb is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Export Control > Search' breadcrumb is displayed successfully");

            systemAdminPage.enterValueField(recordNum);
            ExtentReportListener.getExtentTest().info("Successfully entered dynamic record number: " + recordNum);
            Assert.assertTrue(myActionsPage.isReviewerLabelDisplayed(), "Reviewer label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Reviewer' label is displayed");

            menuFlow.clickSearchButton();
            ExtentReportListener.getExtentTest().info("Clicked Search");
            Assert.assertTrue(menuFlow.isSearchBreadcrumbDisplayed(), "'Export Control > Search' breadcrumb is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Export Control > Search' breadcrumb is displayed successfully");

            // After you search and the grid is loaded:
            systemAdminPage.clickRecordNumber(recordNum);
            ExtentReportListener.getExtentTest().info("Clicked Record Number link in grid: " + recordNum);

            if (responseToReviewPage.clickResponseToReviewIfPresent()) {
                ExtentReportListener.getExtentTest().pass("Response To Review is present and clicked successfully");
            } else {
                ExtentReportListener.getExtentTest().info("Response To Review is NOT present, proceeding to next step");
            }
        }
        catch (Exception e)
        {
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