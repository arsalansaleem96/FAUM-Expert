package com.faum.faum_user;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.faum.faum_expert.R;

import java.util.List;

/**
 * Created by arsal on 03-Nov-17.
 */

public class DealList  extends ArrayAdapter <NewDeal_Database> {

    private Activity context;
    private List<NewDeal_Database> dealList;

    public DealList(Activity context, List<NewDeal_Database> dealList){

        super(context, R.layout.list_layout, dealList);
        this.context = context;
        this.dealList = dealList;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater =  context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout,null,true);

        TextView tvDealName = (TextView)listViewItem.findViewById(R.id.tvDealNamelayout);
        TextView tvNewDealCategory = (TextView)listViewItem.findViewById(R.id.tvNewDealCategorylayout);

        NewDeal_Database info = dealList.get(position);

        //tvDealName.setText(String.valueOf(info.getDealName()));
        tvDealName.setText(info.getDealName());
        tvNewDealCategory.setText(info.getNewDealCategory());




        return listViewItem;
    }
}


