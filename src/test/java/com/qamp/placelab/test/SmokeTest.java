package com.qamp.placelab.test;


import com.beust.jcommander.Parameter;
import com.qamp.placelab.utills.WebDriverSetup;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SmokeTest {
    private WebDriver driver;
    @Parameters("browser")
    @BeforeTest
    private void setup(final String browser) {
        String browserName = System.getProperty("browser");
        driver = WebDriverSetup.getWebDriver(browser);
        driver.get("https://demo.placelab.com/");
        System.out.println("You opened  browser");
    }

    @Test
    private void openPage() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Browser you opened: " + System.getProperty("browser"));
        System.out.println("Webpage title is : " + driver.getTitle());
    }

    @AfterTest
    private void teardown() {

        driver.close();
    }


}