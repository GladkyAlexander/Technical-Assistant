package ru.great_larder.technical_assistant_android;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

public class SelectCountryAdapter extends BaseAdapter {
    
    Context context;
    int flags[];
    String[] countryNames;
    LayoutInflater inflter;
    
    public SelectCountryAdapter(Context applicationContext, int[] flags, String[] countryNames) {
        this.context = applicationContext;
        this.flags = flags;
        this.countryNames = countryNames;
        inflter = (LayoutInflater.from(applicationContext));
    }
    
    @Override
    public int getCount() {
        return flags.length;
    }
    
    @Override
    public Object getItem(int i) {
        return null;
    }
    
    @Override
    public long getItemId(int i) {
        return 0;
    }
    
    @SuppressLint("ViewHolder")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.fragment_item_row_spinner, null);
        ImageView icon = view.findViewById(R.id.icon);
        TextView names = view.findViewById(R.id.text_language);
        icon.setImageResource(flags[i]);
        names.setText(countryNames[i]);
        return view;
    }
}
