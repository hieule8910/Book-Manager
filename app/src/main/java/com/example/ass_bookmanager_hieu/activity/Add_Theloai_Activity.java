package com.example.ass_bookmanager_hieu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ass_bookmanager_hieu.DAO.TheLoaiDAO;
import com.example.ass_bookmanager_hieu.R;
import com.example.ass_bookmanager_hieu.model.TheLoai;

public class Add_Theloai_Activity extends AppCompatActivity {
    EditText edMaTL, edTenTL, edMota, edVitri;
    TheLoaiDAO theloaiDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__theloai_);
        setTitle("Thêm Thể Loại");
        
        init();
        
    }
    //anh xa
    private void init() {
        edMaTL = findViewById(R.id.edMaTheloai);
        edTenTL = findViewById(R.id.edTentheloai);
        edMota = findViewById(R.id.edMota);
        edVitri = findViewById(R.id.edVitri);
    }

    //chuc nang them the loai
    public void addnewTheloai(View view) {
        theloaiDAO = new TheLoaiDAO(Add_Theloai_Activity.this);

        String maTL = edMaTL.getText().toString();
        String tenTL = edTenTL.getText().toString();
        String mota = edMota.getText().toString();
        String vitri = edVitri.getText().toString();

        TheLoai theLoai = new TheLoai(maTL, tenTL, mota, vitri);
        boolean isInsert = theloaiDAO.insertTheloai(theLoai);

        try{
            if(validate()>0){

                if (isInsert) {
                    Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Thêm thất bại", Toast.LENGTH_LONG).show();
                }
            }
        } catch (Exception e){
            Log.e("ADDTheLoai",e.toString());
        }
    }

    public int validate(){
        int check = 1;
        if (edMaTL.getText().length() == 0 || edTenTL.getText().length() == 0
                || edMota.getText().length() == 0 || edVitri.getText().length()==0) {
            Toast.makeText(getApplicationContext(), "Bạn phải nhập đầy đủ thông tin ",
                    Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }

    public void huy(View view){
        finish();
    }


    public void listTheloai(View view) {
        startActivity(new Intent(Add_Theloai_Activity.this, List_TheLoai_Activity.class));
        finish();
    }
}
