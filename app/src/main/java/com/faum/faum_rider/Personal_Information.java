package com.faum.faum_rider;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.faum.faum_expert.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.faum.faum_rider.Main3Activity.rid;

public class Personal_Information extends AppCompatActivity {

    private EditText etFName, etLName , etEmail;
    private Button btnConfirm;

    protected DatabaseReference pInformation;
    public static String t_name = "Personal Information";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal__information);

        etFName = (EditText)findViewById(R.id.etFName);
        etLName = (EditText)findViewById(R.id.etLName);
        etEmail = (EditText)findViewById(R.id.etEmail);
        btnConfirm = (Button)findViewById(R.id.btnConfirm);

        pInformation = FirebaseDatabase.getInstance().getReference("Rider Basic Information");

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPersonalInformation();

            }
        });
    }

    private void addPersonalInformation(){
        String firstname = etFName.getText().toString();
        String lastname = etLName.getText().toString();
        String email = etEmail.getText().toString();

        if(TextUtils.isEmpty(firstname)){
            Toast.makeText(this,"Enter your First name",Toast.LENGTH_LONG).show();
        }else if(TextUtils.isEmpty(lastname)){
            Toast.makeText(this,"Enter your Last name",Toast.LENGTH_LONG).show();
        }
        else{

            Intent intent = new Intent(Personal_Information.this,Contact_Infrormation.class);
            //id = pInformation.push().getKey();

            //intent.putExtra(id,id);



            //intent.putExtra(t_name,t_name);
            
            User_Infromation info =new User_Infromation(firstname,lastname);
            pInformation.child(rid).child(t_name).setValue(info);

            Toast.makeText(this,"Information Added",Toast.LENGTH_LONG).show();
            startActivity(intent);
            finish();
        }

    }
}
