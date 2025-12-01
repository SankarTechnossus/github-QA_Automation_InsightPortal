package tests.Exportcontrol.Sprint2;


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
import pages.Administration.AncillaryWorkflowsPage;
import pages.Adobe.AgreementPage;
import pages.Home.DashboardPage;
import pages.Home.LoginPage;
import utils.DriverManager;
import utils.JsonDataReader;

import java.time.Duration;


@Listeners(listeners.ExtentReportListener.class)
public class PBI_239475_Export_Control_WorkflowManagement_Ancillary_Workflow_Flow {

    WebDriver driver;
    WebDriverWait wait;
    BasePage basePage;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    AncillaryWorkflowsPage ancillaryWorkflowsPage;

    @BeforeMethod
    public void setupBrowser() {
//         User will setup and configure the Chrome WebDriver using WebDriverManager
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
        ancillaryWorkflowsPage = new AncillaryWorkflowsPage(driver);
    }

    @Test
    public void Exportcontrol_WorkflowManagement_Ancillary_workflow_Test () {
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


            //Ancillary workflow
            basePage.pause(2000);
            ancillaryWorkflowsPage.clickWorkflowManagement();
            ExtentReportListener.getExtentTest().pass("Opened 'Workflow Management'");


            basePage.pause(2000);
            ancillaryWorkflowsPage.clickAncillaryWorkflowsScope3();
            ExtentReportListener.getExtentTest().pass("Opened 'Ancillary Workflows' (scopeId=3, workflowType=2)");


            basePage.pause(2000);
            ancillaryWorkflowsPage.clickAddNew();
            ExtentReportListener.getExtentTest().pass("Clicked 'Add New' button");

            String uniqueName01 = "Test_" + System.currentTimeMillis(); // Timestamp-based unique name
            basePage.pause(5000);
            ancillaryWorkflowsPage.enterNameanc(uniqueName01);
            ExtentReportListener.getExtentTest().pass("Entered unique name '" + uniqueName01 + "' in the Name input field successfully");

            basePage.pause(5000);
            ancillaryWorkflowsPage.selectOptionFromDropdownancillary("Email From","insighthelpdesk@partners.org");
            ExtentReportListener.getExtentTest().pass("Selected 'Test' from Record Type dropdown successfully");


            basePage.pause(5000);
            ancillaryWorkflowsPage.clickCancelButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Cancel' button successfully");

            basePage.pause(2000);
            ancillaryWorkflowsPage.clickAddNew();
            ExtentReportListener.getExtentTest().pass("Clicked 'Add New' button");


            String uniqueName02 = "Test_" + System.currentTimeMillis(); // Timestamp-based unique name
            basePage.pause(5000);
            ancillaryWorkflowsPage.enterNameanc(uniqueName01);
            ExtentReportListener.getExtentTest().pass("Entered unique name '" + uniqueName02 + "' in the Name input field successfully");

            basePage.pause(5000);
            ancillaryWorkflowsPage.selectOptionFromDropdownancillary("Email From","insighthelpdesk@partners.org");
            ExtentReportListener.getExtentTest().pass("Selected 'Test' from Record Type dropdown successfully");

            basePage.pause(5000);
            ancillaryWorkflowsPage.clickSaveButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Save' button successfully");

            basePage.pause(1200);
            ancillaryWorkflowsPage.clickLastEdit();
            ExtentReportListener.getExtentTest().pass("Clicked last 'Edit' on Ancillary Workflows list");


            basePage.pause(3000);
            ancillaryWorkflowsPage.appendSanToNameanc();
            ExtentReportListener.getExtentTest().pass("Appended 'San' to Name field successfully");

            basePage.pause(3000);
            ancillaryWorkflowsPage.clickUpdateButtonanc();
            ExtentReportListener.getExtentTest().pass("Clicked 'Update' button successfully");

        } catch (Exception e) {
            // User will capture and log any exceptions that occur during the test
            ExtentReportListener.getExtentTest().fail("Test failed due to exception: " + e.getMessage());
        }
    }

    @AfterMethod
    public void tearDown() {

//        DriverManager.quitDriver();
        // User will record browser closure in the test report
        ExtentReportListener.getExtentTest().info("Browser was successfully closed.");

    }

}
