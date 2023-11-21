package com.example.myrecyclerview;

import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public static final String TAG = "로그";

    private TextView userNameTextView = null;
    private ImageView profileImageView = null;
    private MyRecyclerViewInterface myRecyclerViewInterface;

    public MyViewHolder(@NonNull View itemView, MyRecyclerViewInterface myRecyclerViewInterface) {
        super(itemView);
        Log.d(TAG, "MyViewHolder - Constructor called");
        userNameTextView = itemView.findViewById(R.id.user_name_txt);
        profileImageView = itemView.findViewById(R.id.profile_img);

        this.myRecyclerViewInterface = myRecyclerViewInterface;
        itemView.setOnClickListener(this);
    }

    // 데이터와 뷰를 묶음
    public void bindModelAndView(MyModel model) {
        Log.d(TAG, "MyViewHolder - bindModelAndView called");
        userNameTextView.setText(model.getName());

        // 이미지뷰 - 글라이드를 사용하여 이미지 로딩
        Glide
                .with(App.getInstance())
                .load(model.getProfileImage())
//             .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .into(profileImageView);
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "MyViewHolder - onClick() called");
        this.myRecyclerViewInterface.onItemClicked(getAbsoluteAdapterPosition());
    }
}
