package page.UserPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class fMeeting {
    WebDriver driver;
    By taoCuocHopButtonLocator = By.xpath("//span[text()='Tạo cuộc họp']");
    By giftButtonLocator = By.xpath("//img[@alt='Icon Gift']");
    By themPhienHopButtonLocator = By.xpath("//span[normalize-space(text())='Thêm phiên họp']");
    By chonPhienHopDropdownlist = By.xpath("//li[@class='column']//span[@class='icon-text']");
    By capNhatButtonLocator = By.xpath("//span[text()='Cập nhật']");
    By toastMessageLocator = By.xpath("//p[text()='Chúc mừng bạn đã hoàn thành nhiệm vụ!']");
    By percentProcessLocator = By.xpath("//div[@class='mission-header']/div/span");
    By batDauHopButtonLocator = By.xpath("//button[normalize-space(text())='Bắt đầu họp']");
    By navigateButtonLocator = By.xpath("//i[text()='navigate_before']");
    By arrowFowardButtonLocator = By.xpath("//i[text()='arrow_forward']");
    By themCongViecButtonLocator = By.xpath("//span[text()='Tạo công việc']");
    By taoVanDeButtonLocator = By.xpath("//span[text()='Tạo vấn đề']");
    By tieuDeVanDeTextboxLocator = By.xpath("//input[@class='input is_bg']");
    By noiDungVanDeTextBoxLocator = By.xpath("//div[@class='ql-editor ql-blank']");
    By xacNhanButtonLocator = By.xpath("//span[normalize-space(text())='Xác nhận']");
    By taoMoiGiaiPhapButtonLocator = By.xpath("//span[text()='Tạo mới']");
    By tieuDeGiaiPhapTextboxLocator = By.xpath("//input[@class='input is_bg']");
    By noiDungGiaiPhapTextBoxLocator = By.xpath("//div[@class='ql-editor ql-blank']");
    By capNhatGiaiPhapButtonLocator = By.xpath("//span[normalize-space(text())='Cập nhật']");
    By thongNhatCheckBoxLocator = By.xpath("//span[text()='Thống nhất']");
    By theoTodolistLocator = By.xpath("//a[text()='Theo Todolist']");
    By tieuDeCongViecTextboxLocator = By.xpath("//input[@class='input is_bg']");


    public fMeeting(WebDriver driver) {
        this.driver = driver;
    }

    public void clickTaoCuocHopButton() {
        driver.findElement(taoCuocHopButtonLocator).click();
    }

    public void clickGiftButton() {
        driver.findElement(giftButtonLocator).click();
    }

    public void clickThemPhienHopButton() {
        driver.findElement(themPhienHopButtonLocator).click();
    }

    public void clickPhienHop(int i) {
        driver.findElements(chonPhienHopDropdownlist).get(i).click();
    }

    public void selectPhienHop(String option) {
        String xpathOption = String.format("//a[@class='dropdown-item']/span[text()='%s']",option);
        driver.findElement(By.xpath(xpathOption)).click();
    }

    public void clickCapNhatButton() {
        driver.findElement(capNhatButtonLocator).click();
    }

    public boolean isToastMessageDisplay() {
        return driver.findElement(toastMessageLocator).isDisplayed();
    }

    public String percentProcess() {
        return driver.findElement(percentProcessLocator).getText();
    }

    public void clickBatDauHopButton() {
        driver.findElement(batDauHopButtonLocator).click();
    }

    public void clickNavigateButton() {
        boolean status = driver.findElement(navigateButtonLocator).isDisplayed();
        if (status == false) {
            driver.findElement(navigateButtonLocator).click();
        }
    }

    public void clickArrowForwardButton(int i) {
        driver.findElements(arrowFowardButtonLocator).get(i).click();
    }

    public void clickThemCongViecButton() {
        driver.findElement(themCongViecButtonLocator).click();
    }

    public void selectPhienCuocHop(String phien){
        String xpathOption = String.format("//div[@class='sections-meeting']//span[contains(normalize-space(), '%s')]",phien);
        driver.findElement(By.xpath(xpathOption)).click();
    }

    public void clickTaoVanDeButton() {
        driver.findElement(taoVanDeButtonLocator).click();
    }


    public void taoVanDe(String tieude, String noidung) {
        driver.findElement(tieuDeVanDeTextboxLocator).sendKeys(tieude);
        driver.findElement(noiDungVanDeTextBoxLocator).sendKeys(noidung);
        driver.findElement(xacNhanButtonLocator).sendKeys();
    }
    public void taoThongTinGiaiPhap(String tieude, String noidung) {
        driver.findElement(tieuDeGiaiPhapTextboxLocator).sendKeys(tieude);
        driver.findElement(noiDungGiaiPhapTextBoxLocator).sendKeys(noidung);
        driver.findElement(capNhatGiaiPhapButtonLocator).sendKeys();
    }
    public void clickThongNhatCheckBox() {
        driver.findElement(thongNhatCheckBoxLocator).click();
    }
    public void clickTabTheoTodolist() {
        driver.findElement(theoTodolistLocator).click();
    }

}

