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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import tdc.edu.danhsachdm.DBDanhMuc;
import tdc.edu.danhsachdm.DanhMuc;

public class ViewSanPhamAdd extends AppCompatActivity {
    EditText edtGiaSP, edtTenSP, edtSoLuongSP;

    Spinner spinnerDanhMuc;
    Button btnAdd, btnRefresh, btnBack;


    ImageView ivHinhAdd;

    List<String> data_lsp = new ArrayList<>();

    ArrayAdapter<DanhMuc> adapter_lsp;
    DBHangHoa dbHangHoa;
    DBDanhMuc dbDanhMuc;

    DanhMuc selectedDanhmucSpinner = new DanhMuc();
    int selectedMaDM = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);// hiển thị nút quay lại trang chủ
        setContentView(R.layout.layout_sanpham_add);
        // ánh xạ
        setControl();
        setEvent();
    }

    private void setControl() {
//        this.edtMaSp = findViewById(R.id.edtMaSP);
        this.edtGiaSP = findViewById(R.id.edtGiaSP);
        this.edtTenSP = findViewById(R.id.edtTenSP);
        this.spinnerDanhMuc = findViewById(R.id.spinnerDanhMuc);
        this.edtSoLuongSP = findViewById(R.id.edtSoLuongSP);
        this.btnAdd = findViewById(R.id.btnAdd);
        this.btnRefresh = findViewById(R.id.btnRefresh);
        this.btnBack = findViewById(R.id.btnBackThem);
        this.ivHinhAdd = findViewById(R.id.ivHinhThemSP);
    }

    /**
     * Đổ dữ liệu vào spinner loại sản phẩm
     */
    private void setEvent() {
        KhoiTao();

        // loại sản phẩm


        spinnerDanhMuc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedDanhmucSpinner = (DanhMuc) parent.getItemAtPosition(position);
                selectedMaDM = selectedDanhmucSpinner.getMa();
                // Xử lý mã đã chọn...
                if (selectedMaDM == 0) {
                    ivHinhAdd.setImageResource(R.drawable.img_thit);
                }
                if (selectedMaDM == 1) {
                    ivHinhAdd.setImageResource(R.drawable.img_ca);
                }
                if (selectedMaDM == 2) {
                    ivHinhAdd.setImageResource(R.drawable.img_trung);
                }
                if (selectedMaDM == 3) {
                    ivHinhAdd.setImageResource(R.drawable.img_sua);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        // Sử dụng dữ liệu tĩnh của chính nó(adapter_lsp)
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtGiaSP.getText().length() <= 0) {
                    edtGiaSP.setError("Vui long nhap gia");
                    return;
                }
                if (edtTenSP.getText().length() <= 0) {
                    edtTenSP.setError("Vui long ghi ten");
                    return;
                }
                if (edtSoLuongSP.getText().length() <= 0) {
                    edtTenSP.setError("Vui long nhập số lượng");
                    return;
                }
                // Tạo sản phẩm mới
                HangHoa sanPham = new HangHoa();

                // thêm tên sp
                sanPham.setTenSp(edtTenSP.getText().toString());
                // thêm giá sp
                sanPham.setGiaSp(Double.valueOf(edtGiaSP.getText().toString()));
                // Thêm số lượng nhập kho
                sanPham.setSoLuongTonKho(Integer.parseInt(edtSoLuongSP.getText().toString()));
                // thêm loại sp(lưu mã loại sản phẩm)
                sanPham.setLoaiSp(selectedMaDM+"");

                // Dữ liệu tĩnh tham gia
                if (ViewSanPhamList.hangHoas.add(sanPham)) {
                    // Thêm đối tượng HangHoa vào cơ sở dữ liệu
                    dbHangHoa.ThemDL(sanPham);
                    Toast.makeText(ViewSanPhamAdd.this, "Thêm Thành Công!", Toast.LENGTH_SHORT).show();
                    ViewSanPhamList.sanPhamListAdapter.notifyDataSetChanged();// Cập nhật ListView

                }
            }
        });


        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtGiaSP.setText("");
                edtTenSP.setText("");
                spinnerDanhMuc.setSelection(0);
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                Toast.makeText(ViewSanPhamAdd.this, "Quay lai Thành công", Toast.LENGTH_SHORT).show();
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
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}