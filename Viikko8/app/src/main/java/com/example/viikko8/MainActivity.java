package com.example.viikko8;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {



    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayout;
    BottleDispenser bD = BottleDispenser.getInstance();
    ArrayList<Bottle> bottles = bD.getArray();
    TextView bala;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinnerChoice();
        recyclerList();
        bala = findViewById(R.id.balance);




    }

    public void spinnerChoice() {

        Spinner spinner = (Spinner) findViewById(R.id.choice_spinner);

        ArrayAdapter adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, bottles);;
        spinner.setAdapter(adapter);
        spinner.setSelected(false);
        spinner.setSelection(0, true);

        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                TextView botl = findViewById(R.id.textView2);
                String text = parentView.getItemAtPosition(position).toString();

                botl.setText(text);
                bottles.remove(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });

    }

    public void recyclerList() {

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new MainAdapter(bottles);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        mRecyclerView.setAdapter(mAdapter);

    }

    public void addM(View v) {
        bD.addMoney();
        bala.setText("balance: " + bD.getMoney());
    }

    public void returnM(View v) {

    }
}
