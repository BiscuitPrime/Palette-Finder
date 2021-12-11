package com.example.palettefinder;

import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
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
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsString(new StringRequestListener() {
                                 @Override
                                 public void onResponse(String response) {
                                     if (response != null) {
                                         Log.d("VOILA", response);
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
        Log.i("JFL", "Response from the server: " +jsonReader+result);
    }
}
