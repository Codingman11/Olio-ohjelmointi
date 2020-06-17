package com.example.viikko8;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {



    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayout;
    private BottleDispenser bD = BottleDispenser.getInstance();
    private ArrayList<Bottle> bottles = bD.getArray();
    private TextView seekBMon;
    private TextView bala;
    private TextView info;
    private SeekBar skbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBarSlide();
        spinnerChoice();
        recyclerList();
        bala = findViewById(R.id.balance);
        info = findViewById(R.id.textView2);
        bala.setText("Balance: " + bD.getMoney());
    }

    public void spinnerChoice() {

        Spinner spinner = (Spinner) findViewById(R.id.choice_spinner);

        ArrayAdapter adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, bottles);
        spinner.setAdapter(adapter);
        spinner.setSelected(false);
        spinner.setSelection(0, true);

        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {


                String text = parentView.getItemAtPosition(position).toString();

                if(!bD.buyBottle(position)) {
                    info.setText("Insufficient funds!");
                } else {

                    info.setText("Bought: " + text);
                    bottles.remove(position);
                    bala.setText("Balance: " + Double.toString((double) bD.getMoney()));
                    mAdapter.notifyItemRemoved(position);
                    mAdapter.notifyItemRangeChanged(position, bottles.size());
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
    }

    public void seekBarSlide() {
        skbar = (SeekBar) findViewById(R.id.seekBar1);
        seekBMon = (TextView) findViewById(R.id.seekBarMoney);


        skbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int pval = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                pval = progress;
                seekBMon.setText(Integer.toString(pval));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seekBMon.setText(Integer.toString(pval));

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
        Double money = Double.parseDouble((String) seekBMon.getText());
        bD.addMoney(money);
        skbar.setProgress(0);
        bala.setText("Balance: " + bD.getMoney());
    }

    public void returnM(View v) {
        info.setText(bD.returnMoney());
        bala.setText("Balance: " + 0);
    }


}