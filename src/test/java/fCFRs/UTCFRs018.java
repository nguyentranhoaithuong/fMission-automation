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

public class UTCFRs018 {
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
        capSaoPage.enterSoSao(sosao);
        capSaoPage.selectViCapSao(vi);
        capSaoPage.clickCapNhatCapSaoButton();
        softAssert.assertTrue(capSaoPage.isErrorMessageChecked());
        softAssert.assertEquals(capSaoPage.getErrorMessage(),"Bạn chưa chọn nhân viên cần cấp sao !");
        capSaoPage.closeErrorMessage();
        softAssert.assertFalse(capSaoPage.isCheckMark(1),"Đã đánh dấu hoành thành nhiệm vụ");
        softAssert.assertEquals(capSaoPage.getPhanTramHoanThanh(),"0%");
        homePage.clickHelpOutLineIcon();
        softAssert.assertEquals(homePage.getPhanThuongText(),"");
        softAssert.assertAll();
    }
}




