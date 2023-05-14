package com.qamp.placelab.test;

import com.qamp.placelab.pages.ForgotPasswordPage;
import com.qamp.placelab.pages.LoginPage;
import com.qamp.placelab.utills.WebDriverSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class ForgotYourPasswordFunctionality {
    private WebDriver driver;
    private ForgotPasswordPage forgotPasswordPage;

    @Parameters("browser")
    @BeforeTest
    public void setup(@Optional("chrome") final String browser) {
        driver = WebDriverSetup.getWebDriver(browser);
        driver.get("https://demo.placelab.com/");
        System.out.println("You opened  browser " + browser);
        this.forgotPasswordPage = new ForgotPasswordPage(driver);
    }

    @Parameters("email")
    @Test(description = "User is able to change password ")
    public void forgotPasswordFunctionality(final String email) {

        //open a Forgot password link
        driver.findElement(By.xpath("//*[@id=\"password-area\"]/a")).click();

        //validate forgot password page
        forgotPasswordPage.validateForgotPasswordPage();

        // enter a email
        forgotPasswordPage.enterCredentials(email);
        driver.findElement(By.xpath("//*[@id=\"login_form\"]/input[3]")).click();

        // validate a Password reset page
        forgotPasswordPage.validatePasswordResetPage();
    }

    @AfterTest
    public void tearDown() {
        driver.close();
    }
}

