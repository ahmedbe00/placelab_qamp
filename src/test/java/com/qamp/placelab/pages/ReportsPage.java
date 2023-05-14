package com.qamp.placelab.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class ReportsPage {
    private WebDriver driver;
    private final static String LOGIN_PAGE_TITLE = "https://demo.placelab.com/queries";
    private final static By SEARCH_BAR = By.id("query_search_name");
    private final static By SEARCH_FILTERS = By.id("filters-table");
    private final static By REPORTS_HEADER = By.xpath("//h2[text()='Reports']");
    private final static By REPORTS_TABLE = By.id("table_queries");
    private final static By DELETE_ICON = By.cssSelector("a[data-original-title='Delete']");
    private final static By POPUP_MESSAGE_CONFIRM_BUTTON = By.id("confirm-link");

    public ReportsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void validateReportsPageElements() {
        Assert.assertEquals(driver.getCurrentUrl(), LOGIN_PAGE_TITLE,
                "Validate correct address opens after clicking on 'Reports' link.");
        Assert.assertTrue(driver.findElement(SEARCH_BAR).isDisplayed(),
                "Validate search bar on Reports page is displayed.");
        Assert.assertTrue(driver.findElement(SEARCH_FILTERS).isDisplayed(),
                "Validate search filters on Reports page are displayed.");
        Assert.assertTrue(driver.findElement(REPORTS_HEADER).isDisplayed(),
                "Validate reports header on Reports page is displayed.");
        Assert.assertTrue(driver.findElement(REPORTS_TABLE).isDisplayed(),
                "Validate reports table on Reports page is displayed.");
    }

    public void openReport(final String reportTitle) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(" //a[contains(text(), '" + reportTitle + "')]"))));
        driver.findElement(By.xpath("//a[contains(text(), '" + reportTitle + "')]")).click();
    }

    public void deleteReport(final String reportTitle) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[contains(text(), '" + reportTitle + "')]"))));
        driver.findElement(By.xpath("//a[contains(text(), '" + reportTitle + "')]/parent::div/parent::td/parent::tr/td/div[contains(@class, 'icheckbox')]")).click();
        driver.findElement(DELETE_ICON).click();
        wait.until(ExpectedConditions.elementToBeClickable(POPUP_MESSAGE_CONFIRM_BUTTON));
        driver.findElement(POPUP_MESSAGE_CONFIRM_BUTTON).click();
    }
}
