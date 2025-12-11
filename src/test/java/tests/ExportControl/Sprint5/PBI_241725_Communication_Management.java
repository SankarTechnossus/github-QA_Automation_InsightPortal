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
import pages.Administration.Communication_Management.CommunicationManagement_ExportControlPage;
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
import pages.System_Admin_Flow.SystemAdminPage;
import utils.DriverManager;
import utils.JsonDataReader;
import utils.UniqueNameGenerator;

import java.time.Duration;

@Listeners(ExtentReportListener.class)

public class PBI_241725_Communication_Management {

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
    }

    @Test
    public void Communication_Management ()
    {
        try {
            String url = JsonDataReader.get(0, "URL");
            String userName = JsonDataReader.get(0, "Username");
            String password = JsonDataReader.get(0, "Password");

            String templateNamePrefix              = JsonDataReader.get(1, "TemplateNamePrefix");
            String templateNoticeGroup             = JsonDataReader.get(1, "TemplateNoticeGroup");
            String templateLayout                  = JsonDataReader.get(1, "TemplateLayout");
            String templateReminderFrequency       = JsonDataReader.get(1, "TemplateReminderFrequency");
            String notificationTypeApproved        = JsonDataReader.get(1, "NotificationTypeApproved");
            String workflowVersionDescriptionPref  = JsonDataReader.get(1, "WorkflowVersionDescriptionPrefix");
            // User will open the login page of the Insight Portal application
            driver.get(url);
            ExtentReportListener.getExtentTest().info("Opened dashboard URL");

            // User will wait for the login screen to load completely before performing actions
            basePage.pause(20000);

            // Login into the application
            loginPage.LoginIntoApplication(userName, password);

            Assert.assertTrue(dashboardPage.VerifyUserLandsOnDashboardPage());
            ExtentReportListener.getExtentTest().pass("User logged into the application successfully and lands on the dashboard page.");

            basePage.pause(10000);
            agreementPage.clickAdministrationLink();
            ExtentReportListener.getExtentTest().pass("Clicked Administration link");

            //**********Communication_Management_Flow 1_***************

            basePage.pause(1500);
            communicationManagementExportControlPage.expandCommunicationManagement();
            ExtentReportListener.getExtentTest().pass("Expanded Communication Management menu successfully");

            basePage.pause(800);
            communicationManagementExportControlPage.clickCommunicationExportControl();
            ExtentReportListener.getExtentTest().pass("Clicked 'Export Control' under Communication Management successfully");

            basePage.pause(1000);
            communicationManagementExportControlPage.clickAddNewTemplate();
            ExtentReportListener.getExtentTest().pass("Clicked 'Add New Template' button successfully");

            // Step 1: Generate random template name using your method
            String templateName = uniqueNameGenerator.GenerateRandomName(8);

            // Step 1 – Template Name
            basePage.pause(1000);
            communicationManagementExportControlPage.setTemplateName(templateName);
            ExtentReportListener.getExtentTest().pass("Entered Template Name as '" + templateName + "'");

            basePage.pause(1000);
            communicationManagementExportControlPage.selectNoticeGroup(templateNoticeGroup);
            ExtentReportListener.getExtentTest().pass("Selected Notice Group: '" + templateNoticeGroup + "'");

            basePage.pause(800);
            communicationManagementExportControlPage.selectLayout(templateLayout);
            ExtentReportListener.getExtentTest().pass("Selected Layout: '" + templateLayout + "'");

            basePage.pause(2000);
            communicationManagementExportControlPage.setReminderFrequency(templateReminderFrequency);
            ExtentReportListener.getExtentTest().pass("Set Reminder Frequency (days) to '" + templateReminderFrequency + "'");

            // Step 6 – Click Cancel (if requirement is to close modal after creation)
            basePage.pause(2000);
            communicationManagementExportControlPage.clickCancelTemplateButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Cancel' button on Add New Template modal successfully");

            basePage.pause(1000);
            communicationManagementExportControlPage.clickAddNewTemplate();
            ExtentReportListener.getExtentTest().pass("Clicked 'Add New Template' button successfully");

            // Step 1: Generate random template name using your method
            String templateName02 = uniqueNameGenerator.GenerateRandomName(8);

            basePage.pause(1000);
            communicationManagementExportControlPage.setTemplateName(templateName02);
            ExtentReportListener.getExtentTest().pass("Entered Template Name as '" + templateName02 + "'");

            basePage.pause(1000);
            communicationManagementExportControlPage.selectNoticeGroup(templateNoticeGroup);
            ExtentReportListener.getExtentTest().pass("Selected Notice Group: '" + templateNoticeGroup + "'");

            basePage.pause(800);
            communicationManagementExportControlPage.selectLayout(templateLayout);
            ExtentReportListener.getExtentTest().pass("Selected Layout: '" + templateLayout + "'");

            basePage.pause(800);
            communicationManagementExportControlPage.setReminderFrequency(templateReminderFrequency);
            ExtentReportListener.getExtentTest().pass("Set Reminder Frequency (days) to '" + templateReminderFrequency + "'");

            // Step 5 – Click Create Template
            basePage.pause(800);
            communicationManagementExportControlPage.clickCreateTemplateButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Create Template' button successfully");

            // Step 1 – Notification Type: Export Control Approved
            basePage.pause(3000);
            communicationManagementExportControlPage.selectNotificationType(notificationTypeApproved);
            ExtentReportListener.getExtentTest().pass("Selected Notification Type as '" + notificationTypeApproved + "' successfully");

            // Step 2 – Click Cancel
            basePage.pause(2000);
            communicationManagementExportControlPage.clickCancelButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Cancel' button successfully on Notification Template form");

            basePage.pause(2000);
            formsVisibilityExportControlPage.openExportControlUnderFormVisibility();
            ExtentReportListener.getExtentTest().pass("Opened 'Export Control' under Form Visibility successfully");

            basePage.pause(1500);
            communicationManagementExportControlPage.expandCommunicationManagement();
            ExtentReportListener.getExtentTest().pass("Expanded Communication Management menu successfully");

            basePage.pause(800);
            communicationManagementExportControlPage.clickCommunicationExportControl();
            ExtentReportListener.getExtentTest().pass("Clicked 'Export Control' under Communication Management successfully");

            basePage.pause(2000);
            communicationManagementExportControlPage.selectNotificationType(notificationTypeApproved);
            ExtentReportListener.getExtentTest().pass("Selected Notification Type as '" + notificationTypeApproved + "' successfully after reopening form");

            // Step 4 – Click Save
            basePage.pause(3000);
            communicationManagementExportControlPage.clickSaveButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Save' button successfully on Notification Template form");

            // Workflow for communication management **********_Flow 2_***************

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

            // Step 0 – Go to Notifications tab
            basePage.pause(2000);
            communicationManagementExportControlPage.clickNotificationsButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Notifications' button successfully on Communication Management page");

            basePage.pause(2000);
            addChecklistFlowPage.clickUpdateButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Update' button successfully");

            basePage.pause(2000);
            addChecklistFlowPage.clickSaveButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Save' button successfully");

            //**********Communication_Management_Flow 3_***************

            basePage.pause(3000);
            dashboardPage.clickExportControlLink();
            ExtentReportListener.getExtentTest().pass("Clicked 'Export Control' module link successfully");

            basePage.pause(2000);
            communicationManagementExportControlPage.clickNotificationsMenu();
            ExtentReportListener.getExtentTest().pass("Clicked 'Notifications' menu link successfully from left navigation");

            basePage.pause(3000);
            communicationManagementExportControlPage.clickFirstPlusButton();
            ExtentReportListener.getExtentTest().pass("Clicked first '+' expand button successfully");

            basePage.pause(1500);
            communicationManagementExportControlPage.clickCollapseButton();
            ExtentReportListener.getExtentTest().pass("Clicked '-' collapse button successfully");
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