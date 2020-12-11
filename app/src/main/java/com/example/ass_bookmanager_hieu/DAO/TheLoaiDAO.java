package com.example.ass_bookmanager_hieu.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.ass_bookmanager_hieu.database.DatabaseHelper;
import com.example.ass_bookmanager_hieu.model.TheLoai;

import java.util.ArrayList;
import java.util.List;

public class TheLoaiDAO {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;
    public static final String TABLE_NAME = "TheLoai";
    public static final String SQL_THE_LOAI = "CREATE TABLE TheLoai(matheloai textprimary key," +
            "tentheloai text, mota text, vitri text);";
    public static final String TAG = "TheLoaiDAO";

    public TheLoaiDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    //insert
    public boolean insertTheloai(TheLoai tl) {
        ContentValues values = new ContentValues();
        values.put("matheloai", tl.getMaTheLoai());
        values.put("tentheloai", tl.getTenTheLoai());
        values.put("mota", tl.getMoTa());
        values.put("vitri", tl.getViTri());

        long result = db.insert(TABLE_NAME, null, values);
        try {
            if (result == -1) {
                return false;
            }

        } catch (Exception e) {
            Log.e("theloaiDAO ", e.toString());
            return false;
        }
        return true;
    }

    //getAllTheLoai
    public List<TheLoai> getAllTheLoai() {
        List<TheLoai> dsTheLoai = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME, null, null, null, null, null,
                null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            TheLoai ee = new TheLoai();
            ee.setMaTheLoai(c.getString(0));
            ee.setTenTheLoai(c.getString(1));
            ee.setMoTa(c.getString(2));
            ee.setViTri(c.getString(3));
            dsTheLoai.add(ee);
            Log.d("//=====", ee.toString());
            c.moveToNext();
        }
        c.close();
        return dsTheLoai;
    }

    //update
    public int updateTheLoai(TheLoai theLoai) {
        ContentValues values = new ContentValues();
        values.put("matheloai", theLoai.getMaTheLoai());
        values.put("tentheloai", theLoai.getTenTheLoai());
        values.put("mota", theLoai.getMoTa());
        values.put("vitri", theLoai.getViTri());
        int result = db.update(TABLE_NAME, values, "matheloai=?", new
                String[]{theLoai.getMaTheLoai()});
        if (result == 0) {
            return -1;
        }
        return 1;
    }

    //delete
    public int deleteTheLoaiByID(String matheloai) {
        int result = db.delete(TABLE_NAME, "matheloai=?", new String[]{matheloai});
        if (result == 0)
            return -1;
        return 1;
    }
}
