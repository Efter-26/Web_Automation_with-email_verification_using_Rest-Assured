package testrunner;

import config.Setup;
import net.bytebuddy.build.Plugin;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
//import pages.LoginPage;
import pages.ResetPasswordPage;


import javax.naming.ConfigurationException;
import java.io.FileReader;
import java.io.IOException;

public class ResetPasswordTestRunner extends Setup {

    public static String newPass;
    public static String userEmail;

    ResetPasswordPage resetPass;

    @Test(priority = 1 , description = "Reset New Password")
    public void resetPassword() throws IOException, ParseException, ConfigurationException, org.apache.commons.configuration.ConfigurationException, InterruptedException {
        resetPass = new ResetPasswordPage(driver);
        resetPass.linkResetPassword.click();

        userEmail = "efterjahanema@gmail.com";
        System.out.println(userEmail);
        newPass = resetPass.getResetPass(userEmail);

    }

    @Test(priority = 2 , description = "Reset New Password With Unregistered Email")
    public void resetPassUnregisteredEmail() throws IOException, ParseException, ConfigurationException, org.apache.commons.configuration.ConfigurationException, InterruptedException {
        driver.get("https://dailyfinance.roadtocareer.net/login");
        resetPass.linkResetPassword.click();
        resetPass.sendUnregisteredEmail("efterjahan894@gmail.com");

    }



}
