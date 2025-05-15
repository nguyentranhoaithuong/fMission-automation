package fCFRs;

import com.github.javafaker.Faker;
import fMission.Model.GhiNhan;
import fMission.page.UserPage.GhiNhanPage;
import fMission.page.UserPage.HomePage;
import fMission.page.UserPage.LoginPage;
import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class UTCFRs029 {
    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    GhiNhanPage ghiNhanPage;
    Faker faker;
    SoftAssert softAssert;
    Random random;
    String tenNguoiThucHien;
    String tenNguoiNhan;
    String noiDung;
    String thoiGian;

    GhiNhan taoGhiNhan;
    GhiNhan ghiNhan;


    @BeforeMethod
    public void initData() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options); // Khởi tạo driver trước

        driver.get("http://localhost:5000/");
        driver.manage().window().maximize();

        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        ghiNhanPage = new GhiNhanPage(driver);

        softAssert = new SoftAssert();
        faker = new Faker();
        random = new Random();

        Allure.step("Dang nhap vao he thong");

        loginPage.clickAcceptPolicy();
        loginPage.Login("thuongnth.fastdo@gmail.com","thuong@123");

        tenNguoiThucHien = "Nguyễn Trần Hoài Thương";
        tenNguoiNhan = "Lê Thị Linh";
        noiDung = faker.lorem().paragraph();

        LocalDate today = LocalDate.now(); // Lấy ngày hiện tại
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        thoiGian = today.format(formatter); // Định dạng ngày

        taoGhiNhan = new GhiNhan();
        taoGhiNhan.setTenNguoiThucHien(tenNguoiThucHien);
        taoGhiNhan.setTenNguoiNhan(tenNguoiNhan);
        taoGhiNhan.setNoiDung(noiDung);
        taoGhiNhan.setThoiGian(thoiGian);

    }
    @AfterMethod
    public void cleanUp() {
//        driver.quit();
    }
    @Test
    public void Test() {
        Allure.step("Truy cap module Ghi nhan");
        homePage.clickFGhiNhanModule();
        ghiNhanPage.ghiNhanDongNghiep(tenNguoiNhan,noiDung);
        softAssert.assertTrue(ghiNhanPage.isToastMessageChecked());
        softAssert.assertEquals(ghiNhanPage.toastMessageText(), "Chúc mừng bạn đã hoàn thành nhiệm vụ!");
        ghiNhanPage.closeToastMessage();
        ghiNhan = ghiNhanPage.getGhiNhanVuaTao();
        softAssert.assertTrue(ghiNhanPage.isCheckMark(3), "Chưa đánh dấu hoành thành nhiệm vụ");
        softAssert.assertEquals(ghiNhanPage.getPhanTramHoanThanh(),"60%");
        softAssert.assertEquals(ghiNhan, taoGhiNhan);
        homePage.clickHelpOutLineIcon();
        softAssert.assertEquals(homePage.getPhanThuongText(), "Tặng 6 ngày dùng chính thức");
        softAssert.assertAll();
    }
}
