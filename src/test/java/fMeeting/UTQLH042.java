package fMeeting;

import com.github.javafaker.Faker;
import fMission.Model.TodolistLienKet;
import fMission.page.UserPage.HomePage;
import fMission.page.UserPage.LoginPage;
import fMission.page.UserPage.fMeetingPage;
import fMission.page.UserPage.fTodolistPage;
import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class UTQLH042 {
    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    Faker faker;
    SoftAssert softAssert;
    fMission.page.UserPage.fTodolistPage fTodolistPage;
    fMission.page.UserPage.fMeetingPage fMeetingPage;
    Random random;
    SimpleDateFormat formatter;
    TodolistLienKet themTodolistLienKet;
    String tenCongViec;
    String moTaCongViec;
    String currentDate;

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
        fMeetingPage = new fMeetingPage(driver);
        softAssert = new SoftAssert();
        faker = new Faker();
        random = new Random();

        Allure.step("Dang nhap vao he thong");
        loginPage.clickAcceptPolicy();
        loginPage.Login("thuongnth.fastdo@gmail.com","thuong@123");

        tenCongViec = "";
        moTaCongViec = "";
        themTodolistLienKet = new TodolistLienKet();
        themTodolistLienKet.setTenCongViec(tenCongViec);
        formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        currentDate = formatter.format(date);
    }
    @AfterMethod
    public void cleanUp() {
        driver.quit();
    }
    @Test
    public void Test() {
        //Xác minh rằng thực hiện nhiệm vụ 4 không thành công khi user tạo công việc theo Todolist không thành công
        Allure.step("Truy cap module Quan ly hop");
        homePage.clickFMeetingModule();
        fMeetingPage.openBangNhiemVu();
        fMeetingPage.clickArrowForwardButton(4);
        fMeetingPage.clickTaoCongViecTodolist();
        fMeetingPage.taoCongViecTodolist(tenCongViec,moTaCongViec);
        softAssert.assertTrue(fMeetingPage.isErrorMessageChecked());
        softAssert.assertEquals(fMeetingPage.getErrorMessage(),"Bạn chưa nhập tiêu đề công việc!");
        fMeetingPage.closeErrorMessage();
        fMeetingPage.clickDongPopupTaoCongViec();
        softAssert.assertFalse(fMeetingPage.isCheckMark(4),"Đã đánh dấu hoành thành nhiệm vụ");
        softAssert.assertEquals(fMeetingPage.getPhanTramHoanThanh(),"60%");
        homePage.clickHelpOutLineIcon();
        softAssert.assertEquals(homePage.getPhanThuongText(),"Tặng 6 ngày dùng chính thức");
        softAssert.assertAll();
    }
}
