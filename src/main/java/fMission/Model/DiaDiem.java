package fMission.Model;
import java.util.Objects;
public class DiaDiem {
    String tenDiaDiem;
    int khoangCach;

    public String getTenDiaDiem() {
        return tenDiaDiem;
    }

    public void setTenDiaDiem(String tenDiaDiem) {
        this.tenDiaDiem = tenDiaDiem;
    }

    public int getKhoangCach() {
        return khoangCach;
    }

    public void setKhoangCach(int khoangCach) {
        this.khoangCach = khoangCach;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiaDiem diaDiem = (DiaDiem) o;
        return khoangCach == diaDiem.khoangCach && Objects.equals(tenDiaDiem, diaDiem.tenDiaDiem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tenDiaDiem, khoangCach);
    }

    @Override
    public String toString() {
        return "DiaDiem{" +
                "tenDiaDiem='" + tenDiaDiem + '\'' +
                ", khoangCach=" + khoangCach +
                '}';
    }
}
