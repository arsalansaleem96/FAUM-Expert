package com.faum.faum_expert;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RidersIDS extends AppCompatActivity {

    DatabaseReference riderLocation = FirebaseDatabase.getInstance().getReference("Rider Location Information");
    List<String> riderIDS;
    TextView riderID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riders_ids2);

        riderIDS = new ArrayList<>();

        riderLocation.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot riderSnapshot: dataSnapshot.getChildren()){
                    riderIDS.add(riderSnapshot.toString());
                    //riderID.setText(riderIDS.toString());

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        /*for(int i=0;i<riderIDS.size();i++){

            riderID.setText(riderIDS.toString());
            //Log.d("list", riderIDS.toString());
        }*/
    }
}
