package tdc.edu.danhsachdh;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import tdc.edu.danhsachsp.R;

public class ThemDonHang extends AppCompatActivity {
    TextView tvTieuDeChiTietDonHang, tvMaDonHang;
    EditText edtMaSanPham, edtSoLuong, edtTenSP, edtDonGia, edtThanhTien, edtLoaiTien;
    Button btnAddGioHang, btnAddHoaDon, btnLamMoiHoaDon, btnExit;
    ListView lvDanhSachGioHang;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_donhang_add);
        setControl();
        setEvent();
    }

    private void setEvent() {
        KhoiTao();
    }

    private void KhoiTao() {

        tvMaDonHang.setText("2323211113322");
    }

    private void setControl() {
        tvTieuDeChiTietDonHang = findViewById(R.id.tvTieuDeChiTietDonHang);
        tvMaDonHang = findViewById(R.id.tvMaDonHang);
        edtMaSanPham = findViewById(R.id.edtMaSanPham);
        edtSoLuong = findViewById(R.id.edtSoLuong);
        edtTenSP = findViewById(R.id.edtTenSP);
        edtDonGia = findViewById(R.id.edtDonGia);
        edtThanhTien = findViewById(R.id.edtThanhTien);
        edtLoaiTien = findViewById(R.id.edtLoaiTien);
        btnAddGioHang = findViewById(R.id.btnAddGioHang);
        btnAddHoaDon = findViewById(R.id.btnAddHoaDon);
        btnLamMoiHoaDon = findViewById(R.id.btnLamMoi);
        btnExit = findViewById(R.id.btnExit);
        lvDanhSachGioHang = findViewById(R.id.cartListView);
    }
}