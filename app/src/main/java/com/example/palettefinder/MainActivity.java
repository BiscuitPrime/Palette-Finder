package com.example.palettefinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Quit Button --------------------------------------------------------
        Button quitButton = (Button) findViewById(R.id.quitButton);
        quitButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){finish();}
        });
        //Quit Button --------------------------------------------------------

        //Generate Button ----------------------------------------------------
        Button generateButton = (Button) findViewById(R.id.generateButton);
        generateButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent generateIntent = new Intent(MainActivity.this, GeneratePalette.class);
                MainActivity.this.startActivity(generateIntent);
            }
        });
        //Generate Button ----------------------------------------------------

        //SavedPalettesButton -----------------------------------------------------
        Button savedPalettesButton = (Button) findViewById(R.id.savedButton);
        savedPalettesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent savedIntent = new Intent(MainActivity.this, SavedPalettes.class);
                MainActivity.this.startActivity(savedIntent);
            }
        });
        //SavedPalettesButton -----------------------------------------------------
    }
}