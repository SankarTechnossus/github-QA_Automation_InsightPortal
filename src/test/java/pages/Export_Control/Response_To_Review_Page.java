package pages.Export_Control;

import base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Response_To_Review_Page extends BasePage {

    private WebDriverWait wait;

    public Response_To_Review_Page(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    //Locator
    By chiefApproverNode = By.xpath("//div[contains(@class,'_nodeHeader')]/p[normalize-space()='Chief Approver (chiefapprover1)']");
    By activitiesTab = By.xpath("//button[normalize-space()='Activities']");
    By responseToReviewLink = By.xpath("//div[@id='left-sidebar']//a[contains(@class,'label')][span[normalize-space()='Response To Review']]");

    //Method
    public void clickResponseToReview() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement link = wait.until(
                ExpectedConditions.elementToBeClickable(responseToReviewLink));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", link);

        link.click();

        pause(1000);
    }

    public void clickActivitiesTab() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement tab = wait.until(
                ExpectedConditions.elementToBeClickable(activitiesTab));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", tab);

        tab.click();

        pause(1000);
    }

    public void clickChiefApprover() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement node = wait.until(
                ExpectedConditions.elementToBeClickable(chiefApproverNode));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", node);

        node.click();

        pause(1000);
    }
}