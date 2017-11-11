package com.faum.faum_expert;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("Expert");




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new__deal__list);

        lvDealList = (ListView)findViewById(R.id.lvDealList);

        dealList = new ArrayList<>();


    }
/*
    rootRef.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            showData(dataSnapshot);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    });
    private void showData(DataSnapshot dataSnapshot){
        NewDeal_Database info = new NewDeal_Database();
        info.getDealName(dataSnapshot.);
        info.getNewDealCategory();

        ArrayList<String> array = new ArrayList<>();
        array.add(info.getDealName());
        array.add(info.getNewDealCategory());
        ArrayAdapter adatpter =  new ArrayAdapter(this,android.R.layout.simple_list_item_1,array);
        lvDealList.setAdapter(adatpter);
    }*/



    @Override
    protected void onStart() {
        super.onStart();

        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                dealList.clear();

                for(DataSnapshot dealSnapshot : dataSnapshot.getChildren()){

                    NewDeal_Database info = dealSnapshot.getValue(NewDeal_Database.class);

                    dealList.add(info);
                }

                DealList adapter = new DealList( New_Deal_List.this,dealList);
                lvDealList.setAdapter(adapter);


            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(New_Deal_List.this,"Databse error",Toast.LENGTH_SHORT).show();

            }
        });
    }
}
