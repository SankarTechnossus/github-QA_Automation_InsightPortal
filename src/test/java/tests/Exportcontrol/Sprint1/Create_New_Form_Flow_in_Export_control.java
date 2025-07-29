package tests.Exportcontrol.Sprint1;
import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import listeners.ExtentReportListener;
import org.testng.annotations.Listeners;
import pages.LoginPage;
import pages.AgreementPage;
import pages.formBuilderPage;
import utils.DriverManager;
import java.time.Duration;

@Listeners(listeners.ExtentReportListener.class)
public class Create_New_Form_Flow_in_Export_control {


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
    public void createFormFlowExportcontrol() {
        ExtentReportListener.getExtentTest().info("your log message");
        try {
            // User will open the login page of the Insight Portal application
            driver.get("https://sacramento-insight4.partners.org/");
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


            basePage.pause(10000);
            agreementPage.clickFormsManagementLink();
            ExtentReportListener.getExtentTest().pass("Clicked Forms Management link");

            basePage.pause(3000);  // Optional wait
            agreementPage.scrollSidebarToExportControlAndClick();
            ExtentReportListener.getExtentTest().pass("Scrolled and clicked on 'Export Control' from left navigation.");


            basePage.pause(3000);
            agreementPage.scrollSidebarToExportControlOnly();
            ExtentReportListener.getExtentTest().pass("Scrolled to 'Export Control' in the left navigation without clicking.");

            //********commented for testing **********

//            // Now use the new input field
//            agreementPage.enterSearchText("Test");
//            ExtentReportListener.getExtentTest().pass("Entered 'Test' in Search by Name input");
//
//
//            basePage.pause(10000);
//            agreementPage.clickSearchButton();
//            ExtentReportListener.getExtentTest().pass("Clicked Search button");
//
//
//            basePage.pause(10000);
//            agreementPage.clickClearSelectionsButton();
//            ExtentReportListener.getExtentTest().pass("Clicked Clear Selections button");
//
//            //**** Negative case ***
//
//            // Now use the new input field
//            agreementPage.enterSearchText("@@@@@");
//            ExtentReportListener.getExtentTest().pass("Entered '@@@@' in Search by Name input passed a special character");
//
//
//            basePage.pause(5000);
//            agreementPage.clickSearchButton();
//            ExtentReportListener.getExtentTest().pass("Clicked Search button");
//
//
//            basePage.pause(5000);
//            agreementPage.clickClearSelectionsButton();
//            ExtentReportListener.getExtentTest().pass("Clicked Clear Selections button");

            basePage.pause(5000);  // Optional wait if needed
            agreementPage.clickAddNewLink();
            ExtentReportListener.getExtentTest().pass("Clicked 'Add new' link on Export Control page");


            basePage.pause(5000);
            agreementPage.enterName("TestSan0008");
            ExtentReportListener.getExtentTest().pass("Entered 'TestSan0001' into Name input field");


            basePage.pause(5000);  // Optional initial wait
            agreementPage.enterDescription("santest04");
            ExtentReportListener.getExtentTest().pass("Entered 'santest01' into Description text area");


            basePage.pause(5000);  // Optional
            agreementPage.selectTypeAsChecklist();
            ExtentReportListener.getExtentTest().pass("Selected 'Checklist' from Type dropdown");


            basePage.pause(5000);
            agreementPage.selectCategoryAsGeneral();
            ExtentReportListener.getExtentTest().pass("Selected 'General' from Category dropdown");



            basePage.pause(5000);
            agreementPage.enterCategorySequenceNo("1");
            ExtentReportListener.getExtentTest().pass("Entered '1' into Category Sequence No field");


            basePage.pause(5000);
            agreementPage.clickCreateButton();
            ExtentReportListener.getExtentTest().pass("Clicked the 'Create' button");


            basePage.pause(5000);
            agreementPage.clickVersion1Link();
            ExtentReportListener.getExtentTest().pass("Clicked on 'Version 1' link");


            // Agreement Page Actions
            formBuilderPage formBuilderPage01 = new formBuilderPage(driver);


            basePage.pause(5000);
            formBuilderPage01.clickAddRootLevelQuestionButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Add root level question' button");


            basePage.pause(5000);
            formBuilderPage01.clickRadioButtonGroupOption();
            ExtentReportListener.getExtentTest().pass("Clicked 'Radio button group' in Add Question Modal");


            basePage.pause(5000);
            formBuilderPage01.enterRadioOption1Text("Yes");
            ExtentReportListener.getExtentTest().pass("Entered text 'Yes' into the first radio option input");


            basePage.pause(5000);
            formBuilderPage01.clickAddOptionButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Add option' button to add new radio choice");


            basePage.pause(5000);
            formBuilderPage01.enterRadioOption2Text("No");
            ExtentReportListener.getExtentTest().pass("Entered text 'No' into the second radio option input");


            basePage.pause(5000);
            formBuilderPage01.checkReadOnly();
            ExtentReportListener.getExtentTest().pass("Checked the 'Read only' checkbox");


            basePage.pause(5000);
            formBuilderPage01.enterHelpText("test01");
            ExtentReportListener.getExtentTest().pass("Entered help text as 'test01'");


            basePage.pause(5000);
            formBuilderPage01.clickApplyButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Apply' button");


            basePage.pause(5000);
            formBuilderPage01.clickPreviewLink();
            ExtentReportListener.getExtentTest().pass("Clicked 'Preview' link");

            basePage.pause(5000);
            formBuilderPage01.clickClosePreviewLink();
            ExtentReportListener.getExtentTest().pass("Clicked 'Close preview' link");

            basePage.pause(5000);
            formBuilderPage01.clickSaveButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Save' button");

            //********** Negative_case ************

            basePage.pause(5000);
            formBuilderPage01.clickAddChildQuestion();
            ExtentReportListener.getExtentTest().pass("Clicked 'Add child question' button successfully");

//
//            basePage.pause(3000);
//            formBuilderPage01.clickOutsidePopup();
//            ExtentReportListener.getExtentTest().pass("Clicked 'outside pop up' successfully");

            basePage.pause(3000);
            formBuilderPage01.clickOutsidePopupByOffset();
            ExtentReportListener.getExtentTest().pass("Clicked outside popup using offset successfully");


            basePage.pause(3000);
            formBuilderPage01.clickCancelAddingButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Cancel adding' button successfully");


            basePage.pause(3000);
            formBuilderPage01.clickMoveButton();
            ExtentReportListener.getExtentTest().pass(" Clicked 'Move' button successfully");


            basePage.pause(3000);
            formBuilderPage01.clickCancelMovingButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Cancel moving' button successfully");

            basePage.pause(3000);
            formBuilderPage01.clickEditButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Edit' button successfully");

            basePage.pause(3000);
            formBuilderPage01.clickCancelButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Cancel' button successfully");

            basePage.pause(5000);
            formBuilderPage01.clickRemoveButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Remove' button successfully");


            basePage.pause(5000);
            formBuilderPage01.clickUndoButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Undo' button successfully");











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
