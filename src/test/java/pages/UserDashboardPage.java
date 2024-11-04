package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class UserDashboardPage {

    WebDriver driver;


    @FindBy( xpath = "//div[@class='summary']/span")
    public List<WebElement> rowCount;

    @FindBy(tagName = "tbody")
    public WebElement table;

    @FindBy(className = "add-cost-button")
    public WebElement btnAddCost;

    @FindBy( xpath = "//div[@class='summary']/span")
    public List<WebElement> txtCost;



    public UserDashboardPage(WebDriver driver){
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }

    public void itemAssertion() throws InterruptedException {

        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.visibilityOf(table));


        String str = rowCount.get(0).getAttribute("innerText");

        String actualTotalRow = str.replaceAll("[^\\d]", "");


        String expectedTotalRow="2";
        Assert.assertEquals(actualTotalRow,expectedTotalRow);


    }

}
