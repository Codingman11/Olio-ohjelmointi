package com.example.viikko9;

import androidx.appcompat.app.AppCompatActivity;

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

    smartPost sPostEE = smartPost.getInstance();
    smartPost sPostFI = smartPost.getInstance();
    ArrayList<Posti> viroPostit = sPostEE.getPostiLista();
    ArrayList<Posti> suomiPostit = sPostFI.getPostiLista();
    ArrayList<String> countriesList;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        makeCountriesList();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);




    }

    public void makeCountriesList() {
        countriesList = new ArrayList<>();
        countriesList.add("Suomi");
        countriesList.add("Viro");
    }

    public void readXML(View v) {
        DocumentBuilder builder = null;

        try {
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            String viroURL = "http://iseteenindus.smartpost.ee/api/?request=destinations&country=EE&type=APT";
            String suomiURL = "http://iseteenindus.smartpost.ee/api/?request=destinations&country=FI&type=APT";
            Document doc1 = builder.parse(viroURL);
            Document doc2 = builder.parse(suomiURL);
            doc1.getDocumentElement().normalize();
            doc2.getDocumentElement().normalize();
            NodeList nListEE = doc1.getDocumentElement().getElementsByTagName("item");

            for (int i = 0; i < nListEE.getLength(); i++) {
                Node node = nListEE.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String id = element.getElementsByTagName("place_id").item(0).getTextContent();
                    String name = element.getElementsByTagName("name").item(0).getTextContent();
                    String city = element.getElementsByTagName("city").item(0).getTextContent();
                    String address = element.getElementsByTagName("address").item(0).getTextContent();
                    String avail = element.getElementsByTagName("availability").item(0).getTextContent();
                    sPostEE.addSmartP(id, name, city, address, avail);
                }
            }
            NodeList nListFI = doc2.getElementsByTagName("item");
            for (int i = 0; i < nListFI.getLength(); i++) {
                Node node = nListFI.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String id = element.getElementsByTagName("place_id").item(0).getTextContent();
                    String name = element.getElementsByTagName("name").item(0).getTextContent();
                    String city = element.getElementsByTagName("city").item(0).getTextContent();
                    String address = element.getElementsByTagName("address").item(0).getTextContent();
                    String avail = element.getElementsByTagName("availability").item(0).getTextContent();
                    sPostFI.addSmartP(id, name, city, address, avail);
                }
            }




        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } finally {
            spinnerCountries();
            spinnerPostFI();
        }

    }

    public void spinnerCountries() {
        Spinner spinner = findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, countriesList);
        spinner.setAdapter(adapter);
        spinner.setSelected(false);
        spinner.setSelection(0, false);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(parent.getSelectedItemPosition() == 0) {
                    spinnerPostFI();
                } else if (parent.getSelectedItemPosition() == 1) {
                    spinnerPostEE();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        } );
    }

    public void spinnerPostEE() {
        Spinner spinner = findViewById(R.id.spinner2);
        ArrayAdapter<Posti> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, viroPostit);
        spinner.setAdapter(adapter);
        spinner.setSelected(false);
        spinner.setSelection(0, true);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            TextView info = findViewById(R.id.postiTiedot);
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                info.setText("Nimi: " + viroPostit.get(position).getName() +
                            "\nKaupunki: " + viroPostit.get(position).getCity() +
                            "\nOsoite: " + viroPostit.get(position).getAddress() +
                            "\nAukioloajat: " + viroPostit.get(position).getAvail());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void spinnerPostFI() {
        Spinner spinner = findViewById(R.id.spinner2);
        ArrayAdapter<Posti> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, suomiPostit);
        spinner.setAdapter(adapter);
        spinner.setSelected(false);
        spinner.setSelection(0, true);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            TextView info = findViewById(R.id.postiTiedot);
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                info.setText("Nimi: " + suomiPostit.get(position).getName() +
                        "\nKaupunki: " + suomiPostit.get(position).getCity() +
                        "\nOsoite: " + suomiPostit.get(position).getAddress() +
                        "\nAukioloajat: " + suomiPostit.get(position).getAvail());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
