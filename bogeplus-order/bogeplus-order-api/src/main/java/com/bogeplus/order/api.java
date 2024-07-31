package com.bogeplus.order;

import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class api {
    private static final String API_KEY = "425515ff8d2e941cfe9d69c06943d7a2";
    private static final String BASE_URL = "https://restapi.amap.com/v3/direction/driving";
    public static String getDistance(String origin,String destination)throws Exception{

        String url = BASE_URL + "?key=" + API_KEY + "&origin=" + origin + "&destination=" + destination + "&extensions=base&output=JSON";

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        if ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        connection.disconnect();
        JSONObject jsonResponse = JSONObject.parseObject(response.toString());
    }
}
