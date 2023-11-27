package tdc.edu.danhsachdh;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import tdc.edu.danhsachsp.DBHangHoa;
import tdc.edu.danhsachsp.R;
import tdc.edu.giohang.DBGioHang;


public class DS_DonHang extends AppCompatActivity {
Button btnQuayLai;
ListView grDSHoaDon;
ArrayAdapter adapter;
DBGioHang dbHangHoa = new DBGioHang(DS_DonHang.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ds_don_hang);
        setConTrol();
      setEvent();
    }

    private void setEvent() {
       adapter = new ArrayAdapter(DS_DonHang.this, android.R.layout.simple_list_item_1,dbHangHoa.DocDL());
    //    adapter = new ArrayAdapter(DS_DonHang.this, android.R.layout.simple_list_item_1,z);
        grDSHoaDon.setAdapter(adapter);
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
                               dbHangHoa.XoaDL(dbHangHoa.TraCuu().get(position));
                                adapter.clear();
                                adapter = new ArrayAdapter(DS_DonHang.this, android.R.layout.simple_list_item_1,dbHangHoa.DocDL());
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
    }
}