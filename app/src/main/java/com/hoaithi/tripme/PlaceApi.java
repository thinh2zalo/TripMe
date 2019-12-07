package com.hoaithi.tripme;

import android.util.Log;

import com.hoaithi.tripme.model.Attraction;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class PlaceApi {
    final String API_KEY = "AIzaSyA3gCqHV2M456HOm_35h9MiGSP_7NgPrnE";
    public ArrayList<String> autoComplete(String input){
        ArrayList<String> arrayList=new ArrayList();
        HttpURLConnection connection=null;
        StringBuilder jsonResult=new StringBuilder();
        try {
            StringBuilder sb=new StringBuilder("https://maps.googleapis.com/maps/api/place/autocomplete/json?");
            sb.append("input="+input);
            sb.append("&types=geocode&components=country:vn&key="+API_KEY);
            URL url=new URL(sb.toString());
            connection=(HttpURLConnection)url.openConnection();
            InputStreamReader inputStreamReader=new InputStreamReader(connection.getInputStream());

            int read;

            char[] buff=new char[1024];
            while ((read=inputStreamReader.read(buff))!=-1){
                jsonResult.append(buff,0,read);
            }
        }
        catch (MalformedURLException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally {
            if(connection!=null){
                connection.disconnect();
            }
        }

        try {
            JSONObject jsonObject=new JSONObject(jsonResult.toString());
            JSONArray prediction=jsonObject.getJSONArray("predictions");
            for(int i=0;i<prediction.length();i++){
                JSONObject objecti = prediction.getJSONObject(i);
                if (objecti.getJSONArray("types").toString().contains("locality"))
                {
                    String city = objecti.getJSONObject("structured_formatting").getString("main_text");
                    arrayList.add(city);
                }

            }
        }
        catch (JSONException e){
            e.printStackTrace();
        }

        return arrayList;
    }

    public ArrayList<Attraction> getAttractionInCity(String city)
    {
        ArrayList<Attraction> arrayList=new ArrayList();
        HttpURLConnection connection=null;
        StringBuilder jsonResult=new StringBuilder();
        try {
            StringBuilder sb=new StringBuilder("https://maps.googleapis.com/maps/api/place/textsearch/json?");
            sb.append("query="+city.replaceAll("\\s+","")+"+city+point+of+interest&language=vi&key="+API_KEY);
            Log.d("Nunu", sb.toString());
            URL url=new URL(sb.toString());
            connection=(HttpURLConnection)url.openConnection();
            InputStreamReader inputStreamReader=new InputStreamReader(connection.getInputStream());
            int read;

            char[] buff=new char[1024];
            while ((read=inputStreamReader.read(buff))!=-1){
                jsonResult.append(buff,0,read);
            }
        }
        catch (MalformedURLException e){
            e.printStackTrace();
            Log.d("Nunu", e.getMessage());
        }
        catch (IOException e){
            e.printStackTrace();
            Log.d("Nunu", e.getLocalizedMessage());
        }
        finally {
            if(connection!=null){
                connection.disconnect();
            }
        }

        try {
            JSONObject jsonObject=new JSONObject(jsonResult.toString());
            JSONArray results=jsonObject.getJSONArray("results");
            Log.d("Nunu", Integer.toString(results.length()));
            for(int i=0;i< results.length();i++){
                JSONObject objecti = results.getJSONObject(i);
                String name = objecti.getString("name");
                String address = objecti.getString("formatted_address");
                JSONObject location = objecti.getJSONObject("geometry").getJSONObject("location");
                String photo = objecti.getJSONArray("photos").getJSONObject(0).getString("photo_reference");
                String rating = objecti.getString("rating");
                arrayList.add(new Attraction(name, photo, address, location.getString("lat"), location.getString("lng"),
                        rating));
            }
        }
        catch (JSONException e){
            e.printStackTrace();
        }


        return arrayList;
    }
}
