import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait webDriverWait;
    protected Faker faker;
    protected SignUpPage signUpPage;
    protected final String baseUrl = "https://etherscan.io/register";


    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\HP\\Local\\webdriver/chromedriver.exe");
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(20));
        faker = new Faker();
        signUpPage = new SignUpPage(driver, webDriverWait, faker);

    }

    @BeforeMethod
    public void beforeMethod() {
        driver.get(baseUrl);
        driver.manage().window().maximize();
    }

    //@AfterClass
    //public void afterClass() {
        //driver.quit();
   // }

}