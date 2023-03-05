import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;


import org.testng.Assert;

public class SignUpPage extends BasePage {


    private final By cookieGotItButtonLocator = By.id("btnCookie");
    private final By usernameFieldLocator = By.id("ContentPlaceHolder1_txtUserName");
    private final By emailFieldLocator = By.id("ContentPlaceHolder1_txtEmail");
    private final By confirmEmailFieldLocator = By.id("ContentPlaceHolder1_txtConfirmEmail");
    private final By passwordFieldLocator = By.id("ContentPlaceHolder1_txtPassword");
    private final By confirmPasswordFieldLocator = By.id("ContentPlaceHolder1_txtPassword2");
    private final By termsAndConditionsCheckBoxLocator = By.id("ContentPlaceHolder1_MyCheckBox");
    private final By createAnAccountButtonLocator = By.id("ContentPlaceHolder1_btnRegister");
    private final By successMessageLocator = By.xpath("//*[@id=\"ctl00\"]/div[3]");
    private final By successMessageWithEmailLocator = By.xpath("//*[@id=\"ctl00\"]/p[1]");
    private final By takenUsernameMessageLocator = By.xpath("//*[@id=\"ctl00\"]/div[3]");
    private final By shortUsernameMessageLocator = By.id("ContentPlaceHolder1_txtUserName-error");
    private final By onlyAlphanumericCharsAllowedMessageLocator = By.id("ContentPlaceHolder1_txtUserName-error");
    private final By invalidEmailTypeMessageLocator = By.id("ContentPlaceHolder1_txtEmail-error");
    private final By shortPasswordMessageLocator = By.id("ContentPlaceHolder1_txtUserName-error");

    private final By acceptTermsMessageLocator = By.id("ctl00$ContentPlaceHolder1$MyCheckBox-error");
    private final By newsletterCheckBoxLocator = By.id("ContentPlaceHolder1_SubscribeNewsletter");



    public SignUpPage(WebDriver driver, WebDriverWait webDriverWait, Faker faker) {
        super(driver, webDriverWait, faker);
    }


    public String getSuccessMessage() {
        log.debug("successMessage()");
        return driver.findElement(successMessageLocator).getText();
    }
    public String getSuccessMessageWithEmail() {
        log.debug("getSuccessMessageWithEmail()");
        return driver.findElement(successMessageWithEmailLocator).getText();
    }
    public String getTakenUsernameMessage() {
        log.debug("getTakenUsernameMessage()");
        return driver.findElement(takenUsernameMessageLocator).getText();
    }

    public String getShortUsernameMessage() {
        log.debug("getShortUsernameMessage()");
        return driver.findElement(shortUsernameMessageLocator).getText();
    }


    public String getOnlyAlphanumericCharsAllowedMessage() {
        log.debug("getOnlyAlphanumericCharsAllowedMessage()");
        return driver.findElement(onlyAlphanumericCharsAllowedMessageLocator).getText();
    }

    public String getInvalidEmailTypeMessage() {
        log.debug("getInvalidEmailTypeMessage()");
        return driver.findElement(invalidEmailTypeMessageLocator).getText();
    }
    public String getshortPasswordMessage() {
        log.debug("getshortPasswordMessage()");
        return driver.findElement(shortPasswordMessageLocator).getText();
    }
    public String getAcceptTermsMessage() {
        log.debug("getAcceptTermsMessage()");
        return driver.findElement(acceptTermsMessageLocator).getText();
    }

