package tdc.edu.danhmucsp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import tdc.edu.danhsachsp.R;

public class ViewDanhMucList extends AppCompatActivity {
//3 thanh phan hien thi danh sach

    //1 danh sach san pham
    //2 sanphamadapter
    //3 listview
    static DanhMucList dataDM = new DanhMucList();
    static DanhMucListAdapter adapter;

    ListView lvDanhMucList;

    ImageView ivHinhDM;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.danhmuc_list_layout);
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
        // gan san pham bang menu item layout(gan template item)
        adapter = new DanhMucListAdapter(this, R.layout.danhmuc_layout, dataDM.danhMucList);
        // hien thi len listview
        lvDanhMucList.setAdapter(adapter);

        // su kien click vao item de update
        lvDanhMucList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ViewDanhMucList.this, ViewDanhMucEdit.class);
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
                intent.putExtra("item",  dataDM.danhMucList.get(position));
                startActivity(intent);
            }
        });

        //su kien long click de xoa item
//        lvDanhMucList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//
//                dataSp.remove(position);
//                spAdapter.notifyDataSetChanged();
//                Toast.makeText(DanhSachSanPham.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        });
    }

    // khoi tao danh sach san pham
    private void KhoiTao() {
        dataDM.Them(new DanhMucSP("dm001", "Thịt", "Ghi chu danh muc 1"));
        dataDM.Them(new DanhMucSP("dm002", "Cá", "Ghi chu danh muc 2"));
        dataDM.Them(new DanhMucSP("dm003", "Trứng", "Ghi chu danh muc 2"));
        dataDM.Them(new DanhMucSP("dm004", "Sữa", "Ghi chu danh muc 2"));
    }

    // gan menu vao danh sach
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_layout,menu);
//        return super.onCreateOptionsMenu(menu);
//    }

    // gan su kien cho menu

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        // TH: click menu them
//        if(item.getItemId()==R.id.mnThem){
//            Intent intent = new Intent(this, ThemSanPham.class);
//            startActivity(intent);
//        }
//
//        // TH: click menu thoat
//        if(item.getItemId()==R.id.mnThoat){
//            finish();// ket thuc chuong trinh
//        }
//        return super.onOptionsItemSelected(item);
//    }
}