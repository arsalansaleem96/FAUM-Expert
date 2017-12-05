package com.faum.faum_user;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.faum.faum_expert.R;

import java.util.List;

/**
 * Created by arsal on 01-Dec-17.
 */

public class RecentList extends ArrayAdapter<Confirm_Order_Database> {

    private Activity context;
    private List<Confirm_Order_Database> dealList;

    public RecentList(Activity context, List<Confirm_Order_Database> dealList){

        super(context, R.layout.list_layout, dealList);
        this.context = context;
        this.dealList = dealList;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater =  context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout,null,true);

        TextView tvDealPrice = (TextView)listViewItem.findViewById(R.id.tvDealNamelayout);
        TextView tvDealQty= (TextView)listViewItem.findViewById(R.id.tvNewDealCategorylayout);

        Confirm_Order_Database info = dealList.get(position);

        //tvDealName.setText(String.valueOf(info.getDealName()));
        tvDealPrice.setText(info.getOrderPrice());
        tvDealQty.setText(info.getOrderQty());




        return listViewItem;
    }
}
