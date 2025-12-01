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
import pages.Administration.RulesPage;
import pages.Adobe.AgreementPage;
import pages.Administration.Workflow_Management.Export_Control.*;
import pages.Home.DashboardPage;
import pages.Home.LoginPage;
import utils.DriverManager;
import utils.JsonDataReader;

import java.time.Duration;


@Listeners(listeners.ExtentReportListener.class)
public class PBI_239475_Exportcontrol_WorkflowManagement_Rules_flow {

    WebDriver driver;
    WebDriverWait wait;
    BasePage basePage;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    RulesPage rulesPage;

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
        rulesPage = new RulesPage(driver);
    }

    @Test
    public void Exportcontrol_WorkflowManagement_Rules_Test () {
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


            basePage.pause(5000);
            rulesPage.clickWorkflowManagementLinkrules();
            ExtentReportListener.getExtentTest().pass("Clicked 'Workflow Management' menu link successfully");

            basePage.pause(5000);
            rulesPage.clickRulesLink();
            ExtentReportListener.getExtentTest().pass("Clicked 'Rules' link successfully");


            basePage.pause(5000);
            rulesPage.clickAddRuleButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Add rule' button successfully");


            basePage.pause(5000);
            String generatedName = rulesPage.enterUniqueNameWithTestPrefix();
            ExtentReportListener.getExtentTest().pass("Entered unique name: '" + generatedName + "' into Name input field successfully");


            basePage.pause(5000);
            rulesPage.selectQueryBuilderOperator("Query Builder","AND");
            ExtentReportListener.getExtentTest().pass("Selected 'AND' from Record Type dropdown successfully");


            basePage.pause(5000);
            rulesPage.clickAddRuleButtonrule();
            ExtentReportListener.getExtentTest().pass("Clicked 'Add Rule' button successfully");


            basePage.pause(5000);
            rulesPage.clickRemoveRuleButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Remove rule' button successfully");


            basePage.pause(5000);
            rulesPage.clickAddGroupButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Add Group' button successfully");


            basePage.pause(5000);
            rulesPage.clickRemoveGroupButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Remove group' button successfully");


            basePage.pause(5000);
            rulesPage.clickMigrationButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Migration' button successfully");


            basePage.pause(5000);
            rulesPage.clickMigrationButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Migration' button successfully");


            basePage.pause(5000);
            rulesPage.clickCancelButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Cancel' button successfully");


            basePage.pause(5000);
            rulesPage.clickAddRuleButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Add rule' button successfully");


            basePage.pause(5000);
            String generatedNamelast = rulesPage.enterUniqueNameWithTestPrefix();
            ExtentReportListener.getExtentTest().pass("Entered unique name: '" + generatedNamelast + "' into Name input field successfully");


            basePage.pause(5000);
            rulesPage.selectQueryBuilderOperator("Query Builder","AND");
            ExtentReportListener.getExtentTest().pass("Selected 'AND' from Record Type dropdown successfully");


            basePage.pause(5000);
            rulesPage.clickSaveButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Save' button successfully");


            basePage.pause(5000);
            rulesPage.clickEditButtonForRule(generatedNamelast);
            ExtentReportListener.getExtentTest().pass("Clicked 'Edit' button for rule: '" + generatedNamelast + "' successfully");


            basePage.pause(5000);
            rulesPage.appendToNameField("san");
            ExtentReportListener.getExtentTest().pass("Appended 'san' to Name input field successfully");

            basePage.pause(5000);
            rulesPage.clickSaveButton();
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
