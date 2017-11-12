package com.faum.faum_expert;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import static com.faum.faum_expert.MainActivity.id;

public class Contact_Infrormation extends Personal_Information{

    private EditText etLandline, etCell , etAddress;
    private Button btnNext;

    //private DatabaseReference cInformation;

    //Intent intent = getIntent();

    //String id = intent.getStringExtra(Personal_Information.id);
    //String t_name = intent.getStringExtra(Personal_Information.t_name);

    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("Expert Basic Information");
    //DatabaseReference personalRef = FirebaseDatabase.getInstance().getReference("Expert");
    DatabaseReference keyRefrence = rootRef.child(id);
    //DatabaseReference tableRefrence = keyRefrence.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_infrormation);

        etLandline = (EditText)findViewById(R.id.etLandline);
        etCell = (EditText)findViewById(R.id.etCell);
        etAddress = (EditText)findViewById(R.id.etAddres);
        btnNext = (Button)findViewById(R.id.btnNext);



        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addContactInformation();

            }
        });
    }

    private void addContactInformation(){
        String landline = etLandline.getText().toString();
        String cell = etCell.getText().toString();
        String address = etAddress.getText().toString();

        if(TextUtils.isEmpty(landline)){
            Toast.makeText(this,"Enter Landline number",Toast.LENGTH_LONG).show();
        }else if(TextUtils.isEmpty(cell)){
            Toast.makeText(this,"Enter Cel number",Toast.LENGTH_LONG).show();
        }else if(TextUtils.isEmpty(address)){
            Toast.makeText(this,"Enter your Address",Toast.LENGTH_LONG).show();
        }else{

            String tt_name = "Contact Information";

            Contact_Info info =new Contact_Info(landline,cell,address);
            //pInformation = FirebaseDatabase.getInstance().getReference("Expert").child(id);
            //pInformation.child(tt_name).setValue(info);
            keyRefrence.child(tt_name).setValue(info);

            Toast.makeText(this,"Information Added",Toast.LENGTH_LONG).show();
            startActivity(new Intent(Contact_Infrormation.this,MapsActivity.class));
            finish();
        }

    }
}
