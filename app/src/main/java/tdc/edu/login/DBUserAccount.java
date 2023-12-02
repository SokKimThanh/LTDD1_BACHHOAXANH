package tdc.edu.login;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


public class DBUserAccount extends SQLiteOpenHelper {
    public DBUserAccount(@Nullable Context context) {
        super(context, "DBUser", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table UserAccount(mataikhoan INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, tentaikhoan text, matkhau text, ngayhethantruycap date, capdotaikhoan INTEGER, email text)";
        db.execSQL(sql);
    }

    public void ThemDL(UserAccount userAccount) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "INSERT INTO UserAccount(tentaikhoan, matkhau, ngayhethantruycap, capdotaikhoan, email) VALUES(?,?,?,?,?)";
        String tentaikhoan = userAccount.getTentaikhoan();
        String matkhau = userAccount.getMatkhau();
        String ngayhethantruycap = String.valueOf(userAccount.getNgayhethantruycap());
        String capdotaikhoan = String.valueOf(userAccount.getCapdotaikhoan());
        String email = userAccount.getEmail();
        db.execSQL(sql, new String[]{tentaikhoan, matkhau, ngayhethantruycap, capdotaikhoan, email});
        db.close();
    }

    public void XoaDL(String ma) {
        String sql = "Delete from UserAccount where mataikhoan = ?";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql, new String[]{ma});
    }

    public List<String> TraCuu() {
        List<String> listHoaDon = new ArrayList<>();
        String sql = "Select * from UserAccount";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                listHoaDon.add((String) cursor.getString(0).toString());
            } while (cursor.moveToNext());
        }
        return listHoaDon;
    }

    public List<String> TraCuu2() {
        List<String> listHoaDon = new ArrayList<>();
        String sql = "Select * from UserAccount";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                listHoaDon.add((String) cursor.getString(2).toString());
            } while (cursor.moveToNext());
        }
        return listHoaDon;
    }

    public List<String> DocDL() {
        List<String> listHoaDon = new ArrayList<>();
        String sql = "Select * from UserAccount";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                // listHoaDon.add((String) cursor.getString(0).toString());
                listHoaDon.add((String) cursor.getString(1).toString());
                //  listHoaDon.add((String) cursor.getString(2).toString());
            } while (cursor.moveToNext());
        }
        return listHoaDon;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
