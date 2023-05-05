package com.qamp.placelab.utills;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverSetup {
    public static WebDriver getWebDriver (final String browserName) {
        if (browserName.equals("chrome")) {
            return getChromeDriver();
        } else if (browserName.equals("edge")) {
            return getEdgeDriver();
        } else if (browserName.equals("firefox")) {
            return getFirefoxDriver();
        } else {
            throw new IllegalArgumentException("Match case not found for browser name: " + browserName);
        }
    }
    private static WebDriver getChromeDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    private static WebDriver getEdgeDriver () {
        WebDriverManager.edgedriver().setup();
        return new EdgeDriver();
    }
    private static WebDriver getFirefoxDriver () {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }

}
