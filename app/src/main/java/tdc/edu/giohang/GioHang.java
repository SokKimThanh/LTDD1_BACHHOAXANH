package tdc.edu.giohang;

import java.util.ArrayList;
import java.util.List;

import tdc.edu.danhsachsp.HangHoa;

public class GioHang {

    //danh sách sản phẩm, tổng số lượng, tổng giá tiền
    private List<HangHoa> hangHoaList;


    private int tongSoLuong;

    private double tongThanhTien;

    public GioHang() {
        hangHoaList = new ArrayList<>();
        this.tongThanhTien = 0;
        this.tongSoLuong = hangHoaList.size();
    }


    public List<HangHoa> getHangHoaList() {
        return hangHoaList;
    }

    public void setHangHoaList(List<HangHoa> hangHoaList) {
        this.hangHoaList = hangHoaList;
    }

    public int getTongSoLuong() {
        return tongSoLuong;
    }


    public double getTongThanhTien() {
        double sum = 0;
        for (HangHoa hh : hangHoaList) {
            sum += ThanhTien(hh);
        }
        this.tongThanhTien = sum;
        return this.tongThanhTien;
    }

    public double ThanhTien(HangHoa hangHoa) {
        return hangHoa.getSoluongNhapkho() * Double.parseDouble(hangHoa.getGiaSp());
    }

    public void increaseQuantity(HangHoa hangHoa) {
        hangHoaList.add(hangHoa);
        this.tongSoLuong++;
    }

    public void add(HangHoa hangHoa) {
        hangHoaList.add(hangHoa);
        this.tongSoLuong++;
    }

    public void remove(HangHoa hangHoa) {
        for (HangHoa hh : this.hangHoaList) {
            if (hh.getMaSp().equals(hangHoa.getMaSp())) {
                this.hangHoaList.remove(hh);
                break;
            }
        }
        this.tongSoLuong --;
     }
}
