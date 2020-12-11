package com.example.ass_bookmanager_hieu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ass_bookmanager_hieu.DAO.NguoiDungDAO;
import com.example.ass_bookmanager_hieu.model.NguoiDung;

public class Login_Activity extends AppCompatActivity {

    Button btnLogin, btnExit;
    EditText edUser, edPass;
    CheckBox ckncheck;
    NguoiDungDAO nguoiDungDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);
        setTitle("QUẢN LÍ SÁCH");
        init();
    }

    private void init() {
        //anh xa
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnExit = (Button) findViewById(R.id.btnExit);
        edUser = findViewById(R.id.edUser);
        edPass = findViewById(R.id.edPass);
        ckncheck = findViewById(R.id.cknCheck);
    }

    public void checkLogin(View view) {
        nguoiDungDAO = new NguoiDungDAO(this);
        String username = edUser.getText().toString();
        String password = edPass.getText().toString();

        NguoiDung nguoiDung = new NguoiDung(username, password);

        boolean result = nguoiDungDAO.isLogin(nguoiDung);
        if (username.length() == 0 || password.length() == 0) {
            Toast.makeText(getApplicationContext(), "không được bỏ trống.phải ghi đầy đủ", Toast.LENGTH_LONG).show();
            return;
        }
        if (password.length() <5) {
            Toast.makeText(getApplicationContext(), " phải trên 5 ký tự", Toast.LENGTH_LONG).show();
            return;
        }
        if (result) {
            Intent intent = new Intent(Login_Activity.this, MainActivity.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "Login thanh cong", Toast.LENGTH_LONG).show();
            rememberUser(username, password, ckncheck.isChecked());
        } else {
            Toast.makeText(getApplicationContext(), "Login lỗi", Toast.LENGTH_LONG).show();
        }
    }


    public void rememberUser(String u, String p, boolean status) {
        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        if (!status) {
            //xoa tinh trang luu tru truoc do
            edit.clear();
        } else {
            //luu du lieu
            edit.putString("USERNAME", u);
            edit.putString("PASSWORD", p);
            edit.putBoolean("REMEMBER", status);
        }
        //luu lai toan bo
        edit.commit();
    }
}
