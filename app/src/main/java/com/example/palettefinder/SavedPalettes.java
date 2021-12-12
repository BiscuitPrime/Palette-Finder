package com.example.palettefinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class SavedPalettes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_palettes);

        //Quit Button -------------------------------------------------------------
        Button quitButton = (Button) findViewById(R.id.quitButton);
        quitButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){finish();}
        });
        //Quit Button -------------------------------------------------------------

        //Generate Button ----------------------------------------------------
        Button generateButton = (Button) findViewById(R.id.generateButton);
        generateButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent generateIntent = new Intent(SavedPalettes.this, GeneratePalette.class);
                SavedPalettes.this.startActivity(generateIntent);
            }
        });
        //Generate Button ----------------------------------------------------

        //Home Button -------------------------------------------------------------
        Button homeButton = (Button) findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent homeIntent = new Intent(SavedPalettes.this, MainActivity.class);
                SavedPalettes.this.startActivity(homeIntent);
            }
        });
        //Home Button -------------------------------------------------------------

        //Displaying the list :
        ListView list = (ListView) findViewById(R.id.listSavedPalettes);
        //list.setAdapter(new CustomAdapter(context));
        ArrayAdapter<String> tableau = new ArrayAdapter<String>(list.getContext(), R.layout.liste);

        DatabaseHelper mydb = new DatabaseHelper(this);
        mydb.readData();
        mydb.printData(tableau);
        list.setAdapter(tableau);

        //Interaction with the liste :
        //When an item is clicked, the list displays the custom palette activity
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent customPaletteIntent = new Intent(SavedPalettes.this, CustomPalette.class);
                customPaletteIntent.putExtra("Saved Palette",tableau.getItem(position).toString());
                SavedPalettes.this.startActivity(customPaletteIntent);
            }
        });
    }
}