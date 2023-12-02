package tdc.edu.login;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class DBUserAccount extends SQLiteOpenHelper {
    public DBUserAccount(@Nullable Context context) {

        super(context, "DBUser", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table UserAccount(mataikhoan INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, tentaikhoan text, matkhau text, ngayhethantruycap text, capdotaikhoan INTEGER, email text, isEmailVerified INTEGER)";
        db.execSQL(sql);

    }

    public boolean ThemDL(UserAccount userAccount) {
        boolean flag = false;
        if(userAccount!=null){

            SQLiteDatabase db = getWritableDatabase();
            String sql = "INSERT INTO UserAccount(tentaikhoan, matkhau, ngayhethantruycap, capdotaikhoan, email, isEmailVerified) VALUES(?,?,?,?,?,?)";

            String tentaikhoan = userAccount.getTentaikhoan();
            String matkhau = userAccount.getMatkhau();
            String ngayhethantruycap = String.valueOf(userAccount.getNgayhethantruycap());
            String capdotaikhoan = String.valueOf(userAccount.getCapdotaikhoan());
            String email = userAccount.getEmail();

            // Chuyển boolean thành int, sau đó chuyển int thành String
            String isEmailVerified = String.valueOf(userAccount.isEmailVerified() ? 1 : 0);

            db.execSQL(sql, new String[]{tentaikhoan, matkhau, ngayhethantruycap, capdotaikhoan, email, isEmailVerified});
            db.close();
            flag= true;
        }
        return flag;
    }

    public void XoaDL(String ma) {
        String sql = "Delete from UserAccount where mataikhoan = ?";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql, new String[]{ma});
    }


    public List<UserAccount> DocDL() {
        List<UserAccount> userAccounts = new ArrayList<>();
        String sql = "Select * from UserAccount";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                UserAccount userAccount = new UserAccount();
                // Lấy chỉ số của cột "your_column"
                int i0 = cursor.getColumnIndex("mataikhoan");
                int i1 = cursor.getColumnIndex("tentaikhoan");
                int i2 = cursor.getColumnIndex("matkhau");
                int i3 = cursor.getColumnIndex("ngayhethantruycap");
                int i4 = cursor.getColumnIndex("capdotaikhoan");
                int i5 = cursor.getColumnIndex("email");
                int i6 = cursor.getColumnIndex("isEmailVerified");

                // Lấy dữ liệu từ cột "your_column"
                int mataikhoan = cursor.getInt(i0);
                String tentaikhoan = cursor.getString(i1);
                String matkhau = cursor.getString(i2);
                String ngayhethantruycap = cursor.getString(i3);
                int capdotaikhoan = cursor.getInt(i4);
                String email = cursor.getString(i5);
                boolean isEmailValidation = Boolean.valueOf(cursor.getString(i6));

                // set du lieu cho user account
                userAccount.setMataikhoan(mataikhoan);
                userAccount.setTentaikhoan(tentaikhoan);
                userAccount.setMatkhau(matkhau);
                userAccount.setNgayhethantruycap(ngayhethantruycap);
                userAccount.setCapdotaikhoan(capdotaikhoan);
                userAccount.setEmail(email);
                userAccount.setEmailVerified(isEmailValidation);

                userAccounts.add(userAccount);
            } while (cursor.moveToNext());
            // In dữ liệu ra log
            Log.d("BHX", "Data from UserAccount: " + userAccounts);
        }
        cursor.close();
        return userAccounts;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Xóa bảng
        db.execSQL("DROP TABLE IF EXISTS UserAccount");

        // Tạo lại bảng
        onCreate(db);

        // Thêm 3 tài khoản admin, user, và guest
        ThemDL(new UserAccount("admin", "admin123", "2023-12-31", 0, "admin@example.com", true));
        ThemDL(new UserAccount("user", "user123", "2023-12-31", 1, "user@example.com", true));
        ThemDL(new UserAccount("guest", "guest123", "2023-12-31", 2, "guest@example.com", true));

    }
}
