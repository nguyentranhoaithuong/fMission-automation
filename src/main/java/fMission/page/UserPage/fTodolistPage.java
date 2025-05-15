package fMission.page.UserPage;

import fMission.Model.CongViecTodolist;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class fTodolistPage {
    WebDriver driver;
    By themCongViecButtonLocator = By.xpath("//span[text()='Thêm công việc']");
    By tenCongViecTextboxLocator = By.xpath("//input[@placeholder='Tên công việc']");
    By capNhatButtonLocator = By.xpath("//span[text()='Cập nhật']");
    By toastMessageLocator = By.xpath("//div[@class='notification is-success']/p");
    By errorMessageLocator = By.xpath("//div[@class='notification is-danger']/p");
    By closeErrorMessageLocator = By.xpath("//div[@class='notification is-danger']/a");
    By closeToastMessageLoctor = By.xpath("//div[@class='notification is-success']/a[@class='delete']");
    By chiTietCongViecTextboxLocator = By.xpath("//div[@data-placeholder='Nhập mô tả công việc...']");
    By tenCongViecLocator = By.xpath("//span[@class='has-text-weight-medium has-text-link']");
    By chiTietCongViecLocator = By.xpath("//div[@class='text_2_line is-word-break ql-editor']/p");
    By leftCalendarButtonLocator = By.xpath("//i[normalize-space(text())='chevron_left']");
    By lichTabLocator = By.xpath("//span[text()='Lịch']");
    By dongButtonLocator = By.xpath("//span[text()='Đóng']");
    By calendarLocator = By.xpath("//p[text()='Thứ 2']");
    By tenCongViecDangLichTextBoxLocator = By.xpath("//input[@placeholder='Nhập tiêu đề công việc']");
    By chiTietCongViecDangLichTextboxLocator = By.xpath("//div[@data-placeholder='Nhập chi tiết công việc...']");
    By themCongViecDangLichButtonLocator = By.xpath("//span[text()='công việc']");
    By tenCongViecDangLichLocator = By.xpath("//ul[@class='columns is-vcentered is-variable is-2']/li/p");
    By danhSachTabLocator = By.xpath("//span[text()='Danh sách']");
    By nopTodolistButtonLocator = By.xpath("//span[text()='Nộp todolist']");
    By baoCaoTodolistLocator = By.xpath("//span[text()='Báo cáo todolist']");
    By tinhTrangCongViecDropDownLocator = By.xpath("(//div[@class='dropdown-trigger']/a/span/i[text()='arrow_drop_down'])[last()]");
    By tinhTrangCongViecDangLichDropDownLocator = By.xpath("//div[@class='dropdown-trigger']/a[@class='button is-rounded  is-small tag-calendar-todo']");
    By hopQuaButtonLocator = By.xpath("//img[@alt='Icon Gift']");
    By bangNhiemVuLocator = By.id("sidebar");
    By getPhanTramHoanThanhLocator = By.xpath("//div[@class='mission-header']/div/span");
    By giftIconLocator = By.xpath("//img[@alt='Icon Gift']");

    public fTodolistPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickArrowForwardButton(int i) {
        String xpath = String.format("//div[@data-pos='%d']/div/div/a/span[@class='icon']/i[text()='arrow_forward']", i);
        By arrowForwardButtonLocator = By.xpath(xpath);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(arrowForwardButtonLocator));

        driver.findElement(arrowForwardButtonLocator).click();
    }

    public void openDanhSachTab() {
        List<WebElement> elements = driver.findElements(calendarLocator);
        if (!elements.isEmpty() && elements.get(0).isDisplayed()) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(danhSachTabLocator));
            driver.findElement(danhSachTabLocator).click();
        }
    }


    public void clickThemCongViecButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(themCongViecButtonLocator));
        driver.findElement(themCongViecButtonLocator).click();
    }

    public void enterTenCongViec(String name) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(tenCongViecTextboxLocator));
        driver.findElement(tenCongViecTextboxLocator).sendKeys(name);
    }

    public void enterChiTietCongViec(String chitiet) {
        driver.findElement(chiTietCongViecTextboxLocator).sendKeys(chitiet);
    }

    public void clickCapNhatButton() {
        driver.findElement(capNhatButtonLocator).click();
    }

    public void themCongViec(String name, String chitiet) {
        enterTenCongViec(name);
        enterChiTietCongViec(chitiet);
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

    public String getTenCongViec() {
        return driver.findElement(tenCongViecLocator).getText();
    }

    public String getChiTietCongViec() {
        return driver.findElement(chiTietCongViecLocator).getText();
    }

    public CongViecTodolist getTodolistByIndex() {
        CongViecTodolist cv = new CongViecTodolist();
        cv.setTenCongViec(getTenCongViec());
        cv.setChiTiet(getChiTietCongViec());
        return cv;
    }

    public void clickLeftCalendarButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement leftButton = wait.until(ExpectedConditions.elementToBeClickable(leftCalendarButtonLocator));
        leftButton.click();
    }

    public void openDateChuaCoCongViec() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        while (true) {
            try {
                // Chờ phần tử tenCongViec xuất hiện
                wait.until(ExpectedConditions.visibilityOfElementLocated(tenCongViecLocator));
            } catch (TimeoutException e) {
                // Nếu chờ hết thời gian mà không thấy thì kết thúc hàm
                System.out.println("Không tìm thấy công việc trong thời gian chờ.");
                return;
            }
            // Tìm các phần tử công việc
            List<WebElement> elements = driver.findElements(tenCongViecLocator);

            // Nếu không tìm thấy phần tử nào nữa thì thoát vòng lặp
            if (elements.isEmpty()) {
                break;
            }
            // Nếu có ít nhất 1 phần tử hiển thị thì tiếp tục click lùi lịch
            boolean isAnyVisible = false;
            for (WebElement element : elements) {
                if (element.isDisplayed()) {
                    isAnyVisible = true;
                    break;
                }
            }

            // Nếu không có phần tử nào hiển thị thì dừng
            if (!isAnyVisible) {
                break;
            }

            // Click nút lùi lịch
            clickLeftCalendarButton();

            // Đợi trang cập nhật
            try {
                Thread.sleep(500); // Có thể thay bằng wait.until nếu biết điều kiện cụ thể
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void openDateChuaNopToDoList() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        while (true) {
            try {
                // Chờ tối đa 5 giây để nút nộp todolist xuất hiện
                WebElement submitButton = wait.until(ExpectedConditions.visibilityOfElementLocated(nopTodolistButtonLocator));
                if (submitButton.isDisplayed()) {
                    return; // Nếu thấy thì thoát
                }
            } catch (TimeoutException e) {
                // Nếu hết thời gian mà không thấy thì click lùi
                clickLeftCalendarButton();

                try {
                    Thread.sleep(500); // Cho trang cập nhật
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }

                // Sau khi lùi, tiếp tục vòng lặp để chờ nút hiển thị ở ngày mới
            }
        }
    }


    public void clickNopTodolistButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Chờ nút có thể click được rồi click
        wait.until(ExpectedConditions.elementToBeClickable(nopTodolistButtonLocator));
        driver.findElement(nopTodolistButtonLocator).click();
    }

    public void clickOKAlert() {
        // Chờ alert xuất hiện
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        // Nhấn OK (accept) trên alert
        alert.accept();
    }

    public void OpenDateChuaBaoCaoTodolist() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        while (true) {
            try {
                // Chờ tối đa 5 giây để nút báo cáo todolist hiển thị
                WebElement reportButton = wait.until(ExpectedConditions.visibilityOfElementLocated(baoCaoTodolistLocator));
                if (reportButton.isDisplayed()) {
                    return; // Nếu thấy nút thì thoát luôn
                }
            } catch (TimeoutException e) {
                // Nếu không thấy thì lùi lịch
                clickLeftCalendarButton();

                try {
                    Thread.sleep(500); // Cho trang cập nhật
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }

                // Tiếp tục vòng lặp để chờ ở ngày mới
            }
        }
    }


    public void clickBaoCaoTodolist(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(baoCaoTodolistLocator));
        driver.findElement(baoCaoTodolistLocator).click();
    }

    public void selectTinhTrangCongViec(String status) {
        driver.findElement(tinhTrangCongViecDropDownLocator).click();
        String xpath = String.format("//a[@class='dropdown-item'][text()='%s']",status);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        driver.findElement(By.xpath(xpath)).click();
    }

