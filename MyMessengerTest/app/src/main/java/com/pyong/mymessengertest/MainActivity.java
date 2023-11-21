package com.pyong.mymessengertest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView messengerListView;
    ListViewItemAdapter listViewItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        messengerListView = findViewById(R.id.messenger_list_view);
        listViewItemAdapter = new ListViewItemAdapter();
        listViewItemAdapter.addItem(new MessengerItem());
        messengerListView.setAdapter(listViewItemAdapter);
    }
}