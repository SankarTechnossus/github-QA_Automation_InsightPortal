package tests.ExportControl.Sprint6;


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
import pages.Administration.Communication_Management.CommunicationManagement_ExportControlPage;
import pages.Administration.Workflow_Management.WorkflowsPage;
import pages.Export_Control.Actions.CreateExportControlPage;
import pages.Export_Control.Export_Control_Details.*;
import pages.Administration.Form_Visibility.FormsVisibility_ExportControlPage;
import pages.Adobe.AgreementPage;
import pages.Home.DashboardPage;
import pages.Home.LoginPage;
import pages.My_Profile.Security_Page.Organization_Level_Access;
import pages.My_Profile.Security_Page.Record_Level_Access;
import pages.System_Admin_Flow.SystemAdminPage;
import utils.DriverManager;
import utils.JsonDataReader;
import utils.UniqueNameGenerator;

import java.time.Duration;

@Listeners(ExtentReportListener.class)
@Test (groups = {"regression", "integration"})
public class PBI_256620_Record_Level_Access_Security_Flow {

    WebDriver driver;
    WebDriverWait wait;
    BasePage basePage;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    CommunicationManagement_ExportControlPage communicationManagementExportControlPage;
    AmendExportControlPage amendExportControlPage;
    FormsVisibility_ExportControlPage formsVisibilityExportControlPage;
    InitialReviewWorkflowPage initialReviewWorkflowPage;
    AddChecklistFlowPage addChecklistFlowPage;
    DisplayChecklistFlowPage displayChecklistFlowPage;
    MenuFlow menuFlow;
    ResponseToReviewPage responseToReviewPage;
    SystemAdminPage systemAdminPage;
    AgreementPage agreementPage;
    UniqueNameGenerator uniqueNameGenerator;
    WorkflowsPage workflowsPage;
    Organization_Level_Access ManagementAccessSecurityPage;
    Record_Level_Access RecordLevelAccess;
    CreateExportControlPage createExportControlPage;
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

