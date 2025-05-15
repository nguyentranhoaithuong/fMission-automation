package fMission.page.AdminPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CauHinhPage {
    WebDriver driver;
    By arrowForwardButtonLocator = By.xpath("//i[text()='arrow_forward']");
    By themMoiDiaDiemButtonLocator = By.xpath("//span[text()='Thêm mới']");


    public CauHinhPage(WebDriver driver) {
        this.driver = driver;
    }
    public void clickArrowForwardButton(int i) {
        driver.findElements(arrowForwardButtonLocator).get(i).click();
    }

    public void clickThemMoiDiaDiemButton() {
        driver.findElement(themMoiDiaDiemButtonLocator).click();
    }

    public void clickMenu(String menu) {
        String xpath = String.format("//span[text()='%s']",menu);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        driver.findElement(By.xpath(xpath)).click();
    }

    public void clickSubMenu(String submemu) {
        String xpath = String.format("//a[@class='button is-white']/span[text()='%s']",submemu);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));

        driver.findElement(By.xpath(xpath)).click();
    }
}
