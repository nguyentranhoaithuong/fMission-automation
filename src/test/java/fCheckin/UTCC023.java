package fCheckin;

import com.github.javafaker.Faker;
import fMission.Model.DiaDiem;
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

public class UTCC023 {
    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    fMission.page.UserPage.fCheckinPage fCheckinPage;
    DiaDiemPage diaDiemPage;
    Faker faker;
    SoftAssert softAssert;
    PhanDiaDiemPage phanDiaDiemPage;
    DiaDiem diaDiem;
    String phanthuongbf;

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
        phanDiaDiemPage = new PhanDiaDiemPage(driver);
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
    public void Test() {
        Allure.step("Truy cap module Cham cong");
        homePage.clickFCheckInModule();
        homePage.clickHelpOutLineIcon();
        phanthuongbf = homePage.getPhanThuongText();
        Allure.step("Click button Thuc hien ngay");
        fCheckinPage.clickArrowForwardButton(2);
        phanDiaDiemPage.clickEmployee("Nguyễn Trần Hoài  Thương");
        phanDiaDiemPage.clickCapNhatButton();
        softAssert.assertTrue(phanDiaDiemPage.isToastMessageChecked());
        softAssert.assertEquals(phanDiaDiemPage.toastMessageText(),"Đã cập nhật phân địa điểm thành công!");
        phanDiaDiemPage.closeToastMessage();
        softAssert.assertTrue(phanDiaDiemPage.isCheckMark(2),"Đã đánh dấu hoành thành nhiệm vụ");
        softAssert.assertEquals(phanDiaDiemPage.getPhanTramHoanThanh(),"20%");
        softAssert.assertEquals(phanDiaDiemPage.getViTriChamCongStatusByName("Nguyễn Trần Hoài Thương"),"Chưa phân vị trí");
        homePage.clickHelpOutLineIcon();
        softAssert.assertEquals(homePage.getPhanThuongText(),"Tặng 1 ngày dùng chính thức");
        softAssert.assertAll();
    }
}
