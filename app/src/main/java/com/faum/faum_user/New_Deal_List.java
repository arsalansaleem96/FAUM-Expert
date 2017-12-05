package com.faum.faum_user;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.faum.faum_expert.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class New_Deal_List extends AppCompatActivity {

    ListView lvDealList;

    List<NewDeal_Database> dealList;

    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("Expert Deal Information");
    DatabaseReference dealConfirmationRefrence = FirebaseDatabase.getInstance().getReference("Expert Deal Confirmation");
    DatabaseReference dealPricesRefrence = FirebaseDatabase.getInstance().getReference("Expert Deal Prices");
    DatabaseReference dealTimeRefrence = FirebaseDatabase.getInstance().getReference("Expert Deal Times");
    DatabaseReference dealDaysRefrence = FirebaseDatabase.getInstance().getReference("Expert Deal Days");

    private SharedPreferences mPrefrences;
    private SharedPreferences.Editor mEditor;

    public static String DEAL_NAME = "";
    public static String DEAL_CATEGORY = "";
    public static String DEAL_ID = "";
    public static String DEAL_DISH_NAME = "";
    public static String DEAL_DESCRIPTION = "";
    //public static String COOKER_ID = "";

    public static String DEAL_ESTIMATEDTIME = "";
    public static String DEAL_PRICE = " ";
    public static String DEAL_SIZE = "";

    public static String DEAL_TIME_EIGHTTOTEN ;
    public static String DEAL_TIME_TWELVETOTWO = "";
    public static String DEAL_TIME_SIXTOEIGHT = "";
    public static String DEAL_TIME_NINETOELEVEN = "";

    public static String DEAL_DAYS_MONDAY  = "";
    public static String DEAL_DAYS_TUESDAY = "";
    public static String DEAL_DAYS_WEDNESDAY = "";
    public static String DEAL_DAYS_THURSDAY = "";
    public static String DEAL_DAYS_FRIDAY = "";






    Boolean check=false;

    String Dealid;

    ArrayList mylist;

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

        mPrefrences = PreferenceManager.getDefaultSharedPreferences(this);

        mEditor = mPrefrences.edit();



        lvDealList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                NewDeal_Database newDeal_database = dealList.get(i);

                Intent intent = new Intent(getApplicationContext(), Deal_Detail_one.class);

                mEditor.putString(getString(R.string.COOKER_ID), newDeal_database.getuId());
                mEditor.commit();
                mEditor.putString(getString(R.string.DEAL_ID), newDeal_database.getDealId());
                mEditor.commit();

                //Toast.makeText(getApplicationContext(),newDeal_database.getNewDealPrice(),Toast.LENGTH_SHORT).show();

                passingtoDealInfoValues(newDeal_database.getDealId());
                passingtoDealPricesValues(newDeal_database.getDealId());
                passingtoDealTimesValues(newDeal_database.getDealId());
                passingtoDealDaysValues(newDeal_database.getDealId());
                //passingtoDealDaysCheckValues(newDeal_database.getDealId());


                //intent.putExtra(DEAL_NAME,newDeal_database.getDealName());
                //intent.putExtra(DEAL_CATEGORY,newDeal_database.getNewDealCategory());
                //intent.putExtra(DEAL_ID,newDeal_database.getDealId());
                //intent.putExtra(DEAL_DISH_NAME,newDeal_database.getDishName());
                //intent.putExtra(DEAL_DESCRIPTION,newDeal_database.getDealDescription());

                //intent.putExtra(DEAL_ESTIMATEDTIME,newDeal_database.getEstimateTime());
                //intent.putExtra(DEAL_PRICE,newDeal_database.getNewDealPrice());
                //intent.putExtra(DEAL_SIZE,newDeal_database.getNewDealSize());

                //Intent intent1 = intent.putExtra(String.valueOf(DEAL_TIME_EIGHTTOTEN), newDeal_database.getEightToten());
                //Intent intent2 = intent.putExtra(DEAL_TIME_TWELVETOTWO,newDeal_database.getTwelveTotwo());
                //Intent intent3 = intent.putExtra(DEAL_TIME_SIXTOEIGHT,newDeal_database.getSixToeight().toString());
                //intent.putExtra(DEAL_TIME_NINETOELEVEN,newDeal_database.getNineToeleven().toString());

                //intent.putExtra(DEAL_DAYS_MONDAY,newDeal_database.getMonday().toString());
                //intent.putExtra(DEAL_DAYS_TUESDAY,newDeal_database.getTuesday().toString());
                //intent.putExtra(DEAL_DAYS_WEDNESDAY,newDeal_database.getWednesday().toString());
                //intent.putExtra(DEAL_DAYS_THURSDAY,newDeal_database.getThursday().toString());
                //intent.putExtra(DEAL_DAYS_FRIDAY,newDeal_database.getFriday().toString());

                //startActivity(intent1);
                startActivity(intent);
            }
        });
    }

    private void passingtoDealInfoValues(final String DealId){
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dealSnapshot : dataSnapshot.getChildren()) {
                    try{
                        DEAL_NAME = dealSnapshot.child(DealId).getValue(NewDeal_Database.class).getDealName();
                        DEAL_CATEGORY = dealSnapshot.child(DealId).getValue(NewDeal_Database.class).getNewDealCategory();
                        DEAL_DISH_NAME = dealSnapshot.child(DealId).getValue(NewDeal_Database.class).getDishName();
                        DEAL_DESCRIPTION = dealSnapshot.child(DealId).getValue(NewDeal_Database.class).getDealDescription();
                    }catch (Exception e){
                        e.printStackTrace();
                    }finally {
                        mEditor.putString(getString(R.string.DEAL_NAME), DEAL_NAME);
                        mEditor.commit();

                        mEditor.putString(getString(R.string.DEAL_CATEGORY), DEAL_CATEGORY);
                        mEditor.commit();

                        mEditor.putString(getString(R.string.DEAL_DISH_NAME), DEAL_DISH_NAME);
                        mEditor.commit();

                        mEditor.putString(getString(R.string.DEAL_DESCRIPTION), DEAL_DESCRIPTION);
                        mEditor.commit();
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(New_Deal_List.this,"Databse error",Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void passingtoDealPricesValues(final String DealId){
        //Toast.makeText(getApplicationContext(),DealId,Toast.LENGTH_SHORT).show();

        dealPricesRefrence.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dealSnapshot : dataSnapshot.getChildren()) {
                    try{
                        //Toast.makeText(New_Deal_List.this,dealSnapshot.child(DealId).toString(),Toast.LENGTH_SHORT).show();
                        DEAL_ESTIMATEDTIME = dealSnapshot.child(DealId).getValue(NewDeal_Database.class).getEstimateTime();
                        DEAL_PRICE = dealSnapshot.child(DealId).getValue(NewDeal_Database.class).getNewDealPrice();
                        DEAL_SIZE = dealSnapshot.child(DealId).getValue(NewDeal_Database.class).getNewDealSize();



                        //Toast.makeText(getApplicationContext(),DEAL_ESTIMATEDTIME,Toast.LENGTH_SHORT).show();
                        //Toast.makeText(getApplicationContext(),DEAL_PRICE,Toast.LENGTH_LONG).show();
                        //Toast.makeText(getApplicationContext(),DEAL_SIZE,Toast.LENGTH_SHORT).show();

                        //Toast.makeText(getApplicationContext(),DealId,Toast.LENGTH_SHORT).show();
                    }catch (Exception e){
                        e.printStackTrace();
                    }finally {
                        DEAL_ESTIMATEDTIME="60";
                        DEAL_PRICE = "700";
                        DEAL_SIZE = "10";
                        mEditor.putString(getString(R.string.DEAL_ESTIMATEDTIME), DEAL_ESTIMATEDTIME);
                        mEditor.commit();

                        mEditor.putString(getString(R.string.DEAL_PRICE), DEAL_PRICE);
                        mEditor.commit();

                        mEditor.putString(getString(R.string.DEAL_SIZE), DEAL_SIZE);
                        mEditor.commit();
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(New_Deal_List.this,"Databse error",Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void passingtoDealTimesValues(final String DealId){


        dealTimeRefrence.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dealSnapshot : dataSnapshot.getChildren()) {
                    try{

                        //Toast.makeText(New_Deal_List.this,dealSnapshot.,Toast.LENGTH_SHORT).show();
                        if(dealSnapshot.child(DealId).getValue(NewDeal_Database.class).getEightToten() == true){
                            DEAL_TIME_EIGHTTOTEN = "Available";
                        }else{
                            DEAL_TIME_EIGHTTOTEN = "Not-Available";
                        }
                        if(dealSnapshot.child(DealId).getValue(NewDeal_Database.class).getTwelveTotwo() == true){
                            DEAL_TIME_TWELVETOTWO = "Available";

                        }else{
                            DEAL_TIME_TWELVETOTWO = "Not-Available";
                        }
                        if(dealSnapshot.child(DealId).getValue(NewDeal_Database.class).getSixToeight() == true){
                            DEAL_TIME_SIXTOEIGHT = "Available";
                        }else{
                            DEAL_TIME_SIXTOEIGHT = "Not-Available";
                        }
                        if(dealSnapshot.child(DealId).getValue(NewDeal_Database.class).getNineToeleven() == true){
                            DEAL_TIME_NINETOELEVEN = "Available";
                        }else{
                            DEAL_TIME_NINETOELEVEN = "Not-Available";
                        }

                        //Toast.makeText(getApplicationContext(),DEAL_TIME_EIGHTTOTEN,Toast.LENGTH_SHORT).show();
                        //Toast.makeText(getApplicationContext(),DEAL_TIME_TWELVETOTWO,Toast.LENGTH_SHORT).show();
                        //Toast.makeText(getApplicationContext(),DEAL_TIME_SIXTOEIGHT,Toast.LENGTH_SHORT).show();
                        //Toast.makeText(getApplicationContext(),DEAL_TIME_NINETOELEVEN,Toast.LENGTH_SHORT).show();

                    }catch (Exception e){
                        e.printStackTrace();
                    }finally {
                        mEditor.putString(getString(R.string.DEAL_TIME_EIGHTTOTEN), DEAL_TIME_EIGHTTOTEN);
                        mEditor.commit();

                        mEditor.putString(getString(R.string.DEAL_TIME_TWELVETOTWO), DEAL_TIME_TWELVETOTWO);
                        mEditor.commit();

                        mEditor.putString(getString(R.string.DEAL_TIME_SIXTOEIGHT), DEAL_TIME_SIXTOEIGHT);
                        mEditor.commit();

                        mEditor.putString(getString(R.string.DEAL_TIME_NINETOELEVEN), DEAL_TIME_NINETOELEVEN);
                        mEditor.commit();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private  void passingtoDealDaysValues(final String DealId){

        dealDaysRefrence.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dealSnapshot : dataSnapshot.getChildren())
                {
                    NewDeal_Database newDeal_database = dealSnapshot.child(DealId).getValue(NewDeal_Database.class);

                    try{
                        //Toast.makeText(New_Deal_List.this,dealSnapshot.child(DealId).toString(),Toast.LENGTH_SHORT).show();
                        if(newDeal_database.getMonday()== true ){
                            Toast.makeText(getApplicationContext(),DEAL_DAYS_MONDAY,Toast.LENGTH_SHORT).show();
                            DEAL_DAYS_MONDAY = "Available";
                        }else{
                            Toast.makeText(getApplicationContext(),DEAL_DAYS_MONDAY,Toast.LENGTH_SHORT).show();
                            DEAL_DAYS_MONDAY = "Not-Available";
                        }
                        if(dealSnapshot.child(DealId).getValue(NewDeal_Database.class).getTuesday() == true){
                            DEAL_DAYS_TUESDAY = "Available";
                            Toast.makeText(getApplicationContext(),DEAL_DAYS_TUESDAY,Toast.LENGTH_SHORT).show();

                        }else{
                            DEAL_DAYS_TUESDAY = "Not-Available";
                            Toast.makeText(getApplicationContext(),DEAL_DAYS_TUESDAY,Toast.LENGTH_SHORT).show();
                        }
                        if(dealSnapshot.child(DealId).getValue(NewDeal_Database.class).getWednesday() == true){
                            DEAL_DAYS_WEDNESDAY = "Available";
                        }else{
                            DEAL_DAYS_WEDNESDAY = "Not-Available";
                        }
                        if(dealSnapshot.child(DealId).getValue(NewDeal_Database.class).getThursday() == true){
                            DEAL_DAYS_THURSDAY = "Available";
                        }else{
                            DEAL_DAYS_THURSDAY = "Not-Available";
                        }
                        if(dealSnapshot.child(DealId).getValue(NewDeal_Database.class).getFriday() == true){
                            DEAL_DAYS_FRIDAY = "Available";
                        }else{
                            DEAL_DAYS_FRIDAY = "Not-Available";
                        }


                        //Toast.makeText(getApplicationContext(),DEAL_DAYS_TUESDAY,Toast.LENGTH_SHORT).show();
                        //Toast.makeText(getApplicationContext(),DEAL_DAYS_WEDNESDAY,Toast.LENGTH_SHORT).show();
                        //Toast.makeText(getApplicationContext(),DEAL_DAYS_FRIDAY,Toast.LENGTH_SHORT).show();

                    }catch (Exception e){
                        e.printStackTrace();
                    }finally {
                        DEAL_DAYS_MONDAY = "Available";
                        DEAL_DAYS_TUESDAY = "Available";
                        DEAL_DAYS_WEDNESDAY = "Not-Available";
                        DEAL_DAYS_THURSDAY = "Not-Available";
                        DEAL_DAYS_FRIDAY = "Not-Available";
                        mEditor.putString(getString(R.string.DEAL_DAYS_MONDAY), DEAL_DAYS_MONDAY);
                        mEditor.commit();

                        mEditor.putString(getString(R.string.DEAL_DAYS_TUESDAY), DEAL_DAYS_TUESDAY);
                        mEditor.commit();

                        mEditor.putString(getString(R.string.DEAL_DAYS_WEDNESDAY), DEAL_DAYS_WEDNESDAY);
                        mEditor.commit();

                        mEditor.putString(getString(R.string.DEAL_DAYS_THURSDAY), DEAL_DAYS_THURSDAY);
                        mEditor.commit();

                        mEditor.putString(getString(R.string.DEAL_DAYS_FRIDAY), DEAL_DAYS_THURSDAY);
                        mEditor.commit();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        mylist =  new ArrayList<String>();
        dealConfirmationRefrence.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dealSnapshot : dataSnapshot.getChildren())
                    for(DataSnapshot datas  : dealSnapshot.getChildren())
                    {
                        try {
                            check  = datas.getValue(NewDeal_Database.class).getCheckBoxConfirmation();
                        } catch (Exception e) {
                            e.printStackTrace();

                        } finally {

                            if(check==true){
                                Dealid =   datas.getValue(NewDeal_Database.class).getDealId();
                                mylist.addAll(Arrays.asList(Dealid));
                            }
                        }
                    }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dealList.clear();
                for(DataSnapshot dealSnapshot : dataSnapshot.getChildren()) {
                    for (DataSnapshot datas : dealSnapshot.getChildren()) {
                        if(mylist.contains(datas.getValue(NewDeal_Database.class).getDealId())) {

                            NewDeal_Database info = datas.getValue(NewDeal_Database.class);
                            dealList.add(info);
                        }
                    }
                    DealList adapter = new DealList(New_Deal_List.this, dealList);
                    lvDealList.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(New_Deal_List.this,"Databse error",Toast.LENGTH_SHORT).show();

            }
        });

    }
}
