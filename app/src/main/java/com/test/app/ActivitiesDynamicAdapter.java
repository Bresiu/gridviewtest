package com.test.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.askerov.dynamicgid.BaseDynamicGridAdapter;

import java.util.List;

public class ActivitiesDynamicAdapter extends BaseDynamicGridAdapter {

    private Context context;

    public ActivitiesDynamicAdapter(Context context, List<?> items, int columnCount) {
        super(context, items, columnCount);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_cell, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.build(getItem(position).toString());

        holder.position = position;

        return convertView;
    }

    @Override
    public void remove(Object position) {
        super.remove(position);
    }

    @Override
    public Object getItem(int position) {
        return super.getItem(position);
    }

    private class ViewHolder {
        public int position;
        private TextView titleText;
        private ImageView image;

        private ViewHolder(View view) {
            titleText = (TextView) view.findViewById(R.id.activity_name);
            image = (ImageView) view.findViewById(R.id.activity_icon);
        }

        void build(String title) {
            titleText.setText(Activities.activitiesNameIntMap.get(title));
            Picasso.with(context).load(Activities.activitiesIconIntMap.get(title)).into(image);
        }
    }
}