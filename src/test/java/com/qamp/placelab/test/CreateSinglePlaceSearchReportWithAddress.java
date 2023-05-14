package com.qamp.placelab.test;

import com.qamp.placelab.pages.CreateSinglePlaceSearchPage;
import com.qamp.placelab.pages.HomePage;
import com.qamp.placelab.pages.LoginPage;
import com.qamp.placelab.pages.ReportsPage;
import com.qamp.placelab.utills.WebDriverSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class CreateSinglePlaceSearchReportWithAddress {
    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;
    private ReportsPage reportsPage;
    private CreateSinglePlaceSearchPage createSinglePlaceSearchPage;
    @Parameters({"browser", "email", "password"})
    @BeforeTest
    public void setup(@Optional("chrome") final String browser, final String email, final String password) {
        driver = WebDriverSetup.getWebDriver(browser);
        driver.get("https://demo.placelab.com/");
        System.out.println("You opened  browser " + browser);
        loginPage = new LoginPage(driver);

        //enter credentials
        loginPage.enterCredentials(email, password);
    }

    @Test(description = "User is  able to create a single place search Report ")
    public void createSinglePlaceSearchReportTest() {
        homePage=new HomePage(driver);
        createSinglePlaceSearchPage=new CreateSinglePlaceSearchPage(driver);

        //validate Home page
        homePage.validateHomePageContent();

        //open a Single Place search
        Assert.assertTrue(
                driver.findElement(By.xpath("//*[@id=\"create-menu\"]/i")).isDisplayed(),
                "Validate drop down button is displayed");
        driver.findElement(By.xpath("//*[@id=\"create-menu\"]/i")).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.findElement(By.xpath("//*[@id=\"singleplacesearch\"]/a/label")).click();

        //validate a Single Place Search Page
        createSinglePlaceSearchPage.validateCreateSinglePlaceSearchPageContent();

        //enter a credentials with address
        createSinglePlaceSearchPage.enterCredentialsByAddress();

        //again validate home page ( with that we knows that Report is successfully created)
        homePage.validateHomePageContent();

       /* homePage.goToReportsPage();
        reportsPage.validateReportsPageElements();
       reportsPage.openReport(reportTitle);
        reportsPage.deleteReport(reportTitle); */


        //logout
        loginPage.logout();

    }
    @AfterTest
    public void teardown() {
        driver.close();
    }

}
