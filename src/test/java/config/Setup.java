package config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.time.Duration;

public class Setup {
    public WebDriver driver;
    @BeforeTest
    public void setup(){
        ChromeOptions options=new ChromeOptions();
        options.addArguments("--headed");
        driver=new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get("https://dailyfinance.roadtocareer.net/");
    }
//    @AfterTest
//    public void closeDriver(){
//        driver.quit();
//    }
}