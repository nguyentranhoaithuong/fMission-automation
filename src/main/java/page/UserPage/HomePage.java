package page.UserPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    WebDriver driver;
    By fMeetingModuleLocator = By.xpath("//span[text()='Quản lý họp']");
    By fTodolistModuleLocator = By.xpath("//span[text()='Todolist']");
    By fCheckInModuleLocator = By.xpath("//span[text()='Chấm công']");
    By fPlanModuleLocator = By.xpath("//span[text()='Kế hoạch']");
    By fWorkflowModuleLocator = By.xpath("//span[text()='Quy trình']");
    By fGhiNhanModuleLocator = By.xpath("//span[text()='Ghi nhận']");
    By fDoiQuaModuleLocator = By.xpath("//span[text()='Đổi quà']");


    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickFMeetingModule() {
        driver.findElement(fMeetingModuleLocator).click();
    }

    public void clickFTodolistModule() {
        driver.findElement(fTodolistModuleLocator).click();
    }

    public void clickFCheckInModule() {
        driver.findElement(fCheckInModuleLocator).click();
    }

    public void clickFWorkflowModule() {
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
}
