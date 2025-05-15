package fMission.page.AdminPage;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PhanDiaDiemPage {
    WebDriver driver;
    By firstLocationLocator = By.xpath("//div[@class='has-text-weight-semibold pl-0']");
    By capNhatButtonLocator = By.xpath("//span[text()='Cập nhật']");
    By toastMessageLocator = By.xpath("//div[@class='notification is-success']/p");
    By closeToastMessageLoctor = By.xpath("//div[@class='notification is-success']/a[@class='delete']");
    By tenDiaDiemPopupLocator = By.xpath("//div[@class='has-text-weight-semibold pl-0']/label");
    By getPhanTramHoanThanhLocator = By.xpath("//div[@class='mission-header']/div/span");

    public PhanDiaDiemPage(WebDriver driver) {
        this.driver = driver;
    }
    public void clickEmployee(String user) {
        String xpath = String.format("//span[text()='%s']",user);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        driver.findElement(By.xpath(xpath)).click();
    }

    public void clickFirstLocation() {
        driver.findElement(firstLocationLocator).click();
    }

    public void clickLastLocation() {
        driver.findElement(firstLocationLocator).click();
    }

    public void clickCapNhatButton() {
        driver.findElement(capNhatButtonLocator).click();
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

    public String getViTriChamCongStatusByName(String name) {
        String xpath = String.format("//tr[.//span[normalize-space(text())='%s']]/td[4]/div",name);
        return driver.findElement(By.xpath(xpath)).getText();
    }

    public String getTenDiaDiemDauTienPopup() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(tenDiaDiemPopupLocator));
        return driver.findElement(tenDiaDiemPopupLocator).getText();
    }
}
