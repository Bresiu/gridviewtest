package com.test.app;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;

public class PlaceholderFragment extends Fragment {

    private ArrayList<Object> items;
    private Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.pull_to_refresh_list, container, false);
        initView(rootView);
        return rootView;
    }

    private void initView(View rootView) {
        items = Array.getItems();
        ListAdapter listAdapter = new ListAdapter(context, items);
        PullToRefreshListView pullToRefreshListView = (PullToRefreshListView) rootView.findViewById(R.id.pull_to_refresh_list);
        pullToRefreshListView.setAdapter(listAdapter);
    }
}