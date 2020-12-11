package com.example.ass_bookmanager_hieu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ass_bookmanager_hieu.DAO.NguoiDungDAO;
import com.example.ass_bookmanager_hieu.R;

public class Detail_NguoiDung_Activity extends AppCompatActivity {

    EditText edFullName, edPhone;
    NguoiDungDAO nguoiDungDAO;
    String username, fullname, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail__nguoi_dung_);
        setTitle("Chi tiết người dùng");
        init();
        getData();

    }

    private void getData() {
        nguoiDungDAO = new NguoiDungDAO(this);
        Intent in = getIntent();
        Bundle b = in.getExtras();
        fullname = b.getString("FULLNAME");
        phone = b.getString("PHONE");
        username = b.getString("USERNAME");
        edFullName.setText(fullname);
        edPhone.setText(phone);
    }

    private void init() {
        edFullName = (EditText) findViewById(R.id.edFullName);
        edPhone = (EditText) findViewById(R.id.edPhone);
    }

    public void updateUser(View view) {
        if (edPhone.getText().toString().isEmpty() || edFullName.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        } else if
        (nguoiDungDAO.updateInfoNguoiDung(username, edPhone.getText().toString(), edFullName.getText().toString()) > 0) {
            Toast.makeText(getApplicationContext(), "Update thành công", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Detail_NguoiDung_Activity.this, List_NguoiDung_Activity.class));
        }
    }

    public void Huy(View view) {
        finish();
    }
}
