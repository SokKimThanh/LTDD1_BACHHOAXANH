package tdc.edu.danhsachdh;

import tdc.edu.danhsachsp.HangHoa;

public class GioHangItem {

    private HangHoa hangHoa;
    private int soLuongXuatKho;


    public GioHangItem() {
    }

    public GioHangItem(HangHoa hangHoa, int soLuongXuatKho) {
        this.hangHoa = hangHoa;
        this.soLuongXuatKho = soLuongXuatKho;
    }


    public HangHoa getHangHoa() {
        return hangHoa;
    }

    public void setHangHoa(HangHoa hangHoa) {
        this.hangHoa = hangHoa;
    }

    public int getSoLuong() {
        return soLuongXuatKho;
    }

    public void setSoLuongXuatKho(int soLuongXuatKho) {
        this.soLuongXuatKho = soLuongXuatKho;
    }

    public double ThanhTien()
    {
        return this.getSoLuong() * Double.parseDouble(this.getHangHoa().getGiaSp());
    }
    @Override
    public String toString() {
        return "GioHangItem{" +
                "hangHoa=" + hangHoa.toString() +
                ", soLuongXuatKho=" + soLuongXuatKho +
                '}';
    }


}
