package tdc.edu.danhsachsp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ViewSanPhamList extends AppCompatActivity {
    //3 thanh phan hien thi danh sach

    //1 danh sach san pham
    //2 sanphamadapter
    //3 listview
    static List<HangHoa> dataSp = new ArrayList<>();
    static SanPhamListAdapter spAdapter;

    ListView lvDanhSachSp;

    ImageView ivHinh;

    DBHangHoa dbSanPham;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_sanpham_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);// hiển thị nút quay lại trang chủ

        // anh xa
        setControl();
        setEvent();
    }

    //hien thi danh sach
    private void setControl() {
        this.lvDanhSachSp = findViewById(R.id.lvDanhSachDM);
    }

    // gan menu bar
    private void setEvent() {
        // khoi tao san pham
        KhoiTao();


        // su kien click vao item de update
        lvDanhSachSp.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(ViewSanPhamList.this, ViewSanPhamEdit.class);
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

            intent.putExtra("item", dataSp.get(position));
            startActivity(intent);
        });

        //su kien long click de xoa item
        lvDanhSachSp.setOnItemLongClickListener((parent, view, position, id) -> {
            HangHoa hh = dataSp.get(position);
            dbSanPham.XoaDL(hh);
            dataSp.remove(position);
            spAdapter.notifyDataSetChanged();
            Toast.makeText(ViewSanPhamList.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
            return false;
        });
    }

    // khoi tao danh sach san pham
    private void KhoiTao() {
        // dbsinhvien truy cập dữ liệu DB
        dbSanPham = new DBHangHoa(this);
        dataSp.clear();
        // Kiểm tra xem cơ sở dữ liệu có rỗng không
        if ((long) dbSanPham.DocDL().size() <= 0) {
            Toast.makeText(this, "DB Rỗng không có dữ liệu", Toast.LENGTH_SHORT).show();
            return;
        }
        // Thêm dữ liệu từ cơ sở dữ liệu vào danh sách và cập nhật giao diện
        for (HangHoa sanpham: dbSanPham.DocDL()) {
            dataSp.add(sanpham);
        }

        // gan san pham bang menu item layout(gan template item)
        spAdapter = new SanPhamListAdapter(this, R.layout.layout_sanpham_item, dataSp);
        // hien thi len listview
        lvDanhSachSp.setAdapter(spAdapter);
        spAdapter.notifyDataSetChanged();
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
            Intent intent = new Intent(this, ViewSanPhamAdd.class);
            startActivity(intent);
        }
        // hỗ trợ quay lại màn hình chính
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}