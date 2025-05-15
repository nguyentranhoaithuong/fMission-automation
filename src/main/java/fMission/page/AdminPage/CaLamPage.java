package fMission.page.AdminPage;

import fMission.Model.CaLam;
import fMission.Model.DiaDiem;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CaLamPage {
    WebDriver driver;
    By themMoiButtonLocator = By.xpath("//span[text()='Thêm mới']");
    By tenCaLamTextBoxLocator = By.xpath("//label[text()='Tên ca làm việc *']/following-sibling::div/input");
    By gioBatDauDropDownLocator = By.xpath("//label[text()='Giờ bắt đầu *']/following-sibling::div/div");
    By gioKetThucDropDownLocator = By.xpath("//label[text()='Giờ kết thúc *']/following-sibling::div/div");
    By soCongTextboxLocator = By.xpath("//label[text()='Số công *']/following-sibling::div/input");
    By capNhatButtonLocator = By.xpath("//span[text()='Cập nhật']");
    By tenCaLamLocator = By.xpath("//label[text()='Tên ca làm việc']/following-sibling::div/div/span");
    By thoiGianLamViecLocator = By.xpath("//label[text()='Thời gian của ca']/following-sibling::div/div/span");
    By soCongCaLamLocator = By.xpath("//label[text()='Số công của ca']/following-sibling::div/span");
    By ngayTaoLocator = By.xpath("//label[text()='Ngày tạo']/following-sibling::div/span");
    By toastMessageLocator = By.xpath("//div[@class='notification is-success']/p");
    By errorMessageLocator = By.xpath("//div[@class='notification is-danger']/p");
    By closeErrorMessageLocator = By.xpath("//div[@class='notification is-danger']/a");
    By closeTaoCaLamPopupLocator = By.xpath("//a[@class='delete is-medium']");
    By getPhanTramHoanThanhLocator = By.xpath("//div[@class='mission-header']/div/span");
    By closeToastMessageLoctor = By.xpath("//div[@class='notification is-success']/a[@class='delete']");


    public CaLamPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickThemMoiButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(themMoiButtonLocator));
        driver.findElement(themMoiButtonLocator).click();
    }

    public void enterTenCaLam( String name ) {
        driver.findElement(tenCaLamTextBoxLocator).sendKeys(name);
    }

    public void selectGioBatDau(String giobd) {
        String xpath = String.format("//label[text()='Giờ bắt đầu *']/following-sibling::div/div/select/option[@value='%s']",giobd);
        driver.findElement(gioBatDauDropDownLocator).click();
        driver.findElement(By.xpath(xpath)).click();
    }

    public void selectGioKetThuc(String giokt) {
        String xpath = String.format("//label[text()='Giờ kết thúc *']/following-sibling::div/div/select/option[@value='%s']",giokt);
        driver.findElement(gioKetThucDropDownLocator).click();
        driver.findElement(By.xpath(xpath)).click();
    }

    public void enterSoCong(float socong) {
        String cong = String.valueOf(socong);
        driver.findElement(soCongTextboxLocator).clear();
        driver.findElement(soCongTextboxLocator).sendKeys(cong);
    }

    public void clickCapNhatButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(capNhatButtonLocator));
        driver.findElement(capNhatButtonLocator).click();
    }

    public void clickOKAlert() {
        // Chờ alert xuất hiện
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        // Nhấn OK (accept) trên alert
        alert.accept();
    }

    public void themCaLam(String name, String giobd, String giokt, float socong) {
        enterTenCaLam(name);
        selectGioBatDau(giobd);
        selectGioKetThuc(giokt);
        enterSoCong(socong);
        clickCapNhatButton();
        clickOKAlert();
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

    public String getTenCaLam(int i) {
        return driver.findElements(tenCaLamLocator).get(i-1).getText();
    }

    public String getThoiGianLamViec(int i) {
        return driver.findElements(thoiGianLamViecLocator).get(i-1).getText();
    }

    public float getSoCongCaLam(int i) {
        return Float.parseFloat(driver.findElements(soCongCaLamLocator).get(i-1).getText());
    }

    public String getNgayTao(int i) {
        return driver.findElements(ngayTaoLocator).get(i-1).getText();
    }

    public CaLam getCaLamByIndex(int i) {
        CaLam calam = new CaLam();
        calam.setTenCaLam(getTenCaLam(i));
        calam.setThoiGianLamViec(getThoiGianLamViec(i));
        calam.setSoCong(getSoCongCaLam(i));
        calam.setNgayTao(getNgayTao(i));
        return calam;
    }

    public boolean isErrorMessageChecked() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageLocator));
        return driver.findElement(errorMessageLocator).isDisplayed();
    }

    public String getErrorMessage() {
        return driver.findElement(errorMessageLocator).getText();
    }

    public void closeErrorMessage() {
        driver.findElement(closeErrorMessageLocator).click();
    }

    public void closeTaoCaLamPopup() {
        driver.findElement(closeTaoCaLamPopupLocator).click();
    }

    public int soLuongRecordCaLam() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(themMoiButtonLocator));
        List<WebElement> items = driver.findElements(tenCaLamLocator);
        return items.size();
    }
}
