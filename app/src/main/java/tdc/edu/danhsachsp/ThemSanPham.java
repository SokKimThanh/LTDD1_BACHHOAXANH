package tdc.edu.danhsachsp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import tdc.edu.danhsachdh.HangHoa;

public class ThemSanPham extends AppCompatActivity {
    EditText edtMaSp, edtGiaSP, edtTenSP, edtSoLuongSP;
    Spinner spLoaiSP;
    Button btnAdd,btnRefresh, btnBack;


    ImageView ivHinh;

    List<String> data_lsp = new ArrayList<>();
    ArrayAdapter adapter_lsp;

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
        this.ivHinh = findViewById(R.id.ivHinhThemSP);
    }

    /**
     * Đổ dữ liệu vào spinner loại sản phẩm
     * */
    private void setEvent() {
        KhoiTao();
        adapter_lsp = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data_lsp);
        spLoaiSP.setAdapter(adapter_lsp);
        spLoaiSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(spLoaiSP.getSelectedItem().toString().equals("Thịt")){
                    ivHinh.setImageResource(R.drawable.thit);
                }
                if(spLoaiSP.getSelectedItem().toString().equals("Cá")){
                    ivHinh.setImageResource(R.drawable.ca);
                }
                if(spLoaiSP.getSelectedItem().toString().equals("Trứng")){
                    ivHinh.setImageResource(R.drawable.trung);
                }
                if(spLoaiSP.getSelectedItem().toString().equals("Sữa")){
                    ivHinh.setImageResource(R.drawable.sua);
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
                DanhSachSanPham.dataSp.add(sanPham);
                DanhSachSanPham.spAdapter.notifyDataSetChanged();
                Toast.makeText(ThemSanPham.this, "Thêm Thành công", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(ThemSanPham.this, "Quay lai Thành công", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void KhoiTao() {
        data_lsp.add("Thịt");
        data_lsp.add("Cá");
        data_lsp.add("Trứng");
        data_lsp.add("Sữa");
    }
}