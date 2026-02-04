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
import pages.System_Admin_Flow.SystemAdminPage;
import utils.DriverManager;
import utils.JsonDataReader;
import utils.UniqueNameGenerator;

import java.time.Duration;

@Listeners(ExtentReportListener.class)
//@Test (groups = {"regression", "integration"})
public class PBI_256615_Profile_Level_Access_Security_Flow {

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
    Profile_Level_Access ProfileLevelAccess;

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
        ProfileLevelAccess = new Profile_Level_Access (driver);
    }

    @Test
    public void PBI_256615_Profile_Level_Access_Flow ()
    {
        try {
            String url = JsonDataReader.get(0, "URLTucson");
            String userName = JsonDataReader.get(0, "Username");
            String password = JsonDataReader.get(0, "Password");
            String profileName = JsonDataReader.get(1, "ProfileName");
            String profileCode = JsonDataReader.get(1, "ProfileCode");

            // User will open the login page of the Insight Portal application
            driver.get(url);
            ExtentReportListener.getExtentTest().info("Opened dashboard URL");

            // User will wait for the login screen to load completely before performing actions
            basePage.pause(20000);

            // Login into the application
            loginPage.LoginIntoApplication(userName, password);
            Assert.assertTrue(dashboardPage.VerifyUserLandsOnDashboardPage());
            ExtentReportListener.getExtentTest().pass("User logged into the application successfully and lands on the dashboard page.");

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

            ProfileLevelAccess.toggleProfileDelegateSectionTwice();
            ExtentReportListener.getExtentTest().pass("Clicked expand and collapse on Profile/Delegate Level Access section");
            Assert.assertTrue(ManagementAccessSecurityPage.VerifyUserLandsOnSecurityPage());
            ExtentReportListener.getExtentTest().pass("User successfully landed on the Security page.");

            ProfileLevelAccess.clickAddAdditionalProfileTwice();
            ExtentReportListener.getExtentTest().pass("Clicked 'Add Additional Profile' button twice successfully");
            Assert.assertTrue(ProfileLevelAccess.verifyProfileLabelIsDisplayed(), "'Profile' label is not displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Profile' label is displayed successfully");


            if (ProfileLevelAccess.isProfilePresent(profileName)) {

                ProfileLevelAccess.clickRemoveProfile(profileName);
                ExtentReportListener.getExtentTest().pass("Profile '" + profileName + "' already present. Removed successfully");
                Assert.assertTrue(ProfileLevelAccess.verifyRemoveProfileConfirmationMessageIsDisplayed(), "Remove profile confirmation message is not displayed");
                ExtentReportListener.getExtentTest().pass("Verified remove profile confirmation message is displayed successfully");

                ProfileLevelAccess.clickCancelOnRemoveProfileModal();
                ExtentReportListener.getExtentTest().pass("Clicked Cancel button successfully");
                Assert.assertTrue(ProfileLevelAccess.verifyProfileLabelIsDisplayed(), "'Profile' label is not displayed");
                ExtentReportListener.getExtentTest().pass("Verified 'Profile' label is displayed successfully");

                ProfileLevelAccess.clickRemoveProfile(profileName);
                ExtentReportListener.getExtentTest().pass("Profile '" + profileName + "' already present. Removed successfully");
                Assert.assertTrue(ProfileLevelAccess.verifyRemoveProfileConfirmationMessageIsDisplayed(), "Remove profile confirmation message is not displayed");
                ExtentReportListener.getExtentTest().pass("Verified remove profile confirmation message is displayed successfully");

                ProfileLevelAccess.clickOkOnRemoveProfileModal();
                ExtentReportListener.getExtentTest().pass("Clicked OK button successfully");
                Assert.assertTrue(ProfileLevelAccess.verifyProfileLabelIsDisplayed(), "'Profile' label is not displayed");
                ExtentReportListener.getExtentTest().pass("Verified 'Profile' label is displayed successfully");

                ProfileLevelAccess.clickSaveButton();
                ExtentReportListener.getExtentTest().pass("Clicked Save button successfully");
                ManagementAccessSecurityPage.waitForSecurityAccessUpdatedToastToDisappear();
                ExtentReportListener.getExtentTest().info("Waited for success toast to disappear");
                Assert.assertTrue(ManagementAccessSecurityPage.VerifyUserLandsOnSecurityPage());
                ExtentReportListener.getExtentTest().pass("User successfully landed on the Security page.");

                ProfileLevelAccess.clickAddAdditionalProfile();
                ExtentReportListener.getExtentTest().pass("Clicked Add Additional Profile");
                Assert.assertTrue(ManagementAccessSecurityPage.VerifyUserLandsOnSecurityPage());
                ExtentReportListener.getExtentTest().pass("User successfully landed on the Security page.");

                ProfileLevelAccess.searchAndSelectProfile(profileCode);
                ExtentReportListener.getExtentTest().pass("Searched and selected profile with code: " + profileCode);
                Assert.assertTrue(ManagementAccessSecurityPage.VerifyUserLandsOnSecurityPage());
                ExtentReportListener.getExtentTest().pass("User successfully landed on the Security page.");

                ProfileLevelAccess.clickApplyButton();
                ExtentReportListener.getExtentTest().pass("Clicked Apply button");
                Assert.assertTrue(ProfileLevelAccess.verifySpecifyAccessLevelMessageIsDisplayed(), "'Please specify access level' validation message is not displayed");
                ExtentReportListener.getExtentTest().pass("Verified 'Please specify access level' validation message is displayed successfully");

                ProfileLevelAccess.selectExportControlManage();
                ExtentReportListener.getExtentTest().pass("Selected Export Control Manage access");
                Assert.assertTrue(ProfileLevelAccess.verifyAllValidationsCompletedMessageIsDisplayed(), "'All validations in this area have been completed' message is not displayed");
                ExtentReportListener.getExtentTest().pass("Verified 'All validations in this area have been completed' message is displayed successfully");

                ProfileLevelAccess.clickSaveButton();
                ExtentReportListener.getExtentTest().pass("Clicked Save button successfully");
                ManagementAccessSecurityPage.waitForSecurityAccessUpdatedToastToDisappear();
                ExtentReportListener.getExtentTest().info("Waited for success toast to disappear");
                Assert.assertTrue(ManagementAccessSecurityPage.VerifyUserLandsOnSecurityPage());
                ExtentReportListener.getExtentTest().pass("User successfully landed on the Security page.");


            }
            else {

                ProfileLevelAccess.clickAddAdditionalProfile();
                ExtentReportListener.getExtentTest().pass("Clicked Add Additional Profile");
                Assert.assertTrue(ManagementAccessSecurityPage.VerifyUserLandsOnSecurityPage());
                ExtentReportListener.getExtentTest().pass("User successfully landed on the Security page.");

                ProfileLevelAccess.searchAndSelectProfile(profileCode);
                ExtentReportListener.getExtentTest().pass("Searched and selected profile with code: " + profileCode);
                Assert.assertTrue(ManagementAccessSecurityPage.VerifyUserLandsOnSecurityPage());
                ExtentReportListener.getExtentTest().pass("User successfully landed on the Security page.");

                ProfileLevelAccess.clickApplyButton();
                ExtentReportListener.getExtentTest().pass("Clicked Apply button");
                Assert.assertTrue(ProfileLevelAccess.verifySpecifyAccessLevelMessageIsDisplayed(), "'Please specify access level' validation message is not displayed");
                ExtentReportListener.getExtentTest().pass("Verified 'Please specify access level' validation message is displayed successfully");


                ProfileLevelAccess.selectExportControlManage();
                ExtentReportListener.getExtentTest().pass("Selected Export Control Manage access");
                Assert.assertTrue(ProfileLevelAccess.verifyAllValidationsCompletedMessageIsDisplayed(), "'All validations in this area have been completed' message is not displayed");
                ExtentReportListener.getExtentTest().pass("Verified 'All validations in this area have been completed' message is displayed successfully");

                ProfileLevelAccess.clickSaveButton();
                ExtentReportListener.getExtentTest().pass("Clicked Save button successfully");
                ManagementAccessSecurityPage.waitForSecurityAccessUpdatedToastToDisappear();
                ExtentReportListener.getExtentTest().info("Waited for success toast to disappear");
                Assert.assertTrue(ManagementAccessSecurityPage.VerifyUserLandsOnSecurityPage());
                ExtentReportListener.getExtentTest().pass("User successfully landed on the Security page.");
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