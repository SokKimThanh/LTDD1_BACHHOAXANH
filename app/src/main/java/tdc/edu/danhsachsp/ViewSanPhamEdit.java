package tdc.edu.danhsachsp;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import tdc.edu.danhsachdm.DBDanhMuc;
import tdc.edu.danhsachdm.DanhMuc;

public class ViewSanPhamEdit extends AppCompatActivity {
    EditText edtGiaSP, edtTenSP, edtSoLuongSPNhapKho;
    TextView tvMaSp;
    Spinner spinnerDanhMuc;
    Button btnUpdate, btnDelete, btnBack;


    ImageView ivHinhEdit;

    List<String> data_lsp = new ArrayList<>();
    ArrayAdapter adapter_lsp;

    // lấy 1 dữ liệu sản phẩm để cập nhật
    HangHoa sanPham;

    DBDanhMuc dbDanhMuc;
    DBHangHoa dbHangHoa;


    DanhMuc selectedDanhmucSpinner = new DanhMuc();

    int selectedMaDM = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);// hiển thị nút quay lại trang chủ
        setContentView(R.layout.layout_sanpham_edit);
        // ánh xạ
        setControl();
        setEvent();
    }

    private void setControl() {
        this.tvMaSp = findViewById(R.id.tvMaSp);
        this.edtGiaSP = findViewById(R.id.edtGiaSP);
        this.edtTenSP = findViewById(R.id.edtTenSP);
        this.edtSoLuongSPNhapKho = findViewById(R.id.edtSoLuongSPNhapKho);
        this.spinnerDanhMuc = findViewById(R.id.spinnerDanhMuc);
        this.btnUpdate = findViewById(R.id.btnUpdate);
        this.btnDelete = findViewById(R.id.btnDelete);
        this.btnBack = findViewById(R.id.btnBackChiTiet);
        this.ivHinhEdit = findViewById(R.id.ivHinhSuaSP);
    }

    /**
     * Đổ dữ liệu vào spinner loại sản phẩm
     */
    private void setEvent() {
        KhoiTao();
        spinnerDanhMuc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                selectedDanhmucSpinner = (DanhMuc) parent.getItemAtPosition(position);
                selectedMaDM = selectedDanhmucSpinner.getMa();
// Xử lý mã đã chọn...
                if (selectedMaDM == 0) {
                    spinnerDanhMuc.setSelection(0);
                    ivHinhEdit.setImageResource(R.drawable.img_thit);
                }
                if (selectedMaDM == 1) {
                    spinnerDanhMuc.setSelection(1);
                    ivHinhEdit.setImageResource(R.drawable.img_ca);
                }
                if (selectedMaDM == 2) {
                    spinnerDanhMuc.setSelection(2);
                    ivHinhEdit.setImageResource(R.drawable.img_trung);
                }
                if (selectedMaDM == 3) {
                    spinnerDanhMuc.setSelection(3);
                    ivHinhEdit.setImageResource(R.drawable.img_sua);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // Khởi tạo item để chuyển đối tượng lên màn hình
        sanPham = (HangHoa) getIntent().getSerializableExtra("item");

        //hiển thị dữ liệu lên màn hình sửa
        tvMaSp.setText(sanPham.getMaSp() + "");// mã sp
        edtTenSP.setText(sanPham.getTenSp());// tên sp
        edtGiaSP.setText(String.valueOf(sanPham.getGiaSp()));// giá sp
        String soluong = String.valueOf(sanPham.getSoLuongTonKho());

        edtSoLuongSPNhapKho.setText(soluong);// soluong sp nhập kho

        // loại sản phẩm
        if (sanPham.getLoaiSp().equals("0"))
            spinnerDanhMuc.setSelection(0);
        if (sanPham.getLoaiSp().equals("1"))
            spinnerDanhMuc.setSelection(1);
        if (sanPham.getLoaiSp().equals("2"))
            spinnerDanhMuc.setSelection(2);
        if (sanPham.getLoaiSp().equals("3"))
            spinnerDanhMuc.setSelection(3);
        // sự kiện update
        // Sử dụng dữ liệu tĩnh của chính nó(adapter_lsp)
        // Sự kiện xóa
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (HangHoa sp : ViewSanPhamList.hangHoas) {
                    //tim ma san pham de cap nhat
                    if (sp.getMaSp() == sanPham.getMaSp()) {
                        // sua
                        sp.setTenSp(edtTenSP.getText().toString());
                        sp.setGiaSp(Double.valueOf(edtGiaSP.getText().toString()));
                        sp.setSoLuongTonKho(Integer.parseInt(edtSoLuongSPNhapKho.getText().toString()));
                        sp.setLoaiSp(selectedMaDM + "");
                        // thong bao
                        dbHangHoa.SuaDL(sp);
                        ViewSanPhamList.sanPhamListAdapter.notifyDataSetChanged();
                        Toast.makeText(ViewSanPhamEdit.this, "Sửa thành công", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });

        // Sự kiện xóa
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (HangHoa sp : ViewSanPhamList.hangHoas) {
                    //tim ma san pham de cap nhat
                    if (sp.getMaSp() == sanPham.getMaSp()) {
                        // xoa

                        ViewSanPhamList.hangHoas.remove(sp);
                        // thong bao
                        dbHangHoa.XoaDL(sp);
                        ViewSanPhamList.sanPhamListAdapter.notifyDataSetChanged();
                        Toast.makeText(ViewSanPhamEdit.this, "Xóa thành công", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });


        // sự kiện quay lại
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                Toast.makeText(ViewSanPhamEdit.this, "Quay lai Thành công", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void KhoiTao() {
        // dbHangHoa truy cập dữ liệu DB
        dbHangHoa = new DBHangHoa(this);
        // dbDanhMuc truy cập dữ liệu DB
        dbDanhMuc = new DBDanhMuc(this);

        // Danh sách các đối tượng DanhMuc
        List<DanhMuc> list = dbDanhMuc.DocDL();
        adapter_lsp = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        spinnerDanhMuc.setAdapter(adapter_lsp);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        // hỗ trợ quay lại màn hình chính
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}