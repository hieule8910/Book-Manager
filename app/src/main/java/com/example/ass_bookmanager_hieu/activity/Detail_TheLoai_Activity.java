package com.example.ass_bookmanager_hieu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ass_bookmanager_hieu.DAO.TheLoaiDAO;
import com.example.ass_bookmanager_hieu.R;
import com.example.ass_bookmanager_hieu.model.TheLoai;

public class Detail_TheLoai_Activity extends AppCompatActivity {

    EditText edTen, edVitri, edMota;

    String maTL, tenTL, Vitri, Mota;

    TheLoaiDAO theloaiDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail__the_loai_);
        setTitle("Chi Tiết Thể Loại");
        init();
        getData();
    }

    private void init() {
        edTen = findViewById(R.id.edTLDetail);
        edMota = findViewById(R.id.edMTDetail);
        edVitri = findViewById(R.id.edVitriDetail);

        theloaiDAO = new TheLoaiDAO(this);
    }
    public void getData(){
        Intent intent = getIntent();

        Bundle b = intent.getExtras();

        maTL = b.getString("MATHELOAI");
        tenTL = b.getString("TENTHELOAI");
        edTen.setText(tenTL);
        Mota = b.getString("MOTA");
        edMota.setText(Mota);
        Vitri = b.getString("VITRI");
        edVitri.setText(Vitri);
    }

    public void updateTL(View view) {
        TheLoai theLoai = new TheLoai(maTL, edTen.getText().toString(), edMota.getText().toString(),
                edVitri.getText().toString());
        if (edTen.getText().toString().length() == 0 || edMota.getText().toString().length() == 0
                || edVitri.getText().toString().length() == 0) {
            Toast.makeText(getApplicationContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        } else if
        (theloaiDAO.updateTheLoai(theLoai) > 0) {
            Toast.makeText(getApplicationContext(), "Update thành công", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(getApplicationContext(), "Update that bai", Toast.LENGTH_SHORT).show();

        }
    }

    public void listTL(View view) {
        startActivity(new Intent(Detail_TheLoai_Activity.this,List_TheLoai_Activity.class));
    }

    public void huy(View view) {
        finish();
    }
}
