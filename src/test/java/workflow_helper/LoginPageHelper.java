package workflow_helper;

import listeners.ExtentReportListener;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;

public class LoginPageHelper {

    private WebDriver driver;
    private LoginPage loginPage;

    public LoginPageHelper(WebDriver driver){
        this.driver = driver;
        this.loginPage = new LoginPage(driver);
    }

    public void enterUserName(String userName){
        // User will enter the username into the username input field
        loginPage.enterUsername(userName);
        ExtentReportListener.getExtentTest().pass("Entered username");
    }

    public void clickNext(){
        // User will click the 'Next' button to proceed to the password entry screen
        loginPage.clickNext();
        ExtentReportListener.getExtentTest().pass("Clicked Next");
    }

    public void enterPassword(){
        // User will input the user's password into the password field
        loginPage.enterPassword("Devinivetha@1930");
//        loginPage.enterPassword("Nexon@1996");
        ExtentReportListener.getExtentTest().pass("Entered password");
    }

    public void clickVerify(){
        // User will click the 'Verify' button to authenticate the user
        loginPage.clickVerify();
        ExtentReportListener.getExtentTest().pass("Clicked Verify");
    }


}
