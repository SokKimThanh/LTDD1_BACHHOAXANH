package tdc.edu.navigation;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import tdc.edu.danhsachdm.DBDanhMuc;
import tdc.edu.danhsachdm.DanhMuc;
import tdc.edu.danhsachdm.DanhMucList;
import tdc.edu.danhsachdm.DanhMucListAdapter;
import tdc.edu.danhsachsp.DBHangHoa;
import tdc.edu.danhsachsp.HangHoa;
import tdc.edu.danhsachsp.R;

public class ViewProtypeProductSearch extends AppCompatActivity {

    RadioButton radMaLoaiSP, radTenSP;
    RadioGroup rgSearchBy;
    Button btnTimKiem;

    ListView dsLoaiSPNavigation, dsSanPhamNavigation;

    ProductItemAdapter productItemAdapter;
    ProtypeItemAdapter protypeItemAdapter;


    //3 thanh phan hien thi danh sach

    //1 danh sach san pham
    //2 sanphamadapter
    //3 listview
    static List<HangHoa> listHangHoa = new ArrayList<>();
    //ba thanh phan hien thi danh sach
    //1 danh sach san pham
    //2 sanphamadapter
    //3 listview
    static List<DanhMuc> listDanhMuc = new ArrayList<>();

    DBHangHoa dbSanPham;
    DBDanhMuc dbDanhMuc;
    ImageView ivHinhDM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_protype_product_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);// hiển thị nút quay lại trang chủ
        Toast.makeText(ViewProtypeProductSearch.this, "search view here", Toast.LENGTH_SHORT).show();

        setControl();
        setEvent();
    }


    private void setEvent() {

        // kiem tra click search
        btnTimKiem.setOnClickListener(v -> {
            // Xử lý sự kiện khi nhấn nút tìm kiếm
            int selectedId = rgSearchBy.getCheckedRadioButtonId();
            RadioButton radioButton = findViewById(selectedId);
            String selectedText = radioButton.getText().toString();
            Toast.makeText(ViewProtypeProductSearch.this, selectedText , Toast.LENGTH_SHORT).show();
        });

        // kiểm tra chọn tiêu chí
        rgSearchBy.clearCheck();
        radMaLoaiSP.setChecked(true);
        radTenSP.setChecked(false);
        rgSearchBy.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Xử lý sự kiện khi chọn radio button khác nhau
                RadioButton radioButton = findViewById(checkedId);
                String selectedText = radioButton.getText().toString();
                Toast.makeText(ViewProtypeProductSearch.this, selectedText , Toast.LENGTH_SHORT).show();
            }
        });

        // Thêm dữ liệu vào lưới dsLoaiSPNavigation
        GetDanhSachSanPhamList();
        // Thêm dữ liệu vào lưới dsLoaiSPNavigation
        GetDanhSachDanhMucList();
    }

    private void GetDanhSachDanhMucList() {
        // dbsinhvien truy cập dữ liệu DB
        dbDanhMuc = new DBDanhMuc(this);

        listDanhMuc.clear();

        // Kiểm tra xem cơ sở dữ liệu có rỗng không
        if ((long) dbDanhMuc.DocDL().size() <= 0) {
            Toast.makeText(this, "DB Rỗng không có dữ liệu", Toast.LENGTH_SHORT).show();
            return;
        }
        // Thêm dữ liệu từ cơ sở dữ liệu vào danh sách và cập nhật giao diện
        for (DanhMuc danhMuc: dbDanhMuc.DocDL()) {
            listDanhMuc.add(danhMuc);
        }

        // gan san pham bang menu item layout(gan template item)
        protypeItemAdapter = new ProtypeItemAdapter(this, R.layout.layout_protype_item, listDanhMuc);
        // hien thi len listview
        dsLoaiSPNavigation.setAdapter(protypeItemAdapter);

        protypeItemAdapter.notifyDataSetChanged();
    }

    private void GetDanhSachSanPhamList() {
        // dbsinhvien truy cập dữ liệu DB
        dbSanPham = new DBHangHoa(this);

        listHangHoa.clear();

        // Kiểm tra xem cơ sở dữ liệu có rỗng không
        if ((long) dbSanPham.DocDL().size() <= 0) {
            Toast.makeText(this, "DB Rỗng không có dữ liệu", Toast.LENGTH_SHORT).show();
            return;
        }
        // Thêm dữ liệu từ cơ sở dữ liệu vào danh sách và cập nhật giao diện
        for (HangHoa sanpham: dbSanPham.DocDL()) {
            listHangHoa.add(sanpham);
        }

        // gan san pham bang menu item layout(gan template item)
        productItemAdapter = new ProductItemAdapter(this, R.layout.layout_product_item, listHangHoa);
        // hien thi len listview
        dsSanPhamNavigation.setAdapter(productItemAdapter);

        productItemAdapter.notifyDataSetChanged();
    }

    private void setControl() {
         //2 nút radio
        radMaLoaiSP =  findViewById(R.id.radMaLoaiSP);
        radTenSP =  findViewById(R.id.radTenSP);
        rgSearchBy =  findViewById(R.id.rgSearchBy);
        btnTimKiem =  findViewById(R.id.btnTimKiem);
        dsSanPhamNavigation = findViewById(R.id.dsSanPhamNavigation);
        dsLoaiSPNavigation = findViewById(R.id.dsLoaiSPNavigation);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        // hỗ trợ quay lại màn hình chính
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}