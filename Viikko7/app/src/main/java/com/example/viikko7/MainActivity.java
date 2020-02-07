package com.example.viikko7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);


    }

    public void changeText(View v) {

        TextView text = (TextView) findViewById(R.id.textView);
        EditText textEdit = (EditText) findViewById(R.id.editText2);
        text.setText(textEdit.getText());

    }

}
