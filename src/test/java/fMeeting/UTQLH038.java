package fMeeting;

import com.github.javafaker.Faker;
import fMission.Model.GiaiPhap;
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

import java.util.Random;

public class UTQLH038 {
    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    Faker faker;
    SoftAssert softAssert;
    fMission.page.UserPage.fTodolistPage fTodolistPage;
    fMission.page.UserPage.fMeetingPage fMeetingPage;
    GiaiPhap giaiPhap;
    GiaiPhap themGiaiPhap;
    Random random;

    String tenGiaiPhap;
    String noiDungGiaiPhap;

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

        tenGiaiPhap = faker.company().buzzword();
        noiDungGiaiPhap = faker.lorem().paragraph();

        themGiaiPhap = new GiaiPhap();
        themGiaiPhap.setTenGiaiPhap(tenGiaiPhap);
        themGiaiPhap.setNoiDungGiaiPhap(noiDungGiaiPhap);

    }
    @AfterMethod
    public void cleanUp() {
        driver.quit();
    }
    @Test
    public void Test() {
        //Xác minh rằng thực hiện nhiệm vụ 3 thành công khi user thêm mới giải pháp thành công
        Allure.step("Truy cap module Quan ly hop");
        homePage.clickFMeetingModule();
        fMeetingPage.openBangNhiemVu();
        fMeetingPage.clickArrowForwardButton(3);
        fMeetingPage.clickTaoMoiGiaiPhap();
        fMeetingPage.taoThongTinGiaiPhap(tenGiaiPhap, noiDungGiaiPhap);
        giaiPhap = fMeetingPage.getGiaiPhapByIndex(1);
        fMeetingPage.clickThongNhatCheckBox();
        softAssert.assertTrue(fMeetingPage.isToastMessageChecked());
        softAssert.assertEquals(fMeetingPage.toastMessageText(),"Chúc mừng bạn đã hoàn thành nhiệm vụ!");
        fMeetingPage.closeToastMessage();
        softAssert.assertEquals(giaiPhap, themGiaiPhap);
        softAssert.assertTrue(fMeetingPage.isCheckMark(3),"Chưa đánh dấu hoành thành nhiệm vụ");
        softAssert.assertEquals(fMeetingPage.getPhanTramHoanThanh(),"60%");
        homePage.clickHelpOutLineIcon();
        softAssert.assertEquals(homePage.getPhanThuongText(),"Tặng 6 ngày dùng chính thức");
        softAssert.assertAll();
    }
}
