package com.example.palettefinder;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class GetPaletteOnClickEvent implements View.OnClickListener {
    private GeneratePalette activity;

    public GetPaletteOnClickEvent(GeneratePalette mainActivity){
        this.activity=mainActivity;
    }

    @Override
    public void onClick(View v) {
        String url = new String("\"http://colormind.io/api/");
        //We collect the necessary data :
        EditText colorText1 = (EditText) activity.findViewById(R.id.RGBval1);
        String color1 = colorText1.getText().toString();
        EditText colorText2 = (EditText) activity.findViewById(R.id.RGBval2);
        String color2 = colorText2.getText().toString();
        EditText colorText3 = (EditText) activity.findViewById(R.id.RGBval3);
        String color3 = colorText3.getText().toString();
        EditText colorText4 = (EditText) activity.findViewById(R.id.RGBval4);
        String color4 = colorText4.getText().toString();
        EditText colorText5 = (EditText) activity.findViewById(R.id.RGBval5);
        String color5 = colorText5.getText().toString();

        //we create the list of the different colors :
        //NOTE : in future, check wether or not string or [int,int,int] value
        List dataToSend = new ArrayList();
        dataToSend.add(color1);
        dataToSend.add(color2);
        dataToSend.add(color3);
        dataToSend.add(color4);
        dataToSend.add(color5);

        AsyncColorMindJSONData task = new AsyncColorMindJSONData(activity, dataToSend);
        task.execute(url);
    }
}
