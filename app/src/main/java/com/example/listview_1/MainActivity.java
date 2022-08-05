package com.example.listview_1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerCity;
    private ListView listViewFood;
    private ArrayAdapter<CharSequence> spinnerAdapter;
    private String[] food;
    private ArrayAdapter<String> listViewAdapter;
    private String cityName;
    private String foodName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerCity = (Spinner)findViewById(R.id.spinner_id);
        listViewFood = (ListView)findViewById(R.id.listView_id);
//      [Spinner set layout]---------------------------------------
//      1. Adapter : provide Spinner two view
//      (1) before press spinner (2)after press spinner
//        1.1 call default layout
//        spinnerAdapter = ArrayAdapter.createFromResource(MainActivity.this,R.array.city,
//                android.R.layout.simple_spinner_item);
//        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerCity.setAdapter(spinnerAdapter);
//        1.2 call self-defined layout :
//        layout/simple_spinner_item.xml
//        layout/simple_spinner_dropdown_item.xml
        spinnerAdapter = ArrayAdapter.createFromResource(MainActivity.this,R.array.city,
                R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        spinnerCity.setAdapter(spinnerAdapter);

//      [ListView set layout]------------------------------------------
//      1. Adapter : provide ListView two view
//        method from class
//        food = getResources().getStringArray(R.array.food);
//        1.1 call default layout
//        listViewAdapter = new ArrayAdapter<String>(MainActivity.this,
//                android.R.layout.simple_expandable_list_item_1,food);
//        1.2 call self-defined layout :
//        listViewAdapter = new ArrayAdapter<String>(MainActivity.this,
//                R.layout.simple_expandable_list_item_1,food);
//        listViewFood.setAdapter(listViewAdapter);
//        method from handbook
//        2.1 call default layout
//        ArrayAdapter<CharSequence> blistViewAdapter = ArrayAdapter.createFromResource(MainActivity.this, R.array.food,
//                android.R.layout.simple_list_item_1);
//        2.2 call self-defined layout :
          ArrayAdapter<CharSequence> blistViewAdapter = ArrayAdapter.createFromResource(MainActivity.this, R.array.food,
                R.layout.simple_list_item_1);
          listViewFood.setAdapter(blistViewAdapter);
//      [Spinner Listener]---------------------------------------
        spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cityName= (String) parent.getItemAtPosition(position);
                Log.d("main","cityNAme="+cityName);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        listViewFood.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                foodName=parent.getItemAtPosition(position).toString();
                Log.d("main","foodNAme="+foodName);
                Log.d("main","position="+position);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Select food");
                builder.setMessage(cityName+"\n"+foodName);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
                        intent.putExtra("city",cityName);
                        intent.putExtra("food",foodName);
                        intent.putExtra("index",position);
                        startActivity(intent);

                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });


                builder.show();
            }
        });


    }
}