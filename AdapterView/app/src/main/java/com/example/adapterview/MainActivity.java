package com.example.adapterview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ListView mainList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainList = findViewById(R.id.main_list);

        ArrayList<HashMap<String, String>> datas = new ArrayList<>();
        HashMap<String, String> map = new HashMap<>();
        map.put("name", "류현진");
        map.put("content", "화이팅 해라");
        datas.add(map);

        map = new HashMap<>();
        map.put("name", "오승환");
        map.put("content", "역시 돌직구 장난 아니구만...");
        datas.add(map);

        SimpleAdapter adapter = new SimpleAdapter(
                this,
                datas,
                android.R.layout.simple_list_item_2,
                new String[]{"name", "content"},
                new int[]{android.R.id.text1, android.R.id.text2}
        );
        mainList.setAdapter(adapter);
    }
}