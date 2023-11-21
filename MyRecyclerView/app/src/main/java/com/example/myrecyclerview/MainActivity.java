package com.example.myrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MyRecyclerViewInterface{

    public static final String TAG = "로그";
    private ArrayList<MyModel> modelList = new ArrayList<MyModel>();
    private MyRecyclerAdapter myRecyclerAdapter = null;
    private RecyclerView recyclerView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "MainActivity - onCreate() called");

        for (int i = 0; i < 20; i++) {
            MyModel myModel = new MyModel("찜닭" + i, "https://avatars.githubusercontent.com/u/46298060?v=4");
            modelList.add(myModel);
        }
        Log.d(TAG, "MainActivity - model size: " + modelList.size());

        recyclerView = findViewById(R.id.my_recycler_view);

        // 어댑터 인스턴스 생성
        myRecyclerAdapter = new MyRecyclerAdapter(this);
        myRecyclerAdapter.setModelList(modelList);
        // 리사이클러 뷰 설정
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(myRecyclerAdapter);

    }

    @Override
    public void onItemClicked(int position) {
        Toast.makeText(this, "클릭! " + modelList.get(position).getName(), Toast.LENGTH_SHORT).show();
    }
}