package com.hoaithi.tripme;


import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class ThamquanFragment extends Fragment {

    private View mRootView;
    Unbinder unbinder;

    @BindView(R.id.results_recycler_view)
    RecyclerView resultsRecyclerView;
    AttractionItemAdapter attractionItemAdapter;
    List<Attraction> attractionList = new ArrayList<>();

    public ThamquanFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_thamquan, container, false);
        unbinder = ButterKnife.bind(this, mRootView);
        initView();
        return mRootView;
    }

    public void initView()
    {
        attractionItemAdapter = new AttractionItemAdapter(this.getContext(), attractionList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        resultsRecyclerView.setHasFixedSize(true);
        resultsRecyclerView.setLayoutManager(layoutManager);
        resultsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        resultsRecyclerView.setAdapter(attractionItemAdapter);

        new getAttractionInCity(SearchPlaceActivity.city);
    }

    private class  getAttractionInCity  extends AsyncTask<String, Integer, String>
    {
        StringBuilder sb;

        public getAttractionInCity(String city)
        {
            sb=new StringBuilder("https://maps.googleapis.com/maps/api/place/textsearch/json?");
            sb.append("query="+city.replaceAll("\\s+","")+"+city+point+of+interest&language=vi&key="+ PlaceApi.API_KEY);
            Log.d("Nunu", sb.toString());
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
                for(int i=0;i< Math.min(10, results.length());i++){
                    JSONObject objecti = results.getJSONObject(i);
                    String name = objecti.getString("name");
                    String address = objecti.getString("formatted_address");
                    JSONObject location = objecti.getJSONObject("geometry").getJSONObject("location");
                    String photo = "";
                    if (objecti.has("photos"))
                    {
                        photo = objecti.getJSONArray("photos").getJSONObject(0).getString("photo_reference");
                    }
                    String rating = objecti.getString("rating");
                    attractionList.add(new Attraction(name, photo, address, location.getString("lat"), location.getString("lng"),
                            rating));
                }
                attractionItemAdapter.notifyDataSetChanged();
            }
            catch (JSONException e){
                e.printStackTrace();
                Log.d("Nunu",e.getMessage());
            }
        }
    }

}
