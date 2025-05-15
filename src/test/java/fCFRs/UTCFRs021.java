package fCFRs;

import com.github.javafaker.Faker;
import fMission.Model.LichSuCapSao;
import fMission.page.AdminPage.CapSaoPage;
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

public class UTCFRs021 {
    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    CapSaoPage capSaoPage;
    GhiNhanPage ghiNhanPage;
    Faker faker;
    SoftAssert softAssert;
    Random random;
    String ten;
    String phongban;
    String capbac;
    String vi;
    String thoigian;
    String nguoiThucHien;
    int sosao;

    LichSuCapSao lichSuCapSao;
    LichSuCapSao capSaoNhanVien;

    @BeforeMethod
    public void initData() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options); // Khởi tạo driver trước

        driver.get("http://localhost:5000/");
        driver.manage().window().maximize();

        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        capSaoPage = new CapSaoPage(driver);
        ghiNhanPage = new GhiNhanPage(driver);

        softAssert = new SoftAssert();
        faker = new Faker();
        random = new Random();

        Allure.step("Dang nhap vao he thong");

        loginPage.clickAcceptPolicy();
        loginPage.Login("thuongnth.fastdo@gmail.com","thuong@123");

        thoigian ="";
        ten = "Nguyễn Trần Hoài Thương";
        phongban = "";
        capbac = "";
        vi = "Ghi nhận";
        sosao = random.nextInt(50);
        nguoiThucHien = "Nguyễn Trần Hoài Thương";

        capSaoNhanVien = new LichSuCapSao();
        capSaoNhanVien.setThoiGian(thoigian);
        capSaoNhanVien.setTen(ten);
        capSaoNhanVien.setPhongBan(phongban);
        capSaoNhanVien.setCapBac(capbac);
        capSaoNhanVien.setLoaiVi(vi);
        capSaoNhanVien.setSosao(sosao);
        capSaoNhanVien.setNguoiThucHien(nguoiThucHien);
    }
    @AfterMethod
    public void cleanUp() {
        driver.quit();
    }
    @Test
    public void Test() {
        Allure.step("Truy cap module Ghi nhan");
        homePage.clickFGhiNhanModule();
        ghiNhanPage.clickArrowForwardButton(1);
        capSaoPage.clickCapSaoButton();
        capSaoPage.selectUserCapSaoByName(ten);
        capSaoNhanVien.setPhongBan(capSaoPage.getPhongBanCuaNhanVienDuocChon(ten));
        capSaoNhanVien.setCapBac(capSaoPage.getCapBacCuaNhanVienDuocChon(ten));
        capSaoPage.enterSoSao(sosao);
        capSaoPage.selectViCapSao(vi);
        capSaoPage.clickCapNhatCapSaoButton();
        capSaoPage.clickXacNhanCapSao();
        capSaoNhanVien.setThoiGian(capSaoPage.thoiGianHienTai());
        softAssert.assertTrue(capSaoPage.isToastMessageChecked());
        softAssert.assertEquals(capSaoPage.toastMessageText(),"Chúc mừng bạn đã hoàn thành nhiệm vụ!");
        capSaoPage.closeToastMessage();
        lichSuCapSao = capSaoPage.getLichSuCapSao();
        softAssert.assertEquals(lichSuCapSao, capSaoNhanVien);
        softAssert.assertTrue(capSaoPage.isCheckMark(1),"Chưa đánh dấu hoành thành nhiệm vụ");
        softAssert.assertEquals(capSaoPage.getPhanTramHoanThanh(),"20%");
        homePage.clickHelpOutLineIcon();
        softAssert.assertEquals(homePage.getPhanThuongText(),"Tặng 1 ngày dùng chính thức");
        softAssert.assertAll();
    }
}
