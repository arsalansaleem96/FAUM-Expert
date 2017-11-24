package com.faum.faum_expert;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.faum.faum_user.Main2Activity;

import java.nio.BufferUnderflowException;

public class User_Select_SignIn extends AppCompatActivity {

    Button btnUser,btnCooker,btnDriver;
    Boolean flagUser = false,flagDriver = false , flagCooker = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__select__sign_in);

        btnUser = (Button)findViewById(R.id.btnUser);
        btnCooker = (Button)findViewById(R.id.BtnCooker);
        btnDriver = (Button)findViewById(R.id.btnDriver);


        btnCooker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(User_Select_SignIn.this,MainActivity.class);
                flagCooker = true;
                startActivity(intent);
            }
        });

        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(User_Select_SignIn.this, Main2Activity.class);
                flagUser = true;
                startActivity(intent);
            }
        });

        btnDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }
}
