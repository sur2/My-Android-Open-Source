package com.pyong.mymessengertest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class ListViewItemAdapter extends BaseAdapter {

    ArrayList<MessengerItem> itemList = new ArrayList<>();
    Context context;

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        this.context = parent.getContext();
        MessengerItem item = this.itemList.get(position);
        
        // item.xml 을 ListView 에 들어갈 View 객체로 변환
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.messenger_listview_item, parent, false);
        }
        // item View 를 리턴
        return convertView;
    }

    public void addItem(MessengerItem item) {
        this.itemList.add(item);
    }
}
