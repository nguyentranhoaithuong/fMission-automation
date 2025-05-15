package fTodolist;

import com.github.javafaker.Faker;
import fMission.Model.ViecDaGiao;
import fMission.page.UserPage.GiaoViecPage;
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

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Random;

public class UTTDL040 {
    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    fMission.page.UserPage.fCheckinPage fCheckinPage;
    Faker faker;
    SoftAssert softAssert;
    fMission.page.UserPage.fTodolistPage fTodolistPage;
    GiaoViecPage giaoViecPage;
    Random random;

    String tenCongViec;
    String tenNguoiDuocGiao;
    String phanthuongbf;
    ViecDaGiao viecDaGiao;
    ViecDaGiao themGiaoViec;
    String formattedDate;



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
        giaoViecPage = new GiaoViecPage(driver);
        softAssert = new SoftAssert();
        faker = new Faker();
        random = new Random();

        Allure.step("Dang nhap vao he thong");

        loginPage.clickAcceptPolicy();
        loginPage.Login("thuongnth.fastdo@gmail.com","thuong@123");

        tenCongViec = faker.job().title();
        tenNguoiDuocGiao = "Lê Thị Linh";

        themGiaoViec = new ViecDaGiao();
        themGiaoViec.setTenCongViec(tenCongViec);
        themGiaoViec.setTenNguoiDuocGiao(tenNguoiDuocGiao);

        LocalDate today = LocalDate.now();
        DayOfWeek dayOfWeek = today.getDayOfWeek();
        String[] thu = {"CN", "T2", "T3", "T4", "T5", "T6", "T7"};
        String thuText = thu[dayOfWeek.getValue() % 7];  // Thứ 2 → T2, Chủ Nhật → CN

        int day = today.getDayOfMonth();
        int month = today.getMonthValue();
        int year = today.getYear();

        // Định dạng có 0 ở trước ngày/tháng nếu < 10
        formattedDate = String.format("%s - %02d/%02d/%d", thuText, day, month, year);



    }
    @AfterMethod
    public void cleanUp() {
//        driver.quit();
    }
    @Test
    public void Test() {
        //Xác minh rằng thực hiện nhiệm vụ 4 thành công khi user giao việc thành công
        Allure.step("Truy cap module Todolist");
        homePage.clickFTodolistModule();
        fTodolistPage.openBangNhiemVu();
        fTodolistPage.clickArrowForwardButton(4);
        giaoViecPage.clickGiaoViecButton();
        giaoViecPage.giaoViec(tenCongViec,tenNguoiDuocGiao);
        softAssert.assertTrue(fTodolistPage.isToastMessageChecked());
        softAssert.assertEquals(fTodolistPage.toastMessageText(),"Chúc mừng bạn đã hoàn thành nhiệm vụ!");
        fTodolistPage.closeToastMessage();
        System.out.println(formattedDate);
        viecDaGiao = giaoViecPage.getViecDaGiaoVuaTao(formattedDate);
        giaoViecPage.openBangNhiemVu();
        softAssert.assertTrue(fTodolistPage.isCheckMark(4),"Chưa đánh dấu hoành thành nhiệm vụ");
        softAssert.assertEquals(fTodolistPage.getPhanTramHoanThanh(),"80%");
        homePage.clickHelpOutLineIcon();
        softAssert.assertEquals(homePage.getPhanThuongText(),"Tặng 10 ngày dùng chính thức");
        softAssert.assertAll();

    }
}
