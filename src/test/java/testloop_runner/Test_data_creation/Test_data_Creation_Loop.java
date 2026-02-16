package testloop_runner.Test_data_creation;

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
import pages.System_Admin_Flow.SystemAdminPage;
import utils.DriverManager;
import utils.JsonDataReader;
import utils.UniqueNameGenerator;

import java.time.Duration;

@Listeners(ExtentReportListener.class)
@Test (groups = {"regression", "integration"})
public class Test_data_Creation_Loop {

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
    MyActionsPage myActionsPage;
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
        myActionsPage = new MyActionsPage(driver);
        createExportControlPage = new CreateExportControlPage(driver);
    }

    @Test
    public void Test_data_Creation_Loop01() {
        try {
            String url = JsonDataReader.get(0, "URLTucson");
            String userName = JsonDataReader.get(0, "Username");
            String password = JsonDataReader.get(0, "Password");
            String piSearchText = JsonDataReader.get(3, "InitialReviewPiSearchText"); // "mohan"
            String piFullName = JsonDataReader.get(3, "PIName"); // "Chandra, Mohan"

            driver.get(url);
            ExtentReportListener.getExtentTest().info("Opened dashboard URL");

            basePage.pause(10000);

            loginPage.LoginIntoApplication(userName, password);
            Assert.assertTrue(dashboardPage.VerifyUserLandsOnDashboardPage());
            ExtentReportListener.getExtentTest().pass("Login successful");

            int recordCount = 2;

            for (int i = 1; i <= recordCount; i++) {

                ExtentReportListener.getExtentTest().info("===== Iteration: " + i + " =====");

                dashboardPage.clickExportControlLink();
                ManagementAccessSecurityPage.clickActionsButton();
                ManagementAccessSecurityPage.clickCreateExportControl();
                ManagementAccessSecurityPage.selectExportControlRequestRadioOption();
                createExportControlPage.selectPiName(piSearchText, piFullName);
                ExtentReportListener.getExtentTest().info("Selected PI as '" + piFullName + "'");
                createExportControlPage.clickCreateButton();
                ManagementAccessSecurityPage.selectEncryptionSourceCodeNo();
                ManagementAccessSecurityPage.selectPublicationRestrictionNo();
                ManagementAccessSecurityPage.selectConfidentialityRequirementNo();
                ManagementAccessSecurityPage.selectRestrictionOnForeignPersonNo();
                ManagementAccessSecurityPage.selectSponsorPermissionToClaimResultNo();
                ManagementAccessSecurityPage.selectExportControlledOrITARControlledNo();
                ManagementAccessSecurityPage.selectTransfersControlsLicensingNo();
                createExportControlPage.clickNext();
                ManagementAccessSecurityPage.clickConfirmSignOffCheckbox();
                displayChecklistFlowPage.clickSaveAction();
                displayChecklistFlowPage.clickSubmitAction();

                ExtentReportListener.getExtentTest().pass("Record created successfully for iteration: " + i);

                basePage.pause(3000);
            }

        } catch (Exception e) {
            ExtentReportListener.getExtentTest().fail("Test failed due to exception: " + e.getMessage());
            Assert.fail("Test failed due to exception: " + e.getMessage());
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