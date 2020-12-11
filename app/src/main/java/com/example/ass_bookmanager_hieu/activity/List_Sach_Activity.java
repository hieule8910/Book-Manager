package com.example.ass_bookmanager_hieu.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.ass_bookmanager_hieu.DAO.SachDAO;
import com.example.ass_bookmanager_hieu.Login_Activity;
import com.example.ass_bookmanager_hieu.R;
import com.example.ass_bookmanager_hieu.adapter.SachAdapter;
import com.example.ass_bookmanager_hieu.model.Sach;

import java.util.ArrayList;
import java.util.List;

public class List_Sach_Activity extends AppCompatActivity {

    public static List<Sach> dsSach = new ArrayList<>();
    ListView lvBook;
    SachAdapter adapter = null;
    SachDAO sachDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__sach_);
        setTitle("SÁCH");

        lvBook = (ListView) findViewById(R.id.lvSach);
        sachDAO = new SachDAO(List_Sach_Activity.this);
        dsSach = sachDAO.getAllSach();
        adapter = new SachAdapter(this, dsSach);
        lvBook.setAdapter(adapter);
        lvBook.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Sach sach = (Sach) parent.getItemAtPosition(position);
                Intent intent = new
                        Intent(List_Sach_Activity.this,Detail_Sach_Activity.class);
                Bundle b = new Bundle();
                b.putString("MASACH", sach.getMaSach());
                b.putString("MATHELOAI", sach.getMaTheLoai());
                b.putString("TENSACH", sach.getTenSach());
                b.putString("TACGIA", sach.getTacGia());
                b.putString("NXB", sach.getNXB());
                b.putString("GIABIA", String.valueOf(sach.getGiaBia()));
                b.putString("SOLUONG", String.valueOf(sach.getSoLuong()));
                intent.putExtras(b);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_sach,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.addnewBook){
            startActivity(new Intent(List_Sach_Activity.this,Add_Sach_Activity.class));
        } else if(id == R.id.logout){
            startActivity(new Intent(List_Sach_Activity.this, Login_Activity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
