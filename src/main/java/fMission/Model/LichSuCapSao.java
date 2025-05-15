package fMission.Model;

import java.util.Objects;

public class LichSuCapSao {
    String thoiGian;
    String ten;
    String phongBan;
    String capBac;
    String loaiVi;
    int sosao;
    String nguoiThucHien;

    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getPhongBan() {
        return phongBan;
    }

    public void setPhongBan(String phongBan) {
        this.phongBan = phongBan;
    }

    public String getCapBac() {
        return capBac;
    }

    public void setCapBac(String capBac) {
        this.capBac = capBac;
    }

    public String getLoaiVi() {
        return loaiVi;
    }

    public void setLoaiVi(String loaiVi) {
        this.loaiVi = loaiVi;
    }

    public int getSosao() {
        return sosao;
    }

    public void setSosao(int sosao) {
        this.sosao = sosao;
    }

    public String getNguoiThucHien() {
        return nguoiThucHien;
    }

    public void setNguoiThucHien(String nguoiThucHien) {
        this.nguoiThucHien = nguoiThucHien;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LichSuCapSao that = (LichSuCapSao) o;
        return sosao == that.sosao && Objects.equals(thoiGian, that.thoiGian) && Objects.equals(ten, that.ten) && Objects.equals(phongBan, that.phongBan) && Objects.equals(capBac, that.capBac) && Objects.equals(loaiVi, that.loaiVi) && Objects.equals(nguoiThucHien, that.nguoiThucHien);
    }

    @Override
    public int hashCode() {
        return Objects.hash(thoiGian, ten, phongBan, capBac, loaiVi, sosao, nguoiThucHien);
    }

    @Override
    public String toString() {
        return "LichSuCapSao{" +
                "thoiGian='" + thoiGian + '\'' +
                ", ten='" + ten + '\'' +
                ", phongBan='" + phongBan + '\'' +
                ", capBac='" + capBac + '\'' +
                ", loaiVi='" + loaiVi + '\'' +
                ", sosao=" + sosao +
                ", nguoiThucHien='" + nguoiThucHien + '\'' +
                '}';
    }
}
