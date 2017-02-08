package com.example.android.popularmovies.data;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.example.android.popularmovies.R;

/**
 * Created by lucas on 08/02/2017.
 */

public class TextViewAdapter extends BaseAdapter {
    private Context context;

    public TextViewAdapter(Context context) {
        this.context= context;
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView tv;
        if (convertView == null) {
            tv = new TextView(context);
            tv.setLayoutParams(new GridView.LayoutParams(1000, 200));
        }
        else {
            tv = (TextView) convertView;
        }
        tv.setText(context.getString(R.string.error_message));
        return tv;
    }
}
