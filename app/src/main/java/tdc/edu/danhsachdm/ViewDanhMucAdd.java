package tdc.edu.danhsachdm;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import tdc.edu.danhsachsp.R;

public class ViewDanhMucAdd extends AppCompatActivity {

    EditText edtMaDM, edtTenDM, edtGhiChuDM;
    Button btnThem, btnLamMoi, btnQuayVe;

    DBDanhMuc dbDanhMuc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.danhmuc_add_layout);
        setControl();
        setEvent();
    }

    private void setEvent() {
        KhoiTao();
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtMaDM.getText().length() <= 0) {
                    edtMaDM.setError("Vui long nhap ma");
                    return;
                }
                if (edtTenDM.getText().length() <= 0) {
                    edtTenDM.setError("Vui long nhap ten");
                    return;
                }
                if (edtGhiChuDM.getText().length() <= 0) {
                    edtGhiChuDM.setError("Vui long ghi chu");
                    return;
                }
                DanhMuc danhMuc = new DanhMuc(edtMaDM.getText().toString(), edtTenDM.getText().toString(), edtGhiChuDM.getText().toString());

                if (ViewDanhMucList.danhMucList.Them(danhMuc)) {
                    // Thêm đối tượng DanhMuc vào cơ sở dữ liệu
                    dbDanhMuc.ThemDL(danhMuc);
                    Toast.makeText(ViewDanhMucAdd.this, "Thêm Thành Công!", Toast.LENGTH_SHORT).show();
                    ViewDanhMucList.adapter.notifyDataSetChanged();// Cập nhật ListView
                }

            }
        });
        btnLamMoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtMaDM.setText("");
                edtTenDM.setText("");
                edtGhiChuDM.setText("");
                edtGhiChuDM.clearFocus();
            }
        });
        btnQuayVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void KhoiTao() {
        // khoi tao gia tri danh sach danh muc
        // dbsinhvien truy cập dữ liệu DB
        dbDanhMuc = new DBDanhMuc(this);
    }

    private void setControl() {
        this.edtMaDM = findViewById(R.id.edtDMEditMa);
        this.edtTenDM = findViewById(R.id.edtDMEditTen);
        this.edtGhiChuDM = findViewById(R.id.edtDMGhiChu);
        this.btnThem = findViewById(R.id.btnDMEditAdd);
        this.btnLamMoi = findViewById(R.id.btnDMEditRefresh);
        this.btnQuayVe = findViewById(R.id.btnDMEditBack);
    }
}