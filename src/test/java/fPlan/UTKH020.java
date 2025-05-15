package fPlan;

import com.github.javafaker.Faker;
import fMission.Model.DanhGia;
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

import java.text.SimpleDateFormat;
import java.util.Random;

public class UTKH020 {
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

        tenKeHoach = faker.company().buzzword();


    }
    @AfterMethod
    public void cleanUp() {
        driver.quit();
    }
    @Test
    public void Test() {
        //Xác minh rằng thực hiện nhiệm vụ 1 thành công khi user tạo mới kế hoạch thành công
        Allure.step("Truy cap module Ke hoach");
        homePage.clickFPlanModule();
        fPlanPage.openBangNhiemVu();
        fPlanPage.clickTaoKeHoachButton();
        fPlanPage.taoKeHoach(tenKeHoach);
        softAssert.assertTrue(fPlanPage.isKeHoachVuaTaoDisplayed());
        softAssert.assertTrue(fPlanPage.isToastMessageChecked());
        softAssert.assertEquals(fPlanPage.toastMessageText(),"Chúc mừng bạn đã hoàn thành nhiệm vụ!");
        fPlanPage.closeToastMessage();
        softAssert.assertTrue(fPlanPage.isCheckMark(1),"Chưa đánh dấu hoành thành nhiệm vụ");
        softAssert.assertEquals(fPlanPage.getPhanTramHoanThanh(),"20%");
        homePage.clickHelpOutLineIcon();
        softAssert.assertEquals(homePage.getPhanThuongText(),"Tặng 1 ngày dùng chính thức");
        softAssert.assertAll();
    }
}
