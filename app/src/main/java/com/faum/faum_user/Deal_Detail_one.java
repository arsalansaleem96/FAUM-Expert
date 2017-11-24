package com.faum.faum_user;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.faum.faum_expert.R;

public class Deal_Detail_one extends AppCompatActivity {

    TextView tvDDDishDescvalue,tvDDDishNamevalue,tvDDCategoryvalue,tvDDNamevalue;
    Button btnDealDetailOne;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal__detail_one);
        tvDDNamevalue = (TextView)findViewById(R.id.tvDDNamevalue);
        tvDDCategoryvalue = (TextView)findViewById(R.id.tvDDCategoryvalue);
        tvDDDishNamevalue = (TextView)findViewById(R.id.tvDDDishNamevalue);
        tvDDDishDescvalue = (TextView)findViewById(R.id.tvDDDishDescvalue);

        btnDealDetailOne = (Button)findViewById(R.id.btnDealDetailOne);

        SharedPreferences mPrefrences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor mEditor =  mPrefrences.edit();

        //Intent intent = getIntent();

        //String dealName = intent.getStringExtra(New_Deal_List.DEAL_NAME);
        //String newDealCategory = intent.getStringExtra(New_Deal_List.DEAL_CATEGORY);
        //String dishName = intent.getStringExtra(New_Deal_List.DEAL_DISH_NAME);
        //String dealDescription  = intent.getStringExtra(New_Deal_List.DEAL_DESCRIPTION);

        String dealName = mPrefrences.getString(getString(R.string.DEAL_NAME)," ");
        String newDealCategory = mPrefrences.getString(getString(R.string.DEAL_CATEGORY)," ");
        String dishName = mPrefrences.getString(getString(R.string.DEAL_DISH_NAME)," ");
        String dealDescription  = mPrefrences.getString(getString(R.string.DEAL_DESCRIPTION)," ");

        tvDDNamevalue.setText(dealName);
        tvDDCategoryvalue.setText(newDealCategory);
        tvDDDishNamevalue.setText(dishName);
        tvDDDishDescvalue.setText(dealDescription);


        btnDealDetailOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Deal_Detail_one.this,Deal_Detail_two.class));
            }
        });


    }
}