        ManagementAccessSecurityPage = new Organization_Level_Access(driver);
        basePage = new BasePage (driver);
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        communicationManagementExportControlPage = new CommunicationManagement_ExportControlPage(driver);
        amendExportControlPage = new AmendExportControlPage(driver);
        formsVisibilityExportControlPage = new FormsVisibility_ExportControlPage(driver);
        initialReviewWorkflowPage = new InitialReviewWorkflowPage(driver);
        addChecklistFlowPage = new AddChecklistFlowPage(driver);
        displayChecklistFlowPage = new DisplayChecklistFlowPage(driver);
        menuFlow = new MenuFlow(driver);
        responseToReviewPage = new ResponseToReviewPage(driver);
        systemAdminPage = new SystemAdminPage(driver);
        agreementPage = new AgreementPage(driver);
        uniqueNameGenerator = new UniqueNameGenerator();
        workflowsPage = new WorkflowsPage(driver);
        RecordLevelAccess = new Record_Level_Access(driver);
        createExportControlPage = new CreateExportControlPage(driver);
        myActionsPage = new MyActionsPage(driver);
    }

    @Test
    public void PBI_256620_Record_Level_Access_Flow ()
    {
        try {
            String url = JsonDataReader.get(0, "URLTucson");
            String userName = JsonDataReader.get(0, "Username");
            String piSearchText      = JsonDataReader.get(3, "InitialReviewPiSearchText"); // "mohan"
            String piFullName        = JsonDataReader.get(3, "PIName");
            String password = JsonDataReader.get(0, "Password");
            String templateNoticeGroup = JsonDataReader.get(1, "TemplateNoticeGroup");
            String organizationName = JsonDataReader.get(1, "OrganizationName");
            String OrganizationIDKashif = JsonDataReader.get(1, "OrganizationIDKashif");
            String userFullName = JsonDataReader.get(1, "UserFullName");
            String expectedUserFullName = JsonDataReader.get(1, "UserFullName");
            String expectedUserId = JsonDataReader.get(1, "expectedUserId");

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

            String recordNum = systemAdminPage.getRecordNumber();
            ExtentReportListener.getExtentTest().info("Fetched Record Number: " + recordNum);

            systemAdminPage.clickLogout();
            ExtentReportListener.getExtentTest().info("Clicked Logout successfully");

            //User login to Kashif's Account

            String URLTucson = JsonDataReader.get(0, "URLTucson");
            String KashifUsername = JsonDataReader.get(0, "KashifUsername");
            String KashifPassword = JsonDataReader.get(0, "KashifPassword");

            // User will open the login page of the Insight Portal application
            driver.get(URLTucson);
            ExtentReportListener.getExtentTest().info("Opened dashboard URL");

            // User will wait for the login screen to load completely before performing actions
            basePage.pause(20000);

            // Login into the application
            loginPage.LoginIntoApplication(KashifUsername, KashifPassword);

            Assert.assertTrue(dashboardPage.VerifyUserLandsOnDashboardPage());
            ExtentReportListener.getExtentTest().pass("User logged into  Kashif's Account successfully and lands on the dashboard page.");

            dashboardPage.clickExportControlLink();
            ExtentReportListener.getExtentTest().info("Clicked 'Export Control' module link successfully");

            menuFlow.clickSearchLink();
            ExtentReportListener.getExtentTest().info("Clicked 'Search' link successfully from Export Control sidebar");
            Assert.assertTrue(ManagementAccessSecurityPage.verifyUserLandsOnSearchPage(), "User did NOT land on Search page");
            ExtentReportListener.getExtentTest().pass("Verified user landed on Search page successfully");

            systemAdminPage.enterValueField(recordNum);
            ExtentReportListener.getExtentTest().info("Successfully entered dynamic record number: " + recordNum);
            Assert.assertTrue(myActionsPage.isReviewerLabelDisplayed(), "Reviewer label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Reviewer' label is displayed");

            menuFlow.clickSearchButton();
            ExtentReportListener.getExtentTest().info("Clicked Search");
            Assert.assertTrue(myActionsPage.isReviewerLabelDisplayed(), "Reviewer label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Reviewer' label is displayed");

            Assert.assertTrue(RecordLevelAccess.verifyNoSearchResultsMessageDisplayed(), "'The search criteria yielded no results.' message is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'The search criteria yielded no results.' message is displayed correctly");

            systemAdminPage.clickLogout();
            ExtentReportListener.getExtentTest().info("Clicked Logout successfully");

            // User will open the login page of the Insight Portal application
            driver.get(url);
            ExtentReportListener.getExtentTest().info("Opened dashboard URL");

            // User will wait for the login screen to load completely before performing actions
            basePage.pause(20000);

            // Login into the application
            loginPage.LoginIntoApplication(userName, password);
            Assert.assertTrue(dashboardPage.VerifyUserLandsOnDashboardPage());
            ExtentReportListener.getExtentTest().pass("User logged into Sankar's Account successfully and lands on the dashboard page.");

            Assert.assertTrue(ManagementAccessSecurityPage.VerifyUserLandsOnMyProfilePage());
            ExtentReportListener.getExtentTest().pass("User successfully landed on the My Profile page.");
            ManagementAccessSecurityPage.clickMyProfileLink();
            ExtentReportListener.getExtentTest().pass("Clicked 'My Profile' link successfully");

            Assert.assertTrue(ManagementAccessSecurityPage.VerifyFirstNameLabelIsDisplayed());
            ExtentReportListener.getExtentTest().pass("Verified 'First Name' label is displayed successfully.");
            ManagementAccessSecurityPage.clickSecurityLink();
            ExtentReportListener.getExtentTest().pass("Clicked 'Security' link successfully");

            Assert.assertTrue(ManagementAccessSecurityPage.VerifyUserLandsOnSecurityPage());
            ExtentReportListener.getExtentTest().pass("User successfully landed on the Security page.");
            RecordLevelAccess.clickSearchForUserMenu();
            ExtentReportListener.getExtentTest().info("Clicked 'Search For User' menu successfully");

            RecordLevelAccess.enterUserSearchValue(OrganizationIDKashif);
            ExtentReportListener.getExtentTest().info("Entered User search value: " + OrganizationIDKashif);

            RecordLevelAccess.selectUserFromDropdownById(expectedUserId);
            ExtentReportListener.getExtentTest().info("Selected user from dropdown: " + expectedUserId);

            Assert.assertTrue(RecordLevelAccess.verifySearchButtonDisplayed(), "'Search' button is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Search' button is displayed successfully");
            RecordLevelAccess.clickSearchButton();
            ExtentReportListener.getExtentTest().info("Clicked 'Search' button successfully");

            RecordLevelAccess.clickOnUserName(userFullName);
            ExtentReportListener.getExtentTest().info("Clicked on User Name: " + userFullName);

            ManagementAccessSecurityPage.clickSecurityLink();
            ExtentReportListener.getExtentTest().pass("Clicked 'Security' link successfully");
            Assert.assertTrue(ManagementAccessSecurityPage.verifySecurityPageTitleDisplayed(), "'Security: Alam, Md (MA1279)' page title is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Security: Alam, Md (MA1279)' page title is displayed successfully");

            RecordLevelAccess.clickRecordLevelAccessExpandButton();
            ExtentReportListener.getExtentTest().info("Clicked Record Level Access expand (+) button");

            Assert.assertTrue(RecordLevelAccess.verifySecurityPageTitle(expectedUserFullName, expectedUserId), "Security page title is NOT displayed as expected");
            ExtentReportListener.getExtentTest().pass("Verified Security page title is displayed correctly");
            RecordLevelAccess.clickAddAdditionalExportControlButton();
            ExtentReportListener.getExtentTest().info("Clicked 'Add Additional Export Control' button successfully");

            Assert.assertTrue(RecordLevelAccess.verifyExportControlNumberLabelDisplayed(), "'Export Control #' label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Export Control #' label is displayed successfully");
            RecordLevelAccess.enterExportControlRecordNumber(recordNum);
            ExtentReportListener.getExtentTest().info("Entered Export Control Record Number in modal: " + recordNum);

            Assert.assertTrue(RecordLevelAccess.verifyExportControlNumberLabelDisplayed(), "'Export Control #' label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Export Control #' label is displayed successfully");
            RecordLevelAccess.clickClearSelectionsButton();
            ExtentReportListener.getExtentTest().info("Clicked 'Clear Selections' in Add Additional Export Control modal");

            Assert.assertTrue(RecordLevelAccess.verifyExportControlNumberLabelDisplayed(), "'Export Control #' label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Export Control #' label is displayed successfully");
            RecordLevelAccess.enterExportControlRecordNumber(recordNum);
            ExtentReportListener.getExtentTest().info("Entered Export Control Record Number in modal: " + recordNum);

            Assert.assertTrue(RecordLevelAccess.verifyExportControlNumberLabelDisplayed(), "'Export Control #' label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Export Control #' label is displayed successfully");
            RecordLevelAccess.clickModalSearchButton();
            ExtentReportListener.getExtentTest().info("Clicked 'Search' in Add Additional Export Control modal");

            RecordLevelAccess.clickFirstExportControlCheckbox();
            ExtentReportListener.getExtentTest().info("Selected first Export Control result checkbox successfully");
            Assert.assertTrue(RecordLevelAccess.verifyExportControlNumberLabelDisplayed(), "'Export Control #' label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Export Control #' label is displayed successfully");

            RecordLevelAccess.clickApplyButtonWhenEnabled();
            ExtentReportListener.getExtentTest().info("Clicked 'Apply' button successfully after it became enabled");


            Assert.assertTrue(RecordLevelAccess.verifyValidationsCompletedMessageDisplayed(), "'All validations in this area have been completed' message is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'All validations in this area have been completed' message is displayed successfully");
            ManagementAccessSecurityPage.clickSaveButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Save' button successfully");

            systemAdminPage.clickLogout();
            ExtentReportListener.getExtentTest().info("Clicked Logout successfully");

            // User will open the login page of the Insight Portal application
            driver.get(URLTucson);
            ExtentReportListener.getExtentTest().info("Opened dashboard URL");

            // User will wait for the login screen to load completely before performing actions
            basePage.pause(20000);

            // Login into the application
            loginPage.LoginIntoApplication(KashifUsername, KashifPassword);

            Assert.assertTrue(dashboardPage.VerifyUserLandsOnDashboardPage());
            ExtentReportListener.getExtentTest().pass("User logged into  Kashif's Account successfully and lands on the dashboard page.");

            dashboardPage.clickExportControlLink();
            ExtentReportListener.getExtentTest().info("Clicked 'Export Control' module link successfully");

            menuFlow.clickSearchLink();
            ExtentReportListener.getExtentTest().info("Clicked 'Search' link successfully from Export Control sidebar");
            Assert.assertTrue(ManagementAccessSecurityPage.verifyUserLandsOnSearchPage(), "User did NOT land on Search page");
            ExtentReportListener.getExtentTest().pass("Verified user landed on Search page successfully");

            systemAdminPage.enterValueField(recordNum);
            ExtentReportListener.getExtentTest().info("Successfully entered dynamic record number: " + recordNum);
            Assert.assertTrue(myActionsPage.isReviewerLabelDisplayed(), "Reviewer label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Reviewer' label is displayed");

            menuFlow.clickSearchButton();
            ExtentReportListener.getExtentTest().info("Clicked Search");
            Assert.assertTrue(myActionsPage.isReviewerLabelDisplayed(), "Reviewer label is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Reviewer' label is displayed");
            ExtentReportListener.getExtentTest().pass("Access has been provided to this Record Number:"+ recordNum+"For Kashif's Account");


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