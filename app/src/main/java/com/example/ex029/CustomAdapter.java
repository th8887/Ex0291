package com.example.ex029;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> s;

    public CustomAdapter(Context context, ArrayList<String> stuList) {
        this.context = context;
        this.s= stuList;
    }

    @Override
    public int getCount() {
        return s.size();
    }

    public Object getItem(int i) {
        return s.get(i);
    }

    public long getItemId(int i) {
        return i;
    }

    public View getView(int i, View view, ViewGroup parent) {
         view= LayoutInflater.from(context).inflate(R.layout.custom_lv,parent,false);
         String tmpStudent= (String) getItem(i);
         TextView tv= (TextView) view.findViewById(R.id.t);

         tv.setText(tmpStudent);
        return view;
    }

}
