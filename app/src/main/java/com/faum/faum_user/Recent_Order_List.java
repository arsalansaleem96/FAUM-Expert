package com.faum.faum_user;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.faum.faum_expert.*;
import com.faum.faum_expert.NewDeal_Database;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.faum.faum_user.Main2Activity.uid;

public class Recent_Order_List extends AppCompatActivity {

    ListView lvDealList;

    List<Confirm_Order_Database> dealList;

    DatabaseReference userOrderConfirmedRefrence = FirebaseDatabase.getInstance().getReference("User Confirmed Order");

    //public static final String DEAL_NAME="";
    //public static final String DEAL_CATEGORY="";
    //public static final String ORDER_PRICE="";
    //public static final String ORDER_QTY="";
    //public static final String COOKER_ID="";
    //public static final String USER_ID="";
    //public static final String ORDER_ID="";

    private SharedPreferences mPrefrences;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent__order__list);

        lvDealList = (ListView)findViewById(R.id.lvRecentOrder);

        dealList = new ArrayList<>();

        mPrefrences = PreferenceManager.getDefaultSharedPreferences(this);

        mEditor = mPrefrences.edit();

        lvDealList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Confirm_Order_Database confirm_order_database = dealList.get(i);

                Intent intent = new Intent(getApplicationContext(), Recent_Order_Data.class);


                //intent.putExtra(DEAL_NAME,confirm_order_database.getDealName());
                mEditor.putString(getString(R.string.DEAL_NAME), confirm_order_database.getDealName());
                mEditor.commit();
                //intent.putExtra(DEAL_CATEGORY,confirm_order_database.getDealCategory());
                mEditor.putString(getString(R.string.DEAL_CATEGORY), confirm_order_database.getDealCategory());
                mEditor.commit();
                //intent.putExtra(ORDER_PRICE,confirm_order_database.getOrderPrice());
                mEditor.putString(getString(R.string.ORDER_PRICE), confirm_order_database.getOrderPrice());
                mEditor.commit();
                //intent.putExtra(ORDER_QTY,confirm_order_database.getOrderQty());
                mEditor.putString(getString(R.string.ORDER_QTY), confirm_order_database.getOrderQty());
                mEditor.commit();


                //intent.putExtra(ORDER_ID,confirm_order_database.getOrderID());
                //intent.putExtra(COOKER_ID,confirm_order_database.getCookerID());
                //intent.putExtra(USER_ID,confirm_order_database.getUserID());

                //Toast.makeText(Recent_Order_List.this,DEAL_NAME, Toast.LENGTH_SHORT).show();
                //Toast.makeText(Recent_Order_List.this,confirm_order_database.getOrderPrice(), Toast.LENGTH_SHORT).show();
                //Toast.makeText(Recent_Order_List.this,confirm_order_database.getCookerID(), Toast.LENGTH_SHORT).show();
                startActivity(intent);

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        userOrderConfirmedRefrence.child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dealList.clear();

                for(DataSnapshot dealSnapshot : dataSnapshot.getChildren()){
                    Confirm_Order_Database info = dealSnapshot.getValue(Confirm_Order_Database.class);
                    dealList.add(info);
                }
                RecentList adapter = new RecentList( Recent_Order_List.this,dealList);
                lvDealList.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Recent_Order_List.this,"Databse error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
