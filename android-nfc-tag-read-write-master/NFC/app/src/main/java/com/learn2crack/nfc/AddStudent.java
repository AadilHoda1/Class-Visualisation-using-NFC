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
import java.util.List;

import static android.provider.AlarmClock.EXTRA_MESSAGE;


public class AddStudent extends AppCompatActivity {
    private InputMethodManager imm;
    private boolean b;
    private Long n;
    private Long counter;
    private Integer help;
    private String k;
    DatabaseReference Ref;
    String id;
    ArrayList<String> jl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        EditText editText = (EditText) findViewById(R.id.editText);
        editText.requestFocus();
    }

    public void add_student(View view) {

        EditText editText = (EditText) findViewById(R.id.editText);
        editText.requestFocus();
        String nam = editText.getText().toString();
        editText.setText("");
        EditText editText1 = (EditText) findViewById(R.id.editText2);
        String rn = editText1.getText().toString();
        editText1.setText("");
        EditText editText2 = (EditText) findViewById(R.id.editText3);
        id = editText2.getText().toString();
        editText2.setText("");

        if (null != this.getCurrentFocus())
            imm.hideSoftInputFromWindow(this.getCurrentFocus()
                    .getApplicationWindowToken(), 0);

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        Ref = db.getReference();

        if(nam.equals("") | rn.equals("") | id.equals(""))
        {
            Toast.makeText(getApplicationContext(),"Invalid Details",Toast.LENGTH_SHORT).show();
        }
        else {

            jl = new ArrayList<String>();
            jl.add(nam);
            jl.add(rn);


            counter=0L;
            help=1;
            k="";
            b=true;

            DatabaseReference testref = Ref.child("Student");
            testref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    n=dataSnapshot.getChildrenCount();


                    if (dataSnapshot.exists()) {
                        for(DataSnapshot snap : dataSnapshot.getChildren()){
                            counter += 1;
                            if(snap.getKey().equals(id))
                                b=false;
                            if(snap.child("1").getValue().toString().equals(rn))
                            {
                                help=2;
                                k=snap.getKey();

                            }

                            if (counter==n)
                            {
                                doThisAfter();
                            }



                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {


                }


            });



            }






    }

    public void doThisAfter() {
        if(help==1)
        {
            Ref.child("Student").child(id).setValue(jl);
            if(!b)
            Toast.makeText(getApplicationContext(), "Info with this mobile id updated!!!", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getApplicationContext(), "New Student Added!!!", Toast.LENGTH_SHORT).show();

        }
        else if(help==2)
        {
            Ref.child("Student").child(k).setValue(null);
            Ref.child("Student").child(id).setValue(jl);
            Toast.makeText(getApplicationContext(), "Info with this roll no. updated!!!", Toast.LENGTH_SHORT).show();


        }

    }




}
