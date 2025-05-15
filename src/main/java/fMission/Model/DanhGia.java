package fMission.Model;

import java.util.Objects;

public class DanhGia {
    int diem;
    String danhGia;

    public int getDiem() {
        return diem;
    }

    public void setDiem(int diem) {
        this.diem = diem;
    }

    public String getDanhGia() {
        return danhGia;
    }

    public void setDanhGia(String danhGia) {
        this.danhGia = danhGia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DanhGia danhGia1 = (DanhGia) o;
        return diem == danhGia1.diem && Objects.equals(danhGia, danhGia1.danhGia);
    }
    @Override
    public int hashCode() {
        return Objects.hash(diem, danhGia);
    }
    @Override
    public String toString() {
        return "DanhGia{" +
                "diem=" + diem +
                ", danhGia='" + danhGia + '\'' +
                '}';
    }
}
