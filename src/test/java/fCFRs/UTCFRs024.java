package fCFRs;

import com.github.javafaker.Faker;
import fMission.Model.TieuChiGhiNhan;
import fMission.page.AdminPage.TieuChiGhiNhanPage;
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

public class UTCFRs024 {
    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    TieuChiGhiNhanPage tieuChiGhiNhanPage;
    GhiNhanPage ghiNhanPage;
    Faker faker;
    SoftAssert softAssert;
    Random random;
    String tieuchi;
    int sosao;

    TieuChiGhiNhan themtieuChi;
    TieuChiGhiNhan tieuChiGhiNhan;

    @BeforeMethod
    public void initData() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options); // Khởi tạo driver trước

        driver.get("http://localhost:5000/");
        driver.manage().window().maximize();

        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        tieuChiGhiNhanPage = new TieuChiGhiNhanPage(driver);
        ghiNhanPage = new GhiNhanPage(driver);

        softAssert = new SoftAssert();
        faker = new Faker();
        random = new Random();

        Allure.step("Dang nhap vao he thong");

        loginPage.clickAcceptPolicy();
        loginPage.Login("thuongnth.fastdo@gmail.com","thuong@123");

        tieuchi = faker.job().title();
        sosao = random.nextInt(500-201+1)+201;
        themtieuChi = new TieuChiGhiNhan();
        themtieuChi.setTieuChi(tieuchi);
        themtieuChi.setSoSao(sosao);

    }
    @AfterMethod
    public void cleanUp() {
        driver.quit();
    }
    @Test
    public void Test() {
        Allure.step("Truy cap module Ghi nhan");
        homePage.clickFGhiNhanModule();
        ghiNhanPage.clickArrowForwardButton(2);
        tieuChiGhiNhanPage.clickThemMoiTieuChi();
        tieuChiGhiNhanPage.themMoiTieuChiGhiNhan(tieuchi,sosao);
        softAssert.assertTrue(tieuChiGhiNhanPage.isToastMessageChecked());
        softAssert.assertEquals(tieuChiGhiNhanPage.toastMessageText(),"Chúc mừng bạn đã hoàn thành nhiệm vụ!");
        tieuChiGhiNhanPage.closeToastMessage();
        tieuChiGhiNhan = tieuChiGhiNhanPage.getTieuChiGhiNhan();
        softAssert.assertTrue(tieuChiGhiNhanPage.isCheckMark(2),"Chưa đánh dấu hoành thành nhiệm vụ");
        softAssert.assertEquals(tieuChiGhiNhanPage.getPhanTramHoanThanh(),"40%");
        softAssert.assertEquals(tieuChiGhiNhan, themtieuChi);
        homePage.clickHelpOutLineIcon();
        softAssert.assertEquals(homePage.getPhanThuongText(),"Tặng 3 ngày dùng chính thức");
        softAssert.assertAll();
        tieuChiGhiNhanPage.xoaTieuChiVuaTao(tieuchi,sosao);
    }
}
