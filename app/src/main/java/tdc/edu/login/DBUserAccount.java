package tdc.edu.login;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


public class DBUserAccount extends SQLiteOpenHelper {
    public DBUserAccount(@Nullable Context context) {
        super(context, "DBUser", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table UserAccount(mataikhoan INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, tentaikhoan text, matkhau text, ngayhethantruycap date, capdotaikhoan INTEGER, email text, isEmailVerified text)";
        db.execSQL(sql);
    }

    public void ThemDL(UserAccount userAccount) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "INSERT INTO UserAccount(tentaikhoan, matkhau, ngayhethantruycap, capdotaikhoan, email, isEmailVerified) VALUES(?,?,?,?,?,?)";
        String tentaikhoan = userAccount.getTentaikhoan();
        String matkhau = userAccount.getMatkhau();
        String ngayhethantruycap = String.valueOf(userAccount.getNgayhethantruycap());
        String capdotaikhoan = String.valueOf(userAccount.getCapdotaikhoan());
        String email = userAccount.getEmail();
        String isEmailVerified = String.valueOf(userAccount.isEmailVerified());
        db.execSQL(sql, new String[]{tentaikhoan, matkhau, ngayhethantruycap, capdotaikhoan, email, isEmailVerified});
        db.close();
    }

    public void XoaDL(String ma) {
        String sql = "Delete from UserAccount where mataikhoan = ?";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql, new String[]{ma});
    }


    public List<UserAccount> DocDL() {
        List<UserAccount> listHoaDon = new ArrayList<>();
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

                // Lấy dữ liệu từ cột "your_column"
                int mataikhoan = cursor.getInt(i0);
                int tentaikhoan = cursor.getInt(i1);
                int matkhau = cursor.getInt(i2);
                int ngayhethantruycap = cursor.getInt(i3);
                int capdotaikhoan = cursor.getInt(i4);
                int email = cursor.getInt(i5);

                // set du lieu cho user account
                userAccount.setMataikhoan(mataikhoan);
                userAccount.setMataikhoan(tentaikhoan);
                userAccount.setMataikhoan(matkhau);
                userAccount.setMataikhoan(ngayhethantruycap);
                userAccount.setMataikhoan(capdotaikhoan);
                userAccount.setMataikhoan(email);

                listHoaDon.add(userAccount);
            } while (cursor.moveToNext());
            // In dữ liệu ra log
            Log.d("BHX", "Data from UserAccount: " + listHoaDon);
        }
        cursor.close();
        return listHoaDon;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
