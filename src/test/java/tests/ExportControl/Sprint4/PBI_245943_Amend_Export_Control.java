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
import pages.Export_Control.Actions.CreateExportControlPage;
import pages.Export_Control.Export_Control_Details.*;
import pages.Home.DashboardPage;
import pages.Home.LoginPage;
import pages.System_Admin_Flow.SystemAdminPage;
import utils.DriverManager;
import utils.JsonDataReader;

import java.time.Duration;

@Listeners(listeners.ExtentReportListener.class)

public class PBI_245943_Amend_Export_Control {

    WebDriver driver;
    WebDriverWait wait;
    BasePage basePage;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    AmendExportControlPage amendExportControlPage;
    MenuFlow menuFlow;
    SystemAdminPage systemAdminPage;
    ResponseToReviewPage responseToReviewPage;
    AddChecklistFlowPage addChecklistFlowPage;
    AgreementPage agreementPage;
    CreateExportControlPage createExportControlPage;
    DisplayChecklistFlowPage displayChecklistFlowPage;
    InitialReviewWorkflowPage initialReviewWorkflowPage;

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
        amendExportControlPage = new AmendExportControlPage(driver);
        menuFlow = new MenuFlow(driver);
        systemAdminPage = new SystemAdminPage (driver);
        responseToReviewPage =new ResponseToReviewPage (driver);
        addChecklistFlowPage = new AddChecklistFlowPage(driver);
        agreementPage = new AgreementPage(driver);
        createExportControlPage = new CreateExportControlPage(driver);
        displayChecklistFlowPage = new DisplayChecklistFlowPage (driver);
        initialReviewWorkflowPage = new InitialReviewWorkflowPage(driver);
    }

    @Test
    public void Amend_Export_control() {
        ExtentReportListener.getExtentTest().info("your log message");
        try
        {
            //********************_Test_Started_************
            String url = JsonDataReader.get(0, "URL");
            String userName = JsonDataReader.get(0, "Username");
            String password = JsonDataReader.get(0, "Password");

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

            // Step 2: Select PI Name (type 'mohan' and choose 'Chandra mohan')
            basePage.pause(2000);
            createExportControlPage.selectPiName("mohan", "Chandra, Mohan");
            ExtentReportListener.getExtentTest().pass("Typed 'mohan' and selected PI as 'Chandra mohan' successfully");

            // Step 3: Click Create button
            basePage.pause(2000);
            createExportControlPage.clickCreateButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Create' button on Create Export Control sidebar successfully");

            basePage.pause(14000);
            ExtentReportListener.getExtentTest().info("Waited for 14 seconds after clicking Create button");

            basePage.pause(2000);
            displayChecklistFlowPage.clickNewTest07();
            ExtentReportListener.getExtentTest().pass("Clicked 'New Test 07' link successfully");

            basePage.pause(1000);
            displayChecklistFlowPage.enterPhoneNumber("7550309189");
            ExtentReportListener.getExtentTest().pass("Entered phone number '7550309189' successfully");

            // Step 2: Click Next
            basePage.pause(2000);
            createExportControlPage.clickNextButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Next >' button on dynamic form successfully");

            // Step 1: Click Save
            basePage.pause(2000);
            displayChecklistFlowPage.clickSaveAction();
            ExtentReportListener.getExtentTest().pass("Clicked Save button successfully");

            // Step 2: Click Submit
            basePage.pause(2000);
            displayChecklistFlowPage.clickSubmitAction();
            ExtentReportListener.getExtentTest().pass("Clicked Submit button successfully");

            //******************_Un comment if comment is required_**************

            //            basePage.pause(2000);
            //            displayChecklistFlowPage.selectOption1();
            //            ExtentReportListener.getExtentTest().pass("Selected Option (1) radio button successfully");
            //
            //            // Step 1: Click Save
            //            basePage.pause(2000);
            //            displayChecklistFlowPage.clickSaveAction();
            //            ExtentReportListener.getExtentTest().pass("Clicked Save button successfully");
            //
            //            // Step 2: Click Submit
            //            basePage.pause(2000);
            //            displayChecklistFlowPage.clickSubmitAction();
            //            ExtentReportListener.getExtentTest().pass("Clicked Submit button successfully");

            //******************_Un comment if comment is required_**************

            basePage.pause(2000);
            responseToReviewPage.clickResponseToReview();
            ExtentReportListener.getExtentTest().pass("Clicked 'Response To Review' menu successfully");

            basePage.pause(14000);
            ExtentReportListener.getExtentTest().info("Waited for 14 seconds after clicking Create button");

            //Login to System Admin page

            String sysAdminUrl = JsonDataReader.get(0, "SysAdminURL");
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
            systemAdminPage.clickComments();
            ExtentReportListener.getExtentTest().pass("Clicked 'Comments' button successfully");

            //  Enter comment text in Comments modal
            basePage.pause(2000);
            systemAdminPage.enterCommentInModal("Test_Auto");
            ExtentReportListener.getExtentTest().pass("Entered comment text 'Test_Auto' successfully in Comments modal");

            // Click Comment button
            basePage.pause(2000);
            systemAdminPage.clickCommentButtonOnModal();
            ExtentReportListener.getExtentTest().pass("Clicked 'Comment' button on Comments modal successfully");

            basePage.pause(2000);
            systemAdminPage.clickApproveButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Approve' button successfully");

            // Step: Click "Response To Review" menu
            basePage.pause(2000);
            systemAdminPage.clickResponseToReviewMenu();
            ExtentReportListener.getExtentTest().pass("Clicked 'Response To Review' menu successfully");

            // Step 1: Fetch record #
            String recordNum = systemAdminPage.getRecordNumber();
            ExtentReportListener.getExtentTest().info("Fetched Record Number: " + recordNum);

            basePage.pause(2000);
            systemAdminPage.clickLogout();
            ExtentReportListener.getExtentTest().pass("Clicked Logout successfully");

            //User login to main dashboard

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

            basePage.pause(2000);
            amendExportControlPage.clickInitiateAmendment();
            ExtentReportListener.getExtentTest().pass("Clicked 'Initiate Amendment' button successfully");

            basePage.pause(1500);
            amendExportControlPage.clickAmendmentOkButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'OK' button on amendment confirmation popup successfully");

            basePage.pause(1000);
            amendExportControlPage.clickSave();
            ExtentReportListener.getExtentTest().pass("Clicked 'Save' button successfully");

            basePage.pause(1000);
            amendExportControlPage.clickSubmit();
            ExtentReportListener.getExtentTest().pass("Clicked 'Submit' button successfully");

            // Step 1: Enter name
            basePage.pause(1000);
            amendExportControlPage.enterYourName("Test01");
            ExtentReportListener.getExtentTest().pass("Entered Name as 'Test01' successfully");

            // Step 2: Select gender = Male
            basePage.pause(500);
            amendExportControlPage.selectGenderMale();
            ExtentReportListener.getExtentTest().pass("Selected Gender as 'Male' successfully");

            basePage.pause(1000);
            amendExportControlPage.clickSave();
            ExtentReportListener.getExtentTest().pass("Clicked 'Save' button successfully");

            basePage.pause(1000);
            amendExportControlPage.clickSubmit();
            ExtentReportListener.getExtentTest().pass("Clicked 'Submit' button successfully");

            //Login to System Admin page

            String sysAdminUrl2 = JsonDataReader.get(0, "SysAdminURL");
            String sysAdminBusinessUser2 = JsonDataReader.get(0, "SysAdminBusinessUser");

            driver.get(sysAdminUrl2);
            ExtentReportListener.getExtentTest().pass("Opened SysAdmin public URL successfully");
            basePage.pause(5000);

            basePage.pause(2000);
            loginPage.enterSysAdminBusinessUser(sysAdminBusinessUser2);
            ExtentReportListener.getExtentTest().pass("Entered SysAdmin Business User: " + sysAdminBusinessUser2 + " successfully");

            basePage.pause(2000);
            loginPage.clickSysAdminLoginButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Login' button on SysAdmin page successfully");

            basePage.pause(3000);
            dashboardPage.clickExportControlLink();
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
            amendExportControlPage.clickAmendmentAMD1Tab();
            ExtentReportListener.getExtentTest().pass("Opened 'Amendment (AMD1)' transaction tab successfully");
        } catch (Exception e) {
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