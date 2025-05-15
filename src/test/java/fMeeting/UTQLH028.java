package fMeeting;

import com.github.javafaker.Faker;
import fMission.Model.CongViecTodolist;
import fMission.page.UserPage.*;
import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Random;

public class UTQLH028 {
    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    Faker faker;
    SoftAssert softAssert;
    fMission.page.UserPage.fTodolistPage fTodolistPage;
    fMeetingPage fMeetingPage;
    Random random;

    String tenCuocHop;

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

        tenCuocHop = faker.job().title();
    }
    @AfterMethod
    public void cleanUp() {
        driver.quit();
    }
    @Test
    public void Test() {
        //Xác minh rằng thực hiện nhiệm vụ 1 thành công khi user tạo cuộc họp mới thành công
        Allure.step("Truy cap module Quan ly hop");
        homePage.clickFMeetingModule();
        fMeetingPage.clickTaoCuocHopButton();
        fMeetingPage.enterTieuDeCuocHop(tenCuocHop);
        fMeetingPage.clickThemPhienHopButton();
        fMeetingPage.clickThemPhienHopButton();
        fMeetingPage.clickPhienHop(1);
        fMeetingPage.selectPhienHop("Vấn đề - Giải pháp");
        fMeetingPage.clickPhienHop(2);
        fMeetingPage.selectPhienHop("Công việc");
        fMeetingPage.clickCapNhatButton();
        softAssert.assertTrue(fMeetingPage.isToastMessageChecked());
        softAssert.assertEquals(fMeetingPage.toastMessageText(),"Chúc mừng bạn đã hoàn thành nhiệm vụ!");
        fMeetingPage.closeToastMessage();
        softAssert.assertEquals(fMeetingPage.getTieuDeCuocHop(),tenCuocHop);
        softAssert.assertTrue(fMeetingPage.isCheckMark(1),"Chưa đánh dấu hoành thành nhiệm vụ");
        softAssert.assertEquals(fMeetingPage.getPhanTramHoanThanh(),"20%");
        homePage.clickHelpOutLineIcon();
        softAssert.assertEquals(homePage.getPhanThuongText(),"Tặng 1 ngày dùng chính thức");
        softAssert.assertAll();
    }
}
