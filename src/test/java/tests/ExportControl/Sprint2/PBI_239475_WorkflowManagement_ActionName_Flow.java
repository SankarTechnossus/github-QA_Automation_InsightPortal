package tests.ExportControl.Sprint2;


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
import pages.Administration.Workflow_Management.ActionNamePage;
import pages.Adobe.AgreementPage;
import pages.Home.DashboardPage;
import pages.Home.LoginPage;
import utils.DriverManager;
import utils.JsonDataReader;

import java.time.Duration;


@Listeners(listeners.ExtentReportListener.class)
public class PBI_239475_WorkflowManagement_ActionName_Flow {

    WebDriver driver;
    WebDriverWait wait;
    BasePage basePage;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    ActionNamePage actionNamePage;

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
        actionNamePage = new ActionNamePage(driver);
    }

    @Test
    public void ExportControl_WorkflowManagement_ActionName_Test () {
        ExtentReportListener.getExtentTest().info("your log message");
        try {
            String url = JsonDataReader.get(0,"URL");
            String userName = JsonDataReader.get(0,"Username");
            String password = JsonDataReader.get(0,"Password");

            String actionNamePrefix = JsonDataReader.get(1, "ActionNamePrefix");       // e.g. "Test_"
            String actionHistoryTitle = JsonDataReader.get(1, "ActionHistoryTitle");   // e.g. "TestSan"

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

            basePage.pause(2000);
            actionNamePage.clickWorkflowManagementaction();
            ExtentReportListener.getExtentTest().pass("Opened 'Workflow Management'");

            basePage.pause(2000);
            actionNamePage.clickActionNameScope3();
            ExtentReportListener.getExtentTest().pass("Opened 'Action name' (scopeId=3)");

            basePage.pause(5000);
            actionNamePage.clickAddNewButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Add New' button successfully");

            basePage.pause(5000);
            String uniqueName03 = actionNamePrefix + System.currentTimeMillis();
            actionNamePage.enterName(uniqueName03);
            ExtentReportListener.getExtentTest().pass("Entered unique name '" + uniqueName03 + "' in the Name input field successfully");

            basePage.pause(5000);
            actionNamePage.enterHistoryTitle(actionHistoryTitle);
            ExtentReportListener.getExtentTest().pass("Entered '" + actionHistoryTitle + "' into History Title input field successfully");

            basePage.pause(5000);
            actionNamePage.clickCancelButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Cancel' button successfully");

            basePage.pause(5000);
            actionNamePage.clickAddNewButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Add New' button successfully");

            basePage.pause(5000);
            String uniqueName5 = actionNamePrefix + System.currentTimeMillis();
            actionNamePage.enterName(uniqueName5);
            ExtentReportListener.getExtentTest().pass("Entered unique name '" + uniqueName5 + "' in the Name input field successfully");

            basePage.pause(5000);
            actionNamePage.enterHistoryTitle(actionHistoryTitle);
            ExtentReportListener.getExtentTest().pass("Entered '" + actionHistoryTitle + "' into History Title input field successfully");

            basePage.pause(5000);
            actionNamePage.clickAddButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Add' button successfully");

            basePage.pause(5000);
            actionNamePage.clickEditForActionName(uniqueName5);
            ExtentReportListener.getExtentTest().pass("Clicked 'Edit' for action name '" + uniqueName5 + "' successfully");

            basePage.pause(5000);
            actionNamePage.clickCancelForActionName(uniqueName5);
            ExtentReportListener.getExtentTest().pass("Clicked 'Cancel' for action name '" + uniqueName5 + "' successfully");

            basePage.pause(5000);
            actionNamePage.clickEditForActionName(uniqueName5);
            ExtentReportListener.getExtentTest().pass("Clicked 'Edit' for action name '" + uniqueName5 + "' successfully");

            basePage.pause(5000);
            actionNamePage.clickSaveForActionName(uniqueName5);
            ExtentReportListener.getExtentTest().pass("Clicked 'Save' for action name '" + uniqueName5 + "' successfully");

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