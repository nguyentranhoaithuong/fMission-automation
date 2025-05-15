package fCheckin;

import com.github.javafaker.Faker;
import fMission.page.AdminPage.CaLamPage;
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

public class UTCC041 {
    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    fMission.page.UserPage.fCheckinPage fCheckinPage;
    Faker faker;
    SoftAssert softAssert;
    CaLamPage caLamPage;


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
        caLamPage = new CaLamPage(driver);
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
        //Xác minh rằng không hiển thị thông báo hoàn thành nhiệm vụ khi user thực hiện lại nhiệm vụ 4
        Allure.step("Truy cap module Cham cong");
        homePage.clickFCheckInModule();
        Allure.step("Mo trang Phan Ca");
        fCheckinPage.clickPhanCaMenu();
        fCheckinPage.clickPhanCaSubMenu();
        fCheckinPage.clickPhanCaButton();
        fCheckinPage.searchNhanVien("nguyễn trần hoài thương");
        fCheckinPage.clickCheckBoxNhanVien(1);
        fCheckinPage.clickTiepTucButton();
        fCheckinPage.clickFirstCaLam();
        fCheckinPage.clickApDungButton();
        softAssert.assertTrue(fCheckinPage.isToastMessageChecked());
        softAssert.assertEquals(fCheckinPage.toastMessageText(),"Chúc mừng bạn đã hoàn thành nhiệm vụ!");
        softAssert.assertAll();
    }
}

