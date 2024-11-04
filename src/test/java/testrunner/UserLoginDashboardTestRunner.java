package testrunner;

import config.Setup;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.AddCostPage;
import pages.UserDashboardPage;
import utils.AddCostData;

import java.io.IOException;

public class UserLoginDashboardTestRunner extends Setup {

    LoginPage loginPage;
    @Test(priority = 1,description = "User Logged in after reset password")
    public void UserLogin() throws ParseException, IOException, InterruptedException {
        loginPage = new LoginPage(driver);

        String email= "efterjahanema@gmail.com"; //efterjahanema@gmail.com
        String password= "54321";//54321

        loginPage.doLogin(email,password);
        String expectedMsg = "User Daily Costs";
        String actualMsg = loginPage.dashboardMsg.getText();
        Assert.assertTrue(actualMsg.contains(expectedMsg));
    }

    @Test( priority = 2, dataProvider = "AddCostData", dataProviderClass = AddCostData.class, description = "Add the item form CSV" )
    public void addCost(String item,int quantity,String amount,String date,String month,String remark) throws InterruptedException {

        AddCostPage addCostPage=new AddCostPage(driver);
        addCostPage.btnAddCost.click();

        addCostPage.addCost(item,quantity,amount,date, month ,remark);

    }

    @Test(priority = 3, description = "Assert The newly Added Products")
    public  void productAssertion( ) throws InterruptedException {

        UserDashboardPage dashboardPage = new UserDashboardPage(driver);
        dashboardPage.itemAssertion();


    }

}
