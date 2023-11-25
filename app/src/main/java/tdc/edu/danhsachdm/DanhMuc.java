package tdc.edu.danhsachdm;

import java.io.Serializable;

public class DanhMuc implements Serializable {
    private String ma, ten, ghichu;
    public DanhMuc() {
    }


    public DanhMuc(String ma, String ten, String ghichu) {
        this.ma = ma;
        this.ten = ten;
        this.ghichu = ghichu;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    @Override
    public String toString() {
        return ten;
    }
}
