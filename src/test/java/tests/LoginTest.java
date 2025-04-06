package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;

import java.time.Duration;

public class LoginTest {
    private WebDriver driver;
    private WebDriverWait wait;
    @Before
    public void start(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @After
    public void tearDown(){
        if (driver!=null){
            driver.quit();
        }
    }

    @Test
    public void testLoginWorks() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginMethod("standard_user", "secret_sauce");

        //Thread.sleep(2000); // waits for 2sec
        //Explicit wait for synchronization using selenium. waits for 10s until inventory url getting. ow goto next line
        wait.until(ExpectedConditions.urlContains("inventory"));
        Assert.assertTrue("Login failed", driver.getCurrentUrl().contains("inventory"));
    }

    @Test

    public void testLoginInvalidCred(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginMethod("abcd", "1234");
        String error = driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3")).getText();
        System.out.println(error);
        Assert.assertTrue(error.contains("Username and password do not match"));
    }

}
