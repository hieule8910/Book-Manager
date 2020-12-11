package com.example.ass_bookmanager_hieu.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ass_bookmanager_hieu.DAO.HoaDonChiTietDAO;
import com.example.ass_bookmanager_hieu.DAO.HoaDonDAO;
import com.example.ass_bookmanager_hieu.DAO.NguoiDungDAO;
import com.example.ass_bookmanager_hieu.DAO.SachDAO;
import com.example.ass_bookmanager_hieu.DAO.TheLoaiDAO;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "manager";
    public static final int VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(NguoiDungDAO.SQL_NGUOI_DUNG);
        db.execSQL(TheLoaiDAO.SQL_THE_LOAI);
        db.execSQL(SachDAO.baoveASM);
        db.execSQL(HoaDonDAO.SQL_HOA_DON);
        db.execSQL(HoaDonChiTietDAO.SQL_HOA_DON_CHI_TIET);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + NguoiDungDAO.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TheLoaiDAO.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + SachDAO.TABLE_NAME);
        db.execSQL(" DROP TABLE IF EXISTS " + HoaDonDAO.TABLE_NAME);
        db.execSQL(" DROP TABLE IF EXISTS " + HoaDonChiTietDAO.TABLE_NAME);

        onCreate(db);

    }
}
