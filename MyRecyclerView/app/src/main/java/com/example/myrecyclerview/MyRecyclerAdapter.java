package com.example.myrecyclerview;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyViewHolder>{

    private MyRecyclerViewInterface myRecyclerViewInterface = null;

    public MyRecyclerAdapter(MyRecyclerViewInterface myRecyclerViewInterface) {
        this.myRecyclerViewInterface = myRecyclerViewInterface;
    }

    private static final String TAG = "로그";
    private ArrayList<MyModel> modelList = new ArrayList<MyModel>();

    // 뷰홀더가 생성될 때
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recycler_item, parent, false),
                myRecyclerViewInterface);
    }

    // 뷰와 뷰홀더가 묶였을 때(포지션 만큼)
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Log.d(TAG, "MyRecyclerAdapter - onBindViewHolder() called / position: " + position);
        holder.bindModelAndView(modelList.get(position));
    }

    // item 이 그려질 개수
    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public void setModelList(ArrayList<MyModel> list) {
        modelList = list;
    }


}
