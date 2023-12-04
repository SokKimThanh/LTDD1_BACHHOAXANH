package tdc.edu.danhsachdm;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.Objects;

import tdc.edu.danhsachsp.R;

public class ViewDanhMucList extends AppCompatActivity {

    DBDanhMuc dbDanhMuc;
    //ba thanh phan hien thi danh sach
    //1 danh sach san pham
    //2 sanphamadapter
    //3 listview
    static DanhMucList danhMucList;

    @SuppressLint("StaticFieldLeak")
    static DanhMucListAdapter danhMucListAdapter;

    static ListView lvDanhMucList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_danhmuc_list);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);// hiển thị nút quay lại trang chủ

        // anh xa
        setControl();
        setEvent();
    }


    //hien thi danh sach
    private void setControl() {

        this.lvDanhMucList = findViewById(R.id.lvDanhSachDM);
    }

    // gan menu bar
    private void setEvent() {
        // khoi tao san pham
        KhoiTao();

        // su kien click vao item de update
        lvDanhMucList.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(ViewDanhMucList.this, ViewDanhMucEdit.class);
            // bạn cần phải chắc chắn rằng DanhMuc
            // có thể được chuyển đổi thành CharSequence,
            // hoặc bạn cần thay đổi cách bạn chuyển dữ liệu
            // giữa các hoạt động.
            // Một cách để làm điều này là để làm cho lớp
            // DanhMuc triển khai Serializable hoặc Parcelable,
            // sau đó bạn có thể chuyển toàn bộ đối tượng qua Intent.
            intent.putExtra("item", danhMucList.getDanhMucList().get(position));
            startActivity(intent);
        });

        //su kien long click de xoa item
    }

    // khoi tao danh sach san pham
    private void KhoiTao() {
        danhMucList  = new DanhMucList();
        // dbDanhMuc truy cập dữ liệu DB
        dbDanhMuc = new DBDanhMuc(this);

        danhMucList.getDanhMucList().clear();

        // Kiểm tra xem cơ sở dữ liệu có rỗng không
        if ((long) dbDanhMuc.DocDL().size() <= 0) {
            Toast.makeText(this, "DB Rỗng không có dữ liệu", Toast.LENGTH_SHORT).show();
            return;
        }
        // Thêm dữ liệu từ cơ sở dữ liệu vào danh sách và cập nhật giao diện
        danhMucList.setDanhMucList(dbDanhMuc.DocDL());

        // gan san pham bang menu item layout(gan template item)
        danhMucListAdapter = new DanhMucListAdapter(this, R.layout.layout_danhmuc_item, danhMucList.getDanhMucList());
        // hien thi len listview
        lvDanhMucList.setAdapter(danhMucListAdapter);

        // Sau khi cập nhật dữ liệu
        this.danhMucListAdapter. notifyDataSetChanged();
    }

    // gan menu vao danh sach
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
            Intent intent = new Intent(this, ViewDanhMucAdd.class);
            startActivity(intent);
        }


        // Xử lý sự kiện khi nhấn vào nút hỗ trợ
        // Nếu id của item là android.R.id.home, tức là nút hỗ trợ
        if (item.getItemId() == android.R.id.home) {
            // Gọi phương thức onBackPressed() để quay về màn hình trước đó
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

}