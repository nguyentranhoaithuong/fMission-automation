package fCFRs;

import com.github.javafaker.Faker;
import fMission.Model.DonHang;
import fMission.page.AdminPage.CauHinhPage;
import fMission.page.AdminPage.CuaHangPage;
import fMission.page.AdminPage.DonHangPage;
import fMission.page.UserPage.DoiQuaPage;
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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class UTCFRs035 {
    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    GhiNhanPage ghiNhanPage;
    DoiQuaPage doiQuaPage;
    CauHinhPage cauHinhPage;
    DonHangPage donHangPage;
    CuaHangPage cuaHangPage;
    Faker faker;
    SoftAssert softAssert;
    Random random;
    String tenSanPham;
    int soLuong;
    int sosao;
    String thoiGian;
    String nguoiMua;
    String trangThai;

    DonHang muaSanPham;
    DonHang donHang;


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
        doiQuaPage = new DoiQuaPage(driver);
        cauHinhPage = new CauHinhPage(driver);
        donHangPage = new DonHangPage(driver);
        cuaHangPage = new CuaHangPage(driver);

        softAssert = new SoftAssert();
        faker = new Faker();
        random = new Random();

        Allure.step("Dang nhap vao he thong");

        loginPage.clickAcceptPolicy();
        loginPage.Login("thuongnth.fastdo@gmail.com","thuong@123");
        tenSanPham="";
        soLuong=1;
        sosao=0;
        // Lấy thời gian hiện tại
        LocalDateTime now = LocalDateTime.now();
        // Định dạng theo "dd/MM/yyyy HH:mm"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        thoiGian = now.format(formatter);
        nguoiMua = "Nguyễn Trần Hoài Thương";
        trangThai = "Chờ xử lý";

        muaSanPham = new DonHang();
        muaSanPham.setTenSanPham(tenSanPham);
        muaSanPham.setSoLuong(soLuong);
        muaSanPham.setSaoDoi(sosao);
        muaSanPham.setThoiGian(thoiGian);
        muaSanPham.setNguoiMua(nguoiMua);
        muaSanPham.setTrangThai(trangThai);

    }
    @AfterMethod
    public void cleanUp() {
//        driver.quit();
    }
    @Test
    public void Test() {
        //Xác minh rằng thực hiện nhiệm vụ 5 không thành công khi user không đủ sao đổi quà
        Allure.step("Truy cap module Doi qua");
        homePage.clickFDoiQuaModule();
        doiQuaPage.themGioHangSanPhamDauTien();
        doiQuaPage.themGioHangSanPhamDauTien();
        doiQuaPage.themGioHangSanPhamDauTien();
        doiQuaPage.clickMuaNgayButton();
        softAssert.assertTrue(doiQuaPage.isErrorMessageChecked());
        softAssert.assertEquals(doiQuaPage.getErrorMessage(), "Bạn không đủ sao để đổi các quà này!");
        doiQuaPage.closeErrorMessage();
        softAssert.assertFalse(doiQuaPage.isCheckMark(5), "Đã đánh dấu hoành thành nhiệm vụ");
        softAssert.assertEquals(doiQuaPage.getPhanTramHoanThanh(),"80%");
        homePage.clickHelpOutLineIcon();
        softAssert.assertEquals(homePage.getPhanThuongText(), "Tặng 10 ngày dùng chính thức");
        softAssert.assertAll();
    }
}
