package com.faum.faum_user;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.faum.faum_expert.R;
import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.faum.faum_user.Main2Activity.uid;
import static com.faum.faum_user.MapsActivityForCooker.cookerLocation;
import static com.faum.faum_user.MapsActivityForCooker.userLocation;

public class Deal_Invoice extends AppCompatActivity {

    Button btnCoOrder,btnCall,btnText,btnCalculate;
    EditText etQty;
    TextView tvTotal;
    int userQty;
    int totalPrice;
    int NewDealPrice;
    DatabaseReference userOrderRefrence = FirebaseDatabase.getInstance().getReference("Confirmed Order");

    DatabaseReference expertContactRefrence = FirebaseDatabase.getInstance().getReference("Expert Basic Information");

    SharedPreferences mPrefrences;

    GeoFire geoFireCooker = new GeoFire(userOrderRefrence);
    GeoFire geoFireUser = new GeoFire(userOrderRefrence);

    String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal__invoice);

        btnCoOrder = (Button)findViewById(R.id.btnCoOrder);
        btnCall = (Button)findViewById(R.id.btnCall);
        btnText = (Button)findViewById(R.id.btnText);
        btnCalculate = (Button)findViewById(R.id.btnCalculate);
        tvTotal = (TextView)findViewById(R.id.tvTotal);
        etQty = (EditText)findViewById(R.id.etQty);

        mPrefrences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor mEditor =  mPrefrences.edit();

        int  EstimateTime = Integer.parseInt(mPrefrences.getString(getString(R.string.DEAL_ESTIMATEDTIME)," "));
        final int  NewDealSize = Integer.parseInt(mPrefrences.getString(getString(R.string.DEAL_SIZE)," "));

        NewDealPrice = Integer.parseInt(mPrefrences.getString(getString(R.string.DEAL_PRICE)," "));

        etQty.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                try {
                    userQty = Integer.parseInt(etQty.getText().toString().trim());

                }catch (Exception e){
                    e.printStackTrace();

                }finally {
                    if(userQty>NewDealSize){
                        Toast.makeText(getApplicationContext(),"Max Qty is : "+ NewDealSize,Toast.LENGTH_LONG).show();
                        btnCoOrder.setVisibility(View.INVISIBLE);

                    }else{

                        totalPrice = (NewDealPrice * userQty);
                        tvTotal.setText(String.valueOf("Rs " + totalPrice));
                        btnCoOrder.setVisibility(View.VISIBLE);
                    }

                }

            }
        });






        btnCoOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvTotal.setVisibility(View.INVISIBLE);
                etQty.setVisibility(View.INVISIBLE);
                btnCoOrder.setVisibility(View.INVISIBLE);
                btnCalculate.setVisibility(View.INVISIBLE);

                btnCall.setVisibility(View.VISIBLE);
                btnText.setVisibility(View.VISIBLE);

                String orderID = userOrderRefrence.push().getKey();
                String cookerID = mPrefrences.getString(getString(R.string.COOKER_ID)," ");
                String userID = uid;
                String dealID = mPrefrences.getString(getString(R.string.DEAL_ID)," ");
                String orderPrice = String.valueOf(totalPrice);
                String orderQty = etQty.getText().toString();
                String dealName = mPrefrences.getString(getString(R.string.DEAL_NAME)," ");
                String dealCategory = mPrefrences.getString(getString(R.string.DEAL_CATEGORY)," ");

                Confirm_Order_Database data = new Confirm_Order_Database(orderID,cookerID,userID,dealID,orderPrice,orderQty,dealName,dealCategory);

                userOrderRefrence.child(orderID).setValue(data);



                geoFireUser.setLocation(userID,new GeoLocation(userLocation.latitude,userLocation.longitude));
                geoFireCooker.setLocation(cookerID,new GeoLocation(cookerLocation.latitude,cookerLocation.longitude));

                Toast.makeText(getApplicationContext(),"Order Confirmed",Toast.LENGTH_LONG).show();
            }
        });
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                expertContactRefrence.child(mPrefrences.getString(getString(R.string.COOKER_ID)," ")).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot datas :dataSnapshot.getChildren()){
                            Toast.makeText(getApplicationContext(),datas.toString(),Toast.LENGTH_LONG).show();
                            phoneNumber = datas.getValue(Contact_Info.class).getCell();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + phoneNumber));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        btnText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Deal_Invoice.this,Navigation_Drawer_User.class));
                finish();
            }
        });

        //Toast.makeText(getApplicationContext(),String.valueOf(cross),Toast.LENGTH_LONG).show();


    }
}
