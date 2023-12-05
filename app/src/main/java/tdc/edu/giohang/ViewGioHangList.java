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
import tdc.edu.ShoppingSearch.ViewProductSearch;
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
        checkTrangThaiNutThanhToan();
        btnThanhToan_ClickEvent();
    }

    private void checkTrangThaiNutThanhToan() {
        // xử lý cộng trừ gio hàng
        // Toast.makeText(this, "Giỏ hàng rỗng", Toast.LENGTH_SHORT).show();
        // khóa thao tác thanh toán
        // tắt thông báo
        // mở khóa thao tác thanh toán
        btnThanhToan.setEnabled(ViewProductSearch.gioHang.getHangHoaList().size() != 0);
    }

    private void btnThanhToan_ClickEvent() {
        btnThanhToan.setOnClickListener(v -> {
            try {
                Date date = parseDate(edtNgayLapHoaDon.getText().toString());
                if (edttenDonHang.getText() != null) {
                    ChiTietGioHang chiTietGioHang = taoChiTietGioHang(date);
                    capNhatSoLuongTonKho();
                    dbGioHang.ThemDL(chiTietGioHang);
                    Toast.makeText(ViewGioHangList.this, "Thanh toán thành công", Toast.LENGTH_SHORT).show();
                    lamMoiGioHang();
                    checkTrangThaiNutThanhToan();

//                // gan san pham bang menu item layout(gan template item)
//                ProductItemAdapter productItemAdapter = new ProductItemAdapter(this, R.layout.layout_product_item, dbHangHoa.DocDL(), this);
//                // hien thi len listview
//                ViewProductSearch.listviewSanPhamSearch.setAdapter(productItemAdapter);
                    ViewProductSearch.productItemAdapter.notifyDataSetChanged();

                    onBackPressed();
                }
            } catch (Exception ex) {

            }
        });
    }

    private Date parseDate(String dateString) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }

    private ChiTietGioHang taoChiTietGioHang(Date date) {
        ChiTietGioHang chiTietGioHang = new ChiTietGioHang();
        chiTietGioHang.setTenDH(edttenDonHang.getText().toString());
        chiTietGioHang.setDataHangHoa(taoHoaDon());
        chiTietGioHang.setNgay(date.getDate());
        chiTietGioHang.setThang(date.getMonth() + 1);
        chiTietGioHang.setNam(date.getYear() + 1900);
        chiTietGioHang.setTongTien((int) Double.parseDouble(tvTongThanhTien.getText().toString()));
        return chiTietGioHang;
    }

    private String taoHoaDon() {
        StringBuilder msg = new StringBuilder();
        for (Map.Entry<HangHoa, Integer> entry : ViewProductSearch.gioHang.getGiohang().entrySet()) {
            HangHoa hangHoa = entry.getKey();
            int quantity = entry.getValue();
            msg.append("Tên sp: ").append(hangHoa.getTenSp());
            msg.append("\nĐơn giá: ").append((int) hangHoa.getGiaSp()).append(".........................................").append(quantity).append("\n");
        }
        return msg.toString();
    }

    private void capNhatSoLuongTonKho() {
        for (Map.Entry<HangHoa, Integer> entry : ViewProductSearch.gioHang.getGiohang().entrySet()) {
            HangHoa hangHoa = entry.getKey();
            int quantity = entry.getValue();
            hangHoa.setSoLuongTonKho(hangHoa.getSoLuongTonKho() - quantity);
            dbHangHoa.SuaDL(hangHoa);
        }
    }

    private void lamMoiGioHang() {
        ViewProductSearch.gioHang.clear();
        gioHangAdapter = new GioHangAdapter(ViewGioHangList.this, R.layout.layout_giohang_item, ViewProductSearch.gioHang.getHangHoaList(), this);
        listView.setAdapter(gioHangAdapter);
        gioHangAdapter.notifyDataSetChanged();
        ViewProductSearch.tvCartCounting.setText(String.valueOf(ViewProductSearch.gioHang.getQuantity()));
    }


    private void KhoiTao() {
        // Lấy danh sách hàng hóa từ giỏ hàng
        // Tính tổng số tiền và cập nhật tvTongThanhTien
        double tongThanhTien = ViewProductSearch.gioHang.getTongThanhTien();
        tvTongThanhTien.setText(String.valueOf(tongThanhTien));

        // Khởi tạo adapter với danh sách GioHang
        gioHangAdapter = new GioHangAdapter(this, R.layout.layout_giohang_item, ViewProductSearch.gioHang.getHangHoaList(), this);
        listView.setAdapter(gioHangAdapter);
        gioHangAdapter.notifyDataSetChanged();
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
        if (ViewProductSearch.gioHang.remove(hangHoa)) {
            capNhatGioHang();
            Toast.makeText(ViewGioHangList.this, "Xóa sản phẩm khỏi giỏ hàng thành công!", Toast.LENGTH_SHORT).show();
            checkTrangThaiNutThanhToan();
        } else {
            Toast.makeText(ViewGioHangList.this, "Sản phẩm không tồn tại trong giỏ hàng!", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onIncreaseCartItemClicked(HangHoa hangHoa) {
        ViewProductSearch.gioHang.increaseQuantity(hangHoa);
        capNhatTongThanhTien();
        capNhatSoLuongIconGioHang();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onDecreaseCartItemClicked(HangHoa hangHoa) {
        ViewProductSearch.gioHang.decreaseQuantity(hangHoa);
        capNhatGioHang();
        checkTrangThaiNutThanhToan();
    }

    private void capNhatGioHang() {
        gioHangAdapter = new GioHangAdapter(ViewGioHangList.this, R.layout.layout_giohang_item, ViewProductSearch.gioHang.getHangHoaList(), this);
        listView.setAdapter(gioHangAdapter);
        gioHangAdapter.notifyDataSetChanged();
        capNhatSoLuongIconGioHang();
    }

    @SuppressLint("SetTextI18n")
    private void capNhatTongThanhTien() {
        ViewGioHangList.tvTongThanhTien.setText((ViewProductSearch.gioHang.getTongThanhTien() + ""));
    }

    private void capNhatSoLuongIconGioHang() {
        ViewProductSearch.tvCartCounting.setText(String.valueOf(ViewProductSearch.gioHang.getQuantity()));
    }

}
