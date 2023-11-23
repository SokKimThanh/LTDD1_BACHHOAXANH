package tdc.edu.danhsachdh;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import tdc.edu.danhsachsp.HangHoa;
import tdc.edu.giohang.GioHang;

public class DonHang {


    public static int _SoThuTu;
    private String[] _MaHang;
    private int _SoLuong; //tong so luong mua hang hoa
    private double _TongTien; // tong tien mua hang hoa
    private String _TenKH;
    private String _DiaChiKH;
    private String _SDT;
    private Date _NgayDatHang;

    public DonHang() {
        this._SoThuTu++;
        this._NgayDatHang = new Date();
    }

    public DonHang(String[] _MaHang, int _SoLuong, double _TongTien, String _TenKH, String _DiaChiKH, String _SDT, Date _NgayDatHang) {
        this._MaHang = _MaHang;
        this._SoLuong = _SoLuong;
        this._TongTien = _TongTien;
        this._TenKH = _TenKH;
        this._DiaChiKH = _DiaChiKH;
        this._SDT = _SDT;
        this._NgayDatHang = _NgayDatHang;
        this._SoThuTu++;
    }

    public static int getTongSoLuongGioHang() {
        return _SoThuTu;
    }

    public static void setTongSoLuongGioHang(int tongcong) {
        _SoThuTu = tongcong;
    }

    public String[] get_MaHang() {
        return _MaHang;
    }

    public void set_MaHang(String[] _MaHang) {
        this._MaHang = _MaHang;
    }

    public int get_SoLuong() {
        return _SoLuong;
    }

    public void set_SoLuong(int _SoLuong) {
        this._SoLuong = _SoLuong;
    }

    public double get_TongTien() {
        return _TongTien;
    }

    public void set_TongTien(double _TongTien) {
        this._TongTien = _TongTien;
    }

    public String get_TenKH() {
        return _TenKH;
    }

    public void set_TenKH(String _TenKH) {
        this._TenKH = _TenKH;
    }

    public String get_DiaChiKH() {
        return _DiaChiKH;
    }

    public void set_DiaChiKH(String _DiaChiKH) {
        this._DiaChiKH = _DiaChiKH;
    }

    public String get_SDT() {
        return _SDT;
    }

    public void set_SDT(String _SDT) {
        this._SDT = _SDT;
    }

    public Date get_NgayDatHang() {
        return _NgayDatHang;
    }

    public void set_NgayDatHang(Date _NgayDatHang) {
        this._NgayDatHang = _NgayDatHang;
    }

    /// <summary>
    /// ham Tao don hang moi
    /// </summary>
    /// <param name="gioHang"></param>
    public void NhapDonHang(GioHang gioHang) {
        // khai bao
        Random d = new Random();
        String maHang = "";
        int tongcong = 0;
        // xu ly ma hang
        for (HangHoa hangHoa : gioHang.getHangHoaList()) {
            maHang += hangHoa.getMaSp() + ",";
        }

        String[] arr = {"Le Duy Anh Tu", "Sok Kim Thanh", "Hoang Van Dung"};

        this.set_TenKH(arr[d.nextInt(arr.length)]) ;
        int number = d.nextInt(90000000) + 100000000;
        this.set_SDT(Integer.toString(number));
        /* Lưu ý rằng không phải tất cả các tháng đều có 31 ngày và
         * tháng 2 có thể có 28 hoặc 29 ngày tùy thuộc vào năm.
         *
         * Điều này có thể dẫn đến một ngoại lệ IllegalArgumentException
         * khi khởi tạo đối tượng Calendar với một ngày không hợp lệ
         *
         * cho tháng đã chọn.*/
// Random ngay
        try {
            int nam = Calendar.getInstance().get(Calendar.YEAR);// nam hien tai
            int thang = new Random().nextInt(12) + 1;// 1-12
            int soNgayTrongThang = Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH);// tính số ngày trong tháng trước khi random
            int ngay = new Random().nextInt(soNgayTrongThang) + 1;
            Calendar calendar = Calendar.getInstance();
            calendar.set(nam, thang - 1, ngay);
            Date NgayDatHang = calendar.getTime();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }
}
