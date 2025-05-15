package fMission.Model;

import java.util.Objects;

public class CuaHang {
    String tenSanPham;
    String danhMuc;
    int soSao;
    String moTa;

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getDanhMuc() {
        return danhMuc;
    }

    public void setDanhMuc(String danhMuc) {
        this.danhMuc = danhMuc;
    }

    public int getSoSao() {
        return soSao;
    }

    public void setSoSao(int soSao) {
        this.soSao = soSao;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CuaHang cuaHang = (CuaHang) o;
        return soSao == cuaHang.soSao && Objects.equals(tenSanPham, cuaHang.tenSanPham) && Objects.equals(danhMuc, cuaHang.danhMuc) && Objects.equals(moTa, cuaHang.moTa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tenSanPham, danhMuc, soSao, moTa);
    }

    @Override
    public String toString() {
        return "CuaHang{" +
                "tenSanPham='" + tenSanPham + '\'' +
                ", danhMuc='" + danhMuc + '\'' +
                ", soSao=" + soSao +
                ", moTa='" + moTa + '\'' +
                '}';
    }
}
