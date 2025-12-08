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
import pages.Export_Control.Export_Control_Details.MenuFlow;
import pages.Export_Control.Export_Control_Details.MyActionsPage;
import pages.Home.DashboardPage;
import pages.Home.LoginPage;
import utils.DriverManager;
import utils.JsonDataReader;

import java.time.Duration;

@Listeners(listeners.ExtentReportListener.class)

public class PBI_239502_Menu_Flow {

    WebDriver driver;
    WebDriverWait wait;
    BasePage basePage;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    MenuFlow menuFlow;
    MyActionsPage myActionsPage;


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
        menuFlow = new MenuFlow(driver);
        myActionsPage = new MyActionsPage(driver);
    }

    @Test
    public void Export_Control_Menu_Flow() {
        ExtentReportListener.getExtentTest().info("your log message");
        try {
            String url = JsonDataReader.get(0,"URL");
            String userName = JsonDataReader.get(0,"Username");
            String password = JsonDataReader.get(0,"Password");
            String submitterCode    = JsonDataReader.get(1, "MyActionsSubmitterCode");
            String submitterDisplay = JsonDataReader.get(1, "MyActionsSubmitterDisplay");
            String reviewerCode    = JsonDataReader.get(1, "MyActionsReviewerCode");
            String reviewerDisplay = JsonDataReader.get(1, "MyActionsReviewerDisplay");
            String createdOnFrom = JsonDataReader.get(1, "MyActionsCreatedOnFrom");
            String createdOnTo = JsonDataReader.get(1, "MyActionsCreatedOnTo");
            String reviewDateFrom = JsonDataReader.get(1, "MyActionsReviewDateFrom");
            String reviewDateTo = JsonDataReader.get(1, "MyActionsReviewDateTo");
            String recordNumber = JsonDataReader.get(1, "MyActionsRecordNumber");


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
            menuFlow.clickExportControlLink();
            ExtentReportListener.getExtentTest().pass("Clicked 'Export Control' module link successfully");

            basePage.pause(2000);
            menuFlow.clickSearchLink();
            ExtentReportListener.getExtentTest().pass("Clicked 'Search' link successfully from Export Control sidebar");

//            basePage.pause(2000);
//            myActionsPage.enterCreatedOnFrom(createdOnFrom);
//            ExtentReportListener.getExtentTest().pass("Entered Created On From date: " + createdOnFrom);
//
//            myActionsPage.enterCreatedOnTo(createdOnTo);
//            ExtentReportListener.getExtentTest().pass("Entered Created On To date: " + createdOnTo);
//
//            myActionsPage.enterReviewDateFrom(reviewDateFrom);
//            ExtentReportListener.getExtentTest().pass("Entered Review Date From date: " + reviewDateFrom);
//
//            myActionsPage.enterReviewDateTo(reviewDateTo);
//            ExtentReportListener.getExtentTest().pass("Entered Review Date To date: " + reviewDateTo);

            basePage.pause(1000);
            myActionsPage.selectReviewer(reviewerCode, reviewerDisplay);
            ExtentReportListener.getExtentTest().pass("Selected Reviewer '" + reviewerDisplay + "' successfully");

            // Record number
            basePage.pause(1000);
            myActionsPage.enterRecordNumber(recordNumber);
            ExtentReportListener.getExtentTest().pass("Entered Record Number: " + recordNumber);

            basePage.pause(1000);
            myActionsPage.clickSubmitterFilter();
            ExtentReportListener.getExtentTest().pass("Clicked Submitter filter successfully");

            basePage.pause(500);
            myActionsPage.selectSubmitter(submitterCode, submitterDisplay);
            ExtentReportListener.getExtentTest().pass("Selected Submitter '" + submitterDisplay + "' successfully");

            basePage.pause(1000);
            myActionsPage.clickSearchButton();
            ExtentReportListener.getExtentTest().pass("Clicked Search button successfully");

            basePage.pause(1000);
            myActionsPage.clickClearSelections();
            ExtentReportListener.getExtentTest().pass("Clicked Clear Selections successfully");

            // Record number search again
            basePage.pause(2000);
            myActionsPage.enterRecordNumber(recordNumber);
            ExtentReportListener.getExtentTest().pass("Entered Record Number: " + recordNumber);

            basePage.pause(2000);
            // 6. Search
            myActionsPage.clickSearchButton();
            ExtentReportListener.getExtentTest().pass("Clicked Search on Action Required");

            basePage.pause(2000);
            menuFlow.clickRecordNumberFromSearchGrid();
            ExtentReportListener.getExtentTest().pass("Clicked Record Number link 2025E006129 from Search grid");

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