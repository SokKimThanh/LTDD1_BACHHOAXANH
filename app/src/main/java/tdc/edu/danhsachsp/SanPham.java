package tdc.edu.danhsachsp;

import java.io.Serializable;

public class SanPham implements Serializable {
    String tenSp;
    int maSp,loaiSp;
    double giaSp;

    public SanPham() {
    }

    public SanPham(String tenSp, int loaiSp, double giaSp ) {
        this.tenSp = tenSp;
        this.loaiSp = loaiSp;
        this.giaSp = giaSp;
    }

    public String getTenSp() {
        return tenSp;
    }

    public void setTenSp(String tenSp) {
        this.tenSp = tenSp;
    }

    public int getLoaiSp() {
        return loaiSp;
    }

    public void setLoaiSp(int loaiSp) {
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
