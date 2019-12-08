package com.hoaithi.tripme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.hoaithi.tripme.model.Attraction;
import com.hoaithi.tripme.model.Place;
import com.hoaithi.tripme.util.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchPlaceActivity extends AppCompatActivity  {

    @BindView(R.id.status_bar)
    View mStatusBar;
    @BindView(R.id.city_text_view)
    TextView cityTextView;

    @BindView(R.id.attraction_recycler_view)
    RecyclerView attractionRecyclerView;
    AttractionItemAdapter attractionItemAdapter;
    List<Attraction> attractionList = new ArrayList<>();

    @OnClick(R.id.back_button)
    void back() {
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setNavigationBarColor(Color.WHITE);
        setContentView(R.layout.activity_search_place);
        ButterKnife.bind(this);

        // add status bar space
        mStatusBar.getLayoutParams().height = Util.getStatusHeight(getResources());
        mStatusBar.requestLayout();
        initView();
    }

    private void initView() {
        Log.d("Nunu", Integer.toString(attractionList.size()));
        attractionItemAdapter = new AttractionItemAdapter(this, attractionList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        attractionRecyclerView.setHasFixedSize(true);
        attractionRecyclerView.setLayoutManager(layoutManager);
        attractionRecyclerView.setItemAnimator(new DefaultItemAnimator());
        attractionRecyclerView.setAdapter(attractionItemAdapter);

        new getAttractionInCity(cityTextView.getText().toString());
    }

    private class  getAttractionInCity  extends AsyncTask<String, Integer, String>
    {
        StringBuilder sb;

        public getAttractionInCity(String city)
        {
            sb=new StringBuilder("https://maps.googleapis.com/maps/api/place/textsearch/json?");
            sb.append("query="+city.replaceAll("\\s+","")+"+city+point+of+interest&language=vi&key="+ PlaceApi.API_KEY);
            this.execute(sb.toString());
        }

        @Override
        protected String doInBackground(String... strurl) {
            HttpURLConnection connection=null;

            StringBuilder jsonResult=new StringBuilder();
            try{
                URL url=new URL(strurl[0]);
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
            return jsonResult.toString();
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                JSONObject jsonObject=new JSONObject(result);
                JSONArray results=jsonObject.getJSONArray("results");
                Log.d("Nunu", Integer.toString(results.length()));
                for(int i=0;i< results.length();i++){
                    JSONObject objecti = results.getJSONObject(i);
                    String name = objecti.getString("name");
                    String address = objecti.getString("formatted_address");
                    JSONObject location = objecti.getJSONObject("geometry").getJSONObject("location");
                    String photo = objecti.getJSONArray("photos").getJSONObject(0).getString("photo_reference");
                    String rating = objecti.getString("rating");
                    attractionList.add(new Attraction(name, photo, address, location.getString("lat"), location.getString("lng"),
                            rating));
                    attractionItemAdapter.notifyDataSetChanged();
                }
            }
            catch (JSONException e){
                e.printStackTrace();
            }
        }
    }

}
