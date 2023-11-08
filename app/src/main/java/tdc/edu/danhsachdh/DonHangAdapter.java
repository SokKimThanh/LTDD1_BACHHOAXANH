package tdc.edu.danhsachdh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import tdc.edu.danhsachsp.R;

public class DonHangAdapter extends ArrayAdapter {
    Context context;//tham chiếu đến bộ nhớ trong quá trình app chạy

    int resource;//id cua control

    List<DonHang> data;// danh sach san pham (dau vao+ dau ra)
    public DonHangAdapter(Context context, int resource,List<DonHang> data){
        super(context,resource,data);
        this.context = context;
        this.resource = resource;
        this.data = data;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){

        convertView = LayoutInflater.from(context).inflate(resource,null);

        // danh sach hien thi len tung item
        ImageView ivHinh = convertView.findViewById(R.id.ivHinh);
        TextView tvNgayHoaDon = convertView.findViewById(R.id.tvNgayHoaDon);
         // dau vao position set gia tri cho control
        DonHang sp = data.get(position);
        // hien thi ngay lap hoa don
//        tvNgayHoaDon.setText(sp.get_NgayDatHang());

        // hien thi toan bo du lieu len view hop le
        return convertView;
    }
}
