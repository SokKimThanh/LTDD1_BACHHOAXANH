package tdc.edu.danhsachsp;

public class HangHoa extends SanPham{


    int soluongNhapkho;

    public HangHoa(){

    }
    public HangHoa(int soluongNhapkho) {
        this.soluongNhapkho = soluongNhapkho;
    }

    public HangHoa(String tenSp, String loaiSp, double giaSp, String maSp, int soluongNhapkho) {
        super(tenSp, loaiSp, giaSp, maSp);
        this.soluongNhapkho = soluongNhapkho;
    }
    public HangHoa(String tenSp, String loaiSp, double giaSp, String maSp ) {
        super(tenSp, loaiSp, giaSp, maSp);
    }

    public int getSoLuongTonKho() {
        return soluongNhapkho;
    }

    public void setSoLuongTonKho(int soluongNhapkho) {
        this.soluongNhapkho = soluongNhapkho;
    }


}
