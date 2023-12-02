package tdc.edu.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

import tdc.edu.danhsachsp.R;
import tdc.edu.navigation.ViewNavigation;

public class ViewLogin extends AppCompatActivity {

    ImageView btnDangNhap, btnShowPassword;
    EditText edtUserName, edtPassword;
    TextView tvMessageStatus;
    DBUserAccount dbUserAccount;

    List<UserAccount> userAccounts = new ArrayList<>();

    public static UserAccount currentUserAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_login);
        setControl();
        setEvent();
    }

    private void setEvent() {
        KhoiTao();
        ClickEventImageView();

    }

    private void KhoiTao() {
        dbUserAccount = new DBUserAccount(ViewLogin.this);
        userAccounts = dbUserAccount.DocDL();
    }

    private void ShowPassword() {

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

        // cập nhật dữ liệu mới
        userAccounts = dbUserAccount.DocDL();

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
            // Chuyển đến màn hình menu chính sau 3 giây
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    // Nếu đăng nhập thành công, tạo một Intent để chuyển từ ViewLogin sang ViewNavigation
                    Intent intent = new Intent(ViewLogin.this, ViewNavigation.class);
                    startActivity(intent);
                    finish();  // Đóng Activity hiện tại
                }
            }, 3000); // Độ trễ là 3 giây
        } else {
            System.out.println("Tài khoản không tồn tại trong cơ sở dữ liệu.");
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
    }

    private void setControl() {
        btnDangNhap = findViewById(R.id.btnDangNhap);
        btnShowPassword = findViewById(R.id.btnShowPassword);
        edtUserName = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassword);
        tvMessageStatus = findViewById(R.id.tvMessageStatus);
    }
}