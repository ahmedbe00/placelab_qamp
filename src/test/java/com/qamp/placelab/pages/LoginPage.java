package com.qamp.placelab.pages;

import com.sun.jdi.PrimitiveValue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPage {
    private final static By LOGIN_FORM = By.id("login_form");
    private final static String EXPECTED_PAGE_TITLE = "PlaceLab";
    private final static By EMAIL_INPUT = By.id("email");
    private final static By PASSWORD_INPUT = By.id("password");
    private final static By SUBMIT_LOGIN = By.name("commit");
    private final static By DROP_DOWN_BUTTON = By.cssSelector("#user-name > i");
    private final static By SIGN_OUT_BUTTON = By.xpath(".//a[text()='Sign out']");
    private final static By ERROR_MESSAGE = By.xpath("//div[@class='error-area']");
    private final WebDriver driver;

    public LoginPage(final WebDriver webDriver) {
        this.driver = webDriver;
    }

    public void validateLogInPageContent() {

        final boolean isLogInFormDisplayed = driver.findElement(LOGIN_FORM).isDisplayed();
        final String actualPageTitle = driver.getTitle();
        Assert.assertTrue(isLogInFormDisplayed, "Validate log in form is displayed");
        Assert.assertEquals(actualPageTitle, EXPECTED_PAGE_TITLE, "Validate page title is correct");

    }

    public void enterCredentials(final String email, final String password) {
        //enter a email
        driver.findElement(EMAIL_INPUT).sendKeys(email);

        //enter a password
        driver.findElement(PASSWORD_INPUT).sendKeys(password);

        //submit
        driver.findElement(SUBMIT_LOGIN).click();

    }

    public  void logout(){
        Assert.assertTrue(
                driver.findElement(DROP_DOWN_BUTTON).isDisplayed(),
                "Validate drop down button is displayed");
        driver.findElement(DROP_DOWN_BUTTON).click();
        driver.findElement(SIGN_OUT_BUTTON).click();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public  void validateErrorMessage(){
        Assert.assertTrue(
                driver.findElement(ERROR_MESSAGE).isDisplayed(), "Validate that we receive a error message");
    }

}
