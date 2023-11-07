package tdc.edu.danhsachdh;

import java.util.ArrayList;
import java.util.List;

import tdc.edu.danhsachdh.GioHangItem;
import tdc.edu.danhsachdh.HangHoa;

public class DanhSachGioHang {


    // fields
    private List<GioHangItem> list;
    private int size;

    // constructor
    public DanhSachGioHang() {
        this.list = new ArrayList<GioHangItem>();
        size = 0;
    }
    public List<GioHangItem> getList() {
        return list;
    }

    public void setList(List<GioHangItem> list) {
        this.list = list;
    }

    // properties

    /// <summary>
    /// Hàm thêm đơn hàng mới
    /// </summary>
    /// <param name="item"></param>
    /// <returns></returns>
    public boolean Them(GioHangItem item) {
        size++;
        return list.add(item);
    }

    /// <summary>
    /// hàm xóa đơn hàng
    /// </summary>
    /// <returns></returns>
    public boolean Xoa(GioHangItem o) {
        size--;
        return list.remove(o);
    }

    public String GetMaHang() {
        String mahang = "";
        // cap nhat so luong
        for (GioHangItem gioHangItem :
                list) {
            HangHoa hangHoa = gioHangItem.getHangHoa();
            mahang += hangHoa.getMaSp() + ",";
        }
        return mahang.substring(0, mahang.length() - 1); //xoa dau phay cuoi cung
    }

    public double TongThanhTien() {
        double tongThanhTien = 0.0;
        // cap nhat so luong
        for (GioHangItem gioHangItem : list) {
            tongThanhTien += gioHangItem.ThanhTien();
        }
        return tongThanhTien;
    }

    /// <summary>
    /// Hàm xuất danh sách
    /// </summary>
    public void XuatDS() {
        // cap nhat so luong
        String result = "";
        for (GioHangItem gioHangItem : list) {
            {
                result += gioHangItem.getHangHoa().getTenSp() + " " + gioHangItem.getSoLuong() + "Thanh tien: {" + gioHangItem.ThanhTien() + ")";
            }
            result += " Tam Tinh: " + TongThanhTien();
        }
    }
}
