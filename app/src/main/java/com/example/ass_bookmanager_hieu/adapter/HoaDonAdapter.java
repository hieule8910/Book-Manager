package com.example.ass_bookmanager_hieu.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ass_bookmanager_hieu.DAO.HoaDonDAO;
import com.example.ass_bookmanager_hieu.R;
import com.example.ass_bookmanager_hieu.model.HoaDon;

import java.text.SimpleDateFormat;
import java.util.List;

public class HoaDonAdapter extends BaseAdapter {
    List<HoaDon> arrHoaDon;
    List<HoaDon> arrSortHoaDon;
    private Filter hoaDonFilter;
    public Activity context;
    public LayoutInflater inflater;
    HoaDonDAO hoadonDAO;
    //    HoaDonChiTietDAO hoaDonChiTietDAO;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    public HoaDonAdapter(Activity context, List<HoaDon> arrayHoaDon) {
        super();
        this.context = context;
        this.arrHoaDon = arrayHoaDon;
        this.arrSortHoaDon = arrayHoaDon;
        this.inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        hoadonDAO = new HoaDonDAO(context);
        // hoaDonChiTietDAO = new HoaDonChiTietDAO(context);
    }

    @Override
    public int getCount() {
        return arrHoaDon.size();
    }

    @Override
    public Object getItem(int position) {
        return arrHoaDon.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class ViewHolder {
        ImageView imgHD;
        TextView txtMaHoaDon;
        TextView txtNgayMua;
        ImageView imgDelete;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_hoadon, null);
            holder.imgHD = (ImageView) convertView.findViewById(R.id.ivIconHoadon);
            holder.txtMaHoaDon = (TextView)
                    convertView.findViewById(R.id.tvMaHoaDon);
            holder.txtNgayMua = (TextView) convertView.findViewById(R.id.tvNgayMua);
            holder.imgDelete = (ImageView) convertView.findViewById(R.id.ivDelete);
            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hoadonDAO.deleteHoaaDonByID(arrHoaDon.get(position).getMaHoaDon());
                    arrHoaDon.remove(position);
                    notifyDataSetChanged();
                }
            });
            convertView.setTag(holder);

        } else
            holder = (ViewHolder) convertView.getTag();
        HoaDon _entry = (HoaDon) arrHoaDon.get(position);
        holder.imgHD.setImageResource(R.drawable.hdicon);
        holder.txtMaHoaDon.setText(_entry.getMaHoaDon());
        holder.txtNgayMua.setText(sdf.format(_entry.getNgayMua()));
        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
}
