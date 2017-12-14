package com.example.shivani.jsonvolleytest;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CustomListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private ArrayList<Content> items;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public CustomListAdapter(Activity activity, ArrayList<Content> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int location) {
        return items.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_items, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        
        NetworkImageView thumbNail = (NetworkImageView) convertView.findViewById(R.id.imageView);
        TextView title = (TextView) convertView.findViewById(R.id.textTitle);
        TextView date = (TextView) convertView.findViewById(R.id.textDate);
        TextView des = (TextView) convertView.findViewById(R.id.textDescription);
        TextView auth = (TextView) convertView.findViewById(R.id.textAuthor);
      //  TextView link = (TextView) convertView.findViewById(R.id.textLink);

        Content m =items.get(position);
        thumbNail.setImageUrl(m.getIcon(), imageLoader);
        title.setText(m.getTitle());
        des.setText(m.getDescription());
        auth.setText(m.getAuthor());
        date.setText(String.valueOf(m.getDate()));
       // link.setText(m.getLink());
        return convertView;
    }

}