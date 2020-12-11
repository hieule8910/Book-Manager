package com.example.ass_bookmanager_hieu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ass_bookmanager_hieu.DAO.SachDAO;
import com.example.ass_bookmanager_hieu.DAO.TheLoaiDAO;
import com.example.ass_bookmanager_hieu.R;
import com.example.ass_bookmanager_hieu.model.Sach;
import com.example.ass_bookmanager_hieu.model.TheLoai;

import java.util.ArrayList;
import java.util.List;

public class Add_Sach_Activity extends AppCompatActivity {

    SachDAO sachDAO;
    TheLoaiDAO theLoaiDAO;
    Spinner spnTheLoai;
    EditText edMaSach, edTenSach, edNXB, edTacGia, edGiaBia, edSoLuong;
    String maTheLoai = "";
    List<TheLoai> listTheLoai = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__sach_);
        setTitle("Thêm sách");
        init();

        spnTheLoai = (Spinner) findViewById(R.id.spnTheLoai);
        getTheLoai();
        spnTheLoai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maTheLoai = listTheLoai.get(spnTheLoai.getSelectedItemPosition()).getMaTheLoai();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void init() {
        edMaSach = (EditText) findViewById(R.id.edMaSach);
        edTenSach = (EditText) findViewById(R.id.edTenSach);
        edNXB = (EditText) findViewById(R.id.edNXB);
        edTacGia = (EditText) findViewById(R.id.edTacGia);
        edGiaBia = (EditText) findViewById(R.id.edGiaBia);
        edSoLuong = (EditText) findViewById(R.id.edSoLuong);
    }

    public void getTheLoai() {   //Lấy giá trị của Spinner
        theLoaiDAO = new TheLoaiDAO(Add_Sach_Activity.this);
        listTheLoai = theLoaiDAO.getAllTheLoai();
        ArrayAdapter<TheLoai> dataAdapter = new ArrayAdapter<TheLoai>(this,
                android.R.layout.simple_spinner_item, listTheLoai);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnTheLoai.setAdapter(dataAdapter);
    }

    public void addbook(View view) {
        sachDAO = new SachDAO(Add_Sach_Activity.this);
        Sach sach = new Sach(edMaSach.getText().toString(), maTheLoai, edTenSach.getText().toString(),
                edTacGia.getText().toString(), edNXB.getText().toString(),
                Double.parseDouble(edGiaBia.getText().toString()), Integer.parseInt(edSoLuong.getText
                ().toString()));
        try {
            if (sachDAO.inserSach(sach) > 0) {
                Toast.makeText(getApplicationContext(), "Thêm thành công",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Thêm thất bại",
                        Toast.LENGTH_SHORT).show();
            }
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }
    }

    public void huy(View view) {
        finish();
    }

    public void showListBook(View view) {
        startActivity(new Intent(Add_Sach_Activity.this, List_Sach_Activity.class));
        finish();
    }

    private int validateForm() {
        if (edMaSach.getText().toString().isEmpty() || edTenSach.getText().toString().isEmpty() ||
                edTacGia.getText().toString().isEmpty() || edNXB.getText().toString().isEmpty() ||
                edGiaBia.getText().toString().isEmpty() || edSoLuong.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ thông tin",
                    Toast.LENGTH_SHORT).show();
            return -1;
        }
        return 1;
    }
}
