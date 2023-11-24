package tdc.edu.navigation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import tdc.edu.danhsachdm.DanhMuc;
import tdc.edu.danhsachsp.HangHoa;
import tdc.edu.danhsachsp.R;

public class ProtypeItemAdapter extends ArrayAdapter {
    Context context;//tham chiếu đến bộ nhớ trong quá trình app chạy

    int resource;//id cua control

    List<DanhMuc> danhMucList;//danh sách hàng hóa
    public ProtypeItemAdapter(Context context, int resource, List<DanhMuc> danhMucList) {
        super(context,resource, danhMucList);
        this.context = context;
        this.resource = resource;
        this.danhMucList = danhMucList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource,null);
        // danh sach hien thi len tung item

        TextView tvProtypeName = convertView.findViewById(R.id.tvProtypeName);


        // dau vao position set gia tri cho control
        DanhMuc hangHoa = danhMucList.get(position);
        // hien thi ten san pham
        tvProtypeName.setText(hangHoa.getTen());


        return convertView;
    }
}
