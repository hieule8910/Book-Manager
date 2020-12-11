package com.example.ass_bookmanager_hieu.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.ass_bookmanager_hieu.DAO.HoaDonDAO;
import com.example.ass_bookmanager_hieu.Login_Activity;
import com.example.ass_bookmanager_hieu.R;
import com.example.ass_bookmanager_hieu.adapter.HoaDonAdapter;
import com.example.ass_bookmanager_hieu.model.HoaDon;

import java.util.ArrayList;
import java.util.List;

public class List_HoaDon_Activity extends AppCompatActivity {

    public static List<HoaDon> dsHoaDon = new ArrayList<>();
    ListView lvHoaDon;
    HoaDonDAO hoaDonDAO;
    HoaDonAdapter adapter=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__hoa_don_);
        setTitle("HOÁ ĐƠN");
        lvHoaDon = (ListView) findViewById(R.id.lvhoadon);
        hoaDonDAO = new HoaDonDAO(List_HoaDon_Activity.this);
        try {
            dsHoaDon = hoaDonDAO.getAllHoaDon();
        } catch (Exception e) {
            Log.d("Error: ", e.toString());
        }
        adapter = new HoaDonAdapter(this, dsHoaDon);
        lvHoaDon.setAdapter(adapter);
        lvHoaDon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HoaDon hoaDon = (HoaDon) parent.getItemAtPosition(position);
                Intent intent = new Intent(List_HoaDon_Activity.this,
                        Detail_HDCT_Activity.class);
                Bundle b = new Bundle();
                b.putString("MAHOADON", hoaDon.getMaHoaDon());
                intent.putExtras(b);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_hoadon,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.addnewHD){
            startActivity(new Intent(List_HoaDon_Activity.this,Add_HoaDon_Activity.class));
        } else if(id == R.id.logout){
            startActivity(new Intent(List_HoaDon_Activity.this, Login_Activity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
