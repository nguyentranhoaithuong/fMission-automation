package fMission.Model;

import java.util.Objects;

public class TieuChiGhiNhan {
    String tieuChi;
    int soSao;

    public String getTieuChi() {
        return tieuChi;
    }

    public void setTieuChi(String tieuChi) {
        this.tieuChi = tieuChi;
    }

    public int getSoSao() {
        return soSao;
    }

    public void setSoSao(int soSao) {
        this.soSao = soSao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TieuChiGhiNhan that = (TieuChiGhiNhan) o;
        return soSao == that.soSao && Objects.equals(tieuChi, that.tieuChi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tieuChi, soSao);
    }

    @Override
    public String toString() {
        return "TieuChiGhiNhan{" +
                "tieuChi='" + tieuChi + '\'' +
                ", soSao=" + soSao +
                '}';
    }
}
