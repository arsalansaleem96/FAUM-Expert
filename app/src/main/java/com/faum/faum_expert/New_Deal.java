    package com.faum.faum_expert;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.faum.faum_expert.MainActivity.id;
import static com.faum.faum_expert.Personal_Information.t_name;


    public class New_Deal extends AppCompatActivity {
        TextView tvNewDealTitle;
        EditText etDealName,etNewDealCategory,etDishName,etDealDescription;
        Button btnNewDealConfirm;
        //DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("Expert");
        //DatabaseReference keyRefrence = rootRef.child(id);
        DatabaseReference dealInformation = FirebaseDatabase.getInstance().getReference("Expert Deal Information");

        public static String DealId;
        public static String Cooker_Deal = "Cooker Deals";
        public static String  Deal = "Deals Information";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_deal);


        etDealName = (EditText)findViewById(R.id.etDealName);
        etNewDealCategory = (EditText)findViewById(R.id.etNewDealCategory);
        etDishName = (EditText)findViewById(R.id.etDishName);
        etDealDescription = (EditText)findViewById(R.id.etDealDescription);

        btnNewDealConfirm = (Button)findViewById(R.id.btnNewDealConfirm);

        btnNewDealConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddNewDeal();
            }
        });
    }

    private void AddNewDeal(){


        //int DealName = Integer.parseInt(etDealName.getText().toString());
        String DealName = etDealName.getText().toString();
        String NewDealCategory = etNewDealCategory.getText().toString();
        String DishName = etDishName.getText().toString();
        String DealDescription  = etDealDescription.getText().toString();


        if(TextUtils.isEmpty(etDishName.getText().toString()) || TextUtils.isEmpty(etNewDealCategory.getText().toString()) || TextUtils.isEmpty(etDishName.getText().toString()) || TextUtils.isEmpty(etDealDescription.getText().toString())){
            Toast.makeText(this,"All fileds must be filled.",Toast.LENGTH_SHORT).show();
        }else{


            DealId = dealInformation.push().getKey();
            //firebaseDatabase data =  new firebaseDatabase(DealName,NewDealCategory,DishName,DealDescription);

            //Contact_Info info = new Contact_Info( DealName, NewDealCategory,  DealDescription);



            NewDeal_Database info =  new NewDeal_Database(id,DealId,DealName,NewDealCategory, DishName,  DealDescription);

            //keyRefrence.child(Cooker_Deal).child(DealId).child(Deal).setValue(info);
            dealInformation.child(id).child(DealId).setValue(info);

            Toast.makeText(this,"Information Added",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(New_Deal.this,New_Deal_Time.class);
            startActivity(intent);
        }
    }
}
