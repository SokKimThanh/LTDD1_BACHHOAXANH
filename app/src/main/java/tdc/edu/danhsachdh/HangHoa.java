package tdc.edu.danhsachdh;

import tdc.edu.danhsachsp.SanPham;

public class HangHoa extends SanPham{


    int soluongNhapkho;

    public HangHoa(){

    }
    public HangHoa(int soluongNhapkho) {
        this.soluongNhapkho = soluongNhapkho;
    }

    public HangHoa(String tenSp, String loaiSp, String giaSp, String maSp, int soluongNhapkho) {
        super(tenSp, loaiSp, giaSp, maSp);
        this.soluongNhapkho = soluongNhapkho;
    }
    public HangHoa(String tenSp, String loaiSp, String giaSp, String maSp ) {
        super(tenSp, loaiSp, giaSp, maSp);
    }

    public int getSoluongNhapkho() {
        return soluongNhapkho;
    }

    public void setSoluongNhapkho(int soluongNhapkho) {
        this.soluongNhapkho = soluongNhapkho;
    }


}
