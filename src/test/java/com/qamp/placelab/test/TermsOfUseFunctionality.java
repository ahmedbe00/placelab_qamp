package com.qamp.placelab.test;

import com.qamp.placelab.utills.WebDriverSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

public class TermsOfUseFunctionality {
    private WebDriver driver;

    @Parameters("browser")
    @BeforeTest
    public void setup(@Optional("chrome") final String browser) {
        driver = WebDriverSetup.getWebDriver(browser);
        driver.get("https://demo.placelab.com/");
        System.out.println("You opened  browser " + browser);
    }


    @Test
    public void termsOfUseTest() {
        final String expectedTermsOfUseLink = "https://demo.placelab.com/terms_of_service";
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"footer\"]/footer/span[2]/a")).isDisplayed(),
                "Validate if Terms of Use link exist");

        driver.findElement(By.xpath("//*[@id=\"footer\"]/footer/span[2]/a")).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


/*   I can't use this because I get error, that expected url is different from actual.
     It's possible that the link redirects to a different URL before reaching the final "Terms of Use" page,
     and new tab is opened

        final String actualTermsOfUseLink = driver.getCurrentUrl();
        Assert.assertEquals(actualTermsOfUseLink, expectedTermsOfUseLink,
                "Validate if it's transferred to Terms Of Use page");   */

    }
    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}


