package com.example.palettefinder;

import android.graphics.Color;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AsyncColorMindJSONData extends AsyncTask<String, Void, String> {
    private GeneratePalette generateActivity;
    private List dataToSend;

    public AsyncColorMindJSONData(GeneratePalette generateActivity, List dataToSend){this.generateActivity=generateActivity; this.dataToSend=dataToSend;}

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    JsonReader jsonReader;
    String value;
    JSONObject jsonresult;

    @Override
    protected String doInBackground(String... strings) {

        //We select the RGB colors text editors
        EditText textRGBVal1 = (EditText) generateActivity.findViewById(R.id.RGBval1);
        EditText textRGBVal2 = (EditText) generateActivity.findViewById(R.id.RGBval2);
        EditText textRGBVal3 = (EditText) generateActivity.findViewById(R.id.RGBval3);
        EditText textRGBVal4 = (EditText) generateActivity.findViewById(R.id.RGBval4);
        EditText textRGBVal5 = (EditText) generateActivity.findViewById(R.id.RGBval5);
        View Color1 = (View) generateActivity.findViewById(R.id.colorView1);
        View Color2 = (View) generateActivity.findViewById(R.id.colorView2);
        View Color3 = (View) generateActivity.findViewById(R.id.colorView3);
        View Color4 = (View) generateActivity.findViewById(R.id.colorView4);
        View Color5 = (View) generateActivity.findViewById(R.id.colorView5);

        //We create a jsonObject that will be sent to the API, to get the relevant data
        JsonObject jsonObject = new JsonObject();
        List dataToSend= new ArrayList();
        //Since we must send a very precise data in terms of their type, we use the transform function for each EditText, as to get the corresponding data :
        //String if N, an array list otherwise
        dataToSend.add(transform(textRGBVal1));
        dataToSend.add(transform(textRGBVal2));
        dataToSend.add(transform(textRGBVal3));
        dataToSend.add(transform(textRGBVal4));
        dataToSend.add(transform(textRGBVal5));
        JsonArray dataToSendJSON = new Gson().toJsonTree(dataToSend).getAsJsonArray(); //the sent data is an array
        String defaut = new Gson().toJson("default");
        jsonObject.add("input", dataToSendJSON);
        jsonObject.addProperty("model","default");

        AndroidNetworking.post("http://colormind.io/api/")
                .addByteBody(jsonObject.toString().getBytes())
                //.addBodyParameter("model", "default")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsString(new StringRequestListener() {
                                 @Override
                                 public void onResponse(String response) {
                                     if (response != null) {
                                         Log.d("VOILA", response);
                                         value=response;
                                         try {
                                             jsonresult = new JSONObject(response);
                                         } catch (JSONException e) {
                                             e.printStackTrace();
                                         }
                                         try {
                                             //We display the acquired data in the edit text :
                                             textRGBVal1.setText(jsonresult.getJSONArray("result").get(0).toString());
                                             textRGBVal2.setText(jsonresult.getJSONArray("result").get(1).toString());
                                             textRGBVal3.setText(jsonresult.getJSONArray("result").get(2).toString());
                                             textRGBVal4.setText(jsonresult.getJSONArray("result").get(3).toString());
                                             textRGBVal5.setText(jsonresult.getJSONArray("result").get(4).toString());
                                             //we display the colors in the views :
                                             List color1 = obtainRGB(jsonresult.getJSONArray("result").get(0).toString());
                                             List color2 = obtainRGB(jsonresult.getJSONArray("result").get(1).toString());
                                             List color3 = obtainRGB(jsonresult.getJSONArray("result").get(2).toString());
                                             List color4 = obtainRGB(jsonresult.getJSONArray("result").get(3).toString());
                                             List color5 = obtainRGB(jsonresult.getJSONArray("result").get(4).toString());
                                             Color1.setBackgroundColor(Color.rgb(Integer.parseInt(color1.get(0).toString()),Integer.parseInt(color1.get(1).toString()),Integer.parseInt(color1.get(2).toString())));
                                             Color2.setBackgroundColor(Color.rgb(Integer.parseInt(color2.get(0).toString()),Integer.parseInt(color2.get(1).toString()),Integer.parseInt(color2.get(2).toString())));
                                             Color3.setBackgroundColor(Color.rgb(Integer.parseInt(color3.get(0).toString()),Integer.parseInt(color3.get(1).toString()),Integer.parseInt(color3.get(2).toString())));
                                             Color4.setBackgroundColor(Color.rgb(Integer.parseInt(color4.get(0).toString()),Integer.parseInt(color4.get(1).toString()),Integer.parseInt(color4.get(2).toString())));
                                             Color5.setBackgroundColor(Color.rgb(Integer.parseInt(color5.get(0).toString()),Integer.parseInt(color5.get(1).toString()),Integer.parseInt(color5.get(2).toString())));
                                         } catch (JSONException e) {
                                             e.printStackTrace();
                                         }
                                     }
                                     else {
                                         Log.d("VOILA3", "RIEN");
                                     }
                                 }

                                 @Override
                                 public void onError(ANError error) {
                                     Log.d("VOILA2", error.toString());
                                 }
                             }
                );

        return value;
    }

    @Override
    protected void onPostExecute(String result) {
        Log.i("JFL", "Response from the server: " +result+value);
    }

    private Object transform(EditText textRGBVal){
        String rgbval = textRGBVal.getText().toString();
        String test = "N";
        if(rgbval.equals(test))
        {
            return rgbval;
        }
        else
        {
            //if the editext contains anything other than N, we must transform it into a list
            List new_list = new ArrayList();
            String[] values =rgbval.toString().split("[ ,]+");
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
