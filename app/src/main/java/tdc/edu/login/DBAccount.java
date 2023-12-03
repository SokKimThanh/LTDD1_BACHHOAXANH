package tdc.edu.login;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


public class DBAccount extends SQLiteOpenHelper {
    public DBAccount(@Nullable Context context) {

        super(context, "DBAccount", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Xóa bảng
        String sql = "create table UserAccount(mataikhoan INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, tentaikhoan text not null unique, matkhau text, ngayhethantruycap text, capdotaikhoan INTEGER, email text, isEmailVerified INTEGER)";
        db.execSQL(sql);
    }

    public boolean ThemDL(Account account) {
        boolean flag = false;
        if (account != null) {

            try {
                SQLiteDatabase db = getWritableDatabase();
                String sql = "INSERT INTO UserAccount(tentaikhoan, matkhau, ngayhethantruycap, capdotaikhoan, email, isEmailVerified) VALUES(?,?,?,?,?,?)";

                String tentaikhoan = account.getTentaikhoan();
                String matkhau = account.getMatkhau();
                String ngayhethantruycap = String.valueOf(account.getNgayhethantruycap());
                String capdotaikhoan = String.valueOf(account.getCapdotaikhoan());
                String email = account.getEmail();

                // Chuyển boolean thành int, sau đó chuyển int thành String
                String isEmailVerified = String.valueOf(account.isEmailVerified() ? 1 : 0);

                db.execSQL(sql, new String[]{tentaikhoan, matkhau, ngayhethantruycap, capdotaikhoan, email, isEmailVerified});
                db.close();
                flag = true;
            } catch (Exception ex) {
                flag = false;
                try {
                    throw new Exception(ex.getMessage());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return flag;
    }

    public void XoaDL(String ma) {
        String sql = "Delete from UserAccount where mataikhoan = ?";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql, new String[]{ma});
    }


    public List<Account> DocDL() {
        List<Account> accounts = new ArrayList<>();
        String sql = "Select * from UserAccount";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                Account account = new Account();
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
                account.setMataikhoan(mataikhoan);
                account.setTentaikhoan(tentaikhoan);
                account.setMatkhau(matkhau);
                account.setNgayhethantruycap(ngayhethantruycap);
                account.setCapdotaikhoan(capdotaikhoan);
                account.setEmail(email);
                account.setEmailVerified(isEmailValidation);

                accounts.add(account);
            } while (cursor.moveToNext());
            // In dữ liệu ra log
            Log.d("BHX", "Data from Account: " + accounts);
        }
        cursor.close();
        return accounts;
    }

    public List<Account> DocDLByCapDoTaiKhoan(int levelaccount) {
        List<Account> accounts = new ArrayList<>();
        // get by cap do tai khoan
        String sql = "Select * from UserAccount where capdotaikhoan = ?";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(levelaccount)});
        if (cursor.moveToFirst()) {
            do {
                Account account = new Account();
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
                account.setMataikhoan(mataikhoan);
                account.setTentaikhoan(tentaikhoan);
                account.setMatkhau(matkhau);
                account.setNgayhethantruycap(ngayhethantruycap);
                account.setCapdotaikhoan(capdotaikhoan);
                account.setEmail(email);
                account.setEmailVerified(isEmailValidation);

                accounts.add(account);
            } while (cursor.moveToNext());
            // In dữ liệu ra log
            Log.d("BHX", "Data from Account: " + accounts);
        }
        cursor.close();
        return accounts;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Xóa bảng
        db.execSQL("DROP TABLE IF EXISTS UserAccount");
        // Tạo lại bảng
        onCreate(db);
    }
}
