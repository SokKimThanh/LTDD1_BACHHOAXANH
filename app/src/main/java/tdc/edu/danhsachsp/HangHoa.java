package tdc.edu.danhsachsp;

public class HangHoa extends SanPham {


    int soluongNhapkho;
    int slban;


    public int getSlban() {
        return slban;
    }

    public void setSlban(int slban) {
        this.slban = slban;
    }



    public HangHoa() {
        maSp++;
    }

    public HangHoa(int soluongNhapkho) {
        this.soluongNhapkho = soluongNhapkho;
    }

    public HangHoa(String tenSp, int loaiSp, double giaSp,  int soluongNhapkho) {
        super(tenSp, loaiSp, giaSp );
        this.soluongNhapkho = soluongNhapkho;
    }

    public HangHoa(String tenSp, int loaiSp, double giaSp ) {
        super(tenSp, loaiSp, giaSp );
    }

    public int getSoLuongTonKho() {
        return soluongNhapkho;
    }

    public void setSoLuongTonKho(int soluongNhapkho) {
        this.soluongNhapkho = soluongNhapkho;
    }


}
