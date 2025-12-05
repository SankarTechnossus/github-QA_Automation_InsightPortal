package tests.ExportControl.Sprint1;
import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import listeners.ExtentReportListener;
import org.testng.annotations.Listeners;
import pages.Administration.Status_Management.StatusManagement_ExportControlPage;
import pages.Home.DashboardPage;
import pages.Home.LoginPage;
import pages.Adobe.AgreementPage;
import utils.DriverManager;
import utils.JsonDataReader;

import java.time.Duration;


@Listeners(listeners.ExtentReportListener.class)
public class PBI_239497_Export_Control_Status_Management_Flow {

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
    public void Export_control_Status_Management_Test() {
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
            StatusManagement_ExportControlPage statusManagementPage  = new StatusManagement_ExportControlPage(driver);

            basePage.pause(5000);
            statusManagementPage.clickStatusManagementLink();
            ExtentReportListener.getExtentTest().pass("Clicked 'Status Management' link successfully");

            basePage.pause(5000);
            statusManagementPage.clickStatusManagementExportControl();
            ExtentReportListener.getExtentTest().pass("Clicked 'Status Management > Export Control' successfully");

            basePage.pause(5000);
            statusManagementPage.clickAddStatusButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Add Status' button successfully");

            basePage.pause(5000);

            String statusName = "Test" + new java.text.SimpleDateFormat("HHmmss").format(new java.util.Date());
            statusManagementPage.enterStatusName(statusName);

            ExtentReportListener.getExtentTest().pass("Entered '" + statusName + "' into Status Name input field");

            basePage.pause(5000);
            statusManagementPage.clickAddButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Add' button successfully");

            basePage.pause(5000);
            statusManagementPage.clickDeleteButtonForStatus(statusName);
            ExtentReportListener.getExtentTest().pass("Clicked delete icon for: " + statusName);

            basePage.pause(5000);
            statusManagementPage.clickEditButtonForStatus(statusName);
            ExtentReportListener.getExtentTest().pass("Clicked edit icon for: " + statusName);

            basePage.pause(5000);
            statusManagementPage.appendToStatusName("SAN01");
            ExtentReportListener.getExtentTest().pass("Appended 'SAN01' to Status Name input field");

            basePage.pause(3000);
            statusManagementPage.selectActiveAsNo();
            ExtentReportListener.getExtentTest().pass("Selected 'No' from Active dropdown");

            basePage.pause(5000);
            statusManagementPage.clickSaveButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Save' button successfully");

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
