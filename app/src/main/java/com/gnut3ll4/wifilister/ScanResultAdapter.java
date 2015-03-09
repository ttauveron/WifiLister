package com.gnut3ll4.wifilister;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by gnut3ll4 on 03/03/15.
 */
public class ScanResultAdapter extends ArrayAdapter<ScanResult> {

    private LayoutInflater inflater;

    public ScanResultAdapter(Context context,int rowLayoutResourceId, ArrayList<ScanResult> list) {
        super(context, rowLayoutResourceId, list);
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        ViewHolder holder;
        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            view = inflater.inflate(R.layout.row_listview, parent, false);
            holder = new ViewHolder();
            holder.tvBSSID = (TextView) view.findViewById(R.id.tv_bssid);
            holder.tvSSID = (TextView) view.findViewById(R.id.tv_ssid);
            holder.tvLevel = (TextView) view.findViewById(R.id.tv_level);

            view.setTag(holder);
        }

        ScanResult item = getItem(position);

        holder.tvBSSID.setText(item.BSSID);
        holder.tvSSID.setText(item.SSID);
        holder.tvLevel.setText("" + item.level);


        return view;
    }

    static class ViewHolder {
        TextView tvBSSID;
        TextView tvSSID;
        TextView tvLevel;

    }

}
