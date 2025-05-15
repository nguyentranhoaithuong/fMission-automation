package fWorkflow;

import com.github.javafaker.Faker;
import fMission.page.UserPage.HomePage;
import fMission.page.UserPage.LoginPage;
import fMission.page.UserPage.fWorkflowPage;
import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Random;

public class UTWF029 {
    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    fMission.page.UserPage.fWorkflowPage fWorkflowPage;
    Faker faker;
    SoftAssert softAssert;
    Random random;

    String tenCongViec;
    String nguoiThucHien;
    int thoigian;

    @BeforeMethod
    public void initData() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options); // Khởi tạo driver trước

        driver.get("http://localhost:5000/");
        driver.manage().window().maximize();

        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        fWorkflowPage = new fWorkflowPage(driver);
        softAssert = new SoftAssert();
        faker = new Faker();
        random = new Random();

        Allure.step("Dang nhap vao he thong");

        loginPage.clickAcceptPolicy();
        loginPage.Login("thuongnth.fastdo@gmail.com","thuong@123");
        tenCongViec = faker.company().buzzword();
        nguoiThucHien = "Nguyễn Trần Hoài Thương";
        thoigian = 1;

    }
    @AfterMethod
    public void cleanUp() {
        driver.quit();
    }
    @Test
    public void Test() {
        //Xác minh rằng thực hiện nhiệm vụ 3 thành công khi user kéo thả thẻ và xuất bản quy trình
        Allure.step("Truy cap module Ke hoach");
        homePage.clickFWorkflowModule();
        fWorkflowPage.openBangNhiemVu();
        fWorkflowPage.clickArrowForwardButton(3);
        fWorkflowPage.keoThaCongViec();
        fWorkflowPage.clickXuatBanButton();
        softAssert.assertTrue(fWorkflowPage.isToastMessageChecked());
        softAssert.assertEquals(fWorkflowPage.toastMessageText(),"Chúc mừng bạn đã hoàn thành nhiệm vụ!");
        fWorkflowPage.closeToastMessage();
        fWorkflowPage.clickThuGonButton();
        fWorkflowPage.openBangNhiemVu();
        softAssert.assertTrue(fWorkflowPage.isCheckMark(3),"Chưa đánh dấu hoành thành nhiệm vụ");
        softAssert.assertEquals(fWorkflowPage.getPhanTramHoanThanh(),"60%");
        homePage.clickHelpOutLineIcon();
        softAssert.assertEquals(homePage.getPhanThuongText(),"Tặng 6 ngày dùng chính thức");
        softAssert.assertAll();
    }
}
