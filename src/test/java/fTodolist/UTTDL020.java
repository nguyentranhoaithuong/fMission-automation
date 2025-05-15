package fTodolist;

import com.github.javafaker.Faker;
import fMission.Model.CongViecTodolist;
import fMission.page.UserPage.HomePage;
import fMission.page.UserPage.LoginPage;
import fMission.page.UserPage.fCheckinPage;
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

public class UTTDL020 {
    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    fMission.page.UserPage.fCheckinPage fCheckinPage;
    Faker faker;
    SoftAssert softAssert;
    fMission.page.UserPage.fTodolistPage fTodolistPage;
    CongViecTodolist congViecTodolist;
    CongViecTodolist themCongViec;
    Random random;

    String tenCongViec;
    String chitiet;
    String phanthuongbf;
    int n;

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
        softAssert = new SoftAssert();
        faker = new Faker();
        random = new Random();

        Allure.step("Dang nhap vao he thong");

        loginPage.clickAcceptPolicy();
        loginPage.Login("thuongnth.fastdo@gmail.com","thuong@123");

        tenCongViec = "";
        chitiet = "";

        themCongViec = new CongViecTodolist();
        themCongViec.setTenCongViec(tenCongViec);
        themCongViec.setChiTiet(chitiet);

        n=1;

    }
    @AfterMethod
    public void cleanUp() {
        driver.quit();
    }
    @Test
    public void Test() {
        //Xác minh rằng không hoàn thành nhiệm vụ 1 khi user tạo công việc mới không thành công ở dạng lịch
        Allure.step("Truy cap module Todolist");
        homePage.clickFTodolistModule();
        fTodolistPage.openDanhSachTab();
        fTodolistPage.openDateChuaCoCongViec();
        fTodolistPage.clickThemCongViecButton();
        fTodolistPage.themCongViec(tenCongViec,chitiet);
        softAssert.assertTrue(fTodolistPage.isErrorMessageChecked());
        softAssert.assertEquals(fTodolistPage.getErrorMessage(),"Bạn chưa nhập tên công việc!");
        fTodolistPage.closeErrorMessage();
        softAssert.assertFalse(fTodolistPage.isCheckMark(1),"Đã đánh dấu hoành thành nhiệm vụ");
        softAssert.assertEquals(fTodolistPage.getPhanTramHoanThanh(),"0%");
        homePage.clickHelpOutLineIcon();
        softAssert.assertEquals(homePage.getPhanThuongText(),"");
        softAssert.assertAll();
    }
}
