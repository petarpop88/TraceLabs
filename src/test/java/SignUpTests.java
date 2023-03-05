import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class SignUpTests extends BaseTest {

    //Environment: TESTING
    //CAPTCHA = false

    @Test
    //#1: Visit registation page and verify that mandatory fields are present
    public void visitRegisterPage() {

        String expectedResult = baseUrl;
        String actualResult = driver.getCurrentUrl();
        Assert.assertEquals(actualResult, expectedResult);

        Assert.assertTrue(signUpPage.isUsernameFieldDisplayed());
        Assert.assertTrue(signUpPage.isEmailFieldDisplayed());
        Assert.assertTrue(signUpPage.isConfirmEmailFieldDisplayed());
        Assert.assertTrue(signUpPage.isPasswordFieldDisplayed());
        Assert.assertTrue(signUpPage.isConfirmPasswordFieldDisplayed());
        Assert.assertTrue(signUpPage.isTermsAndConditionsCheckBoxDisplayed());
        Assert.assertTrue(signUpPage.isCreateAnAccountButtonDisplayed());
    }

    @Test
    //#2 HAPPY FLOW - Sign up new user with valid data
    public void signUpNewUserWithValidData() {

        signUpPage.clickOnCookieGotItButton();

        signUpPage.typeUsername(Users.USERNAME);

        signUpPage.typeEmail(Users.EMAIL);
        signUpPage.typeConfirmEmail(Users.EMAIL);

        signUpPage.typePassword(Users.PASSWORD);
        signUpPage.typeConfirmPassword(Users.PASSWORD);

        signUpPage.checkOnTermsAndConditionsCheckBox();

        //Google Captcha is disabled in testing environment
        signUpPage.clickOnCreateAnAccountButton();

        //Verify success messagess
        Assert.assertEquals(SuccesMessages.SUCCESS_MESSAGE, signUpPage.successMessage());

        //Verify that second paragraph of success message equals success message with registered email
        Assert.assertEquals(signUpPage.successMessageWithEmail(),SuccesMessages.SUCCESS_MESSAGE_WITH_EMAIL);

    }

    @Test
    //#3: Sign up new user with data from previous test and verify that username is already taken
    public void signUpWithExistingUser() {

        signUpPage.clickOnCookieGotItButton();

        signUpPage.typeUsername(Users.USERNAME);

        signUpPage.typeEmail(Users.EMAIL);
        signUpPage.typeConfirmEmail(Users.EMAIL);

        signUpPage.typePassword(Users.PASSWORD);
        signUpPage.typeConfirmPassword(Users.PASSWORD);

        signUpPage.checkOnTermsAndConditionsCheckBox();

        //Google Captcha is disabled in testing environment
        signUpPage.clickOnCreateAnAccountButton();

        //Verify that username is already in use
        Assert.assertEquals(ErrorMessages.TAKEN_USERNAME_ERROR_MESSAGE, signUpPage.takenUsernameMessage());

    }

    @Test
    //#4: Sign up new user with new username and existing email. Verify that you successfully register and get success message
    public void signUpWithNewUsernameAndExistingEmail() {

        signUpPage.clickOnCookieGotItButton();

        signUpPage.typeUsername(Users.NEW_USERNAME);

        signUpPage.typeEmail(Users.EMAIL);
        signUpPage.typeConfirmEmail(Users.EMAIL);

        signUpPage.typePassword(Users.PASSWORD);
        signUpPage.typeConfirmPassword(Users.PASSWORD);

        signUpPage.checkOnTermsAndConditionsCheckBox();

        //Google Captcha is disabled in testing environment
        signUpPage.clickOnCreateAnAccountButton();

        //Verify success messagess
        Assert.assertEquals(SuccesMessages.SUCCESS_MESSAGE, signUpPage.successMessage());

        //Verify that second paragraph of success message equals success message with registered email
        Assert.assertEquals(signUpPage.successMessageWithEmail(),SuccesMessages.SUCCESS_MESSAGE_WITH_EMAIL);

        //When user go to mailbox there is message "Welcome Back - Login to your account [Etherscan.io]".
        //This is a good practice for database security, because there is no any toast message to show that this email is in use and in our database until user check mailbox.

    }

    @Test
    //#4: Sign up new user with newsletter check box
    public void signUpWithNewUserWithNewsletter() {
        signUpPage.clickOnCookieGotItButton();

        signUpPage.typeUsername(Users.NEWSLETTER_USERNAME);

        signUpPage.typeEmail(Users.RANDOM_EMAIL);
        signUpPage.typeConfirmEmail(Users.RANDOM_EMAIL);

        signUpPage.typePassword(Users.PASSWORD);
        signUpPage.typeConfirmPassword(Users.PASSWORD);

        signUpPage.checkOnTermsAndConditionsCheckBox();
        signUpPage.checkOnNewsletterCheckBox();

        //Google Captcha is disabled in testing environment
        signUpPage.clickOnCreateAnAccountButton();

        //Verify success messagess
        Assert.assertEquals(SuccesMessages.SUCCESS_MESSAGE, signUpPage.successMessage());

        //Verify that second paragraph of success message equals success message with registered email
        Assert.assertEquals(signUpPage.successMessageWithEmail(),SuccesMessages.SUCCESS_MESSAGE_WITH_EMAIL);

    }

}