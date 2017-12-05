package com.faum.faum_user;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.faum.faum_expert.R;

public class Recent_Order_Data extends AppCompatActivity {

    TextView tvOrderDealName,tvOrderDealCat,tvOrderDealPrice,tvOrderDealQty;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent__order__data);

        tvOrderDealName = (TextView)findViewById(R.id.tvOrderDealName);
        tvOrderDealCat = (TextView)findViewById(R.id.tvOrderDealCat);
        tvOrderDealPrice = (TextView)findViewById(R.id.tvOrderDealPrice);
        tvOrderDealQty = (TextView)findViewById(R.id.tvOrderDealQty);

        SharedPreferences mPrefrences = PreferenceManager.getDefaultSharedPreferences(this);

        //Intent intent = getIntent();

        String dealName = mPrefrences.getString(getString(R.string.DEAL_NAME)," ");
        String newDealCategory = mPrefrences.getString(getString(R.string.DEAL_CATEGORY)," ");
        String dealTotalPrice = mPrefrences.getString(getString(R.string.ORDER_PRICE)," ");
        String dealTotalQty = mPrefrences.getString(getString(R.string.ORDER_QTY)," ");

        Toast.makeText(Recent_Order_Data.this,dealName, Toast.LENGTH_SHORT).show();

        tvOrderDealName.setText(dealName);
        tvOrderDealCat.setText(newDealCategory);
        tvOrderDealPrice.setText(dealTotalPrice);
        tvOrderDealQty.setText(dealTotalQty);
    }
}
