package fMission.page.UserPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

public class HomePage {
    WebDriver driver;
    By fMeetingModuleLocator = By.xpath("//span[text()='Quản lý họp']");
    By fTodolistModuleLocator = By.xpath("//span[text()='Todolist']");
    By fCheckInModuleLocator = By.xpath("//span[text()='Chấm công']");
    By fPlanModuleLocator = By.xpath("//span[text()='Kế hoạch']");
    By fWorkflowModuleLocator = By.xpath("//span[text()='Quy trình']");
    By fGhiNhanModuleLocator = By.xpath("//span[text()='Ghi nhận']");
    By fDoiQuaModuleLocator = By.xpath("//span[text()='Đổi quà']");
    By fConfigsModuleLocator = By.xpath("//span[normalize-space(text())='Cấu hình']");
    By helpOutLineIconLocator = By.xpath("//span[@class='icon']/span[normalize-space(text())='help_outline']");
    By phanThuongLocator = By.xpath("//div[@class='is-size-7 p-1 py-2']/span");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickFMeetingModule() {
        driver.findElement(fMeetingModuleLocator).click();
    }

    public void clickFTodolistModule() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(fTodolistModuleLocator));
        driver.findElement(fTodolistModuleLocator).click();
    }

    public void clickFCheckInModule() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(fCheckInModuleLocator));
        driver.findElement(fCheckInModuleLocator).click();
    }

    public void clickFWorkflowModule() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(fWorkflowModuleLocator));
        driver.findElement(fWorkflowModuleLocator).click();
    }

    public void clickFGhiNhanModule() {
        driver.findElement(fGhiNhanModuleLocator).click();
    }

    public void clickFDoiQuaModule() {
        driver.findElement(fDoiQuaModuleLocator).click();
    }
    public void clickFPlanModule() {
        driver.findElement(fPlanModuleLocator).click();
    }

    public void clickFConfigsModule() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(fConfigsModuleLocator));
        driver.findElement(fConfigsModuleLocator).click();
    }

    public void clickHelpOutLineIcon() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(helpOutLineIconLocator));
        driver.findElement(helpOutLineIconLocator).click();
    }

    public String getPhanThuongText() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(phanThuongLocator));

            List<WebElement> elements = driver.findElements(phanThuongLocator);

            if (elements.isEmpty()) {
                return "";
            } else if (elements.size() == 1) {
                return elements.get(0).getText().trim();
            } else {
                StringBuilder fullText = new StringBuilder();
                for (WebElement element : elements) {
                    String text = element.getText().trim();
                    if (!text.isEmpty()) {
                        fullText.append(text).append(" ");
                    }
                }
                return fullText.toString().trim();
            }

        } catch (Exception e) {
            return ""; // Nếu có lỗi xảy ra thì trả về chuỗi rỗng
        }
    }




}
