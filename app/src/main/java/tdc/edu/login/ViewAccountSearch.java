package tdc.edu.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import tdc.edu.danhsachsp.R;

public class ViewAccountSearch extends AppCompatActivity {
    LinearLayout radioGroupConditionPhanQuyen;
    RadioGroup rgCapDoTaiKhoan, rgSearchBy;
    RadioButton rbAdmin, rbUser, rbGuest;
    RadioButton rbLoaiTK, rbTenTK, rbXemTatCa;
    Button btnTimKiem;
    EditText edtSearchKeyword, edtCapDoTaiKhoan;
    ListView listViewDSTaiKhoan;

    // List data account
    List<Account> accounts = new ArrayList<>();
    // Database account
    DBAccount dbUserAccount;
    // Account item Adapter
    AccountAdapter accountAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login_search);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);// hiển thị nút quay lại trang chủ
        setControl();
        setEvent();

        // set default value control
        rgCapDoTaiKhoan.clearCheck();
        rgSearchBy.clearCheck();
    }

    /**
     * Show hide radio group button cap do tai khoan
     */
    private void ClickEventShowRadioGroupLevelAccount() {
        rgCapDoTaiKhoan.setOnCheckedChangeListener((group, checkedId) -> {
            accounts.clear();
            // Xử lý sự kiện khi chọn radio button khác nhau
            if (checkedId == rbAdmin.getId()) {
                // Hiện cấp độ   khi RadioButton được nhấn
                accounts.addAll(dbUserAccount.DocDLByCapDoTaiKhoan(AccountLevel.ADMIN.getLevelCode()));
            } else if (checkedId == rbUser.getId()) {
                // Hiện cấp độ   khi RadioButton được nhấn
                accounts.addAll(dbUserAccount.DocDLByCapDoTaiKhoan(AccountLevel.CUSTOMER.getLevelCode()));
            } else if (checkedId == rbGuest.getId()) {
                // Hiện cấp độ   khi RadioButton được nhấn
                accounts.addAll(dbUserAccount.DocDLByCapDoTaiKhoan(AccountLevel.EMPLOYEE.getLevelCode()));
            }
        });
        rgSearchBy.setOnCheckedChangeListener((group, checkedId) -> {
            accounts.clear();
            // Xử lý sự kiện khi chọn radio button khác nhau
            if (rbLoaiTK.isChecked()) {
                // Hiện khi RadioButton được nhấn
                radioGroupConditionPhanQuyen.setVisibility(View.VISIBLE);
            } else if (rbTenTK.isChecked()) {
                // Hiện  khi RadioButton được nhấn
                radioGroupConditionPhanQuyen.setVisibility(View.GONE);
            } else if (rbXemTatCa.isChecked()) {
                // Hiện khi RadioButton được nhấn
                radioGroupConditionPhanQuyen.setVisibility(View.GONE);
            }
        });
    }

    private void setEvent() {
        KhoiTao();
        ClickEventShowRadioGroupLevelAccount();
        CLickEventListViewAccountItem();
        ClickEventTimKiem();
    }

    /**
     * click event tim kiem search by loai
     */
    private void ClickEventTimKiem() {
        btnTimKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = 0;
                if (rbUser.isChecked()) {
                    i = 1;// user
                } else if (rbAdmin.isChecked()) {
                    i = 0;// admin
                } else if (rbGuest.isChecked()) {
                    i = 2;// guest
                }else{
                    i = -1;// view all
                }
                if(accounts.addAll(dbUserAccount.DocDLByCapDoTaiKhoan(i))){
                    accountAdapter = new AccountAdapter(v.getContext(), R.layout.layout_login_item, accounts);
                    // hien thi len listview
                    listViewDSTaiKhoan.setAdapter(accountAdapter);
                    accountAdapter.notifyDataSetChanged();
                }
            }
        });

    }

    private void CLickEventListViewAccountItem() {
        listViewDSTaiKhoan.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(ViewAccountSearch.this, ViewAccountForgotten.class);
            // bạn cần phải chắc chắn rằng SanPham
            // có thể được chuyển đổi thành CharSequence,
            // hoặc bạn cần thay đổi cách bạn chuyển dữ liệu
            // giữa các hoạt động.
            // Một cách để làm điều này là để làm cho lớp
            // SanPham triển khai Serializable hoặc Parcelable,
            // sau đó bạn có thể chuyển toàn bộ đối tượng qua Intent.

            //public class SanPham implements Serializable {
            // các trường và phương thức của bạn ở đây
            // }
            intent.putExtra("item", accounts.get(position));
            startActivity(intent);
        });
        //su kien long click de xoa item
        listViewDSTaiKhoan.setOnItemLongClickListener((parent, view, position, id) -> {
            Account o = accounts.get(position);
            dbUserAccount.XoaDL(String.valueOf(o.getMataikhoan()));
            accounts.remove(position);
            Toast.makeText(ViewAccountSearch.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
            return false;
        });
    }

    private void KhoiTao() {
        dbUserAccount = new DBAccount(ViewAccountSearch.this);
        accounts.clear();
        // Kiểm tra xem cơ sở dữ liệu có rỗng không
        if ((long) dbUserAccount.DocDL().size() <= 0) {
            Toast.makeText(this, "DB Rỗng không có dữ liệu", Toast.LENGTH_SHORT).show();
            return;
        }
        if(accounts.addAll(dbUserAccount.DocDLByCapDoTaiKhoan(-1))){
            accountAdapter = new AccountAdapter(ViewAccountSearch.this, R.layout.layout_login_item, accounts);
            // hien thi len listview
            listViewDSTaiKhoan.setAdapter(accountAdapter);
            accountAdapter.notifyDataSetChanged();
        }
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
        listViewDSTaiKhoan = findViewById(R.id.lvDanhSachTaiKhoan);
        // ẩn thân
        radioGroupConditionPhanQuyen = findViewById(R.id.radioGroupConditionPhanQuyen);
    }

    // gan menu add vao danh sach
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_item, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // gan su kien cho menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // TH: click menu them
        if (item.getItemId() == R.id.mnThem) {
            Intent intent = new Intent(this, ViewAccountRegister.class);
            startActivity(intent);
        }
        // hỗ trợ quay lại màn hình chính
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}