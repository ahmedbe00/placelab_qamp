package com.qamp.placelab.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class ForgotPasswordPage {
    private final static String EXPECTED_PAGE_TITLE = "PlaceLab";
    private final static String EXPECTED_FORGOT_LINK= "https://demo.placelab.com/password/forgot";
    private final static By EMAIL_INPUT = By.id("email");
    private final WebDriver driver;
    public ForgotPasswordPage(final WebDriver webDriver) {
        this.driver = webDriver;
    }
    public void validateForgotPasswordPage(){
        final String actualPageTitle = driver.getTitle();
        final String actualForgotlink = driver.getCurrentUrl();
        Assert.assertEquals(actualForgotlink, EXPECTED_FORGOT_LINK, "Validate if it's transferred to Forgot link page");
        Assert.assertEquals(actualPageTitle, EXPECTED_PAGE_TITLE, "Validate page title is correct");
    }
    public void enterCredentials(final String email) {
        //enter a email
        driver.findElement(EMAIL_INPUT).sendKeys(email);
         }
    public void validatePasswordResetPage(){
        final String expectedResetPasswordLink = "https://demo.placelab.com/email/sent";
        final String actualResetPasswordLink = driver.getCurrentUrl();
        Assert.assertEquals(actualResetPasswordLink, expectedResetPasswordLink,
                "Validate if it's transferred to Reset password link");
    }
}
