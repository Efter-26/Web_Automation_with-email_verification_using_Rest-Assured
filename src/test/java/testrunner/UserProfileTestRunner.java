package testrunner;

import config.Setup;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.UserProfilePage;
import utils.Utils;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;

public class UserProfileTestRunner extends Setup {

    LoginPage loginPage;
    String previousEmail;


    @BeforeTest
    public void UserLogin() throws ParseException, IOException, InterruptedException {
        loginPage = new LoginPage(driver);

        String email= "efterjahanema@gmail.com"; //efterjahanema@gmail.com
        String password= "54321";//54321

        previousEmail = email;

        loginPage.doLogin(email,password);
        String expectedMsg = "User Daily Costs";
        String actualMsg = loginPage.dashboardMsg.getText();
        Assert.assertTrue(actualMsg.contains(expectedMsg));
    }

    @Test( priority = 1, description = "Update User Email")
    public  void updateUserEmail() throws InterruptedException, IOException, ParseException {
        driver.get("https://dailyfinance.roadtocareer.net/user");
        loginPage = new LoginPage(driver);
        loginPage.btnProfileIcon.click();
        loginPage.btnProfileMenuItems.get(0).click();


        UserProfilePage userProfilePage = new UserProfilePage(driver);
        userProfilePage.updateEmail();

        loginPage.doLogout();

        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.elementToBeClickable(loginPage.btnLogin));

    }

    @Test(priority = 2, description = "Can not login with previous email" )
    public void userLoginFail() throws ParseException, IOException {


        loginPage.doLogin(previousEmail,"54321");


        String errorMessageActual= driver.findElement(By.tagName("p")).getText();
        String errorMessageExpected="Invalid";
        Assert.assertTrue(errorMessageActual.contains(errorMessageExpected));
        Utils.clearLoginCreds(driver);

    }

    @Test(priority = 3, description = "User can login with Updated Email" )
    public void userLoginUpdatedEmail() throws ParseException, IOException {

        String email= "abcd3855@gmail.com";//efterjahan894@gmail.com
        String password= "54321";
        driver.get("https://dailyfinance.roadtocareer.net/login");
        loginPage = new LoginPage(driver);
        loginPage.doLogin(email,password);
        String expectedMsg = "User Daily Costs";

        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.visibilityOf(loginPage.dashboardMsg));

        String actualMsg = loginPage.dashboardMsg.getText();
        Assert.assertTrue(actualMsg.contains(expectedMsg));
        loginPage.doLogout();

    }

}
