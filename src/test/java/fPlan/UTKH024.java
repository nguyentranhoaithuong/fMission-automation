package fPlan;

import com.github.javafaker.Faker;
import fMission.Model.DanhGia;
import fMission.page.UserPage.HomePage;
import fMission.page.UserPage.LoginPage;
import fMission.page.UserPage.fPlanPage;
import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class UTKH024 {
    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    fMission.page.UserPage.fPlanPage fPlanPage;
    Faker faker;
    SoftAssert softAssert;
    Random random;

    String tenCongViec;
    String moTa;
    String thoiGian;

    @BeforeMethod
    public void initData() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options); // Khởi tạo driver trước

        driver.get("http://localhost:5000/");
        driver.manage().window().maximize();

        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        fPlanPage = new fPlanPage(driver);
        softAssert = new SoftAssert();
        faker = new Faker();
        random = new Random();

        Allure.step("Dang nhap vao he thong");

        loginPage.clickAcceptPolicy();
        loginPage.Login("thuongnth.fastdo@gmail.com","thuong@123");

        tenCongViec = "";
        moTa = "";
        LocalDate today = LocalDate.now(); // Lấy ngày hiện tại
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        thoiGian = today.format(formatter);

    }
    @AfterMethod
    public void cleanUp() {
        driver.quit();
    }
    @Test
    public void Test() {
        //Xác minh rằng thực hiện nhiệm vụ 4 thất bại khi user tạo mới todolist liên kết không thành công
        Allure.step("Truy cap module Ke hoach");
        homePage.clickFPlanModule();
        fPlanPage.openBangNhiemVu();
        fPlanPage.clickArrowForwardButton(4);
        fPlanPage.clickThemTodolistButton();
        fPlanPage.taoTodoListLienKet(tenCongViec,moTa);
        fPlanPage.clickXacNhanButton();
        softAssert.assertTrue(fPlanPage.isErrorMessageChecked());
        softAssert.assertEquals(fPlanPage.getErrorMessage(),"Vui lòng nhập tên công việc!");
        fPlanPage.closeErrorMessage();
        fPlanPage.closePopupXacNhanTaoTodolist();
        softAssert.assertTrue(fPlanPage.isThongBaoChuaCoTodolistDisplayed());
        fPlanPage.clickDongChiTietCongViec();
        fPlanPage.openBangNhiemVu();
        softAssert.assertFalse(fPlanPage.isCheckMark(4),"Đã đánh dấu hoành thành nhiệm vụ");
        softAssert.assertEquals(fPlanPage.getPhanTramHoanThanh(),"60%");
        homePage.clickHelpOutLineIcon();
        softAssert.assertEquals(homePage.getPhanThuongText(),"Tặng 6 ngày dùng chính thức");
        softAssert.assertAll();
    }
}
