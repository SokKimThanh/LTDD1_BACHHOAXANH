package tdc.edu.navigation;

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

    Context context;//tham chiếu đến bộ nhớ trong quá trình app chạy

    int resource;//id cua control

    List<HangHoa> hangHoaList;//danh sách hàng hóa
    // Tạo giỏ hàng mới để thêm sản phẩm
    GioHang cart = new GioHang();


    public ProductItemAdapter(Context context, int resource, List<HangHoa> hangHoaList) {
        super(context, resource, hangHoaList);
        this.context = context;
        this.resource = resource;
        this.hangHoaList = hangHoaList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(resource, null);
        // danh sach hien thi len tung item
        ImageView ivProduct = convertView.findViewById(R.id.ivProduct);
        TextView tvProductName = convertView.findViewById(R.id.tvProductName);
        TextView tvGiaSPSearch = convertView.findViewById(R.id.tvGiaSPSearch);
        TextView tvSoLuongSPSearch = convertView.findViewById(R.id.tvSoLuongSPSearch);
        Button btnAddToCart = convertView.findViewById(R.id.btnAddToCart);
        if(position> hangHoaList.size()){
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
        }
        if (hangHoa.getLoaiSp().equals("dm002")) {
            ivProduct.setImageResource(R.drawable.ca);
        }
        if (hangHoa.getLoaiSp().equals("dm003")) {
            ivProduct.setImageResource(R.drawable.trung);
        }
        if (hangHoa.getLoaiSp().equals("dm004")) {
            ivProduct.setImageResource(R.drawable.sua);
        }

        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ketqua = "Sản phẩm đã có trong giỏ hàng bạn muốn thêm nữa không?";
                // xử lý nút thêm vào giỏ hàng
                // Kiểm tra xem sản phẩm đã có trong giỏ hàng chưa
                if (cart.getHangHoaList() != null) {
                    if (cart.contains(hangHoa)) {
                        // Nếu có, hiển thị một hộp thoại xác nhận
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setMessage(ketqua);
                        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Nếu người dùng chọn có, tăng số lượng sản phẩm trong giỏ hàng
                                cart.increaseQuantity(hangHoa);
                                // Cập nhật biểu tượng giỏ hàng
                                updateCartIcon();
                                // Thông báo cho người dùng
                                Toast.makeText(context, "Đã thêm sản phẩm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                            }
                        });
                        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Nếu người dùng chọn không, đóng hộp thoại
                                dialog.dismiss();
                            }
                        });
                        builder.create().show();
                    } else {
                        // Nếu không, thêm sản phẩm vào giỏ hàng
                        cart.add(hangHoa);
                        // Cập nhật biểu tượng giỏ hàng
                        updateCartIcon();
                        // Thông báo cho người dùng
                        Toast.makeText(context, "Đã thêm sản phẩm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });


        // hien thi toan bo du lieu len view hop le
        return convertView;
    }

    private void updateCartIcon() {
        // xử lý icon giỏ hàng đếm số lượng sản phẩm trong giỏ hàng

    }

    public void UpdateData(List<HangHoa> hangHoaList) {
        this.hangHoaList = hangHoaList;
    }
}
