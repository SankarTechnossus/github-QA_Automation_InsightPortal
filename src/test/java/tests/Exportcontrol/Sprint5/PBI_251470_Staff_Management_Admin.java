package tests.Exportcontrol.Sprint5;


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
import pages.Administration.Forms_Management.FormsManagement_ExportControlPage;
import pages.Administration.People_Management.PeopleManagement_ExportControlPage;
import pages.Export_Control.Actions.CreateExportControlPage;
import pages.Home.DashboardPage;
import pages.LoginPage;
import utils.DriverManager;
import utils.JsonDataReader;
import utils.UniqueNameGenerator;

import java.time.Duration;

@Listeners(ExtentReportListener.class)

public class PBI_251470_Staff_Management_Admin {

    WebDriver driver;
    WebDriverWait wait;
    BasePage basePage;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    PeopleManagement_ExportControlPage peopleManagementExportControlPage;

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
        peopleManagementExportControlPage = new PeopleManagement_ExportControlPage(driver);
    }

    @Test
    public void StaffManagementAdmin ()
    {
        try
        {
            String url = JsonDataReader.get(0,"URL");
            String userName = JsonDataReader.get(0,"Username");
            String password = JsonDataReader.get(0,"Password");

            // User will open the login page of the Insight Portal application
            driver.get(url);
            ExtentReportListener.getExtentTest().info("Opened dashboard URL");

            // User will wait for the login screen to load completely before performing actions
            basePage.pause(20000);

            // Login into the application
            loginPage.enterUsername(userName);
            loginPage.clickNext();
            loginPage.enterPassword(password);
            loginPage.clickVerify();

            // Optional: pause if any post-login actions needed
            basePage.pause(30000);

            Assert.assertTrue(dashboardPage.VerifyUserLandsOnDashboardPage());
            ExtentReportListener.getExtentTest().pass("User logged into the application successfully and lands on the dashboard page.");

            // Navigate to Administration module
            dashboardPage.NavigateToAdministrationModule();
            ExtentReportListener.getExtentTest().info("User navigated to Administration module.");

            // Navigate to Export Control under Forms Management
            peopleManagementExportControlPage.NavigateToPeopleManagementExportControlPage();
            Assert.assertEquals(driver.getCurrentUrl(), "https://hollywood-insight4.partners.org/administration/people-management");
            ExtentReportListener.getExtentTest().pass("User navigated to Export Control page under People Management.");





            // End User Logout
            dashboardPage.UserLogout();
            ExtentReportListener.getExtentTest().info("User successfully logged out of the application.");
        }
        catch (Exception e)
        {
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