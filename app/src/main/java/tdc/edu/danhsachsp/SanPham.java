package tdc.edu.danhsachsp;

import java.io.Serializable;

public class SanPham implements Serializable {
    String tenSp, loaiSp;
    int maSp;
    double giaSp;

    public SanPham() {
    }

    public SanPham(String tenSp, String loaiSp, double giaSp ) {
        this.tenSp = tenSp;
        this.loaiSp = loaiSp;
        this.giaSp = giaSp;
        this.maSp++;
    }

    public String getTenSp() {
        return tenSp;
    }

    public void setTenSp(String tenSp) {
        this.tenSp = tenSp;
    }

    public String getLoaiSp() {
        return loaiSp;
    }

    public void setLoaiSp(String loaiSp) {
        this.loaiSp = loaiSp;
    }

    public double getGiaSp() {
        return giaSp;
    }

    public void setGiaSp(double giaSp) {
        this.giaSp = giaSp;
    }

    public int getMaSp() {
        return maSp;
    }

    public void setMaSp(int maSp) {
        this.maSp = maSp;
    }

    @Override
    public String toString() {
        return "SanPham{" +
                "tenSp='" + tenSp + '\'' +
                ", loaiSp='" + loaiSp + '\'' +
                ", giaSp='" + giaSp + '\'' +
                ", maSp='" + maSp + '\'' +
                '}';
    }
}
