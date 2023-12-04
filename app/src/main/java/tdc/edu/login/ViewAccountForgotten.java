package tdc.edu.login;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import tdc.edu.danhsachsp.R;

public class ViewAccountForgotten extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login_forgotten);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);// hiển thị nút quay lại trang chủ
    }
}