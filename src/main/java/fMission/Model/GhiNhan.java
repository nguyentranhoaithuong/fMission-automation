package fMission.Model;

import java.util.Objects;

public class GhiNhan {
    String tenNguoiThucHien;
    String tenNguoiNhan;
    String noiDung;
    String thoiGian;

    public String getTenNguoiThucHien() {
        return tenNguoiThucHien;
    }

    public void setTenNguoiThucHien(String tenNguoiThucHien) {
        this.tenNguoiThucHien = tenNguoiThucHien;
    }

    public String getTenNguoiNhan() {
        return tenNguoiNhan;
    }

    public void setTenNguoiNhan(String tenNguoiNhan) {
        this.tenNguoiNhan = tenNguoiNhan;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GhiNhan ghiNhan = (GhiNhan) o;
        return Objects.equals(tenNguoiThucHien, ghiNhan.tenNguoiThucHien) && Objects.equals(tenNguoiNhan, ghiNhan.tenNguoiNhan) && Objects.equals(noiDung, ghiNhan.noiDung) && Objects.equals(thoiGian, ghiNhan.thoiGian);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tenNguoiThucHien, tenNguoiNhan, noiDung, thoiGian);
    }

    @Override
    public String toString() {
        return "GhiNhan{" +
                "tenNguoiThucHien='" + tenNguoiThucHien + '\'' +
                ", tenNguoiNhan='" + tenNguoiNhan + '\'' +
                ", noiDung='" + noiDung + '\'' +
                ", thoiGian='" + thoiGian + '\'' +
                '}';
    }
}
