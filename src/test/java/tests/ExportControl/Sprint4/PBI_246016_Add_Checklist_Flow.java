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
import pages.Export_Control.Export_Control_Details.AddChecklistFlowPage;
import pages.Home.DashboardPage;
import pages.Home.LoginPage;
import utils.DriverManager;
import utils.JsonDataReader;

import java.time.Duration;

@Listeners(listeners.ExtentReportListener.class)

public class PBI_246016_Add_Checklist_Flow {

    WebDriver driver;
    WebDriverWait wait;
    BasePage basePage;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    AddChecklistFlowPage addChecklistFlowPage;

    @BeforeMethod
    public void setupBrowser() {
        // User will setup and configure the Chrome WebDriver using WebDriverManager
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
        addChecklistFlowPage = new AddChecklistFlowPage(driver);
    }

    @Test
    public void Export_control_Add_Checklist_flow() {
        ExtentReportListener.getExtentTest().info("your log message");
        try {
            String url = JsonDataReader.get(0,"URL");
            String userName = JsonDataReader.get(0,"Username");
            String password = JsonDataReader.get(0,"Password");

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

            basePage.pause(10000);
            agreementPage.clickAdministrationLink();
            ExtentReportListener.getExtentTest().pass("Clicked Administration link");

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
            String versionDescription = "Test01_" + basePage.GenerateRandomName(6);

            basePage.pause(1000);
            addChecklistFlowPage.enterDescriptionForLatestVersion(versionDescription);
            ExtentReportListener.getExtentTest().pass("Entered description for Version 90 as: " + versionDescription);

            basePage.pause(1000);
            addChecklistFlowPage.clickSaveVersion();
            ExtentReportListener.getExtentTest().pass("Clicked 'Save' button successfully for Version 90");

            basePage.pause(2000);
            addChecklistFlowPage.clickLatestVersionLink();
            ExtentReportListener.getExtentTest().pass("Clicked latest Version link successfully");

            basePage.pause(3000);
            addChecklistFlowPage.clickDraftNode();
            ExtentReportListener.getExtentTest().pass("Clicked on 'Draft (draft1)' node successfully");

            basePage.pause(2000);
            addChecklistFlowPage.clickActionsTab();
            ExtentReportListener.getExtentTest().pass("Clicked 'Actions' tab successfully");

            basePage.pause(2000);
            addChecklistFlowPage.clickSubmitAction();
            ExtentReportListener.getExtentTest().pass("Clicked 'Submit' action under Actions list successfully");

            basePage.pause(2000);
            addChecklistFlowPage.clickChecklistsToggle();
            ExtentReportListener.getExtentTest().pass("Clicked 'Checklists' section successfully");

            basePage.pause(2000);
            addChecklistFlowPage.clickUpdateButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Update' button successfully");

            basePage.pause(2000);
            addChecklistFlowPage.clickSaveButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Save' button successfully");

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