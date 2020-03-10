package com.example.viikko7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.InputStream;


public class MainActivity extends AppCompatActivity {

    private EditText textEdit;
    private TextView text_view;
    private Object InputStream;
    private EditText file;
    Context context = null;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
         context = MainActivity.this;
    }


    public void readFile(View v) {

        text_view = (TextView) findViewById(R.id.viewText);
        file = (EditText) findViewById(R.id.fileName);

        try {
            InputStream ins = context.openFileInput(file.getText().toString());
            BufferedReader br = new BufferedReader(new InputStreamReader(ins));

            String text = "", line = "";

            while ((line = br.readLine()) != null) {
                text += line;
            }
            text_view.setText(text);
            br.close();
        } catch (IOException e) {
            Log.e("IOException", "Virhe");
        } finally {
            System.out.println("Luettu");
        }
    }



    public void writeFile(View v) {

        textEdit = (EditText) findViewById(R.id.textEdit);
        file = (EditText) findViewById(R.id.fileName);
        String text = "";
        try {

            OutputStreamWriter ows = new OutputStreamWriter(context.openFileOutput(file.getText().toString(), Context.MODE_PRIVATE));

            text += textEdit.getText().toString();
            ows.write(text);
            System.out.println("Tiedoston nimi: " + file.getText().toString());

            ows.close();

        } catch (IOException e) {
            Log.e("IOException", "Virhe");
        } finally {
            System.out.println("Kirjoitettu");
        }

    }


}
