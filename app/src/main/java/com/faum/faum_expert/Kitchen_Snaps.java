package com.faum.faum_expert;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Kitchen_Snaps extends AppCompatActivity {

    private ImageView ivKitchen1,ivKitchen2,ivKitchen3;
    private Button btnCapture1,btnCapture2,btnCapture3;
    private TextView tvKitchenSnaps;

    private StorageReference snapsStorage;

    private static final int CAMERA_REQUEST_CODE = 1;

    private ProgressDialog mProgress;

    private Uri filepath;



    String mCurrentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File...
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        getApplicationContext().getPackageName() + ".provider",
                        photoFile);

                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, CAMERA_REQUEST_CODE);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kitchen_snaps);

        snapsStorage = FirebaseStorage.getInstance().getReference();


        ivKitchen1 = (ImageView)findViewById(R.id.ivKitchen1);

        btnCapture1 = (Button)findViewById(R.id.btnCapture1);

        mProgress = new ProgressDialog(this);

        btnCapture1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(intent, CAMERA_REQUEST_CODE);*/

                dispatchTakePictureIntent();

            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK){
            mProgress.setMessage("Uploading...");
            mProgress.show();
            Uri uri = data.getData();

            final StorageReference filepath = snapsStorage.child("Photos").child(uri.getLastPathSegment());
            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(Kitchen_Snaps.this, "Upload Successful!", Toast.LENGTH_SHORT).show();
                    mProgress.dismiss();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Kitchen_Snaps.this, "Upload Failed!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}



        /*if(requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK ){

            //Bitmap bitmap = (Bitmap) data.getExtras().get("data");



            try {
                filepath = data.getData();

                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filepath);
                ivKitchen1.setImageBitmap(bitmap);
            }catch (IOException e) {

                e.printStackTrace();
            }


            if(filepath != null){

                mProgress.setTitle("Uploading ... ");
                mProgress.show();
                //filepath = Uri.fromFile(new File("path/to/images/rivers.jpg"));
                StorageReference riversRef = snapsStorage.child("images/rivers.jpg");

                riversRef.putFile(filepath)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                // Get a URL to the uploaded content
                                mProgress.dismiss();

                                Uri downloaduri = taskSnapshot.getDownloadUrl();

                                Picasso.with(Kitchen_Snaps.this).load(downloaduri).fit().centerCrop().into(ivKitchen1);

                                Toast.makeText(Kitchen_Snaps.this,"Uploading Finished ... ",Toast.LENGTH_LONG).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                mProgress.dismiss();
                                Toast.makeText(Kitchen_Snaps.this,exception.getMessage(),Toast.LENGTH_LONG).show();
                            }
                        })
                        .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                                double progress = (100.0 * taskSnapshot.getBytesTransferred())/taskSnapshot.getTotalByteCount();
                                mProgress.setMessage(((int) progress)+ " % Uploading...");
                            }
                        });
            }else{
                Toast.makeText(Kitchen_Snaps.this,"Filepath not found. ",Toast.LENGTH_LONG).show();

            }*/



            /*Uri uri = data.getData();

            StorageReference fielpath = snapsStorage.child("Expert").child(uri.getLastPathSegment());

            fielpath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    mProgress.hide();

                    Uri downloaduri = taskSnapshot.getDownloadUrl();

                    Picasso.with(Kitchen_Snaps.this).load(downloaduri).fit().centerCrop().into(ivKitchen1);

                    Toast.makeText(Kitchen_Snaps.this,"Uploading Finished ... ",Toast.LENGTH_LONG).show();
                }
            });*/
