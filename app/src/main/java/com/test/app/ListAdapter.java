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
    private Context context;

    public ListAdapter(Context context, ArrayList arrayList) {
        this.context = context;
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
        //ItemViewHolder itemViewHolder = null;
        //DividerViewHolder dividerViewHolder = null;

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



        /*
        // First, let's create a new convertView if needed. You can also
        // create a ViewHolder to speed up changes if you want ;)
        if (convertView == null) {
            if (type == ITEM_VIEW_TYPE_DIVIDER) {
                convertView = layoutInflater.inflate(R.layout.divider, parent, false);
                dividerViewHolder = new DividerViewHolder();
                dividerViewHolder.divider = (TextView) convertView.findViewById(R.id.divider);
                convertView.setTag(dividerViewHolder);
            } else {
                convertView = layoutInflater.inflate(R.layout.row, parent, false);
                itemViewHolder = new ItemViewHolder();
                itemViewHolder.name = (TextView) convertView.findViewById(R.id.name);
                itemViewHolder.surname = (TextView) convertView.findViewById(R.id.surname);
                convertView.setTag(itemViewHolder);
            }
        } else {
            if (type == ITEM_VIEW_TYPE_DIVIDER) {
                dividerViewHolder = (DividerViewHolder) convertView.getTag();
            } else {
                itemViewHolder = (ItemViewHolder) convertView.getTag();
            }
        }

        // We can now fill the list item view with the appropriate data.
        if (type == ITEM_VIEW_TYPE_DIVIDER) {
            final Divider divider = (Divider) getItem(position);
            dividerViewHolder.divider.setText(divider.getNumber());
        } else {
            final Item item = (Item) getItem(position);
            itemViewHolder.name.setText(item.getName());
            itemViewHolder.surname.setText(item.getSurname());
        }
        */

        // return convertView;
    }

    /*
    private class ItemViewHolder {
        public TextView name;
        public TextView surname;
    }

    private class DividerViewHolder {
        public TextView divider;
    }
    */

}
