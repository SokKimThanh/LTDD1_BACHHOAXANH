package tdc.edu.giohang;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import tdc.edu.ShoppingSearch.ViewProtypeProductSearch;
import tdc.edu.danhsachsp.HangHoa;
import tdc.edu.danhsachsp.R;

public class GioHangAdapter extends ArrayAdapter<HangHoa> {

    public GioHangAdapter(Context context, List<HangHoa> cartItems) {
        super(context, 0, cartItems);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Lấy dữ liệu item cho vị trí này
        HangHoa hangHoa = getItem(position);
         // Kiểm tra xem một view đã tồn tại chưa, nếu không thì inflate
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_giohang_item, parent, false);
        }

        // Tìm kiếm view cho dữ liệu trong layout
        TextView tvName = convertView.findViewById(R.id.tvProductName);
        TextView tvPrice = convertView.findViewById(R.id.tvProductPrice);
        TextView tvProductStock = convertView.findViewById(R.id.tvProductStock);
        TextView tvQuantity = convertView.findViewById(R.id.tvQuantity);
        ImageView ivProduct =  convertView.findViewById(R.id.ivProduct);

        // Điền dữ liệu vào các view
        tvName.setText(hangHoa.getTenSp());
        tvPrice.setText(String.valueOf(hangHoa.getGiaSp()));
        tvProductStock.setText(String.valueOf(hangHoa.getSoLuongTonKho()));
        tvQuantity.setText(String.valueOf(ViewProtypeProductSearch.gioHang.getQuantity(hangHoa)));

        // hien thi hinh`
        if (hangHoa.getLoaiSp().equals("dm001")) {
            ivProduct.setImageResource(R.drawable.thit);
        } else if (hangHoa.getLoaiSp().equals("dm002")) {
            ivProduct.setImageResource(R.drawable.ca);
        } else if (hangHoa.getLoaiSp().equals("dm003")) {
            ivProduct.setImageResource(R.drawable.trung);
        } else if (hangHoa.getLoaiSp().equals("dm004")) {
            ivProduct.setImageResource(R.drawable.sua);
        }
        // Trả về view hoàn thiện để hiển thị trên màn hình
        return convertView;
    }
}



