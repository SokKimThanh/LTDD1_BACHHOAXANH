package tdc.edu.login;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

import tdc.edu.danhsachsp.R;
import tdc.edu.navigation.ViewNavigation;

public class ViewLogin extends AppCompatActivity {

    ImageView btnDangNhap, btnShowPassword;
    EditText edtUserName, edtPassword, edtCapDoTaiKhoan;
    TextView tvMessageStatus, tvDangKy, tvQuenMatKhau;
    DBUserAccount dbUserAccount;

    List<UserAccount> userAccounts = new ArrayList<>();

    RadioGroup rgCapDoTaiKhoan;
    RadioButton rbAdmin, rbUser, rbGuest;
    public static UserAccount currentUserAccount = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login_view);
        setControl();
        setEvent();
    }

    private void setEvent() {
        KhoiTao();
        ClickEventImageView();
        ClickEventLevelAccount();
    }

    private void ClickEventLevelAccount() {
        rgCapDoTaiKhoan.setOnCheckedChangeListener((group, checkedId) -> {
            userAccounts.clear();
            // Xử lý sự kiện khi chọn radio button khác nhau
            if (checkedId == rbAdmin.getId()) {
                // Hiện cấp độ   khi RadioButton được nhấn
                userAccounts.addAll(dbUserAccount.DocDLByCapDoTaiKhoan(AccountLevel.ADMIN.getLevelCode()));
            } else if (checkedId == rbUser.getId()) {
                // Hiện cấp độ   khi RadioButton được nhấn
                userAccounts.addAll(dbUserAccount.DocDLByCapDoTaiKhoan(AccountLevel.USER.getLevelCode()));
            } else if (checkedId == rbGuest.getId()) {
                // Hiện cấp độ   khi RadioButton được nhấn
                userAccounts.addAll(dbUserAccount.DocDLByCapDoTaiKhoan(AccountLevel.GUEST.getLevelCode()));
            } else {
                rgCapDoTaiKhoan.clearCheck();
                rbAdmin.setChecked(true);
                rbUser.setChecked(false);
                rbGuest.setChecked(false);
                userAccounts.addAll(dbUserAccount.DocDLByCapDoTaiKhoan(AccountLevel.USER.getLevelCode()));
            }
        });
    }

    private void KhoiTao() {
        dbUserAccount = new DBUserAccount(ViewLogin.this);
        userAccounts.clear();
        userAccounts.addAll(dbUserAccount.DocDLByCapDoTaiKhoan(AccountLevel.USER.getLevelCode()));
    }

    boolean isChecked = true;//tắt mật khẩu

    private void ShowPassword() {
        if (isChecked) {
            // Hiển thị mật khẩu
            edtPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            isChecked = false;
        } else {
            // Ẩn mật khẩu
            edtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            isChecked = true;
        }

        // Di chuyển con trỏ về cuối mật khẩu
        edtPassword.setSelection(edtPassword.getText().length());
    }

    private void DangNhap() {
        if (edtUserName.length() <= 0) {
            // Thiết lập thông báo lỗi
            tvMessageStatus.setError("Tên đăng nhập không được để trống");
            tvMessageStatus.setText("Tên đăng nhập không được để trống");
            return;
        } else if (edtPassword.length() <= 0) {
            // Thiết lập thông báo lỗi
            tvMessageStatus.setError("Mật khẩu không được để trống");
            tvMessageStatus.setText("Mật khẩu không được để trống");
            return;
        } else {
            // Hủy thông báo lỗi
            tvMessageStatus.setError(null);
            tvMessageStatus.setText("");
        }

        String usernameToCheck = edtUserName.getText().toString();
        String passwordToCheck = edtPassword.getText().toString();
        boolean isExist = false;


        // check thông tin
        for (UserAccount user : userAccounts) {
            if (user.getTentaikhoan().equals(usernameToCheck)) {
                if (user.getMatkhau().equals(passwordToCheck)) {
                    currentUserAccount = user;// tìm thấy thông tin
                    isExist = true;
                    break;
                }
            }
        }

        if (isExist) {
            //  System.out.println("Tài khoản đã tồn tại trong cơ sở dữ liệu.");
            int color = ContextCompat.getColor(getApplicationContext(), R.color.greenPrimary);
            tvMessageStatus.setTextColor(color);
            tvMessageStatus.setText("Đăng nhập thành công");
            // Chuyển đến màn hình menu chính sau 0.5 giây
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    // Nếu đăng nhập thành công, tạo một Intent để chuyển từ ViewLogin sang ViewNavigation
                    Intent intent = new Intent(ViewLogin.this, ViewNavigation.class);
                    startActivity(intent);
                    finish();  // Đóng Activity hiện tại
                }
            }, 500); // Độ trễ là 0.5 giây
        } else {
            Toast.makeText(this, "Tài khoản không tồn tại trong cơ sở dữ liệu.", Toast.LENGTH_SHORT).show();
            int color = ContextCompat.getColor(getApplicationContext(), R.color.danger);
            tvMessageStatus.setTextColor(color);
            tvMessageStatus.setText("Tên đăng nhập/ Mật khẩu chưa đúng");
        }
    }

    private void ClickEventImageView() {

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(v.getContext(), R.anim.image_click));

                DangNhap();
            }
        });
        btnShowPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(v.getContext(), R.anim.image_click));
                ShowPassword();
            }


        });
        tvDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(v.getContext(), R.anim.image_click));
                DangKy();
            }


        });

        tvQuenMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(v.getContext(), R.anim.image_click));
                QuenMatKhau();
            }


        });

    }

    private void DangKy() {
        Intent intent = new Intent(ViewLogin.this, ViewDangKyTaiKhoan.class);
        startActivity(intent);
    }

    private void QuenMatKhau() {
        Intent intent = new Intent(ViewLogin.this, ViewQuenMatKhau.class);
        startActivity(intent);
    }

    private void setControl() {
        btnDangNhap = findViewById(R.id.btnDangNhap);
        btnShowPassword = findViewById(R.id.btnShowPassword);
        edtUserName = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassword);
        tvMessageStatus = findViewById(R.id.tvMessageStatus);
        tvDangKy = findViewById(R.id.tvDangKy);
        tvQuenMatKhau = findViewById(R.id.tvQuenMatKhau);
        edtCapDoTaiKhoan = findViewById(R.id.edtCapDoTaiKhoan);

        rgCapDoTaiKhoan = findViewById(R.id.rgCapDoTaiKhoan);
        rbAdmin = findViewById(R.id.rbAdmin);
        rbUser = findViewById(R.id.rbUser);
        rbGuest = findViewById(R.id.rbGuest);

        // cap do tai khoan
        rgCapDoTaiKhoan.clearCheck();
        rbUser.setChecked(true);//khách hàng
        rbAdmin.setChecked(false);
        rbGuest.setChecked(false);
        edtCapDoTaiKhoan.setText("0");
        edtCapDoTaiKhoan.setBackgroundColor(Color.TRANSPARENT);
    }
}