package tdc.edu.giohang;

import java.util.List;

import tdc.edu.danhsachsp.HangHoa;

public class ChiTietGioHang extends GioHang{
private String tenDH;
private String dataHangHoa;
private  int ngay;
private  int thang;
private  int nam;
private  int tongTien;

    @Override
    public String toString() {
        return "ChiTietGioHang{" +
                "tenDH='" + tenDH + '\'' +
                ", dataHangHoa='" + dataHangHoa + '\'' +
                ", ngay=" + ngay +
                ", thang=" + thang +
                ", nam=" + nam +
                ", tongTien=" + tongTien +
                '}';
    }

    public String getTenDH() {
        return tenDH;
    }

    public void setTenDH(String tenDH) {
        this.tenDH = tenDH;
    }

    public String getDataHangHoa() {
        return dataHangHoa;
    }

    public void setDataHangHoa(String dataHangHoa) {
        this.dataHangHoa = dataHangHoa;
    }

    public int getNgay() {
        return ngay;
    }

    public void setNgay(int ngay) {
        this.ngay = ngay;
    }

    public int getThang() {
        return thang;
    }

    public void setThang(int thang) {
        this.thang = thang;
    }

    public int getNam() {
        return nam;
    }

    public void setNam(int nam) {
        this.nam = nam;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public ChiTietGioHang() {
    }

    public ChiTietGioHang(String tenDH, String dataHangHoa, int ngay, int thang, int nam, int tongTien) {
        this.tenDH = tenDH;
        this.dataHangHoa = dataHangHoa;
        this.ngay = ngay;
        this.thang = thang;
        this.nam = nam;
        this.tongTien = tongTien;
    }



}
