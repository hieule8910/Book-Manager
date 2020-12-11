package com.example.ass_bookmanager_hieu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.example.ass_bookmanager_hieu.DAO.HoaDonChiTietDAO;
import com.example.ass_bookmanager_hieu.R;
import com.example.ass_bookmanager_hieu.adapter.CartAdapter;
import com.example.ass_bookmanager_hieu.model.HoaDonChiTiet;

import java.util.ArrayList;
import java.util.List;

public class Detail_HDCT_Activity extends AppCompatActivity {
    public List<HoaDonChiTiet> dsHDCT = new ArrayList<>();
    ListView lvCart;
    CartAdapter adapter = null;
    HoaDonChiTietDAO hoaDonChiTietDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail__hdct_);
        setTitle("Hóa Đơn Chi Tiết");

        lvCart = (ListView) findViewById(R.id.lvHoaDonChiTiet);
        hoaDonChiTietDAO = new HoaDonChiTietDAO(Detail_HDCT_Activity.this);
        Intent in = getIntent();
        Bundle b = in.getExtras();
        if (b != null) {
            dsHDCT =
                    hoaDonChiTietDAO.getAllHoaDonChiTietByID(b.getString("MAHOADON"));
        }
        adapter = new CartAdapter(this, dsHDCT);
        lvCart.setAdapter(adapter);
    }
}
