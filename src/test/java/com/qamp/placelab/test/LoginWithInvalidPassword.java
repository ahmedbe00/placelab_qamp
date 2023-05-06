package com.qamp.placelab.test;

import com.qamp.placelab.utills.WebDriverSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginWithInvalidPassword {
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
    public void loginWithValidEmailPassword() {
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
        driver.findElement(By.id("password")).sendKeys("123456");
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

        //verify that user can't log in with Invalid password
        Assert.assertTrue(
                driver.findElement(By.xpath("//div[@class='error-area']")).isDisplayed(),
                "Validate that we receive a error message"
        );
        Assert.assertTrue(
                driver.findElement(By.id("login_form")).isDisplayed(),
                "Validate login form is still displayed and we can try again"
        );
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    @AfterTest
    public void teardown () {
        driver.close();
    }
}


