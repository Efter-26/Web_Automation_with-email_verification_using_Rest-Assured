package testrunner;

import config.Setup;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AdminDashboardPage;
import pages.LoginPage;
import utils.Utils;

import java.io.IOException;


public class AdminLoginTestRunner extends Setup {
    LoginPage loginPage;

    @Test(priority = 1, description = "login with wrong Password")
    public void adminLoginWithWrongPass() throws InterruptedException {
        loginPage=new LoginPage(driver);
        loginPage.doLogin("admin@test.com","453pass");
        String errorMessageActual= driver.findElement(By.tagName("p")).getText();
        String errorMessageExpected="Invalid";
        Assert.assertTrue(errorMessageActual.contains(errorMessageExpected));
        Utils.clearLoginCreds(driver);
    }

    @Test(priority = 2, description = "login with wrong Email")
    public void adminLoginWithWrongEmail() throws InterruptedException {

        loginPage.doLogin("1234admin@test.com","admin123");
        String errorMessageActual= driver.findElement(By.tagName("p")).getText();
        String errorMessageExpected="Invalid";
        Assert.assertTrue(errorMessageActual.contains(errorMessageExpected));
        Utils.clearLoginCreds(driver);
    }


    @Test(priority = 3, description = "login with right email and password")
    public void adminLogin() throws IOException {


        if(System.getProperty("username")!=null && System.getProperty("password")!=null){
            loginPage.doLogin(System.getProperty("username"),System.getProperty("password"));
        }
        else{
            loginPage.doLogin("admin@test.com","admin123");
        }

        String headerActual= loginPage.dashboardMsg.getText();
        String headerExpected="Admin Dashboard";
        Assert.assertTrue(headerActual.contains(headerExpected));
        //Utils.getToken(driver);
    }
    @Test(priority = 3, description = "Search by The newly updated email on the DashBoard" )
    public void userValueMatching() throws IOException, ParseException {

        String email= "abcd3855@gmail.com"; //update email
        AdminDashboardPage admin = new AdminDashboardPage(driver);
        admin.tableDataSearching(email);
    }

}
