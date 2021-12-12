package com.example.palettefinder;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class SavePaletteOnClickEvent implements View.OnClickListener {
    private GeneratePalette generateActivity;

    public SavePaletteOnClickEvent(GeneratePalette generatePalette) {this.generateActivity=generatePalette;
    }

    @Override
    public void onClick(View v) {
        //We collect the data on all the colors and name of the given palette :
        EditText textRGBVal1 = (EditText) generateActivity.findViewById(R.id.RGBval1);
        EditText textRGBVal2 = (EditText) generateActivity.findViewById(R.id.RGBval2);
        EditText textRGBVal3 = (EditText) generateActivity.findViewById(R.id.RGBval3);
        EditText textRGBVal4 = (EditText) generateActivity.findViewById(R.id.RGBval4);
        EditText textRGBVal5 = (EditText) generateActivity.findViewById(R.id.RGBval5);
        EditText nameText = (EditText) generateActivity.findViewById(R.id.paletteName);

        String color1 = textRGBVal1.getText().toString();
        String color2 = textRGBVal2.getText().toString();
        String color3 = textRGBVal3.getText().toString();
        String color4 = textRGBVal4.getText().toString();
        String color5 = textRGBVal5.getText().toString();
        String paletteName = nameText.getText().toString();

        //Now that we have the data, we're putting it inside a database :
        DatabaseHelper myDataBase = new DatabaseHelper(generateActivity);

        ContentValues values = new ContentValues();
        values.put(DatabaseContract.DataEntry.COLUMN_NAME_NAME, paletteName);
        values.put(DatabaseContract.DataEntry.COLUMN_NAME_COLOR1, color1);
        values.put(DatabaseContract.DataEntry.COLUMN_NAME_COLOR2, color2);
        values.put(DatabaseContract.DataEntry.COLUMN_NAME_COLOR3, color3);
        values.put(DatabaseContract.DataEntry.COLUMN_NAME_COLOR4, color4);
        values.put(DatabaseContract.DataEntry.COLUMN_NAME_COLOR5, color5);

        myDataBase.insertData(values);
        Log.d("Db","Data saved !");
    }
}
