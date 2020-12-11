package com.example.ass_bookmanager_hieu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.ass_bookmanager_hieu.DAO.HoaDonChiTietDAO;
import com.example.ass_bookmanager_hieu.R;

public class DoanhThu_Activity extends AppCompatActivity {
    TextView tvNgay, tvThang, tvNam;
    HoaDonChiTietDAO hoaDonChiTietDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doanh_thu_);
        setTitle("Thống Kê");

        tvNgay = (TextView) findViewById(R.id.tvThongKeNgay);
        tvThang = (TextView) findViewById(R.id.tvThongKeThang);
        tvNam = (TextView) findViewById(R.id.tvThongKeNam);
        hoaDonChiTietDAO = new HoaDonChiTietDAO(this);
        tvNgay.setText("Hôm nay: "+hoaDonChiTietDAO.getDoanhThuTheoNgay());
        tvThang.setText("Tháng này: "+hoaDonChiTietDAO.getDoanhThuTheoThang());
        tvNam.setText("Năm này: "+hoaDonChiTietDAO.getDoanhThuTheoNam());
    }
}
