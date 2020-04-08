package com.example.viikko8;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

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
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinnerChoice();
        recyclerList();
        TextView text = findViewById(R.id.textView1);
        Button bt =  findViewById(R.id.addMoney);
        text.setText("Money: " + bD.getMoney() + "€");
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
        mRecyclerView.setAdapter(mAdapter);
    }


}