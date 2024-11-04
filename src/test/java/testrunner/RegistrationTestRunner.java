package testrunner;

import com.github.javafaker.Faker;
import config.Setup;
import config.UserModel;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.RegistrationPage;
import utils.Utils;

import javax.naming.ConfigurationException;
import java.io.IOException;
import java.time.Duration;

public class RegistrationTestRunner extends Setup {

    @Test(priority = 3, description = "User can register by all valid info")
    public void userRegByAllFields() throws InterruptedException, IOException, ParseException, ConfigurationException, org.apache.commons.configuration.ConfigurationException {
        RegistrationPage userReg=new RegistrationPage(driver);
        Faker faker=new Faker();
        userReg.btnRegister.click();
        String firstname=faker.name().firstName();
        String lastname=faker.name().lastName();
        String email="abcd"+Utils.generateRandomNumber(1000,9999)+"@gmail.com";
        String password="123456";
        String phonenumber= "01505"+Utils.generateRandomNumber(100000,999999);
        String address=faker.address().fullAddress();
        UserModel userModel=new UserModel();
        userModel.setFirstname(firstname);
        userModel.setLastname(lastname);
        userModel.setEmail(email);
        userModel.setPassword(password);
        userModel.setPhonenumber(phonenumber);
        userModel.setAddress(address);
        userReg.doRegistration(userModel);

        //assertion
        //doRegAssertion();
        assertEmail();

        JSONObject userObj=new JSONObject();
        userObj.put("firstName",firstname);
        userObj.put("lastName",lastname);
        userObj.put("email",email);
        userObj.put("password",password);
        userObj.put("phoneNumber",phonenumber);
        userObj.put("address",address);
        Utils.saveUserInfo("./src/test/resources/users.json",userObj);
        Thread.sleep(5000);

    }
    @Test(priority = 1, description = "User can not register by Invalid Phone Number")
    public void userRegInvalidPhoneNumber() throws IOException, ParseException, InterruptedException {
        RegistrationPage userReg=new RegistrationPage(driver);
        Faker faker=new Faker();
        userReg.btnRegister.click();
        String firstname=faker.name().firstName();
        String lastname=faker.name().lastName();

        String email = "xyzab"+Utils.generateRandomNumber(1000,9999)+"@gmail.com";
        String password="1234";
        String phonenumber= "abcde"+Utils.generateRandomNumber(100000,999999);
        UserModel userModel=new UserModel();
        userModel.setFirstname(firstname);
        userModel.setLastname(lastname);
        userModel.setEmail(email);
        userModel.setPassword(password);
        userModel.setPhonenumber(phonenumber);
        userReg.doRegistration(userModel);


        invalidRegAssertion("phoneNumber", "Phone Number is Invalid");


        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.elementToBeClickable(userReg.btnRegister));

    }


    @Test(priority = 2, description = "User can not register by Invalid EMail")
    public void userRegWithInvalidEmail() throws IOException, ParseException, InterruptedException {
        RegistrationPage userReg=new RegistrationPage(driver);
        Faker faker=new Faker();
        userReg.btnRegister.click();
        String firstname=faker.name().firstName();
        String email = "abcd.com";
        String password="1234";
        String phonenumber= "01505"+Utils.generateRandomNumber(100000,999999);
        UserModel userModel=new UserModel();
        userModel.setFirstname(firstname);
        userModel.setEmail(email);
        userModel.setPassword(password);
        userModel.setPhonenumber(phonenumber);
        userReg.doRegistration(userModel);

        invalidRegAssertion("email","Please include an '@' in the email address");

        // Going Back To the registration Page
        driver.findElement(By.tagName("a")).click();


        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.elementToBeClickable(userReg.btnRegister));

    }


    public void invalidRegAssertion(String id, String msg) throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
        String errorMessageActual = element.getAttribute("validationMessage");
        String errorMessageExpected = msg;
        System.out.println(errorMessageActual);

        SoftAssert softAssert = new SoftAssert(); //soft assertion
        softAssert.assertTrue(errorMessageActual.contains(errorMessageExpected));
        softAssert.assertAll();
    }

    public void doRegAssertion() throws InterruptedException {
        Thread.sleep(4000);

        String successMessageActual= driver.findElement(By.className("Toastify__toast")).getText();
        String successMessageExpected="successfully";
        System.out.println(successMessageActual);
        Assert.assertTrue(successMessageActual.contains(successMessageExpected));
    }

    public void assertEmail() throws ConfigurationException, org.apache.commons.configuration.ConfigurationException, IOException {

        String confirmationEmailActual = Utils.readLatestMail();
        String confirmationEmailExpected = "Dear";
        System.out.println(confirmationEmailActual);
        Assert.assertTrue( confirmationEmailActual.contains(confirmationEmailExpected) );

    }
}