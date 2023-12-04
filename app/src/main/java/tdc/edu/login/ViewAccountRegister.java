package tdc.edu.login;

import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.Date;

import tdc.edu.danhsachsp.R;

public class ViewAccountRegister extends AppCompatActivity {

    EditText edtTenTaiKhoan, edtCapDoTaiKhoan, edtMatKhau, edtNgayHetHanTaiKhoan, edtEmail;
    ImageView btnDangKy, btnShowPassword;

    TextView tvMessageStatus;
    DBAccount dbUserAccount;
    RadioGroup rgCapDoTaiKhoan, rgNgayHetHan;
    RadioButton rbAdmin, rbUser, rbGuest;
    RadioButton rb1Thang, rb6Thang, rb1Nam;

    String ngayGiaHan_1Thang;
    String ngayGiaHan_6Thang;
    String ngayGiaHan_1Nam;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login_register);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setControl();
        PhanQuyenKhiNhapDangKy();
        setEvent();
    }

    private void PhanQuyenKhiNhapDangKy() {
        if (ViewAccountLogin.currentAccount != null) {

        }
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setEvent() {
        KhoiTao();
        rgCapDoTaiKhoan.setOnCheckedChangeListener((group, checkedId) -> {
            // Xử lý sự kiện khi chọn radio button khác nhau
            if (checkedId == rbAdmin.getId()) {
                // Hiện cấp độ   khi RadioButton được nhấn
                edtCapDoTaiKhoan.setText("0");
            } else if (checkedId == rbUser.getId()) {
                // Hiện cấp độ   khi RadioButton được nhấn
                edtCapDoTaiKhoan.setText("1");
            } else if (checkedId == rbGuest.getId()) {
                // Hiện cấp độ   khi RadioButton được nhấn
                edtCapDoTaiKhoan.setText("2");
            } else {
                rbAdmin.setChecked(true);
                edtCapDoTaiKhoan.setText("0");
            }
        });


        rgNgayHetHan.setOnCheckedChangeListener((group, checkedId) -> {
            // Xử lý sự kiện khi chọn radio button khác nhau
            if (checkedId == rb1Thang.getId()) {
                // Hiện cấp độ   khi RadioButton được nhấn
                edtNgayHetHanTaiKhoan.setText("" + ngayGiaHan_1Thang);
            } else if (checkedId == rb6Thang.getId()) {
                // Hiện cấp độ   khi RadioButton được nhấn
                edtNgayHetHanTaiKhoan.setText("" + ngayGiaHan_6Thang);
            } else if (checkedId == rb1Nam.getId()) {
                // Hiện cấp độ   khi RadioButton được nhấn
                edtNgayHetHanTaiKhoan.setText("" + ngayGiaHan_1Nam);
            }
        });
        btnDangKy.setOnClickListener(v -> {
            v.startAnimation(AnimationUtils.loadAnimation(v.getContext(), R.anim.image_click));

            if (edtTenTaiKhoan.getText().length() <= 0) {
                tvMessageStatus.setText("Chưa nhập tên tài khoản");
                tvMessageStatus.setError("Chưa nhập tên tài khoản");
                return;
            } else if (edtCapDoTaiKhoan.getText().length() <= 0) {
                tvMessageStatus.setText("Chưa Chọn cấp độ tài khoản");
                tvMessageStatus.setError("Chưa Chọn cấp độ tài khoản");
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

            Account user = new Account(tentaikhoan, matkhau, ngayhethan, capdotaikhoan, email, true);
            try {
                if (dbUserAccount.ThemDL(user)) {
                    Toast.makeText(ViewAccountRegister.this, "Dang ky thanh cong", Toast.LENGTH_SHORT).show();
                    int color = ContextCompat.getColor(getApplicationContext(), R.color.greenPrimary);
                    tvMessageStatus.setTextColor(color);
                    tvMessageStatus.setText("Đăng ký thành công!");
                } else {
                    Toast.makeText(ViewAccountRegister.this, "Dang ky khong thanh cong", Toast.LENGTH_SHORT).show();
                    int color = ContextCompat.getColor(getApplicationContext(), R.color.danger);
                    tvMessageStatus.setTextColor(color);
                    tvMessageStatus.setText("Đăng ký không thành công!");
                }
            } catch (Exception ex) {
                Toast.makeText(ViewAccountRegister.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                int color = ContextCompat.getColor(getApplicationContext(), R.color.danger);
                tvMessageStatus.setTextColor(color);
                tvMessageStatus.setText("Trùng tên tài khoản");
            }
        });

        btnShowPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(v.getContext(), R.anim.image_click));
                ShowPassword();
            }


        });

        // cap do tai khoan
        rbAdmin.setChecked(true);
        // ngay het han
        rb1Thang.setChecked(true);
    }

    boolean isChecked = true;//tắt mật khẩu

    private void ShowPassword() {
        if (isChecked) {
            // Hiển thị mật khẩu
            edtMatKhau.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            isChecked = false;
        } else {
            // Ẩn mật khẩu
            edtMatKhau.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            isChecked = true;
        }

        // Di chuyển con trỏ về cuối mật khẩu
        edtMatKhau.setSelection(edtMatKhau.getText().length());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void KhoiTao() {
        dbUserAccount = new DBAccount(ViewAccountRegister.this);
        Date d = new Date();
        String ngayGiaHan = DateFormat.format("dd/MM/yyyy", d.getTime()).toString();
        String[] dataGH = ngayGiaHan.split("/");
        int namGH_1Nam = Integer.parseInt(dataGH[2]) + 1;
        int namGH_6Thang = Integer.parseInt(dataGH[2]);
        int namGH_1Thang = Integer.parseInt(dataGH[2]);

        int thangGH_6Thang = Integer.parseInt(dataGH[1]) + 6;
        int thangGH_1Thang = Integer.parseInt(dataGH[1]) + 1;
        int thangGH_1Nam = Integer.parseInt(dataGH[1]);

        if (thangGH_6Thang > 12) {
            thangGH_6Thang = thangGH_6Thang - 12;
            namGH_6Thang++;
        }
        if (thangGH_1Thang > 12) {
            thangGH_1Thang = 1;
            namGH_1Thang++;
        }
        ngayGiaHan_1Thang = dataGH[0] + "/" + String.format("%02d", thangGH_1Thang) + "/" + namGH_1Thang;
        ngayGiaHan_6Thang = dataGH[0] + "/" + String.format("%02d", thangGH_6Thang) + "/" + namGH_6Thang;
        ngayGiaHan_1Nam = dataGH[0] + "/" + String.format("%02d", thangGH_1Nam) + "/" + namGH_1Nam;

    }

    private void setControl() {
        btnDangKy = findViewById(R.id.btnDangKy);
        btnShowPassword = findViewById(R.id.btnShowPassword);
        edtMatKhau = findViewById(R.id.edtMatKhau);
        edtNgayHetHanTaiKhoan = findViewById(R.id.edtNgayHetHanTaiKhoan);
        edtCapDoTaiKhoan = findViewById(R.id.edtAccountLevel);
        edtTenTaiKhoan = findViewById(R.id.edtTenTaiKhoan);

        tvMessageStatus = findViewById(R.id.tvMessageStatusDangKy);
        edtEmail = findViewById(R.id.edtEmail);
        rgCapDoTaiKhoan = findViewById(R.id.rgCapDoTaiKhoan);
        rbAdmin = findViewById(R.id.rbAdmin);
        rbUser = findViewById(R.id.rbUser);
        rbGuest = findViewById(R.id.rbGuest);

        rgNgayHetHan = findViewById(R.id.rgNgayHetHan);
        rb1Thang = findViewById(R.id.rb1Thang);
        rb6Thang = findViewById(R.id.rb6Thang);
        rb1Nam = findViewById(R.id.rb1nam);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_nav_item, menu);
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