package tdc.edu.ThongKe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import tdc.edu.danhsachsp.R;


public class BaoCao extends AppCompatActivity {
Button btnQuayLai;
TextView tvTongThit, tvTongCa,tvTongTrung, tvTongSua;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bao_cao);
        setConTrol();
        setEvent();
    }

    private void setEvent() {


        btnQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setConTrol() {

        btnQuayLai = findViewById(R.id.btnQuayLai);
        tvTongThit =findViewById(R.id.tvTongThit);
        tvTongTrung =findViewById(R.id.tvTongTrung);
        tvTongCa =findViewById(R.id.tvTongCa);
        tvTongSua =findViewById(R.id.tvTongSua);
    }
}