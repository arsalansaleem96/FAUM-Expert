package com.faum.faum_expert;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;
import static com.faum.faum_expert.MainActivity.id;
import static com.faum.faum_expert.Personal_Information.t_name;


public class Social_Connect extends AppCompatActivity {

    private SignInButton mGoogleBtn;

    private LoginButton mFacebookBtn;

    private static final int RC_SIGN_IN = 1;

    private GoogleApiClient mGoogleApiClient;

    private FirebaseAuth mAuth; //for googe verification

    private FirebaseAuth.AuthStateListener mAuthListener;   //for googe verification

    private static final String TAG = "MAIN_ACTIVITY";  //for googe verification and facebook

    private ProgressDialog progressDialog;  //for googe verification

    private CallbackManager callbackManager;
    FirebaseUser user;

    DatabaseReference Userid = FirebaseDatabase.getInstance().getReference("Expert");

    public static final String uidemail = "Email and Id";
    public static String email;
    public static Boolean facebookCheck=false;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());     //for facebook
        setContentView(R.layout.activity_social__connect);

        mAuth= FirebaseAuth.getInstance();  //for googe verification

        progressDialog = new ProgressDialog(this);
        user = FirebaseAuth.getInstance().getCurrentUser();

        mAuthListener = new FirebaseAuth.AuthStateListener() { //for googe verification
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {


                if(firebaseAuth.getCurrentUser() != null){
                    id= firebaseAuth.getCurrentUser().getUid() ;
                    email = firebaseAuth.getCurrentUser().getEmail();
                    //Userid.push().toString();

                    Userid.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                // user already exists in db
                                Toast.makeText(Social_Connect.this,"Already user exits",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Social_Connect.this,Navigation_Drawer.class));

                            }else{

                                UserIdSet userIdSet = new UserIdSet(email,id);
                                Userid.child(id).child(uidemail).setValue(userIdSet);
                                Toast.makeText(Social_Connect.this,"Id and email exits now.",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Social_Connect.this,Personal_Information.class));

                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });


                }


            }
        };


        mFacebookBtn = (LoginButton)findViewById(R.id.login_button);    //for facebook
        mGoogleBtn=(SignInButton)findViewById(R.id.googlebtn);  //for googe verification

        callbackManager = CallbackManager.Factory.create(); //for facebook

        mFacebookBtn.setReadPermissions("email", "public_profile");

        mFacebookBtn.registerCallback(callbackManager, new FacebookCallback<LoginResult>() { //facebook
            @Override
            public void onSuccess(LoginResult loginResult) {
                if(user != null){
                    facebookCheck = true;
                    id= user.getUid() ;
                    email = user.getEmail();
                    //Userid.push().toString();

                    Userid.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                // user already exists in db
                                Toast.makeText(Social_Connect.this,"Already user exits",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Social_Connect.this,Navigation_Drawer.class));

                            }else{

                                UserIdSet userIdSet = new UserIdSet(email,id);
                                Userid.child(id).child(uidemail).setValue(userIdSet);
                                Toast.makeText(Social_Connect.this,"Id and email exits now.",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Social_Connect.this,Personal_Information.class));

                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });


                }
                //startActivity(new Intent(Social_Connect.this,Navigation_Drawer.class));
                Log.d(TAG, "facebook:onSuccess:" + loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());


            }

            @Override
            public void onCancel() {
                Log.d(TAG, "facebook:onCancel");
                // ...
                Toast.makeText(Social_Connect.this,"You canceled facebook login",Toast.LENGTH_LONG).show();

            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "facebook:onError", error);
                // ..

            }
        });

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)  //for googe verification
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(getApplicationContext())     //for googe verification
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                        Toast.makeText(Social_Connect.this,"You Got an error",Toast.LENGTH_LONG).show();

                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        mGoogleBtn.setOnClickListener(new View.OnClickListener() {      //for googe verification
            @Override
            public void onClick(View view) {
                signIn();
            }
        });

        }

    @Override
    protected void onStart() {
        super.onStart();

        mAuth.addAuthStateListener(mAuthListener);      //for googe verification

        FirebaseUser currentUser = mAuth.getCurrentUser(); //for facebook


    }

    private void signIn() { //for googe verification
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
        progressDialog.setMessage("Searching accounts.");
        progressDialog.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {    //for googe verification and facebook
        super.onActivityResult(requestCode, resultCode, data); //for google
        callbackManager.onActivityResult(requestCode, resultCode, data); //for facebook
        progressDialog.hide();

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {    //for googe verification

            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);   //for googe verification
            if (result.isSuccess()) {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();    //for googe verification
                firebaseAuthWithGoogle(account);
            } else {
                // Google Sign In failed, update UI appropriately
                // ...
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) { //for googe verification


        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithCredential:onComplete" + task.isSuccessful());
                        FirebaseUser user = mAuth.getCurrentUser();



                        if(!task.isSuccessful()){       //for googe verification
                            Log.w(TAG, "signInWithCredential", task.getException());
                            Toast.makeText(Social_Connect.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        /*if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(Social_Connect.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }*/

                        // ...
                    }
                });
    }

    private void handleFacebookAccessToken(AccessToken token) { //for facebook
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithCredential:onComplete" + task.isSuccessful());
                        facebookCheck = true;

                        if(!task.isSuccessful()){       //for facebook verification
                            Log.w(TAG, "signInWithCredential", task.getException());
                            Toast.makeText(Social_Connect.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        /*if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(Social_Connect.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }*/

                        // ...
                    }
                });
    }


}
