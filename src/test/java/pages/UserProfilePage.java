package pages;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.Utils;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class UserProfilePage {

    WebDriver driver;

    @FindBy(tagName = "button")
    public List<WebElement> buttons;

    @FindBy(css = "input[name='email']")
    public WebElement txtEmail;

    public UserProfilePage(WebDriver driver){
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }

    public void updateEmail() throws InterruptedException, IOException, ParseException {
        buttons.get(1).click(); // Edit Button

        //txtEmail.clear();
        txtEmail.sendKeys(Keys.CONTROL,"a");
        txtEmail.sendKeys(Keys.BACK_SPACE);

        String newEmail = "abcd3855@gmail.com";//efterjahan894@gmail.com

        txtEmail.sendKeys(newEmail);

        buttons.get(2).click();

        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.alertIsPresent());

        Alert  alert = driver.switchTo().alert();
        String userUpdateMsgExpected = "User updated successfully!";
        String userUpdateMsgActual = alert.getText();
        Assert.assertTrue(userUpdateMsgActual.contains(userUpdateMsgExpected));
        alert.accept();

    }

}
