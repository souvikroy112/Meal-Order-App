package com.example.souvikroy.meal.others;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Tanay on 11-08-2015.
 */
public class JSONParser {

    public JSONParser() {

    }

    public JSONObject getJsonFromURL(String getURL) {
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        URL url;
        String result = null;
        JSONObject jsonObject = null;
        try {
            url = new URL(getURL);
//            httpURLConnection = (HttpURLConnection) url.openConnection();
//             // requesting post method
//httpURLConnection.setRequestMethod("POST");
//            httpURLConnection.setDoOutput(true);
//            httpURLConnection.connect();
//           int responseCode = httpURLConnection.getResponseCode(); // getting response code
//
//            inputStream = new BufferedInputStream(httpURLConnection.getInputStream());


            // Creating an http connection to communicate with url
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            inputStream = urlConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder stringBuilder = new StringBuilder();

            String line;
            while ((line = bufferedReader.readLine()) != null)
            {
                stringBuilder.append(line).append("\n");
            }

            result = stringBuilder.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (inputStream != null)
            {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (httpURLConnection != null)
            {
                httpURLConnection.disconnect();
            }
        }

        try {
            jsonObject = new JSONObject(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }
    
    
}
