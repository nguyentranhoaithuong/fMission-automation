package fMission.page.UserPage;

import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    WebDriver driver;
    By emailTexboxLocator = By.xpath("//input[@type='email']");
    By passwordTextboxLocator = By.xpath("//input[@type='password']");
    By loginButtonLocator = By.xpath("//span[text()='Đăng nhập']");
    By accessFastdoCompanyLocator = By.xpath("//a[text()='Công ty TNHH Quản trị số Fastdo']");
    By acceptPolicyButton = By.xpath("//*[@id='cookieConsent']/button");
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterUserName(String email) {
        driver.findElement(emailTexboxLocator).sendKeys(email);
    }

    public void enterPassword(String pass) {
        driver.findElement(passwordTextboxLocator).sendKeys(pass);
    }

    public void clickLoginButton() {
        driver.findElement(loginButtonLocator).click();
    }

    public void clickAcceptPolicy() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(acceptPolicyButton));
        driver.findElement(acceptPolicyButton).click();
    }

    public void Login(String username, String password) {
        Allure.step(String.format("Login with username: %s and password: %s", username, password));
        enterUserName(username);
        enterPassword(password);
        clickLoginButton();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(accessFastdoCompanyLocator));
        driver.findElement(accessFastdoCompanyLocator).click();
    }
}
