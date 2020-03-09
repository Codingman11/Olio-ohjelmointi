package com.example.viikko8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottleDispenser boDis = BottleDispenser.getInstance();
        Button addM = (Button) findViewById(R.id.addMoney);
        initializeUI(boDis, addM);



    }

    public void initializeUI(BottleDispenser boDis, Button addM) {

        TextView text_view = (TextView) findViewById(R.id.textView);
        text_view.setText(Double.toString(boDis.getMoney()));



        Spinner spinner = findViewById(R.id.spinner1);

    }



}
