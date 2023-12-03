package tdc.edu.login;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import tdc.edu.ShoppingSearch.OnAddToCartClickListener;
import tdc.edu.danhsachsp.HangHoa;
import tdc.edu.danhsachsp.R;

public class AccountItemAdapter {

    Context context;//tham chiếu đến bộ nhớ trong quá trình app chạy

    int resource;//id cua control

    List<UserAccount> userAccounts;//danh sách hàng hóa
    // Tạo giỏ hàng mới để thêm sản phẩm

    public AccountItemAdapter(Context context, int resource, List<UserAccount> userAccounts ) {
        super();
        this.context = context;
        this.resource = resource;
        this.userAccounts = userAccounts;
    }

    @NonNull
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Khởi tạo giá hàng hóa khi getView
        UserAccount userAccount = userAccounts.get(position);

        // Inflate layout, get references to views, etc.
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, null);
        }

        // danh sach hien thi len tung item
        ImageView ivProduct = convertView.findViewById(R.id.ivProduct);
        TextView tvProductName = convertView.findViewById(R.id.tvProductCartName);
        TextView tvGiaSPSearch = convertView.findViewById(R.id.tvGiaSPSearch);
        TextView tvSoLuongSPSearch = convertView.findViewById(R.id.tvSoLuongSPSearch);
        Button btnAddToCart = convertView.findViewById(R.id.btnAddToCartProductItemAdapter);

        // kiểm tra xem position có lớn hơn hoặc bằng kích thước của hangHoaList hay không, vì chỉ số của danh sách bắt đầu từ 0
        if (position >= userAccounts.size()) {
            Toast.makeText(context, "Hết dữ liệu", Toast.LENGTH_SHORT).show();
            return convertView;
        }


        // Hiển thị thông tin
//        tvProductName.setText(userAccount.getTenSp());
//        tvGiaSPSearch.setText(String.valueOf(userAccount.getGiaSp()));
//        tvSoLuongSPSearch.setText(String.valueOf(userAccount.getSoLuongTonKho()));



        // Return the completed view to render on screen
        // hien thi toan bo du lieu len view hop le
        return convertView;
    }
}
