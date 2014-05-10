package com.test.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {

    private static final int ITEM_VIEW_TYPE_ITEM = 0;
    private static final int ITEM_VIEW_TYPE_DIVIDER = 1;
    private static final int ITEM_VIEW_TYPE_COUNT = 2;

    private ArrayList arrayList;
    private LayoutInflater layoutInflater;

    public ListAdapter(Context context, ArrayList arrayList) {
        this.arrayList = arrayList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return ITEM_VIEW_TYPE_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        return (arrayList.get(position) instanceof Item) ?
                ITEM_VIEW_TYPE_ITEM : ITEM_VIEW_TYPE_DIVIDER;
    }

    @Override
    public boolean isEnabled(int position) {
        // A separator cannot be clicked !
        return getItemViewType(position) != ITEM_VIEW_TYPE_DIVIDER;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final int type = getItemViewType(position);

        if (convertView == null) {
            convertView = layoutInflater.inflate(
                    type == ITEM_VIEW_TYPE_DIVIDER ? R.layout.divider : R.layout.row, parent, false);
        }

        // We can now fill the list item view with the appropriate data.
        if (type == ITEM_VIEW_TYPE_DIVIDER) {
            final Divider divider = (Divider) getItem(position);
            ((TextView) convertView.findViewById(R.id.divider)).setText(divider.getNumber() + "");
        } else {
            final Item item = (Item) getItem(position);
            ((TextView) convertView.findViewById(R.id.name)).setText(item.getName());
            ((TextView) convertView.findViewById(R.id.surname)).setText(item.getSurname());
        }

        return convertView;
    }

}
