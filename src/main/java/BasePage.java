import com.github.javafaker.Faker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    protected final Logger log = LogManager.getLogger();
    protected WebDriver driver;
    protected WebDriverWait webDriverWait;
    protected Faker faker;

    public BasePage(WebDriver driver, WebDriverWait webDriverWait, Faker faker) {
        this.driver = driver;
        this.webDriverWait = webDriverWait;
        this.faker = faker;
    }

    protected WebElement getWebElement(By locator) {
        log.trace("getWebElement(" + locator + ")");
        return driver.findElement(locator);
    }


    protected void clearAndTypeTextToWebElement(WebElement element, String text) {
        log.trace("clearAndTypeTextToWebElement(" + element + ", " + text + ")");
        element.clear();
        element.sendKeys(text);
    }

    public boolean isWebElementDisplayed(By locator) {
        log.trace("isWebElementDisplayed(" + locator + ")");
        try {
            WebElement element = getWebElement(locator);
            return element.isDisplayed();
        } catch (Exception e) {
            log.trace(e.getMessage());
            return false;
        }
    }
}