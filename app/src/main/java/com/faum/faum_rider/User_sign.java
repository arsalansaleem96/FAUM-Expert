package com.faum.faum_rider;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.faum.faum_expert.R;
import com.faum.faum_expert.User_Select_SignIn;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;

import static com.faum.faum_expert.Social_Connect.facebookCheck;

public class User_sign extends AppCompatActivity {

    private Button mLogOutBtn;

    private FirebaseAuth mAuth;

    private FirebaseAuth.AuthStateListener mAuthListener;

    private GoogleApiClient mGoogleApiClient;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if(firebaseAuth.getCurrentUser() == null){
                    startActivity(new Intent(User_sign.this,User_Select_SignIn.class));
                }
            }
        };

        mLogOutBtn= (Button) findViewById(R.id.btnLogout);

        mLogOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(facebookCheck==true) {
                    Toast.makeText(User_sign.this,"check",Toast.LENGTH_SHORT).show();
                    LoginManager.getInstance().logOut();
                    FirebaseAuth.getInstance().signOut();
                }else{
                    mAuth.signOut(); // for google
                    FirebaseAuth.getInstance().signOut(); //for facebook
                }
                //startActivity(new Intent(User_sign.this,MainActivity.class));

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        mAuth.addAuthStateListener(mAuthListener);


    }


}
