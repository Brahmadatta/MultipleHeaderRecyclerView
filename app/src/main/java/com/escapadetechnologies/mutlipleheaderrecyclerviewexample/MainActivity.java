package com.escapadetechnologies.mutlipleheaderrecyclerviewexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;





    private RecyclerAdapter mRecyclerAdapter;


    private String description[] = {

            "Upload the iOS or Android application.",
            "Send the link to your testers, clients, friends or even use it yourself.",
            "Open the link in the browser on the device and click on install.",
            "It works for iOS and Android.",
            "Upload the iOS or Android application.",
            "Send the link to your testers, clients, friends or even use it yourself.",
            "Open the link in the browser on the device and click on install.",
            "It works for iOS and Android.",
            "Upload the iOS or Android application.",
            "Send the link to your testers, clients, friends or even use it yourself.",
            "Open the link in the browser on the device and click on install.",
            "It works for iOS and Android."
    };

    private String title[] = {

            "Development",
            "Production",
            "Testing",
            "Development",
            "Production",
            "Testing",
            "Development",
            "Production",
            "Testing",
            "Development",
            "Production",
            "Testing"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        ArrayList<RecyclerDataList> mRecyclerDataListFirst = firstRecyclerList();
        ArrayList<RecyclerDataList> mRecyclerDataListSecond = secondRecyclerList();

        mRecyclerAdapter = new RecyclerAdapter();
        mRecyclerAdapter.setFirstList(mRecyclerDataListFirst);
        mRecyclerAdapter.setSecondList(mRecyclerDataListSecond);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mRecyclerAdapter);

    }


    private ArrayList<RecyclerDataList> firstRecyclerList(){

        ArrayList<RecyclerDataList> recyclerDataLists = new ArrayList<>();
        for (int i = 0 ; i < description.length ; i++){

            RecyclerDataList recyclerDataList = new RecyclerDataList();
            recyclerDataList.setDescription(description[i]);
            recyclerDataList.setTitle(title[i]);
            recyclerDataLists.add(recyclerDataList);
        }
        return recyclerDataLists;
    }

    private ArrayList<RecyclerDataList> secondRecyclerList(){

        ArrayList<RecyclerDataList> recyclerDataLists = new ArrayList<>();
        for (int i = 0 ; i < description.length ; i++){

            RecyclerDataList recyclerDataList = new RecyclerDataList();
            recyclerDataList.setDescription(description[i]);
            recyclerDataList.setTitle(title[i]);
            recyclerDataLists.add(recyclerDataList);
        }
        return recyclerDataLists;
    }
}
