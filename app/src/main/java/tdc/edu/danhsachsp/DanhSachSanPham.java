package tdc.edu.danhsachsp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import tdc.edu.danhsachdh.HangHoa;

public class DanhSachSanPham extends AppCompatActivity {
    //3 thanh phan hien thi danh sach

    //1 danh sach san pham
    //2 sanphamadapter
    //3 listview
    static List<HangHoa> dataSp = new ArrayList<>();
    static SanPhamAdapter spAdapter;

    ListView lvDanhSachSp;

    ImageView ivHinh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.danhsachsanpham_layout);
        // anh xa
        setControl();
        setEvent();
    }
   
    //hien thi danh sach
    private void setControl() {
        this.lvDanhSachSp = findViewById(R.id.lvDanhSachSp);
    }
    // gan menu bar
    private void setEvent() {
        // khoi tao san pham
        KhoiTao();
        // gan san pham bang menu item layout(gan template item)
        spAdapter = new SanPhamAdapter(this, R.layout.sanpham_layout, dataSp);
        // hien thi len listview
        lvDanhSachSp.setAdapter(spAdapter);

        // su kien click vao item de update
        lvDanhSachSp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(DanhSachSanPham.this, ChiTietSanPham.class);
                // bạn cần phải chắc chắn rằng SanPham
                // có thể được chuyển đổi thành CharSequence,
                // hoặc bạn cần thay đổi cách bạn chuyển dữ liệu
                // giữa các hoạt động.
                // Một cách để làm điều này là để làm cho lớp
                // SanPham triển khai Serializable hoặc Parcelable,
                // sau đó bạn có thể chuyển toàn bộ đối tượng qua Intent.
                /**
                 * public class SanPham implements Serializable {
                 *     // các trường và phương thức của bạn ở đây
                 * }
                 * */
                intent.putExtra("item",  dataSp.get(position));
                startActivity(intent);
            }
        });

        //su kien long click de xoa item
        lvDanhSachSp.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                dataSp.remove(position);
                spAdapter.notifyDataSetChanged();
                Toast.makeText(DanhSachSanPham.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    // khoi tao danh sach san pham
    private void KhoiTao() {
        dataSp.add(new HangHoa("san pham 1","Thịt","2000","sp1",400));
        dataSp.add(new HangHoa("san pham 2","Cá","3000","sp2",500));
        dataSp.add(new HangHoa("san pham 3","Trứng","4000","sp3",300));
        dataSp.add(new HangHoa("san pham 4","Sữa","5000","sp4",200));
    }

    // gan menu vao danh sach
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_layout,menu);
        return super.onCreateOptionsMenu(menu);
    }

    // gan su kien cho menu

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // TH: click menu them
        if(item.getItemId()==R.id.mnThem){
            Intent intent = new Intent(this, ThemSanPham.class);
            startActivity(intent);
        }

        // TH: click menu thoat
        if(item.getItemId()==R.id.mnThoat){
            finish();// ket thuc chuong trinh
        }
        return super.onOptionsItemSelected(item);
    }
}