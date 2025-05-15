package fWorkflow;

import com.github.javafaker.Faker;
import fMission.Model.DanhGia;
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

import java.text.SimpleDateFormat;
import java.util.Random;

public class UTWF018 {
    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    fMission.page.UserPage.fWorkflowPage fWorkflowPage;
    Faker faker;
    SoftAssert softAssert;
    Random random;
    String tenQuyTrinh;

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

        tenQuyTrinh = "";
    }
    @AfterMethod
    public void cleanUp() {
        driver.quit();
    }
    @Test
    public void Test() {
        //Xác minh rằng thực hiện nhiệm vụ 1 thất bại khi user tạo mới quy trình không thành công
        Allure.step("Truy cap module Ke hoach");
        homePage.clickFWorkflowModule();
        fWorkflowPage.clickNavigateBeforeButton();
        fWorkflowPage.clickTaoQuyTrinhButton();
        fWorkflowPage.taoQuyTrinh(tenQuyTrinh);
        softAssert.assertTrue(fWorkflowPage.isErrorMessageChecked());
        softAssert.assertEquals(fWorkflowPage.getErrorMessage(),"Bạn chưa nhập Tên quy trình!");
        fWorkflowPage.closeErrorMessage();
        fWorkflowPage.closePopupTaoQuyTrinh();
        fWorkflowPage.openBangNhiemVu();
        softAssert.assertFalse(fWorkflowPage.isCheckMark(1),"Đã đánh dấu hoành thành nhiệm vụ");
        softAssert.assertEquals(fWorkflowPage.getPhanTramHoanThanh(),"0%");
        homePage.clickHelpOutLineIcon();
        softAssert.assertEquals(homePage.getPhanThuongText(),"");
        softAssert.assertAll();
    }
}
