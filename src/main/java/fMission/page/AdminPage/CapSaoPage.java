package fMission.page.AdminPage;

import fMission.Model.LichSuCapSao;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CapSaoPage {
    WebDriver driver;
    By toastMessageLocator = By.xpath("//div[@class='notification is-success']/p");
    By errorMessageLocator = By.xpath("//div[@class='notification is-danger']/p");
    By closeErrorMessageLocator = By.xpath("//div[@class='notification is-danger']/a");
    By closeToastMessageLoctor = By.xpath("//div[@class='notification is-success']/a[@class='delete']");
    By capSaoButtonLocator = By.xpath("//a[@class='button is-link']/span[text()='Cấp sao']");
    By capSaoTextboxLocator = By.xpath("//div[text()='Cấp sao']/following-sibling::div/input");
    By chonViDropDownLocator = By.xpath("//div[@class='select']");
    By capNhatCapSaoButtonLocator = By.xpath("//span[text()='Cập nhật']");
    By xacNhanCapSaoLocator = By.xpath("//span[text()='Xác nhận']");
    By getTenNhanVienCapSao = By.xpath("//label[text()='Nhân viên']/following-sibling::div/div/a");
    By getThoiGianCapSao = By.xpath("(//tr/td)[1]");
    By getPhongBanNhanVien = By.xpath("//label[text()='Phòng ban']/following-sibling::div/div");
    By getCapBacNhanVien = By.xpath("//label[text()='Cấp bậc']/following-sibling::div/span");
    By getLoaiVi = By.xpath("//label[text()='Loại ví']/following-sibling::div");
    By getSaoCapLocator = By.xpath("//label[text()='Sao cấp']/following-sibling::div");
    By getNguoiThucHienLocator = By.xpath("//label[text()='Người thực hiện']/following-sibling::div//a");
    By getPhanTramHoanThanhLocator = By.xpath("//div[@class='mission-header']/div/span");


    public CapSaoPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickArrowForwardButton(int i) {
        String xpath = String.format("//div[@data-pos='%d']/div/div/a/span[@class='icon']/i[text()='arrow_forward']", i);
        By arrowForwardButtonLocator = By.xpath(xpath);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(arrowForwardButtonLocator));

        driver.findElement(arrowForwardButtonLocator).click();
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

    public void clickCapSaoButton() {
        driver.findElement(capSaoButtonLocator).click();
    }

    public void selectUserCapSaoByName(String name) {
        String xpath = String.format("//a[normalize-space(text())='%s']/../../../..//label[@class='checkbox']",name);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        driver.findElement(By.xpath(xpath)).click();
    }

    public String getPhongBanCuaNhanVienDuocChon(String name) {
        String xpath = String.format("//a[normalize-space(text())='%s']/../../../..//label[text()='Phòng ban']/following-sibling::div/div",name);
        return driver.findElement(By.xpath(xpath)).getText();
    }
    public String getCapBacCuaNhanVienDuocChon(String name) {
        String xpath = String.format("//a[normalize-space(text())='%s']/../../../..//label[text()='Cấp bậc']/following-sibling::div/span",name);
        return driver.findElement(By.xpath(xpath)).getText();
    }

    public void enterSoSao(int sao) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(capSaoTextboxLocator));
        driver.findElement(capSaoTextboxLocator).clear();
        String sosao = String.valueOf(sao);
        driver.findElement(capSaoTextboxLocator).sendKeys(sosao);
    }

    public void selectViCapSao(String vi) {
        driver.findElement(chonViDropDownLocator).click();
        String xpath = String.format("//div[@class='select']/select/option[text()='%s']",vi);
        driver.findElement(By.xpath(xpath)).click();
    }

    public void clickCapNhatCapSaoButton() {
        driver.findElement(capNhatCapSaoButtonLocator).click();
    }

    public void clickXacNhanCapSao() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(xacNhanCapSaoLocator));
        driver.findElement(xacNhanCapSaoLocator).click();
    }

    public String getTenNhanVienCapSao() {
        return driver.findElement(getTenNhanVienCapSao).getText();
    }

    public String getThoiGianCapSao() {
        return driver.findElement(getThoiGianCapSao).getText();
    }

    public String getPhongBanNhanVien() {
        return driver.findElement(getPhongBanNhanVien).getText();
    }

    public String getCapBacNhanVien() {
        return driver.findElement(getCapBacNhanVien).getText();
    }

    public String getLoaiVi() {
        return driver.findElement(getLoaiVi).getText();
    }

    public int getSaoCap() {
        return Integer.parseInt(driver.findElement(getSaoCapLocator).getText());
    }

    public String getNguoiThucHien() {
        return driver.findElement(getNguoiThucHienLocator).getText();
    }

    public String thoiGianHienTai() {
        // Lấy thời gian hiện tại
        LocalDateTime now = LocalDateTime.now();
        // Định dạng theo yêu cầu: "HH:mm - dd/MM/yyyy"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm - dd/MM/yyyy");
        // In ra thời gian đã định dạng
        return now.format(formatter);
    }

    public LichSuCapSao getLichSuCapSao() {
        LichSuCapSao ls = new LichSuCapSao();
        ls.setThoiGian(getThoiGianCapSao());
        ls.setTen(getTenNhanVienCapSao());
        ls.setPhongBan(getPhongBanNhanVien());
        ls.setCapBac(getCapBacNhanVien());
        ls.setLoaiVi(getLoaiVi());
        ls.setSosao(getSaoCap());
        ls.setNguoiThucHien(getNguoiThucHien());
        return ls;
    }




}
