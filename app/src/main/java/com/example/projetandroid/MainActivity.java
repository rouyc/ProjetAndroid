package com.example.projetandroid;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;

public class MainActivity extends Activity {

    private static final int REQUEST_CODE_SECOND_ACTIVITY = 1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editTextCityName = findViewById(R.id.editText1MainActivity);
        Button buttonCity = (Button) findViewById(R.id.button1MainActivity);

        buttonCity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CityActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putString(CityActivity.INPUT_PARAMETER, editTextCityName.getText().toString());

                intent.putExtras(bundle1);
                startActivityForResult(intent, REQUEST_CODE_SECOND_ACTIVITY);
            }
        });
    }
}
