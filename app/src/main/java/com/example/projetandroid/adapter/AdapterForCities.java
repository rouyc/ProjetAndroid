package com.example.projetandroid.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projetandroid.R;
import com.example.projetandroid.model.City;

import java.util.List;

public class AdapterForCities extends BaseAdapter
{

    private LayoutInflater layoutInflater;
    private List<City> cities;

    public AdapterForCities(Context context, List<City> cities) {
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


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = this.layoutInflater.inflate(R.layout.city_list_item, parent, false);

        TextView postCode = (TextView) itemView.findViewById(R.id.postCode);
        TextView nameVille = (TextView) itemView.findViewById(R.id.nameVille);
        TextView populationVille = (TextView) itemView.findViewById(R.id.populationNameVille);

        ImageView imageDepartment = (ImageView) itemView.findViewById(R.id.imageDepartment);
         TextView nameDepartment = (TextView) itemView.findViewById(R.id.nameDepartment);
         TextView numDepartment = (TextView) itemView.findViewById(R.id.numDepartment);
        ImageView numRegion = (ImageView) itemView.findViewById(R.id.numRegion);
        TextView nameRegion = (TextView) itemView.findViewById(R.id.nameRegion);
        Log.d("Debug","" + this.cities.get(position).getPostCode());
        postCode.setText(this.cities.get(position).getPostCode()+"");
        nameVille.setText(this.cities.get(position).getName());
        populationVille.setText(this.cities.get(position).getPopulation()+"");

        //ImageLoader.getInstance().displayImage(this.cities.get(position).getThumbnail(), gameImage);
        nameDepartment.setText(this.cities.get(position).getNameDepartment());
        numDepartment.setText(this.cities.get(position).getNumDepartment());

        //ImageLoader.getInstance().displayImage(this.cities.get(position).getThumbnail(), gameImage);
        nameRegion.setText(this.cities.get(position).getNameRegion());

        return itemView;
    }
}

