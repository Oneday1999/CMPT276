package com.example.myapplication.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.db.AccountBean;

import java.util.Calendar;
import java.util.List;

public class AccountAdapter extends BaseAdapter {
    Context context;
    List<AccountBean>mDatas;
    LayoutInflater inflater;
    int year,month,day;
    public AccountAdapter(Context context, List<AccountBean> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
        inflater = LayoutInflater.from(context);
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH)+1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
        Log.d(String.valueOf(year) + month + day, "getView: ");
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_main,parent,false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        AccountBean bean = mDatas.get(position);
        holder.typeIv.setImageResource(bean.getsImageId());
        holder.typeTv.setText(bean.getTypename());
        holder.noteTv.setText(bean.getComment());
        holder.moneyTv.setText("$ "+bean.getMoney());
        if (bean.getYear()==year&&bean.getMonth()==month&&bean.getDay()==day) {
            String time = bean.getTime().split(" ")[1];
            Log.d(time, "getView: ");
            holder.timeTv.setText("Today "+time);
        }else {
            Log.d(bean.getTime(), "Time: ");
            holder.timeTv.setText(bean.getTime());
        }
        return convertView;
    }

    static class ViewHolder{
        ImageView typeIv;
        TextView typeTv,noteTv,timeTv,moneyTv;
        public ViewHolder(View view){
            typeIv = view.findViewById(R.id.list_mainly);
            typeTv = view.findViewById(R.id.list_mainly_tv_title);
            timeTv = view.findViewById(R.id.list_mainly_tv_time);
            noteTv = view.findViewById(R.id.list_mainly_tv_notes);
            moneyTv = view.findViewById(R.id.list_mainly_tv_costs);
        }
    }
}
