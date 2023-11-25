package tdc.edu.danhsachsp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHangHoa extends SQLiteOpenHelper {

    /**
     * Khởi tạo DBSANPHAM
     */
    public DBHangHoa(@Nullable Context context) {
        // Gọi hàm khởi tạo của lớp cha SQLiteOpenHelper
        super(context, "dbQuanLyBanHang.HangHoa", null, 1);
    }

    /**
     * Tạo bảng tbHangHoa
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Câu lệnh SQL để tạo bảng HangHoa với các cột ma, ten, gioitinh
        String sql = "Create Table If not exists HangHoa (ma Text primary key, ten Text, gia Text, soluong int, loaisp text)";

        // Thực hiện câu lệnh SQL để tạo bảng
        db.execSQL(sql);
    }

    /**
     * Thêm hàng hóa
     *
     * @param o Hàng hóa
     */
    public void ThemDL(HangHoa o) {
        // Câu lệnh SQL để thêm một dòng vào bảng HangHoa
        String sql = "Insert into HangHoa(ma, ten, gia, soluong, loaisp) values(?,?,?,?,?)";

        // Mở cơ sở dữ liệu để ghi
        SQLiteDatabase db = getWritableDatabase();

        // Thực hiện câu lệnh SQL với các tham số từ đối tượng HangHoa
        db.execSQL(sql, new String[]{o.getMaSp(), o.getTenSp(), o.getGiaSp(), String.valueOf(o.getSoluongNhapkho()), o.getLoaiSp()});
    }

    /**
     * Xóa hàng hóa
     *
     * @param o hanghoa
     */
    public void XoaDL(HangHoa o) {
        // Câu lệnh SQL để xóa một dòng từ bảng HangHoa dựa trên mã hàng hóa
        String sql = "Delete from HangHoa where ma = ?";

        // Mở cơ sở dữ liệu để ghi
        SQLiteDatabase db = getWritableDatabase();

        // Thực hiện câu lệnh SQL với mã hàng hóa từ đối tượng HangHoa
        db.execSQL(sql, new String[]{o.getMaSp()});
    }


    /**
     * Sửa thông tin hàng hóa
     *
     * @param o The hanghoa.
     */
    public void SuaDL(HangHoa o) {
        // Câu lệnh SQL để cập nhật thông tin hàng hóa trong bảng HangHoa
        String sql = "Update HangHoa set ten = ?, gia=?, soluong=?, loaisp=? where ma=?";

        // Mở cơ sở dữ liệu để ghi
        SQLiteDatabase db = getWritableDatabase();

        // Thực hiện câu lệnh SQL với các tham số từ đối tượng HangHoa
        // o.getTen() và o.getGioitinh() sẽ thay thế cho ?, o.getMa() sẽ thay thế cho ?
        db.execSQL(sql, new String[]{o.getTenSp(), o.getGiaSp(), String.valueOf(o.getSoluongNhapkho()), o.getLoaiSp(), o.getMaSp()});
    }


    /**
     * Đọc dữ liệu từ db
     *
     * @return danh sách đối tượng hàng hóa
     */
    public List<HangHoa> DocDL() {
        // Khởi tạo danh sách hàng hóa
        List<HangHoa> listHangHoa = new ArrayList<>();

        // Câu lệnh SQL để lấy tất cả dữ liệu từ bảng HangHoa
        String sql = "Select * from HangHoa";

        // Mở cơ sở dữ liệu để đọc
        SQLiteDatabase db = getReadableDatabase();

        // Thực hiện truy vấn SQL và lưu kết quả vào Cursor
        Cursor cursor = db.rawQuery(sql, null);

        // Kiểm tra xem con trỏ có dữ liệu không
        if (cursor.moveToFirst()) {
            do {
                // Khởi tạo đối tượng hàng hóa mới
                HangHoa hanghoa = new HangHoa();

                // Đọc dữ liệu từ cột 0 (Mã hàng hóa) và cập nhật vào đối tượng
                hanghoa.setMaSp(cursor.getString(0).toString());

                // Đọc dữ liệu từ cột 1 (Tên hàng hóa) và cập nhật vào đối tượng
                hanghoa.setTenSp(cursor.getString(1).toString());

                // Đọc dữ liệu từ cột 2 (Giá) và cập nhật vào đối tượng
                hanghoa.setGiaSp(cursor.getString(2).toString());

                // Đọc dữ liệu từ cột 3 (Số lượng) và cập nhật vào đối tượng
                hanghoa.setSoluongNhapkho(cursor.getInt(3));

                // Đọc dữ liệu từ cột 4 (loại sản phẩm) và cập nhật vào đối tượng
                hanghoa.setLoaiSp(cursor.getString(4).toString());

                // Thêm đối tượng hàng hóa vào danh sách
                listHangHoa.add(hanghoa);
            } while (cursor.moveToNext()); // Di chuyển con trỏ đến hàng tiếp theo
        }

        // Trả về danh sách hàng hóa
        return listHangHoa;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public List<HangHoa> DocDLBy(String keyword) {
        // Khởi tạo danh sách hàng hóa
        List<HangHoa> listHangHoa = new ArrayList<>();

        // Câu lệnh SQL để lấy tất cả dữ liệu từ bảng HangHoa
        String sql = "Select * from HangHoa where ten like N'%" + keyword+ "%'" + "or loaisp like N'%" + keyword + "%'";

        // Mở cơ sở dữ liệu để đọc
        SQLiteDatabase db = getReadableDatabase();


        // Thực hiện truy vấn SQL và lưu kết quả vào Cursor
        Cursor cursor = db.rawQuery(sql, null);

        // Kiểm tra xem con trỏ có dữ liệu không
        if (cursor.moveToFirst()) {
            do {
                // Khởi tạo đối tượng hàng hóa mới
                HangHoa hanghoa = new HangHoa();

                // Đọc dữ liệu từ cột 0 (Mã hàng hóa) và cập nhật vào đối tượng
                hanghoa.setMaSp(cursor.getString(0).toString());

                // Đọc dữ liệu từ cột 1 (Tên hàng hóa) và cập nhật vào đối tượng
                hanghoa.setTenSp(cursor.getString(1).toString());

                // Đọc dữ liệu từ cột 2 (Giá) và cập nhật vào đối tượng
                hanghoa.setGiaSp(cursor.getString(2).toString());

                // Đọc dữ liệu từ cột 3 (Số lượng) và cập nhật vào đối tượng
                hanghoa.setSoluongNhapkho(cursor.getInt(3));

                // Đọc dữ liệu từ cột 4 (loại sản phẩm) và cập nhật vào đối tượng
                hanghoa.setLoaiSp(cursor.getString(4).toString());

                // Thêm đối tượng hàng hóa vào danh sách
                listHangHoa.add(hanghoa);
            } while (cursor.moveToNext()); // Di chuyển con trỏ đến hàng tiếp theo
        }


        // Trả về danh sách hàng hóa
        return listHangHoa;
    }
}
