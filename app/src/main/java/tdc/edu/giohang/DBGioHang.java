package tdc.edu.giohang;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBGioHang extends SQLiteOpenHelper {

    public DBGioHang(@Nullable Context context) {
        super(context, "QLBach_HoaXanh", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table Hoa_Don(maDH text,thongtin text,ngay text)";
        db.execSQL(sql);
    }
    public void ThemDL(String maHD, String chuoi,String ngay) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "INSERT INTO Hoa_Don VALUES(?,?,?)";
        db.execSQL(sql, new String[]{maHD,chuoi,ngay});
        db.close();
    }
    public void XoaDL(String ma) {
        String sql = "Delete from Hoa_Don where maDH = ?";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql, new String[]{ma});
    }
    public  List<String> TraCuu() {
        List<String> listHoaDon = new ArrayList<>();
        String sql = "Select * from Hoa_Don";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                listHoaDon.add((String) cursor.getString(0).toString());
            } while (cursor.moveToNext());
        }
        return listHoaDon;
    }
    public  List<String> TraCuu2() {
        List<String> listHoaDon = new ArrayList<>();
        String sql = "Select * from Hoa_Don";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                listHoaDon.add((String) cursor.getString(2).toString());
            } while (cursor.moveToNext());
        }
        return listHoaDon;
    }
    public  List<String> DocDL() {
        List<String> listHoaDon = new ArrayList<>();
        String sql = "Select * from Hoa_Don";
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
