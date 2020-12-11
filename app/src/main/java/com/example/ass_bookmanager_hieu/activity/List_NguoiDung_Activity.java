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

import com.example.ass_bookmanager_hieu.DAO.NguoiDungDAO;
import com.example.ass_bookmanager_hieu.Login_Activity;
import com.example.ass_bookmanager_hieu.R;
import com.example.ass_bookmanager_hieu.adapter.NguoiDungAdapter;
import com.example.ass_bookmanager_hieu.model.NguoiDung;

import java.util.ArrayList;
import java.util.List;

public class List_NguoiDung_Activity extends AppCompatActivity {
    public static List<NguoiDung> dsNguoiDung = new ArrayList<>();
    ListView lvNguoiDung;
    NguoiDungAdapter adapter = null;
    NguoiDungDAO nguoiDungDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__nguoi_dung_);
        setTitle("NGƯỜI DÙNG");
        lvNguoiDung = findViewById(R.id.lvNguoiDung);

        nguoiDungDAO = new NguoiDungDAO(List_NguoiDung_Activity.this);

        dsNguoiDung = nguoiDungDAO.getAllNguoiDung();
        adapter = new NguoiDungAdapter(List_NguoiDung_Activity.this, dsNguoiDung);
        lvNguoiDung.setAdapter(adapter);
        lvNguoiDung.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new
                        Intent(List_NguoiDung_Activity.this, Detail_NguoiDung_Activity.class);
                Bundle b = new Bundle();
                b.putString("USERNAME", dsNguoiDung.get(position).getUserName());
                b.putString("PHONE", dsNguoiDung.get(position).getPhone());
                b.putString("FULLNAME", dsNguoiDung.get(position).getHoTen());
                intent.putExtras(b);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_user, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.addUser:
                intent = new
                        Intent(List_NguoiDung_Activity.this, Add_NguoiDung_Activity.class);
                startActivity(intent);
                finish();
                return (true);
            case R.id.logout:
                intent = new
                        Intent(List_NguoiDung_Activity.this, Login_Activity.class);
                startActivity(intent);
                finish();
                return (true);

        }
        return super.onOptionsItemSelected(item);
    }
}