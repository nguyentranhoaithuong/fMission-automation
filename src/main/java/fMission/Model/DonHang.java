package fMission.Model;

import java.util.Objects;

public class DonHang {
    String tenSanPham;
    int soLuong;
    int saoDoi;
    String thoiGian;
    String nguoiMua;
    String trangThai;

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getSaoDoi() {
        return saoDoi;
    }

    public void setSaoDoi(int saoDoi) {
        this.saoDoi = saoDoi;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }

    public String getNguoiMua() {
        return nguoiMua;
    }

    public void setNguoiMua(String nguoiMua) {
        this.nguoiMua = nguoiMua;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DonHang donHang = (DonHang) o;
        return soLuong == donHang.soLuong && saoDoi == donHang.saoDoi && Objects.equals(tenSanPham, donHang.tenSanPham) && Objects.equals(thoiGian, donHang.thoiGian) && Objects.equals(nguoiMua, donHang.nguoiMua) && Objects.equals(trangThai, donHang.trangThai);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tenSanPham, soLuong, saoDoi, thoiGian, nguoiMua, trangThai);
    }

    @Override
    public String toString() {
        return "DonHang{" +
                "tenSanPham='" + tenSanPham + '\'' +
                ", soLuong=" + soLuong +
                ", saoDoi=" + saoDoi +
                ", thoiGian='" + thoiGian + '\'' +
                ", nguoiMua='" + nguoiMua + '\'' +
                ", trangThai='" + trangThai + '\'' +
                '}';
    }
}
