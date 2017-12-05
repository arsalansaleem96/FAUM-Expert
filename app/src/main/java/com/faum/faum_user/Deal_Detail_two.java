package com.faum.faum_user;

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

public class Deal_Detail_two extends AppCompatActivity {

    TextView tvDDTEstimatedValue,tvDDTSizeValue,tvDDTPriceValue;
    Button btnDealDetailTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal__detail_two);

        tvDDTEstimatedValue = (TextView)findViewById(R.id.tvDDTEstimatedValue);
        tvDDTSizeValue = (TextView)findViewById(R.id.tvDDTSizeValue);
        tvDDTPriceValue = (TextView)findViewById(R.id.tvDDTPriceValue);
        btnDealDetailTwo = (Button)findViewById(R.id.btnDealDetailTwo);

        SharedPreferences mPrefrences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor mEditor =  mPrefrences.edit();

        String EstimateTime = mPrefrences.getString(getString(R.string.DEAL_ESTIMATEDTIME)," ");
        String NewDealPrice = mPrefrences.getString(getString(R.string.DEAL_PRICE)," ");
        String NewDealSize = mPrefrences.getString(getString(R.string.DEAL_SIZE)," ");

        tvDDTEstimatedValue.setText(EstimateTime);
        tvDDTSizeValue.setText(NewDealSize);
        tvDDTPriceValue.setText(NewDealPrice);

        //Toast.makeText(getApplicationContext(),EstimateTime,Toast.LENGTH_LONG).show();
        //Toast.makeText(getApplicationContext(),NewDealPrice,Toast.LENGTH_LONG).show();
        //Toast.makeText(getApplicationContext(),NewDealSize,Toast.LENGTH_LONG).show();

        btnDealDetailTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Deal_Detail_two.this,Deal_Detail_three.class));
            }
        });


    }
}
