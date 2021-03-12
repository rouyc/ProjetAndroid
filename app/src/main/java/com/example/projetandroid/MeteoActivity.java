package com.example.projetandroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.projetandroid.adapter.AdapterForCities;
import com.example.projetandroid.model.City;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MeteoActivity extends Activity {

    public static final String INPUT_PARAMETER = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        Log.d("Debug", "MeteoActivity");

        setContentView(R.layout.meteo_activity);


        RequestQueue queue = Volley.newRequestQueue(this);

        String input = getIntent().getExtras().getString(INPUT_PARAMETER);

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            Log.d("Debug", "Connected to internet");
            String url = "https://api.openweathermap.org/data/2.5/weather";
            url += "?zip="+input+",fr&appid=eedf0a01e8ded220beec409e4f82a73b&units=metric&lang=fr";


            Log.d("Debug", "URL is " + url);

            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d("Debug", "Meteo Response" + response);

                            try {
                                JSONObject jsonItem = new JSONObject(response);

                                TextView nomVille = findViewById(R.id.nomVille);
                                nomVille.setText(jsonItem.getString("name"));

                                TextView descMeteo = findViewById(R.id.descMeteo);
                                descMeteo.setText(jsonItem.getJSONArray("weather").optJSONObject(0).getString("description"));

                                TextView temp = findViewById(R.id.temp);
                                temp.setText(String.format(getString(R.string.tempText), jsonItem.getJSONObject("main").getString("temp"))  );

                                TextView vent = findViewById(R.id.vent);
                                vent.setText(String.format(getString(R.string.windSpeed), jsonItem.getJSONObject("wind").getString("speed")));



                                ImageView imageMeteo = findViewById(R.id.imageMeteo);
                                Log.d("Debug", "http://openweathermap.org/img/wn/" +jsonItem.getJSONArray("weather").optJSONObject(0).getString("icon")+".png");
                                Uri uri = Uri.parse("http://openweathermap.org/img/wn/01d.png");
                                SimpleDraweeView draweeView = (SimpleDraweeView) imageMeteo;
                                draweeView.setImageURI(uri);


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
