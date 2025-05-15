package fCheckin;

import com.github.javafaker.Faker;
import fMission.Model.DiaDiem;
import fMission.page.AdminPage.DiaDiemPage;
import fMission.page.UserPage.HomePage;
import fMission.page.UserPage.LoginPage;
import fMission.page.UserPage.fCheckinPage;
import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class UTCC018 {
    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    fMission.page.UserPage.fCheckinPage fCheckinPage;
    DiaDiemPage diaDiemPage;
    Faker faker;
    SoftAssert softAssert;
    DiaDiem themDiaDiem;
    DiaDiem diaDiem;

    @BeforeMethod
    public void initData() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options); // Khởi tạo driver trước
        driver.get("http://localhost:5000/");
        driver.manage().window().maximize();

        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        fCheckinPage = new fCheckinPage(driver);
        diaDiemPage = new DiaDiemPage(driver);
        softAssert = new SoftAssert();
        diaDiem = new DiaDiem();
        faker = new Faker();

        Allure.step("Dang nhap vao he thong");
        loginPage.clickAcceptPolicy();
        loginPage.Login("thuongnth.fastdo@gmail.com","thuong@123");

    }
    @AfterMethod
    public void cleanUp() {
        driver.quit();
    }
    @Test
    public void Test() throws InterruptedException {
        Allure.step("Truy cap module Cham cong");
        homePage.clickFCheckInModule();
        Allure.step("Click button Thuc hien ngay");
        fCheckinPage.clickArrowForwardButton(1);
        Allure.step("Click button Them moi");
        diaDiemPage.clickThemMoiDiaDiemButton();
        Allure.step("Them dia diem");
        diaDiemPage.clickCapNhatButton();
        softAssert.assertTrue(diaDiemPage.isErrorMessageChecked());
        softAssert.assertEquals(diaDiemPage.getErrorMessage(),"Bạn chưa nhập tên địa điểm!");
        diaDiemPage.closeErrorMessage();
        diaDiemPage.clickXIcon();
        softAssert.assertTrue(diaDiemPage.isArrowForwardButtonChecked(1),"Khong hien thi Arrow Forward button");
        softAssert.assertFalse(diaDiemPage.isCheckMark(1),"Đã đánh dấu hoành thành nhiệm vụ");
        softAssert.assertEquals(diaDiemPage.getPhanTramHoanThanh(),"0%");
        homePage.clickHelpOutLineIcon();
        softAssert.assertEquals(homePage.getPhanThuongText(),"");
        softAssert.assertAll();
    }
}
