package tdc.edu.danhsachsp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import tdc.edu.danhsachdh.HangHoa;

public class ChiTietSanPham extends AppCompatActivity {
    EditText   edtGiaSP, edtTenSP, edtSoLuongSPNhapKho;
    TextView tvMaSp;
    Spinner spLoaiSP;
    Button btnUpdate, btnDelete, btnBack;


    ImageView ivHinh;

    List<String> data_lsp = new ArrayList<>();
    ArrayAdapter adapter_lsp;

    // lấy 1 dữ liệu sản phẩm để cập nhật
    HangHoa sanPham;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chitietsanpham_layout);
        // ánh xạ
        setControl();
        setEvent();
    }

    private void setControl() {
        this.tvMaSp = findViewById(R.id.tvMaSp);
        this.edtGiaSP = findViewById(R.id.edtGiaSP);
        this.edtTenSP = findViewById(R.id.edtTenSP);
        this.edtSoLuongSPNhapKho = findViewById(R.id.edtSoLuongSPNhapKho);
        this.spLoaiSP = findViewById(R.id.spLoaiSP);
        this.btnUpdate = findViewById(R.id.btnUpdate);
        this.btnDelete = findViewById(R.id.btnDelete);
        this.btnBack = findViewById(R.id.btnBackChiTiet);
        this.ivHinh = findViewById(R.id.ivHinhThemSP);
     }

    /**
     * Đổ dữ liệu vào spinner loại sản phẩm
     * */
    private void setEvent() {
        KhoiTao();
        adapter_lsp = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data_lsp);
        spLoaiSP.setAdapter(adapter_lsp);
        spLoaiSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(spLoaiSP.getSelectedItem().toString().equals("Thịt")){
                    spLoaiSP.setSelection(0);
                }
                if(spLoaiSP.getSelectedItem().toString().equals("Cá")){
                    spLoaiSP.setSelection(1);
                }
                if(spLoaiSP.getSelectedItem().toString().equals("Trứng")){
                    spLoaiSP.setSelection(2);
                }
                if(spLoaiSP.getSelectedItem().toString().equals("Sữa")){
                    spLoaiSP.setSelection(2);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // Khởi tạo item để chuyển đối tượng lên màn hình
        sanPham= (HangHoa) getIntent().getSerializableExtra("item");

        //hiển thị dữ liệu lên màn hình sửa
        tvMaSp.setText(sanPham.getMaSp());// mã sp
        edtTenSP.setText(sanPham.getTenSp());// tên sp
        edtGiaSP.setText(Integer.parseInt(sanPham.getGiaSp()));// giá sp
        String soluong = String.valueOf(sanPham.getSoluongNhapkho());

        edtSoLuongSPNhapKho.setText(soluong);// soluong sp nhập kho

        // loại sản phẩm
        if(sanPham.getLoaiSp().equals("Thịt"))
            spLoaiSP.setSelection(0);
        if(sanPham.getLoaiSp().equals("Cá"))
            spLoaiSP.setSelection(1);
        if(sanPham.getLoaiSp().equals("Trứng"))
            spLoaiSP.setSelection(2);
        if(sanPham.getLoaiSp().equals("Sữa"))
            spLoaiSP.setSelection(2);
        // sự kiện update
        // Sử dụng dữ liệu tĩnh của chính nó(adapter_lsp)
        // Sự kiện xóa
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (HangHoa sp : DanhSachSanPham.dataSp){
                    //tim ma san pham de cap nhat
                    if(sp.getMaSp().equals(sanPham.getMaSp())){
                        // sua
                        sp.setTenSp(edtTenSP.getText().toString());
                        sp.setGiaSp(edtGiaSP.getText().toString());
                        sp.setSoluongNhapkho(Integer.parseInt(edtSoLuongSPNhapKho.getText().toString()));
                        sp.setLoaiSp(spLoaiSP.getSelectedItem().toString());
                        // thong bao
                        DanhSachSanPham.spAdapter.notifyDataSetChanged();
                        Toast.makeText(ChiTietSanPham.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // Sự kiện xóa
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (HangHoa sp : DanhSachSanPham.dataSp){
                    //tim ma san pham de cap nhat
                    if(sp.getMaSp().equals(sanPham.getMaSp())){
                        // xoa
                        DanhSachSanPham.dataSp.remove(sp);
                        // thong bao
                        DanhSachSanPham.spAdapter.notifyDataSetChanged();
                        Toast.makeText(ChiTietSanPham.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        // sự kiện quay lại
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void KhoiTao() {
        data_lsp.add("Thịt");
        data_lsp.add("Cá");
        data_lsp.add("Trứng");
        data_lsp.add("Sữa");
    }
}