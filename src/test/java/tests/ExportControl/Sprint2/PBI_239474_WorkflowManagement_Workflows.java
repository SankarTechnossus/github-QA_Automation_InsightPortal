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
//import pages.Administration.workflowsPageNew;
import pages.Administration.Workflow_Management.WorkflowsPage;
import pages.Adobe.AgreementPage;
import pages.Home.DashboardPage;
import pages.Home.LoginPage;
import utils.DriverManager;
import utils.JsonDataReader;

import java.time.Duration;


@Listeners(listeners.ExtentReportListener.class)
public class PBI_239474_WorkflowManagement_Workflows {

    WebDriver driver;
    WebDriverWait wait;
    BasePage basePage;
    LoginPage loginPage;
    DashboardPage dashboardPage;


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

    }

    @Test
    public void ExportControl_WorkflowManagement_Workflows_Test () {
        ExtentReportListener.getExtentTest().info("your log message");
        try {
            String url = JsonDataReader.get(0,"URL");
            String userName = JsonDataReader.get(0,"Username");
            String password = JsonDataReader.get(0,"Password");

            String workflowNamePrefix = JsonDataReader.get(1, "WorkflowNamePrefix");                     // "Test_"
            String workflowRecordTypeOption = JsonDataReader.get(1, "WorkflowRecordTypeOption");         // "Test1"
            String workflowTransactionTypeOption = JsonDataReader.get(1, "WorkflowTransactionTypeOption"); // "tesss"
            String workflowExportControlStatusOption = JsonDataReader.get(1, "WorkflowExportControlStatusOption"); // "Draft"
            String workflowEmailFrom = JsonDataReader.get(1, "WorkflowEmailFrom");                       // "insighthelpdesk@partners.org"
            String workflowNameAppendText = JsonDataReader.get(1, "WorkflowNameAppendText");

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
            WorkflowsPage workflowsPageNew = new WorkflowsPage(driver);

            basePage.pause(10000);
            agreementPage.clickAdministrationLink();
            ExtentReportListener.getExtentTest().pass("Clicked Administration link");


            // Work flow page
            basePage.pause(5000);
            workflowsPageNew.clickWorkflowManagementLink();
            ExtentReportListener.getExtentTest().pass("Clicked 'Workflow Management' menu link successfully");


            basePage.pause(5000);
            workflowsPageNew.clickExportControlWorkflows();
            ExtentReportListener.getExtentTest().pass("Clicked 'Export Control > Workflows' successfully");


            basePage.pause(5000);
            workflowsPageNew.clickAddNewButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Add New' button successfully");


            String uniqueName = workflowNamePrefix + System.currentTimeMillis();
            basePage.pause(5000);
            workflowsPageNew.enterName(uniqueName);
            ExtentReportListener.getExtentTest().pass("Entered unique name '" + uniqueName + "' in the Name input field successfully");

            basePage.pause(5000);
            workflowsPageNew.selectOptionFromDropdown("Record Type", workflowRecordTypeOption);
            ExtentReportListener.getExtentTest().pass("Selected '" + workflowRecordTypeOption + "' from Record Type dropdown successfully");

            basePage.pause(5000);
            workflowsPageNew.selectOptionFromDropdown("Transaction Type", workflowTransactionTypeOption);
            ExtentReportListener.getExtentTest().pass("Selected '" + workflowTransactionTypeOption + "' from Transaction Type dropdown successfully");

            basePage.pause(5000);
            workflowsPageNew.selectOptionFromDropdownExportControlStatus("Export Control Status", workflowExportControlStatusOption);
            ExtentReportListener.getExtentTest().pass("Selected '" + workflowExportControlStatusOption + "' from Export Control Status dropdown successfully");

            basePage.pause(5000);
            workflowsPageNew.selectOptionFromDropdown("Email From", workflowEmailFrom);
            ExtentReportListener.getExtentTest().pass("Selected '" + workflowEmailFrom + "' from Email From dropdown successfully");

            // ** Negative_case **
            basePage.pause(5000);
            workflowsPageNew.clickCancelButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Cancel' button successfully");

            basePage.pause(5000);
            workflowsPageNew.clickAddNewButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Add New' button successfully");


            String uniqueName01 = workflowNamePrefix + System.currentTimeMillis();
            basePage.pause(5000);
            workflowsPageNew.enterName(uniqueName01);
            ExtentReportListener.getExtentTest().pass("Entered unique name '" + uniqueName01 + "' in the Name input field successfully");


            basePage.pause(5000);
            workflowsPageNew.selectOptionFromDropdown("Record Type", workflowRecordTypeOption);
            ExtentReportListener.getExtentTest().pass("Selected '" + workflowRecordTypeOption + "' from Record Type dropdown successfully");

            basePage.pause(5000);
            workflowsPageNew.selectOptionFromDropdown("Transaction Type", workflowTransactionTypeOption);
            ExtentReportListener.getExtentTest().pass("Selected '" + workflowTransactionTypeOption + "' from Transaction Type dropdown successfully");

            basePage.pause(5000);
            workflowsPageNew.selectOptionFromDropdownExportControlStatus("Export Control Status", workflowExportControlStatusOption);
            ExtentReportListener.getExtentTest().pass("Selected '" + workflowExportControlStatusOption + "' from Export Control Status dropdown successfully");

            basePage.pause(5000);
            workflowsPageNew.selectOptionFromDropdown("Email From", workflowEmailFrom);
            ExtentReportListener.getExtentTest().pass("Selected '" + workflowEmailFrom + "' from Email From dropdown successfully");

            basePage.pause(5000);
            workflowsPageNew.clickCancelButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Cancel' button successfully");

            basePage.pause(3000);
            workflowsPageNew.clickFirstEdit();
            ExtentReportListener.getExtentTest().pass("Clicked the first visible 'Edit' in Workflows grid.");

            basePage.pause(5000);
            workflowsPageNew.clickCancelButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Cancel' button successfully");

            basePage.pause(3000);
            workflowsPageNew.clickFirstEdit();
            ExtentReportListener.getExtentTest().pass("Clicked the first visible 'Edit' in Workflows grid.");

            basePage.pause(3000);
            workflowsPageNew.clickUpdateButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Update' button successfully");

            basePage.pause(5000);
            workflowsPageNew.clickCancelButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Cancel' button successfully");

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