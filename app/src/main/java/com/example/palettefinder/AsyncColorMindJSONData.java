package com.example.palettefinder;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class AsyncColorMindJSONData extends AsyncTask<String, Void, String> {
    private GeneratePalette generateActivity;
    private List dataToSend;

    public AsyncColorMindJSONData(GeneratePalette generateActivity, List dataToSend){this.generateActivity=generateActivity; this.dataToSend=dataToSend;}

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {
        String urlString = "https://colormind.io/api/"; // URL to call
        List data = dataToSend; //data to post
        OutputStream out = null;
        String response = "";

        try {
            URL url = new URL(urlString);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            out = new BufferedOutputStream(conn.getOutputStream());

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
            writer.write("{\"model\":\"default\"}"); //TEST
            writer.flush();
            writer.close();
            out.close();

            conn.connect();

            int responseCode=conn.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line=br.readLine()) != null) {
                    response+=line;
                }
            }
            else {
                response="";

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Log.d("Response", response);
        return response;
    }
}
