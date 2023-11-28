package tdc.edu.giohang;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import tdc.edu.ShoppingSearch.ViewProtypeProductSearch;
import tdc.edu.danhsachsp.DBHangHoa;
import tdc.edu.danhsachsp.HangHoa;
import tdc.edu.danhsachsp.R;

public class ViewGioHangList extends AppCompatActivity {
    // Khởi tạo adapter với danh sách GioHang
    GioHangAdapter adapter;


    // Lấy danh sách hàng hóa từ giỏ hàng
    List<HangHoa> hangHoaList;

    // Cài đặt adapter cho ListView của bạn
    ListView listView;
    @SuppressLint("StaticFieldLeak")
    static TextView tvTongThanhTien;
    Button btnThanhToan;
    EditText edttenDonHang;
    ImageView ivGioHangNull;
    DBGioHang dbGioHang = new DBGioHang(ViewGioHangList.this);
    DBHangHoa dbHangHoa = new DBHangHoa(ViewGioHangList.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_giohang_list);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);// hiển thị nút quay lại trang chủ
        setControl();
        setEvent();
    }

    private void setEvent() {
        KhoiTao();
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        // xử lý cộng trừ gio hàng
        if (hangHoaList.size() == 0) {
            Toast.makeText(this, "Giỏ hàng rỗng", Toast.LENGTH_SHORT).show();
            // khóa thao tác thanh toán
            btnThanhToan.setEnabled(false);
        } else {
            // tắt thông báo
            // mở khóa thao tác thanh toán
            btnThanhToan.setEnabled(true);
        }
        btnThanhToan.setOnClickListener(v -> {


            if (edttenDonHang.getText() != null) {
                StringBuilder msg = new StringBuilder("                               " + day + "/" + month + "/" + year + "\n");
                msg.append("\n                                  ").append(edttenDonHang.getText().toString()).append("\n\n");
                for (int i = 0; i < hangHoaList.size(); i++) {

                    HangHoa hh = hangHoaList.get(i);
                    // cập nhật số lượng tồn kho
                    hh.setSoLuongTonKho(hh.getSoLuongTonKho() - hh.getSlban());
                    dbHangHoa.SuaDL(hh);
                    // in thông tin mặt hàng
                    msg.append("Tên sản phẩm: ").append(hh.getTenSp()).append("\n").append("Giá: ").append(hh.getGiaSp()).append("VND                        sl: ").append(hh.getSlban()).append("\n");
                }
                msg.append("\nTổng tiền: ").append(tvTongThanhTien.getText().toString()).append(" VND");
                dbGioHang.ThemDL(edttenDonHang.getText().toString(), msg.toString(), tvTongThanhTien.toString());
                Toast.makeText(ViewGioHangList.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void KhoiTao() {
        // Lấy danh sách hàng hóa từ giỏ hàng
        hangHoaList = ViewProtypeProductSearch.gioHang.getHangHoaList();

        // Tính tổng số tiền và cập nhật tvTongThanhTien
        double tongThanhTien = ViewProtypeProductSearch.gioHang.getTongThanhTien();
        tvTongThanhTien.setText(String.valueOf(tongThanhTien));

        // Khởi tạo adapter với danh sách GioHang
        adapter = new GioHangAdapter(this, hangHoaList);
        listView.setAdapter(adapter);
    }

    private void setControl() {
        // Cài đặt adapter cho ListView
        listView = findViewById(R.id.cartListView);
        // cài đặt textview tổng thành tiền
        tvTongThanhTien = findViewById(R.id.tvTongThanhTien);
        // Cài đặt button thanh toán
        btnThanhToan = findViewById(R.id.btnThanhToan);
        edttenDonHang = findViewById(R.id.edtTenDonHang);

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
