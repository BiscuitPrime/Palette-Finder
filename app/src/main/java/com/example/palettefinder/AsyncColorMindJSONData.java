package com.example.palettefinder;

import android.graphics.Color;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

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
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("model", "default");
        } catch (JSONException e) {
            e.printStackTrace();
        }

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
                                         EditText textRGBVal1 = (EditText) generateActivity.findViewById(R.id.RGBval1);
                                         EditText textRGBVal2 = (EditText) generateActivity.findViewById(R.id.RGBval2);
                                         EditText textRGBVal3 = (EditText) generateActivity.findViewById(R.id.RGBval3);
                                         EditText textRGBVal4 = (EditText) generateActivity.findViewById(R.id.RGBval4);
                                         EditText textRGBVal5 = (EditText) generateActivity.findViewById(R.id.RGBval5);
                                         ImageView colView1 = (ImageView) generateActivity.findViewById(R.id.colorView1);
                                         try {
                                             textRGBVal1.setText(jsonresult.getJSONArray("result").get(0).toString());

                                             StringBuilder test = new StringBuilder(jsonresult.getJSONArray("result").get(0).toString());
                                             test.deleteCharAt(0);
                                             test.deleteCharAt(test.length()-1);
                                             String[] values1 =test.toString().split("[ ,]+");
                                             colView1.setBackgroundColor(Color.rgb(Integer.parseInt(values1[0]),Integer.parseInt(values1[1]),Integer.parseInt(values1[2])));

                                             textRGBVal2.setText(jsonresult.getJSONArray("result").get(1).toString());
                                             textRGBVal3.setText(jsonresult.getJSONArray("result").get(2).toString());
                                             textRGBVal4.setText(jsonresult.getJSONArray("result").get(3).toString());
                                             textRGBVal5.setText(jsonresult.getJSONArray("result").get(4).toString());
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
}
