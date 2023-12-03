package tdc.edu.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import tdc.edu.danhsachsp.R;

public class ViewAccountSearch extends AppCompatActivity {
    RadioGroup rgCapDoTaiKhoan, rgSearchBy;
    RadioButton rbAdmin, rbUser, rbGuest;
    RadioButton rbLoaiTK, rbTenTK, rbXemTatCa;
    Button btnTimKiem;
    EditText edtSearchKeyword,edtCapDoTaiKhoan;
    ListView lvDanhSachTaiKhoan;

    // List data account
    List<UserAccount> userAccounts = new ArrayList<>();
    // Database account
    DBUserAccount dbUserAccount;
    // Account item Adapter
    AccountItemAdapter accountItemAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login_search);
        setControl();
        setEvent();
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
    private void setEvent() {
        KhoiTao();
    }

    private void KhoiTao() {
        dbUserAccount = new DBUserAccount(ViewAccountSearch.this);
        userAccounts.clear();
        userAccounts.addAll(dbUserAccount.DocDL());
    }

    private void setControl() {
        // access control
        rgCapDoTaiKhoan = findViewById(R.id.rgCapDoTaiKhoan);
        rgSearchBy = findViewById(R.id.rgSearchBy);
        rbAdmin = findViewById(R.id.rbAdmin);
        rbUser = findViewById(R.id.rbUser);
        rbGuest = findViewById(R.id.rbGuest);
        rbLoaiTK = findViewById(R.id.rbLoaiTK);
        rbTenTK = findViewById(R.id.rbTenTK);
        rbXemTatCa = findViewById(R.id.rbXemTatCa);
        btnTimKiem = findViewById(R.id.btnTimKiem);
        edtSearchKeyword = findViewById(R.id.edtSearchKeyword);
        edtCapDoTaiKhoan = findViewById(R.id.edtCapDoTaiKhoan);
        lvDanhSachTaiKhoan = findViewById(R.id.lvDanhSachGioHang);

        //default value control

    }

}