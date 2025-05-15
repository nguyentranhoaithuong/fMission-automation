package fMeeting;

import com.github.javafaker.Faker;
import fMission.Model.DanhGia;
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
import java.util.Random;

public class UTQLH048 {
    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    Faker faker;
    SoftAssert softAssert;
    fMission.page.UserPage.fTodolistPage fTodolistPage;
    fMission.page.UserPage.fMeetingPage fMeetingPage;
    Random random;
    DanhGia themDanhGia;
    DanhGia danhGiaCuocHop;

    String danhGia;
    int diem;

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

        diem = random.nextInt(100);
        danhGia = faker.lorem().sentence();
        themDanhGia = new DanhGia();
        themDanhGia.setDiem(diem);
        themDanhGia.setDanhGia(danhGia);
    }
    @AfterMethod
    public void cleanUp() {
        driver.quit();
    }
    @Test
    public void Test() {
        //Xác minh rằng thực hiện nhiệm vụ 5 thành công khi user thêm mới đánh giá thành công
        Allure.step("Truy cap module Quan ly hop");
        homePage.clickFMeetingModule();
        fMeetingPage.openBangNhiemVu();
        fMeetingPage.clickArrowForwardButton(5);
        fMeetingPage.taoDanhGia(diem,danhGia);
        softAssert.assertTrue(fMeetingPage.isCouPonPopupDisplayed());
        fMeetingPage.closeCouponPopup();
        softAssert.assertTrue(fMeetingPage.isToastMessageChecked());
        softAssert.assertEquals(fMeetingPage.toastMessageText(),"Chúc mừng bạn đã hoàn thành nhiệm vụ!");
        fMeetingPage.closeToastMessage();
        danhGiaCuocHop = fMeetingPage.getDanhGiaCuocHop();
        softAssert.assertEquals(danhGiaCuocHop, themDanhGia);
        softAssert.assertTrue(fMeetingPage.isCheckMark(5),"Chưa đánh dấu hoành thành nhiệm vụ");
        softAssert.assertEquals(fMeetingPage.getPhanTramHoanThanh(),"100%");
        homePage.clickHelpOutLineIcon();
        softAssert.assertEquals(homePage.getPhanThuongText(),"Tặng 10 ngày dùng chính thức Tặng 1 Voucher giảm giá 5%");
        softAssert.assertAll();
    }
}
