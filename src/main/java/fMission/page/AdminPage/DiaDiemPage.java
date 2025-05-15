package fMission.page.AdminPage;

import fMission.Model.DiaDiem;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DiaDiemPage {
    WebDriver driver;
    By arrowForwardButtonLocator = By.xpath("//i[text()='arrow_forward']");
    By themMoiDiaDiemButtonLocator = By.xpath("//span[text()='Thêm mới']");
    By tenDiaDiemTextboxLocator = By.xpath("//input[@placeholder='Nhập tên địa điểm'][@class='input']");
    By khoangCachTextboxLocator = By.id("mapDistance");
    By viTriTextboxLocator = By.xpath("//input[@class='input is-rounded']");
    By capNhatButtonLocator = By.xpath("//span[text()='Cập nhật']");
    By iconSearchButtonLocator = By.xpath("//div[@id='mapSearch']/span/i[normalize-space(text())='search']");
    By toastMessageLocator = By.xpath("//div[@class='notification is-success']/p");
    By closeToastMessageLoctor = By.xpath("//div[@class='notification is-success']/a[@class='delete']");
    By getTenDiaDiemLocator = By.xpath("//label[text()='Tên địa điểm']/..//following-sibling::div/span");
    By getKhoangCachLocator = By.xpath("//label[text()='Khoảng cách hợp lệ']/..//following-sibling::div/span");
    By errorMessageLocator = By.xpath("//div[@class='notification is-danger']/p");
    By iconXLocator = By.xpath("//a[@class='delete is-medium']");
    By closeErrorMessageLocator = By.xpath("//div[@class='notification is-danger']/a");
    By getPhanTramHoanThanhLocator = By.xpath("//div[@class='mission-header']/div/span");

    public DiaDiemPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickArrowForwardButton(int i) {
        String xpath = String.format("//div[@data-pos='%d']/div/div/a/span[@class='icon']/i[text()='arrow_forward']", i);
        By arrowForwardButtonLocator = By.xpath(xpath);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(arrowForwardButtonLocator));

        driver.findElement(arrowForwardButtonLocator).click();
    }

    public boolean isArrowForwardButtonChecked(int i) {
        String xpath = String.format("//div[@data-pos='%d']/div/div/a/span[@class='icon']/i[text()='arrow_forward']", i);
        By arrowForwardButtonLocator = By.xpath(xpath);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(arrowForwardButtonLocator));

        return driver.findElement(arrowForwardButtonLocator).isDisplayed();
    }

    public void clickThemMoiDiaDiemButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(themMoiDiaDiemButtonLocator));
        driver.findElement(themMoiDiaDiemButtonLocator).click();
    }

    public void nhapTenDiaDiem(String ten) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(tenDiaDiemTextboxLocator));
        driver.findElement(tenDiaDiemTextboxLocator).sendKeys(ten);
    }

    public void nhapKhoangCach(int kc) {
        driver.findElement(khoangCachTextboxLocator).clear();
        driver.findElement(khoangCachTextboxLocator).sendKeys(String.valueOf(kc));
    }

    public void nhapViTri(String vt) {
        driver.findElement(viTriTextboxLocator).sendKeys(vt);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(iconSearchButtonLocator));
        driver.findElement(iconSearchButtonLocator).click();
    }

    public void clickCapNhatButton() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(capNhatButtonLocator));
        Thread.sleep(10000);
        driver.findElement(capNhatButtonLocator).click();
    }

    public void themDiaDiem(String ten, int kc, String vt) throws InterruptedException {
        nhapTenDiaDiem(ten);
        nhapKhoangCach(kc);
        nhapViTri(vt);
        clickCapNhatButton();
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


    public String getTenDiaDiem(int i) {
        return driver.findElements(getTenDiaDiemLocator).get(i-1).getText();
    }
    public int getKhoangCach(int i) {
        return Integer.parseInt(driver.findElements(getKhoangCachLocator).get(i-1).getText().replace("m",""));
    }

    public DiaDiem getDiaDiemByIndex(int i) {
        DiaDiem dd = new DiaDiem();
        dd.setTenDiaDiem(getTenDiaDiem(i));
        dd.setKhoangCach(getKhoangCach(i));
        return dd;
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

    public boolean isCapNhatButtonChecked() {
        return driver.findElement(capNhatButtonLocator).isDisplayed();
    }

    public void clickXIcon() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(iconXLocator));
        driver.findElement(iconXLocator).click();
    }
}
