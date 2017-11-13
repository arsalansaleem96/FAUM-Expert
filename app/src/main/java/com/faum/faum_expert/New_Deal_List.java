package com.faum.faum_expert;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.faum.faum_expert.New_Deal.Cooker_Deal;
import static com.faum.faum_expert.New_Deal.Deal;
import static com.faum.faum_expert.New_Deal.DealId;
import static com.faum.faum_expert.MainActivity.id;

public class New_Deal_List extends AppCompatActivity {

    ListView lvDealList;

    List<NewDeal_Database> dealList;

    //public static final String TAG = "New_Deal_List";

    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("Expert Deal Information");

    //DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
    //DatabaseReference ref = rootRef.child("faum-expert").child("Expert").child(id).child("Cooker Deals");
    //int count = 0;

    public static final String DEAL_NAME = "dealName";
    public static final String DEAL_CATEGORY = "newDealCategory";
    public static final String DEAL_ID = "DealId";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new__deal__list);

        lvDealList = (ListView)findViewById(R.id.lvDealList);

        dealList = new ArrayList<>();

        /*
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String dealName = ds.child("Deals Information").child("dealName").getValue(String.class);
                    String newDealCategory = ds.child("Deals Information").child("newDealCategory").getValue(String.class);
                    Log.d(TAG, dealName + " / " + newDealCategory);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        ref.addListenerForSingleValueEvent(eventListener);
        */

        lvDealList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                NewDeal_Database newDeal_database = dealList.get(i);

                Intent intent = new Intent(getApplicationContext(), New_Deal_Confirmation.class);


                intent.putExtra(DEAL_NAME,newDeal_database.getDealName());
                intent.putExtra(DEAL_CATEGORY,newDeal_database.getNewDealCategory());
                intent.putExtra(DEAL_ID,newDeal_database.getDealId());
                startActivity(intent);

            }
        });


    }
    @Override
    protected void onStart() {
        super.onStart();
        rootRef.child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dealList.clear();

                for(DataSnapshot dealSnapshot : dataSnapshot.getChildren()){
                        NewDeal_Database info = dealSnapshot.getValue(NewDeal_Database.class);
                        dealList.add(info);
                }
                DealList adapter = new DealList( New_Deal_List.this,dealList);
                lvDealList.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(New_Deal_List.this,"Databse error",Toast.LENGTH_SHORT).show();

            }
        });
    }
}
