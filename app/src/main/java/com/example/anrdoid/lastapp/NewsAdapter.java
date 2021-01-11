package com.example.anrdoid.lastapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class NewsAdapter extends ArrayAdapter<News> {
    public NewsAdapter(Context context, ArrayList<News> news) {
        super(context, 0, news);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        final News news1 = getItem(position);
        TextView Titledata = (TextView) listItem.findViewById(R.id.title);
        String titlei = news1.getTitle();
        Titledata.setText(titlei);

        TextView secdata = (TextView) listItem.findViewById(R.id.cat);
        String seci = news1.getSection();
        secdata.setText(seci);

        TextView datedata = (TextView) listItem.findViewById(R.id.date);
        String datei = news1.getDate();
        datedata.setText(datei);

        TextView pillardata = (TextView) listItem.findViewById(R.id.pillar);
        String pillari = news1.getpillar();
        pillardata.setText(pillari);

        TextView typedata = (TextView) listItem.findViewById(R.id.type);
        String typei = news1.getType();
        typedata.setText(typei);

        TextView autherdata = (TextView) listItem.findViewById(R.id.auther);
        String autheri = news1.getAuther();
        autherdata.setText(autheri);

        TextView textv = (TextView) listItem.findViewById(R.id.text);
        textv.setText(R.string.readmore);
        return listItem;
    }
}
