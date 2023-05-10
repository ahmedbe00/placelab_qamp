package com.qamp.placelab.pages;

import org.bouncycastle.jcajce.provider.drbg.DRBG;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class CreateSinglePlaceSearchPage {
    private final static By PLACELAB_LOGO = By.xpath("/html/body/div[2]/div[1]/div/a/img");
    private final static String EXPECTED_USER_ROLE = "Group Admin";
    private final static String EXPECTED_HEADER = "placeCreate Single Place Search Report";
    private final static String EXPECTED_PAGE_URL= "https://demo.placelab.com/places/single_place_searches/new";
    private final static By USER_ROLE = By.id("user-role");
    private final static By HEADER_TEXT = By.xpath("//*[@id=\"single_poi_dialog\"]/div[1]");
    private final static By REPORT_NAME = By.id("name");
    private final static By PLACE_NAME = By.id("single_text");
    private final static By PHONE_NUMBER = By.id("single_phone");
    private final static By CATEGORY_DROPDOWN_BUTTON = By.xpath("//*[@id=\"single_poi_query\"]/div[3]/div[2]/div/button/b");
    private final static By LOCATION_NAME= By.id("location_name");
    private final static By LATITUDE= By.id("city_lat");
    private final static By LONGITUDE= By.id("city_lng");
    private final static By CREATE_REPORT_BUTTON = By.xpath("//*[@id=\"single_poi_query\"]/button");
    private final static By RADIO_BUTTON_ACCOMMODATION = By.xpath("//*[@id=\"single_poi_query\"]/div[3]/div[2]/div/ul/div/li[2]/a/label");
    private final static By YES_BUTTON = By.xpath("/html/body/div[10]/div[3]/div/button[1]");




    private final WebDriver driver;

    public CreateSinglePlaceSearchPage(final WebDriver webDriver) {
        this.driver = webDriver;
    }
    public void validateCreateSinglePlaceSearchPageContent(){
        final String actualUserRole=driver.findElement(USER_ROLE).getText();
        Assert.assertEquals(actualUserRole ,EXPECTED_USER_ROLE, "Validate user role is correct");

        Assert.assertTrue(
                driver.findElement(PLACELAB_LOGO).isDisplayed(), "Validate that Logo is displayed");

        final String actualHeaderText = driver.findElement(HEADER_TEXT).getText();
        Assert.assertEquals(actualHeaderText,EXPECTED_HEADER,"Validate header text is correct");

        final String actualPageURL = driver.getCurrentUrl();
        Assert.assertEquals(actualPageURL,EXPECTED_PAGE_URL,"Validate Page URL is correct");

        // input fields displayed
        Assert.assertTrue(
                driver.findElement(REPORT_NAME).isDisplayed(), "Validate that Report Name field is displayed");
        Assert.assertTrue(
                driver.findElement(PLACE_NAME).isDisplayed(), "Validate that Place Name field is displayed");
        Assert.assertTrue(
                driver.findElement(PHONE_NUMBER).isDisplayed(), "Validate that PHONE field is displayed");
        Assert.assertTrue(
                driver.findElement(CATEGORY_DROPDOWN_BUTTON).isDisplayed(), "Validate that Category drop-down button is displayed");
        Assert.assertTrue(
                driver.findElement(LOCATION_NAME).isDisplayed(), "Validate that Location name field is displayed");
        Assert.assertTrue(
                driver.findElement(LATITUDE).isDisplayed(), "Validate that latidude field is displayed");
        Assert.assertTrue(
                driver.findElement(LONGITUDE).isDisplayed(), "Validate that longitude name field is displayed");
        Assert.assertTrue(
                driver.findElement(CREATE_REPORT_BUTTON).isDisplayed(), "Validate that create button is displayed");



    }
    public void enterCredentialsByAddress(){
        driver.findElement(REPORT_NAME).sendKeys("Malak Regency Hotel, Sarajevo");
        driver.findElement(PLACE_NAME).sendKeys("Ilidža");
        driver.findElement(CATEGORY_DROPDOWN_BUTTON).click();
        driver.findElement(RADIO_BUTTON_ACCOMMODATION).click();
        driver.findElement(LOCATION_NAME).sendKeys("R8F5+7H5, Hrasnička cesta bb, Ilidža 71210");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.findElement(By.xpath("//*[@id=\"single_poi_query\"]/div[4]/div[1]/div/ul/li[1]/a")).click();
        Assert.assertTrue(
                driver.findElement(YES_BUTTON).isDisplayed(),"Validate that YES button is displayed");
        driver.findElement(YES_BUTTON).click();
        driver.findElement(CREATE_REPORT_BUTTON).click();
    }
    public void enterCredentialsByMap(){

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 400);");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Actions actions = new Actions(driver);
        WebElement element = driver.findElement(By.xpath("//*[@id=\"single_poi_query\"]/div[4]/div[3]"));
        actions.moveToElement(element).click();
        driver.findElement(YES_BUTTON).click();

        // Here i receive a error message that element is not interactable, becuase after clicking on map the pop up
        // with Yes and NO button is not present, so that is the error and i can't find solution.

        driver.findElement(CREATE_REPORT_BUTTON).click();

    }

}
