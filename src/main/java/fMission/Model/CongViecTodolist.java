package fMission.Model;

import java.util.Objects;

public class CongViecTodolist {
    String tenCongViec;
    String chiTiet;

    public String getTenCongViec() {
        return tenCongViec;
    }

    public void setTenCongViec(String tenCongViec) {
        this.tenCongViec = tenCongViec;
    }

    public String getChiTiet() {
        return chiTiet;
    }

    public void setChiTiet(String chiTiet) {
        this.chiTiet = chiTiet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CongViecTodolist that = (CongViecTodolist) o;
        return Objects.equals(tenCongViec, that.tenCongViec) && Objects.equals(chiTiet, that.chiTiet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tenCongViec, chiTiet);
    }

    @Override
    public String toString() {
        return "CongViecTodolist{" +
                "tenCongViec='" + tenCongViec + '\'' +
                ", chiTiet='" + chiTiet + '\'' +
                '}';
    }
}
