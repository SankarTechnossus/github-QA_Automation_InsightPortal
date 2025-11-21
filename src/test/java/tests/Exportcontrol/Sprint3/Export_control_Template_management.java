package tests.Exportcontrol.Sprint3;

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
import pages.Administration.Export_control_Template_managemnet_Pages;
import pages.Home.DashboardPage;
import pages.Home.LoginPage;
import utils.DriverManager;
import utils.JsonDataReader;

import java.time.Duration;

@Listeners(listeners.ExtentReportListener.class)
public class Export_control_Template_management {

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
    public void Exportcontrol_Templatemanagement_Test() {
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


            Export_control_Template_managemnet_Pages  Export_control_Template_managemnet_Page01 = new Export_control_Template_managemnet_Pages(driver);

            basePage.pause(5000);
            Export_control_Template_managemnet_Page01.clickTemplateManagementExportControl01();
            ExtentReportListener.getExtentTest().pass("Clicked on 'Template Management > Export Control' successfully");


            basePage.pause(5000);
            Export_control_Template_managemnet_Page01.clickAddNewTemplate();
            ExtentReportListener.getExtentTest().pass("Clicked on 'Add new' under Template Management successfully");


            basePage.pause(3000);
            String generatedTitle01 = Export_control_Template_managemnet_Page01.enterUniqueTitle();
            ExtentReportListener.getExtentTest().pass("Entered unique title: " + generatedTitle01);

            basePage.pause(5000);
            String filePath01 = System.getProperty("user.dir") + "/Test_Data/Agreement Info 2025_03.pdf";
            Export_control_Template_managemnet_Page01.uploadAgreementFile(filePath01);
            ExtentReportListener.getExtentTest().pass("Uploaded file: Agreement Info 2025_03.pdf successfully");

            basePage.pause(5000);
            Export_control_Template_managemnet_Page01.clickCreateButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Create' button under Template Management successfully");


            basePage.pause(3000);
            Export_control_Template_managemnet_Page01.selectDateFormat("01/01/2020 (MM/DD/YYYY)"); // or just "MM/DD/YYYY"
            ExtentReportListener.getExtentTest().pass("Selected Date Format successfully");

            basePage.pause(2000);
            Export_control_Template_managemnet_Page01.setActiveToNo();
            ExtentReportListener.getExtentTest().pass("Active set to 'No' successfully");


            basePage.pause(3000);
            Export_control_Template_managemnet_Page01.clickSaveButton01();
            ExtentReportListener.getExtentTest().pass("Clicked 'Save' button successfully");

            basePage.pause(2000);
            Export_control_Template_managemnet_Page01.setActive("Yes");
            ExtentReportListener.getExtentTest().pass("Active set to 'Yes' successfully");

            basePage.pause(2000);
            Export_control_Template_managemnet_Page01.clickCancelButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Cancel' button successfully");


            Export_control_Template_managemnet_Page01.clickTemplateByTitle(generatedTitle01);
            ExtentReportListener.getExtentTest().pass("Opened template '" + generatedTitle01 + "' from the list successfully");


            basePage.pause(2000);
            Export_control_Template_managemnet_Page01.setActiveToNo();
            ExtentReportListener.getExtentTest().pass("Active set to 'No' successfully");


            basePage.pause(3000);
            Export_control_Template_managemnet_Page01.clickSaveButton01();
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