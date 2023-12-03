package tdc.edu.login;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
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

import java.time.LocalDate;

import tdc.edu.danhsachsp.R;

public class ViewAccountRegister extends AppCompatActivity {

    EditText edtTenTaiKhoan, edtCapDoTaiKhoan, edtMatKhau, edtNgayHetHanTaiKhoan, edtEmail;
    ImageView btnDangKy, btnShowPassword;

    TextView tvMessageStatus;
    DBUserAccount dbUserAccount;
    RadioGroup rgCapDoTaiKhoan, rgNgayHetHan;
    RadioButton rbAdmin, rbUser, rbGuest;
    RadioButton rb1Thang, rb6Thang, rb1Nam;

    LocalDate today;
    LocalDate oneMonthLater;
    LocalDate sixMonthsLater;
    LocalDate oneYearLater;

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
        if(ViewAccountLogin.currentUserAccount!= null){

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
                rgCapDoTaiKhoan.clearCheck();
                rbAdmin.setChecked(true);
                rbUser.setChecked(false);
                rbGuest.setChecked(false);
                edtCapDoTaiKhoan.setText("0");
            }
        });


        rgNgayHetHan.setOnCheckedChangeListener((group, checkedId) -> {
            // Xử lý sự kiện khi chọn radio button khác nhau
            if (checkedId == rb1Thang.getId()) {
                // Hiện cấp độ   khi RadioButton được nhấn
                edtNgayHetHanTaiKhoan.setText("" + oneMonthLater);
            } else if (checkedId == rb6Thang.getId()) {
                // Hiện cấp độ   khi RadioButton được nhấn
                edtNgayHetHanTaiKhoan.setText("" + sixMonthsLater);
            } else if (checkedId == rb1Nam.getId()) {
                // Hiện cấp độ   khi RadioButton được nhấn
                edtNgayHetHanTaiKhoan.setText("" + oneYearLater);
            } else {
                rgNgayHetHan.clearCheck();
                rb1Thang.setChecked(true);
                rb6Thang.setChecked(false);
                rb1Nam.setChecked(false);
                edtNgayHetHanTaiKhoan.setText("" + today);
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

            UserAccount user = new UserAccount(tentaikhoan, matkhau, ngayhethan, capdotaikhoan, email, true);
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
        });

        btnShowPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(v.getContext(), R.anim.image_click));
                ShowPassword();
            }


        });
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
        dbUserAccount = new DBUserAccount(ViewAccountRegister.this);

        today = LocalDate.now();
        oneMonthLater = today.plusMonths(1);
        sixMonthsLater = today.plusMonths(6);
        oneYearLater = today.plusYears(1);
    }

    private void setControl() {
        btnDangKy = findViewById(R.id.btnDangKy);
        btnShowPassword = findViewById(R.id.btnShowPassword);
        edtMatKhau = findViewById(R.id.edtMatKhau);
        edtNgayHetHanTaiKhoan = findViewById(R.id.edtNgayHetHanTaiKhoan);
        edtCapDoTaiKhoan = findViewById(R.id.edtCapDoTaiKhoan);
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

//        cap do tai khoan
        rgCapDoTaiKhoan.clearCheck();
        rbAdmin.setChecked(true);
        rbUser.setChecked(false);
        rbGuest.setChecked(false);
        edtCapDoTaiKhoan.setText("0");
        edtCapDoTaiKhoan.setBackgroundColor(Color.TRANSPARENT);

        // ngay het han
        rgNgayHetHan.clearCheck();
        rb1Thang.setChecked(true);
        rb6Thang.setChecked(false);
        rb1Nam.setChecked(false);
        edtNgayHetHanTaiKhoan.setText("" + today);
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