    public boolean isCookieButtonDisplayed() {
        log.debug("isCookieButtonDisplayed()");
        return isWebElementDisplayed(cookieGotItButtonLocator);
    }
    public boolean isUsernameFieldDisplayed() {
        log.debug("isUsernameFieldDisplayed()");
        return isWebElementDisplayed(usernameFieldLocator);
    }
    public boolean isEmailFieldDisplayed() {
        log.debug("isEmailFieldDisplayed()");
        return isWebElementDisplayed(emailFieldLocator);
    }
    public boolean isConfirmEmailFieldDisplayed() {
        log.debug("isConfirmEmailFieldDisplayed()");
        return isWebElementDisplayed(confirmEmailFieldLocator);
    }
    public boolean isPasswordFieldDisplayed() {
        log.debug("isPassswordFieldDisplayed()");
        return isWebElementDisplayed(passwordFieldLocator);
    }
    public boolean isConfirmPasswordFieldDisplayed() {
        log.debug("isConfirmPassswordFieldDisplayed()");
        return isWebElementDisplayed(confirmPasswordFieldLocator);
    }
    public boolean isTermsAndConditionsCheckBoxDisplayed() {
        log.debug("isTermsAndConditionsCheckBoxDisplayed()");
        return isWebElementDisplayed(termsAndConditionsCheckBoxLocator);
    }
    public boolean isCreateAnAccountButtonDisplayed() {
        log.debug("isCreateAnAccountButtonDisplayed()");
        return isWebElementDisplayed(createAnAccountButtonLocator);
    }
    public boolean isNewsletterCheckBoxDisplayed() {
        log.debug("isNewsletterCheckBoxDisplayed()");
        return isWebElementDisplayed(newsletterCheckBoxLocator);
    }


    public void clickOnCookieGotItButton() {
        log.debug("clickOnGotItButton");
        Assert.assertTrue(isCookieButtonDisplayed(), "Got It! button is NOT present on page!");
        WebElement cookieGotItButton = getWebElement(cookieGotItButtonLocator);
        cookieGotItButton.click();
    }

    public SignUpPage typeUsername(String username) {
        log.debug("typeUsername(" + username + ")");
        Assert.assertTrue(isUsernameFieldDisplayed(), "Username field is NOT present on page!");
        WebElement usernameField = getWebElement(usernameFieldLocator);
        clearAndTypeTextToWebElement(usernameField, username);
        return this;
    }

    public SignUpPage typeEmail(String email) {
        log.debug("typeEmail(" + email + ")");
        Assert.assertTrue(isEmailFieldDisplayed(), "Email field is NOT present on page!");
        WebElement typeEmailField = getWebElement(emailFieldLocator);
        clearAndTypeTextToWebElement(typeEmailField, email);
        return this;
    }

    public SignUpPage typeConfirmEmail(String email) {
        log.debug("typeEmail(" + email + ")");
        Assert.assertTrue(isConfirmEmailFieldDisplayed(), "Confirm email field is NOT present on page!");
        WebElement typeConfirmEmailField = getWebElement(confirmEmailFieldLocator);
        clearAndTypeTextToWebElement(typeConfirmEmailField, email);
        return this;
    }

    public SignUpPage typePassword(String password) {
        log.debug("typePassword(" + password + ")");
        Assert.assertTrue(isPasswordFieldDisplayed(), "Password field is NOT present on page!");
        WebElement typePasswordField = getWebElement(passwordFieldLocator);
        clearAndTypeTextToWebElement(typePasswordField, password);
        return this;
    }

    public SignUpPage typeConfirmPassword(String password) {
        log.debug("typeConfirmPassword(" + password + ")");
        Assert.assertTrue(isConfirmPasswordFieldDisplayed(), "Confirm password field is NOT present on page!");
        WebElement typeConfirmPasswordField = getWebElement(confirmPasswordFieldLocator);
        clearAndTypeTextToWebElement(typeConfirmPasswordField, password);
        return this;
    }

    public void checkOnTermsAndConditionsCheckBox() {
        log.debug("checkOnTermsAndConditionsCheckBox");
        Assert.assertTrue(isTermsAndConditionsCheckBoxDisplayed(), "Terms and Conditions check box is NOT present on page!");
        WebElement termsAndConditionsCheckBox = getWebElement(termsAndConditionsCheckBoxLocator);
        termsAndConditionsCheckBox.sendKeys(Keys.ENTER);
    }
    public void clickOnCreateAnAccountButton() {
        log.debug("clickOnCreateAnAccountButton");
        Assert.assertTrue(isCreateAnAccountButtonDisplayed(), "Create an account button is NOT present on page!");
        WebElement createAnAccountButton = getWebElement(createAnAccountButtonLocator);
        createAnAccountButton.click();
    }
    public void checkOnNewsletterCheckBox() {
        log.debug("checkOnNewsletterCheckBox");
        Assert.assertTrue(isNewsletterCheckBoxDisplayed(), "Newsletter check box is NOT present on page!");
        WebElement newsletterCheckBox = getWebElement(newsletterCheckBoxLocator);
        newsletterCheckBox.click();
    }

}