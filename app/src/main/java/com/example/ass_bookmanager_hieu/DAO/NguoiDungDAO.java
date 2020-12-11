package com.example.ass_bookmanager_hieu.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.ass_bookmanager_hieu.database.DatabaseHelper;
import com.example.ass_bookmanager_hieu.model.NguoiDung;

import java.util.ArrayList;
import java.util.List;

public class NguoiDungDAO {

    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;
    public static final String TABLE_NAME = "NguoiDung";
    public static final String SQL_NGUOI_DUNG = "CREATE TABLE NguoiDung (username text primary key, password text, phone text, hoten text);";
    public static final String TAG = "NguoiDungDAO";

    public NguoiDungDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public boolean insertNguoiDung(NguoiDung nd) {
        ContentValues values = new ContentValues();
        values.put("username", nd.getUserName());
        values.put("password", nd.getPassword());
        values.put("phone", nd.getPhone());
        values.put("hoten", nd.getHoTen());
        long result = db.insert(TABLE_NAME, null, values);
        try {
            if (result == -1) {
                return false;
            }

        } catch (Exception e) {
            Log.e("NguoidungDAO ", e.toString());
            return false;
        }
        return true;
    }

    public int deleteNguoiDungById(String username) {
        int result = db.delete(TABLE_NAME, "username=?", new String[]{username});
        if (result == 0)
            return -1;
        return 1;
    }

    public int updateInfoNguoiDung(String username, String phone, String name) {
        ContentValues values = new ContentValues();
        values.put("phone", phone);
        values.put("hoten", name);
        int result = db.update(TABLE_NAME, values, "username=?", new
                String[]{username});
        if (result == 0) {
            return -1;
        }
        return 1;
    }

    public List<NguoiDung> getAllNguoiDung() {
        List<NguoiDung> dsNguoiDung = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            NguoiDung ee = new NguoiDung();
            ee.setUserName(c.getString(0));
            ee.setPassword(c.getString(1));
            ee.setPhone(c.getString(2));
            ee.setHoTen(c.getString(3));
            dsNguoiDung.add(ee);
            Log.d("//=====", ee.toString());
            c.moveToNext();
        }
        c.close();
        return dsNguoiDung;
    }

    public int changePasswordNguoiDung(NguoiDung nd){
        ContentValues values = new ContentValues();
        values.put("username",nd.getUserName());
        values.put("password",nd.getPassword());
        int result = db.update(TABLE_NAME,values,"username=?", new
                String[]{nd.getUserName()});
        if (result == 0){
            return -1;
        }
        return 1;
    }

    public boolean isLogin(NguoiDung nd){

        String sqlSelect=" select username, password from nguoidung where username=? and password = ?";

        String username = nd.getUserName();
        String password = nd.getPassword();

        Cursor c = db.rawQuery(sqlSelect, new String[]{username,password});

        if(c.moveToFirst()){
            return true;
        }

        return false;
    }


}
