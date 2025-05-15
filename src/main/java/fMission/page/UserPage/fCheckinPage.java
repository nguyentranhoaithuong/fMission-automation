package fMission.page.UserPage;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class fCheckinPage {
    WebDriver driver;
//    By arrowForwardButtonLocator = By.xpath("//i[text()='arrow_forward']");
    By phanCaButtonLocator = By.xpath("//button/span[text()='Phân ca']");
    By searchNhanVienTextboxLocator = By.xpath("//input[@placeholder='Tìm tên nhân viên...']");
    By checkBoxNhanVienLocator = By.xpath("//label[@class='checkbox']/input");
    By tiepTucButtonLocator = By.xpath("//a[normalize-space(text())='Tiếp tục']");
    By checkBoxCaLamLocator = By.xpath("//p[normalize-space(text())='Ca làm áp dụng']/following-sibling::div/a/input");
    By apDungButtonLocator = By.xpath("//span[text()='Áp dụng']");
    By toastMessageLocator = By.xpath("//div[@class='notification is-success']/p");
    By phanCaMenuLocator = By.xpath("(//li/a/span[text()='Phân ca'])[1]");
    By phanCaSubMenuLocator = By.xpath("(//li/a/span[text()='Phân ca'])[2]");
    By getPhanTramHoanThanhLocator = By.xpath("//div[@class='mission-header']/div/span");

    public fCheckinPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickPhanCaMenu() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(phanCaMenuLocator));
        driver.findElement(phanCaMenuLocator).click();
    }

    public void clickPhanCaSubMenu() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(phanCaSubMenuLocator));
        driver.findElement(phanCaSubMenuLocator).click();
    }

    public void clickArrowForwardButton(int i) {
        String xpath = String.format("//div[@data-pos='%d']/div/div/a/span[@class='icon']/i[text()='arrow_forward']", i);
        By arrowForwardButtonLocator = By.xpath(xpath);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(arrowForwardButtonLocator));
        driver.findElement(arrowForwardButtonLocator).click();
    }

    public void clickPhanCaButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(phanCaButtonLocator));
        driver.findElement(phanCaButtonLocator).click();
    }

    public void searchNhanVien(String name) {
        driver.findElement(searchNhanVienTextboxLocator).sendKeys(name + Keys.ENTER);
    }

    public void clickCheckBoxNhanVien(int i) {
        driver.findElements(checkBoxNhanVienLocator).get(i-1).click();
    }

    public void clickTiepTucButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(tiepTucButtonLocator));
        driver.findElement(tiepTucButtonLocator).click();
    }

    public void clickFirstCaLam() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(checkBoxCaLamLocator));
        driver.findElement(checkBoxCaLamLocator).click();
    }

    public void clickApDungButton() {
        driver.findElement(apDungButtonLocator).click();
    }

    public boolean isToastMessageChecked() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(toastMessageLocator));
        return driver.findElement(toastMessageLocator).isDisplayed();
    }

    public String toastMessageText() {
        return driver.findElement(toastMessageLocator).getText();
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



}
