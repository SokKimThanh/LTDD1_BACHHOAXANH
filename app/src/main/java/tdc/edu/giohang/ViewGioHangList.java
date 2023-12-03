package tdc.edu.giohang;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

import tdc.edu.ShoppingSearch.OnDeleteFromCartClickListener;
import tdc.edu.ShoppingSearch.ViewProtypeProductSearch;
import tdc.edu.danhsachsp.DBHangHoa;
import tdc.edu.danhsachsp.HangHoa;
import tdc.edu.danhsachsp.R;

public class ViewGioHangList extends AppCompatActivity implements OnDeleteFromCartClickListener {
    // Khởi tạo adapter với danh sách GioHang
    GioHangAdapter gioHangAdapter;
    EditText edtNgayLapHoaDon;
    // Lấy danh sách hàng hóa từ giỏ hàng


    // Cài đặt adapter cho ListView của bạn
    ListView listView;
    @SuppressLint("StaticFieldLeak")
    static TextView tvTongThanhTien;
    Button btnThanhToan;
    EditText edttenDonHang;
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
        // xử lý cộng trừ gio hàng
        if (ViewProtypeProductSearch.gioHang.getHangHoaList().size() == 0) {
            Toast.makeText(this, "Giỏ hàng rỗng", Toast.LENGTH_SHORT).show();
            // khóa thao tác thanh toán
            btnThanhToan.setEnabled(false);
        } else {
            // tắt thông báo
            // mở khóa thao tác thanh toán
            btnThanhToan.setEnabled(true);
        }
        btnThanhToan.setOnClickListener(v -> {
            Date date = new Date();
            @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            try {
                date = dateFormat.parse(edtNgayLapHoaDon.getText().toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (edttenDonHang.getText() != null) {
                ChiTietGioHang chiTietGioHang = new ChiTietGioHang();
                chiTietGioHang.setTenDH(edttenDonHang.getText().toString());
                StringBuilder msg = new StringBuilder();

                // cập nhật số lượng tồn kho
                for (Map.Entry<HangHoa, Integer> entry : ViewProtypeProductSearch.gioHang.getGiohang().entrySet()) {
                    HangHoa hangHoa = entry.getKey();
                    int quantity = entry.getValue();
                    // cập nhật số lượng tồn kho
                    hangHoa.setSoLuongTonKho(hangHoa.getSoLuongTonKho() - quantity);
                    dbHangHoa.SuaDL(hangHoa);
                    // in ra hoa don
                    msg.append("Tên sp: ").append(hangHoa.getTenSp());
                    msg.append("\nĐơn giá: ").append((int) hangHoa.getGiaSp()).append(".........................................").append(quantity).append("\n");
                }

                chiTietGioHang.setDataHangHoa(msg.toString());
                assert date != null;
                chiTietGioHang.setNgay(date.getDate());
                chiTietGioHang.setThang(date.getMonth() + 1);
                chiTietGioHang.setNam(date.getYear() + 1900);
                chiTietGioHang.setTongTien((int) Double.parseDouble(tvTongThanhTien.getText().toString()));
                dbGioHang.ThemDL(chiTietGioHang);
                Toast.makeText(ViewGioHangList.this, "Thêm thành công", Toast.LENGTH_SHORT).show();


                // Thông báo giỏ hàng clear
                ViewProtypeProductSearch.gioHang.clear();
                // Thông báo giỏ hàng cập nhật
                // this là listener delete hàng;
                gioHangAdapter = new GioHangAdapter(ViewGioHangList.this, R.layout.layout_giohang_item, ViewProtypeProductSearch.gioHang.getHangHoaList(), this);
                listView.setAdapter(gioHangAdapter);
                gioHangAdapter.notifyDataSetChanged();
                //==================================================
                // Thông báo cập nhật lại số lượng icon giỏ hàng
                //==================================================
                ViewProtypeProductSearch.tvCartCounting.setText(String.valueOf(ViewProtypeProductSearch.gioHang.getQuantity()));
                if (ViewProtypeProductSearch.gioHang.getGiohang().size() == 0) {
                    btnThanhToan.setEnabled(false);
                }
            }
        });

    }

    private void KhoiTao() {
        // Lấy danh sách hàng hóa từ giỏ hàng


        // Tính tổng số tiền và cập nhật tvTongThanhTien
        double tongThanhTien = ViewProtypeProductSearch.gioHang.getTongThanhTien();
        tvTongThanhTien.setText(String.valueOf(tongThanhTien));

        // Khởi tạo adapter với danh sách GioHang
        gioHangAdapter = new GioHangAdapter(this, R.layout.layout_giohang_item, ViewProtypeProductSearch.gioHang.getHangHoaList(), this);
        listView.setAdapter(gioHangAdapter);
        gioHangAdapter.notifyDataSetChanged();
        // Định dạng chuỗi ngày tháng năm
    }

    private void setControl() {
        // Cài đặt adapter cho ListView
        listView = findViewById(R.id.cartListView);
        // cài đặt textview tổng thành tiền
        tvTongThanhTien = findViewById(R.id.tvTongThanhTien);
        // Cài đặt button thanh toán
        btnThanhToan = findViewById(R.id.btnThanhToan);
        edttenDonHang = findViewById(R.id.edtTenDonHang);
        edtNgayLapHoaDon = findViewById(R.id.edtNgayLapHoaDon);

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

    @Override
    public void onDeleteCartItemClicked(HangHoa hangHoa) {

        if (ViewProtypeProductSearch.gioHang.remove(hangHoa)) {
            // Thông báo giỏ hàng cập nhật
            // this là listener delete hàng;
            gioHangAdapter = new GioHangAdapter(ViewGioHangList.this, R.layout.layout_giohang_item, ViewProtypeProductSearch.gioHang.getHangHoaList(), this);
            listView.setAdapter(gioHangAdapter);
            gioHangAdapter.notifyDataSetChanged();
            //==================================================
            // Thông báo cập nhật lại số lượng icon giỏ hàng
            //==================================================
            ViewProtypeProductSearch.tvCartCounting.setText(String.valueOf(ViewProtypeProductSearch.gioHang.getQuantity()));
            Toast.makeText(ViewGioHangList.this, "Xóa sản phẩm khỏi giỏ hàng thành công! SL:" + ViewProtypeProductSearch.gioHang.getQuantity(hangHoa), Toast.LENGTH_SHORT).show();
            if (ViewProtypeProductSearch.gioHang.getGiohang().size() == 0) {
                btnThanhToan.setEnabled(false);
            }
        } else {
            Toast.makeText(ViewGioHangList.this, "Sản phẩm không tồn tại trong giỏ hàng!", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onIncreaseCartItemClicked(HangHoa hangHoa) {
        ViewProtypeProductSearch.gioHang.increaseQuantity(hangHoa);
        ViewGioHangList.tvTongThanhTien.setText((ViewProtypeProductSearch.gioHang.getTongThanhTien() + ""));
        //==================================================
        // Thông báo cập nhật lại số lượng icon giỏ hàng
        //==================================================
        ViewProtypeProductSearch.tvCartCounting.setText(String.valueOf(ViewProtypeProductSearch.gioHang.getQuantity()));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onDecreaseCartItemClicked(HangHoa hangHoa) {

        ViewProtypeProductSearch.gioHang.decreaseQuantity(hangHoa);
        ViewGioHangList.tvTongThanhTien.setText((ViewProtypeProductSearch.gioHang.getTongThanhTien()) + "");
        // Thông báo giỏ hàng cập nhật
        // this là listener delete hàng;
        gioHangAdapter = new GioHangAdapter(ViewGioHangList.this, R.layout.layout_giohang_item, ViewProtypeProductSearch.gioHang.getHangHoaList(), this);
        listView.setAdapter(gioHangAdapter);
        gioHangAdapter.notifyDataSetChanged();
        //==================================================
        // Thông báo cập nhật lại số lượng icon giỏ hàng
        //==================================================
        ViewProtypeProductSearch.tvCartCounting.setText(String.valueOf(ViewProtypeProductSearch.gioHang.getQuantity()));

        if (ViewProtypeProductSearch.gioHang.getGiohang().size() == 0) {
            btnThanhToan.setEnabled(false);
        }
    }
}
