package com.example.gridview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    //宣告變數
    private int[] imageNumber = {R.drawable.butterfly,R.drawable.cat,R.drawable.flower,
                                 R.drawable.hippo,R.drawable.monkey,R.drawable.mushroom,
                                 R.drawable.panda,R.drawable.rabbit,R.drawable.raccoon};

    private String[] imageName = {"butterfly","cat","flower","hippo","monkey","mushroom","panda","rabbit","raccoon"};
    private Context context;
    private GridView gridViewPic;
    private TextView textViewResult;
    private List<Map<String, Object>> itemList;


    //在onCreate()內呼叫方法
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        findViews();
        putDataIntoList();
        myGridViewAdaptor();
    }

    //找到元件
    private void findViews(){

        gridViewPic = (GridView) findViewById(R.id.gridView_pic);
        textViewResult = (TextView) findViewById(R.id.textView_result);
        textViewResult.setText("");
    }


    //使用Map打包:先將資料(image & name)放到itemData內
    //再將itemData放到itemList內
    private void putDataIntoList(){

        itemList = new ArrayList< Map<String, Object> >();
        for (int i = 0; i<imageNumber.length; i++){
            Map<String, Object> itemData = new HashMap<String, Object>();
            itemData.put("image", imageNumber[i]);
            itemData.put("name", imageName[i]);

            itemList.add(itemData);
        }
    }


    //將資料顯示在畫面上(activity_main.xml)
    /*
     1.先new出adaptor:line 76
     2.建立外部類別MyGridAdaptor
     3.接著邏輯會跑到MyGridAdaptor.java
     4.在外部類別MyGridAdaptor設定要將資料顯示在哪個layout，以及取出資料
     5.將adaptor放入gridView中:line78
     6.監聽girdView
     7.(不確定)item:將資料透過Position取出:line 84
     */
    private void myGridViewAdaptor(){

        MyGridAdaptor adaptor = new MyGridAdaptor(context, itemList);
        gridViewPic.setNumColumns(2);       //將gridView的column數量設為2行
        gridViewPic.setAdapter(adaptor);
        gridViewPic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, Object> item = (Map<String, Object>) parent.getItemAtPosition(position);
                textViewResult.setText("You select :\n");
                textViewResult.append(item.get("name").toString());
            }
        });

    }


}