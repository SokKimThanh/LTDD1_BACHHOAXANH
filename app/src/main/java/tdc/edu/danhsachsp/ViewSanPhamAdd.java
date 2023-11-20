package tdc.edu.danhsachsp;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import tdc.edu.danhsachdm.DBDanhMuc;
import tdc.edu.danhsachdm.DanhMuc;

public class ViewSanPhamAdd extends AppCompatActivity {
    EditText edtMaSp, edtGiaSP, edtTenSP, edtSoLuongSP;
    Spinner spLoaiSP;
    Button btnAdd, btnRefresh, btnBack;


    ImageView ivHinhAdd;

    List<String> data_lsp = new ArrayList<>();
    ArrayAdapter adapter_lsp;
    DBHangHoa dbHangHoa;
    DBDanhMuc dbDanhMuc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.themsanpham_layout);
        // ánh xạ
        setControl();
        setEvent();
    }

    private void setControl() {
        this.edtMaSp = findViewById(R.id.edtMaSP);
        this.edtGiaSP = findViewById(R.id.edtGiaSP);
        this.edtTenSP = findViewById(R.id.edtTenSP);
        this.spLoaiSP = findViewById(R.id.spLoaiSP);
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
        adapter_lsp = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data_lsp);
        spLoaiSP.setAdapter(adapter_lsp);
        spLoaiSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (spLoaiSP.getSelectedItem().toString().equals("Thit")) {
                    ivHinhAdd.setImageResource(R.drawable.thit);
                }
                if (spLoaiSP.getSelectedItem().toString().equals("Ca")) {
                    ivHinhAdd.setImageResource(R.drawable.ca);
                }
                if (spLoaiSP.getSelectedItem().toString().equals("Trung")) {
                    ivHinhAdd.setImageResource(R.drawable.trung);
                }
                if (spLoaiSP.getSelectedItem().toString().equals("Sua")) {
                    ivHinhAdd.setImageResource(R.drawable.sua);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        // Sử dụng dữ liệu tĩnh của chính nó(adapter_lsp)
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtMaSp.getText().length() <= 0) {
                    edtMaSp.setError("Vui long nhap ma");
                    return;
                }
                if (edtGiaSP.getText().length() <= 0) {
                    edtGiaSP.setError("Vui long nhap gia");
                    return;
                }
                if (edtTenSP.getText().length() <= 0) {
                    edtTenSP.setError("Vui long ghi ten");
                    return;
                }

                // Tạo sản phẩm mới
                HangHoa sanPham = new HangHoa();
                // thêm mã sp
                sanPham.setMaSp(edtMaSp.getText().toString());
                // thêm giá sp
                sanPham.setGiaSp(edtGiaSP.getText().toString());
                // thêm tên sp
                sanPham.setTenSp(edtTenSP.getText().toString());
                // thêm loại sp
                sanPham.setLoaiSp(spLoaiSP.getSelectedItem().toString());

                // Thêm số lượng nhập kho
                sanPham.setSoluongNhapkho(Integer.parseInt(edtSoLuongSP.getText().toString()));

                // Dữ liệu tĩnh tham gia
                if (ViewSanPhamList.dataSp.add(sanPham)) {
                    // Thêm đối tượng HangHoa vào cơ sở dữ liệu
                    dbHangHoa.ThemDL(sanPham);
                    Toast.makeText(ViewSanPhamAdd.this, "Thêm Thành Công!", Toast.LENGTH_SHORT).show();
                    ViewSanPhamList.spAdapter.notifyDataSetChanged();// Cập nhật ListView
                }
            }
        });
        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtMaSp.setText("");
                edtGiaSP.setText("");
                edtTenSP.setText("");
                spLoaiSP.setSelection(0);
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
        for (DanhMuc danhmuc : dbDanhMuc.DocDL()) {
            data_lsp.add(danhmuc.getTen());
        }

    }
}