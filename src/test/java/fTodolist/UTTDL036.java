package fTodolist;

import com.github.javafaker.Faker;
import fMission.Model.CongViecTodolist;
import fMission.page.UserPage.HomePage;
import fMission.page.UserPage.LoginPage;
import fMission.page.UserPage.fTodolistPage;
import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Random;

public class UTTDL036 {
    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    fMission.page.UserPage.fCheckinPage fCheckinPage;
    Faker faker;
    SoftAssert softAssert;
    fMission.page.UserPage.fTodolistPage fTodolistPage;
    CongViecTodolist congViecTodolist;
    CongViecTodolist themCongViec;
    Random random;

    String tenCongViec;
    String chitiet;
    String phanthuongbf;
    int date;

    @BeforeMethod
    public void initData() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options); // Khởi tạo driver trước

        driver.get("http://localhost:5000/");
        driver.manage().window().maximize();

        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        fTodolistPage = new fTodolistPage(driver);
        softAssert = new SoftAssert();
        faker = new Faker();
        random = new Random();

        Allure.step("Dang nhap vao he thong");

        loginPage.clickAcceptPolicy();
        loginPage.Login("thuongnth.fastdo@gmail.com","thuong@123");

        tenCongViec = faker.job().title();
        chitiet = faker.lorem().paragraph();

        themCongViec = new CongViecTodolist();
        themCongViec.setTenCongViec(tenCongViec);
        themCongViec.setChiTiet(chitiet);

        date=1;

    }
    @AfterMethod
    public void cleanUp() {
        driver.quit();
    }
    @Test
    public void Test() {
        //Xác minh rằng thực hiện nhiệm vụ 3 thành công khi user Báo cáo todolist dạng lịch
        Allure.step("Truy cap module Todolist");
        homePage.clickFTodolistModule();
        fTodolistPage.openLichTab();
        fTodolistPage.clickDate(date);
        fTodolistPage.selectTinhTrangCongViecDangLich("Done");
        fTodolistPage.clickBaoCaoTodolist();
        fTodolistPage.clickOKAlert();
        softAssert.assertTrue(fTodolistPage.isToastMessageChecked());
        softAssert.assertEquals(fTodolistPage.toastMessageText(),"Chúc mừng bạn đã hoàn thành nhiệm vụ!");
        fTodolistPage.closeToastMessage();
        fTodolistPage.clickDongButton();
        softAssert.assertTrue(fTodolistPage.isCheckMark(3),"Chưa đánh dấu hoành thành nhiệm vụ");
        softAssert.assertEquals(fTodolistPage.getPhanTramHoanThanh(),"60%");
        homePage.clickHelpOutLineIcon();
        softAssert.assertEquals(homePage.getPhanThuongText(),"Tặng 6 ngày dùng chính thức");
        softAssert.assertAll();
    }
}
