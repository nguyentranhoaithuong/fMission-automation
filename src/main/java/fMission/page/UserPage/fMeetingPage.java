package fMission.page.UserPage;

import fMission.Model.DanhGia;
import fMission.Model.GiaiPhap;
import fMission.Model.TodolistLienKet;
import fMission.Model.VanDe;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class fMeetingPage {
    WebDriver driver;
    By taoCuocHopButtonLocator = By.xpath("//span[text()='Tạo cuộc họp']");
    By giftButtonLocator = By.xpath("//img[@alt='Icon Gift']");
    By themPhienHopButtonLocator = By.xpath("//span[normalize-space(text())='Thêm phiên họp']");
    By chonPhienHopDropdownlist = By.xpath("//li[@class='column']//span[@class='icon-text']");
    By capNhatButtonLocator = By.xpath("//span[text()='Cập nhật']");
    By percentProcessLocator = By.xpath("//div[@class='mission-header']/div/span");
    By batDauHopButtonLocator = By.xpath("//button[normalize-space(text())='Bắt đầu họp']");
    By navigateButtonLocator = By.xpath("//i[text()='navigate_before']");
    By arrowFowardButtonLocator = By.xpath("//i[text()='arrow_forward']");
    By themCongViecButtonLocator = By.xpath("//span[text()='Tạo công việc']");
    By taoVanDeButtonLocator = By.xpath("//span[text()='Tạo vấn đề']");
    By tieuDeVanDeTextboxLocator = By.xpath("//input[@class='input is_bg']");
    By noiDungVanDeTextBoxLocator = By.xpath("//div[@class='ql-editor ql-blank']");
    By xacNhanTaoVanDeButtonLocator = By.xpath("//button/span[normalize-space(text())='Xác nhận']");
    By taoMoiGiaiPhapButtonLocator = By.xpath("//span[text()='Tạo mới']");
    By tieuDeGiaiPhapTextboxLocator = By.xpath("//input[@class='input is_bg']");
    By noiDungGiaiPhapTextBoxLocator = By.xpath("//div[@class='ql-editor ql-blank']");
    By capNhatGiaiPhapButtonLocator = By.xpath("//span[normalize-space(text())='Cập nhật']");
    By thongNhatCheckBoxLocator = By.xpath("//div[@class='control']//span[text()='Thống nhất']");
    By theoTodolistLocator = By.xpath("//a[text()='Theo Todolist']");
    By toastMessageLocator = By.xpath("//div[@class='notification is-success']/p");
    By errorMessageLocator = By.xpath("//div[@class='notification is-danger']/p");
    By closeErrorMessageLocator = By.xpath("//div[@class='notification is-danger']/a");
    By closeToastMessageLocator = By.xpath("//div[@class='notification is-success']/a[@class='delete']");
    By tenPhienVanDeGiaiPhapLocator = By.xpath("//p[text()='Vấn đề - Giải pháp']");
    By phienVanDeLocator = By.xpath("//div[@class='sections-meeting']//span[contains(normalize-space(), 'Vấn đề')]");
    By tenPhienCongViecLocator = By.xpath("//p[text()='Công việc']");
    By phienCongViecLocator = By.xpath("//div[@class='sections-meeting']//span[contains(normalize-space(), 'Công việc')]");
    By tenVanDeLocator = By.xpath("//p[@class='has-text-weight-semibold text_1_line']");
    By noiDungVanDeLocator = By.xpath("//p[@class=' is-size-7 text_many_line mb-3 px-4 ql-editor']/p");
    By tenGiaiPhapLocator = By.xpath("//p[@class='is-size-7  has-text-weight-semibold text_1_line']");
    By noiDungGiaiPhapLocator = By.xpath("//p[@class=' is-size-7 text_many_line mb-3 ql-editor']/p");
    By taoCongViecTodolistLocator = By.xpath("//span[text()='Tạo công việc']");
    By tieuDeCongViecTextboxLocator = By.xpath("//input[@class='input is_bg']");
    By moTaCongViecTextboxLocator = By.xpath("//div[@data-placeholder='Nhập mô tả công việc...']");
    By xacNhanCongViecButtonLocator = By.xpath("//span[normalize-space(text())='Xác nhận']");
    By diemDanhGiaTextBoxLocator = By.xpath("//input[@class='input is_bg']");
    By danhGiaTextBoxLocator = By.xpath("//div[@data-placeholder='Nhập đánh giá...']/p");
    By checkIconLocator = By.xpath("//i[text()='check']");
    By couponPopupLocator = By.xpath("//div[@class='image']");
    By closeCouponPopupLocator = By.xpath("//div[@class='image']/a/span[text()='close'][@class='material-icons-outlined']");
    By diemLocator = By.xpath("//label[text()='Điểm']/following-sibling::div/span");
    By danhGiaLocator = By.xpath("//label[text()='Mô tả']/following-sibling::div//p/p");
    By getPhanTramHoanThanhLocator = By.xpath("//div[@class='mission-header']/div/span");
    By tieuDeCuocHopTextboxLocator = By.xpath("//input[@placeholder='Nhập tiêu đề cuộc họp...']");
    By tieuDeCuocHopLocator = By.xpath("(//span[@class='icon-text has-text-link has-text-weight-medium']/span)[last()]");
    By giftIconLocator = By.xpath("//img[@alt='Icon Gift']");
    By dongPopupTaoCongViecLocator = By.xpath("//span[text()='Đóng']");
    By dongPanelLocator = By.xpath("//span[text()='Đóng']");
    public fMeetingPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickTaoCuocHopButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(taoCuocHopButtonLocator));
        driver.findElement(taoCuocHopButtonLocator).click();
    }

    public void clickGiftButton() {
        driver.findElement(giftButtonLocator).click();
    }

    public void clickThemPhienHopButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(themPhienHopButtonLocator));
        driver.findElement(themPhienHopButtonLocator).click();
    }

    public void clickPhienHop(int i) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(chonPhienHopDropdownlist));
        driver.findElements(chonPhienHopDropdownlist).get(i-1).click();
    }

    public void selectPhienHop(String option) {
        String xpathOption = String.format("//a[@class='dropdown-item']/span[text()='%s']",option);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathOption)));
        driver.findElement(By.xpath(xpathOption)).click();
    }

    public void clickCapNhatButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(capNhatButtonLocator));
        driver.findElement(capNhatButtonLocator).click();
    }


    public void clickArrowForwardButton(int i) {
        String xpath = String.format("//div[@data-pos='%d']/div/div/a/span[@class='icon']/i[text()='arrow_forward']", i);
        By arrowForwardButtonLocator = By.xpath(xpath);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(arrowForwardButtonLocator));

        driver.findElement(arrowForwardButtonLocator).click();
    }

    public void clickTaoVanDeButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(taoVanDeButtonLocator));
        driver.findElement(taoVanDeButtonLocator).click();
    }

    public void enterTieudeVanDe(String tieude) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(tieuDeVanDeTextboxLocator));
        driver.findElement(tieuDeVanDeTextboxLocator).sendKeys(tieude);
    }

    public void enterNoiDungVanDe(String noidung) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(noiDungVanDeTextBoxLocator));
        driver.findElement(noiDungVanDeTextBoxLocator).sendKeys(noidung);
    }

    public void clickXacNhanTaoVanDe() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(xacNhanTaoVanDeButtonLocator));
        driver.findElement(xacNhanTaoVanDeButtonLocator).click();
    }

    public void taoVanDe(String tieude, String noidung) {
        enterTieudeVanDe(tieude);
        enterNoiDungVanDe(noidung);
        clickXacNhanTaoVanDe();
    }

    public void enterTieuDeGiaiPhap(String tieude) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(tieuDeGiaiPhapTextboxLocator));
        driver.findElement(tieuDeGiaiPhapTextboxLocator).clear();
        wait.until(ExpectedConditions.visibilityOfElementLocated(tieuDeGiaiPhapTextboxLocator));
        driver.findElement(tieuDeGiaiPhapTextboxLocator).sendKeys(tieude);
    }

    public void enterNoiDungGiaiPhap(String noidung) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(noiDungGiaiPhapTextBoxLocator));
        driver.findElement(noiDungGiaiPhapTextBoxLocator).sendKeys(noidung);
    }

    public void clickCapNhatGiaiPhap() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(capNhatGiaiPhapButtonLocator));
        driver.findElement(capNhatGiaiPhapButtonLocator).click();
    }

    public void taoThongTinGiaiPhap(String tieude, String noidung) {
        enterTieuDeGiaiPhap(tieude);
        enterNoiDungGiaiPhap(noidung);
        clickCapNhatGiaiPhap();
    }
    public void clickThongNhatCheckBox() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(thongNhatCheckBoxLocator));
        driver.findElement(thongNhatCheckBoxLocator).click();
    }
    public void clickTabTheoTodolist() {
        driver.findElement(theoTodolistLocator).click();
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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(closeToastMessageLocator));
        driver.findElement(closeToastMessageLocator).click();
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

    public void clickPhienVanDeGiaiPhap() {
        try {
            boolean status = driver.findElement(tenPhienVanDeGiaiPhapLocator).isDisplayed();
            if (!status) {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.visibilityOfElementLocated(phienVanDeLocator));
                driver.findElement(phienVanDeLocator).click();
            }
        } catch (NoSuchElementException e) {
            // Nếu không tìm thấy phần tử, vẫn thử click để mở
            driver.findElement(phienVanDeLocator).click();
        }
    }

    public void clickPhienCongViecGiaiPhap() {
        try {
            boolean status = driver.findElement(tenPhienCongViecLocator).isDisplayed();
            if (!status) {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.visibilityOfElementLocated(phienCongViecLocator));
                driver.findElement(phienCongViecLocator).click();
            }
        } catch (NoSuchElementException e) {
            // Nếu không tìm thấy phần tử, vẫn thử click để mở
            driver.findElement(phienCongViecLocator).click();
        }
    }

    public String getTenVanDe(int i) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(tenVanDeLocator));
        return driver.findElements(tenVanDeLocator).get(i-1).getText().replaceAll("^Vấn đề \\d+\\s*", "").trim();
    }

    public String getNoiDungVanDe(int i) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(noiDungVanDeLocator));
        return driver.findElements(noiDungVanDeLocator).get(i-1).getText();
    }

    public VanDe getVanDeByIndex(int i) {
        VanDe vd = new VanDe();
        vd.setTenVanDe(getTenVanDe(i));
        vd.setNoiDung(getNoiDungVanDe(i));
        return vd;
    }

    public void clickTaoMoiGiaiPhap() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(taoMoiGiaiPhapButtonLocator));
        driver.findElement(taoMoiGiaiPhapButtonLocator).click();
    }

    public String getTenGiaiPhap(int i) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(tenGiaiPhapLocator));
        return driver.findElements(tenGiaiPhapLocator).get(i-1).getText();
    }

    public String getNoiDungGiaiPhap(int i) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(noiDungGiaiPhapLocator));
        return driver.findElements(noiDungGiaiPhapLocator).get(i-1).getText();
    }

    public GiaiPhap getGiaiPhapByIndex(int i) {
        GiaiPhap gp = new GiaiPhap();
        gp.setTenGiaiPhap(getTenGiaiPhap(i));
        gp.setNoiDungGiaiPhap(getNoiDungGiaiPhap(i));
        return gp;
    }

    public void clickTaoCongViecTodolist() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(taoCongViecTodolistLocator));
        driver.findElement(taoCongViecTodolistLocator).click();
    }

    public void enterTieuDeCongViec(String ten){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(tieuDeCongViecTextboxLocator));
        driver.findElement(tieuDeCongViecTextboxLocator).clear();
        wait.until(ExpectedConditions.visibilityOfElementLocated(tieuDeCongViecTextboxLocator));
        driver.findElement(tieuDeCongViecTextboxLocator).sendKeys(ten);
    }

    public void enterMoTaCongViec(String mota){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(moTaCongViecTextboxLocator));
        driver.findElement(moTaCongViecTextboxLocator).sendKeys(mota);
    }

    public void clickXacNhanCongViecButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(xacNhanCongViecButtonLocator));
        driver.findElement(xacNhanCongViecButtonLocator).click();
    }

    public void taoCongViecTodolist(String ten, String mota) {
        enterTieuDeCongViec(ten);
        enterMoTaCongViec(mota);
        clickXacNhanCongViecButton();
    }

    public String getTieuDeCongViecByDate(String date) {
            // Tìm thẻ <p> theo XPath
            String xpath = String.format("(//span[text()='%s']/../following-sibling::ul/li/p)[last()]",date);
            // Lấy giá trị thuộc tính "title"
            String title = driver.findElement(By.xpath(xpath)).getAttribute("title");
            return title;
    }

    public TodolistLienKet getTodolistLienKet(String date) {
        TodolistLienKet td = new TodolistLienKet();
        td.setTenCongViec(getTieuDeCongViecByDate(date));
        return td;
    }

    public void enterDiemDanhGia(int diem) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(diemDanhGiaTextBoxLocator));
        String diemDanhGia = String.valueOf(diem);
        driver.findElement(diemDanhGiaTextBoxLocator).clear();
        driver.findElement(diemDanhGiaTextBoxLocator).sendKeys(diemDanhGia);
    }

    public void enterDanhGia(String danhgia) {
        driver.findElement(danhGiaTextBoxLocator).sendKeys(danhgia);
    }

    public void clickCheckIcon() {
        driver.findElement(checkIconLocator).click();
    }

    public void taoDanhGia(int diem, String danhgia) {
        enterDiemDanhGia(diem);
        enterDanhGia(danhgia);
        clickCheckIcon();
    }

    public int getDiemDanhGia() {
        return Integer.parseInt(driver.findElement(diemLocator).getText());
    }
    public String getDanhGia() {
        return driver.findElement(danhGiaLocator).getText();
    }

    public DanhGia getDanhGiaCuocHop() {
        DanhGia dg= new DanhGia();
        dg.setDiem(getDiemDanhGia());
        dg.setDanhGia(getDanhGia());
        return dg;
    }

    public boolean isCouPonPopupDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(couponPopupLocator));
        return driver.findElement(couponPopupLocator).isDisplayed();
    }

    public void closeCouponPopup() {
        driver.findElement(closeCouponPopupLocator).click();
    }

    public void enterTieuDeCuocHop(String tieude) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(tieuDeCuocHopTextboxLocator));
        driver.findElement(tieuDeCuocHopTextboxLocator).clear();
        wait.until(ExpectedConditions.visibilityOfElementLocated(tieuDeCuocHopTextboxLocator));
        driver.findElement(tieuDeCuocHopTextboxLocator).sendKeys(tieude);
    }

    public String getTieuDeCuocHop() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(tieuDeCuocHopLocator));
        return driver.findElement(tieuDeCuocHopLocator).getText();
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

    public void clickDongPopupTaoCongViec() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(dongPopupTaoCongViecLocator));
        driver.findElement(dongPopupTaoCongViecLocator).click();
    }

    public void clickDongPanel() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(dongPanelLocator));
        driver.findElement(dongPanelLocator).click();
    }

}

