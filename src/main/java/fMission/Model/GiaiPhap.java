package fMission.Model;

import java.util.Objects;

public class GiaiPhap {
    String tenGiaiPhap;
    String noiDungGiaiPhap;

    public String getTenGiaiPhap() {
        return tenGiaiPhap;
    }

    public void setTenGiaiPhap(String tenGiaiPhap) {
        this.tenGiaiPhap = tenGiaiPhap;
    }

    public String getNoiDungGiaiPhap() {
        return noiDungGiaiPhap;
    }

    public void setNoiDungGiaiPhap(String noiDungGiaiPhap) {
        this.noiDungGiaiPhap = noiDungGiaiPhap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GiaiPhap giaiPhap = (GiaiPhap) o;
        return Objects.equals(tenGiaiPhap, giaiPhap.tenGiaiPhap) && Objects.equals(noiDungGiaiPhap, giaiPhap.noiDungGiaiPhap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tenGiaiPhap, noiDungGiaiPhap);
    }

    @Override
    public String toString() {
        return "GiaiPhap{" +
                "tenGiaiPhap='" + tenGiaiPhap + '\'' +
                ", noiDungGiaiPhap='" + noiDungGiaiPhap + '\'' +
                '}';
    }
}
