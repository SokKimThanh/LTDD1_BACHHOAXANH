package tdc.edu.login;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import tdc.edu.danhsachsp.R;

public class AccountAdapter extends ArrayAdapter<Account> {

    Context context;//tham chiếu đến bộ nhớ trong quá trình app chạy

    int resource;//id cua control

    List<Account> accounts;//danh sách tài khoản

    public AccountAdapter(Context context, int resource, List<Account> accounts) {
        super(context, resource, accounts);
        this.context = context;
        this.resource = resource;
        this.accounts = accounts;
    }

    @NonNull
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Khởi tạo giá hàng hóa khi getView
        Account account = accounts.get(position);

        // Inflate layout, get references to views, etc.
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, null);
        }

        // danh sach hien thi len tung item
        TextView tvMaTK = convertView.findViewById(R.id.tvMaTK);
        TextView tvTenTK = convertView.findViewById(R.id.tvTenTK);
        TextView tvEmailTK = convertView.findViewById(R.id.tvEmailTK);
        TextView tvNgayHetHan = convertView.findViewById(R.id.tvNgayHetHan);
        TextView tvLoaiTK = convertView.findViewById(R.id.tvLoaiTK);
        TextView tvKichHoatEmail = convertView.findViewById(R.id.tvKichHoatEmail);


        // kiểm tra xem position có lớn hơn hoặc bằng kích thước của hangHoaList hay không, vì chỉ số của danh sách bắt đầu từ 0
        if (position >= accounts.size()) {
            Toast.makeText(context, "Hết dữ liệu", Toast.LENGTH_SHORT).show();
            return convertView;
        }


        // Hiển thị thông tin
        tvMaTK.setText(account.getMataikhoan() + "");
        tvTenTK.setText(account.getTentaikhoan());
        tvEmailTK.setText(account.getEmail());
        tvNgayHetHan.setText(account.getNgayhethantruycap());
        tvLoaiTK.setText(account.getCapdotaikhoan() == AccountLevel.ADMIN.getLevelCode() ? "Quản lý" : account.getCapdotaikhoan() == AccountLevel.CUSTOMER.getLevelCode() ? "Khách hàng" : account.getCapdotaikhoan() == AccountLevel.EMPLOYEE.getLevelCode() ? "Khách vãng lai" : "Unknow");
        tvKichHoatEmail.setText(account.isEmailVerified() == false ? "Chưa kích hoạt" : "Đã kích hoạt");


        // Return the completed view to render on screen
        // hien thi toan bo du lieu len view hop le
        return convertView;
    }
}
