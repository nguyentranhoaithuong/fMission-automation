package fPlan;

import com.github.javafaker.Faker;
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

import java.util.Random;

public class UTKH026 {
    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    fMission.page.UserPage.fPlanPage fPlanPage;
    Faker faker;
    SoftAssert softAssert;
    Random random;
    String binhLuan;
    String nguoiThucHien;

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

        binhLuan = "";
        nguoiThucHien = "Nguyễn Trần Hoài Thương";
    }
    @AfterMethod
    public void cleanUp() {
        driver.quit();
    }
    @Test
    public void Test() {
        //Xác minh rằng thực hiện nhiệm vụ 5 thành công khi user bình luận thành công
        Allure.step("Truy cap module Ke hoach");
        homePage.clickFPlanModule();
        fPlanPage.openBangNhiemVu();
        fPlanPage.clickArrowForwardButton(5);
        fPlanPage.taoBinhLuan(binhLuan);
        softAssert.assertTrue(fPlanPage.isErrorMessageChecked());
        softAssert.assertEquals(fPlanPage.getErrorMessage(), "Bạn chưa nhập nội dung bình luận!");
        fPlanPage.closeErrorMessage();
        fPlanPage.clickDongChiTietCongViec();
        fPlanPage.openBangNhiemVu();
        softAssert.assertFalse(fPlanPage.isCheckMark(5),"Đã đánh dấu hoành thành nhiệm vụ");
        softAssert.assertEquals(fPlanPage.getPhanTramHoanThanh(),"80%");
        homePage.clickHelpOutLineIcon();
        softAssert.assertEquals(homePage.getPhanThuongText(),"Tặng 10 ngày dùng chính thức");
        softAssert.assertAll();
    }
}
