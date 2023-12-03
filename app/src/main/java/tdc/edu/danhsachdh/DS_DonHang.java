package tdc.edu.danhsachdh;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import tdc.edu.danhsachsp.DBHangHoa;
import tdc.edu.danhsachsp.R;
import tdc.edu.giohang.ChiTietGioHang;
import tdc.edu.giohang.DBGioHang;


public class DS_DonHang extends AppCompatActivity {
Button btnQuayLai;
ListView grDSHoaDon;
AdapterDonHang adapter;
List<ChiTietGioHang> dataTheoThang = new ArrayList<>();
Spinner spThang;
List<String> dataThang = new ArrayList<>();
ArrayAdapter adapterThang;
TextView tvTongDoanhThu, tvSLDaBan;
DBGioHang dbGioHang = new DBGioHang(DS_DonHang.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ds_don_hang);
        setConTrol();
        KhoiTao();
      setEvent();

    }

    private void KhoiTao() {

        dataThang.add("Tất cả");
        dataThang.add("Tháng 1");
        dataThang.add("Tháng 2");
        dataThang.add("Tháng 3");
        dataThang.add("Tháng 4");
        dataThang.add("Tháng 5");
        dataThang.add("Tháng 6");
        dataThang.add("Tháng 7");
        dataThang.add("Tháng 8");
        dataThang.add("Tháng 9");
        dataThang.add("Tháng 10");
        dataThang.add("Tháng 11");
        dataThang.add("Tháng 12");
        adapter = new AdapterDonHang(DS_DonHang.this,R.layout.listhoadon,dbGioHang.DocDL());
        grDSHoaDon.setAdapter(adapter);
        adapterThang = new ArrayAdapter(DS_DonHang.this, android.R.layout.simple_spinner_item,dataThang);
        spThang.setAdapter(adapterThang);
        spThang.setSelection(0);
    }
    private void setEvent() {

        spThang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dataTheoThang.clear();
                adapter.notifyDataSetChanged();
             //   adapterThang.clear();

                if (spThang.getSelectedItem().equals("Tháng 1")) {
                    for (ChiTietGioHang data : dbGioHang.DocDL()) {
                        if ((int)data.getThang() == 1) {
                            dataTheoThang.add(data);
                        }
                    }

                } else  if (spThang.getSelectedItem().equals("Tháng 2")) {
                    for (ChiTietGioHang data : dbGioHang.DocDL()) {
                        if (data.getThang()==2) {
                            dataTheoThang.add(data);
                        }
                    }
                } else if (spThang.getSelectedItem().equals("Tháng 3")) {
                    for (ChiTietGioHang data : dbGioHang.DocDL()) {
                        if (data.getThang()==3) {
                            dataTheoThang.add(data);
                        }
                    }
                } else if (spThang.getSelectedItem().equals("Tháng 4")) {
                    for (ChiTietGioHang data : dbGioHang.DocDL()) {
                        if (data.getThang()==4) {
                            dataTheoThang.add(data);
                        }
                    }
                } else  if (spThang.getSelectedItem().equals("Tháng 5")) {
                    for (ChiTietGioHang data : dbGioHang.DocDL()) {
                        if (data.getThang()==5) {
                            dataTheoThang.add(data);
                        }
                    }
                } else  if (spThang.getSelectedItem().equals("Tháng 6")) {
                    for (ChiTietGioHang data : dbGioHang.DocDL()) {
                        if (data.getThang()==6) {
                            dataTheoThang.add(data);
                        }
                    }
                } else  if (spThang.getSelectedItem().equals("Tháng 7")) {
                    for (ChiTietGioHang data : dbGioHang.DocDL()) {
                        if (data.getThang()==7) {
                            dataTheoThang.add(data);
                        }
                    }
                } else  if (spThang.getSelectedItem().equals("Tháng 8")) {
                    for (ChiTietGioHang data : dbGioHang.DocDL()) {
                        if (data.getThang()==8) {
                            dataTheoThang.add(data);
                        }
                    }
                } else  if (spThang.getSelectedItem().equals("Tháng 9")) {
                    for (ChiTietGioHang data : dbGioHang.DocDL()) {
                        if (data.getThang()==9) {
                            dataTheoThang.add(data);
                        }
                    }
                } else if (spThang.getSelectedItem().equals("Tháng 10")) {
                    for (ChiTietGioHang data : dbGioHang.DocDL()) {
                        if (data.getThang()==10) {
                            dataTheoThang.add(data);
                        }
                    }
                } else if (spThang.getSelectedItem().equals("Tháng 11")) {
                    for (ChiTietGioHang data : dbGioHang.DocDL()) {
                        if (data.getThang()==11) {
                            dataTheoThang.add(data);
                        }
                    }
                } else if (spThang.getSelectedItem().equals("Tháng 12")) {
                    for (ChiTietGioHang data : dbGioHang.DocDL()) {
                        if (data.getThang()==12) {
                            dataTheoThang.add(data);
                        }
                    }
               } else {
                    for (ChiTietGioHang data : dbGioHang.DocDL()) {
                        dataTheoThang.add(data);
                    }
               }
                tvTongDoanhThu.setText("0");
                for (int i = 0;i<dataTheoThang.size();i++){
                    int tdt = Integer.parseInt(tvTongDoanhThu.getText().toString())+dataTheoThang.get(i).getTongTien();
                    tvTongDoanhThu.setText(tdt+"");

                }
                adapter = new AdapterDonHang(DS_DonHang.this,R.layout.listhoadon,dataTheoThang);
                adapter.notifyDataSetChanged();
                grDSHoaDon.setAdapter(adapter);
           }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btnQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        grDSHoaDon.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                new AlertDialog.Builder(DS_DonHang.this)
                        .setTitle("Xác nhận xóa")
                        .setMessage("Bạn có chắc chắn muốn xóa hóa đơn này không?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dbGioHang.XoaDL(dbGioHang.TraCuu().get(position));
                                adapter.clear();
                                adapter = new AdapterDonHang(DS_DonHang.this,R.layout.listhoadon,dbGioHang.DocDL());
                             grDSHoaDon.setAdapter(adapter);
                                Toast.makeText(DS_DonHang.this, "Xóa thàng công", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton(android.R.string.no, null).show();
                return false;
            }
        });

    }

    private void setConTrol() {
        btnQuayLai = findViewById(R.id.btnQuayLai);
        grDSHoaDon = findViewById(R.id.grDSHoaDon);
        tvTongDoanhThu = findViewById(R.id.tvTongDoanhThu);
        spThang = findViewById(R.id.spThang);
    }
}