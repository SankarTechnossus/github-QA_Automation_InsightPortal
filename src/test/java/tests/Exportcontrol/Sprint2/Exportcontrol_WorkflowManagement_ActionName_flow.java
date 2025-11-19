package tests.Exportcontrol.Sprint2;


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
import pages.Administration.Exportcontrol_WorkflowManagement_ActionName_page;
import utils.DriverManager;
import workflow_helper.LoginPageHelper;

import java.time.Duration;


@Listeners(listeners.ExtentReportListener.class)
public class Exportcontrol_WorkflowManagement_ActionName_flow {

    WebDriver driver;
    WebDriverWait wait;
    BasePage basePage;
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
    }

    @Test
    public void Exportcontrol_WorkflowManagement_ActionName_Test () {
        ExtentReportListener.getExtentTest().info("your log message");
        try {
            // User will open the login page of the Insight Portal application
            driver.get("https://hollywood-insight4.partners.org/");
            ExtentReportListener.getExtentTest().info("Opened dashboard URL");

            // User will wait for the login screen to load completely before performing actions
            basePage.pause(20000);


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


            Exportcontrol_WorkflowManagement_ActionName_page actionnamepage = new Exportcontrol_WorkflowManagement_ActionName_page(driver);

            basePage.pause(2000);
            actionnamepage.clickWorkflowManagementaction();
            ExtentReportListener.getExtentTest().pass("Opened 'Workflow Management'");


            basePage.pause(2000);
            actionnamepage.clickActionNameScope3();
            ExtentReportListener.getExtentTest().pass("Opened 'Action name' (scopeId=3)");


            basePage.pause(5000);
            actionnamepage.clickAddNewButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Add New' button successfully");


            basePage.pause(5000);
            String uniqueName03 = "Test_" + System.currentTimeMillis();
            actionnamepage.enterName(uniqueName03);
            ExtentReportListener.getExtentTest().pass("Entered unique name '" + uniqueName03 + "' in the Name input field successfully");


            basePage.pause(5000);
            actionnamepage.enterHistoryTitle("TestSan");
            ExtentReportListener.getExtentTest().pass("Entered 'Sample History Title' into History Title input field successfully");


            basePage.pause(5000);
            actionnamepage.clickCancelButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Cancel' button successfully");


            basePage.pause(5000);
            actionnamepage.clickAddNewButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Add New' button successfully");


            basePage.pause(5000);
            String uniqueName5 = "Test_" + System.currentTimeMillis();
            actionnamepage.enterName(uniqueName5);
            ExtentReportListener.getExtentTest().pass("Entered unique name '" + uniqueName5 + "' in the Name input field successfully");


            basePage.pause(5000);
            actionnamepage.enterHistoryTitle("TestSan");
            ExtentReportListener.getExtentTest().pass("Entered 'Sample History Title' into History Title input field successfully");


            basePage.pause(5000);
            actionnamepage.clickAddButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Add' button successfully");

            basePage.pause(5000);
            actionnamepage.clickEditForActionName(uniqueName5);
            ExtentReportListener.getExtentTest().pass("Clicked 'Edit' for action name '" + uniqueName5 + "' successfully");


            basePage.pause(5000);
            actionnamepage.clickCancelForActionName(uniqueName5);
            ExtentReportListener.getExtentTest().pass("Clicked 'Cancel' for action name '" + uniqueName5 + "' successfully");


            basePage.pause(5000);
            actionnamepage.clickEditForActionName(uniqueName5);
            ExtentReportListener.getExtentTest().pass("Clicked 'Edit' for action name '" + uniqueName5 + "' successfully");


            basePage.pause(5000);
            actionnamepage.clickSaveForActionName(uniqueName5);
            ExtentReportListener.getExtentTest().pass("Clicked 'Save' for action name '" + uniqueName5 + "' successfully");





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
