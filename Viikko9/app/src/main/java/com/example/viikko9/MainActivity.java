package com.example.viikko9;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayout;
    private TextView textID;
    private Cinemas cinemas = Cinemas.getInstance();
    private ArrayList<Cinema> cinList = cinemas.getCinemas();
    private Spinner spinner;
    private ArrayList<String> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        readXML();
        spinnerChoice();
    }

    public void readXML() {
        DocumentBuilder builder = null;


        try {
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            String url = "https://www.finnkino.fi/xml/TheatreAreas/";
            Document doc = builder.parse(url);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getDocumentElement().getElementsByTagName("TheatreArea");

            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);
                if (node.getNodeType() == node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String id = element.getElementsByTagName("ID").item(0).getTextContent();
                    if (id.contains("1029") || id.contains("1014") || id.contains("1012") || id.contains("1002") || id.contains("1021")) {
                        continue;
                    }
                    String[] name = element.getElementsByTagName("Name").item(0).getTextContent().split(":");
                    String city = name[0];
                    String cinema = name[1];
                    cinemas.addList(id, city, cinema);
                }

            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void readSchedule(String thID) {

        movies = new ArrayList<>();

        DocumentBuilder builder = null;
        try {
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            String url = "https://www.finnkino.fi/xml/Schedule/?area=" + thID + "&dt=02.07.2020";

            Document doc = builder.parse(url);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getDocumentElement().getElementsByTagName("Show");

            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);
                if (node.getNodeType() == node.ELEMENT_NODE) {
                    Element element = (Element) node;


                    String title = element.getElementsByTagName("Title").item(0).getTextContent();



                    movies.add(title + "\n");


                }

            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            recyclerList();
        }
    }

    public void recyclerList() {
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new MainAdapter(movies);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        mRecyclerView.setAdapter(mAdapter);
    }


    public void spinnerChoice() {
        spinner = (Spinner) findViewById(R.id.spinner1);

        ArrayAdapter<Cinema> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, cinList);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        spinner.setSelected(false);
        spinner.setSelection(0, true);
        spinner.setAdapter(adapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String theatreID = cinList.get(position).getId();
                readSchedule(theatreID);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
