package com.test.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import org.askerov.dynamicgid.DynamicGridView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends Activity {
    private DynamicGridView gridView;
    ActivitiesDynamicAdapter activitiesDynamicAdapter;
    ArrayList<String> items = new ArrayList<String>(Arrays.asList(Activities.activitiesApiStrings));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = (DynamicGridView) findViewById(R.id.dynamic_grid);
        activitiesDynamicAdapter = new ActivitiesDynamicAdapter(this, items, 3);

        gridView.setWobbleInEditMode(false);
        gridView.setAdapter(activitiesDynamicAdapter);
        gridView.setOnDropListener(new DynamicGridView.OnDropListener() {
            @Override
            public void onActionDrop() {
                gridView.stopEditMode();
            }
        });
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                gridView.startEditMode();
                return false;
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, parent.getAdapter().getItem(position).toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (gridView.isEditMode()) {
            gridView.stopEditMode();
        } else {
            super.onBackPressed();
        }
    }

}
