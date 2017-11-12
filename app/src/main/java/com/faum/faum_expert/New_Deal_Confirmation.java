package com.faum.faum_expert;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.faum.faum_expert.MainActivity.id;


public class New_Deal_Confirmation extends AppCompatActivity {

    TextView tvDealName,tvNewDealCategory,tvDealConfirmation;
    CheckBox checkBoxConfirmation;

    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("Expert Deals Confirmation");
    //DatabaseReference keyRefrence = rootRef.child(id);

    public static String DealId;
    public static String Cooker_Deal = "Cooker Deals";
    public static String  Deal_Status = "Deal Status";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new__deal__confirmation);

        tvDealName = (TextView)findViewById(R.id.tvDealName);
        tvNewDealCategory = (TextView)findViewById(R.id.tvNewDealCategory);
        tvDealConfirmation =(TextView)findViewById(R.id.tvDealConfirmation);

        checkBoxConfirmation = (CheckBox)findViewById(R.id.checkBoxConfirmation);


        Intent intent = getIntent();

        String dealName = intent.getStringExtra(New_Deal_List.DEAL_NAME);
        String newDealCategory = intent.getStringExtra(New_Deal_List.DEAL_CATEGORY);
        DealId = intent.getStringExtra(New_Deal_List.DEAL_ID);

        tvDealName.setText(dealName);
        tvNewDealCategory.setText(newDealCategory);



        checkBoxConfirmation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddDealConfirmation();
            }
        });


    }

    private void AddDealConfirmation(){
        Boolean checkConfirmation = false;
        NewDeal_Database confirm;

        if(checkBoxConfirmation.isChecked()==true){
            checkConfirmation = true;
            confirm = new NewDeal_Database(checkConfirmation);
        }else{
            checkConfirmation = false;
            confirm = new NewDeal_Database(checkConfirmation);
        }
        rootRef.child(id).child(DealId).child(Deal_Status).setValue(confirm);

    }
}
