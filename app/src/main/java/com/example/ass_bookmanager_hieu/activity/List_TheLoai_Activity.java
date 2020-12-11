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

import com.example.ass_bookmanager_hieu.DAO.TheLoaiDAO;
import com.example.ass_bookmanager_hieu.Login_Activity;
import com.example.ass_bookmanager_hieu.R;
import com.example.ass_bookmanager_hieu.adapter.TheLoaiAdapter;
import com.example.ass_bookmanager_hieu.model.TheLoai;

import java.util.ArrayList;
import java.util.List;

public class List_TheLoai_Activity extends AppCompatActivity {

    ListView lvTheLoai;
    public static List<TheLoai> dsTheLoai = new ArrayList<>();
    TheLoaiDAO theLoaiDAO;
    TheLoaiAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__the_loai_);
        lvTheLoai= (ListView)findViewById(R.id.lvTheLoai);
        setTitle("THỂ LOẠI");

        theLoaiDAO = new TheLoaiDAO(List_TheLoai_Activity.this);
        dsTheLoai= theLoaiDAO.getAllTheLoai();
        adapter = new TheLoaiAdapter(List_TheLoai_Activity.this,dsTheLoai);
        lvTheLoai.setAdapter(adapter);
        lvTheLoai.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(List_TheLoai_Activity.this,Detail_TheLoai_Activity.class);
                Bundle bundle =new Bundle();
                bundle.putString("MATHELOAI",dsTheLoai.get(position).getMaTheLoai());
                bundle.putString("TENTHELOAI",dsTheLoai.get(position).getTenTheLoai());
                bundle.putString("MOTA",dsTheLoai.get(position).getMoTa());
                bundle.putString("VITRI",dsTheLoai.get(position).getViTri());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_theloai, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.addnewTL) {
            startActivity(new Intent(List_TheLoai_Activity.this, Add_Theloai_Activity.class));
        } else if(id == R.id.logout){
            startActivity(new Intent(List_TheLoai_Activity.this, Login_Activity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
