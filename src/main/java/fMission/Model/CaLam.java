package fMission.Model;

import java.util.Objects;

public class CaLam {
    String tenCaLam;
    String thoiGianLamViec;
    float soCong;
    String ngayTao;

    public String getTenCaLam() {
        return tenCaLam;
    }

    public void setTenCaLam(String tenCaLam) {
        this.tenCaLam = tenCaLam;
    }

    public String getThoiGianLamViec() {
        return thoiGianLamViec;
    }

    public void setThoiGianLamViec(String thoiGianLamViec) {
        this.thoiGianLamViec = thoiGianLamViec;
    }

    public float getSoCong() {
        return soCong;
    }

    public void setSoCong(float soCong) {
        this.soCong = soCong;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CaLam caLam = (CaLam) o;
        return Float.compare(soCong, caLam.soCong) == 0 && Objects.equals(tenCaLam, caLam.tenCaLam) && Objects.equals(thoiGianLamViec, caLam.thoiGianLamViec) && Objects.equals(ngayTao, caLam.ngayTao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tenCaLam, thoiGianLamViec, soCong, ngayTao);
    }

    @Override
    public String toString() {
        return "CaLam{" +
                "tenCaLam='" + tenCaLam + '\'' +
                ", thoiGianLamViec='" + thoiGianLamViec + '\'' +
                ", soCong=" + soCong +
                ", ngayTao='" + ngayTao + '\'' +
                '}';
    }
}
