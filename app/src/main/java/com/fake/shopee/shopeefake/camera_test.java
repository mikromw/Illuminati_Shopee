package com.fake.shopee.shopeefake;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.fake.shopee.shopeefake.Main_pages.loginactivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;


public class camera_test extends Activity {
    private FirebaseAuth mAuth;
    private static final int CAMERA_REQUEST = 1888;
    private ImageView imageView;
    ImageView done;
    Uri targetUri=null;
    private StorageReference mStorageRef;
    EditText nameproduct;
    byte[] tempupload=null;

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
        if (currentUser==null){
            Intent i = new Intent(camera_test.this,loginactivity.class);
            startActivity(i);
            finish();
        }
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_test);

        mAuth = FirebaseAuth.getInstance();
        nameproduct=(EditText) findViewById(R.id.namaprodukcamera);
        done = (ImageButton) findViewById(R.id.donecamera);
        this.imageView = (ImageView)this.findViewById(R.id.imageView1);

        if(mAuth.getCurrentUser()==null){

        }
        else {
            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, CAMERA_REQUEST);
        }
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                YourAsyncTask b = new YourAsyncTask(camera_test.this);
                b.execute();
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
            imageView.setDrawingCacheEnabled(true);
            imageView.buildDrawingCache();
            Bitmap bitmap = imageView.getDrawingCache();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            tempupload = baos.toByteArray();
        }
    }
    private class YourAsyncTask extends AsyncTask<Void, Void, Void> {
        private ProgressDialog dialog;

        public YourAsyncTask(camera_test a) {
            dialog = new ProgressDialog(a, R.style.AppCompatAlertDialogStyle);
        }

        @Override
        protected void onPreExecute() {
            dialog.setMessage("Processing....");
            dialog.show();
        }

        protected Void doInBackground(Void... args) {
            Log.e("erro", targetUri.getPath());
            String temp = nameproduct.getText().toString();

            String referencename = temp;
            StorageReference riversRef = mStorageRef.child(referencename + ".jpg");

            riversRef.putBytes(tempupload)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // Get a URL to the uploaded content
                            Uri downloadUrl = taskSnapshot.getDownloadUrl();
                            Log.e("picture", downloadUrl.toString());
                            onPostExecute(true, "Success");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle unsuccessful uploads
                            onPostExecute(false, "failed");
                        }
                    });
            return null;
        }

        protected void onPostExecute(Boolean b, String a) {
            // do UI work here
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            Toast.makeText(getApplicationContext(), a, Toast.LENGTH_SHORT).show();
            if (b = true) {
                finish();
            } else {

            }
        }
    }
}