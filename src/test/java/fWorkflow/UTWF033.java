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

public class UTWF033 {
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

    }
    @AfterMethod
    public void cleanUp() {
        driver.quit();
    }
    @Test
    public void Test() {
        //Xác minh rằng thực hiện nhiệm vụ 5 thất bại khi user xử lý công việc không thành công
        Allure.step("Truy cap module Ke hoach");
        homePage.clickFWorkflowModule();
        fWorkflowPage.openBangNhiemVu();
        fWorkflowPage.clickArrowForwardButton(5);
        softAssert.assertTrue(fWorkflowPage.isXuLyCongViecPopupDisplayed(),"không hiển thị popup xử lý cong việc!");
        fWorkflowPage.clickHoanThanhButton();
        softAssert.assertTrue(fWorkflowPage.isErrorMessageChecked());
        softAssert.assertEquals(fWorkflowPage.getErrorMessage(),"Bạn chưa chọn bước tiếp theo!");
        fWorkflowPage.closeErrorMessage();
//        fWorkflowPage.dongPopupXulyCongViec();
        fWorkflowPage.closeXuLyNhiemVuPopup();
        softAssert.assertTrue(fWorkflowPage.isTrangThaiDangXuLyDisplayed(nguoiThucHien));
        fWorkflowPage.openBangNhiemVu();
        softAssert.assertFalse(fWorkflowPage.isCheckMark(5),"Đã đánh dấu hoành thành nhiệm vụ");
        softAssert.assertEquals(fWorkflowPage.getPhanTramHoanThanh(),"80%");
        homePage.clickHelpOutLineIcon();
        softAssert.assertEquals(homePage.getPhanThuongText(),"Tặng 10 ngày dùng chính thức");
        softAssert.assertAll();
    }
}
