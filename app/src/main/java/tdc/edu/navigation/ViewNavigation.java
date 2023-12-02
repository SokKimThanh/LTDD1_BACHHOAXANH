package tdc.edu.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import tdc.edu.ShoppingSearch.ViewProtypeProductSearch;
import tdc.edu.ThongKe.BaoCao;
import tdc.edu.danhsachdh.DS_DonHang;
import tdc.edu.danhsachdm.ViewDanhMucList;
import tdc.edu.danhsachsp.R;
import tdc.edu.danhsachsp.ViewSanPhamList;
import tdc.edu.login.AccountLevel;
import tdc.edu.login.UserAccount;
import tdc.edu.login.ViewLogin;

public class ViewNavigation extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Menu menuNav;
    ActionBarDrawerToggle drawerToggle;

    ImageView btnTimKiemSanPham, btnHoaDon, btnBaoCao, btnBaiVietTinTucSanPham;

    UserAccount currentUserAccount;
    AccountLevel admin = AccountLevel.ADMIN;
    AccountLevel user = AccountLevel.USER;
    AccountLevel guest = AccountLevel.GUEST;

    MenuItem mnDanhSachDanhMuc, mnDanhSachSanPham, mnHoaDon, mnSearchSanPham, mnExit, mnBaiVietTinTucSP, mnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_navigation);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
    }

    /**
     * Phân quyền menu lần đầu tiên
     *
     * @param menu The options menu as last shown or first initialized by
     *             onCreateOptionsMenu().
     * @return
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (menu == null) {
            Toast.makeText(this, "chưa có menu", Toast.LENGTH_SHORT).show();
            return false;
        }

        super.onPrepareOptionsMenu(menu);

        // Ẩn tất cả các mục menu và nút
        for (int i = 0; i < menuNav.size(); i++) {
            MenuItem item = menuNav.getItem(i);
            item.setVisible(false);
        }
        //  cài đặt tất cả menu
        mnDanhSachDanhMuc = menuNav.findItem(R.id.mnDanhSachDanhMuc);
        mnDanhSachSanPham = menuNav.findItem(R.id.mnDanhSachSanPham);
        mnSearchSanPham = menuNav.findItem(R.id.mnSearchSanPham);
        mnHoaDon = menuNav.findItem(R.id.mnHoaDon);
        mnExit = menuNav.findItem(R.id.mnExit);
        mnBaiVietTinTucSP = menuNav.findItem(R.id.mnBaiVietTinTucSP);
        mnLogout = menuNav.findItem(R.id.mnLogout);

        btnTimKiemSanPham.setVisibility(View.GONE);
        btnHoaDon.setVisibility(View.GONE);
        btnBaoCao.setVisibility(View.GONE);
        btnBaiVietTinTucSanPham.setVisibility(View.GONE);

        // Luôn hiển thị mục menu Exit
        mnExit.setVisible(true);
        mnLogout.setVisible(true);
        if (currentUserAccount == null || ViewLogin.currentUserAccount == null) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    // Nếu lỗi, tạo một Intent để chuyển từ ViewNavigation sang ViewLogin
                    Intent intent = new Intent(ViewNavigation.this, ViewLogin.class);
                    startActivity(intent);
//                    finish();  // Đóng Activity hiện tại
                }
            }, 1000); // Độ trễ là 1 giây
        }

        // Kiểm tra quyền của người dùng
        if (currentUserAccount.getCapdotaikhoan() == admin.getLevelCode()) {
            // Hiện tất cả các mục menu và nút cho admin
            for (int i = 0; i < menuNav.size(); i++) {
                MenuItem item = menuNav.getItem(i);
                item.setVisible(true);
            }
            // all button show
            btnTimKiemSanPham.setVisibility(View.VISIBLE);
            btnHoaDon.setVisibility(View.VISIBLE);
            btnBaoCao.setVisibility(View.VISIBLE);
            btnBaiVietTinTucSanPham.setVisibility(View.VISIBLE);
        } else if (currentUserAccount.getCapdotaikhoan() == user.getLevelCode()) {
            // Hiện một số mục menu và nút cho người dùng thông thường
            mnSearchSanPham.setVisible(true);
            mnBaiVietTinTucSP.setVisible(true);
            //image view button
            btnTimKiemSanPham.setVisibility(View.VISIBLE);
            btnBaiVietTinTucSanPham.setVisibility(View.VISIBLE);
        } else if (currentUserAccount.getCapdotaikhoan() == guest.getLevelCode()) {
            // Không hiện mục menu hoặc nút nào cho khách, ngoại trừ mục menu Exit, tin tuc bai viet, dang xuat
            // Ẩn tất cả các mục menu và nút
            mnBaiVietTinTucSP.setVisible(true);
            btnBaiVietTinTucSanPham.setVisibility(View.VISIBLE);
        }

        return true;
    }

    /**
     * update onPrepareOptionsMenu các lần tiếp theo
     */
    @Override
    public void invalidateOptionsMenu() {
        super.invalidateOptionsMenu();
    }

    private void setEvent() {
        KhoiTao();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.mnDanhSachDanhMuc) {
                    Intent intent = new Intent(ViewNavigation.this, ViewDanhMucList.class);
                    startActivity(intent);
//                    Toast.makeText(ViewNavigation.this, "Danh sách danh mục", Toast.LENGTH_SHORT).show();
                }
                if (item.getItemId() == R.id.mnDanhSachSanPham) {
                    Intent intent = new Intent(ViewNavigation.this, ViewSanPhamList.class);
                    startActivity(intent);
//                    Toast.makeText(ViewNavigation.this, "Danh sách sản phẩm", Toast.LENGTH_SHORT).show();
                }
                if (item.getItemId() == R.id.mnHoaDon) {
                    Intent intent = new Intent(ViewNavigation.this, DS_DonHang.class);
                    startActivity(intent);
//                    Toast.makeText(ViewNavigation.this, "Tìm kiếm sản phẩm", Toast.LENGTH_SHORT).show();
                }

                if (item.getItemId() == R.id.mnSearchSanPham) {
                    Intent intent = new Intent(ViewNavigation.this, ViewProtypeProductSearch.class);
                    startActivity(intent);
//                    Toast.makeText(ViewNavigation.this, "Tìm kiếm sản phẩm", Toast.LENGTH_SHORT).show();
                }
                if (item.getItemId() == R.id.mnLogout) {
                    Intent intent = new Intent(ViewNavigation.this, ViewLogin.class);
                    startActivity(intent);
//                    Toast.makeText(ViewNavigation.this, "Đăng nhập", Toast.LENGTH_SHORT).show();
                }


                // menu don hang
                if (item.getItemId() == R.id.mnExit) {
                    finish();
                }
                drawerLayout.closeDrawers();
                return false;
            }
        });
        btnTimKiemSanPham.setOnClickListener(v -> {
            v.startAnimation(AnimationUtils.loadAnimation(v.getContext(), R.anim.image_click));
            Intent intent = new Intent(ViewNavigation.this, ViewProtypeProductSearch.class);
            startActivity(intent);
        });
        // quan ly danh sach hoa don
        btnHoaDon.setOnClickListener(v -> {
            v.startAnimation(AnimationUtils.loadAnimation(v.getContext(), R.anim.image_click));
            Intent intent = new Intent(ViewNavigation.this, DS_DonHang.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
        });
        btnBaoCao.setOnClickListener(v -> {
            v.startAnimation(AnimationUtils.loadAnimation(v.getContext(), R.anim.image_click));
            Intent intent = new Intent(ViewNavigation.this, BaoCao.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
        });
        btnBaiVietTinTucSanPham.setOnClickListener(v -> {
            v.startAnimation(AnimationUtils.loadAnimation(v.getContext(), R.anim.image_click));
            Toast.makeText(ViewNavigation.this, "bài viết tin tức sản phẩm", Toast.LENGTH_SHORT).show();

        });
    }

    private void KhoiTao() {
        // khởi tạo tài khoản đăng nhập thành công;
        currentUserAccount = ViewLogin.currentUserAccount;

        // khởi tạo menu navigation;
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    // thanh sok
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setControl() {
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navView);
        menuNav = navigationView.getMenu();

        btnTimKiemSanPham = findViewById(R.id.btnTimKiemSanPham);
        btnHoaDon = findViewById(R.id.btnHoaDon);
        btnBaoCao = findViewById(R.id.btnBaoCao);
        btnBaiVietTinTucSanPham = findViewById(R.id.btnBaiVietTinTucSanPham);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.nav_menu, menu);
        return true;
    }
}