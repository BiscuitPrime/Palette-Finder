package com.example.palettefinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CustomPalette extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        String paletteValue = intent.getStringExtra("Saved Palette");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_palette);

        String[] values =paletteValue.toString().split("[ ,]+");
        Log.d("values ",values[0]);
        Log.d("values :",values[1]);
        Log.d("values :",values[2]);
        Log.d("values :",values[3]);

        TextView paletteName = (TextView) findViewById(R.id.paletteName);
        paletteName.setText(values[0].toString());
        TextView color1 = (TextView) findViewById(R.id.RGBval1);
        color1.setText(values[1]+","+values[2]+","+values[3]);
        TextView color2 = (TextView) findViewById(R.id.RGBval2);
        color2.setText(values[4]+","+values[5]+","+values[6]);
        TextView color3 = (TextView) findViewById(R.id.RGBval3);
        color3.setText(values[7]+","+values[8]+","+values[9]);
        TextView color4 = (TextView) findViewById(R.id.RGBval4);
        color4.setText(values[10]+","+values[11]+","+values[12]);
        TextView color5 = (TextView) findViewById(R.id.RGBval5);
        color5.setText(values[13]+","+values[14]+","+values[15]);

        //SavedPalettesButton -----------------------------------------------------
        Button savedPalettesButton = (Button) findViewById(R.id.savedButton);
        savedPalettesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent savedIntent = new Intent(CustomPalette.this, SavedPalettes.class);
                CustomPalette.this.startActivity(savedIntent);
            }
        });
        //SavedPalettesButton -----------------------------------------------------

        //Quit Button -------------------------------------------------------------
        Button quitButton = (Button) findViewById(R.id.quitButton);
        quitButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){finish();}
        });
        //Quit Button -------------------------------------------------------------

        //Home Button -------------------------------------------------------------
        Button homeButton = (Button) findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent homeIntent = new Intent(CustomPalette.this, MainActivity.class);
                CustomPalette.this.startActivity(homeIntent);
            }
        });
        //Home Button -------------------------------------------------------------

    }
}