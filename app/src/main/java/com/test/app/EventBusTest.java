package com.test.app;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.squareup.otto.Subscribe;

public class EventBusTest extends Fragment implements View.OnClickListener {

    private Context context;
    private TextView result;
    private Button refresh;
    private boolean isServiceRunning = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Override
    public void onResume() {
        super.onResume();
        BusProvider.getInstance().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (isServiceRunning) {
            stopService();
        }
        BusProvider.getInstance().unregister(this);
    }

    private void stopService() {
        context.stopService(new Intent(context, DummyDownloadService.class));
        isServiceRunning = false;
    }

    private void startService() {
        context.startService(new Intent(context, DummyDownloadService.class));
        isServiceRunning = true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.bus, container, false);
        initViews(rootView);
        return rootView;
    }

    private void initViews(View rootView) {
        result = (TextView) rootView.findViewById(R.id.result);
        refresh = (Button) rootView.findViewById(R.id.refresh);
        refresh.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.refresh:
                if (isServiceRunning) {
                    stopService();
                } else {
                    startService();
                }
        }
    }

    @Subscribe
    public void output(String wiki) {
        result.setText(wiki);
    }
}
