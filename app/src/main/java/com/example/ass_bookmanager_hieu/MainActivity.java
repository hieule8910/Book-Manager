package com.example.ass_bookmanager_hieu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.ass_bookmanager_hieu.activity.DoanhThu_Activity;
import com.example.ass_bookmanager_hieu.activity.Doi_MatKhau_Activity;
import com.example.ass_bookmanager_hieu.activity.List_HoaDon_Activity;
import com.example.ass_bookmanager_hieu.activity.List_NguoiDung_Activity;
import com.example.ass_bookmanager_hieu.activity.List_Sach_Activity;
import com.example.ass_bookmanager_hieu.activity.List_TheLoai_Activity;
import com.example.ass_bookmanager_hieu.activity.TopSach_Activity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("QUẢN LÍ SÁCH");
    }

    //dung menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    //chuc nang tren overflow menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.changePass){
            Intent intent = new Intent(MainActivity.this, Doi_MatKhau_Activity.class);
            startActivity(intent);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);

        } else if(id == R.id.logout){
            startActivity(new Intent(MainActivity.this, Login_Activity.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void nguoiDung(View view) {
        startActivity(new Intent(MainActivity.this, List_NguoiDung_Activity.class));
    }

    public void theLoai(View view) {
        startActivity(new Intent(MainActivity.this, List_TheLoai_Activity.class));
    }

    public void sach(View view) {
        startActivity(new Intent(MainActivity.this, List_Sach_Activity.class));
    }

    public void hoaDon(View view) {
        startActivity(new Intent(MainActivity.this, List_HoaDon_Activity.class));
    }

    public void sachBanChay(View view) {
        startActivity(new Intent(MainActivity.this, TopSach_Activity.class));
    }

    public void thongKe(View view) {
        startActivity(new Intent(MainActivity.this, DoanhThu_Activity.class));
    }
}
