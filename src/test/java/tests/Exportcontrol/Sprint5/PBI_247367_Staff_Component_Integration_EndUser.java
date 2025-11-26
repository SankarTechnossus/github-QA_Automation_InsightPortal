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
import pages.Administration.People_Management.PeopleManagement_ExportControlPage;
import pages.Export_Control.Actions.CreateExportControlPage;
import pages.Home.DashboardPage;
import pages.Home.LoginPage;
import utils.DriverManager;
import utils.JsonDataReader;

import java.time.Duration;

@Listeners(ExtentReportListener.class)

public class PBI_247367_Staff_Component_Integration_EndUser {

    WebDriver driver;
    WebDriverWait wait;
    BasePage basePage;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    PeopleManagement_ExportControlPage peopleManagementExportControlPage;
    CreateExportControlPage createExportControlPage;

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
        createExportControlPage = new CreateExportControlPage(driver);
    }

    @Test
    public void StaffManagement_Admin()
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
            loginPage.LoginIntoApplication(userName, password);

            Assert.assertTrue(dashboardPage.VerifyUserLandsOnDashboardPage());
            ExtentReportListener.getExtentTest().pass("User logged into the application successfully and lands on the dashboard page.");

            // Navigate to Administration module
            dashboardPage.NavigateToAdministrationModule();
            ExtentReportListener.getExtentTest().info("User navigated to Administration module.");

            // Navigate to Export Control under People Management
            peopleManagementExportControlPage.NavigateToPeopleManagementExportControlPage();
            Assert.assertEquals(driver.getCurrentUrl(), "https://hollywood-insight4.partners.org/administration/people-management");
            ExtentReportListener.getExtentTest().pass("User navigated to Export Control page under People Management.");

            // Add a new People Type and verify in the People Management list
            String peopleTypeName = basePage.GenerateRandomName(6);
            Assert.assertTrue(peopleManagementExportControlPage.AddPeopleTypeAndVerifyInThePeopleManagementList(peopleTypeName));
            ExtentReportListener.getExtentTest().pass("New People Type with name : " + peopleTypeName + " has been created successfully. Status is : Active and default role assigned to it is : General");

            // Navigate back to Dashboard page
            dashboardPage.NavigateBackToDashboardPage();
            ExtentReportListener.getExtentTest().info("User navigated back to dashboard page.");

            // Navigate back to Export Control module
            dashboardPage.NavigateToExportControlModule();
            ExtentReportListener.getExtentTest().info("User navigated to Export Control module.");

            // Navigate to Create Export Control page
            createExportControlPage.NavigateToCreateExportControlPage();
            ExtentReportListener.getExtentTest().info("User navigated to Create Export Control page.");

            // Create Export Control
            String piName = JsonDataReader.get(2,"PIName");

            createExportControlPage.CreateExportControl(piName);
            Assert.assertTrue(createExportControlPage.VerifyExportControlIsCreatedSuccessfully());
            String recordNo = createExportControlPage.GetExportControlRecordNumber();
            ExtentReportListener.getExtentTest().pass("Export Control created successfully with Record Number : " + recordNo);


















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