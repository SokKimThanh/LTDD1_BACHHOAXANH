package tdc.edu.ShoppingSearch;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import tdc.edu.danhsachdm.DBDanhMuc;
import tdc.edu.danhsachdm.DanhMuc;
import tdc.edu.danhsachsp.DBHangHoa;
import tdc.edu.danhsachsp.HangHoa;
import tdc.edu.danhsachsp.R;
import tdc.edu.giohang.GioHang;

public class ViewProtypeProductSearch extends AppCompatActivity implements OnAddToCartClickListener {
    Context context;//tham chiếu đến bộ nhớ trong quá trình app chạy

    RadioButton radMaLoaiSP, radTenSP, radSearchBy;
    RadioGroup rgSearchBy;
    Button btnTimKiem, btnAddToCartViewHolder;

    ListView listViewDanhMucSearch, listviewSanPhamSearch;

    EditText edtSearchKeyword;
    ImageView ivProduct;



    // cập nhật lại listview bằng adapter
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

    // xác nhận dữ liệu tìm kiếm (selector)
    DanhMuc danhMucSelector;
    HangHoa hanghoaSelector;

    String radioSearchByText;

    static GioHang cart = new GioHang();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_protype_product_search);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);// hiển thị nút quay lại trang chủ



        setControl();
        setEvent();
    }

    Menu customMenu;

    private void setEvent() {
        KhoiTao();
        // kiểm tra chọn tiêu chí
        rgSearchBy.clearCheck();
        radMaLoaiSP.setChecked(true);
        radTenSP.setChecked(false);
        radioSearchByText = radMaLoaiSP.getText().toString();
        rgSearchBy.setOnCheckedChangeListener((group, checkedId) -> {
            // Xử lý sự kiện khi chọn radio button khác nhau
            radSearchBy = findViewById(checkedId);
            radioSearchByText = radSearchBy.getText().toString();
            if (radioSearchByText.equals("Loại sản phẩm")) {
                // Hiện ListView khi RadioButton được nhấn
                listViewDanhMucSearch.setVisibility(View.VISIBLE);
            }
            if (radioSearchByText.equals("Tên sản phẩm")) {
                // Ẩn ListView khi RadioButton được nhấn
                listViewDanhMucSearch.setVisibility(View.GONE);
            }
            // clear danh sach chon lai
            listViewDanhMucSearch.clearChoices();
            protypeItemAdapter.notifyDataSetChanged();

        });

        listViewDanhMucSearch.setOnItemClickListener((parent, view, position, id) -> {
            // Xử lý sự kiện khi item được chọn ở đây
            // 'position' là vị trí của item đã chọn trong ListView
            // 'id' là ID dòng của item đã chọn (nếu Adapter có)
            this.danhMucSelector = (DanhMuc) parent.getItemAtPosition(position);
        });

        listviewSanPhamSearch.setOnItemClickListener((parent, view, position, id) -> {
            // Xử lý sự kiện khi item được chọn ở đây
            // 'position' là vị trí của item đã chọn trong ListView
            // 'id' là ID dòng của item đã chọn (nếu Adapter có)
            this.hanghoaSelector = (HangHoa) parent.getItemAtPosition(position);

        });
        // kiem tra click search
        btnTimKiem.setOnClickListener(v -> {
            // Xử lý sự kiện khi nhấn nút tìm kiếm
            if (radioSearchByText.equals("Loại sản phẩm")) {
                if (Objects.isNull(danhMucSelector)) {
                    Toast.makeText(this, "Vui lòng chọn loại sản phẩm!", Toast.LENGTH_SHORT).show();
                    return;
                }
                GetDanhSachSanPhamListTheoTenVaLoai();
            }
            if (radioSearchByText.equals("Tên sản phẩm")) {
                GetDanhSachSanPhamListTheoTen();
            }
        });

    }

    private void KhoiTao() {
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
        listDanhMuc.addAll(dbDanhMuc.DocDL());

        // gan san pham bang menu item layout(gan template item)
        protypeItemAdapter = new ProtypeItemAdapter(this, R.layout.layout_protype_item, listDanhMuc);
        // hien thi len listview
        listViewDanhMucSearch.setAdapter(protypeItemAdapter);

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
        listHangHoa.addAll(dbSanPham.DocDL());

        // gan san pham bang menu item layout(gan template item)
        productItemAdapter = new ProductItemAdapter(this, R.layout.layout_product_item, listHangHoa, this);
        // hien thi len listview
        listviewSanPhamSearch.setAdapter(productItemAdapter);

        productItemAdapter.notifyDataSetChanged();
    }

    /**
     * Xử lý nút add to cart của mỗi view con
     * @param hangHoa sản phẩm thêm vào giỏ hàng
     */
    @Override
    public void onAddToCartClicked(HangHoa hangHoa) {
        // Xử lý sự kiện nhấp ở đây
        // Ví dụ: hiển thị hộp thoại xác nhận, cập nhật giỏ hàng, v.v.
        String ketqua = "Sản phẩm đã có trong giỏ hàng bạn muốn thêm nữa không?";
        // xử lý nút thêm vào giỏ hàng
        // Kiểm tra xem sản phẩm đã có trong giỏ hàng chưa
        if (cart.getHangHoaList() != null) {
            if (cart.contains(hangHoa)) {
                // Nếu có, hiển thị một hộp thoại xác nhận
                AlertDialog.Builder builder = new AlertDialog.Builder(ViewProtypeProductSearch.this);
                builder.setMessage(ketqua);
                builder.setPositiveButton("Có", (dialog, which) -> {
                    // Nếu người dùng chọn có, tăng số lượng sản phẩm trong giỏ hàng
                    cart.increaseQuantity(hangHoa);
                    // Cập nhật biểu tượng giỏ hàng
                    updateCartIcon(customMenu);
                    // Thông báo cho người dùng
                    Toast.makeText(ViewProtypeProductSearch.this, "Đã thêm sản phẩm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                });
                builder.setNegativeButton("Không", (dialog, which) -> {
                    // Nếu người dùng chọn không, đóng hộp thoại
                    dialog.dismiss();
                });
                builder.create().show();
            } else {
                // Nếu không, thêm sản phẩm vào giỏ hàng
                cart.add(hangHoa);
                // Cập nhật biểu tượng giỏ hàng
                updateCartIcon(customMenu);
                // Thông báo cho người dùng
                Toast.makeText(ViewProtypeProductSearch.this, "Đã thêm sản phẩm vào giỏ hàng", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * Hiển thị danh sách loại sản phẩm từ database
     */
    private void GetDanhSachSanPhamListTheoTenVaLoai() {
        // dbsinhvien truy cập dữ liệu DB
        dbSanPham = new DBHangHoa(this);

        listHangHoa.clear();

        // Kiểm tra xem cơ sở dữ liệu có rỗng không
        if ((long) dbSanPham.DocDL().size() <= 0) {
            Toast.makeText(this, "DB Rỗng không có dữ liệu", Toast.LENGTH_SHORT).show();
            return;
        }
        // Thêm dữ liệu từ cơ sở dữ liệu vào danh sách và cập nhật giao diện
        listHangHoa.addAll(dbSanPham.DocDLByTenSPVaLoaiSP(edtSearchKeyword.getText().toString(), danhMucSelector.getMa()));

        // gan san pham bang menu item layout(gan template item)
        productItemAdapter = new ProductItemAdapter(this, R.layout.layout_product_item, listHangHoa, this);
        // hien thi len listview
        listviewSanPhamSearch.setAdapter(productItemAdapter);

        productItemAdapter.notifyDataSetChanged();
    }

    /**
     * Hiển thị danh sách sản phẩm từ database
     */
    private void GetDanhSachSanPhamListTheoTen() {
        // dbsinhvien truy cập dữ liệu DB
        dbSanPham = new DBHangHoa(this);

        listHangHoa.clear();

        // Kiểm tra xem cơ sở dữ liệu có rỗng không
        if ((long) dbSanPham.DocDL().size() <= 0) {
            Toast.makeText(this, "DB Rỗng không có dữ liệu", Toast.LENGTH_SHORT).show();
            return;
        }
        // Thêm dữ liệu từ cơ sở dữ liệu vào danh sách và cập nhật giao diện
        listHangHoa.addAll(dbSanPham.DocDLByTenSP(edtSearchKeyword.getText().toString()));

        // gan san pham bang menu item layout(gan template item)
        productItemAdapter = new ProductItemAdapter(this, R.layout.layout_product_item, listHangHoa, this);
        // hien thi len listview
        listviewSanPhamSearch.setAdapter(productItemAdapter);

        productItemAdapter.notifyDataSetChanged();
    }

    /**
     * Xử lý ánh xạ
     */
    private void setControl() {
        // ánh xạ các nút điều khiển
        radMaLoaiSP = findViewById(R.id.radMaLoaiSP);
        radTenSP = findViewById(R.id.radTenSP);
        rgSearchBy = findViewById(R.id.rgSearchBy);
        btnTimKiem = findViewById(R.id.btnTimKiem);
        ivProduct = findViewById(R.id.ivProduct);
        // Khởi tạo adapter với this làm listener
        productItemAdapter = new ProductItemAdapter(this, R.layout.layout_product_item, listHangHoa, this);

        // ánh xạ listview hiển thị danh sách
        listviewSanPhamSearch = findViewById(R.id.dsSanPhamNavigation);
        listviewSanPhamSearch.setAdapter(productItemAdapter);

        // ánh xạ listview hiển thị danh sách
        listViewDanhMucSearch = findViewById(R.id.dsLoaiSPNavigation);

        // anh xa ô tìm kiếm theo loại từ khóa
        edtSearchKeyword = findViewById(R.id.edtSearchKeyword);

        // ánh xạ view holder button add to cart
        btnAddToCartViewHolder = findViewById(R.id.btnAddToCartProductItemAdapter);
    }


    /**
     * Xử lý menu event
     * @param item The menu item that was selected.
     *
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        // hỗ trợ quay lại màn hình chính
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Cập nhật cart item counting
     * @param customMenu menu cart item
     */
    public Menu updateCartIcon(Menu customMenu) {
        MenuItem item = customMenu.findItem(R.id.cart_menu_icon);
        MenuItemCompat.setActionView(item, R.layout.layout_quantity_shopping_cart);
        RelativeLayout notifCount = (RelativeLayout) MenuItemCompat.getActionView(item);

        TextView tvCartCounting = notifCount.findViewById(R.id.textview_cart_badge);
        tvCartCounting.setText(String.valueOf(cart.getTongSoLuong()));

        tvCartCounting.setOnClickListener(v -> Toast.makeText(ViewProtypeProductSearch.this, "action_cart : selected", Toast.LENGTH_LONG).show());
        return customMenu;
    }

    /**
     * Xử lý menu cart item = 0
     * @param menu The options menu in which you place your items.
     *
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.cart_menu, menu);
        // giá trị mặc định của cart = 0
        customMenu = updateCartIcon(menu);
        // hiển thị menu
        return super.onCreateOptionsMenu(customMenu);
    }
}