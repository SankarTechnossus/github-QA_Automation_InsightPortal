package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Export_control_My_actions_page extends BasePage {

    public Export_control_My_actions_page(WebDriver driver) {
        super(driver);
    }



    //Locators

    private By exportControlLink = By.xpath("//a[@href='/export-control' and contains(@class,'module-link')]");





    //Actions

    public void clickExportControlLink() {
        WebElement link = driver.findElement(exportControlLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", link);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(link));
        link.click();

        pause(1000);
    }






}
