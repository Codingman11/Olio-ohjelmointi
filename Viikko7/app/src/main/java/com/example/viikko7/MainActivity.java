package com.example.viikko7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText textEdit;
    private TextView text_view;
    private TextWatcher text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
         changeText();

    }

    public void changeText() {

        textEdit = (EditText) findViewById(R.id.textEdit);
        text_view = (TextView) findViewById(R.id.textView);


        text = new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                text_view.setText(textEdit.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };

        textEdit.addTextChangedListener(text);


    }

}