//    TODOLIST DANG LICH ----------------------------------------

    public void openLichTab() {

        List<WebElement> elements = driver.findElements(calendarLocator);
        if (elements.isEmpty() || !elements.get(0).isDisplayed()) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(lichTabLocator));
            driver.findElement(lichTabLocator).click();
        }
    }


    public void clickDate(int d){
        String xpath = String.format("//span[@class='is-size-7'][text()='%d']",d);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        driver.findElement(By.xpath(xpath)).click();
    }

    public void clickThemCongViecDangLich(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(themCongViecDangLichButtonLocator));
        driver.findElement(themCongViecDangLichButtonLocator).click();
    }

    public void clickDongButton() {
        driver.findElement(dongButtonLocator).click();
    }

    public void enterTenCongViecDangLich(String name) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(tenCongViecDangLichTextBoxLocator));
        driver.findElement(tenCongViecDangLichTextBoxLocator).sendKeys(name);
    }

    public void enterChiTietCongViecDangLich(String chitiet) {
        driver.findElement(chiTietCongViecDangLichTextboxLocator).sendKeys(chitiet);
    }

    public void themCongViecDangLich(String name, String chitiet) {
        enterTenCongViecDangLich(name);
        enterChiTietCongViecDangLich(chitiet);
        clickCapNhatButton();
    }

    public String getTenCongViecDangLich() {
        return driver.findElement(tenCongViecDangLichLocator).getText();
    }

    public CongViecTodolist getTodolistDangLich() {
        CongViecTodolist cv = new CongViecTodolist();
        cv.setTenCongViec(getTenCongViecDangLich());
        cv.setChiTiet(getChiTietCongViec());
        return cv;
    }

    public void selectTinhTrangCongViecDangLich(String status) {
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait1.until(ExpectedConditions.visibilityOfElementLocated(tinhTrangCongViecDangLichDropDownLocator));
        driver.findElement(tinhTrangCongViecDangLichDropDownLocator).click();
        String xpath = String.format("//a[@class='dropdown-item is-size-7'][text()='%s']",status);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        driver.findElement(By.xpath(xpath)).click();
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




