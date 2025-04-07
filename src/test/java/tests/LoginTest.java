package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.testng.Assert;
import pages.LoginPage;
import utils.LoginDataProvider;

import java.time.Duration;

public class LoginTest {
    private WebDriver driver;
    private WebDriverWait wait;
    @BeforeMethod
    public void start(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterMethod
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
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"), "Login failed");
    }

    @Test

    public void testLoginInvalidCred(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginMethod("abcd", "1234");
        String error = driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3")).getText();
        System.out.println(error);
        Assert.assertTrue(error.contains("Username and password do not match"));
    }

    @Test(dataProvider = "LoginCredentials", dataProviderClass = LoginDataProvider.class)
    public void testMultipleLogins(String username, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginMethod(username, password);

        if (username.equals("locked_out_user")) {
            String errorText = driver.findElement(By.cssSelector("[data-test='error']")).getText();
            Assert.assertTrue(errorText.contains("locked out"));
        } else {
            Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));
        }
    }




}
