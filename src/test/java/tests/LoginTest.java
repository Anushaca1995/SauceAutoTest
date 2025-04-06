package tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;

public class LoginTest {
    @Test
    public void testLoginWorks() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginMethod("standard_user", "secret_sauce");

        Thread.sleep(2000); // optional

        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));

        driver.quit();
    }
}
