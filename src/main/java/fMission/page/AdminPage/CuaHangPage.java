package fMission.page.AdminPage;

import fMission.Model.CuaHang;
import fMission.Model.GhiNhan;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CuaHangPage {
    WebDriver driver;
    By themMoiButtonLocator = By.xpath("//span[text()='Thêm mới']");
    By tenSanPhamTextboxLocator = By.xpath("//label[text()='Tên sản phẩm *']/following-sibling::div/input");
    By danhMucDropDownLocator = By.xpath("//span[text()='Chọn']");
    By soSaoTextboxLocator = By.xpath("//label[text()='Số sao *']/following-sibling::div/input");
    By moTaSanPhamTextboxLocator = By.xpath("//label[text()='Mô tả']/following-sibling::div/textarea");
    By capNhatButtonLocator = By.xpath("//span[text()='Cập nhật']");
    By tenSanPhamVuaTaoLocator = By.xpath("//label[text()='Sản phẩm']/following-sibling::div/a");
    By danhMucVuaTaoLocator = By.xpath("//label[text()='Danh mục']/following-sibling::div");
    By soSaoVuaTaoLocator = By.xpath("//label[text()='Số sao']/following-sibling::div/span");
    By moTaSanPhamVuaTaoLocator = By.xpath("//label[text()='Mô tả']/following-sibling::div/div");
    By toastMessageLocator = By.xpath("//div[@class='notification is-success']/p");
    By errorMessageLocator = By.xpath("//div[@class='notification is-danger']/p");
    By closeErrorMessageLocator = By.xpath("//div[@class='notification is-danger']/a");
    By closeToastMessageLoctor = By.xpath("//div[@class='notification is-success']/a[@class='delete']");
    By getPhanTramHoanThanhLocator = By.xpath("//div[@class='mission-header']/div/span");

    public CuaHangPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickThemMoiButton() {
        driver.findElement(themMoiButtonLocator).click();
    }

    public void enterTenSanPham(String name) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(tenSanPhamTextboxLocator));
        driver.findElement(tenSanPhamTextboxLocator).sendKeys(name);
    }

    public void selectDanhMuc(String danhmuc) {
        driver.findElement(danhMucDropDownLocator).click();
        String xpath = String.format("//a[@class='dropdown-item']/span[text()='%s']",danhmuc);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        driver.findElement(By.xpath(xpath)).click();
    }

    public void enterSoSao(int sao) {
        String sosao = String.valueOf(sao);
        driver.findElement(soSaoTextboxLocator).clear();
        driver.findElement(soSaoTextboxLocator).sendKeys(sosao);
    }
    public void enterMoTaSanPham(String mota) {
        driver.findElement(moTaSanPhamTextboxLocator).sendKeys(mota);
    }

    public void clickCapNhatButton() {
        driver.findElement(capNhatButtonLocator).click();
    }

    public void themMoiSanPham(String name, String danhmuc,int sao, String mota) {
        enterTenSanPham(name);
        selectDanhMuc(danhmuc);
        enterSoSao(sao);
        enterMoTaSanPham(mota);
        clickCapNhatButton();
    }

    public String getTenSanPhamVuaTao() {
        return driver.findElement(tenSanPhamVuaTaoLocator).getText();
    }

    public String getDanhMucVuaTao() {
        return driver.findElement(danhMucVuaTaoLocator).getText();
    }

    public int getSoSaoVuaTao() {
        return Integer.parseInt(driver.findElement(soSaoVuaTaoLocator).getText());
    }

    public String getMoTaSanPhamVuaTao() {
        return driver.findElement(moTaSanPhamVuaTaoLocator).getText();
    }

    public CuaHang getSanPhamVuaTao() {
        CuaHang ch = new CuaHang();
        ch.setTenSanPham(getTenSanPhamVuaTao());
        ch.setDanhMuc(getDanhMucVuaTao());
        ch.setSoSao(getSoSaoVuaTao());
        ch.setMoTa(getMoTaSanPhamVuaTao());
        return ch;
    }

    public boolean isCheckMark(int i) {
        String xpath = String.format("//div[@data-pos='%d']//div[contains(@class, 'timeline-icon') and contains(@class, 'is-done')]", i);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
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
}
