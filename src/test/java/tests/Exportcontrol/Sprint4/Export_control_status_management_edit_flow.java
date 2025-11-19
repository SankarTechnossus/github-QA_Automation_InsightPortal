package tests.Exportcontrol.Sprint4;


import base.BasePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import listeners.ExtentReportListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.Adobe.AgreementPage;
import pages.Administration.ExportControl_statusManagement_Page;
import utils.DriverManager;
import workflow_helper.LoginPageHelper;

import java.time.Duration;

@Listeners(listeners.ExtentReportListener.class)

public class Export_control_status_management_edit_flow {

    WebDriver driver;
    WebDriverWait wait;
    BasePage basePage;
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
    }
    @Test
    public void Export_control_status_management_edit_flow() {
        ExtentReportListener.getExtentTest().info("your log message");
        try {
            // User will open the login page of the Insight Portal application
            driver.get("https://hollywood-insight4.partners.org/");
            ExtentReportListener.getExtentTest().info("Opened dashboard URL");

            // User will wait for the login screen to load completely before performing actions
            basePage.pause(20000);

            // Create an instance of LoginPage
            LoginPageHelper loginPageHelper =new LoginPageHelper(driver);
            loginPageHelper.enterUserName("SV1179");
            ExtentReportListener.getExtentTest().pass("Entered username");

            loginPageHelper.clickNext();
            ExtentReportListener.getExtentTest().pass("Clicked Next");

            loginPageHelper.enterPassword();
            ExtentReportListener.getExtentTest().pass("Entered password");

            loginPageHelper.clickVerify();
            ExtentReportListener.getExtentTest().pass("Clicked Verify");

            // Optional: pause if any post-login actions needed
            basePage.pause(20000);

            // Agreement Page Actions
            AgreementPage agreementPage = new AgreementPage(driver);
            basePage.pause(10000);
            agreementPage.clickAdministrationLink();
            ExtentReportListener.getExtentTest().pass("Clicked Administration link");

            // StatusManagement Page Actions
            ExportControl_statusManagement_Page statusManagementPage  = new ExportControl_statusManagement_Page(driver);


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