package tdc.edu.ShoppingSearch;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import java.util.List;

import tdc.edu.danhsachsp.HangHoa;
import tdc.edu.danhsachsp.R;
import tdc.edu.giohang.GioHang;

public class ProductItemAdapter extends ArrayAdapter {

    // Khai báo interface
    private OnAddToCartClickListener listener;//Triển khai interface để bắt sự kiện của adapter con từ lớp cha

    Context context;//tham chiếu đến bộ nhớ trong quá trình app chạy

    int resource;//id cua control

    List<HangHoa> hangHoaList;//danh sách hàng hóa
    // Tạo giỏ hàng mới để thêm sản phẩm

    public ProductItemAdapter(Context context, int resource, List<HangHoa> hangHoaList,OnAddToCartClickListener listener) {
        super(context, resource, hangHoaList);
        this.context = context;
        this.resource = resource;
        this.hangHoaList = hangHoaList;
        // constructor của adapter nên chấp nhận một OnAddToCartClickListener
        this.listener = listener;// thêm 1 sự kiện để bắt sự kiện từ add to cart của lớp con
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Inflate layout, get references to views, etc.
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, null);
        }

        // danh sach hien thi len tung item
        ImageView ivProduct = convertView.findViewById(R.id.ivProduct);
        TextView tvProductName = convertView.findViewById(R.id.tvProductName);
        TextView tvGiaSPSearch = convertView.findViewById(R.id.tvGiaSPSearch);
        TextView tvSoLuongSPSearch = convertView.findViewById(R.id.tvSoLuongSPSearch);
        Button btnAddToCart = convertView.findViewById(R.id.btnAddToCartProductItemAdapter);

        //kiểm tra xem position có lớn hơn hoặc bằng kích thước của hangHoaList hay không, vì chỉ số của danh sách bắt đầu từ 0
        if (position >= hangHoaList.size()) {
            Toast.makeText(context, "Hết dữ liệu", Toast.LENGTH_SHORT).show();
            return convertView;
        }

        // dau vao position set gia tri cho control
        HangHoa hangHoa = hangHoaList.get(position);
        // hien thi ten san pham
        tvProductName.setText(hangHoa.getTenSp());
        // hien thi gia san pham va so luong
        tvGiaSPSearch.setText("Giá: " + hangHoa.getGiaSp());

        tvSoLuongSPSearch.setText("Số lượng: " + hangHoa.getSoluongNhapkho());


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

        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HangHoa product = (HangHoa) getItem(position);
                listener.onAddToCartClicked(product);
            }
        });

        // Return the completed view to render on screen
        // hien thi toan bo du lieu len view hop le
        return convertView;
    }
}