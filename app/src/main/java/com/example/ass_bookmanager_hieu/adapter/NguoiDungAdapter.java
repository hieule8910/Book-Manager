package com.example.ass_bookmanager_hieu.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ass_bookmanager_hieu.DAO.NguoiDungDAO;
import com.example.ass_bookmanager_hieu.R;
import com.example.ass_bookmanager_hieu.model.NguoiDung;

import java.util.List;

public class NguoiDungAdapter extends BaseAdapter {
    List<NguoiDung> arrNguoiDung;
    public Activity context;
    public LayoutInflater inflater;
    NguoiDungDAO nguoiDungDAO;

    public NguoiDungAdapter(Activity context, List<NguoiDung> arrayNguoiDung) {
        super();
        this.context = context;
        this.arrNguoiDung = arrayNguoiDung;
        this.inflater =
                (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        nguoiDungDAO = new NguoiDungDAO(context);
    }
    @Override
    public int getCount() {
        return arrNguoiDung.size();
    }

    @Override
    public Object getItem(int position) {
        return arrNguoiDung.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class ViewHolder{
        ImageView imgUser;
        TextView txtName;
        TextView txtPhone;
        ImageView imgDelete;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null)
        {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_nguoi_dung, null);
            holder.imgUser = (ImageView) convertView.findViewById(R.id.ivIconUser);
            holder.txtName = (TextView) convertView.findViewById(R.id.tvName);
            holder.txtPhone = (TextView) convertView.findViewById(R.id.tvPhone);
            holder.imgDelete = (ImageView)convertView.findViewById(R.id.ivDelete);
            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    nguoiDungDAO.deleteNguoiDungById(arrNguoiDung.get(position).getUserName());
                    arrNguoiDung.remove(position);
                    notifyDataSetChanged();
                }
            });
            convertView.setTag(holder);
        }
        else
            holder=(ViewHolder)convertView.getTag();
        NguoiDung _entry = (NguoiDung) arrNguoiDung.get(position);
        if (position % 3 ==0) {
            holder.imgUser.setImageResource(R.drawable.emone);
        }else if (position % 3 == 1){
            holder.imgUser.setImageResource(R.drawable.emtwo);
        }else {
            holder.imgUser.setImageResource(R.drawable.emthree);
        }
        holder.txtName.setText(_entry.getHoTen());
        holder.txtPhone.setText(_entry.getPhone());
        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
}
