package tdc.edu.navigation;

import static tdc.edu.danhsachsp.R.anim;
import static tdc.edu.danhsachsp.R.id;
import static tdc.edu.danhsachsp.R.layout;
import static tdc.edu.danhsachsp.R.string;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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
import tdc.edu.login.ViewAccountLogin;
import tdc.edu.login.ViewAccountSearch;

public class ViewNavigation extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Menu menuNav;
    ActionBarDrawerToggle drawerToggle;

    ImageView btnTimKiemSanPham, btnHoaDon, btnBaoCao, btnBaiVietTinTucSanPham, btnQuanLyHoSo;

    UserAccount currentUserAccount;


    MenuItem mnDanhSachDanhMuc, mnDanhSachSanPham, mnHoaDon, mnSearchSanPham, mnExit, mnBaiVietTinTucSP, mnLogout, mnQuanLyHoSo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.layout_navigation);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
    }

    public void makeAllButtonsVisible(ViewGroup layout) {
        for (int i = 0; i < layout.getChildCount(); i++) {
            View child = layout.getChildAt(i);

            if (child instanceof ViewGroup) {
                makeAllButtonsVisible((ViewGroup) child);
            } else if (child instanceof ImageView) {
                child.setVisibility(View.VISIBLE);
            }
        }
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
        hideAllMenuItems(menuNav);
        hideAllButtons();

        // Luôn hiển thị mục menu Exit
        mnExit.setVisible(true);
        mnLogout.setVisible(true);

        if (currentUserAccount == null || ViewAccountLogin.currentUserAccount == null) {
            redirectToLoginAfterDelay(1000);
            return true;
        }
        final int admin = AccountLevel.ADMIN.getLevelCode();
        final int user = AccountLevel.USER.getLevelCode();
        final int guest = AccountLevel.GUEST.getLevelCode();

        // Kiểm tra quyền của người dùng
        int capDoTaiKhoan = currentUserAccount.getCapdotaikhoan();
        if (capDoTaiKhoan == admin) {
            // Hiện tất cả các mục menu và nút cho admin
            showAllMenuItems(menuNav);
            showAllButtons();
        } else if (capDoTaiKhoan == user) {
            // Hiện một số mục menu và nút cho người dùng thông thường
            mnSearchSanPham.setVisible(true);
            mnBaiVietTinTucSP.setVisible(true);
            btnTimKiemSanPham.setVisibility(View.VISIBLE);
            btnBaiVietTinTucSanPham.setVisibility(View.VISIBLE);
        } else if (capDoTaiKhoan == guest) {
            // Không hiện mục menu hoặc nút nào cho khách, ngoại trừ mục menu Exit, tin tuc bai viet, dang xuat
            mnBaiVietTinTucSP.setVisible(true);
            btnBaiVietTinTucSanPham.setVisibility(View.VISIBLE);
        }


        return true;
    }

    private void hideAllMenuItems(Menu menu) {
        for (int i = 0; i < menu.size(); i++) {
            MenuItem item = menu.getItem(i);
            item.setVisible(false);
        }
    }

    private void hideAllButtons() {
        btnTimKiemSanPham.setVisibility(View.GONE);
        btnHoaDon.setVisibility(View.GONE);
        btnBaoCao.setVisibility(View.GONE);
        btnBaiVietTinTucSanPham.setVisibility(View.GONE);
        btnQuanLyHoSo.setVisibility(View.GONE);
    }

    private void showAllMenuItems(Menu menu) {
        for (int i = 0; i < menu.size(); i++) {
            MenuItem item = menu.getItem(i);
            item.setVisible(true);
        }
    }

    private void showAllButtons() {
        btnTimKiemSanPham.setVisibility(View.VISIBLE);
        btnHoaDon.setVisibility(View.VISIBLE);
        btnBaoCao.setVisibility(View.VISIBLE);
        btnBaiVietTinTucSanPham.setVisibility(View.VISIBLE);
        btnQuanLyHoSo.setVisibility(View.VISIBLE);
    }

    private void redirectToLoginAfterDelay(int delayMillis) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Nếu lỗi, tạo một Intent để chuyển từ ViewNavigation sang ViewAccountLogin
                Intent intent = new Intent(ViewNavigation.this, ViewAccountLogin.class);
                startActivity(intent);
            }
        }, delayMillis); // Độ trễ là 1 giây
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

        navigationView.setNavigationItemSelectedListener(item -> {
            // Chuyển đến màn hình menu chính sau 0.5 giây
            new Handler().postDelayed(new Runnable() {
                @SuppressLint("NonConstantResourceId")
                @Override
                public void run() {
                    // Danh sách danh mục
                    if (item.getItemId() == id.mnDanhSachDanhMuc) {
                        Intent intent = new Intent(ViewNavigation.this, ViewDanhMucList.class);
                        startActivity(intent);

                    }
                    // Danh sách sản phẩm
                    if (item.getItemId() == id.mnDanhSachSanPham) {
                        Intent intent = new Intent(ViewNavigation.this, ViewSanPhamList.class);
                        startActivity(intent);
                    }
                    // Danh sách đơn hàng
                    if (item.getItemId() == id.mnHoaDon) {
                        Intent intent = new Intent(ViewNavigation.this, DS_DonHang.class);
                        startActivity(intent);
                    }
                    // Tìm kiếm sản phẩm
                    if (item.getItemId() == id.mnSearchSanPham) {
                        Intent intent = new Intent(ViewNavigation.this, ViewProtypeProductSearch.class);
                        startActivity(intent);
                    }
                    // Đăng nhập
                    if (item.getItemId() == id.mnLogout) {
                        Intent intent = new Intent(ViewNavigation.this, ViewAccountLogin.class);
                        startActivity(intent);
                    }
                    // Quản lý hồ sơ
                    if (item.getItemId() == id.mnQuanLyHoSo) {
                        Intent intent = new Intent(ViewNavigation.this, ViewAccountSearch.class);
                        startActivity(intent);
                    }
                    // Thoát ứng dụng
                    if (item.getItemId() == id.mnExit) {
                        finish();
                    }

                }
            }, 500); // Độ trễ là 0.5 giây

            drawerLayout.closeDrawers();
            return false;
        });
        btnTimKiemSanPham.setOnClickListener(v -> {
            v.startAnimation(AnimationUtils.loadAnimation(v.getContext(), anim.image_click));
            // Chuyển đến màn hình kế sau 0.5 giây
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent intent = new Intent(ViewNavigation.this, ViewProtypeProductSearch.class);
                    startActivity(intent);
                }
            }, 500); // Độ trễ là 0.5 giây

        });
        // quan ly danh sach hoa don
        btnHoaDon.setOnClickListener(v -> {
            v.startAnimation(AnimationUtils.loadAnimation(v.getContext(), anim.image_click));

            // Chuyển đến màn hình kế sau 0.5 giây
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent intent = new Intent(ViewNavigation.this, DS_DonHang.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent);
                }
            }, 500); // Độ trễ là 0.5 giây
        });
        btnBaoCao.setOnClickListener(v -> {
            v.startAnimation(AnimationUtils.loadAnimation(v.getContext(), anim.image_click));
            // Chuyển đến màn hình kế sau 0.5 giây
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(ViewNavigation.this, BaoCao.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent);
                }
            }, 500); // Độ trễ là 0.5 giây

        });
        btnBaiVietTinTucSanPham.setOnClickListener(v -> {
            v.startAnimation(AnimationUtils.loadAnimation(v.getContext(), anim.image_click));

            // Chuyển đến màn hình kế sau 0.5 giây
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(ViewNavigation.this, "bài viết tin tức sản phẩm", Toast.LENGTH_SHORT).show();
                }
            }, 500); // Độ trễ là 0.5 giây
        });
        btnQuanLyHoSo.setOnClickListener(v -> {
            v.startAnimation(AnimationUtils.loadAnimation(v.getContext(), anim.image_click));

            // Chuyển đến màn hình kế sau 0.5 giây
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(ViewNavigation.this, ViewAccountSearch.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent);
                }
            }, 500); // Độ trễ là 0.5 giây
        });
    }

    private void KhoiTao() {
        // khởi tạo tài khoản đăng nhập thành công;
        currentUserAccount = ViewAccountLogin.currentUserAccount;

        // khởi tạo menu navigation;
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, string.app_name, string.app_name);
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
        drawerLayout = findViewById(id.drawerLayout);
        navigationView = findViewById(id.navView);
        menuNav = navigationView.getMenu();

        btnTimKiemSanPham = findViewById(id.btnTimKiemSanPham);
        btnHoaDon = findViewById(id.btnHoaDon);
        btnBaoCao = findViewById(id.btnBaoCao);
        btnBaiVietTinTucSanPham = findViewById(id.btnBaiVietTinTucSanPham);
        btnQuanLyHoSo = findViewById(id.btnQuanLyHoSo);

        //  cài đặt tất cả menu
        mnDanhSachDanhMuc = menuNav.findItem(R.id.mnDanhSachDanhMuc);
        mnDanhSachSanPham = menuNav.findItem(R.id.mnDanhSachSanPham);
        mnSearchSanPham = menuNav.findItem(R.id.mnSearchSanPham);
        mnHoaDon = menuNav.findItem(R.id.mnHoaDon);
        mnExit = menuNav.findItem(R.id.mnExit);
        mnBaiVietTinTucSP = menuNav.findItem(R.id.mnBaiVietTinTucSP);
        mnLogout = menuNav.findItem(R.id.mnLogout);
        mnQuanLyHoSo = menuNav.findItem(R.id.mnQuanLyHoSo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_nav_item, menu);
        return true;
    }
}