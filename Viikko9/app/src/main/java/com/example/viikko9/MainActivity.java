package com.example.viikko9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity {

    smartPost sPostEE = smartPost.getInstance();
    smartPost sPostFI = smartPost.getInstance();
    ArrayList<Posti> viroLista = sPostEE.getPostiLista();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);




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
            NodeList nList = doc1.getDocumentElement().getElementsByTagName("item");
            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);
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
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } finally {
            spinnerPost();
        }

        /*for(int i = 0; i < sPostEE.getPostiLista().size(); i++ ) {

            System.out.println(sPostEE.getPostiLista().get(i).getName() );
        }

         */


    }

    public void spinnerPost() {
        Spinner spinner = findViewById(R.id.spinner);

        ArrayAdapter<Posti> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, sPostEE.getPostiLista());

        spinner.setAdapter(adapter);
        spinner.setSelected(false);
        spinner.setSelection(0, true);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            TextView info = findViewById(R.id.postiTiedot);
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                info.setText("Nimi: " + sPostEE.getPostiLista().get(position).getName() +
                            "\nKaupunki: " + sPostEE.getPostiLista().get(position).getCity() +
                            "\nOsoite: " + sPostEE.getPostiLista().get(position).getAddress() +
                            "\nAukioloajat: " + sPostEE.getPostiLista().get(position).getAvail());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}
