package tdc.edu.giohang;

import java.io.Serializable;

import tdc.edu.danhsachsp.HangHoa;

public class HoaDon extends HangHoa implements Serializable {
    public int SoLuongBan;
    public static  int ThanhTien = 0;

    public int getSoLuongBan() {
        return SoLuongBan;
    }

    public void setSoLuongBan(int soLuongBan) {
        SoLuongBan = soLuongBan;
    }


    public HoaDon(int soLuongBan) {
        SoLuongBan = soLuongBan;
    }
    public HoaDon(){}



}
