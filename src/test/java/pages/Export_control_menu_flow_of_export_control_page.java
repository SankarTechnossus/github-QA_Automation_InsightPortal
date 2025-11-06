package pages;

import base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Export_control_menu_flow_of_export_control_page extends BasePage {

    public Export_control_menu_flow_of_export_control_page(WebDriver driver) {
        super(driver);
    }



    //Locators

    private By exportControlLink = By.xpath("//a[@href='/export-control' and contains(@class,'module-link')]");




    //Actions

    public void dismissAnyOpenReactSelectMenus() {
        List<WebElement> menus = driver.findElements(
                By.cssSelector("ul[id^='react-select-'][id$='-listbox'], div[id^='react-select-'][id$='-listbox']")
        );
        if (!menus.isEmpty()) {
            try { driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE); } catch (Exception ignored) {}
            try { driver.findElement(By.tagName("body")).click(); } catch (Exception ignored) {}
            new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(d -> d.findElements(By.cssSelector("ul[id^='react-select-'][id$='-listbox'], div[id^='react-select-'][id$='-listbox']")).isEmpty());
        }
    }


    public void clickExportControlLink() {
        dismissAnyOpenReactSelectMenus(); // <— add this
        WebElement link = driver.findElement(exportControlLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", link);
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(link));
        link.click();
        pause(1000);
    }




}
