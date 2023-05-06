package com.qamp.placelab.test;

import com.qamp.placelab.utills.WebDriverSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginWithValidCredentials {
    private WebDriver driver;

    @Parameters("browser")
    @BeforeTest
    public void setup(final String browser) {
        driver = WebDriverSetup.getWebDriver(browser);
        driver.get("https://demo.placelab.com/");
        System.out.println("You opened  browser " + browser);
    }

    @Parameters("password")
    @Test
    public void loginWithValidEmailPassword(final String password) {


        final String expectedAdminUserRole = "Group Admin";
        Assert.assertTrue(
                driver.findElement(By.id("login_form")).isDisplayed(),
                "Validate login from is displayed"
        );

        //enter a email
        driver.findElement(By.id("email")).sendKeys("ahmedberbic2000@gmail.com");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        //enter a password
        driver.findElement(By.id("password")).sendKeys(password);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //submit
        driver.findElement(By.name("commit")).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // verify user role
        final String actualUserRole = driver.findElement(By.id("user-role")).getText();
        Assert.assertEquals(actualUserRole, expectedAdminUserRole, "Validate user ROLE for logged user");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        //logout
        Assert.assertTrue(
                driver.findElement(By.cssSelector("#user-name > i")).isDisplayed(),
                "Validate drop down button is displayed");
        driver.findElement(By.cssSelector("#user-name > i")).click();
        driver.findElement(By.xpath(".//a[text()='Sign out']")).click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
        @AfterTest
        public void teardown () {
            driver.close();
        }
}


