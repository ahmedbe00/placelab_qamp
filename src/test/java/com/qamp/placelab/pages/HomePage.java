package com.qamp.placelab.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class HomePage {
    private final static String EXPECTED_USER_ROLE = "Group Admin";
    private final static By PLACELAB_LOGO = By.xpath("/html/body/div[2]/div[1]/div/a/img");
    private final static By CREATE_REPORT_DROPDOWN_MENU_BUTTON = By.xpath("//*[@id=\"create-menu\"]/i");
    private final static By GO_TO_REPORTS_PAGE_LINK = By.id("queries-nav-item");

    private final WebDriver driver;
    public HomePage(final WebDriver webDriver) {
        this.driver = webDriver;
    }
     public void validateHomePageContent(){
        final String actualUserRole=driver.findElement(By.id("user-role")).getText();
         Assert.assertEquals(actualUserRole ,EXPECTED_USER_ROLE, "Validate user role is correct");
         final boolean isPlaceLabLogoDisplayed = driver.findElement(PLACELAB_LOGO).isDisplayed();
         final boolean isCreateReportDropDownMenuButtonDisplayed = driver.findElement(CREATE_REPORT_DROPDOWN_MENU_BUTTON).isDisplayed();
     }
    public void goToReportsPage() {
        driver.findElement(GO_TO_REPORTS_PAGE_LINK).click();
    }

}
