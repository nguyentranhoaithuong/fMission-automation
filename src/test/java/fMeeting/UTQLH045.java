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

public class UTQLH045 {
    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    fMission.page.UserPage.fCheckinPage fCheckinPage;
    Faker faker;
    SoftAssert softAssert;
    fMission.page.UserPage.fTodolistPage fTodolistPage;
    fMission.page.UserPage.fMeetingPage fMeetingPage;
    Random random;
    SimpleDateFormat formatter;
    TodolistLienKet themTodolistLienKet;
    TodolistLienKet todolistLienKet;
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

        tenCongViec = faker.company().buzzword();
        moTaCongViec = faker.lorem().paragraph();
        themTodolistLienKet = new TodolistLienKet();
        themTodolistLienKet.setTenCongViec(tenCongViec);

        formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        // Gán ngày hiện tại vào String
        currentDate = formatter.format(date);
    }
    @AfterMethod
    public void cleanUp() {
        driver.quit();
    }
    @Test
    public void Test() {
        //Xác minh rằng thực hiện nhiệm vụ 4 thành công khi user Tạo todolist liên kết sau cuộc họp thành công
        Allure.step("Truy cap module Quan ly hop");
        homePage.clickFMeetingModule();
        fMeetingPage.openBangNhiemVu();
        fMeetingPage.clickArrowForwardButton(4);
        fMeetingPage.clickTaoCongViecTodolist();
        fMeetingPage.taoCongViecTodolist(tenCongViec,moTaCongViec);
        softAssert.assertTrue(fMeetingPage.isToastMessageChecked());
        softAssert.assertEquals(fMeetingPage.toastMessageText(),"Chúc mừng bạn đã hoàn thành nhiệm vụ!");
        fMeetingPage.closeToastMessage();
        fMeetingPage.clickDongPopupTaoCongViec();
        todolistLienKet = fMeetingPage.getTodolistLienKet(currentDate);
        softAssert.assertEquals(todolistLienKet, themTodolistLienKet);
        softAssert.assertTrue(fMeetingPage.isCheckMark(4),"Chưa đánh dấu hoành thành nhiệm vụ");
        softAssert.assertEquals(fMeetingPage.getPhanTramHoanThanh(),"80%");
        homePage.clickHelpOutLineIcon();
        softAssert.assertEquals(homePage.getPhanThuongText(),"Tặng 10 ngày dùng chính thức");
        softAssert.assertAll();
    }
}
