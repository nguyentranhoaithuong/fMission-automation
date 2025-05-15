package fMission.page.UserPage;

import fMission.Model.CongViecTodolist;
import fMission.Model.ViecDaGiao;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class GiaoViecPage {
    WebDriver driver;
    By giaoViecButtonLocator = By.xpath("//button/span[normalize-space(text())='Giao việc']");
    By tieuDeGiaoViecTextboxLocator = By.xpath("//div[@class='control is-expanded']/input[@placeholder='Nhập tiêu đề công việc']");
    By addThanhVienIconLocator = By.xpath("//a/i[text()='add']");
    By searchThanhVienTextboxLocator = By.xpath("//input[@id='search_member']");
    By selectThanhVienIconLocator = By.xpath("//a/i[text()='add_circle_outline']");
    By hoanTatButtonLocator = By.xpath("//span[text()='Hoàn tất']");
    By giaoViecButtonFinalLocator = By.xpath("//button[@class='button is-link']/span[text()='Giao việc']");
    By getPhanTramHoanThanhLocator = By.xpath("//div[@class='mission-header']/div/span");
    By giftIconLocator = By.xpath("//img[@alt='Icon Gift']");

    public GiaoViecPage(WebDriver driver) {
        this.driver = driver;
    }

    //giao viec
    public void clickGiaoViecButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(giaoViecButtonLocator));
        driver.findElement(giaoViecButtonLocator).click();
    }

    public void enterTieuDeGiaoViec(String name) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(tieuDeGiaoViecTextboxLocator));
        driver.findElement(tieuDeGiaoViecTextboxLocator).clear();
        driver.findElement(tieuDeGiaoViecTextboxLocator).sendKeys(name);
    }

    public void clickAddThanhVien() {
        driver.findElement(addThanhVienIconLocator).click();
    }

    public void searchThanhVien(String name) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(searchThanhVienTextboxLocator));
        driver.findElement(searchThanhVienTextboxLocator).sendKeys(name);
    }

    public void selectThanhVien() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(selectThanhVienIconLocator));
        driver.findElement(selectThanhVienIconLocator).click();
    }

    public void clickHoanTatButton() {
        driver.findElement(hoanTatButtonLocator).click();
    }

    public void clickGiaoViecFinalButton() {
        driver.findElement(giaoViecButtonFinalLocator).click();
    }

    public void giaoViec(String name,String member) {
        enterTieuDeGiaoViec(name);
        clickAddThanhVien();
        searchThanhVien(member);
        selectThanhVien();
//        clickHoanTatButton();
        clickGiaoViecFinalButton();
    }

    public String getTenCongViecDaGiao(String thoigian) {
        String xpath = String.format("(//span[text()='Đang xử lý']/../../../following-sibling::tr//span[text()='%s']/../../../following-sibling::tr/td//span[@class='text_many_line'])[last()]",thoigian);
        return driver.findElement(By.xpath(xpath)).getText();
    }

    public String getTenNguoiDuocGiao(String thoigian) {
        String xpath = String.format("(//span[text()='Đang xử lý']/../../../following-sibling::tr//span[text()='%s']/../../../following-sibling::tr/td//p[@class='text_1_line user_name '])[last()]",thoigian);
        return driver.findElement(By.xpath(xpath)).getText();
    }

    public ViecDaGiao getViecDaGiaoVuaTao(String thoigian) {
        ViecDaGiao cv = new ViecDaGiao();
        cv.setTenCongViec(getTenCongViecDaGiao(thoigian));
        cv.setTenNguoiDuocGiao(getTenNguoiDuocGiao(thoigian));
        return cv;
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

    public void openBangNhiemVu() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(giftIconLocator));
            List<WebElement> elements = driver.findElements(giftIconLocator);
            if (!elements.isEmpty() && elements.get(0).isDisplayed()) {
                elements.get(0).click();
            }
            // Nếu phần tử không tồn tại hoặc không hiển thị thì bỏ qua
        } catch (Exception e) {
            System.out.println("Đã mở bảng nhiệm vụ!!! :" + e.getMessage());
        }
    }
}
