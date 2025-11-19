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
import pages.Administration.Exportcontrol_WorkflowManagement_Rules_Page;
import pages.LoginPage;
import utils.DriverManager;

import java.time.Duration;


@Listeners(listeners.ExtentReportListener.class)
public class Exportcontrol_WorkflowManagement_Rules_flow {

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
    public void Exportcontrol_WorkflowManagement_Rules_Test () {
        ExtentReportListener.getExtentTest().info("your log message");
        try {
            // User will open the login page of the Insight Portal application
            driver.get("https://hollywood-insight4.partners.org/");
            ExtentReportListener.getExtentTest().info("Opened dashboard URL");

            // User will wait for the login screen to load completely before performing actions
            basePage.pause(20000);

            // Create an instance of LoginPage
            LoginPage loginPage = new LoginPage(driver);

            // User will enter the username into the username input field
            loginPage.enterUsername("SV1179");
            ExtentReportListener.getExtentTest().pass("Entered username");

            // User will click the 'Next' button to proceed to the password entry screen
            loginPage.clickNext();
            ExtentReportListener.getExtentTest().pass("Clicked Next");

            // User will input the user's password into the password field
            loginPage.enterPassword("Devinivetha@1930");
            ExtentReportListener.getExtentTest().pass("Entered password");

            // User will click the 'Verify' button to authenticate the user
            loginPage.clickVerify();
            ExtentReportListener.getExtentTest().pass("Clicked Verify");

            // Optional: pause if any post-login actions needed
            basePage.pause(20000);

            // Agreement Page Actions
            AgreementPage agreementPage = new AgreementPage(driver);

            basePage.pause(10000);
            agreementPage.clickAdministrationLink();
            ExtentReportListener.getExtentTest().pass("Clicked Administration link");

            Exportcontrol_WorkflowManagement_Rules_Page exportcontrolrulespage = new Exportcontrol_WorkflowManagement_Rules_Page(driver);

            basePage.pause(5000);
            exportcontrolrulespage.clickWorkflowManagementLinkrules();
            ExtentReportListener.getExtentTest().pass("Clicked 'Workflow Management' menu link successfully");

            basePage.pause(5000);
            exportcontrolrulespage.clickRulesLink();
            ExtentReportListener.getExtentTest().pass("Clicked 'Rules' link successfully");


            basePage.pause(5000);
            exportcontrolrulespage.clickAddRuleButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Add rule' button successfully");


            basePage.pause(5000);
            String generatedName = exportcontrolrulespage.enterUniqueNameWithTestPrefix();
            ExtentReportListener.getExtentTest().pass("Entered unique name: '" + generatedName + "' into Name input field successfully");


            basePage.pause(5000);
            exportcontrolrulespage.selectQueryBuilderOperator("Query Builder","AND");
            ExtentReportListener.getExtentTest().pass("Selected 'AND' from Record Type dropdown successfully");


            basePage.pause(5000);
            exportcontrolrulespage.clickAddRuleButtonrule();
            ExtentReportListener.getExtentTest().pass("Clicked 'Add Rule' button successfully");


            basePage.pause(5000);
            exportcontrolrulespage.clickRemoveRuleButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Remove rule' button successfully");


            basePage.pause(5000);
            exportcontrolrulespage.clickAddGroupButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Add Group' button successfully");


            basePage.pause(5000);
            exportcontrolrulespage.clickRemoveGroupButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Remove group' button successfully");


            basePage.pause(5000);
            exportcontrolrulespage.clickMigrationButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Migration' button successfully");


            basePage.pause(5000);
            exportcontrolrulespage.clickMigrationButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Migration' button successfully");


            basePage.pause(5000);
            exportcontrolrulespage.clickCancelButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Cancel' button successfully");


            basePage.pause(5000);
            exportcontrolrulespage.clickAddRuleButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Add rule' button successfully");


            basePage.pause(5000);
            String generatedNamelast = exportcontrolrulespage.enterUniqueNameWithTestPrefix();
            ExtentReportListener.getExtentTest().pass("Entered unique name: '" + generatedNamelast + "' into Name input field successfully");


            basePage.pause(5000);
            exportcontrolrulespage.selectQueryBuilderOperator("Query Builder","AND");
            ExtentReportListener.getExtentTest().pass("Selected 'AND' from Record Type dropdown successfully");


            basePage.pause(5000);
            exportcontrolrulespage.clickSaveButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Save' button successfully");


            basePage.pause(5000);
            exportcontrolrulespage.clickEditButtonForRule(generatedNamelast);
            ExtentReportListener.getExtentTest().pass("Clicked 'Edit' button for rule: '" + generatedNamelast + "' successfully");


            basePage.pause(5000);
            exportcontrolrulespage.appendToNameField("san");
            ExtentReportListener.getExtentTest().pass("Appended 'san' to Name input field successfully");

            basePage.pause(5000);
            exportcontrolrulespage.clickSaveButton();
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
