package ui;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By username = By.id("username");
    private final By password = By.id("password");
    private final By loginButton = By.cssSelector("button[type='submit']");
    private final By flashMessage = By.id("flash");
    private final By logoutLink = By.cssSelector("a[href='/logout']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(8));
    }

    public LoginPage open() {
        driver.get("https://the-internet.herokuapp.com/login");
        wait.until(ExpectedConditions.visibilityOfElementLocated(username));
        return this;
    }

    public LoginPage loginAs(String user, String pass) {
        driver.findElement(username).clear();
        driver.findElement(username).sendKeys(user);
        driver.findElement(password).clear();
        driver.findElement(password).sendKeys(pass);
        driver.findElement(loginButton).click();
        return this;
    }

    public String flashMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(flashMessage)).getText();
    }

    public boolean waitForUrl(String urlPart) {
        return wait.until(ExpectedConditions.urlContains(urlPart));
    }

    public LoginPage logout() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutLink)).click();
        return this;
    }

    public String currentUrl() {
        return driver.getCurrentUrl();
    }
}
