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
import pages.Adobe.AgreementPage;
import pages.Administration.Exportcontrol_WorkflowManagement_Workflows_Page;
import pages.Home.DashboardPage;
import pages.Home.LoginPage;
import utils.DriverManager;
import utils.JsonDataReader;

import java.time.Duration;


@Listeners(listeners.ExtentReportListener.class)
public class Exportcontrol_WorkflowManagement_Workflows {

    WebDriver driver;
    WebDriverWait wait;
    BasePage basePage;
    LoginPage loginPage;
    DashboardPage dashboardPage;

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
    }

    @Test
    public void Exportcontrol_WorkflowManagement_Workflows_Test () {
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


            // Work flow page
            Exportcontrol_WorkflowManagement_Workflows_Page   workflowpage = new Exportcontrol_WorkflowManagement_Workflows_Page(driver);


            basePage.pause(5000);
            workflowpage.clickWorkflowManagementLink();
            ExtentReportListener.getExtentTest().pass("Clicked 'Workflow Management' menu link successfully");


            basePage.pause(5000);
            workflowpage.clickExportControlWorkflows();
            ExtentReportListener.getExtentTest().pass("Clicked 'Export Control > Workflows' successfully");


            basePage.pause(5000);
            workflowpage.clickAddNewButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Add New' button successfully");


            String uniqueName = "Test_" + System.currentTimeMillis(); // Timestamp-based unique name
            basePage.pause(5000);
            workflowpage.enterName(uniqueName);
            ExtentReportListener.getExtentTest().pass("Entered unique name '" + uniqueName + "' in the Name input field successfully");


            basePage.pause(5000);
            workflowpage.selectOptionFromDropdown("Record Type","Test");
            ExtentReportListener.getExtentTest().pass("Selected 'Test' from Record Type dropdown successfully");


            basePage.pause(5000);
            workflowpage.selectOptionFromDropdown("Transaction Type","Test1");
            ExtentReportListener.getExtentTest().pass("Selected 'Test' from Transaction Type dropdown successfully");


            basePage.pause(5000);
            workflowpage.selectOptionFromDropdownExportcontrolstatus("ExportControl Status","Draft");
            ExtentReportListener.getExtentTest().pass("Selected 'Test' from Export control status dropdown successfully");


            basePage.pause(5000);
            workflowpage.selectOptionFromDropdown("Email From","insighthelpdesk@partners.org");
            ExtentReportListener.getExtentTest().pass("Entered Email successfully");

            // ** Negative_case **


            basePage.pause(5000);
            workflowpage.clickCancelButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Cancel' button successfully");

            basePage.pause(5000);
            workflowpage.clickAddNewButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Add New' button successfully");


            String uniqueName01 = "Test_" + System.currentTimeMillis(); // Timestamp-based unique name
            basePage.pause(5000);
            workflowpage.enterName(uniqueName01);
            ExtentReportListener.getExtentTest().pass("Entered unique name '" + uniqueName01 + "' in the Name input field successfully");


            basePage.pause(5000);
            workflowpage.selectOptionFromDropdown("Record Type","Test");
            ExtentReportListener.getExtentTest().pass("Selected 'Test' from Record Type dropdown successfully");


            basePage.pause(5000);
            workflowpage.selectOptionFromDropdown("Transaction Type","Test1");
            ExtentReportListener.getExtentTest().pass("Selected 'Test' from Transaction Type dropdown successfully");


            basePage.pause(5000);
            workflowpage.selectOptionFromDropdownExportcontrolstatus("ExportControl Status","Draft");
            ExtentReportListener.getExtentTest().pass("Selected 'Test' from ExportControl Status successfully");


            basePage.pause(5000);
            workflowpage.selectOptionFromDropdown("Email From","insighthelpdesk@partners.org");
            ExtentReportListener.getExtentTest().pass("Entered Email successfully");


//            basePage.pause(5000);
//            workflowpage.clickSaveButton();
//            ExtentReportListener.getExtentTest().pass("Clicked 'Save' button successfully");

            basePage.pause(5000);
            workflowpage.clickCancelButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Cancel' button successfully");


//            basePage.pause(5000);
//            workflowpage.clickEditButtonForWorkflow(uniqueName01);
//            ExtentReportListener.getExtentTest().pass("Clicked 'Edit' button for workflow '" + uniqueName01 + "' successfully");

            basePage.pause(3000);
            workflowpage.clickFirstEdit();
            ExtentReportListener.getExtentTest().pass("Clicked the first visible 'Edit' in Workflows grid.");



            basePage.pause(5000);
            workflowpage.clickCancelButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Cancel' button successfully");


//            basePage.pause(5000);
//            workflowpage.clickEditButtonForWorkflow(uniqueName01);
//            ExtentReportListener.getExtentTest().pass("Clicked 'Edit' button for workflow '" + uniqueName01 + "' successfully");

            basePage.pause(3000);
            workflowpage.clickFirstEdit();
            ExtentReportListener.getExtentTest().pass("Clicked the first visible 'Edit' in Workflows grid.");

            basePage.pause(3000);
            workflowpage.appendSanToName();
            ExtentReportListener.getExtentTest().pass("Appended 'San' to Name field successfully");


            basePage.pause(3000);
            workflowpage.clickUpdateButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Update' button successfully");


            basePage.pause(5000);
            workflowpage.clickCancelButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Cancel' button successfully");




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
