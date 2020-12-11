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

import com.example.ass_bookmanager_hieu.DAO.SachDAO;
import com.example.ass_bookmanager_hieu.R;
import com.example.ass_bookmanager_hieu.model.Sach;

import java.util.List;

public class SachAdapter extends BaseAdapter {

    List<Sach> arrSach;
    List<Sach> arrSortSach;
    private Filter sachFilter;
    public Activity context;
    public LayoutInflater inflater;
    SachDAO sachDAO;
    public SachAdapter(Activity context, List<Sach> arraySach) {
        super();
        this.context = context;
        this.arrSach = arraySach;
        this.arrSortSach = arraySach;
        this.inflater =
                (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        sachDAO = new SachDAO(context);
    }
    @Override
    public int getCount() {
        return arrSach.size();
    }

    @Override
    public Object getItem(int position) {
        return arrSach.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    public static class ViewHolder{
        ImageView imgBook;
        TextView txtBookName;
        TextView txtBookPrice;
        TextView txtSoLuong;
        ImageView imgDelete;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null)
        {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_book, null);
            holder.imgBook = (ImageView) convertView.findViewById(R.id.ivIconBook);
            holder.txtBookName = (TextView)
                    convertView.findViewById(R.id.tvBookName);
            holder.txtBookPrice = (TextView)
                    convertView.findViewById(R.id.tvBookPrice);
            holder.txtSoLuong= (TextView) convertView.findViewById(R.id.tvSoLuong);
            holder.imgDelete = (ImageView)convertView.findViewById(R.id.ivDelete);
            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sachDAO.deleteSachByID(arrSach.get(position).getMaSach());
                    arrSach.remove(position);
                    notifyDataSetChanged();
                }
            });
            convertView.setTag(holder);
        } else
            holder=(ViewHolder)convertView.getTag();
        Sach _entry = (Sach) arrSach.get(position);
        holder.imgBook.setImageResource(R.drawable.bookicon);
        holder.txtBookName.setText("Mã sách: "+_entry.getMaSach());
        holder.txtSoLuong.setText("Số lượng: "+_entry.getSoLuong());
        holder.txtBookPrice.setText("Giá: "+ _entry.getGiaBia()+" ");
        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
}
