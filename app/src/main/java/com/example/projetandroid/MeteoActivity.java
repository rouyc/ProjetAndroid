package com.example.projetandroid;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;


public class MeteoActivity extends Activity {

    public static final String INPUT_PARAMETER = "";

    public static int getResId(String resName, Class<?> c) {

        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("Debug", "MeteoActivity");

        setContentView(R.layout.meteo_activity);

        RequestQueue queue = Volley.newRequestQueue(this);

        String input = getIntent().getExtras().getString(INPUT_PARAMETER);

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            Log.d("Debug", "Connected to internet");
            String url = "https://api.openweathermap.org/data/2.5/weather";
            url += "?zip=" + input + ",fr&appid=eedf0a01e8ded220beec409e4f82a73b&units=metric&lang=fr";


            Log.d("Debug", "URL is " + url);

            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d("Debug", "Meteo Response" + response);
                            try {
                                JSONObject jsonItem = new JSONObject(response);

                                TextView nomVille = findViewById(R.id.zoneGeo);
                                nomVille.setText(String.format(getString(R.string.zoneGeo),jsonItem.getString("name")));

                                TextView descMeteo = findViewById(R.id.descMeteo);
                                descMeteo.setText(jsonItem.getJSONArray("weather").optJSONObject(0).getString("description"));

                                TextView temp = findViewById(R.id.temp);
                                temp.setText(String.format(getString(R.string.tempText), jsonItem.getJSONObject("main").getString("temp")));

                                TextView vent = findViewById(R.id.wind);
                                vent.setText(String.format(getString(R.string.windSpeed), jsonItem.getJSONObject("wind").getString("speed")));

                                ImageView imageMeteo = findViewById(R.id.imageMeteo);
                                int idImageRegion = getResId("w" + jsonItem.getJSONArray("weather").optJSONObject(0).getString("icon"), R.drawable.class);
                                imageMeteo.setImageResource(idImageRegion);
                            } catch (JSONException e) {
                                Log.e("Debug", "Error while parsing games result", e);
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Erreur de requête", Toast.LENGTH_LONG);
                    toast.show();
                }
            });
            queue.add(stringRequest);
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Non connecté à internet", Toast.LENGTH_LONG);
            toast.show();
            finish();
        }

    }


}
