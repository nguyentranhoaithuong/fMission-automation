package fMission.page.AdminPage;

import fMission.Model.CuaHang;
import fMission.Model.DonHang;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DonHangPage {
    WebDriver driver;
    By tenSanPhamVuaMuaLocator = By.xpath("//label[text()='Sản phẩm']/following-sibling::div/div");
    By soLuongSanPhamVuaMuaLocator = By.xpath("//label[text()='Số lượng']/following-sibling::div");
    By soSaoDoiLocator = By.xpath("//label[text()='Sao đổi']/following-sibling::div");
    By ngayDoiLocator = By.xpath("//label[text()='Thời gian']/following-sibling::div/div[normalize-space()]");
    By nguoiMuaLocator = By.xpath("//label[text()='Người mua']/following-sibling::div/div/span[normalize-space()]");
    By trangThaiLocator = By.xpath("//label[text()='Trạng thái']/following-sibling::div/span[@class='tag is-warning']");

    public DonHangPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTenSanPhamVuaMua() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(tenSanPhamVuaMuaLocator));
        return driver.findElement(tenSanPhamVuaMuaLocator).getText();
    }

    public int getSoLuongSanPhamVuaMua() {
        return Integer.parseInt(driver.findElement(soLuongSanPhamVuaMuaLocator).getText());
    }

    public int getSoSaoDoiSanPhamVuaMua() {
        return Integer.parseInt(driver.findElement(soSaoDoiLocator).getText());
    }

    public String getThoiGianDoiSanPhamVuaMua() {
        return driver.findElement(ngayDoiLocator).getText().replaceAll("\\s+", " ").trim();
    }

    public String getNguoiMuaSanPham() {
        return driver.findElement(nguoiMuaLocator).getText();
    }

    public String getTrangThaiSanPham() {
        return driver.findElement(trangThaiLocator).getText();
    }


    public DonHang getDonHangVuaTao() {
        DonHang dh = new DonHang();
        dh.setTenSanPham(getTenSanPhamVuaMua());
        dh.setSoLuong(getSoLuongSanPhamVuaMua());
        dh.setSaoDoi(getSoSaoDoiSanPhamVuaMua());
        dh.setThoiGian(getThoiGianDoiSanPhamVuaMua());
        dh.setNguoiMua(getNguoiMuaSanPham());
        dh.setTrangThai(getTrangThaiSanPham());
        return dh;
    }


}
