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

public class Deal_Detail_three extends AppCompatActivity {

    Button btnDealDetailThree;
    TextView tvDDTeightValue,tvDDTtwelveValue,tvDDTsixValue,tvDDTnineValue;
    TextView tvDDTmondayValue,tvDDTtuesdayValue,tvDDTwednesdayValue,tvDDTthursdayValue,tvDDTfridayValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal__detail_three);

        btnDealDetailThree = (Button)findViewById(R.id.btnDealDetailThree);

        tvDDTeightValue = (TextView)findViewById(R.id.tvDDTeightValue);
        tvDDTtwelveValue = (TextView)findViewById(R.id.tvDDTtwelveValue);
        tvDDTsixValue = (TextView)findViewById(R.id.tvDDTsixValue);
        tvDDTnineValue = (TextView)findViewById(R.id.tvDDTnineValue);

        tvDDTmondayValue = (TextView)findViewById(R.id.tvDDTmondayValue);
        tvDDTtuesdayValue = (TextView)findViewById(R.id.tvDDTtuesdayValue);
        tvDDTwednesdayValue = (TextView)findViewById(R.id.tvDDTwednesdayValue);
        tvDDTthursdayValue = (TextView)findViewById(R.id.tvDDTthursdayValue);
        tvDDTfridayValue = (TextView)findViewById(R.id.tvDDTfridayValue);

        SharedPreferences mPrefrences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor mEditor =  mPrefrences.edit();

        tvDDTeightValue.setText(mPrefrences.getString(getString(R.string.DEAL_TIME_EIGHTTOTEN)," "));
        tvDDTtwelveValue.setText(mPrefrences.getString(getString(R.string.DEAL_TIME_TWELVETOTWO)," "));
        tvDDTsixValue.setText(mPrefrences.getString(getString(R.string.DEAL_TIME_SIXTOEIGHT)," "));
        tvDDTnineValue.setText(mPrefrences.getString(getString(R.string.DEAL_TIME_NINETOELEVEN)," "));


        tvDDTmondayValue.setText(mPrefrences.getString(getString(R.string.DEAL_DAYS_MONDAY)," "));
        tvDDTtuesdayValue.setText(mPrefrences.getString(getString(R.string.DEAL_DAYS_TUESDAY)," "));
        tvDDTwednesdayValue.setText(mPrefrences.getString(getString(R.string.DEAL_DAYS_WEDNESDAY)," "));
        tvDDTthursdayValue.setText(mPrefrences.getString(getString(R.string.DEAL_DAYS_THURSDAY)," "));
        tvDDTfridayValue.setText(mPrefrences.getString(getString(R.string.DEAL_DAYS_FRIDAY)," "));

        btnDealDetailThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Deal_Detail_three.this,MapsActivityForCooker.class));
            }
        });

    }
}
