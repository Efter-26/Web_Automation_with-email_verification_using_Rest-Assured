package testrunner;

import com.github.javafaker.Faker;
import config.Setup;
import config.UserModel;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.RegistrationPage;
import utils.Utils;

import javax.naming.ConfigurationException;
import java.io.IOException;
import java.time.Duration;

public class RegistrationTestRunner extends Setup {

    @Test
    public void userRegByAllFields() throws InterruptedException, IOException, ParseException, ConfigurationException, org.apache.commons.configuration.ConfigurationException {
        RegistrationPage userReg=new RegistrationPage(driver);
        Faker faker=new Faker();
        userReg.btnRegister.click();
        String firstname=faker.name().firstName();
        String lastname=faker.name().lastName();
        String email="efterjahanema@gmail.com";
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