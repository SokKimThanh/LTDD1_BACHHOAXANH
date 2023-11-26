package tdc.edu.danhsachdh;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import tdc.edu.danhsachsp.R;

public class DanhSachDonHang extends AppCompatActivity {

    static List<DonHang> dataDonHang = new ArrayList<>();

    public int getSize() {
        return size;
    }

    private int size;
    static DonHangAdapter donhangAdapter;

    ListView lvDanhSachDonhang;

    Button btnEditDonHang, btnDeleteDonHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_donhang_list);
        // anh xa
        setControl();
        setEvent();
    }

    //hien thi danh sach
    private void setControl() {

        this.lvDanhSachDonhang = findViewById(R.id.lvDanhSachDonHang);
    }

    // gan menu bar
    private void setEvent() {
        // khoi tao san pham
        KhoiTao();
        // gan san pham bang menu item layout(gan template item)
        donhangAdapter = new DonHangAdapter(this, R.layout.layout_donhang_item, dataDonHang);
        // hien thi len listview
        lvDanhSachDonhang.setAdapter(donhangAdapter);
    }

    // khoi tao danh sach san pham
    private void KhoiTao() {
        ThemHoaDon(new DonHang());
    }

    private void ThemHoaDon(DonHang donHang) {
        dataDonHang.add(donHang);
        this.size++;
    }

    /// <summary>
    /// hàm xóa đơn hàng ở đầu danh sách
    /// </summary>
    /// <returns></returns>
    public boolean XoaHoaDon(DonHang donHang) {
        size--;
        return dataDonHang.remove(donHang);
    }

    // gan menu vao danh sach
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_layout, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // gan su kien cho menu

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // TH: click menu them
        if (item.getItemId() == R.id.mnThem) {
            Intent intent = new Intent(this, ThemDonHang.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}