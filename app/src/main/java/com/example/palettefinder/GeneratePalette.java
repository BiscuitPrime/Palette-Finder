package com.example.palettefinder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GeneratePalette extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_palette);

        //Quit Button --------------------------------------------------------
        Button quitButton = (Button) findViewById(R.id.quitButton);
        quitButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){finish();}
        });
        //Quit Button --------------------------------------------------------
    }
}