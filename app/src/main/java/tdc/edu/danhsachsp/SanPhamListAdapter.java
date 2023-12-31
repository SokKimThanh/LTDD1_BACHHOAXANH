package tdc.edu.danhsachsp;

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

/**
 * Đây là một lớp `SanPhamListAdapter` trong Java, được kế thừa từ lớp `ArrayAdapter`. Lớp này được sử dụng để tạo ra một adapter cho danh sách các sản phẩm (`SanPham`). Adapter này có thể được sử dụng để hiển thị danh sách sản phẩm trong một `ListView` hoặc một `RecyclerView`, ví dụ.
 * <p>
 * Dưới đây là mô tả chi tiết về các thành phần trong lớp:
 * <p>
 * - `Context context`: Đối tượng `Context` là một đối tượng cơ bản trong Android, nó giúp truy cập vào các tài nguyên và dịch vụ hệ thống như `LayoutInflater`, `SharedPreferences`, `Database`, v.v.
 * - `int resource`: Đây là ID của layout XML mà bạn muốn sử dụng để hiển thị mỗi mục trong danh sách.
 * - `List<SanPham> data`: Đây là danh sách các đối tượng `SanPham` mà bạn muốn hiển thị.
 * <p>
 * Hàm khởi tạo (`public SanPhamListAdapter(Context context, int resource,List<SanPham> data)`) được sử dụng để khởi tạo một đối tượng mới của lớp `SanPhamListAdapter` với Context, ID layout và danh sách sản phẩm cung cấp. Hàm này gọi hàm khởi tạo của lớp cha (`super(context,resource,data)`) và sau đó gán các giá trị cho các biến thành viên của lớp.
 */
public class SanPhamListAdapter extends ArrayAdapter {
    Context context;//tham chiếu đến bộ nhớ trong quá trình app chạy

    int resource;//id cua control

    List<HangHoa> data;// danh sach san pham (dau vao+ dau ra)

    public SanPhamListAdapter(Context context, int resource, List<HangHoa> data) {
        super(context, resource, data);
        this.context = context;
        this.resource = resource;
        this.data = data;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(resource, null);

        // danh sach hien thi len tung item
        ImageView ivHinh = convertView.findViewById(R.id.ivHinh);
        TextView tvTenSp = convertView.findViewById(R.id.tvTenSp);
        TextView tvGiaSp = convertView.findViewById(R.id.tvGiaSp);
        TextView tvSoLuong = convertView.findViewById(R.id.tvSoLuongNhapKho);
        // dau vao position set gia tri cho control
        HangHoa sp = data.get(position);
        // hien thi ten san pham
        tvTenSp.setText(sp.getTenSp());
        // hien thi gia san pham
        tvGiaSp.setText(String.valueOf(sp.getGiaSp()));
        // hien thi số lượng sản phẩm
        tvSoLuong.setText(String.valueOf(sp.getSoLuongTonKho()));

        // hien thi hinh`
        if (sp.getLoaiSp() == 1) {
            ivHinh.setImageResource(R.drawable.img_thit);
        }
        if (sp.getLoaiSp() == 2) {
            ivHinh.setImageResource(R.drawable.img_ca);
        }
        if (sp.getLoaiSp() == 3) {
            ivHinh.setImageResource(R.drawable.img_trung);
        }
        if (sp.getLoaiSp() == 4) {
            ivHinh.setImageResource(R.drawable.img_sua);
        }
        // hien thi toan bo du lieu len view hop le
        return convertView;
    }
}
