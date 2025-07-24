package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



import java.time.Duration;

public class formBuilderPage extends BasePage {

    public formBuilderPage(WebDriver driver) {super(driver);}

    private By addRootLevelQuestionButton = By.xpath("//button[@type='button' and contains(@class, 'button') and contains(., 'Add root level question')]");
    private By radioButtonGroupCard = By.xpath("//div[contains(@class,'possible-question') and .//text()[contains(., 'Radio button group')]]");
    private By radioOption1Input = By.xpath("//input[@class='value-input text-input default-input' and @value='Option1']");
    private By addOptionButton = By.xpath("//button[@type='button' and div/i[contains(@class, 'fi-add')] and contains(., 'Add option')]");
    private By radioOption2Input = By.xpath("//input[@class='value-input text-input default-input' and @value='Option2']");
    private By readOnlyCheckbox = By.xpath("//label[contains(@class, 'checkbox-editor-label')]/input[@type='checkbox' and contains(@class, 'checkbox')]");
    private By helpTextArea = By.xpath("//span[text()='Help text:']/following-sibling::textarea");
    private By applyButton = By.xpath("//button[contains(@class,'-primary') and contains(@class,'-submission') and contains(@class,'-small') and text()='Apply']");
    private By previewLink = By.xpath("//a[contains(@href, '/preview') and text()='Preview']");
    private By closePreviewLink = By.xpath("//a[contains(@href, '/edit') and text()='Close preview']");
    private By saveButton = By.xpath("//button[@type='button' and contains(@class, '-submission') and text()='Save']");

    // ***********__________Actions__________*************




    public void clickSaveButton() {
        WebElement button = driver.findElement(saveButton);
        button.click();
        pause(3000); // Optional: wait for the form save to complete
    }


    public void clickClosePreviewLink() {
        WebElement link = driver.findElement(closePreviewLink);
        link.click();
        pause(3000); // Optional wait for the editor to reload
    }



    public void clickPreviewLink() {
        WebElement link = driver.findElement(previewLink);
        link.click();
        pause(3000); // Optional wait for preview page to load
    }




    public void clickApplyButton() {
        WebElement button = driver.findElement(applyButton);
        button.click();
        pause(2000); // Optional wait for changes to take effect
    }



    public void enterHelpText(String text) {
        WebElement helpTextField = driver.findElement(helpTextArea);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", helpTextField);
        helpTextField.clear(); // Optional: clear existing text
        helpTextField.sendKeys(text);
        pause(500); // Optional: slight wait after entering text
    }



    public void checkReadOnly() {
        WebElement checkbox = driver.findElement(readOnlyCheckbox);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", checkbox);
        if (!checkbox.isSelected()) {
            checkbox.click();
            pause(500); // Optional delay after click
        }
    }


    public void enterRadioOption2Text(String text) {
        WebElement input = driver.findElement(radioOption2Input);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", input);
        input.clear();
        input.sendKeys(text);
        pause(1000);  // Optional delay
    }



    public void clickAddOptionButton() {
        WebElement addButton = driver.findElement(addOptionButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", addButton);
        pause(1000);  // Optional scroll wait
        addButton.click();
        pause(2000);  // Allow new input field to load
    }



    public void enterRadioOption1Text(String text) {
        WebElement input = driver.findElement(radioOption1Input);
        input.clear();
        input.sendKeys(text);
        pause(1000);  // Let input settle
    }


    public void clickRadioButtonGroupOption() {
        WebElement radioOption = driver.findElement(radioButtonGroupCard);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", radioOption);
        pause(1000);  // Let the scroll complete
        radioOption.click();
        pause(3000);  // Let the new field render
    }



    public void clickAddRootLevelQuestionButton() {
        WebElement button = driver.findElement(addRootLevelQuestionButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);  // Optional: make visible
        pause(1000);  // Wait after scroll
        button.click();
        pause(3000);  // Wait for action completion
    }





}
