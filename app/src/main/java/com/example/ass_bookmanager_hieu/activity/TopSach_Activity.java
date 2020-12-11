package com.example.ass_bookmanager_hieu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ass_bookmanager_hieu.DAO.SachDAO;
import com.example.ass_bookmanager_hieu.R;
import com.example.ass_bookmanager_hieu.adapter.SachAdapter;
import com.example.ass_bookmanager_hieu.model.Sach;

import java.util.ArrayList;
import java.util.List;

public class TopSach_Activity extends AppCompatActivity {

    public static List<Sach> dsSach = new ArrayList<>();
    ListView lvBook;
    SachAdapter adapter = null;
    SachDAO sachDAO;
    EditText edThang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_sach_);
        setTitle("TOP 10 SÁCH BÁN CHẠY");

        lvBook = (ListView) findViewById(R.id.lvBookTop);
        edThang = (EditText) findViewById(R.id.edThang);
    }

    public void viewSachTop10(View view) {
//        try{
//            int so= Integer.parseInt(edThang.getText().toString());
//                     so = Integer.parseInt(String.valueOf(edThang));
//        } catch(Exception e){
//            Toast.makeText(getApplicationContext(),
//                    "Không đúng định dạng số", Toast.LENGTH_SHORT).show(); }

        if (Integer.parseInt(edThang.getText().toString()) > 12 || Integer.parseInt(edThang.getText().toString()) < 0) {
            Toast.makeText(getApplicationContext(), "Không đúng định dạng tháng (1- 12)", Toast.LENGTH_SHORT).show();
        } else
            {
            sachDAO = new SachDAO(TopSach_Activity.this);
            dsSach = sachDAO.getSachTop10(edThang.getText().toString());
            adapter = new SachAdapter(this, dsSach);
            lvBook.setAdapter(adapter);
        }



    }
}