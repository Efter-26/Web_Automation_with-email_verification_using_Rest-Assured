package pages;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.Utils;

import javax.naming.ConfigurationException;
import java.io.IOException;
import java.util.List;

public class ResetPasswordPage {

    public WebDriver driver;

    @FindBy(css = "a[href='/forgot-password']")
    public WebElement linkResetPassword;
    @FindBy(id=":r0:")
    public WebElement txtEmail;
    @FindBy(css = "button[type='submit']")
    public WebElement btnSendReset;
    @FindBy( tagName = "p")
    public WebElement txtInformation;
    @FindBy(tagName = "input")
    public List<WebElement> txtPasswordField;
    @FindBy(tagName = "button")
    public WebElement btnResetPass;

    public ResetPasswordPage(WebDriver driver){
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }

    public String getResetPass(String userEmail) throws ConfigurationException, org.apache.commons.configuration.ConfigurationException, IOException, InterruptedException, ParseException {
        txtEmail.sendKeys(userEmail);
        btnSendReset.click();
        txtInformation.isDisplayed();

        Thread.sleep(3000);

        String email = Utils.readLatestMail();
        String resetPassLink = email.split(": ")[1].trim();
        driver.get(resetPassLink);

        String newPass = "54321";

        txtPasswordField.get(0).sendKeys(newPass); // new password
        txtPasswordField.get(1).sendKeys(newPass); // confirm password
        btnResetPass.click();
        String confirmationMsgActual = txtInformation.getText();
        String confirmationMsgExpected = "Password reset successfully";
        Assert.assertTrue(confirmationMsgActual.contains(confirmationMsgExpected));

        Utils.updatePassword("password",newPass);

        return newPass;

    }

    public void sendUnregisteredEmail(String userEmail){
        txtEmail.sendKeys(userEmail);
        btnSendReset.click();
        String msgActual = txtInformation.getText();
        String msgExpected = "Your email is not registered";
        Assert.assertTrue(msgActual.contains(msgExpected));

    }


}
