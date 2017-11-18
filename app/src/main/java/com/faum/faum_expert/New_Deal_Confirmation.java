package com.faum.faum_expert;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.faum.faum_expert.MainActivity.id;


public class New_Deal_Confirmation extends AppCompatActivity {

    TextView tvDealName,tvNewDealCategory,tvDealConfirmation;
    CheckBox checkBoxConfirmation;

    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("Expert Deal Confirmation");

    public static String DealId;

    Boolean check=false;

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

        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dealSnapshot : dataSnapshot.getChildren()){
                    try {
                        check  = dealSnapshot.child(DealId).getValue(NewDeal_Database.class).getCheckBoxConfirmation();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {

                        if(check==true){
                            checkBoxConfirmation.setChecked(check);
                        }else{
                            checkBoxConfirmation.setChecked(check);
                        }
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }


    private void AddDealConfirmation(){
        Boolean checkConfirmation = false;
        NewDeal_Database confirm;

        if(checkBoxConfirmation.isChecked()==true){
            checkConfirmation = true;
            confirm = new NewDeal_Database(checkConfirmation,DealId);
        }else{
            checkConfirmation = false;
            confirm = new NewDeal_Database(checkConfirmation,DealId);
        }
        rootRef.child(id).child(DealId).setValue(confirm);
    }
}
