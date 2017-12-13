package com.fake.shopee.shopeefake.fragment;

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

public class fragment_login extends Fragment{

    EditText email,pass;
    Button btnlogin;
    private FirebaseAuth mAuth;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_login, container, false);
        email = (EditText) rootView.findViewById(R.id.emaillogin);
        pass = (EditText) rootView.findViewById(R.id.passwordlogin);
        btnlogin = (Button) rootView.findViewById(R.id.btnloginlogin);




        mAuth.createUserWithEmailAndPassword(email.getText().toString(), pass.getText().toString())
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("firebase", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            session_class session= new session_class(getActivity());
                            session.setusename(user.getDisplayName());
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getActivity(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            session_class session= new session_class(getActivity());
                            session.setusename("");
                        }

                        // ...
                    }
                });


        return rootView;
    }
    public class DoLogin extends AsyncTask<String, String, String> {
        String z = "";
        Boolean isSuccess = false;
        String userid = edtuserid.getText().toString();
        String password = edtpass.getText().toString();

        @Override
        protected void onPreExecute() {
            pblogin.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String r) {
            pblogin.setVisibility(View.GONE);
            Toast.makeText(loginprogram.this, r, Toast.LENGTH_SHORT).show();
            if (isSuccess) {
                SharedPreferences.Editor edit = shp.edit();
                edit.putString("UserId", userid);
                edit.commit();
                Intent i = new Intent(loginprogram.this, mainmenu.class);
                startActivity(i);
            }
        }

        ;

        @Override
        protected String doInBackground(String... params) {
            if (userid.trim().equals("") || password.trim().equals(""))
                z = "Please enter User Id and Password";
            else {

                try {
                    Connection con = sqlclass.CONN(sqlclass.ip,sqlclass.db,sqlclass.un,sqlclass.password,sqlclass.port,sqlclass.instance);
                    if (con == null) {
                        z = "Error in connection with SQL server";
                        Intent i = new Intent(loginprogram.this, MainActivity.class);
                        startActivity(i);
                        finish();
                    } else {
                        ResultSet result = sqlclass.querydata("select * from xUser where User_id='"
                                + userid + "' and dbo.GETPASS(password)='"+password+"'");
                        //String querydata = "select * from xUser where User_id='"
                        //        + userid + "' and password='" + password + "'";
                        // Statement stmt = con.createStatement();
                        // ResultSet rs = stmt.executeQuery(query);
                        if (result.next()) {
                            generator.userlogin=userid;
                            z = "Login successfull";
                            isSuccess = true;
                        }
                        if (z!="Login successfull"){
                            z = "Invalid Credentials";
                        }
                    }
                } catch (Exception ex) {
                    isSuccess = false;
                    z = ex.toString();
                }
            }
            return z;
        }
    }
}
