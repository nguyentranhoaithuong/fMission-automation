package fMeeting;

import com.github.javafaker.Faker;
import fMission.Model.CongViecTodolist;
import fMission.Model.VanDe;
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

public class UTQLH033 {
    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    Faker faker;
    SoftAssert softAssert;
    fMission.page.UserPage.fTodolistPage fTodolistPage;
    fMission.page.UserPage.fMeetingPage fMeetingPage;
    VanDe vande;
    VanDe themVanDe;
    Random random;

    String tenVanDe;
    String noiDung;

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

        tenVanDe = faker.company().buzzword();
        noiDung = faker.lorem().paragraph();

        themVanDe = new VanDe();
        themVanDe.setTenVanDe(tenVanDe);
        themVanDe.setNoiDung(noiDung);

    }
    @AfterMethod
    public void cleanUp() {
        driver.quit();
    }
    @Test
    public void Test() {
        //Xác minh rằng thực hiện nhiệm vụ 2 thành công khi user tạo mới vấn đề thành công
        Allure.step("Truy cap module Quan ly hop");
        homePage.clickFMeetingModule();
        fMeetingPage.clickArrowForwardButton(2);
        fMeetingPage.clickTaoVanDeButton();
        fMeetingPage.taoVanDe(tenVanDe, noiDung);
        vande = fMeetingPage.getVanDeByIndex(1);
        softAssert.assertTrue(fMeetingPage.isToastMessageChecked());
        softAssert.assertEquals(fMeetingPage.toastMessageText(),"Chúc mừng bạn đã hoàn thành nhiệm vụ!");
        fMeetingPage.closeToastMessage();
        softAssert.assertEquals(vande,themVanDe);
        softAssert.assertTrue(fMeetingPage.isCheckMark(2),"Chưa đánh dấu hoành thành nhiệm vụ");
        softAssert.assertEquals(fMeetingPage.getPhanTramHoanThanh(),"40%");
        homePage.clickHelpOutLineIcon();
        softAssert.assertEquals(homePage.getPhanThuongText(),"Tặng 3 ngày dùng chính thức");
        softAssert.assertAll();
    }
}
