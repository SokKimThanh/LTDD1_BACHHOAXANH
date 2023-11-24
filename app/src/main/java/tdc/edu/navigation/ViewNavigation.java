package tdc.edu.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import tdc.edu.danhsachdm.ViewDanhMucList;
import tdc.edu.danhsachsp.ViewSanPhamList;
import tdc.edu.danhsachsp.R;

public class ViewNavigation extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_navigation);
        setControl();
        setEvent();
    }

    private void setEvent() {
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
                if (item.getItemId() == R.id.mnSearchSanPham) {
                    Intent intent = new Intent(ViewNavigation.this, ViewProtypeProductSearch.class);
                    startActivity(intent);
//                    Toast.makeText(ViewNavigation.this, "Tìm kiếm sản phẩm", Toast.LENGTH_SHORT).show();
                }
                drawerLayout.closeDrawers();
                return false;
            }
        });
    }

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

    }

}