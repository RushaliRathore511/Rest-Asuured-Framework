package tests.ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ui.LoginPage;

public class LoginUiTest {
    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        if (Boolean.parseBoolean(System.getProperty("headless", "false"))) {
            options.addArguments("--headless=new");
        }
        options.addArguments("--window-size=1440,1000", "--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        loginPage = new LoginPage(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test(description = "Valid credentials open the secure area")
    public void validLoginShouldOpenSecureArea() {
        loginPage.open().loginAs("tomsmith", "SuperSecretPassword!");

        Assert.assertTrue(loginPage.waitForUrl("/secure"), "Expected to land on the secure page");
        Assert.assertTrue(loginPage.flashMessage().contains("You logged into a secure area!"));
    }

    @Test(description = "An unknown username is rejected")
    public void invalidUsernameShouldShowError() {
        loginPage.open().loginAs("unknown-user", "SuperSecretPassword!");

        Assert.assertTrue(loginPage.flashMessage().contains("Your username is invalid!"));
        Assert.assertFalse(loginPage.currentUrl().contains("/secure"));
    }

    @Test(description = "A wrong password is rejected")
    public void invalidPasswordShouldShowError() {
        loginPage.open().loginAs("tomsmith", "not-the-password");

        Assert.assertTrue(loginPage.flashMessage().contains("Your password is invalid!"));
        Assert.assertFalse(loginPage.currentUrl().contains("/secure"));
    }

    @Test(description = "A blank username is rejected")
    public void blankUsernameShouldShowError() {
        loginPage.open().loginAs("", "SuperSecretPassword!");

        Assert.assertTrue(loginPage.flashMessage().contains("Your username is invalid!"));
        Assert.assertFalse(loginPage.currentUrl().contains("/secure"));
    }

    @Test(description = "A blank password is rejected")
    public void blankPasswordShouldShowError() {
        loginPage.open().loginAs("tomsmith", "");

        Assert.assertTrue(loginPage.flashMessage().contains("Your password is invalid!"));
        Assert.assertFalse(loginPage.currentUrl().contains("/secure"));
    }

    @Test(description = "Logging out ends the authenticated session")
    public void logoutShouldReturnToLoginPage() {
        loginPage.open().loginAs("tomsmith", "SuperSecretPassword!");
        Assert.assertTrue(loginPage.waitForUrl("/secure"));

        loginPage.logout();

        Assert.assertTrue(loginPage.waitForUrl("/login"));
        Assert.assertTrue(loginPage.flashMessage().contains("You logged out of the secure area!"));
    }
}
