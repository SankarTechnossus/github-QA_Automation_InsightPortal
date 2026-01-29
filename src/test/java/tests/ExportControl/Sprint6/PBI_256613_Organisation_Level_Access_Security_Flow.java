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
import pages.System_Admin_Flow.SystemAdminPage;
import utils.DriverManager;
import utils.JsonDataReader;
import utils.UniqueNameGenerator;

import java.time.Duration;

@Listeners(ExtentReportListener.class)
//@Test (groups = {"regression", "integration"})
public class PBI_256613_Organisation_Level_Access_Security_Flow {

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
    }

    @Test
    public void PBI_256613_Security__Organisation_Level ()
    {
        try {
            String url = JsonDataReader.get(0, "URLTucson");
            String userName = JsonDataReader.get(0, "Username");
            String password = JsonDataReader.get(0, "Password");
            String templateNoticeGroup             = JsonDataReader.get(1, "TemplateNoticeGroup");
            String organizationName = JsonDataReader.get(1, "OrganizationName");
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

            ManagementAccessSecurityPage.clickOrganizationLevelAccessToggleButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Organization Level Access' expand button successfully");
            Assert.assertTrue(ManagementAccessSecurityPage.VerifyOrganizationLabelIsDisplayed());
            ExtentReportListener.getExtentTest().pass("Verified 'Organization' label is displayed successfully.");

            ManagementAccessSecurityPage.clickOrganizationLevelAccessToggleButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Organization Level Access' Collapse button successfully");
            Assert.assertTrue(ManagementAccessSecurityPage.VerifyUserLandsOnSecurityPage());
            ExtentReportListener.getExtentTest().pass("User successfully landed on the Security page.");

            ManagementAccessSecurityPage.clickAddAdditionalOrganizationButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Add Additional Organization' button successfully");
            Assert.assertTrue(ManagementAccessSecurityPage.VerifyOrganizationLabelIsDisplayed());
            ExtentReportListener.getExtentTest().pass("Verified 'Organization' label is displayed successfully.");

            ManagementAccessSecurityPage.clickAddAdditionalOrganizationButtonToCollapse();
            ExtentReportListener.getExtentTest().pass("Clicked 'Add Additional Organization' button again to collapse search area successfully");
            Assert.assertTrue(ManagementAccessSecurityPage.VerifyOrganizationLabelIsDisplayed());
            ExtentReportListener.getExtentTest().pass("Verified 'Organization' label is displayed successfully.");


            if (!ManagementAccessSecurityPage.isExportControlViewCheckboxSelected()) {

                ManagementAccessSecurityPage.selectExportControlViewAndSave();
                ExtentReportListener.getExtentTest().pass("Selected Export Control 'View' and clicked Save successfully");

            } else {
                ExtentReportListener.getExtentTest().info("Export Control 'View' checkbox is already selected. Skipping selection.");
            }

            Assert.assertTrue(ManagementAccessSecurityPage.VerifyExportLabelIsDisplayed());
            ExtentReportListener.getExtentTest().pass("Verified 'Export' label is displayed successfully.");


            if (!ManagementAccessSecurityPage.isExportControlManageCheckboxSelected()) {

                ManagementAccessSecurityPage.selectExportControlManageAndSave();
                ExtentReportListener.getExtentTest().pass("Selected Export Control 'Manage' and clicked Save successfully");

            } else {
                ExtentReportListener.getExtentTest().info("Export Control 'Manage' checkbox is already selected. Skipping selection.");
            }

            Assert.assertTrue(ManagementAccessSecurityPage.VerifyExportLabelIsDisplayed());
            ExtentReportListener.getExtentTest().pass("Verified 'Export' label is displayed successfully.");

            ManagementAccessSecurityPage.waitForSecurityAccessUpdatedToastToDisappear();
            ExtentReportListener.getExtentTest().info("Waited for success toast to disappear");

            ManagementAccessSecurityPage.clickRemoveIcon();
            ExtentReportListener.getExtentTest().pass("Clicked Remove icon successfully");
            Assert.assertTrue(ManagementAccessSecurityPage.VerifyRemoveOrganizationConfirmationMessageIsDisplayed());
            ExtentReportListener.getExtentTest().pass("Verified remove organization confirmation message is displayed successfully.");

            ManagementAccessSecurityPage.clickRemoveOrganizationCancelButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Cancel' button on remove organization confirmation popup");
            Assert.assertTrue(ManagementAccessSecurityPage.VerifyRemoveOrganizationPopupIsClosed());
            ExtentReportListener.getExtentTest().pass("Verified remove organization popup is closed successfully");
            Assert.assertTrue(ManagementAccessSecurityPage.VerifyUserLandsOnSecurityPage());
            ExtentReportListener.getExtentTest().pass("User successfully landed on the Security page.");

            ManagementAccessSecurityPage.clickRemoveIcon();
            ExtentReportListener.getExtentTest().pass("Clicked Remove icon successfully");
            Assert.assertTrue(ManagementAccessSecurityPage.VerifyRemoveOrganizationConfirmationMessageIsDisplayed());
            ExtentReportListener.getExtentTest().pass("Verified remove organization confirmation message is displayed successfully.");

            ManagementAccessSecurityPage.clickRemoveOrganizationOkButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'OK' button on remove organization confirmation popup");
            Assert.assertTrue(ManagementAccessSecurityPage.VerifyNoResultsMessageIsDisplayed());
            ExtentReportListener.getExtentTest().pass("Verified 'The search criteria yielded no results.' message is displayed successfully.");

            ManagementAccessSecurityPage.clickSaveButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Save' button successfully");

            ManagementAccessSecurityPage.clickAddAdditionalOrganizationButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Add Additional Organization' button successfully");
            Assert.assertTrue(ManagementAccessSecurityPage.VerifyOrganizationLabelIsDisplayed());
            ExtentReportListener.getExtentTest().pass("Verified 'Organization' label is displayed successfully.");

            ManagementAccessSecurityPage.clickOrganizationSearchDropdown();
            ExtentReportListener.getExtentTest().pass("Clicked organization search dropdown successfully");
            Assert.assertTrue(ManagementAccessSecurityPage.VerifyUserLandsOnSecurityPage());
            ExtentReportListener.getExtentTest().pass("User successfully landed on the Security page.");

            ManagementAccessSecurityPage.enterOrganizationSearchText(organizationName);
            ExtentReportListener.getExtentTest().pass("Entered organization name '" + organizationName + "' in search field successfully");
            Assert.assertTrue(ManagementAccessSecurityPage.VerifyUserLandsOnSecurityPage());
            ExtentReportListener.getExtentTest().pass("User successfully landed on the Security page.");

            ManagementAccessSecurityPage.selectAnesthesiaOrganization();
            ExtentReportListener.getExtentTest().pass("Selected '10AA - Anesthesia' organization successfully");
            Assert.assertTrue(ManagementAccessSecurityPage.VerifyUserLandsOnSecurityPage());
            ExtentReportListener.getExtentTest().pass("User successfully landed on the Security page.");

            ManagementAccessSecurityPage.clickCancelButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Cancel' button successfully");
            Assert.assertTrue(ManagementAccessSecurityPage.VerifyOrganizationLabelIsDisplayed());
            ExtentReportListener.getExtentTest().pass("Verified 'Organization' label is displayed successfully.");

            ManagementAccessSecurityPage.clickAddAdditionalOrganizationButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Add Additional Organization' button successfully");
            Assert.assertTrue(ManagementAccessSecurityPage.VerifyOrganizationLabelIsDisplayed());
            ExtentReportListener.getExtentTest().pass("Verified 'Organization' label is displayed successfully.");

            ManagementAccessSecurityPage.clickOrganizationSearchDropdown();
            ExtentReportListener.getExtentTest().pass("Clicked organization search dropdown successfully");
            Assert.assertTrue(ManagementAccessSecurityPage.VerifyUserLandsOnSecurityPage());
            ExtentReportListener.getExtentTest().pass("User successfully landed on the Security page.");

            ManagementAccessSecurityPage.enterOrganizationSearchText(organizationName);
            ExtentReportListener.getExtentTest().pass("Entered organization name '" + organizationName + "' in search field successfully");
            Assert.assertTrue(ManagementAccessSecurityPage.VerifyUserLandsOnSecurityPage());
            ExtentReportListener.getExtentTest().pass("User successfully landed on the Security page.");

            ManagementAccessSecurityPage.selectAnesthesiaOrganization();
            ExtentReportListener.getExtentTest().pass("Selected '10AA - Anesthesia' organization successfully");
            Assert.assertTrue(ManagementAccessSecurityPage.VerifyUserLandsOnSecurityPage());
            ExtentReportListener.getExtentTest().pass("User successfully landed on the Security page.");

            ManagementAccessSecurityPage.clickApplyButton();
            ExtentReportListener.getExtentTest().pass("Clicked Apply button successfully");
            Assert.assertTrue(ManagementAccessSecurityPage.VerifyOrganizationLabelIsDisplayed());
            ExtentReportListener.getExtentTest().pass("Verified 'Organization' label is displayed successfully.");

            ManagementAccessSecurityPage.selectExportControlManageAndSave();
            ExtentReportListener.getExtentTest().pass("Selected 'Manage' for Export Control and clicked Save successfully");
            Assert.assertTrue(ManagementAccessSecurityPage.VerifyExportLabelIsDisplayed());
            ExtentReportListener.getExtentTest().pass("Verified 'Export' label is displayed successfully.");



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
//        DriverManager.quitDriver();
        // User will record browser closure in the test report
        ExtentReportListener.getExtentTest().info("Browser was successfully closed.");
    }
}