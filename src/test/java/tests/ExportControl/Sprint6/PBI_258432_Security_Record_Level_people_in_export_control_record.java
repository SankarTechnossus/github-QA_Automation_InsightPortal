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
import pages.Export_Control.Export_Control_Details.AmendExportControlPage;
import pages.Administration.Form_Visibility.FormsVisibility_ExportControlPage;
import pages.Export_Control.Export_Control_Details.InitialReviewWorkflowPage;
import pages.Adobe.AgreementPage;
import pages.Export_Control.Export_Control_Details.AddChecklistFlowPage;
import pages.Export_Control.Export_Control_Details.DisplayChecklistFlowPage;
import pages.Export_Control.Export_Control_Details.MenuFlow;
import pages.Export_Control.Export_Control_Details.ResponseToReviewPage;
import pages.Home.DashboardPage;
import pages.Home.LoginPage;
import pages.My_Profile.Security_Page.Organization_Level_Access;
import pages.My_Profile.Security_Page.Profile_Level_Access;
import pages.My_Profile.Security_Page.Record_Level_Access;
import pages.My_Profile.Security_Page.Record_Level_people_in_export_control_Page;
import pages.System_Admin_Flow.SystemAdminPage;
import utils.DriverManager;
import utils.JsonDataReader;
import utils.UniqueNameGenerator;

import java.time.Duration;

