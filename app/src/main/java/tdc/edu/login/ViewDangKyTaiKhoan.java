package tdc.edu.login;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;

import tdc.edu.danhsachsp.R;

public class ViewDangKyTaiKhoan extends AppCompatActivity {

    EditText edtTenTaiKhoan, edtCapDoTaiKhoan, edtMatKhau, edtNgayHetHanTaiKhoan, edtEmail;
    ImageView btnDangKy;

    TextView tvMessageStatus;
    DBUserAccount dbUserAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login_dangky);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
    }

    private void setEvent() {
        KhoiTao();
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtTenTaiKhoan.getText().length() <= 0) {
                    tvMessageStatus.setText("Chưa nhập tên tài khoản");
                    tvMessageStatus.setError("Chưa nhập tên tài khoản");
                    return;
                } else if (edtCapDoTaiKhoan.getText().length() <= 0) {
                    tvMessageStatus.setText("Chưa nhập cấp độ tài khoản");
                    tvMessageStatus.setError("Chưa nhập cấp độ tài khoản");
                    return;
                } else if (edtMatKhau.getText().length() <= 0) {
                    tvMessageStatus.setText("Chưa nhập Mật khẩu");
                    tvMessageStatus.setError("Chưa nhập Mật khẩu");
                    return;
                } else if (edtNgayHetHanTaiKhoan.getText().length() <= 0) {
                    tvMessageStatus.setText("Chưa nhập ngày hết hạn tài khoản");
                    tvMessageStatus.setError("Chưa nhập ngày hết hạn tài khoản");
                    return;
                } else if (edtEmail.getText().length() <= 0) {
                    tvMessageStatus.setText("Chưa nhập Email");
                    tvMessageStatus.setError("Chưa nhập Email");
                    return;
                } else {
                    tvMessageStatus.setText("");
                    tvMessageStatus.setError(null);
                }

                String tentaikhoan = edtTenTaiKhoan.getText().toString();
                String matkhau = edtMatKhau.getText().toString();
                int capdotaikhoan = Integer.valueOf(edtCapDoTaiKhoan.getText().toString());
                String ngayhethan = edtNgayHetHanTaiKhoan.getText().toString();
                String email = edtEmail.getText().toString();

                UserAccount user = new UserAccount(tentaikhoan, matkhau, ngayhethan, capdotaikhoan, email, true);
                if (dbUserAccount.ThemDL(user)) {
                    Toast.makeText(ViewDangKyTaiKhoan.this, "Dang ky thanh cong", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ViewDangKyTaiKhoan.this, "Dang ky khong thanh cong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void KhoiTao() {
        dbUserAccount = new DBUserAccount(ViewDangKyTaiKhoan.this);
    }

    private void setControl() {
        btnDangKy = findViewById(R.id.btnDangKy);
        edtMatKhau = findViewById(R.id.edtMatKhau);
        edtNgayHetHanTaiKhoan = findViewById(R.id.edtNgayHetHanTaiKhoan);
        edtTenTaiKhoan = findViewById(R.id.edtTenTaiKhoan);
        edtCapDoTaiKhoan = findViewById(R.id.edtCapDoTaiKhoan);
        tvMessageStatus = findViewById(R.id.tvMessageStatusDangKy);
        edtEmail = findViewById(R.id.edtEmail);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.nav_menu, menu);
        return true;
    }

    /**
     * Xử lý menu event
     *
     * @param item The menu item that was selected.
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        // hỗ trợ quay lại màn hình chính
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}