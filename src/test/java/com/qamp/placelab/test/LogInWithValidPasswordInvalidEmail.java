package com.qamp.placelab.test;

import com.qamp.placelab.pages.LoginPage;
import com.qamp.placelab.utills.WebDriverSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.UUID;

public class LogInWithValidPasswordInvalidEmail {
    private WebDriver driver;
    private SoftAssert softAssert = new SoftAssert();
    private LoginPage loginPage;

    @Parameters("browser")
    @BeforeTest
    public void setup(@Optional("chrome") final String browser) {
        driver = WebDriverSetup.getWebDriver(browser);
        driver.get("https://demo.placelab.com/");
        System.out.println("You opened  browser " + browser);
        this.loginPage = new LoginPage(driver);
    }

    @Parameters("password")
    @Test(description = "User is not able to log in with invalid credentials ")
    public void loginWithInValidEmailPassword(final String password) {

        //validate log in page
        loginPage.validateLogInPageContent();

        //enter credentials
        final String randomEmail = UUID.randomUUID().toString();
        loginPage.enterCredentials(randomEmail, password);

        //verify that user can't log in with Invalid password
        loginPage.validateErrorMessage();
        loginPage.validateLogInPageContent();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    @AfterTest
    public void teardown() {
        driver.close();
    }
}
