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
import pages.System_Admin_Flow.SystemAdminPage;
import utils.DriverManager;
import utils.JsonDataReader;
import utils.UniqueNameGenerator;

import java.time.Duration;

@Listeners(ExtentReportListener.class)
@Test (groups = {"regression", "integration"})
public class PBI_241725_End_User_Export_Control_Notification {

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
        workflowsPage = new WorkflowsPage(driver);
    }

    @Test
    public void PBI_241725_End_User_Export_Control_Notification ()
    {
        try {
            String url = JsonDataReader.get(0, "URL");
            String userName = JsonDataReader.get(0, "Username");
            String password = JsonDataReader.get(0, "Password");

            String templateNoticeGroup             = JsonDataReader.get(1, "TemplateNoticeGroup");
            String templateLayout                  = JsonDataReader.get(1, "TemplateLayout");
            String templateReminderFrequency       = JsonDataReader.get(1, "TemplateReminderFrequency");
            String notificationTypeApproved        = JsonDataReader.get(1, "NotificationTypeApproved");

            // User will open the login page of the Insight Portal application
            driver.get(url);
            ExtentReportListener.getExtentTest().info("Opened dashboard URL");

            // User will wait for the login screen to load completely before performing actions
            basePage.pause(20000);

            // Login into the application
            loginPage.LoginIntoApplication(userName, password);
            Assert.assertTrue(dashboardPage.VerifyUserLandsOnDashboardPage());
            ExtentReportListener.getExtentTest().pass("User logged into the application successfully and lands on the dashboard page.");


            //**********Communication_Management_Flow 3_***************

            dashboardPage.clickExportControlLink();
            ExtentReportListener.getExtentTest().info("Clicked 'Export Control' module link successfully");

            communicationManagementExportControlPage.clickNotificationsMenu();
            ExtentReportListener.getExtentTest().info("Clicked 'Notifications' menu link successfully from left navigation");
            Assert.assertTrue(communicationManagementExportControlPage.isNotificationsScreenTitleDisplayed(), "'Notifications' screen title is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Notifications' screen title is displayed");

            communicationManagementExportControlPage.clickFirstPlusButton();
            ExtentReportListener.getExtentTest().info("Clicked first '+' expand button successfully");
            Assert.assertTrue(communicationManagementExportControlPage.isNotificationsScreenTitleDisplayed(), "'Notifications' screen title is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Notifications' screen title is displayed");

            communicationManagementExportControlPage.clickCollapseButton();
            ExtentReportListener.getExtentTest().info("Clicked '-' collapse button successfully");
            Assert.assertTrue(communicationManagementExportControlPage.isNotificationsScreenTitleDisplayed(), "'Notifications' screen title is NOT displayed");
            ExtentReportListener.getExtentTest().pass("Verified 'Notifications' screen title is displayed");
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