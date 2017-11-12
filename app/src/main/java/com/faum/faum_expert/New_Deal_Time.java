package com.faum.faum_expert;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.faum.faum_expert.New_Deal.Cooker_Deal;
import static com.faum.faum_expert.MainActivity.id;
import static com.faum.faum_expert.New_Deal.DealId;

public class New_Deal_Time extends AppCompatActivity {

    EditText etEstimatedTime,etNewDealPrice,etNewDealSize;
    TextView tvNewDealContinueTitle,tvDealTime,tvDealDays;
    Button btnNewDealConfirm;
    CheckBox checkBox8to10,checkbox12to2,checkBox6to8,checkBox9to11;
    CheckBox checkBoxMonday,checkBoxTuesday,checkBoxWednesday,checkBoxFriday,checkBoxThursday;

    //DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("Expert");
    DatabaseReference dealTimeRefrence = FirebaseDatabase.getInstance().getReference("Expert Deal Times");
    DatabaseReference dealPriceRefrence = FirebaseDatabase.getInstance().getReference("Expert Deal Prices");
    DatabaseReference dealDaysRefrence = FirebaseDatabase.getInstance().getReference("Expert Deal Days");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_deal_time);

        etEstimatedTime = (EditText)findViewById(R.id.etEstimatedTime);
        etNewDealPrice = (EditText)findViewById(R.id.etNewDealPrice);
        etNewDealSize = (EditText)findViewById(R.id.etNewDealSize);

        tvNewDealContinueTitle = (TextView)findViewById(R.id.tvNewDealContinueTitle);
        tvDealTime = (TextView)findViewById(R.id.tvDealTime);
        tvDealDays = (TextView)findViewById(R.id.tvDealDays);

        checkBox8to10 = (CheckBox)findViewById(R.id.checkBox8to10);
        checkbox12to2 = (CheckBox)findViewById(R.id.checkbox12to2);
        checkBox6to8 = (CheckBox)findViewById(R.id.checkBox6to8);
        checkBox9to11 = (CheckBox)findViewById(R.id.checkBox9to11);

        checkBoxMonday = (CheckBox)findViewById(R.id.checkBoxMonday);
        checkBoxTuesday = (CheckBox)findViewById(R.id.checkBoxTuesday);
        checkBoxWednesday = (CheckBox)findViewById(R.id.checkBoxWednesday);
        checkBoxThursday = (CheckBox)findViewById(R.id.checkBoxThursday);
        checkBoxFriday = (CheckBox)findViewById(R.id.checkBoxFriday);

        btnNewDealConfirm = (Button) findViewById(R.id.btnNewDealConfirm);

            btnNewDealConfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    confirmNewDeal();
                }
            });
    }

    public void confirmNewDeal(){
        if (TextUtils.isEmpty(etEstimatedTime.getText().toString()))
        {
            Toast.makeText(this,"Estimated time is empty!",Toast.LENGTH_SHORT).show();
        }
        else if(checkBox8to10.isChecked()==false && checkbox12to2.isChecked()==false && checkBox6to8.isChecked()==false && checkBox9to11.isChecked()==false){
            Toast.makeText(this,"Atleast one Deal Time must be checked.",Toast.LENGTH_SHORT).show();

        }
        else if(checkBoxMonday.isChecked()==false && checkBoxTuesday.isChecked()==false && checkBoxWednesday.isChecked()==false && checkBoxThursday.isChecked()==false && checkBoxFriday.isChecked()==false){
            Toast.makeText(this,"Atleast one Deal Day must be checked.",Toast.LENGTH_SHORT).show();

        }
        else if(TextUtils.isEmpty(etNewDealPrice.getText().toString())){
            Toast.makeText(this,"Per meal price must be inserted.",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(etNewDealSize.getText().toString())){
            Toast.makeText(this,"Per person meal size must be inserted.",Toast.LENGTH_SHORT).show();
        }
        else{

            String EstimateTime = etEstimatedTime.getText().toString();
            String NewDealPrice = etNewDealPrice.getText().toString();
            String NewDealSize = etNewDealSize.getText().toString();

            Boolean eightToten = false,tewlveTotwo = false,sixToeight = false, nineToeleven = false;

            String DealPrice = "Deal Price";
            String DealTime = "Deal Time";
            String DealDays = "Deal Days";

            Boolean Monday=false,Tuesday=false,Wednesday=false,Thursday = false, Friday = false;

            if(checkBox8to10.isChecked()==true){ eightToten =true;    }
            if(checkbox12to2.isChecked()==true){ tewlveTotwo =true;    }
            if(checkBox6to8.isChecked()==true){ sixToeight =true;    }
            if(checkBox9to11.isChecked()==true){ nineToeleven =true;    }

            if(checkBoxMonday.isChecked()==true){ Monday =true;    }
            if(checkBoxTuesday.isChecked()==true){ Tuesday =true;    }
            if(checkBoxWednesday.isChecked()==true){ Wednesday =true;    }
            if(checkBoxThursday.isChecked()==true){ Thursday =true;    }
            if(checkBoxFriday.isChecked()==true){ Friday =true;    }



            //firebaseDatabase dealprice = new firebaseDatabase(EstimateTime, NewDealPrice, NewDealSize);

            NewDeal_Database dealprice  =  new NewDeal_Database(EstimateTime, NewDealPrice, NewDealSize);

            //firebaseDatabase dealtime = new firebaseDatabase(eightToten, tewlveTotwo, sixToeight, nineToeleven);

            NewDeal_Database dealtime =  new NewDeal_Database(eightToten, tewlveTotwo, sixToeight,  nineToeleven);
            //firebaseDatabase dealdays = new firebaseDatabase (Monday,Tuesday, Wednesday, Thursday, Friday);

            NewDeal_Database dealdays =  new NewDeal_Database( Monday,  Tuesday,  Wednesday,  Thursday, Friday);

            dealPriceRefrence.child(id).child(DealId).setValue(dealprice);

            dealTimeRefrence.child(id).child(DealId).setValue(dealtime);

            dealDaysRefrence.child(id).child(DealId).setValue(dealdays);

            Toast.makeText(this,"Information Added",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(New_Deal_Time.this, Navigation_Drawer.class);
            startActivity(intent);

        }

    }
}
