package com.fake.shopee.shopeefake.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fake.shopee.shopeefake.Main_pages.main_profile;
import com.fake.shopee.shopeefake.R;
import com.fake.shopee.shopeefake.session_class;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.content.ContentValues.TAG;

/**
 * Created by Riandy on 12/10/2017.
 */

public class fragement_signup extends Fragment {
    EditText email,pass,userid;
    Button btnsignup;
    private FirebaseAuth mAuth;
    ProgressDialog dialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_signup, container, false);
        email = (EditText) rootView.findViewById(R.id.emailsignup);
        pass = (EditText) rootView.findViewById(R.id.passwordsignup);
        btnsignup = (Button) rootView.findViewById(R.id.btnSignUp);
        userid = (EditText) rootView.findViewById(R.id.usernamesignup);

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new ProgressDialog(getActivity());
                dialog.setMessage("Login...");
                dialog.show();
                DoSignup doLogin = new DoSignup();
                doLogin.execute("");
            }
        });

        return rootView;
    }
    public class DoSignup extends AsyncTask<String, String, String> {
        String z = "";
        Boolean isSuccess = false;
        String username = userid.getText().toString();
        String emaildata = email.getText().toString();
        String password = pass.getText().toString();

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onPostExecute(String r) {
            dialog.dismiss();
            Toast.makeText(getActivity(), r, Toast.LENGTH_SHORT).show();
            if (isSuccess) {
                Intent i = new Intent(getActivity(), main_profile.class);
                startActivity(i);
            }
        }

        ;

        @Override
        protected String doInBackground(String... params) {
            mAuth.createUserWithEmailAndPassword(username, password)
                    .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("firebase", "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                session_class session= new session_class(getActivity());
                                session.setusename(user.getDisplayName());
                                z="Signed in As"+user.getDisplayName();
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(getActivity(), "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                session_class session= new session_class(getActivity());
                                session.setusename("");
                                z="Login failed";
                            }

                            // ...
                        }
                    });
            return z;
        }
    }
}
