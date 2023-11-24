package tdc.edu.navigation;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import tdc.edu.danhsachsp.R;

public class ViewProtypeProductSearch extends AppCompatActivity {

    RadioButton radMaLoaiSP, radTenSP;
    RadioGroup rgSearchBy;
    Button btnTimKiem;

    ListView dsLoaiSPNavigation, dsSanPhamNavigation;

    ConvertViewProductItemAdapter protypeProductItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_protype_product_list);
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
    }

    private void setControl() {
         //2 nút radio
        radMaLoaiSP =  findViewById(R.id.radMaLoaiSP);
        radTenSP =  findViewById(R.id.radTenSP);
        rgSearchBy =  findViewById(R.id.rgSearchBy);
        btnTimKiem =  findViewById(R.id.btnTimKiem);
    }
}