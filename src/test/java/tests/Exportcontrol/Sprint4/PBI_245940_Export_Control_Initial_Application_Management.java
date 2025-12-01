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
import pages.Administration.Initial_Application_Management.InitialApplicationManagement_ExportControlPage;
import pages.Home.DashboardPage;
import pages.Home.LoginPage;
import utils.DriverManager;
import utils.JsonDataReader;

import java.time.Duration;

@Listeners(listeners.ExtentReportListener.class)

public class PBI_245940_Export_Control_Initial_Application_Management {

    WebDriver driver;
    WebDriverWait wait;
    BasePage basePage;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    InitialApplicationManagement_ExportControlPage initialApplicationManagementExportControlPage;

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
        initialApplicationManagementExportControlPage = new InitialApplicationManagement_ExportControlPage(driver);
    }

    @Test
    public void Export_control_Initial_application_management() {
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

            basePage.pause(1000);
            initialApplicationManagementExportControlPage.openInitialApplicationManagementExportControl();
            ExtentReportListener.getExtentTest().pass("Opened Initial Application Management > Export Control successfully");

            basePage.pause(1000);
            initialApplicationManagementExportControlPage.clickAddInitialApplication();
            ExtentReportListener.getExtentTest().pass("Clicked 'Add Initial Application' successfully");

            basePage.pause(1000);
            String actionName = initialApplicationManagementExportControlPage.enterRandomActionAndSelectEntity();
            ExtentReportListener.getExtentTest().pass("Entered Action Name: " + actionName);

            // Step 4: Click Cancel
            basePage.pause(1000);
            initialApplicationManagementExportControlPage.clickCancel();
            ExtentReportListener.getExtentTest().pass("Clicked Cancel on modal");

            basePage.pause(1000);
            initialApplicationManagementExportControlPage.clickAddInitialApplication();
            ExtentReportListener.getExtentTest().pass("Clicked 'Add Initial Application' successfully");

            basePage.pause(1000);
            String actionName01 = initialApplicationManagementExportControlPage.enterRandomActionAndSelectEntity();
            ExtentReportListener.getExtentTest().pass("Entered Action Name: " + actionName01);

            basePage.pause(1000);
            initialApplicationManagementExportControlPage.clickAddButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Add' button successfully");

            // Step : Click first Edit button
            basePage.pause(1000);
            initialApplicationManagementExportControlPage.clickFirstEditButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Edit' button for the first Initial Application row successfully");

            basePage.pause(800);
            initialApplicationManagementExportControlPage.clickInlineCancel();
            ExtentReportListener.getExtentTest().pass("Clicked Cancel on the inline edit row successfully");

            // Step : Click first Edit button
            basePage.pause(1000);
            initialApplicationManagementExportControlPage.clickFirstEditButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Edit' button for the first Initial Application row successfully");

            basePage.pause(800);
            initialApplicationManagementExportControlPage.clickInlineSave();
            ExtentReportListener.getExtentTest().pass("Clicked Save on the inline edit row successfully");

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