@Listeners(ExtentReportListener.class)
@Test (groups = {"regression", "integration"})
public class PBI_258432_Security_Record_Level_people_in_export_control_record {

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
    Profile_Level_Access ProfileLevelAccess;
    Record_Level_people_in_export_control_Page RecordLevelAccessinexport;
    CreateExportControlPage createExportControlPage;

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
        RecordLevelAccessinexport = new Record_Level_people_in_export_control_Page(driver);
        basePage = new BasePage (driver);
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        communicationManagementExportControlPage = new CommunicationManagement_ExportControlPage(driver);
        amendExportControlPage = new AmendExportControlPage(driver);
        formsVisibilityExportControlPage = new FormsVisibility_ExportControlPage(driver);
        RecordLevelAccess = new Record_Level_Access(driver);
        initialReviewWorkflowPage = new InitialReviewWorkflowPage(driver);
        addChecklistFlowPage = new AddChecklistFlowPage(driver);
        displayChecklistFlowPage = new DisplayChecklistFlowPage(driver);
        menuFlow = new MenuFlow(driver);
        responseToReviewPage = new ResponseToReviewPage(driver);
        systemAdminPage = new SystemAdminPage(driver);
        agreementPage = new AgreementPage(driver);
        uniqueNameGenerator = new UniqueNameGenerator();
        workflowsPage = new WorkflowsPage(driver);
        ProfileLevelAccess = new Profile_Level_Access (driver);
        createExportControlPage = new CreateExportControlPage(driver);    }

    @Test
    public void PBI_258432_Security_Record_Level_people_on_export_control_record ()
    {
        try {
            String url = JsonDataReader.get(0, "URLTucson");
            String userName = JsonDataReader.get(0, "Username");
            String password = JsonDataReader.get(0, "Password");
            String profileName = JsonDataReader.get(1, "ProfileName");
            String profileCode = JsonDataReader.get(1, "ProfileCode");
            String piSearchText      = JsonDataReader.get(3, "InitialReviewPiSearchText"); // "mohan"
            String piFullName        = JsonDataReader.get(3, "PIName");
            String OrganizationIDKashif = JsonDataReader.get(1, "OrganizationIDKashif");
            String userFullName = JsonDataReader.get(1, "UserFullName");
            String expectedUserFullName = JsonDataReader.get(1, "UserFullName");
            String expectedUserId = JsonDataReader.get(1, "expectedUserId");
            String PersonName = JsonDataReader.get(1, "PersonName");
            String ColumData = JsonDataReader.get(1, "ColumData");
            String ValueToSelect = JsonDataReader.get(1, "ValueToSelect");


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

            RecordLevelAccessinexport.clickPeopleLink();
            ExtentReportListener.getExtentTest().pass("Clicked on 'People' link successfully");
            Assert.assertTrue(RecordLevelAccessinexport.verifyUserLandsOnPeoplePage(), "User did NOT land on People page");
            ExtentReportListener.getExtentTest().pass("Verified user landed on People page successfully");

            RecordLevelAccessinexport.clickAddNewPeopleButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Add New People' button successfully");
            Assert.assertTrue(RecordLevelAccessinexport.verifyUserLandsOnPeoplePage(), "User did NOT land on People page");
            ExtentReportListener.getExtentTest().pass("Verified user landed on People page successfully");

            RecordLevelAccessinexport.clickCancelButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Cancel' successfully in People section");
            Assert.assertTrue(RecordLevelAccessinexport.verifyUserLandsOnPeoplePage(), "User did NOT land on People page");
            ExtentReportListener.getExtentTest().pass("Verified user landed on People page successfully");

            RecordLevelAccessinexport.clickAddNewPeopleButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Add New People' button successfully");
            Assert.assertTrue(RecordLevelAccessinexport.verifyUserLandsOnPeoplePage(), "User did NOT land on People page");
            ExtentReportListener.getExtentTest().pass("Verified user landed on People page successfully");

            RecordLevelAccessinexport.enterUserIdInSearchBox(expectedUserId);
            ExtentReportListener.getExtentTest().info("Entered UserId '" + expectedUserId + "' in People search box");
            Assert.assertTrue(RecordLevelAccessinexport.verifyUserLandsOnPeoplePage(), "User did NOT land on People page");
            ExtentReportListener.getExtentTest().pass("Verified user landed on People page successfully");

            RecordLevelAccessinexport.selectUserFromDropdownById(expectedUserId);
            ExtentReportListener.getExtentTest().info("Selected UserId '" + expectedUserId + "' from dropdown");
            Assert.assertTrue(RecordLevelAccessinexport.verifyUserLandsOnPeoplePage(), "User did NOT land on People page");
            ExtentReportListener.getExtentTest().pass("Verified user landed on People page successfully");

            RecordLevelAccessinexport.clickAddButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Add' successfully for UserId '" + expectedUserId + "'");

            RecordLevelAccessinexport.selectTypeAsExternal_ForAlam();
            ExtentReportListener.getExtentTest().pass("Selected Type as 'External' for Alam, Md");

            RecordLevelAccessinexport.selectValueFromPeopleGridDropdown(PersonName, ColumData, ValueToSelect);
            ExtentReportListener.getExtentTest().pass("Selected Role as 'Project Manager' for Alam, Md");

            RecordLevelAccessinexport.clickFirstRemoveXMark();
            ExtentReportListener.getExtentTest().pass("Clicked first 'X' (Remove) mark successfully");

            RecordLevelAccessinexport.acceptRemoveUserConfirmationAlert();
            ExtentReportListener.getExtentTest().pass("Accepted confirmation alert to remove user successfully");

            RecordLevelAccessinexport.clickAddExternalPeopleButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Add External People' button successfully");

            RecordLevelAccessinexport.clickCancelButton();
            ExtentReportListener.getExtentTest().info("Clicked 'Cancel' button successfully");

            RecordLevelAccessinexport.clickAddNewPeopleButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Add New People' button successfully");
            Assert.assertTrue(RecordLevelAccessinexport.verifyUserLandsOnPeoplePage(), "User did NOT land on People page");
            ExtentReportListener.getExtentTest().pass("Verified user landed on People page successfully");

            RecordLevelAccessinexport.enterUserIdInSearchBox(expectedUserId);
            ExtentReportListener.getExtentTest().info("Entered UserId '" + expectedUserId + "' in People search box");
            Assert.assertTrue(RecordLevelAccessinexport.verifyUserLandsOnPeoplePage(), "User did NOT land on People page");
            ExtentReportListener.getExtentTest().pass("Verified user landed on People page successfully");

            RecordLevelAccessinexport.selectUserFromDropdownById(expectedUserId);
            ExtentReportListener.getExtentTest().info("Selected UserId '" + expectedUserId + "' from dropdown");
            Assert.assertTrue(RecordLevelAccessinexport.verifyUserLandsOnPeoplePage(), "User did NOT land on People page");
            ExtentReportListener.getExtentTest().pass("Verified user landed on People page successfully");

            RecordLevelAccessinexport.clickAddButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Add' successfully for UserId '" + expectedUserId + "'");

            RecordLevelAccessinexport.selectTypeAsExternal_ForAlam();
            ExtentReportListener.getExtentTest().pass("Selected Type as 'External' for Alam, Md");

            RecordLevelAccessinexport.selectValueFromPeopleGridDropdown(PersonName, ColumData, ValueToSelect);
            ExtentReportListener.getExtentTest().pass("Selected Role as 'Project Manager' for Alam, Md");

            ManagementAccessSecurityPage.clickConfirmSignOffCheckbox();
            ExtentReportListener.getExtentTest().pass("Clicked 'I have carefully reviewed this record and confirm my sign off' checkbox");
            Assert.assertTrue(ManagementAccessSecurityPage.VerifyConfirmSignOffCheckboxIsSelected(), "Confirm sign off checkbox is NOT selected");
            ExtentReportListener.getExtentTest().pass("Verified confirm sign off checkbox is selected successfully");

            displayChecklistFlowPage.clickSubmitAction();
            ExtentReportListener.getExtentTest().info("Clicked Submit button successfully");

            String recordNum = systemAdminPage.getRecordNumber();
            ExtentReportListener.getExtentTest().info("Fetched Record Number: " + recordNum);

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
            ExtentReportListener.getExtentTest().pass("Selected user from dropdown: " + expectedUserId);
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

            Assert.assertTrue(RecordLevelAccessinexport.verifyExportControlRecordPresent(recordNum), "Export Control record '" + recordNum + "' is NOT present in Record Level Access grid");
            ExtentReportListener.getExtentTest().pass("Verified Export Control record '" + recordNum + "' is present in Record Level Access grid");

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