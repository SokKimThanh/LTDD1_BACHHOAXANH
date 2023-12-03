package tdc.edu.danhsachdh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import tdc.edu.danhsachsp.HangHoa;
import tdc.edu.danhsachsp.R;
import tdc.edu.giohang.ChiTietGioHang;

public class AdapterDonHang extends ArrayAdapter {
    Context context;//tham chiếu đến bộ nhớ trong quá trình app chạy
    int resource;//id cua control
    List<ChiTietGioHang> data;
    public AdapterDonHang(@NonNull Context context, int resource,List<ChiTietGioHang> data) {
        super(context,resource,data);
        this.context = context;
        this.resource = resource;
        this.data = data;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource, null);
        TextView tvNgay = convertView.findViewById(R.id.tvNgay);
        TextView tvThang = convertView.findViewById(R.id.tvThang);
        TextView tvNam = convertView.findViewById(R.id.tvNam);
        TextView tvTenHD = convertView.findViewById(R.id.tvTenHD);
        TextView tvListHD = convertView.findViewById(R.id.tvListHD);
        TextView tvTongGiaTriHD = convertView.findViewById(R.id.tvTongGiaTriHoaDon);
        ChiTietGioHang chiTietGioHang =data.get(position);
        if (chiTietGioHang.getNgay()<10){
            tvNgay.setText("0"+chiTietGioHang.getNgay());
        }else {
            tvNgay.setText(chiTietGioHang.getNgay()+"");
        }
        if (chiTietGioHang.getThang()<10){
            tvThang.setText("0"+chiTietGioHang.getThang());
        }else {
            tvThang.setText(chiTietGioHang.getThang()+"");
        }

        tvNam.setText(chiTietGioHang.getNam()+"");
        tvTenHD.setText(chiTietGioHang.getTenDH());
        tvListHD.setText(chiTietGioHang.getDataHangHoa());
        tvTongGiaTriHD.setText(chiTietGioHang.getTongTien()+"");
        return convertView;
    }
// danh sach san pham (dau vao+ dau ra)

}
