package com.example.ass_bookmanager_hieu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ass_bookmanager_hieu.DAO.NguoiDungDAO;
import com.example.ass_bookmanager_hieu.R;
import com.example.ass_bookmanager_hieu.model.NguoiDung;

public class Add_NguoiDung_Activity extends AppCompatActivity {


    Button btnThemNguoiDung;
    NguoiDungDAO nguoiDungDAO;
    EditText edUser, edPass, edRePass, edPhone, edFullName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__nguoi_dung_);
        setTitle("Thêm Người Dùng");

        init();
    }

    public int validateForm() {
        int check = 1;
        if (edUser.getText().length() == 0 || edFullName.getText().length() == 0
                || edPhone.getText().length() == 0 || edPass.getText().length() == 0
                || edRePass.getText().length() == 0) {
            Toast.makeText(getApplicationContext(), "Bạn phải nhập đầy đủ thông tin ",
                    Toast.LENGTH_SHORT).show();
            check = -1;
        } else {
            String pass = edPass.getText().toString();
            String rePass = edRePass.getText().toString();
            if (!pass.equals(rePass)) {
                Toast.makeText(getApplicationContext(), "Mật khẩu không trùng khớp",
                        Toast.LENGTH_SHORT).show();
                check = -1;
            } else if(pass.length()<7){
                Toast.makeText(getApplicationContext(), "Mật khẩu phải có 6 kí tự",
                        Toast.LENGTH_SHORT).show();
                check = -1;
            }else if(edPhone.getText().toString().length()>11 ||edPhone.getText().toString().length()<10 ){
                Toast.makeText(getApplicationContext(), "SĐT phải có từ 10-11 kí tự",
                        Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }

    private void init() {
        btnThemNguoiDung = (Button) findViewById(R.id.btnAddUser);
        edUser = (EditText) findViewById(R.id.edUserName);
        edPass = (EditText) findViewById(R.id.edPassword);
        edPhone = (EditText) findViewById(R.id.edPhone);
        edFullName = (EditText) findViewById(R.id.edFullName);
        edRePass = (EditText) findViewById(R.id.edRePassword);
    }

    public void addUser(View view) {
        nguoiDungDAO = new NguoiDungDAO(Add_NguoiDung_Activity.this);
        NguoiDung user = new NguoiDung(edUser.getText().toString(),
                edPass.getText().toString(),
                edPhone.getText().toString(), edFullName.getText().toString());
        try {
            if (validateForm() > 0) {
                boolean isInsert = nguoiDungDAO.insertNguoiDung(user);
                if (isInsert) {
                    Toast.makeText(getApplicationContext(), "Thêm thành công",
                            Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Thêm thất bại",
                            Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }
    }

    public void listUsers(View view) {
        startActivity(new Intent(Add_NguoiDung_Activity.this,List_NguoiDung_Activity.class));
        finish();
    }

    public void huy(View view) {
        finish();
    }
}
