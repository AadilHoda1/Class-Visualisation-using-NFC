package com.learn2crack.nfc;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Login extends AppCompatActivity {
    public String y;
    public static final String EXTRA_MESSAGE = "extra";
    private ProgressBar spinner;
    private InputMethodManager imm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        EditText editText = (EditText) findViewById(R.id.editText9);
        editText.requestFocus();
        spinner = (ProgressBar)findViewById(R.id.progressBar2);
        spinner.setVisibility(View.GONE);



    }

    public void login(View view) {

        EditText editText = (EditText) findViewById(R.id.editText9);
        String user = editText.getText().toString();

        EditText editText1 = (EditText) findViewById(R.id.editText7);
        String pass = editText1.getText().toString();

        if (null != this.getCurrentFocus())
            imm.hideSoftInputFromWindow(this.getCurrentFocus()
                    .getApplicationWindowToken(), 0);



        FirebaseDatabase db1 = FirebaseDatabase.getInstance();
        DatabaseReference rootref = db1.getReference();

        if(user.equals(""))
        {
            Toast.makeText(getApplicationContext(),"Invalid Username or Password", Toast.LENGTH_SHORT).show();
            editText1.setText("");
            editText.setText("");
        }
        else {

            spinner.setVisibility(View.VISIBLE);
            DatabaseReference testref = rootref.child("Prof").child(user);


            testref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    if (dataSnapshot.exists()) {

                        y = dataSnapshot.child("1").getValue().toString();
                        if (y.equals(pass)) {
                            Log.e("t", "YES");
                            Intent intent = new Intent(Login.this, ProfActivity.class);
                            String message = dataSnapshot.child("0").getValue().toString();
                            message = "Hello!! "+message;
                            intent.putExtra(EXTRA_MESSAGE, message);
                            spinner.setVisibility(View.GONE);
                            startActivity(intent);


                        } else {
                            Log.e("t", "NO");
                            editText1.setText("");
                            editText.setText("");
                            spinner.setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext(),"Invalid Username or Password",Toast.LENGTH_SHORT).show();
                        }


                    } else {
                        Log.e("t", "PROB");
                        editText1.setText("");
                        editText.setText("");
                        spinner.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(),"Invalid Username or Password",Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {


                }
            });

        }











    }
}
