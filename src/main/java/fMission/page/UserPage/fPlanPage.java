package fMission.page.UserPage;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class fPlanPage {
    WebDriver driver;
    By taoKeHoachButtonLocator = By.xpath("//span[@class='icon']/i[text()='add']");
    By tieuDeKeHoachTextBoxLocator = By.id("plan_name");
    By hoanTatTaoKeHoachButtonLocator = By.xpath("//span[text()='Hoàn tất']");
    By tenKeHoachVuaTaoLocator = By.xpath("//div[@class='button w-full mb-1 is-primary is-light ']/a");
    By toastMessageLocator = By.xpath("//div[@class='notification is-success']/p");
    By errorMessageLocator = By.xpath("//div[@class='notification is-danger']/p");
    By closeErrorMessageLocator = By.xpath("//div[@class='notification is-danger']/a");
    By closeToastMessageLoctor = By.xpath("//div[@class='notification is-success']/a[@class='delete']");
    By keHoachVuaTaoLocator = By.xpath("//a[@class='icon-text ']/div");
    By giftIconLocator = By.xpath("//img[@alt='Icon Gift']");
    By taoNhomCongViecButtonLocator = By.xpath("//span[text()='Tạo nhóm công việc']");
    By nhomCongViecMoiLocator = By.xpath("//span[@title='Nhóm công việc mới']");
    By themCongViecButtonLocator = By.xpath("//span[text()='Thêm công việc']");
    By congViecVuaTaoLocator = By.xpath("//a[text()='Công việc mới']");
    By themTodolistButtonLocator = By.xpath("//span[text()='Thêm Todolist ']");
    By tieuDeCongViecTextboxLocator = By.xpath("//label[text()='Tiêu đề công việc']/following-sibling::div/input");
    By moTaCongViecTextBoxLocator = By.xpath("//div[@data-placeholder='Nhập mô tả công việc...']/p");
    By capNhatButtonLocator = By.xpath("//span[text()='Cập nhật']");
    By xacNhanButtonLocator = By.xpath("//span[text()='Xác nhận']");
    By dongChiTietCongViecButtonLocator = By.xpath("//span[text()='Đóng']");
    By binhLuanTextboxLocator = By.xpath("//textarea[@id='task_comment']");
    By sendIconLocator = By.xpath("//i[text()='send']");
    By popupCouponLocator = By.xpath("//div[@class='modal-card is_radius']/div/div[@class='image']");
    By closePopupCoupon = By.xpath("//span[text()='close']");
    By closePopupXacNhanTaoTodolistLocator = By.xpath("//div[text()='Xác nhận tạo todolist liên kết']/following-sibling::a[@class='delete']");
    By thongBaoChuaCoTodolistLocator = By.xpath("//span[text()='Chưa có dữ liệu todolist liên kết']");
    By getPhanTramHoanThanhLocator = By.xpath("//div[@class='mission-header']/div/span");
    By closePopupTaoKeHoachLocator = By.xpath("//a[@class='delete is-medium']");

    public fPlanPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickArrowForwardButton(int i) {
        String xpath = String.format("//div[@data-pos='%d']/div/div/a/span[@class='icon']/i[text()='arrow_forward']", i);
        By arrowForwardButtonLocator = By.xpath(xpath);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(arrowForwardButtonLocator));

        driver.findElement(arrowForwardButtonLocator).click();
    }

    public void clickTaoKeHoachButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(taoKeHoachButtonLocator));
        driver.findElement(taoKeHoachButtonLocator).click();
    }

    public void enterTieuDeKeHoach(String name) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(tieuDeKeHoachTextBoxLocator));
        driver.findElement(tieuDeKeHoachTextBoxLocator).sendKeys(name);
    }

    public void clickHoanTatButton(){
        driver.findElement(hoanTatTaoKeHoachButtonLocator).click();
    }
    public void taoKeHoach(String name) {
        enterTieuDeKeHoach(name);
        clickHoanTatButton();
    }

    public String getTenKeHoachVuaTao() {
        return driver.findElement(tenKeHoachVuaTaoLocator).getText();
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

    public boolean isKeHoachVuaTaoDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(keHoachVuaTaoLocator));
        return driver.findElement(keHoachVuaTaoLocator).isDisplayed();
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

    public void clickTaoNhomCongViecButton() {
        By addIcon = By.xpath("//a[@class='is-toggle']//i[text()='add']");

        try {
            // Tìm tất cả phần tử khớp với addIcon
            List<WebElement> elements = driver.findElements(addIcon);

            if (!elements.isEmpty() && elements.get(0).isDisplayed()) {
                // Nếu addIcon hiển thị thì click vào nó
                elements.get(0).click();
            }

            // Dù addIcon có hiển thị hay không, luôn đợi và click vào nút Tạo Nhóm Công Việc
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(taoNhomCongViecButtonLocator));
            driver.findElement(taoNhomCongViecButtonLocator).click();

        } catch (Exception e) {
            System.out.println("Lỗi khi click tạo nhóm công việc: " + e.getMessage());
        }
    }


    public boolean isNhomCongViecMoiDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(nhomCongViecMoiLocator));
        return driver.findElement(nhomCongViecMoiLocator).isDisplayed();
    }

    public void clickThemCongViecButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(themCongViecButtonLocator));
        driver.findElement(themCongViecButtonLocator).click();
    }

    public boolean isCongViecVuaTaoDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(congViecVuaTaoLocator));
        return driver.findElement(congViecVuaTaoLocator).isDisplayed();
    }

    public void clickThemTodolistButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(themTodolistButtonLocator));
        driver.findElement(themTodolistButtonLocator).click();
    }

    public void enterTieuDeCongViec(String title) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(tieuDeCongViecTextboxLocator));
        driver.findElement(tieuDeCongViecTextboxLocator).clear();
        wait.until(ExpectedConditions.visibilityOfElementLocated(tieuDeCongViecTextboxLocator));
        driver.findElement(tieuDeCongViecTextboxLocator).sendKeys(title);
    }

    public void enterMoTaCongViec(String mota) {
        driver.findElement(moTaCongViecTextBoxLocator).sendKeys(mota);
    }

    public void clickCapNhatButton() {
        driver.findElement(capNhatButtonLocator).click();
    }

    public void taoTodoListLienKet(String title, String mota) {
        enterTieuDeCongViec(title);
        enterMoTaCongViec(mota);
        clickCapNhatButton();
    }

    public void clickXacNhanButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(xacNhanButtonLocator));
        driver.findElement(xacNhanButtonLocator).click();
    }

    public String getTenTodolist(String date) {
        String xpath = String.format("//span[text()='%s']/../..//p",date);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        return driver.findElement(By.xpath(xpath)).getText();
    }

    public boolean isTenTodolistDisplayed(String date) {
        String xpath = String.format("//span[text()='%s']/../..//p",date);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        return driver.findElement(By.xpath(xpath)).isDisplayed();
    }

    public void clickDongChiTietCongViec(){
        driver.findElement(dongChiTietCongViecButtonLocator).click();
    }

    public void enterBinhLuan(String bl) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(binhLuanTextboxLocator));
        driver.findElement(binhLuanTextboxLocator).sendKeys(bl);
    }

    public void clickSendIcon() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(sendIconLocator));
        driver.findElement(sendIconLocator).click();
    }

    public void taoBinhLuan(String binhluan) {
        enterBinhLuan(binhluan);
        clickSendIcon();
    }

    public boolean isPopupCouponDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(popupCouponLocator));
        return driver.findElement(popupCouponLocator).isDisplayed();
    }

    public void closePopupCoupon() {
        driver.findElement(closePopupCoupon).click();
    }

    public String getBinhLuanVuaTao(String name) {
        String xpath = String.format("(//div[@class='comment_detail']/p[normalize-space(text())='%s']/following-sibling::div/span)[last()]",name);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        return driver.findElement(By.xpath(xpath)).getText();
    }

    public void closePopupXacNhanTaoTodolist() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(closePopupXacNhanTaoTodolistLocator));
        driver.findElement(closePopupXacNhanTaoTodolistLocator).click();
    }

    public boolean isThongBaoChuaCoTodolistDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(thongBaoChuaCoTodolistLocator));
        return driver.findElement(thongBaoChuaCoTodolistLocator).isDisplayed();
    }

    public void closePopupTaoKeHoach() {
        driver.findElement(closePopupTaoKeHoachLocator).click();
    }


}
