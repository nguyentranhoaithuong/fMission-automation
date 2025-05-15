package fMission.Model;

import java.util.Objects;

public class ViecDaGiao {
    String tenCongViec;
    String tenNguoiDuocGiao;

    public String getTenCongViec() {
        return tenCongViec;
    }

    public void setTenCongViec(String tenCongViec) {
        this.tenCongViec = tenCongViec;
    }

    public String getTenNguoiDuocGiao() {
        return tenNguoiDuocGiao;
    }

    public void setTenNguoiDuocGiao(String tenNguoiDuocGiao) {
        this.tenNguoiDuocGiao = tenNguoiDuocGiao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ViecDaGiao that = (ViecDaGiao) o;
        return Objects.equals(tenCongViec, that.tenCongViec) && Objects.equals(tenNguoiDuocGiao, that.tenNguoiDuocGiao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tenCongViec, tenNguoiDuocGiao);
    }

    @Override
    public String toString() {
        return "ViecDaGiao{" +
                "tenCongViec='" + tenCongViec + '\'' +
                ", tenNguoiDuocGiao='" + tenNguoiDuocGiao + '\'' +
                '}';
    }
}
