package tests.Exportcontrol.Sprint2;


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
import pages.Administration.StepNamePage;
import pages.Adobe.AgreementPage;
import pages.Administration.Workflow_Management.Export_Control.*;
import pages.Home.DashboardPage;
import pages.Home.LoginPage;
import utils.DriverManager;
import utils.JsonDataReader;

import java.time.Duration;


@Listeners(listeners.ExtentReportListener.class)
public class PBI_239474_Exportcontrol_WorkflowManagement_Stepname_flow {

    WebDriver driver;
    WebDriverWait wait;
    BasePage basePage;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    StepNamePage stepNamePage;

    @BeforeMethod
    public void setupBrowser() {
//         User will setup and configure the Chrome WebDriver using WebDriverManager
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
        stepNamePage = new StepNamePage(driver);
    }

    @Test
    public void Exportcontrol_WorkflowManagement_Stepname_Test () {
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


            basePage.pause(2000);
            stepNamePage.clickWorkflowManagementstepname();
            ExtentReportListener.getExtentTest().pass("Opened 'Workflow Management'");

            basePage.pause(5000);
            stepNamePage.clickExportControlStepName();
            ExtentReportListener.getExtentTest().pass("Opened Export Control > Step name (scopeId=3) successfully");



            basePage.pause(5000);
            stepNamePage.clickAddNewButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Add New' button successfully");



            basePage.pause(5000);
            stepNamePage.enterName("Sample Name");
            ExtentReportListener.getExtentTest().pass("Entered 'Sample Name' into Name input field successfully");



            basePage.pause(5000);
            stepNamePage.clickCancelButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Cancel' button successfully");


            basePage.pause(5000);
            stepNamePage.clickAddNewButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Add New' button successfully");


            basePage.pause(5000);
            String generatedName = stepNamePage.enterUniqueName("Test");
            ExtentReportListener.getExtentTest().pass("Entered unique name '" + generatedName + "' into Name input field successfully");


            basePage.pause(5000);
            stepNamePage.clickAddButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Add' button successfully");


            basePage.pause(5000);
            stepNamePage.clickEditButtonForStepName(generatedName);
            ExtentReportListener.getExtentTest().pass("Clicked 'Edit' button for step name '" + generatedName + "' successfully");


            basePage.pause(5000);
            stepNamePage.clickCancelForStepName(generatedName);
            ExtentReportListener.getExtentTest().pass("Clicked 'Cancel' for step name '" + generatedName + "' successfully");


            basePage.pause(5000);
            stepNamePage.clickEditButtonForStepName(generatedName);
            ExtentReportListener.getExtentTest().pass("Clicked 'Edit' button for step name '" + generatedName + "' successfully");


            basePage.pause(5000);
            stepNamePage.clickSaveForStepName(generatedName);
            ExtentReportListener.getExtentTest().pass("Clicked 'Save' for step name '" + generatedName + "' successfully");

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
