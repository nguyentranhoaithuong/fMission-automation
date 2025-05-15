package fMission.Model;

import java.util.Objects;

public class VanDe {
    String tenVanDe;
    String noiDung;

    public String getTenVanDe() {
        return tenVanDe;
    }

    public void setTenVanDe(String tenVanDe) {
        this.tenVanDe = tenVanDe;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VanDe vanDe = (VanDe) o;
        return Objects.equals(tenVanDe, vanDe.tenVanDe) && Objects.equals(noiDung, vanDe.noiDung);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tenVanDe, noiDung);
    }

    @Override
    public String toString() {
        return "VanDe{" +
                "tenVanDe='" + tenVanDe + '\'' +
                ", noiDung='" + noiDung + '\'' +
                '}';
    }
}
