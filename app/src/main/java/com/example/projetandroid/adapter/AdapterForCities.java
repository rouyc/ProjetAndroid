package com.example.projetandroid.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projetandroid.MeteoActivity;
import com.example.projetandroid.R;
import com.example.projetandroid.model.City;

import java.lang.reflect.Field;
import java.util.List;

public class AdapterForCities extends BaseAdapter
{

    private Context context;
    private LayoutInflater layoutInflater;
    private List<City> cities;

    public AdapterForCities(Context context, List<City> cities) {
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.cities = cities;
    }

    @Override
    public int getCount() {
        return this.cities.size();
    }

    @Override
    public Object getItem(int position) {
        return this.cities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

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
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = this.layoutInflater.inflate(R.layout.city_list_item, parent, false);

        TextView postCode = (TextView) itemView.findViewById(R.id.postCode);
        TextView nameVille = (TextView) itemView.findViewById(R.id.nameVile);
        TextView populationVille = (TextView) itemView.findViewById(R.id.populationNameVille);
        int codePostal = this.cities.get(position).getPostCode();

        postCode.setText(this.cities.get(position).getPostCode() + "");
        nameVille.setText(this.cities.get(position).getName());
        populationVille.setText(this.cities.get(position).getPopulation());

        ImageView imageDepartment = (ImageView) itemView.findViewById(R.id.imageDepartment);
        TextView nameDepartment = (TextView) itemView.findViewById(R.id.nameDepartment);
        TextView numDepartment = (TextView) itemView.findViewById(R.id.numDepartment);
        int idImageDepartement = getResId("dep" + this.cities.get(position).getNumDepartment().toLowerCase(), R.drawable.class);
        imageDepartment.setImageResource(idImageDepartement);
        nameDepartment.setText(this.cities.get(position).getNameDepartment());
        numDepartment.setText(this.cities.get(position).getNumDepartment());

        ImageView imageRegion = (ImageView) itemView.findViewById(R.id.numRegion);
        TextView nameRegion = (TextView) itemView.findViewById(R.id.nameRegion);
        int idImageRegion = getResId("reg" + this.cities.get(position).getNumRegion().toLowerCase(), R.drawable.class);
        imageRegion.setImageResource(idImageRegion);
        nameRegion.setText(this.cities.get(position).getNameRegion());

        Button buttonCity = (Button) itemView.findViewById(R.id.buttonMeteo);

        buttonCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext().getApplicationContext(), MeteoActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putString(MeteoActivity.INPUT_PARAMETER, String.valueOf(codePostal));

                intent.putExtras(bundle1);
                context.startActivity(intent);
            }
        });

        return itemView;
    }
}
