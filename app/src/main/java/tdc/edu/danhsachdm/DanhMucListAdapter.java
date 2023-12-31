package tdc.edu.danhsachdm;


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

public class DanhMucListAdapter extends ArrayAdapter<DanhMuc> {
    Context context;//tham chiếu đến bộ nhớ trong quá trình app chạy

    int resource;//id cua control

    List<DanhMuc> data;// danh sach danh muc (dau vao+ dau ra)
    public DanhMucListAdapter(Context context, int resource, List<DanhMuc> data){
        super(context,resource,data);
        this.context = context;
        this.resource = resource;
        this.data = data;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){

        convertView = LayoutInflater.from(context).inflate(resource,null);

        // danh sach hien thi len tung item
        ImageView ivHinhDM = convertView.findViewById(R.id.ivHinhDM);
        TextView tvTenDM = convertView.findViewById(R.id.tvTenDM);
        TextView tvMaDM = convertView.findViewById(R.id.tvMaDM);
        TextView tvGhiChuDM = convertView.findViewById(R.id.tvGhiChuDM);
        // dau vao position set gia tri cho control
        DanhMuc dm = data.get(position);
        // hien thi ten danh muc
        tvTenDM.setText(dm.getTen());
        // hien thi ma danh muc
        tvMaDM.setText(dm.getMa()+"");
        // hien thi ghi chú danh mục
        tvGhiChuDM.setText(dm.getGhichu());
        // hien thi hinh`
        if(dm.getMa()==1){
            ivHinhDM.setImageResource(R.drawable.img_thit);
        }
        if(dm.getMa()==2){
            ivHinhDM.setImageResource(R.drawable.img_ca);
        }
        if(dm.getMa()==3){
            ivHinhDM.setImageResource(R.drawable.img_trung);
        }
        if(dm.getMa()==4){
            ivHinhDM.setImageResource(R.drawable.img_sua);
        }
        // hien thi toan bo du lieu len view hop le
        return convertView;
    }
}

