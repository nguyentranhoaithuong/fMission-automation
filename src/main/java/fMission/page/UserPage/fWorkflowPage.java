package fMission.page.UserPage;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class fWorkflowPage {
    WebDriver driver;
    By toastMessageLocator = By.xpath("//div[@class='notification is-success']/p");
    By errorMessageLocator = By.xpath("//div[@class='notification is-danger']/p");
    By closeErrorMessageLocator = By.xpath("//div[@class='notification is-danger']/a");
    By closeToastMessageLoctor = By.xpath("//div[@class='notification is-success']/a[@class='delete']");
    By getPhanTramHoanThanhLocator = By.xpath("//div[@class='mission-header']/div/span");
    By giftIconLocator = By.xpath("//img[@alt='Icon Gift']");
    By navigateBeforeButtonLocator = By.xpath("//i[text()='navigate_before']");
    By taoQuyTrinhButtonLocator = By.xpath("//span[text()='Tạo quy trình']");
    By tenQuyTrinhTextboxLocator = By.xpath("//input[@placeholder='Nhập tên quy trình...']");
    By hoanThanhTaoQuyTrinhButtonLocator = By.xpath("//button/span[text()='Hoàn thành']");
    By taoMoiTheButtonLocator = By.xpath("//span[text()='Tạo mới thẻ']");
    By taoBuocCongViecButtonLocator = By.xpath("//span[normalize-space(text())='Tạo bước công việc']");
    By tenCongViecTextboxLocator = By.xpath("//input[@placeholder='Nhập Tên công việc...']");
    By chonThanhVienDropDownLocator = By.xpath("//span[normalize-space(text())='Chọn thành viên']");
    By thoiHanTextboxLocator = By.xpath("//input[@placeholder='Nhập thời hạn']");
    By hoanThanhTaoTheCongViecButtonLocator = By.xpath("//span[text()='Hoàn thành']");
    By thuGonButtonLocator = By.xpath("//span[normalize-space(text())='Thu gọn']");
    By theCongViecLocator = By.xpath("//div[@class='p-4 card_work']");
    By quyTrinhLocator = By.xpath("//div[@id='drawflow']");
    By xuatBanButtonLocator = By.xpath("//span[normalize-space(text())='Xuất bản']");
    By taoNhiemVuButtonLocator = By.xpath("//span[text()='Tạo nhiệm vụ']");
    By tenNhiemVuTextboxLocator = By.xpath("//input[@placeholder='Nhập tên nhiệm vụ...']");
    By hoanThanhTaoNhiemVuButtonLocator = By.xpath("//span[text()='Hoàn thành']");
    By popupXuLyCongViecLocator = By.xpath("//a/span[text()='Xử lý công việc']");
    By hoanThanhNhiemVuRadioButtonLocator = By.xpath("//span[normalize-space(text())='Hoàn thành nhiệm vụ']");
    By hoanThanhButtonLocator = By.xpath("//span[normalize-space(text())='Hoàn thành']");
    By xacNhanButtonLocator = By.xpath("//span[text()='Xác nhận']");
    By closeXuLyCongViecPopupLocator = By.xpath("//a[@class='delete']");
    By popupCouponLocator = By.xpath("//div[@class='modal-card is_radius']/div/div[@class='image']");
    By closePopupCoupon = By.xpath("//span[text()='close']");
    By closePopupTaoQuyTrinhLocator = By.xpath("//a[@class='delete']");
    By huyTaoTheCongViecButtonLocator = By.xpath("//span[text()='Huỷ']");
    By backIconLocator = By.xpath("//span[normalize-space(text())='arrow_back']");
    By thuGonPanelLocator = By.xpath("//span[normalize-space(text())='Thu gọn']");
    By cardWorkLocator = By.xpath("//div[@class='p-4 card_work ']");
    By mainPathLocator = By.xpath("//*[name()='svg']/*[name()='path' and @class='main-path']");
    By dongPopupTaoCongViecLocator = By.xpath("//span[text()='Đóng']");
    By dongPopupXuLyCongViecLocator = By.xpath("//a[@class='delete']");

    public fWorkflowPage(WebDriver driver) {
        this.driver = driver;
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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageLocator));
        return driver.findElement(errorMessageLocator).getText();
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

    public void clickArrowForwardButton(int i) {
        String xpath = String.format("//div[@data-pos='%d']/div/div/a/span[@class='icon']/i[text()='arrow_forward']", i);
        By arrowForwardButtonLocator = By.xpath(xpath);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(arrowForwardButtonLocator));

        driver.findElement(arrowForwardButtonLocator).click();
    }

    public void clickNavigateBeforeButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        try {
            WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(navigateBeforeButtonLocator));
            button.click();
        } catch (TimeoutException e) {
            // Không tìm thấy button trong 5s, không làm gì cả
            System.out.println("Button NavigateBefore không hiển thị, không click.");
        }
    }

    public void clickTaoQuyTrinhButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(taoQuyTrinhButtonLocator));
        driver.findElement(taoQuyTrinhButtonLocator).click();
    }

    public void enterTenQuyTrinh(String ten) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(tenQuyTrinhTextboxLocator));
        driver.findElement(tenQuyTrinhTextboxLocator).sendKeys(ten);
    }

    public void clickHoanThanhTaoQuyTrinh() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(hoanThanhTaoQuyTrinhButtonLocator));
        driver.findElement(hoanThanhTaoQuyTrinhButtonLocator).click();
    }

    public void taoQuyTrinh(String ten) {
        enterTenQuyTrinh(ten);
        clickHoanThanhTaoQuyTrinh();
    }

    public boolean isQuyTrinhVuaTaoDisplayed(String name) {
        String xpath = String.format("//div[@title='%s']",name);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        return driver.findElement(By.xpath(xpath)).isDisplayed();
    }

    public void clickTaoMoiThe() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(taoMoiTheButtonLocator));
        driver.findElement(taoMoiTheButtonLocator).click();
    }

    public void clickTaoBuocCongViec() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(taoBuocCongViecButtonLocator));
        driver.findElement(taoBuocCongViecButtonLocator).click();
    }

    public void enterTenCongViec(String ten) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(tenCongViecTextboxLocator));
        driver.findElement(tenCongViecTextboxLocator).sendKeys(ten);
    }

    public void chonThanhVien(String tv) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(chonThanhVienDropDownLocator));
        driver.findElement(chonThanhVienDropDownLocator).click();
        String xpath = String.format("//div[@class='user_item']/span[normalize-space(text())='%s']",tv);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        driver.findElement(By.xpath(xpath)).click();
    }

    public void enterThoiHan(int tg) {
        String thoigian = String.valueOf(tg);
        driver.findElement(thoiHanTextboxLocator).sendKeys(thoigian);
    }

    public void clickHoanThanhTaoTheCongViec(){
        driver.findElement(hoanThanhTaoTheCongViecButtonLocator).click();
    }

    public void taoTheCongViec(String ten, String tv, int tg) {
        enterTenCongViec(ten);
        chonThanhVien(tv);
        enterThoiHan(tg);
        clickHoanThanhTaoTheCongViec();
    }

    public boolean isTenTheCongViecDisplayed(String name) {
        String xpath = String.format("//div[@class='user_item']/span[normalize-space(text())='%s']/../../..//div[@class='control']/p",name);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        return driver.findElement(By.xpath(xpath)).isDisplayed();
    }

    public void clickThuGonButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(thuGonButtonLocator));
        driver.findElement(thuGonButtonLocator).click();
    }

    public void keoThaCongViec() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Chờ và lấy phần tử cần kéo (thẻ công việc)
        WebElement theCongViec = wait.until(ExpectedConditions.visibilityOfElementLocated(theCongViecLocator));

        // Chờ và lấy phần tử đích (quy trình)
        WebElement quyTrinh = wait.until(ExpectedConditions.visibilityOfElementLocated(quyTrinhLocator));

        // Dùng Actions để kéo và thả
        Actions actions = new Actions(driver);
        actions.clickAndHold(theCongViec)
                .moveToElement(quyTrinh)
                .release()
                .build()
                .perform();
    }

    public void clickXuatBanButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(xuatBanButtonLocator));
        driver.findElement(xuatBanButtonLocator).click();
    }

    public void clickTaoNhiemVuButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(taoNhiemVuButtonLocator));
        driver.findElement(taoNhiemVuButtonLocator).click();
    }

    public void enterTenNhiemVu(String ten) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(tenNhiemVuTextboxLocator));
        driver.findElement(tenNhiemVuTextboxLocator).sendKeys(ten);
    }

    public void clickHoanThanhTaoNhiemVu(){
        driver.findElement(hoanThanhTaoNhiemVuButtonLocator).click();
    }

    public void taoNhiemVu(String ten) {
        enterTenNhiemVu(ten);
        clickHoanThanhTaoNhiemVu();
    }

    public boolean isNhiemVuVuaTaoDisplayed(String tencv) {
        String xpath = String.format("//li[text()='Tên nhiệm vụ']/../following-sibling::ul//p[@class='is-clickable has-text-weight-medium is-fullwidth'][text()='%s']",tencv);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        return driver.findElement(By.xpath(xpath)).isDisplayed();
    }

    public boolean isXuLyCongViecPopupDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(popupXuLyCongViecLocator));
        return driver.findElement(popupXuLyCongViecLocator).isDisplayed();
    }

    public void clickHoanThanhNhiemVuRadioButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(hoanThanhNhiemVuRadioButtonLocator));
        driver.findElement(hoanThanhTaoNhiemVuButtonLocator).click();
    }

    public void clickHoanThanhButton() {
        driver.findElement(hoanThanhButtonLocator).click();
    }

    public void clickXacNhanButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(xacNhanButtonLocator));
        driver.findElement(xacNhanButtonLocator).click();
    }

    public void closeXuLyNhiemVuPopup() {
        driver.findElement(closeXuLyCongViecPopupLocator).click();
    }

    public boolean isPopupCouponDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(popupCouponLocator));
        return driver.findElement(popupCouponLocator).isDisplayed();
    }

    public void closePopupCoupon() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(closePopupCoupon));
        driver.findElement(closePopupCoupon).click();
    }

    public boolean isTrangThaiHoanThanhDisplayed(String name) {
        String xpath = String.format("//span[normalize-space(@title)='%s']/../../../../..//span[@class='tag'][text()='Hoàn thành']",name);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        return driver.findElement(By.xpath(xpath)).isDisplayed();
    }

    public void closePopupTaoQuyTrinh() {
        driver.findElement(closePopupTaoQuyTrinhLocator).click();
    }

    public void clickHuyTaoTheCongViecButton() {
        driver.findElement(huyTaoTheCongViecButtonLocator).click();
    }

    public void clickOKAlert() {
        // Chờ alert xuất hiện
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        // Nhấn OK (accept) trên alert
        alert.accept();
    }

    public void clickBackIcon() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(backIconLocator));
        driver.findElement(backIconLocator).click();
    }

    public void clickThuGonPanel() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(thuGonPanelLocator));
        driver.findElement(thuGonPanelLocator).click();
    }

    public void xoaTheCongViec() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(cardWorkLocator));

        WebElement card = driver.findElement(cardWorkLocator);
        card.click();

        // Nhấn phím Delete sau khi click vào phần tử
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.DELETE).perform();
    }

    public void xoaMainPath() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(mainPathLocator));

        WebElement card = driver.findElement(mainPathLocator);

        // Click đúp chuột vào phần tử
        Actions actions = new Actions(driver);
        actions.doubleClick(card).perform();

        // Nhấn phím Delete sau khi click đúp
        actions.sendKeys(Keys.DELETE).perform();
    }

    public void dongPopupTaoCongViec() {
        driver.findElement(dongPopupTaoCongViecLocator).click();
    }

    public void dongPopupXulyCongViec() {
        driver.findElement(dongPopupXuLyCongViecLocator).click();
    }

    public boolean isTrangThaiDangXuLyDisplayed(String name) {
        String xpath = String.format("//span[normalize-space(@title)='%s']/../../../../..//span[@class='tag'][text()='Đang xử lý']",name);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        return driver.findElement(By.xpath(xpath)).isDisplayed();
    }
}
