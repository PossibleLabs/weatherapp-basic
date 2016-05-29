package com.possiblelabs.weatherapp.gui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.possiblelabs.weatherapp.R;
import com.possiblelabs.weatherapp.db.City;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by possiblelabs
 */
public class CityAdapter extends ArrayAdapter<City> {

    private static class ViewHolder {
        private TextView itemView;
    }

    private ViewHolder viewHolder;

    public CityAdapter(Context context, int textViewResourceId, List<City> items) {
        super(context, textViewResourceId, items);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(this.getContext())
                    .inflate(R.layout.single_item, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.itemView = (TextView) convertView.findViewById(R.id.txt_item);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        City city = getItem(position);
        if (city != null) {
            viewHolder.itemView.setText(city.toStringFormatted());
        }

        return convertView;
    }
}
