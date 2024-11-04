package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class AdminDashboardPage {

    @FindBy(tagName="tbody")
    WebElement table;
    @FindBy(className = "search-box")
    WebElement searchBox;

    public AdminDashboardPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public void tableDataSearching( String email ){

        searchBox.sendKeys(email);

        WebElement firstDataRow = table.findElements(By.tagName("tr")).get(0);

        String Email ;

        List<WebElement> cells = firstDataRow.findElements(By.tagName("td"));

        Email = cells.get(2).getText();
        Assert.assertEquals(Email,email);



    }
}


