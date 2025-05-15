package fMission.Model;

import java.util.Objects;

public class TodolistLienKet {
    String tenCongViec;

    public String getTenCongViec() {
        return tenCongViec;
    }

    public void setTenCongViec(String tenCongViec) {
        this.tenCongViec = tenCongViec;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TodolistLienKet that = (TodolistLienKet) o;
        return Objects.equals(tenCongViec, that.tenCongViec);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(tenCongViec);
    }

    @Override
    public String toString() {
        return "TodolistLienKet{" +
                "tenCongViec='" + tenCongViec + '\'' +
                '}';
    }
}
