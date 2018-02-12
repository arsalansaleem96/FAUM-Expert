package com.faum.faum_expert;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.faum.faum_expert.R;

public class Recent_Order_Data extends AppCompatActivity {

    TextView tvOrderDealName,tvOrderDealCat,tvOrderDealPrice,tvOrderDealQty;
    Button btnBookRider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent__order__data);

        tvOrderDealName = (TextView)findViewById(R.id.tvOrderDealName);
        tvOrderDealCat = (TextView)findViewById(R.id.tvOrderDealCat);
        tvOrderDealPrice = (TextView)findViewById(R.id.tvOrderDealPrice);
        tvOrderDealQty = (TextView)findViewById(R.id.tvOrderDealQty);
        btnBookRider = (Button)findViewById(R.id.btnBooKRider);

        SharedPreferences mPrefrences = PreferenceManager.getDefaultSharedPreferences(this);

        //Intent intent = getIntent();

        String dealName = mPrefrences.getString(getString(R.string.DEAL_NAME_EXPERT)," ");
        String newDealCategory = mPrefrences.getString(getString(R.string.DEAL_CATEGORY_EXPERT)," ");
        String dealTotalPrice = mPrefrences.getString(getString(R.string.ORDER_PRICE_EXPERT)," ");
        String dealTotalQty = mPrefrences.getString(getString(R.string.ORDER_QTY_EXPERT)," ");

        //Toast.makeText(Recent_Order_Data.this,dealName, Toast.LENGTH_SHORT).show();

        tvOrderDealName.setText("Deal Name: "+dealName);
        tvOrderDealCat.setText("Category: "+newDealCategory);
        tvOrderDealPrice.setText("Total Price: "+dealTotalPrice);
        tvOrderDealQty.setText("Total Qty: "+dealTotalQty);

        btnBookRider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Recent_Order_Data.this,RidersIDS.class));
            }
        });
    }
}
