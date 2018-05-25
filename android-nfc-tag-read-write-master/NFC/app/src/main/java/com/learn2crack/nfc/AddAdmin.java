package com.learn2crack.nfc;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AddAdmin extends AppCompatActivity {
    private InputMethodManager imm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_admin);
        imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        EditText editText = (EditText) findViewById(R.id.editText11);
        editText.requestFocus();
    }

    public void add_admin(View view) {

        EditText editText = (EditText) findViewById(R.id.editText11);
        String na = editText.getText().toString();
        editText.setText("");
        EditText editText1 = (EditText) findViewById(R.id.editText12);
        String us = editText1.getText().toString();
        editText1.setText("");
        EditText editText2 = (EditText) findViewById(R.id.editText13);
        String pas = editText2.getText().toString();
        editText2.setText("");
        if (null != this.getCurrentFocus())
            imm.hideSoftInputFromWindow(this.getCurrentFocus()
                    .getApplicationWindowToken(), 0);

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference Ref = db.getReference();

        if (na.equals("") | us.equals("") | pas.equals(""))
        {
            Toast.makeText(getApplicationContext(),"Invalid Details",Toast.LENGTH_SHORT).show();
        }

        else {

            ArrayList<String> jl = new ArrayList<String>();
            jl.add(na);
            jl.add(pas);

            DatabaseReference testref = Ref.child("Admin").child(us);
            testref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    if (dataSnapshot.exists()) {
                        Toast.makeText(getApplicationContext(),"Admin with same username exists",Toast.LENGTH_SHORT).show();
                    } else {
                        testref.setValue(jl);
                        Toast.makeText(getApplicationContext(),"Admin Added Successfully!!",Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {


                }
            });

        }



        }







    }

