package fCheckin;

import com.github.javafaker.Faker;
import fMission.page.AdminPage.DiaDiemPage;
import fMission.page.AdminPage.PhanDiaDiemPage;
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

public class UTCC024 {
    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    fMission.page.UserPage.fCheckinPage fCheckinPage;
    DiaDiemPage diaDiemPage;
    Faker faker;
    String tenDiaDiembf;
    int khoangCach;
    SoftAssert softAssert;
    PhanDiaDiemPage phanDiaDiemPage;

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
        phanDiaDiemPage = new PhanDiaDiemPage(driver);
        softAssert = new SoftAssert();
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
    public void Test() {
        Allure.step("Truy cap module Cham cong");
        homePage.clickFCheckInModule();
        Allure.step("Click button Thuc hien ngay");
        fCheckinPage.clickArrowForwardButton(2);
        phanDiaDiemPage.clickEmployee("Nguyễn Trần Hoài  Thương");
        tenDiaDiembf=phanDiaDiemPage.getTenDiaDiemDauTienPopup();
        phanDiaDiemPage.clickFirstLocation();
        phanDiaDiemPage.clickCapNhatButton();
        softAssert.assertTrue(phanDiaDiemPage.isToastMessageChecked());
        softAssert.assertEquals(phanDiaDiemPage.toastMessageText(),"Chúc mừng bạn đã hoàn thành nhiệm vụ!");
        phanDiaDiemPage.closeToastMessage();
        softAssert.assertEquals(phanDiaDiemPage.getViTriChamCongStatusByName("Nguyễn Trần Hoài Thương"),tenDiaDiembf);
        softAssert.assertTrue(phanDiaDiemPage.isCheckMark(2),"Chưa đánh dấu hoành thành nhiệm vụ");
        softAssert.assertEquals(diaDiemPage.getPhanTramHoanThanh(),"40%");
        homePage.clickHelpOutLineIcon();
        softAssert.assertEquals(homePage.getPhanThuongText(),"Tặng 3 ngày dùng chính thức");
        softAssert.assertAll();
    }
}
