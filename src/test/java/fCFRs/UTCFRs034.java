package fCFRs;

import com.github.javafaker.Faker;
import fMission.Model.CuaHang;
import fMission.page.AdminPage.CuaHangPage;
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

import java.util.Random;

public class UTCFRs034 {
    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    GhiNhanPage ghiNhanPage;
    CuaHangPage cuaHangPage;
    Faker faker;
    SoftAssert softAssert;
    Random random;
    String tenSanPham;
    String danhMuc;
    int sosao;
    String moTa;

    CuaHang taoSanPham;
    CuaHang cuaHang;


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
        cuaHangPage = new CuaHangPage(driver);

        softAssert = new SoftAssert();
        faker = new Faker();
        random = new Random();

        Allure.step("Dang nhap vao he thong");

        loginPage.clickAcceptPolicy();
        loginPage.Login("thuongnth.fastdo@gmail.com","thuong@123");

        tenSanPham = faker.commerce().productName();
        danhMuc = "Khác";
        sosao = random.nextInt(50);
        moTa = faker.lorem().paragraph();

        taoSanPham = new CuaHang();
        taoSanPham.setTenSanPham(tenSanPham);
        taoSanPham.setDanhMuc(danhMuc);
        taoSanPham.setSoSao(sosao);
        taoSanPham.setMoTa(moTa);

    }
    @AfterMethod
    public void cleanUp() {
//        driver.quit();
    }
    @Test
    public void Test() {
        Allure.step("Truy cap module Ghi nhan");
        homePage.clickFGhiNhanModule();
        ghiNhanPage.clickArrowForwardButton(4);
        cuaHangPage.clickThemMoiButton();
        cuaHangPage.themMoiSanPham(tenSanPham, danhMuc,sosao, moTa);
        softAssert.assertTrue(cuaHangPage.isToastMessageChecked());
        softAssert.assertEquals(cuaHangPage.toastMessageText(), "Chúc mừng bạn đã hoàn thành nhiệm vụ!");
        cuaHangPage.closeToastMessage();
        cuaHang = cuaHangPage.getSanPhamVuaTao();
        softAssert.assertTrue(cuaHangPage.isCheckMark(4), "Chưa đánh dấu hoành thành nhiệm vụ");
        softAssert.assertEquals(cuaHangPage.getPhanTramHoanThanh(),"80%");
        softAssert.assertEquals(cuaHang, taoSanPham);
        homePage.clickHelpOutLineIcon();
        softAssert.assertEquals(homePage.getPhanThuongText(), "Tặng 10 ngày dùng chính thức");
        softAssert.assertAll();
    }
}
