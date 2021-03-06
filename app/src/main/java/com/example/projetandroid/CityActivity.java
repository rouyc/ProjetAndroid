package com.example.projetandroid;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.projetandroid.adapter.AdapterForCities;
import com.example.projetandroid.model.City;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CityActivity extends Activity {

    public static final String INPUT_PARAMETER = "input_parameter";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);

        List<City> cities = new ArrayList<>(0);

        RequestQueue queue = Volley.newRequestQueue(this);

        String input = getIntent().getExtras().getString(INPUT_PARAMETER);

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            Log.d("Debug", "Connected to internet");

            String url = "https://geo.api.gouv.fr/";
            String fields = "&fields=nom,code,codesPostaux,codeDepartement,departement,codeRegion,region,population";
            String format = "&format=json";
            String geometry = "&geometry=centre";

            url += "communes?nom=" + input + fields + format + geometry;

            Log.d("Debug", "URL is " + url);

            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d("Debug", "IUT Response" + response);

                            try {
                                JSONArray listOfCities = new JSONArray(response);

                                JSONObject jsonItem;
                                City city;
                                for (int i = 0; i < listOfCities.length(); i++) {
                                    jsonItem = listOfCities.getJSONObject(i);

                                    city = new City(jsonItem.getString("code"),
                                            jsonItem.getString("nom"),
                                            jsonItem.getJSONArray("codesPostaux").optInt(0),
                                            String.format(getString(R.string.populationLayout), jsonItem.getString("population")),
                                            jsonItem.getString("codeDepartement"),
                                            jsonItem.getJSONObject("departement").getString("nom"),
                                            jsonItem.getJSONObject("region").getString("code"),
                                            jsonItem.getJSONObject("region").getString("nom"));
                                    cities.add(city);
                                }

                                ListView listView = (ListView) findViewById(R.id.listView);
                                AdapterForCities adapterForCities = new AdapterForCities(CityActivity.this, cities);
                                listView.setAdapter(adapterForCities);

                            } catch (JSONException e) {
                                Log.e("Debug", "Error while parsing cities result", e);
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Erreur de requ??te", Toast.LENGTH_LONG);
                    toast.show();
                }
            });
            queue.add(stringRequest);
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Non connect?? ?? internet", Toast.LENGTH_LONG);
            toast.show();
            finish();
        }



    }
}
