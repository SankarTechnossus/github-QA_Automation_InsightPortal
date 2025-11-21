package tests.Exportcontrol.Sprint4;


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
import pages.Export_Control.Export_control_My_actions_page;
import pages.Export_Control.Export_control_menu_flow_of_export_control_page;
import pages.Home.DashboardPage;
import pages.Home.LoginPage;
import utils.DriverManager;
import utils.JsonDataReader;

import java.time.Duration;

@Listeners(listeners.ExtentReportListener.class)

public class Export_control_My_actions {

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
    public void Export_control_My_actions() {
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

            Export_control_menu_flow_of_export_control_page menuflowexport = new Export_control_menu_flow_of_export_control_page(driver);

            basePage.pause(3000);
            menuflowexport.clickExportControlLink();
            ExtentReportListener.getExtentTest().pass("Clicked 'Export Control' module link successfully");

            Export_control_My_actions_page myactionspage = new Export_control_My_actions_page(driver);

            basePage.pause(2000);
            myactionspage.clickActionRequiredLink();
            ExtentReportListener.getExtentTest().pass("Clicked 'Action Required' from Export Control left navigation");

            basePage.pause(2000);
            // 2. Record Type = Export Control Request
            myactionspage.selectRecordTypeExportControlRequest();
            ExtentReportListener.getExtentTest().pass("Selected Record Type: Export Control Request");

            basePage.pause(2000);
            // 3. Record Number
            myactionspage.enterRecordNumber("2025E006129");
            ExtentReportListener.getExtentTest().pass("Entered Record Number: 2025E006129");

            basePage.pause(2000);
            // 4. Transaction Type = Initial Review
            myactionspage.selectTransactionTypeInitialReview();
            ExtentReportListener.getExtentTest().pass("Selected Transaction Type: Initial Review");

            basePage.pause(2000);
            // 5. Agreement Numbers
            myactionspage.enterAgreementNumbers("932840");
            ExtentReportListener.getExtentTest().pass("Entered Agreement Numbers: 932840");

            basePage.pause(2000);
            // 6. Search
            myactionspage.clickSearchButton();
            ExtentReportListener.getExtentTest().pass("Clicked Search on Action Required");

            basePage.pause(2000);
            myactionspage.clickClearSelections();
            ExtentReportListener.getExtentTest().pass("Clicked Clear Selections");

            basePage.pause(2000);
            // 3. Record Number
            myactionspage.enterRecordNumber("2025E006129");
            ExtentReportListener.getExtentTest().pass("Entered Record Number: 2025E006129");

            basePage.pause(2000);
            // 6. Search
            myactionspage.clickSearchButton();
            ExtentReportListener.getExtentTest().pass("Clicked Search on Action Required");

            basePage.pause(2000);
            // 7. Click record link in grid
            myactionspage.clickFirstRecordNumberLink();
            ExtentReportListener.getExtentTest().pass("Clicked first Record Number link '2025E006129' from Action Required grid");

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