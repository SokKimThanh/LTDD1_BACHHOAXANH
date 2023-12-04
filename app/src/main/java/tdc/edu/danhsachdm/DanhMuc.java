package tdc.edu.danhsachdm;

import java.io.Serializable;

public class DanhMuc implements Serializable {
    private String ten, ghichu;
    private int ma;

    public DanhMuc() {
        this.ma++;
    }


    public DanhMuc(String ten, String ghichu) {
        this.ten = ten;
        this.ghichu = ghichu;
        this.ma++;
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
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
