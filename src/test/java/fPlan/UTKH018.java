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

public class UTKH018 {
    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    fMission.page.UserPage.fPlanPage fPlanPage;
    Faker faker;
    SoftAssert softAssert;
    Random random;
    String tenKeHoach;

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

        tenKeHoach = "";
    }
    @AfterMethod
    public void cleanUp() {
        driver.quit();
    }
    @Test
    public void Test() {
        //Xác minh rằng thực hiện nhiệm vụ 1 thất bại khi user tạo mới kế hoạch không thành công
        Allure.step("Truy cap module Ke hoach");
        homePage.clickFPlanModule();
        fPlanPage.openBangNhiemVu();
        fPlanPage.clickTaoKeHoachButton();
        fPlanPage.taoKeHoach(tenKeHoach);
        softAssert.assertTrue(fPlanPage.isErrorMessageChecked());
        softAssert.assertEquals(fPlanPage.getErrorMessage(),"Bạn chưa nhập tiêu đề kế hoạch.");
        fPlanPage.closeErrorMessage();
        fPlanPage.closePopupTaoKeHoach();
        softAssert.assertFalse(fPlanPage.isCheckMark(1),"Đã đánh dấu hoành thành nhiệm vụ");
        softAssert.assertEquals(fPlanPage.getPhanTramHoanThanh(),"0%");
        homePage.clickHelpOutLineIcon();
        softAssert.assertEquals(homePage.getPhanThuongText(),"");
        softAssert.assertAll();
    }
}
