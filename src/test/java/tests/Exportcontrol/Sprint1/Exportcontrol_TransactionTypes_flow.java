package tests.Exportcontrol.Sprint1;
import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import listeners.ExtentReportListener;
import org.testng.annotations.Listeners;
import pages.ExportControl_TransactionTypes_Page;
import pages.Exportcontrol_RecordTypes_Page;
import pages.LoginPage;
import pages.AgreementPage;
import utils.DriverManager;
import java.time.Duration;


@Listeners(listeners.ExtentReportListener.class)
public class Exportcontrol_TransactionTypes_flow {

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
    public void Exportcontrol_TransactionTypes_Test () {
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

            ExportControl_TransactionTypes_Page transactionTypesPage = new ExportControl_TransactionTypes_Page(driver);

            basePage.pause(5000);
            transactionTypesPage.clickTransactionTypesLink();
            ExtentReportListener.getExtentTest().pass("Clicked on 'Transaction Types' link from left navigation");


            basePage.pause(5000);
            transactionTypesPage.clickAddNewTransactionType();
            ExtentReportListener.getExtentTest().pass("Clicked 'Add new' link on Transaction Types page");


            basePage.pause(5000);
            transactionTypesPage.enterTransactionType("Test");
            ExtentReportListener.getExtentTest().pass("Entered 'Test' into Transaction Type input field");


            Exportcontrol_RecordTypes_Page adminPageRecordtypes = new Exportcontrol_RecordTypes_Page(driver);


            basePage.pause(10000);
            transactionTypesPage.checkActiveCheckbox();
            ExtentReportListener.getExtentTest().pass("Checked 'Active' checkbox");



            basePage.pause(5000);
            adminPageRecordtypes.clickCreateButton();
            ExtentReportListener.getExtentTest().pass("'Create' button is clicked successfully");


            basePage.pause(5000);
            adminPageRecordtypes.clickCancelButton();
            ExtentReportListener.getExtentTest().pass("'Cancel' button clicked successfully");


            basePage.pause(5000);
            transactionTypesPage.clickAddNewTransactionType();
            ExtentReportListener.getExtentTest().pass("Clicked 'Add new' link on Transaction Types page");


            basePage.pause(10000);
            String transactionTypeName = transactionTypesPage.generateUniqueTransactionTypeName();
            transactionTypesPage.enterTransactionType(transactionTypeName);
            ExtentReportListener.getExtentTest().pass("Entered '" + transactionTypeName + "' into Transaction Type input field");



            basePage.pause(10000);
            transactionTypesPage.checkActiveCheckbox();
            ExtentReportListener.getExtentTest().pass("Checked 'Active' checkbox");


            basePage.pause(5000);
            adminPageRecordtypes.clickCreateButton();
            ExtentReportListener.getExtentTest().pass("'Create' button is clicked successfully");


            basePage.pause(10000);
            transactionTypesPage.enterSearchByName("Test");
            ExtentReportListener.getExtentTest().pass("Entered 'Test001' into Search by Name input field");


            basePage.pause(5000);
            adminPageRecordtypes.clickSearchButton();
            ExtentReportListener.getExtentTest().pass("'Search' button clicked successfully");


            basePage.pause(5000);
            adminPageRecordtypes.clickClearSelectionsButton();
            ExtentReportListener.getExtentTest().pass("'Clear Selections' button clicked successfully");



            basePage.pause(10000);
            transactionTypesPage.enterSearchByName("@@@@@@@@@@");
            ExtentReportListener.getExtentTest().pass("Entered 'Test001' into Search by Name input field");



            basePage.pause(5000);
            adminPageRecordtypes.clickSearchButton();
            ExtentReportListener.getExtentTest().pass("'Search' button clicked successfully");



            basePage.pause(5000);
            adminPageRecordtypes.clickClearSelectionsButton();
            ExtentReportListener.getExtentTest().pass("'Clear Selections' button clicked successfully");



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
