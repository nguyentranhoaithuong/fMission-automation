package fCheckin;

import com.github.javafaker.Faker;
import fMission.page.AdminPage.CauHinhPage;
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

public class UTCC039 {
    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    fMission.page.UserPage.fCheckinPage fCheckinPage;
    Faker faker;
    SoftAssert softAssert;
    PhanDiaDiemPage phanDiaDiemPage;
    CauHinhPage cauHinhPage;
    String tenDiaDiembf;

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
        cauHinhPage = new CauHinhPage(driver);
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
        //Xác minh rằng không hiển thị thông báo hoàn thành nhiệm vụ khi user thực hiện lại nhiệm vụ 2
        Allure.step("Truy cap module Cau hinh");
        homePage.clickFConfigsModule();
        cauHinhPage.clickMenu("Chấm công");
        cauHinhPage.clickSubMenu("Phân địa điểm");
        phanDiaDiemPage.clickEmployee("Vận hành Fastdo");
        tenDiaDiembf=phanDiaDiemPage.getTenDiaDiemDauTienPopup();
        phanDiaDiemPage.clickFirstLocation();
        phanDiaDiemPage.clickCapNhatButton();
        softAssert.assertTrue(phanDiaDiemPage.isToastMessageChecked());
        softAssert.assertEquals(phanDiaDiemPage.toastMessageText(),"Đã cập nhật phân địa điểm thành công!");
        softAssert.assertEquals(phanDiaDiemPage.getViTriChamCongStatusByName("Vận hành Fastdo"),tenDiaDiembf);
        softAssert.assertAll();
    }
}


