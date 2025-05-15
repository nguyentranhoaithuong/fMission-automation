package fTodolist;

import com.github.javafaker.Faker;
import fMission.Model.CongViecTodolist;
import fMission.page.UserPage.DoiNhomPage;
import fMission.page.UserPage.HomePage;
import fMission.page.UserPage.LoginPage;
import fMission.page.UserPage.fTodolistPage;
import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Random;

public class UTTDL041 {
    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    Faker faker;
    SoftAssert softAssert;
    fMission.page.UserPage.fTodolistPage fTodolistPage;
    DoiNhomPage doiNhomPage;
    Random random;

    @BeforeMethod
    public void initData() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options); // Khởi tạo driver trước

        driver.get("http://localhost:5000/");
        driver.manage().window().maximize();

        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        fTodolistPage = new fTodolistPage(driver);
        doiNhomPage = new DoiNhomPage(driver);
        softAssert = new SoftAssert();
        faker = new Faker();
        random = new Random();

        Allure.step("Dang nhap vao he thong");

        loginPage.clickAcceptPolicy();
        loginPage.Login("thuongnth.fastdo@gmail.com","thuong@123");

    }
    @AfterMethod
    public void cleanUp() {
        driver.quit();
    }
    @Test
    public void Test() {
        //Xác minh rằng không hoàn thành nhiệm vụ 5 khi user xem todolist của chính mình
        Allure.step("Truy cap module Todolist");
        homePage.clickFTodolistModule();
        fTodolistPage.openBangNhiemVu();
        fTodolistPage.clickArrowForwardButton(5);
        doiNhomPage.clickTodolistTab();
        softAssert.assertFalse(doiNhomPage.isPopupCouponDisplayed());
        softAssert.assertFalse(doiNhomPage.isToastMessageChecked());
        homePage.clickHelpOutLineIcon();
        softAssert.assertEquals(homePage.getPhanThuongText(),"Tặng 10 ngày dùng chính thức Tặng 1 Voucher giảm giá 5%");
        doiNhomPage.backToHomePageLocator();
        homePage.clickFTodolistModule();
        fTodolistPage.openBangNhiemVu();
        softAssert.assertTrue(doiNhomPage.isCheckMark(5),"Chưa đánh dấu hoành thành nhiệm vụ");
        softAssert.assertEquals(doiNhomPage.getPhanTramHoanThanh(),"100%");
        softAssert.assertAll();
    }
}
