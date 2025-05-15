package fMission.page.UserPage;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DoiQuaPage {
    WebDriver driver;
    By themGioHangSPDauTienButtonLocator = By.xpath("//span[text()='Thêm vào giỏ']");
    By muaNgayButtonLocator = By.xpath("//span[text()='Mua ngay']");
    By tenSanPhamDauTienLocator = By.xpath("//div[@class='has-text-weight-semibold text_many_line']");
    By giaSanPhamDauTienLocator = By.xpath("//div[text()='Giá đổi']/following-sibling::span//span[@class='has-text-weight-semibold flex_none']");
    By popupCouponLocator = By.xpath("//div[@class='modal-card is_radius']/div/div[@class='image']");
    By closePopupCoupon = By.xpath("//span[text()='close']");
    By toastMessageLocator = By.xpath("//div[@class='notification is-success']/p");
    By errorMessageLocator = By.xpath("//div[@class='notification is-danger']/p");
    By closeErrorMessageLocator = By.xpath("//div[@class='notification is-danger']/a");
    By closeToastMessageLoctor = By.xpath("//div[@class='notification is-success']/a[@class='delete']");
    By backToHomePageLocator = By.xpath("//img[@class='image pr-2']");
    By getPhanTramHoanThanhLocator = By.xpath("//div[@class='mission-header']/div/span");

    public DoiQuaPage(WebDriver driver) {
        this.driver = driver;
    }

    public void themGioHangSanPhamDauTien() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(themGioHangSPDauTienButtonLocator));
        driver.findElement(themGioHangSPDauTienButtonLocator).click();
    }

    public void clickMuaNgayButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(muaNgayButtonLocator));
        driver.findElement(muaNgayButtonLocator).click();
    }

    public void clickOKAlert() {
        // Chờ alert xuất hiện
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        // Nhấn OK (accept) trên alert
        alert.accept();
    }

    public String getTenSanPhamDauTien() {
        return driver.findElement(tenSanPhamDauTienLocator).getText();
    }

    public int getGiaSanPhamDauTien() {
        return Integer.parseInt(driver.findElement(giaSanPhamDauTienLocator).getText());
    }

    public boolean isPopupCouponDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(popupCouponLocator));
        return driver.findElement(popupCouponLocator).isDisplayed();
    }

    public void closePopupCoupon() {
        driver.findElement(closePopupCoupon).click();
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

    public boolean isErrorMessageChecked() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageLocator));
        return driver.findElement(errorMessageLocator).isDisplayed();
    }

    public void closeErrorMessage() {
        driver.findElement(closeErrorMessageLocator).click();
    }

    public String getErrorMessage() {
        return driver.findElement(errorMessageLocator).getText();
    }

    public void backToHomePageLocator() {
        driver.findElement(backToHomePageLocator).click();
    }
}
