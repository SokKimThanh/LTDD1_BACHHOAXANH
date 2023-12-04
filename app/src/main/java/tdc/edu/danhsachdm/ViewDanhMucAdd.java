package tdc.edu.danhsachdm;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import tdc.edu.danhsachsp.R;

public class ViewDanhMucAdd extends AppCompatActivity {

    EditText edtTenDM, edtGhiChuDM;
    Button btnThem, btnLamMoi, btnQuayVe;

    DBDanhMuc dbDanhMuc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // hiển thị nút quay lại trang chủ
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.layout_danhmuc_add);
        setControl();
        setEvent();
    }

    private void setEvent() {
        KhoiTao();
        btnThem.setOnClickListener(v -> {

            if (edtTenDM.getText().length() <= 0) {
                edtTenDM.setError("Vui long nhap ten");
                return;
            }
            if (edtGhiChuDM.getText().length() <= 0) {
                edtGhiChuDM.setError("Vui long ghi chu");
                return;
            }
            DanhMuc danhMuc = new DanhMuc(edtTenDM.getText().toString(), edtGhiChuDM.getText().toString());

            try {
                // Thêm đối tượng DanhMuc vào cơ sở dữ liệu
                dbDanhMuc.ThemDL(danhMuc);
                Toast.makeText(ViewDanhMucAdd.this, "Thêm Thành Công!", Toast.LENGTH_SHORT).show();
                ViewDanhMucList.danhMucListAdapter.notifyDataSetChanged();// Cập nhật ListView

            } catch (Exception ex) {
                Toast.makeText(ViewDanhMucAdd.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                ViewDanhMucList.danhMucListAdapter.notifyDataSetChanged();// Cập nhật ListView
            }
        });
        btnLamMoi.setOnClickListener(v -> {

            edtTenDM.setText("");
            edtGhiChuDM.setText("");
            edtGhiChuDM.clearFocus();
        });

        btnQuayVe.setOnClickListener(v -> {
            ViewDanhMucList.danhMucListAdapter.notifyDataSetChanged();// Cập nhật ListView
            finish();
        });
    }

    private void KhoiTao() {
        // khoi tao gia tri danh sach danh muc
        // dbsinhvien truy cập dữ liệu DB
        dbDanhMuc = new DBDanhMuc(this);
    }

    private void setControl() {
        // this.edtMaDM = findViewById(R.id.edtDMEditMa);
        this.edtTenDM = findViewById(R.id.edtDMEditTen);
        this.edtGhiChuDM = findViewById(R.id.edtDMGhiChu);
        this.btnThem = findViewById(R.id.btnDMEditAdd);
        this.btnLamMoi = findViewById(R.id.btnDMEditRefresh);
        this.btnQuayVe = findViewById(R.id.btnDMEditBack);
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