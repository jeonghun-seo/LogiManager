package com.logimanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends BaseAdapter {
    private Context context;
    private List<String> itemList;
    private List<Integer> itemCountList;
    private List<Integer> imageList;

    public CustomAdapter(Context context, List<String> itemList, List<Integer> itemCountList, List<Integer> imageList) {
        this.context = context;
        this.itemList = itemList;
        this.itemCountList = itemCountList;
        this.imageList = imageList;
    }

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
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.list_view, parent, false);

        TextView itemText = itemView.findViewById(R.id.item_text);
        TextView countText = itemView.findViewById(R.id.count_text);
        ImageView imageView = itemView.findViewById(R.id.image_view);

        itemText.setText(itemList.get(position));
        countText.setText("Count: " + itemCountList.get(position));
        imageView.setImageResource(imageList.get(position));

        return itemView;
    }
}
