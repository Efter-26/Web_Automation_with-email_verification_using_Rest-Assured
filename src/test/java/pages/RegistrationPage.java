
package pages;

import config.UserModel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class RegistrationPage {
    @FindBy(xpath = "(//a)[2]")
    public WebElement btnRegister;
    @FindBy(id = "firstName")
    public WebElement txtFirstname;
    @FindBy(id = "lastName")
    WebElement txtLastname;
    @FindBy(id="email")
    WebElement txtEmail;
    @FindBy(id="password")
    WebElement txtPassword;
    @FindBy(id="phoneNumber")
    WebElement txtPhoneNumber;
    @FindBy(id="address")
    WebElement txtAddress;
    @FindBy(css = "[type=radio]")
    List<WebElement> rbGender;
    @FindBy(css = "[type=checkbox]")
    WebElement chkAcceptTerms;
    @FindBy(id="register")
    public WebElement btnSubmitReg;

    public RegistrationPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }
    public void doRegistration(UserModel userModel){
        txtFirstname.sendKeys(userModel.getFirstname());
        txtLastname.sendKeys(userModel.getLastname()!=null?userModel.getLastname():"");
        txtEmail.sendKeys(userModel.getEmail());
        txtPassword.sendKeys(userModel.getPassword());
        txtPhoneNumber.sendKeys(userModel.getPhonenumber());
        txtAddress.sendKeys(userModel.getAddress()!=null?userModel.getAddress():"");
        rbGender.get(0).click();
        chkAcceptTerms.click();
        btnSubmitReg.click();

    }
}
