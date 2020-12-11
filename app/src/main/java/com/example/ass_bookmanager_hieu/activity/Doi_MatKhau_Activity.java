package com.example.ass_bookmanager_hieu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ass_bookmanager_hieu.DAO.NguoiDungDAO;
import com.example.ass_bookmanager_hieu.R;
import com.example.ass_bookmanager_hieu.model.NguoiDung;

public class Doi_MatKhau_Activity extends AppCompatActivity {

    EditText edPass, edRePass;
    NguoiDungDAO nguoiDungDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi__mat_khau_);
        setTitle("Đổi mật  khẩu");
        init();
    }

    private void init() {
        edPass = findViewById(R.id.edPassword);
        edRePass = findViewById(R.id.edRePassword);
    }



    public int validateForm() {
        int check = 1;
        if (edPass.getText().length() == 0 || edRePass.getText().length() == 0) {
            Toast.makeText(getApplicationContext(), "Bạn phải nhập đầy đủ thông ",
                    Toast.LENGTH_SHORT).show();
            check = -1;
        } else {
            String pass = edPass.getText().toString();
            String rePass = edRePass.getText().toString();
            if (!pass.equals(rePass)) {
                Toast.makeText(getApplicationContext(), "Mật khẩu không trùng khớp",
                        Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }

    public void changePassword(View view) {
        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        String strUserName = pref.getString("USERNAME", "");
        nguoiDungDAO = new NguoiDungDAO(Doi_MatKhau_Activity.this);
        NguoiDung user = new NguoiDung(strUserName, edPass.getText().toString(), "",
                "");
        try {
            if (validateForm() > 0) {
                if (nguoiDungDAO.changePasswordNguoiDung(user) > 0) {
                    Toast.makeText(getApplicationContext(), "Lưu thành công",
                            Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Lưu thất bại",
                            Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }
    }

    public void huy(View view) {
        finish();
    }
}