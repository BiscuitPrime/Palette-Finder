package com.example.palettefinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomPalette extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        String paletteValue = intent.getStringExtra("Saved Palette");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_palette);

        //We display the correct informations regarding the RGB colors:
        String[] values =paletteValue.toString().split("[ ,]+");
        TextView paletteName = (TextView) findViewById(R.id.paletteName);
        paletteName.setText(values[0].toString());
        TextView Textcolor1 = (TextView) findViewById(R.id.RGBval1);
        Textcolor1.setText(values[1]+","+values[2]+","+values[3]);
        TextView Textcolor2 = (TextView) findViewById(R.id.RGBval2);
        Textcolor2.setText(values[4]+","+values[5]+","+values[6]);
        TextView Textcolor3 = (TextView) findViewById(R.id.RGBval3);
        Textcolor3.setText(values[7]+","+values[8]+","+values[9]);
        TextView Textcolor4 = (TextView) findViewById(R.id.RGBval4);
        Textcolor4.setText(values[10]+","+values[11]+","+values[12]);
        TextView Textcolor5 = (TextView) findViewById(R.id.RGBval5);
        Textcolor5.setText(values[13]+","+values[14]+","+values[15]);

        //We change the colors of the views :
        View Color1 = (View) findViewById(R.id.colorView1);
        View Color2 = (View) findViewById(R.id.colorView2);
        View Color3 = (View) findViewById(R.id.colorView3);
        View Color4 = (View) findViewById(R.id.colorView4);
        View Color5 = (View) findViewById(R.id.colorView5);
        List color1 = obtainRGB(Textcolor1.getText().toString());
        List color2 = obtainRGB(Textcolor2.getText().toString());
        List color3 = obtainRGB(Textcolor3.getText().toString());
        List color4 = obtainRGB(Textcolor4.getText().toString());
        List color5 = obtainRGB(Textcolor5.getText().toString());
        Color1.setBackgroundColor(Color.rgb(Integer.parseInt(color1.get(0).toString()),Integer.parseInt(color1.get(1).toString()),Integer.parseInt(color1.get(2).toString())));
        Color2.setBackgroundColor(Color.rgb(Integer.parseInt(color2.get(0).toString()),Integer.parseInt(color2.get(1).toString()),Integer.parseInt(color2.get(2).toString())));
        Color3.setBackgroundColor(Color.rgb(Integer.parseInt(color3.get(0).toString()),Integer.parseInt(color3.get(1).toString()),Integer.parseInt(color3.get(2).toString())));
        Color4.setBackgroundColor(Color.rgb(Integer.parseInt(color4.get(0).toString()),Integer.parseInt(color4.get(1).toString()),Integer.parseInt(color4.get(2).toString())));
        Color5.setBackgroundColor(Color.rgb(Integer.parseInt(color5.get(0).toString()),Integer.parseInt(color5.get(1).toString()),Integer.parseInt(color5.get(2).toString())));

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

    private List obtainRGB(String rgb) //We transform the strings in usable lists
    {
        List new_list = new ArrayList();
        String[] values =rgb.toString().split("[ ,]+");
        StringBuilder sb = new StringBuilder(values[0]);
        sb.deleteCharAt(0);
        new_list.add(Integer.parseInt(sb.toString()));
        new_list.add(Integer.parseInt(values[1]));
        StringBuilder sb2 = new StringBuilder(values[2]);
        sb2.deleteCharAt(sb2.length()-1);
        new_list.add(Integer.parseInt(sb2.toString()));
        return new_list;
    }
}