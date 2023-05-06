package com.qamp.placelab.test;

import com.qamp.placelab.utills.WebDriverSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ForgotYourPasswordFunctionality {
    private WebDriver driver;

    @Parameters("browser")
    @BeforeTest
    public void setup(final String browser) {
        driver = WebDriverSetup.getWebDriver(browser);
        driver.get("https://demo.placelab.com/");
        System.out.println("You opened  browser " + browser);
    }

    @Parameters()
    @Test
    public void forgotPasswordFunctionality() {
        final String expectedForgotlink = "https://demo.placelab.com/password/forgot";
        final String expectedResetPasswordLink = "https://demo.placelab.com/email/sent";

        driver.findElement(By.xpath("//*[@id=\"password-area\"]/a")).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        final String actualForgotlink = driver.getCurrentUrl();
        Assert.assertEquals(actualForgotlink, expectedForgotlink,
                "Validate if it's transferred to Forgot link page");

        driver.findElement(By.id("email")).sendKeys("ahmedberbic2000@gmail.com");
        driver.findElement(By.xpath("//*[@id=\"login_form\"]/input[3]")).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        final String actualResetPasswordLink = driver.getCurrentUrl();
        Assert.assertEquals(actualResetPasswordLink, expectedResetPasswordLink,
                "Validate if it's transferred to Reset password link");
    }

        @AfterTest
        public void tearDown() {
            driver.close();
        }
    }

