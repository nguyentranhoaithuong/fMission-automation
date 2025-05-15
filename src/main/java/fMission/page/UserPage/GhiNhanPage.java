package fMission.page.UserPage;

import fMission.Model.GhiNhan;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GhiNhanPage {
    WebDriver driver;
    By tenNhanVienTextboxLocator = By.xpath("//input[@placeholder='Nhập tên nhân viên...']");
    By chonTieuChiGhiNhanDropDownLocator = By.xpath("//span[text()='Chọn tiêu chí ghi nhận']");
    By tieuChiGhiNhanDauTieuLocator = By.xpath("//a[@class='dropdown-item']/span");
    By noiDungGhiNhanTextboxLocator = By.xpath("//textarea[@placeholder='Nhập nội dung...']");
    By guiButtonLocator = By.xpath("//i[text()='send']");
    By toastMessageLocator = By.xpath("//div[@class='notification is-success']/p");
    By errorMessageLocator = By.xpath("//div[@class='notification is-danger']/p");
    By closeErrorMessageLocator = By.xpath("//div[@class='notification is-danger']/a");
    By closeToastMessageLoctor = By.xpath("//div[@class='notification is-success']/a[@class='delete']");
    By tenNguoiThucHienLocator = By.xpath("//div[@class='user_item  mb-2']//p[@class='text_1_line user_name '][normalize-space()]");
    By tenNguoiNhanLocator = By.xpath("//div[@class='user_item  mb-3 pl-6']//p[@class='text_1_line user_name ']");
    By noiDungLocator = By.xpath("//div[@class='text_2_line is-word-break ql-editor']");
    By thoiGianThucHienLocator = By.xpath("(//div[@class='field is-grouped is-vcentered']/div[@class='control has-text-grey'])[last()]");
    By getPhanTramHoanThanhLocator = By.xpath("//div[@class='mission-header']/div/span");

    public GhiNhanPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickArrowForwardButton(int i) {
        String xpath = String.format("//div[@data-pos='%d']/div/div/a/span[@class='icon']/i[text()='arrow_forward']", i);
        By arrowForwardButtonLocator = By.xpath(xpath);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(arrowForwardButtonLocator));

        driver.findElement(arrowForwardButtonLocator).click();
    }

    public void enterTenNhanVien(String name) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(tenNhanVienTextboxLocator));
        driver.findElement(tenNhanVienTextboxLocator).sendKeys(name);
        String xpath = String.format("//div[@class='user_item']/span[text()='%s']",name);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        driver.findElement(By.xpath(xpath)).click();
    }

    public void selectTieuChiGhiNhanDauTien() {
        driver.findElement(chonTieuChiGhiNhanDropDownLocator).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(tieuChiGhiNhanDauTieuLocator));
        driver.findElement(tieuChiGhiNhanDauTieuLocator).click();
    }

    public void enterNoiDungGhiNhan(String nd) {
        driver.findElement(noiDungGhiNhanTextboxLocator).sendKeys(nd);
    }

    public void clickGuiButton() {
        driver.findElement(guiButtonLocator).click();
    }

    public void ghiNhanDongNghiep(String name, String nd) {
        enterTenNhanVien(name);
        selectTieuChiGhiNhanDauTien();
        enterNoiDungGhiNhan(nd);
        clickGuiButton();
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

    public String getTenNguoiThucHien() {
        return driver.findElement(tenNguoiThucHienLocator).getText();
    }

    public String getTenNguoiNhan() {
        return driver.findElement(tenNguoiNhanLocator).getText();
    }

    public String getNoiDung() {
        return driver.findElement(noiDungLocator).getText();
    }

    public String getThoiGianThucHien() {
        return driver.findElement(thoiGianThucHienLocator).getText();
    }

    public GhiNhan getGhiNhanVuaTao() {
        GhiNhan gh = new GhiNhan();
        gh.setTenNguoiThucHien(getTenNguoiThucHien());
        gh.setTenNguoiNhan(getTenNguoiNhan());
        gh.setNoiDung(getNoiDung());
        gh.setThoiGian(getThoiGianThucHien());
        return gh;
    }


}
