import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignInRoyalCaribbeanTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions().addArguments("--incognito");

        String chromeDriverPath = "build/resources/main/chromedriver";
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, 10);
        try {
            driver.get("https://www.royalcaribbean.com/account/signin");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCheckCorrectWebsite() {
        Assert.assertEquals("https://www.royalcaribbean.com/account/signin", driver.getCurrentUrl());
        Assert.assertEquals("Royal Caribbean", driver.getTitle());
    }

    @Test
    public void testSignIn() {
        WebElement emailField = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("form.login__container_form input[aria-label='Email address or username']")));
        WebElement passwordField = driver.findElement(
                By.cssSelector("form.login__container_form input[aria-label='Password']"));
        WebElement submitButton = driver.findElement(
                By.cssSelector("form.login__container_form button[type='submit']"));

        // todo enter in valid email and password
        emailField.sendKeys("rctest-automatedwebtesting@gmail.com");
        passwordField.sendKeys("password1");
        submitButton.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("#nav-profile-button")));
        Assert.assertEquals("https://www.royalcaribbean.com/account/", driver.getCurrentUrl());

        // todo enter in first name of account
        Assert.assertEquals("Rc",
                driver.findElement(By.cssSelector("h2.hero-header__user-name")).getText());
        Assert.assertEquals("Royal Caribbean", driver.getTitle());
    }

    @After
    public void closeTab() {
        driver.quit();
    }
}
