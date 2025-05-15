package fCheckin;

import com.github.javafaker.Faker;
import fMission.Model.CaLam;
import fMission.page.AdminPage.CaLamPage;
import fMission.page.AdminPage.CauHinhPage;
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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class UTCC040 {
    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    fMission.page.UserPage.fCheckinPage fCheckinPage;
    Faker faker;
    SoftAssert softAssert;
    CaLamPage caLamPage;
    CauHinhPage cauHinhPage;

    String tenCaLam;
    String giobd;
    String giokt;
    String ngayTao;

    CaLam themCaLam;
    CaLam caLam;
    LocalDate currentDate;


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
        cauHinhPage = new CauHinhPage(driver);
        softAssert = new SoftAssert();
        faker = new Faker();

        tenCaLam = faker.job().title();
        Random random = new Random();
        // Tạo giờ ngẫu nhiên từ 0 đến 23
        int hour = random.nextInt(21);
        // Tạo phút ngẫu nhiên từ 0 đến 59
        int minute = random.nextInt(60);
        giobd = String.format("%02d:%02d", hour, minute);
        giokt = String.format("%02d:%02d", (hour+2), minute);

        currentDate = LocalDate.now();
        // Định dạng ngày theo kiểu dd/MM/yyyy
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        // Chuyển đổi ngày hiện tại thành chuỗi theo định dạng
        ngayTao = currentDate.format(formatter);

        themCaLam = new CaLam();
        themCaLam.setTenCaLam(tenCaLam);
        themCaLam.setThoiGianLamViec(giobd + " - " + giokt);
        themCaLam.setSoCong(1);
        themCaLam.setNgayTao(ngayTao);

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
        //Xác minh rằng không hiển thị thông báo hoàn thành nhiệm vụ khi user thực hiện lại nhiệm vụ 3
        Allure.step("Truy cap module Cau hinh");
        homePage.clickFConfigsModule();
        cauHinhPage.clickMenu("Chấm công");
        cauHinhPage.clickSubMenu("Ca làm");
        caLamPage.clickThemMoiButton();
        caLamPage.themCaLam(tenCaLam,giobd,giokt,1);
        softAssert.assertTrue(caLamPage.isToastMessageChecked());
        softAssert.assertEquals(caLamPage.toastMessageText(),"Tạo ca làm thành công!");
        caLam = caLamPage.getCaLamByIndex(1);
        softAssert.assertEquals(caLam,themCaLam);
        softAssert.assertAll();
    }
}


