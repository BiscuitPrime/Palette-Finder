package com.example.palettefinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;

public class GeneratePalette extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_palette);

        //Quit Button -------------------------------------------------------------
        Button quitButton = (Button) findViewById(R.id.quitButton);
        quitButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){finish();}
        });
        //Quit Button -------------------------------------------------------------

        //Launch search Button ----------------------------------------------------
        Button launchButton = (Button) findViewById(R.id.launchSearchButton);
        launchButton.setOnClickListener(new GetPaletteOnClickEvent(this));
        //Launch search Button ----------------------------------------------------

        //Save Button -------------------------------------------------------------
        Button saveButton = (Button) findViewById(R.id.buttonSave);
        saveButton.setOnClickListener(new SavePaletteOnClickEvent(this));
        //Save Button -------------------------------------------------------------

        //SavedPalettesButton -----------------------------------------------------
        Button savedPalettesButton = (Button) findViewById(R.id.savedButton);
        savedPalettesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent savedIntent = new Intent(GeneratePalette.this, SavedPalettes.class);
                GeneratePalette.this.startActivity(savedIntent);
            }
        });
        //SavedPalettesButton -----------------------------------------------------
    }
}