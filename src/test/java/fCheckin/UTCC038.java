package fCheckin;

import com.github.javafaker.Faker;
import fMission.Model.DiaDiem;
import fMission.page.AdminPage.CauHinhPage;
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

public class UTCC038 {
    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    fCheckinPage fCheckinPage;
    DiaDiemPage diaDiemPage;
    CauHinhPage cauHinhPage;

    Faker faker;
    String tenDiaDiem;
    int khoangCach;
    String vitri;
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
        cauHinhPage = new CauHinhPage(driver);
        faker = new Faker();

        Allure.step("Dang nhap vao he thong");

        loginPage.clickAcceptPolicy();
        loginPage.Login("thuongnth.fastdo@gmail.com","thuong@123");

        tenDiaDiem = faker.address().fullAddress();
        khoangCach = faker.number().numberBetween(10, 51);
        vitri = "26WQ+XQ Ngũ Hành Sơn, Đà Nẵng, Việt Nam";

        themDiaDiem = new DiaDiem();
        themDiaDiem.setTenDiaDiem(tenDiaDiem);
        themDiaDiem.setKhoangCach(khoangCach);

    }
    @AfterMethod
    public void cleanUp() {
        driver.quit();
    }
    @Test
    public void Test() throws InterruptedException {
        //Xác minh rằng không hiển thị thông báo hoàn thành nhiệm vụ khi user thực hiện lại nhiệm vụ 1
        Allure.step("Truy cap module Cau hinh");
        homePage.clickFConfigsModule();
        cauHinhPage.clickMenu("Chấm công");
        cauHinhPage.clickSubMenu("Địa điểm");
        Allure.step("Click button Them moi");
        diaDiemPage.clickThemMoiDiaDiemButton();
        Allure.step("Them dia diem");
        diaDiemPage.themDiaDiem(tenDiaDiem, khoangCach, vitri);
        softAssert.assertTrue(diaDiemPage.isToastMessageChecked());
        softAssert.assertEquals(diaDiemPage.toastMessageText(), "Tạo địa điểm chấm công thành công!");
        diaDiem = diaDiemPage.getDiaDiemByIndex(1);
        softAssert.assertEquals(diaDiem,themDiaDiem);
        softAssert.assertAll();
    }
}




