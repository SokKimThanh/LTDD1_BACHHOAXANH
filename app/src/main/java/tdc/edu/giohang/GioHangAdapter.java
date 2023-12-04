package tdc.edu.giohang;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import tdc.edu.ShoppingSearch.OnDeleteFromCartClickListener;
import tdc.edu.ShoppingSearch.ViewProductSearch;
import tdc.edu.danhsachsp.HangHoa;
import tdc.edu.danhsachsp.R;

public class GioHangAdapter extends ArrayAdapter<HangHoa> {
    // Khai báo interface
    private OnDeleteFromCartClickListener listener;//Triển khai interface để bắt sự kiện của adapter con từ lớp cha

    Context context;//tham chiếu đến bộ nhớ trong quá trình app chạy

    int resource;//id cua control

    List<HangHoa> hangHoaList;

    public GioHangAdapter(Context context, int resource, List<HangHoa> hangHoaList, OnDeleteFromCartClickListener listener) {
        super(context, 0, hangHoaList);
        this.context = context;
        this.resource = resource;
        this.hangHoaList = hangHoaList;
        this.listener = listener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Lấy dữ liệu item cho vị trí này
        // Khởi tạo giá hàng hóa khi getView
        HangHoa hangHoa = hangHoaList.get(position);

        // Kiểm tra xem một view đã tồn tại chưa, nếu không thì inflate
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_giohang_item, parent, false);
        }

        // Tìm kiếm view cho dữ liệu trong layout
        TextView tvName = convertView.findViewById(R.id.tvProductCartName);
        TextView tvPrice = convertView.findViewById(R.id.tvProductCartPrice);
        TextView tvProductStock = convertView.findViewById(R.id.tvProductCartStock);
        EditText tvQuantity = convertView.findViewById(R.id.tvQuantity);
        ImageView ivProduct = convertView.findViewById(R.id.ivProductCartItem);
        ImageView ivDecrease = convertView.findViewById(R.id.ivDecreaseQuantity);
        ImageView ivIncrease = convertView.findViewById(R.id.ivIncreaseQuantity);
        ImageView btnXoaSanPhamGioHang = convertView.findViewById(R.id.btnXoaSanPhamGioHang);

        // Điền dữ liệu vào các view
        tvName.setText(hangHoa.getTenSp());
        tvPrice.setText(String.valueOf(hangHoa.getGiaSp()));
        tvProductStock.setText(String.valueOf(hangHoa.getSoLuongTonKho()));
        tvQuantity.setText(String.valueOf(ViewProductSearch.gioHang.getQuantity(hangHoa)));

        // hien thi hinh`
        if (hangHoa.getLoaiSp().equals("dm001")) {
            ivProduct.setImageResource(R.drawable.img_thit);
        } else if (hangHoa.getLoaiSp().equals("dm002")) {
            ivProduct.setImageResource(R.drawable.img_ca);
        } else if (hangHoa.getLoaiSp().equals("dm003")) {
            ivProduct.setImageResource(R.drawable.img_trung);
        } else if (hangHoa.getLoaiSp().equals("dm004")) {
            ivProduct.setImageResource(R.drawable.img_sua);
        }

        ivDecrease.setOnClickListener(v -> {
            v.startAnimation(AnimationUtils.loadAnimation(v.getContext(), R.anim.image_click));
            this.listener.onDecreaseCartItemClicked(hangHoa);
            tvQuantity.setText(ViewProductSearch.gioHang.getQuantity(hangHoa)+"");
        });
        ivIncrease.setOnClickListener(v -> {
            v.startAnimation(AnimationUtils.loadAnimation(v.getContext(), R.anim.image_click));
            this.listener.onIncreaseCartItemClicked(hangHoa);
            tvQuantity.setText(ViewProductSearch.gioHang.getQuantity(hangHoa)+"");
        });


        btnXoaSanPhamGioHang.setOnClickListener(v -> {
            v.startAnimation(AnimationUtils.loadAnimation(v.getContext(), R.anim.image_click));
            this.listener.onDeleteCartItemClicked(hangHoa);
        });
        // Trả về view hoàn thiện để hiển thị trên màn hình
        return convertView;
    }
}



