package fMission.page.AdminPage;

import fMission.Model.TieuChiGhiNhan;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TieuChiGhiNhanPage {
    WebDriver driver;
    By themMoiTieuChiButtonLocator = By.xpath("//span[text()='Thêm mới']");
    By tieuChiGhiNhanTextBoxLocator = By.xpath("//label[text()='Tiêu chí ghi nhận*']/following-sibling::div/input");
    By soSaoTextboxLocator = By.xpath("//label[text()='Số sao']/following-sibling::input");
    By capNhatButtonLocator = By.xpath("//span[text()='Cập nhật']");
    By tieuChiGhiNhanLastLocator = By.xpath("(//tbody/tr/td/span[normalize-space()])[last()]");
    By soSaoGhiNhanLastLocator = By.xpath("(//label[text()='Số sao']/following-sibling::div/strong)[last()]");
    By toastMessageLocator = By.xpath("//div[@class='notification is-success']/p");
    By errorMessageLocator = By.xpath("//div[@class='notification is-danger']/p");
    By closeErrorMessageLocator = By.xpath("//div[@class='notification is-danger']/a");
    By closeToastMessageLoctor = By.xpath("//div[@class='notification is-success']/a[@class='delete']");
    By getPhanTramHoanThanhLocator = By.xpath("//div[@class='mission-header']/div/span");

    public TieuChiGhiNhanPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickThemMoiTieuChi() {
        driver.findElement(themMoiTieuChiButtonLocator).click();
    }

    public void enterTieuChiGhiNhan(String tieuchi) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(tieuChiGhiNhanTextBoxLocator));
        driver.findElement(tieuChiGhiNhanTextBoxLocator).sendKeys(tieuchi);
    }

    public void enterSoSao(int sao) {
        driver.findElement(soSaoTextboxLocator).clear();
        String sosao = String.valueOf(sao);
        driver.findElement(soSaoTextboxLocator).sendKeys(sosao);
    }

    public void clickCapNhatButton() {
        driver.findElement(capNhatButtonLocator).click();
    }

    public void themMoiTieuChiGhiNhan(String tieuchi, int sao) {
        enterTieuChiGhiNhan(tieuchi);
        enterSoSao(sao);
        clickCapNhatButton();
    }

    public String getTieuChiGhiNhanLast()  {
        return driver.findElement(tieuChiGhiNhanLastLocator).getText();
    }

    public int getSoSaoGhiNhanLast()  {
        return Integer.parseInt(driver.findElement(soSaoGhiNhanLastLocator).getText());
    }

    public TieuChiGhiNhan getTieuChiGhiNhan() {
        TieuChiGhiNhan tc = new TieuChiGhiNhan();
        tc.setTieuChi(getTieuChiGhiNhanLast());
        tc.setSoSao(getSoSaoGhiNhanLast());
        return tc;
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

    public void xoaTieuChiVuaTao(String title, int sao) {
//        String xpath = String.format("//tr/td/span[normalize-space(text())='%s']/../..//a[@title='Xóa']",title);
        String sosao = String.valueOf(sao);
        String xpath = String.format("//tr/td/span[normalize-space(text())='%s']/../..//label[text()='Số sao']/following-sibling::div/strong[text()='%s']/../../..//a[@title='Xóa']",title,sosao);
        driver.findElement(By.xpath(xpath)).click();

        // Chờ alert xuất hiện
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        // Nhấn OK (accept) trên alert
        alert.accept();
    }

}
