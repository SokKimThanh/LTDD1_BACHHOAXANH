package tdc.edu.giohang;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.Objects;

import tdc.edu.ShoppingSearch.ViewProtypeProductSearch;
import tdc.edu.danhsachsp.HangHoa;
import tdc.edu.danhsachsp.R;

public class ViewGioHangList extends AppCompatActivity {
    // Khởi tạo adapter với danh sách GioHang
    GioHangAdapter adapter;


    // Lấy danh sách hàng hóa từ giỏ hàng
    List<HangHoa> hangHoaList;

    // Cài đặt adapter cho ListView của bạn
    ListView listView;
    TextView tvTongThanhTien;
    Button btnThanhToan;
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
        // xử lý cộng trừ gio hàng
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
