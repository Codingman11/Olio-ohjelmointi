package com.example.viikko8;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {



    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayout;
    BottleDispenser bD = BottleDispenser.getInstance();
    ArrayList<Bottle> bottles = bD.getArray();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinnerChoice();
        recyclerList();

    }

    public void spinnerChoice() {
        Spinner spinner = (Spinner) findViewById(R.id.choice_spinner);

        ArrayAdapter adapter;
        adapter = new ArrayAdapter<Bottle>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item,bottles);
        spinner.setAdapter(adapter);
    }

    public void recyclerList() {

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayout = new LinearLayoutManager(this);
        mAdapter = new MainAdapter(bottles);
        mRecyclerView.setLayoutManager(mLayout);

    }
}
