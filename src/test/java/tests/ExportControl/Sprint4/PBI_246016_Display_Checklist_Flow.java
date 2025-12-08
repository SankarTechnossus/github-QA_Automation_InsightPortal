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
import pages.Home.DashboardPage;
import pages.Home.LoginPage;
import utils.DriverManager;
import utils.JsonDataReader;

import java.time.Duration;

@Listeners(listeners.ExtentReportListener.class)

public class PBI_246016_Display_Checklist_Flow {

    WebDriver driver;
    WebDriverWait wait;
    BasePage basePage;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    CreateExportControlPage createExportControlPage;
    DisplayChecklistFlowPage displayChecklistFlowPage;
    InitialReviewWorkflowPage initialReviewWorkflowPage;

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
        displayChecklistFlowPage = new DisplayChecklistFlowPage(driver);
        initialReviewWorkflowPage = new InitialReviewWorkflowPage(driver);
    }

    @Test
    public void Export_control_Display_checklist_flow() {
        ExtentReportListener.getExtentTest().info("your log message");
        try {
            String url = JsonDataReader.get(0,"URL");
            String userName = JsonDataReader.get(0,"Username");
            String password = JsonDataReader.get(0,"Password");

            String piSearchText   = JsonDataReader.get(3, "InitialReviewPiSearchText");   // "mohan"
            String piFullName     = JsonDataReader.get(3, "PIName");                     // "Chandra, Mohan"
            String phoneNumber    = JsonDataReader.get(3, "InitialReviewPhoneNumber");   // "7550309189"
            String personName     = JsonDataReader.get(3, "InitialReviewName");
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

            basePage.pause(2000);
            createExportControlPage.selectPiName(piSearchText, piFullName);
            ExtentReportListener.getExtentTest().pass("Typed '" + piSearchText + "' and selected PI as '" + piFullName + "' successfully");

            // Step 3: Click Create button
            basePage.pause(2000);
            createExportControlPage.clickCreateButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Create' button on Create Export Control sidebar successfully");

            basePage.pause(14000);
            ExtentReportListener.getExtentTest().info("Waited for 14 seconds after clicking Create button");

//            basePage.pause(2000);
//            displayChecklistFlowPage.clickNewTest07();
//            ExtentReportListener.getExtentTest().pass("Clicked 'New Test 07' link successfully");

//            basePage.pause(1000);
//            displayChecklistFlowPage.enterPhoneNumber(phoneNumber);
//            ExtentReportListener.getExtentTest().pass("Entered phone number '" + phoneNumber + "' successfully");

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

            basePage.pause(5000);
            initialReviewWorkflowPage.enterName(positiveSearchText);
            ExtentReportListener.getExtentTest().pass("Entered name from JSON (PositiveSearchText): '" + positiveSearchText + "' successfully");

            basePage.pause(2000);
            initialReviewWorkflowPage.selectGenderMale();
            ExtentReportListener.getExtentTest().pass("Selected gender as 'Male' successfully");
//
//            // Step 1: Enter Name
//            basePage.pause(1000);
//            displayChecklistFlowPage.enterName(personName);
//            ExtentReportListener.getExtentTest().pass("Entered Name as '" + personName + "' successfully");
//
//            // Step 2: Enter Phone Number
//            basePage.pause(1000);
//            displayChecklistFlowPage.enterPhoneNumber(phoneNumber);
//            ExtentReportListener.getExtentTest().pass("Entered Phone Number as '" + phoneNumber + "' successfully");

            // Step 1: Click Save
            basePage.pause(2000);
            displayChecklistFlowPage.clickSaveAction();
            ExtentReportListener.getExtentTest().pass("Clicked Save button successfully");

            // Step 2: Click Submit
            basePage.pause(2000);
            displayChecklistFlowPage.clickSubmitAction();
            ExtentReportListener.getExtentTest().pass("Clicked Submit button successfully");

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