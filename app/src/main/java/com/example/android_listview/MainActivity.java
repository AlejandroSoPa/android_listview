package com.example.android_listview;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    // Model: Record (intents=puntuació, nom)
    class Record implements Comparable<Record>{
        public int intents;
        public String nom;


        public Record(int _intents, String _nom ) {
            intents = _intents;
            nom = _nom;
        }

        @Override
        public int compareTo(Record o) {
            return o.intents - this.intents;
        }
    }
    // Model = Taula de records: utilitzem ArrayList
    ArrayList<Record> records;

    // ArrayList amb noms per a afegir quan es premi el botó
    ArrayList<String> noms;

    // ArrayAdapter serà l'intermediari amb la ListView
    ArrayAdapter<Record> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicialitzem model
        records = new ArrayList<Record>();

        records.add( new Record(33,"Manolo") );
        records.add( new Record(12,"Pepe") );
        records.add( new Record(42,"Laura") );

        noms = new ArrayList<String>();

        noms.add("Alejandro");
        noms.add("Erik");
        noms.add("Borja");
        noms.add("Eri");
        noms.add("Ale");
        noms.add("Bor");
        noms.add("Alejo");
        noms.add("Laura");
        noms.add("Maria");
        noms.add("Cristina");
        noms.add("Diana");
        noms.add("Jarvan IV");
        noms.add("Chogath");
        noms.add("Volibear");
        noms.add("Hecarim");
        noms.add("Kayn");
        noms.add("Miss Fortune");
        noms.add("Kaisa");
        noms.add("Irelia");
        noms.add("Mordekaiser");
        noms.add("Viego");
        noms.add("Gwen");


        // Inicialitzem l'ArrayAdapter amb el layout pertinent
        adapter = new ArrayAdapter<Record>( this, R.layout.list_item, records )
        {
            @Override
            public View getView(int pos, View convertView, ViewGroup container)
            {
                // getView ens construeix el layout i hi "pinta" els valors de l'element en la posició pos
                if( convertView==null ) {
                    // inicialitzem l'element la View amb el seu layout
                    convertView = getLayoutInflater().inflate(R.layout.list_item, container, false);
                }
                // "Pintem" valors (també quan es refresca)
                ((TextView) convertView.findViewById(R.id.nom)).setText(getItem(pos).nom);
                ((TextView) convertView.findViewById(R.id.intents)).setText(Integer.toString(getItem(pos).intents));
                return convertView;
            }

        };

        // busquem la ListView i li endollem el ArrayAdapter
        ListView lv = (ListView) findViewById(R.id.recordsView);
        lv.setAdapter(adapter);

        // botó per afegir entrades a la ListView
        Button afegir = (Button) findViewById(R.id.afegir);
        afegir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i=0;i<500;i++) {
                    int index = (int) (Math.random() * records.size());
                    records.add(new Record((int) (Math.random()*100), noms.get((int) (Math.random()*noms.size()))));
                }
                // notificar l'adapter dels canvis al model
                adapter.notifyDataSetChanged();
            }
        });

        Button ordenar = (Button) findViewById(R.id.ordenar);
        ordenar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i=0;i<500;i++) {
                    Collections.sort(records);
                }
                // notificar l'adapter dels canvis al model
                adapter.notifyDataSetChanged();
            }
        });
    }
}