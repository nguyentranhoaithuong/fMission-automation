package fMission.page.UserPage;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DoiNhomPage {
    WebDriver driver;
    By ghimDoiNhomIconLocator = By.xpath("//i[text()='dashboard_customize']");
    By nhomDauTienLocator = By.xpath("//span[@class='is-family-code has-text-weight-semibold has-text-dark ql-editor']");
    By closePopupButtonLocator = By.xpath("//a[@class='delete']");
    By openDoiNhomIconLocator = By.xpath("//i[text()='chevron_right']");
    By firstUserLocator = By.xpath("(//p[@class='text_1_line user_name '])[2]");
    By todolistTabLocator = By.xpath("//a[text()='Todolist']");
    By popupCouponLocator = By.xpath("//div[@class='modal-card is_radius']/div/div[@class='image']");
    By closePopupCoupon = By.xpath("//span[text()='close']");
    By closeToastMessageLoctor = By.xpath("//div[@class='notification is-success']/a[@class='delete']");
    By toastMessageLocator = By.xpath("//div[@class='notification is-success']/p");
    By getPhanTramHoanThanhLocator = By.xpath("//div[@class='mission-header']/div/span");
    By backToHomePageLocator = By.xpath("//img[@class='image pr-2']");

    public DoiNhomPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickGhimDoiNhomIcon() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(ghimDoiNhomIconLocator));
        driver.findElement(ghimDoiNhomIconLocator).click();
    }

    public void selectNhomDauTien() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(nhomDauTienLocator));
        driver.findElement(nhomDauTienLocator).click();
    }

    public void closePopup() {
        driver.findElement(closePopupButtonLocator).click();
    }

    public void openDoiNhom() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(openDoiNhomIconLocator));
        driver.findElement(openDoiNhomIconLocator).click();
    }

    public void clickFirstUser(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstUserLocator));
        driver.findElement(firstUserLocator).click();
    }

    public void clickTodolistTab() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(todolistTabLocator));
        driver.findElement(todolistTabLocator).click();
    }

    public boolean isPopupCouponDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(popupCouponLocator));
        return driver.findElement(popupCouponLocator).isDisplayed();
    }

    public void closePopupCoupon() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(closePopupCoupon));
        driver.findElement(closePopupCoupon).click();
    }

    public boolean isToastMessageChecked() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(toastMessageLocator));
        return driver.findElement(toastMessageLocator).isDisplayed();
    }

    public String toastMessageText() {
        return driver.findElement(toastMessageLocator).getText();
    }

    public void closeToastMessage(){
        driver.findElement(closeToastMessageLoctor).click();
    }

    public boolean isCheckMark(int i) {
        String xpath = String.format("//div[@data-pos='%d']//div[contains(@class, 'timeline-icon') and contains(@class, 'is-done')]", i);
        try {
            WebElement element = driver.findElement(By.xpath(xpath));
            return element.isDisplayed(); // true nếu có và hiển thị
        } catch (NoSuchElementException e) {
            return false; // không tồn tại hoặc không hiển thị → coi như chưa đánh dấu
        }
    }

    public String getPhanTramHoanThanh() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(getPhanTramHoanThanhLocator));
        return driver.findElement(getPhanTramHoanThanhLocator).getText().replaceAll("\\s+", " ").trim();
    }

    public void backToHomePageLocator() {
        driver.findElement(backToHomePageLocator).click();
    }

}







