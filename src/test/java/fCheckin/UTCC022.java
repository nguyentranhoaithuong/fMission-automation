package fCheckin;

import com.github.javafaker.Faker;
import fMission.Model.DiaDiem;
import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import fMission.page.AdminPage.DiaDiemPage;
import fMission.page.UserPage.HomePage;
import fMission.page.UserPage.LoginPage;
import fMission.page.UserPage.fCheckinPage;

public class UTCC022 {
    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    fCheckinPage fCheckinPage;
    DiaDiemPage diaDiemPage;
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
        Allure.step("Truy cap module Cham cong");
        homePage.clickFCheckInModule();
        Allure.step("Click button Thuc hien ngay");
        fCheckinPage.clickArrowForwardButton(1);
        Allure.step("Click button Them moi");
        diaDiemPage.clickThemMoiDiaDiemButton();
        Allure.step("Them dia diem");
        diaDiemPage.themDiaDiem(tenDiaDiem,khoangCach,vitri);
        softAssert.assertTrue(diaDiemPage.isToastMessageChecked());
        diaDiem = diaDiemPage.getDiaDiemByIndex(1);
        softAssert.assertEquals(diaDiemPage.toastMessageText(),"Chúc mừng bạn đã hoàn thành nhiệm vụ!");
        diaDiemPage.closeToastMessage();
        softAssert.assertTrue(diaDiemPage.isCheckMark(1),"Chưa đánh dấu hoành thành nhiệm vụ");
        softAssert.assertEquals(diaDiemPage.getPhanTramHoanThanh(),"20%");
        homePage.clickHelpOutLineIcon();
        softAssert.assertEquals(homePage.getPhanThuongText(),"Tặng 1 ngày dùng chính thức");
        System.out.println(homePage.getPhanThuongText());
        softAssert.assertEquals(diaDiem,themDiaDiem);
        softAssert.assertAll();
    }
}
