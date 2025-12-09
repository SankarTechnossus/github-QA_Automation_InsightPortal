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
import pages.Export_Control.Actions.CreateExportControlPage;
import pages.Export_Control.Export_Control_Details.DisplayChecklistFlowPage;
import pages.Export_Control.Export_Control_Details.InitialReviewWorkflowPage;
import pages.Export_Control.Export_Control_Details.MenuFlow;
import pages.Export_Control.Export_Control_Details.NotesPage;
import pages.Home.DashboardPage;
import pages.Home.LoginPage;
import pages.System_Admin_Flow.SystemAdminPage;
import utils.DriverManager;
import utils.JsonDataReader;

import java.time.Duration;

@Listeners(listeners.ExtentReportListener.class)

public class PBI_241726_Initial_Review_Workflow {

    WebDriver driver;
    WebDriverWait wait;
    BasePage basePage;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    CreateExportControlPage createExportControlPage;
    NotesPage notesPage;
    InitialReviewWorkflowPage initialReviewWorkflowPage;
    SystemAdminPage systemAdminPage;
    DisplayChecklistFlowPage displayChecklistFlowPage;
    MenuFlow menuFlow;

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
        createExportControlPage = new CreateExportControlPage(driver);
        notesPage = new NotesPage(driver);
        initialReviewWorkflowPage = new InitialReviewWorkflowPage(driver);
        systemAdminPage = new SystemAdminPage(driver);
        displayChecklistFlowPage = new DisplayChecklistFlowPage(driver);
        menuFlow = new MenuFlow(driver);
    }

    @Test
    public void Initial_review_workflow() {
        try
        {
            String url = JsonDataReader.get(0,"URL");
            String userName = JsonDataReader.get(0,"Username");
            String password = JsonDataReader.get(0,"Password");

            String piSearchText        = JsonDataReader.get(3, "InitialReviewPiSearchText"); // "mohan"
            String piFullName          = JsonDataReader.get(3, "PIName");                   // "Chandra, Mohan"
            String initialLinkText     = JsonDataReader.get(3, "InitialReviewLinkText");     // "New Test 07"
            String initialPhoneNumber  = JsonDataReader.get(3, "InitialReviewPhoneNumber");  // "7550309189"
            String initialName         = JsonDataReader.get(3, "InitialReviewName");         // "Test01"
            String initialNoteText     = JsonDataReader.get(3, "InitialReviewNoteText");     // "Initial_Review_Done"
            String positiveSearchText = JsonDataReader.get(1, "PositiveSearchText");


            // User will open the login page of the Insight Portal application
            driver.get(url);
            ExtentReportListener.getExtentTest().info("Opened dashboard URL");

            // User will wait for the login screen to load completely before performing actions
            basePage.pause(20000);

            // Login into the application
            loginPage.LoginIntoApplication(userName, password);

            Assert.assertTrue(dashboardPage.VerifyUserLandsOnDashboardPage());
            ExtentReportListener.getExtentTest().pass("User logged into the application successfully and lands on the dashboard page.");

            basePage.pause(3000);
            dashboardPage.clickExportControlLink();
            ExtentReportListener.getExtentTest().pass("Clicked 'Export Control' module link successfully");

            basePage.pause(3000);
            createExportControlPage.clickCreateExportControl();
            ExtentReportListener.getExtentTest().pass("Clicked Actions → Create Export Control from left navigation successfully");

            basePage.pause(2000);
            displayChecklistFlowPage.selectPersonnelExclusion();
            ExtentReportListener.getExtentTest().pass("Selected Personnel Exclusion radio button successfully");

            // Step 2: Select PI Name (type from JSON and choose PI name from JSON)
            basePage.pause(2000);
            createExportControlPage.selectPiName(piSearchText, piFullName);
            ExtentReportListener.getExtentTest().pass("Typed '" + piSearchText + "' and selected PI as '" + piFullName + "' successfully");

            // Step 3: Click Create button
            basePage.pause(2000);
            createExportControlPage.clickCreateButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Create' button on Create Export Control sidebar successfully");

            basePage.pause(14000);
            ExtentReportListener.getExtentTest().info("Waited for 14 seconds after clicking Create button");

            // Step 4: Click Submit
            basePage.pause(2000);
            displayChecklistFlowPage.clickSubmitAction();
            ExtentReportListener.getExtentTest().pass("Clicked Submit button successfully");

            // Check the status of request and it should be "Under Review"
            basePage.pause(14000);
            ExtentReportListener.getExtentTest().info("Waited for 14 seconds after clicking Create button");

            basePage.pause(1000);
            initialReviewWorkflowPage.clickInitialReview();
            ExtentReportListener.getExtentTest().pass("Clicked Initial Review (IR) successfully");

            //***************Login to System Admin page********************

            String sysAdminUrl         = JsonDataReader.get(0, "SysAdminURL");
            String sysAdminBusinessUser = JsonDataReader.get(0, "SysAdminBusinessUser");

            driver.get(sysAdminUrl);
            ExtentReportListener.getExtentTest().pass("Opened SysAdmin public URL successfully");
            basePage.pause(5000);

            basePage.pause(2000);
            loginPage.enterSysAdminBusinessUser(sysAdminBusinessUser);
            ExtentReportListener.getExtentTest().pass("Entered SysAdmin Business User: " + sysAdminBusinessUser + " successfully");

            basePage.pause(2000);
            loginPage.clickSysAdminLoginButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Login' button on SysAdmin page successfully");

            basePage.pause(3000);
            dashboardPage.clickExportControlLink();
            ExtentReportListener.getExtentTest().pass("Clicked 'Export Control' module link successfully");

            basePage.pause(2000);
            createExportControlPage.clickActionRequired();
            ExtentReportListener.getExtentTest().pass("Clicked 'Action Required' menu successfully");

            basePage.pause(2000);
            systemAdminPage.clickFirstRecordNumber();
            ExtentReportListener.getExtentTest().pass("Clicked first Record Number in Action Required grid successfully");

            basePage.pause(14000);
            ExtentReportListener.getExtentTest().info("Waited for 14 seconds after clicking Create button");

            basePage.pause(2000);
            systemAdminPage.clickApproveButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Approve' button successfully");

            basePage.pause(2000);
            notesPage.clickNotesSection();
            ExtentReportListener.getExtentTest().pass("Clicked Notes section successfully");

            basePage.pause(2000);
            notesPage.clickAddNoteButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Add Note' button successfully");

            // Step 1: Fetch record
            String recordNum = systemAdminPage.getRecordNumber();
            ExtentReportListener.getExtentTest().info("Fetched Record Number: " + recordNum);

            basePage.pause(2000);
            notesPage.enterNotesAndClickAdd(initialNoteText);
            ExtentReportListener.getExtentTest().pass("Entered '" + initialNoteText + "' in Notes and clicked 'Add' successfully");

            basePage.pause(2000);
            ExtentReportListener.getExtentTest().pass("Wait until the page notes is getting loaded");

            basePage.pause(2000);
            systemAdminPage.clickApproveButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Approve' button successfully");

            basePage.pause(2000);
            systemAdminPage.clickLogout();
            ExtentReportListener.getExtentTest().pass("Clicked Logout successfully");

            //****************_User login to main dashboard_************

            String url1 = JsonDataReader.get(0, "URL");
            String userName1 = JsonDataReader.get(0, "Username");
            String password1 = JsonDataReader.get(0, "Password");

            // User will open the login page of the Insight Portal application
            driver.get(url1);
            ExtentReportListener.getExtentTest().info("Opened dashboard URL");

            // User will wait for the login screen to load completely before performing actions
            basePage.pause(20000);

            // Login into the application
            loginPage.LoginIntoApplication(userName1, password1);

            Assert.assertTrue(dashboardPage.VerifyUserLandsOnDashboardPage());
            ExtentReportListener.getExtentTest().pass("User logged into the application successfully and lands on the dashboard page.");

            basePage.pause(2000);
            menuFlow.clickExportControlLink();
            ExtentReportListener.getExtentTest().pass("Clicked 'Export Control' module link successfully");

            basePage.pause(2000);
            menuFlow.clickSearchLink();
            ExtentReportListener.getExtentTest().pass("Clicked 'Search' link successfully from Export Control sidebar");

            basePage.pause(2000);
            systemAdminPage.enterValueField(recordNum);
            ExtentReportListener.getExtentTest().pass("Successfully entered dynamic record number: " + recordNum);

            basePage.pause(2000);
            menuFlow.clickSearchButton();
            ExtentReportListener.getExtentTest().pass("Clicked Search");

            // After you search and the grid is loaded:
            basePage.pause(2000);
            systemAdminPage.clickRecordNumber(recordNum);
            ExtentReportListener.getExtentTest().pass("Clicked Record Number link in grid: " + recordNum);

            basePage.pause(14000);
            ExtentReportListener.getExtentTest().info("Waited for 14 seconds after clicking Create button");

            basePage.pause(1000);
            initialReviewWorkflowPage.clickInitialReview();
            ExtentReportListener.getExtentTest().pass("Clicked Initial Review (IR) successfully");

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