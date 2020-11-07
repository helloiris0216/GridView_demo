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


//在外部類別MyGridAdaptor設定要將資料顯示在哪個layout，以及取出資料
public class MyGridAdaptor extends BaseAdapter {
    private final LayoutInflater myLayoutInflater;
    private final List<Map<String, Object>> myItemList;


    //建構子:傳入Context, Map, itemList
    //Context:是哪個activity
    //myItemList = itemList:內有image, name
    public MyGridAdaptor(Context context, List<Map<String, Object>> itemList) {
        myLayoutInflater = LayoutInflater.from(context);
        myItemList = itemList;
    }


    //getCount():有多少欄位
    @Override
    public int getCount() {
        return myItemList.size();
    }

    //getItem():透過取得item的Position來取得item
    //Position:myItemList的元素位置=0,1,2,3,4,5,6,7,8
    //每個Position內含有貼兩種標籤的元素各一個:image & name
    @Override
    public Object getItem(int position) {
        return myItemList.get(position);
    }

    //getItemId():透過取得Position來取得item的id
    @Override
    public long getItemId(int position) {
        return position;
    }

    //建立內部類別 ViewHolder:為了存取資料
    //內有兩個屬性:imageId & nameId
    private class ViewHolder{
        ImageView imageId;
        TextView nameId;
    }

    //為了將取出的資料顯示在item_layout中
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        //if..else:存id用
        /*
		 1.如果convertView是空的:
         2.就顯示item_layout
         3.建立ViewHolder holder
         4.找到在item_layout中的imageView_id，並存到imageId中
         5.找到在item_layout中的textView_name，並存到imageId中
         6.將holder放入convertView內
		*/
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
        //因為每個Position內的貼有標籤(image & name)的元素只有各一個，所以不需要用到陣列來取出
        Map<String, Object> item = myItemList.get(position);
        int number = (int) item.get("image");
        String data = (String) item.get("name");
        holder.imageId.setImageResource(number);
        holder.nameId.setText(data);

        return convertView;
    }
}
