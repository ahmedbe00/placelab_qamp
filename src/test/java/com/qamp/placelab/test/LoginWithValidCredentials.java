package com.qamp.placelab.test;

import com.qamp.placelab.pages.LoginPage;
import com.qamp.placelab.utills.WebDriverSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class LoginWithValidCredentials {
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

    @Parameters({"email", "password"})
    @Test(description = "User is  able to log in with valid credentials ")
    public void loginWithValidEmailPassword(final String email, final String password) {

        //validate log in page
        loginPage.validateLogInPageContent();

        //enter credentials
        loginPage.enterCredentials(email, password);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // verify user role
        final String expectedAdminUserRole = "Group Admin";
        final String actualUserRole = driver.findElement(By.id("user-role")).getText();
        Assert.assertEquals(actualUserRole, expectedAdminUserRole, "Validate user Role for logged user");

        //logout
        loginPage.logout();

    }

    @AfterTest
    public void teardown() {
        driver.close();
    }
}

/* <class name="com.qamp.placelab.test.LoginWithInvalidPassword" />
            <class name="com.qamp.placelab.test.TermsOfUseFunctionality" />
            <class name="com.qamp.placelab.test.ForgotYourPasswordFunctionality" /> */
