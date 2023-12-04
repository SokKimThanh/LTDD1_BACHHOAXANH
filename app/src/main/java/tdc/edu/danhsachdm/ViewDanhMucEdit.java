package tdc.edu.danhsachdm;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import tdc.edu.danhsachsp.R;

public class ViewDanhMucEdit extends AppCompatActivity {
    TextView tvMaDM;
    EditText edtTenDM, edtGhiChuDM;
    Button btnSua, btnXoa, btnQuayVe;

    DanhMuc danhmuc;
    DBDanhMuc dbDanhMuc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_danhmuc_edit);
        setControl();
        setEvent();
    }

    private void setEvent() {
        KhoiTao();
        btnSua.setOnClickListener(v -> {
 
            if (edtTenDM.getText().length() <= 0) {
                edtTenDM.setError("Vui long nhap ten");
                return;
            }
            if (edtGhiChuDM.getText().length() <= 0) {
                edtGhiChuDM.setError("Vui long ghi chu");
                return;
            }
            //Cập nhật thông tin mới

            danhmuc.setTen(edtTenDM.getText().toString());
            danhmuc.setGhichu(edtGhiChuDM.getText().toString());

            // Lưu
            if (ViewDanhMucList.danhMucList.Sua(danhmuc)) {
                dbDanhMuc.SuaDL(danhmuc);
                Toast.makeText(ViewDanhMucEdit.this, "Sửa Thành Công!", Toast.LENGTH_SHORT).show();
                ViewDanhMucList.danhMucListAdapter.notifyDataSetChanged();// Cập nhật ListView
            }

        });
        btnXoa.setOnClickListener(v -> new AlertDialog.Builder(ViewDanhMucEdit.this)
                .setTitle("Xác nhận xóa")
                .setMessage("Bạn có chắc chắn muốn xóa không?")
                .setIcon(R.drawable.icon_delete)
                .setPositiveButton(android.R.string.yes, (dialog, whichButton) -> {
                    // Xóa phần tử tại đây
                    if (ViewDanhMucList.danhMucList.Xoa(danhmuc)) {
                        dbDanhMuc.XoaDL(danhmuc);
                        Toast.makeText(ViewDanhMucEdit.this, "Xóa Thành Công!", Toast.LENGTH_SHORT).show();
                        ViewDanhMucList.danhMucListAdapter.notifyDataSetChanged();// Cập nhật ListView
                    }
                }).setNegativeButton(android.R.string.no, null).show());
        btnQuayVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void KhoiTao() {
        // khoi tao gia tri từ danh sach danh muc
        // dbDanhMuc truy cập dữ liệu DB
        dbDanhMuc = new DBDanhMuc(this);
        // Khởi tạo item để chuyển đối tượng lên màn hình
        danhmuc = (DanhMuc) getIntent().getSerializableExtra("item");

        //hiển thị dữ liệu lên màn hình sửa
        tvMaDM.setText(danhmuc.getMa() + "");// mã
        edtTenDM.setText(danhmuc.getTen());// tên
        edtGhiChuDM.setText(danhmuc.getGhichu());// ghi chu

    }

    private void setControl() {
        this.tvMaDM = findViewById(R.id.tvMaDM);
        this.edtTenDM = findViewById(R.id.edtDMEditTen);
        this.edtGhiChuDM = findViewById(R.id.edtDMGhiChu);
        this.btnXoa = findViewById(R.id.btnDMEditXoa);
        this.btnSua = findViewById(R.id.btnDMEditCapNhat);
        this.btnQuayVe = findViewById(R.id.btnDMEditQuayVe);
    }
}