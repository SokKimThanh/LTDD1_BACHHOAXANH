package tdc.edu.danhsachdm;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBDanhMuc extends SQLiteOpenHelper {

    /**
     * Khởi tạo DBDanhMuc
     */
    public DBDanhMuc(@Nullable Context context) {
        // Gọi hàm khởi tạo của lớp cha SQLiteOpenHelper
        super(context, "dbQuanLyBanHang", null, 1);
    }

    /**
     * Tạo bảng tbDanhMuc
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Câu lệnh SQL để tạo bảng tblDanhMuc với các cột ma, ten, ghichu
        String sql = "Create Table If not exists tblDanhMuc(ma Text, ten Text, ghichu text)";

        // Thực hiện câu lệnh SQL để tạo bảng
        db.execSQL(sql);
    }

    /**
     * Thêm danh mục
     *
     * @param sv Danh mục
     */
    public void ThemDL(DanhMuc sv) {
        // Câu lệnh SQL để thêm một dòng vào bảng tblDanhMuc
        String sql = "Insert into tblDanhMuc values(?,?,?)";

        // Mở cơ sở dữ liệu để ghi
        SQLiteDatabase db = getWritableDatabase();

        // Thực hiện câu lệnh SQL với các tham số từ đối tượng DanhMuc
        db.execSQL(sql, new String[]{sv.getMa(), sv.getTen(), sv.getGhichu()});
    }
//xxxxxx
    /**
     * Xóa danh mục
     *
     * @param sv danhMuc
     */
    public void XoaDL(DanhMuc sv) {
        // Câu lệnh SQL để xóa một dòng từ bảng tblDanhMuc dựa trên mã danh mục
        String sql = "Delete from tblDanhMuc where ma = ?";

        // Mở cơ sở dữ liệu để ghi
        SQLiteDatabase db = getWritableDatabase();

        // Thực hiện câu lệnh SQL với mã danh mục từ đối tượng DanhMuc
        db.execSQL(sql, new String[]{sv.getMa()});
    }


    /**
     * Sửa thông tin danh mục
     *
     * @param sv The danhMuc.
     */
    public void SuaDL(DanhMuc sv) {
        // Câu lệnh SQL để cập nhật thông tin danh mục trong bảng tblDanhMuc
        String sql = "Update tblDanhMuc set ten = ?, ghichu=? where ma=?";

        // Mở cơ sở dữ liệu để ghi
        SQLiteDatabase db = getWritableDatabase();

        // Thực hiện câu lệnh SQL với các tham số từ đối tượng DanhMuc
        // sv.getTen() và sv.getGhichu() sẽ thay thế cho ?, sv.getMa() sẽ thay thế cho ?
        db.execSQL(sql, new String[]{sv.getTen(), sv.getGhichu(), sv.getMa()});
    }


    /**
     * Đọc dữ liệu từ db
     *
     * @return danh sách đối tượng danh mục
     */
    public List<DanhMuc> DocDL() {
        // Khởi tạo danh sách danh mục
        List<DanhMuc> listDanhMuc = new ArrayList<>();

        // Câu lệnh SQL để lấy tất cả dữ liệu từ bảng tblDanhMuc
        String sql = "Select * from tblDanhMuc";

        // Mở cơ sở dữ liệu để đọc
        SQLiteDatabase db = getReadableDatabase();

        // Thực hiện truy vấn SQL và lưu kết quả vào Cursor
        Cursor cursor = db.rawQuery(sql, null);

        // Kiểm tra xem con trỏ có dữ liệu không
        if (cursor.moveToFirst()) {
            do {
                // Khởi tạo đối tượng danh mục mới
                DanhMuc danhMuc = new DanhMuc();

                // Đọc dữ liệu từ cột 0 (Mã danh mục) và cập nhật vào đối tượng
                danhMuc.setMa(cursor.getString(0).toString());

                // Đọc dữ liệu từ cột 1 (Tên danh mục) và cập nhật vào đối tượng
                danhMuc.setTen(cursor.getString(1).toString());

                // Đọc dữ liệu từ cột 2 (Giới tính) và cập nhật vào đối tượng
                danhMuc.setGhichu(cursor.getString(2).toString());

                // Thêm đối tượng danh mục vào danh sách
                listDanhMuc.add(danhMuc);
            } while (cursor.moveToNext()); // Di chuyển con trỏ đến hàng tiếp theo
        }

        // Trả về danh sách danh mục
        return listDanhMuc;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
