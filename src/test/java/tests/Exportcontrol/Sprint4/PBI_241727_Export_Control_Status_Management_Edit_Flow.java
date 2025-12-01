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
import pages.Administration.Status_Management.StatusManagement_ExportControlPage;
import pages.Home.DashboardPage;
import pages.Home.LoginPage;
import utils.DriverManager;
import utils.JsonDataReader;

import java.time.Duration;

@Listeners(listeners.ExtentReportListener.class)

public class PBI_241727_Export_Control_Status_Management_Edit_Flow {

    WebDriver driver;
    WebDriverWait wait;
    BasePage basePage;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    StatusManagement_ExportControlPage statusManagementExportControlPage;

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
        statusManagementExportControlPage = new StatusManagement_ExportControlPage(driver);
    }

    @Test
    public void Export_control_status_management_edit_flow() {
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

            // StatusManagement Page Actions
            basePage.pause(800);
            statusManagementExportControlPage.clickStatusManagementLink();
            ExtentReportListener.getExtentTest().pass("Clicked 'Status Management' link successfully");

            basePage.pause(800);
            statusManagementExportControlPage.clickStatusManagementExportControl();
            ExtentReportListener.getExtentTest().pass("Clicked 'Status Management > Export Control' successfully");

            basePage.pause(800);
            statusManagementExportControlPage.clickAddStatusButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Add Status' button successfully");

            basePage.pause(800);
            String statusName = "Test" + new java.text.SimpleDateFormat("HHmmss").format(new java.util.Date());
            statusManagementExportControlPage.enterStatusName(statusName);

            ExtentReportListener.getExtentTest().pass("Entered '" + statusName + "' into Status Name input field");

            basePage.pause(800);
            statusManagementExportControlPage.clickAddButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Add' button successfully");

            basePage.pause(800);
            statusManagementExportControlPage.clickDeleteButtonForStatus(statusName);
            ExtentReportListener.getExtentTest().pass("Clicked delete icon for: " + statusName);

            basePage.pause(800);
            statusManagementExportControlPage.clickEditButtonForStatus(statusName);
            ExtentReportListener.getExtentTest().pass("Clicked edit icon for: " + statusName);

            basePage.pause(800);
            statusManagementExportControlPage.appendToStatusName("SAN01");
            ExtentReportListener.getExtentTest().pass("Appended 'SAN01' to Status Name input field");

            basePage.pause(800);
            statusManagementExportControlPage.selectActiveAsNo();
            ExtentReportListener.getExtentTest().pass("Selected 'No' from Active dropdown");

            basePage.pause(800);
            statusManagementExportControlPage.clickSaveButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Save' button successfully");
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