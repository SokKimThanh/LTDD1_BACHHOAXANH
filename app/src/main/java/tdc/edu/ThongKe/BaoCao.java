package tdc.edu.ThongKe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import tdc.edu.danhsachsp.DBHangHoa;
import tdc.edu.danhsachsp.R;


public class BaoCao extends AppCompatActivity {
Button btnQuayLai;
TextView tvTongThit, tvTongCa,tvTongTrung, tvTongSua,tvTongThitBan, tvTongCaBan,tvTongTrungBan, tvTongSuaBan;
DBHangHoa dbHangHoa = new DBHangHoa(BaoCao.this);
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
        int thit = 10;
        int ca = 0;
        int trung = 0;
        int sua = 0;
        for (int i = 0; i < dbHangHoa.DocDL().size(); i++) {
            if (dbHangHoa.DocDL().get(i).getLoaiSp().equals("dm001")) {
                thit += dbHangHoa.DocDL().get(i).getSoLuongTonKho();
            }
            if (dbHangHoa.DocDL().get(i).getLoaiSp().equals("dm002")) {
                ca += dbHangHoa.DocDL().get(i).getSoLuongTonKho();
            }
            if (dbHangHoa.DocDL().get(i).getLoaiSp().equals("dm003")) {
                trung += dbHangHoa.DocDL().get(i).getSoLuongTonKho();
            }
            if (dbHangHoa.DocDL().get(i).getLoaiSp().equals("dm004")) {
                sua += dbHangHoa.DocDL().get(i).getSoLuongTonKho();
            }
        }
        tvTongCa.setText(ca + "");
        tvTongSua.setText(sua + "");
        tvTongTrung.setText(trung + "");
        tvTongThit.setText(thit + "");
//        int thitBan = 0;
//        int caBan = 0;
//        int trungBan = 0;
//        int suaBan = 0;
//        for (int i = 0;i<dbHangHoa.DocDL().size();i++){
//            if (dbHangHoa.DocDL().get(i).getLoaiSp().equals("dm001")){
//                thitBan += dbHangHoa.DocDL().get(i).getSlban();
//            }
//            if (dbHangHoa.DocDL().get(i).getLoaiSp().equals("dm002")){
//                caBan += dbHangHoa.DocDL().get(i).getSlban();
//            }
//            if (dbHangHoa.DocDL().get(i).getLoaiSp().equals("dm003")){
//                trungBan += dbHangHoa.DocDL().get(i).getSlban();
//            }
//            if (dbHangHoa.DocDL().get(i).getLoaiSp().equals("dm004")){
//                suaBan += dbHangHoa.DocDL().get(i).getSlban();
//            }
//        }
//        tvTongCa.setText(caBan+"");
//        tvTongSua.setText(suaBan+"");
//        tvTongTrung.setText(trungBan+"");
//      tvTongThit.setText(thitBan+"");
//    }
    }
    private void setConTrol() {

        btnQuayLai = findViewById(R.id.btnQuayLai);
        tvTongThit =findViewById(R.id.tvTongThit);
        tvTongTrung =findViewById(R.id.tvTongTrung);
        tvTongCa =findViewById(R.id.tvTongCa);
        tvTongSua =findViewById(R.id.tvTongSua);
        tvTongThitBan =findViewById(R.id.tvTongThitBan);
        tvTongTrungBan =findViewById(R.id.tvTongTrungBan);
        tvTongCaBan =findViewById(R.id.tvTongCaBan);
        tvTongSuaBan =findViewById(R.id.tvTongSuaBan);
    }
}