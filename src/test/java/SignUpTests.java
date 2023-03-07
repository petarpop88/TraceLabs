import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class SignUpTests extends BaseTest {

    //environment = testing
    //captcha = false

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

        signUpPage.typeUsername(Users.USERNAME);

        signUpPage.typeEmail(Users.EMAIL);
        signUpPage.typeConfirmEmail(Users.EMAIL);

        signUpPage.typePassword(Users.PASSWORD);
        signUpPage.typeConfirmPassword(Users.PASSWORD);

        signUpPage.checkOnTermsAndConditionsCheckBox();

        //Google Captcha is disabled in testing environment
        signUpPage.clickOnCreateAnAccountButton();


        //Verify success message
        Assert.assertEquals(SuccesMessages.SUCCESS_MESSAGE, signUpPage.getSuccessMessage());

        //Verify that second paragraph of success message equals success message with registered email
        Assert.assertEquals(SuccesMessages.SUCCESS_MESSAGE_WITH_EMAIL, signUpPage.getSuccessMessageWithEmail());

    }

    @Test
    //#3: Sign up new user with data from previous test and verify that username is already taken
    public void signUpWithExistingUser() {

        signUpPage.typeUsername(Users.USERNAME);

        signUpPage.typeEmail(Users.EMAIL);
        signUpPage.typeConfirmEmail(Users.EMAIL);

        signUpPage.typePassword(Users.PASSWORD);
        signUpPage.typeConfirmPassword(Users.PASSWORD);

        signUpPage.checkOnTermsAndConditionsCheckBox();

        //Google Captcha is disabled in testing environment
        signUpPage.clickOnCreateAnAccountButton();

        //Verify that username is already in use
        Assert.assertEquals(signUpPage.getTakenUsernameMessage(), ErrorMessages.TAKEN_USERNAME_ERROR_MESSAGE);

    }

    @Test
    //#4: Sign up new user with new username and existing email. Verify that user successfully register and get success message
    public void signUpWithNewUsernameAndExistingEmail() {

        signUpPage.typeUsername(Users.NEW_USERNAME);

        signUpPage.typeEmail(Users.EMAIL);
        signUpPage.typeConfirmEmail(Users.EMAIL);

        signUpPage.typePassword(Users.PASSWORD);
        signUpPage.typeConfirmPassword(Users.PASSWORD);

        signUpPage.checkOnTermsAndConditionsCheckBox();

        //Google Captcha is disabled in testing environment
        signUpPage.clickOnCreateAnAccountButton();

        //Verify success messages
        Assert.assertEquals(signUpPage.getSuccessMessage(), SuccesMessages.SUCCESS_MESSAGE);

        //Verify that second paragraph of success message equals success message with registered email
        Assert.assertEquals(signUpPage.getSuccessMessageWithEmail(), SuccesMessages.SUCCESS_MESSAGE_WITH_EMAIL);

        //When user go to mailbox there is message "Welcome Back - Login to your account [Etherscan.io]".
        //This is a good practice for database security, because there is no any toast message to show that this email is in use and in our database until user check mailbox.

    }

    @Test
    //#5: Sign up new user with newsletter check box and accept cookies. Verify that user successfully register and get success message
    public void signUpWithNewUserWithNewsletter() {

        signUpPage.clickOnCookieGotItButton();
        signUpPage.typeUsername(Users.NEWSLETTER_USERNAME);

        signUpPage.typeEmail(Users.NEWSLETTER_EMAIL);
        signUpPage.typeConfirmEmail(Users.NEWSLETTER_EMAIL);

        signUpPage.typePassword(Users.PASSWORD);
        signUpPage.typeConfirmPassword(Users.PASSWORD);

        signUpPage.checkOnTermsAndConditionsCheckBox();
        signUpPage.checkOnNewsletterCheckBox();

        //Google Captcha is disabled in testing environment
        signUpPage.clickOnCreateAnAccountButton();

        //Verify success messages
        Assert.assertEquals(signUpPage.getSuccessMessage(), SuccesMessages.SUCCESS_MESSAGE);

        //Verify that second paragraph of success message equals success message with registered email
        Assert.assertEquals(signUpPage.getSuccessMessageWithEmail(), SuccesMessages.SUCCESS_MESSAGE_WITH_EMAIL);

    }

    @Test
    //#6: Sign up new user without username. Verify that user can't register without username and error message appears under username fields
    public void signUpNewUserWithoutUsername() {

        signUpPage.typeEmail(Users.EMAIL);
        signUpPage.typeConfirmEmail(Users.EMAIL);

        signUpPage.typePassword(Users.PASSWORD);
        signUpPage.typeConfirmPassword(Users.PASSWORD);

        signUpPage.checkOnTermsAndConditionsCheckBox();

        //Google Captcha is disabled in testing environment
        signUpPage.clickOnCreateAnAccountButton();

        Assert.assertEquals(signUpPage.getShortUsernameMessage(), ErrorMessages.ENTER_USERNAME_MESSAGE);

    }

    @Test
    //#7: Enter short username. Verify that user get error message under username field.
    public void shortUsername() {

        signUpPage.typeUsername(Users.SHORT_USERNAME);
        Assert.assertEquals(signUpPage.getShortUsernameMessage(), ErrorMessages.SHORT_USERNAME_MESSAGE);
    }

    @Test
    //#6: Enter username with special char. Verify that user get error message under username field.
    public void alphaNumericOnly() {

        signUpPage.typeUsername(Users.SPECIAL_CHAR_IN_USERNAME);
        Assert.assertEquals(signUpPage.getOnlyAlphanumericCharsAllowedMessage(), ErrorMessages.ONLY_ALPHA_CHARS_ALLOWED_MESSAGE);

    }

    @Test
    //#8: Sign up new user without email. Verify that user get error messages under email address and confirm email address fields
    public void signUpNewUserWithoutEmail() {

        signUpPage.typeUsername(Users.USERNAME);

        signUpPage.typePassword(Users.PASSWORD);
        signUpPage.typeConfirmPassword(Users.PASSWORD);

        signUpPage.checkOnTermsAndConditionsCheckBox();

        //Google Captcha is disabled in testing environment
        signUpPage.clickOnCreateAnAccountButton();

        Assert.assertEquals(signUpPage.getInvalidEmailMessage(), ErrorMessages.RE_ENTER_EMAIL_MESSAGE);
        Assert.assertEquals(signUpPage.getInvalidConfirmEmailMessage(), ErrorMessages.RE_ENTER_EMAIL_MESSAGE);

    }

    @Test
    //#9: Input two different emails. Verify that user get error message under confirm email address field
    public void differentEmails() {

        signUpPage.typeEmail(Users.EMAIL);
        signUpPage.typeConfirmEmail(faker.internet().emailAddress());
        Assert.assertEquals(signUpPage.getEmailDoesNotMatchMessage(), ErrorMessages.DIFFERENT_EMAIL_MESSAGE);

    }

    @Test
    //#10: Input invalid email type. Verify that user get error message under email address field
    public void inputInvalidEmailType() {

        signUpPage.typeEmail(Users.INVALID_EMAIL_FORMAT);
        Assert.assertEquals(signUpPage.getInvalidEmailMessage(), ErrorMessages.EMAIL_ERROR_MESSAGE);
    }

    @Test
    //11: Sign up new user without password. Verify that user get error messages under password and confirm password fields
    public void signUpNewUserWithoutPassword() {

        signUpPage.typeUsername(Users.USERNAME);

        signUpPage.typeEmail(Users.EMAIL);
        signUpPage.typeConfirmEmail(Users.EMAIL);

        signUpPage.checkOnTermsAndConditionsCheckBox();

        //Google Captcha is disabled in testing environment
        signUpPage.clickOnCreateAnAccountButton();


        Assert.assertEquals(signUpPage.getEnterPasswordMessage(), ErrorMessages.ENTER_PASSWORD_MESSAGE);
        Assert.assertEquals(signUpPage.getConfirmPasswordErrorMessage(), ErrorMessages.SHORT_PASSWORD_MESSAGE);

    }

    @Test
    //12: Sign up new user with minimum password requirements. Verify that user successfully register and get success message
    public void signUpNewWithMinimalPasswordReq() {

        signUpPage.typeUsername(faker.gameOfThrones().character());

        signUpPage.typeEmail(Users.RANDOM_EMAIL);
        signUpPage.typeConfirmEmail(Users.RANDOM_EMAIL);

        signUpPage.typePassword(Users.MINIMUM_PASSWORD_CRITERIA);
        signUpPage.typeConfirmPassword(Users.MINIMUM_PASSWORD_CRITERIA);

        signUpPage.checkOnTermsAndConditionsCheckBox();

        //Google Captcha is disabled in testing environment
        signUpPage.clickOnCreateAnAccountButton();

        //Verify success messages
        Assert.assertEquals(signUpPage.getSuccessMessage(), SuccesMessages.SUCCESS_MESSAGE);

        //Verify that second paragraph of success message contains registered email
        Assert.assertTrue(signUpPage.getSuccessMessageWithEmail().contains(Users.EMAIL));

    }
    @Test
    //13: Input short password in password fields. Verify that user get error messages under password and confirm password fields
    public void inputShortPassword() {

        signUpPage.typePassword(Users.SHORT_PASSWORD);
        signUpPage.typeConfirmPassword(Users.SHORT_PASSWORD);

        Assert.assertEquals(signUpPage.getShortPasswordMessage(), ErrorMessages.SHORT_PASSWORD_MESSAGE);
        Assert.assertEquals(signUpPage.getConfirmShortPasswordMessage(), ErrorMessages.SHORT_PASSWORD_MESSAGE);

    }

    @Test
    //14: Input different passwords in password fields. Verify that user get error message under confirm password address field
    public void differentPasswords() {

        signUpPage.typePassword(Users.PASSWORD);
        signUpPage.typeConfirmPassword(Users.MINIMUM_PASSWORD_CRITERIA);

        Assert.assertEquals(signUpPage.getPasswordDoesNotMatchMessage(), ErrorMessages.PASSWORD_DOES_NOT_MATCH_MESSAGE);
    }

    @Test
    //15: Input valid passwords in password fields. Verify that password is masked, click to show password and then verify that password is visible
    public void checkMaskedPassword() {

        signUpPage.typePassword(Users.PASSWORD);
        signUpPage.typeConfirmPassword(Users.PASSWORD);

        //Verify that get text from type is "password" which means text is masked.
        Assert.assertEquals(signUpPage.getTextFromType(), "password");

        //Method for revealing passwords. Clicking on any of two eye-icons shows both passwords
        signUpPage.showPassword();

        //Verify that get text from type is "text, which means text is unmasked
        Assert.assertEquals(signUpPage.getTextFromType(), "text");
    }
    @Test
    //16: Sign up new user without checking box for Terms and Conditions. Verify that user can't register and error message appears under
    public void signUpNewUserWithWithoutTermsAndConditions() {

        signUpPage.typeUsername(faker.gameOfThrones().character());

        signUpPage.typeEmail(Users.RANDOM_EMAIL);
        signUpPage.typeConfirmEmail(Users.RANDOM_EMAIL);

        signUpPage.typePassword(Users.MINIMUM_PASSWORD_CRITERIA);
        signUpPage.typeConfirmPassword(Users.MINIMUM_PASSWORD_CRITERIA);

        //Google Captcha is disabled in testing environment
        signUpPage.clickOnCreateAnAccountButton();

        //Verify error message under terms and conditions check box
        Assert.assertEquals(signUpPage.getAcceptTermsMessage(), ErrorMessages.ACCEPT_TERMS_MESSAGE);


    }


}