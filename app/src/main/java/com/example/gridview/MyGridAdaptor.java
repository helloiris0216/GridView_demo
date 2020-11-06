package com.example.gridview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

public class MyGridAdaptor extends BaseAdapter {
    private final LayoutInflater myLayoutInflater;
    private final List<Map<String, Object>> myItemList;

    public MyGridAdaptor(Context context, List<Map<String, Object>> itemList) {
        myLayoutInflater = LayoutInflater.from(context);
        myItemList = itemList;
    }

    @Override
    public int getCount() {
        return myItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return myItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    private class ViewHolder{
        ImageView imageId;
        TextView nameId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        //存id
        //如果convertView是空的
        if (convertView == null){
            convertView = myLayoutInflater.inflate(R.layout.item_layout, null);
            holder = new ViewHolder();
            holder.imageId = (ImageView) convertView.findViewById(R.id.imageView_id);
            holder.nameId = (TextView) convertView.findViewById(R.id.textView_name);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        //將資料(myItemList)從Map(item)中取出來
        Map<String, Object> item = myItemList.get(position);
        int number = (int) item.get("image");
        String data = (String) item.get("name");
        holder.imageId.setImageResource(number);
        holder.nameId.setText(data);

        return convertView;
    }
}
