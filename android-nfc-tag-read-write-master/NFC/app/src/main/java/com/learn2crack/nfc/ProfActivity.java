package com.learn2crack.nfc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE1 = "extra1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof);
        Intent intent = getIntent();
        String message = intent.getStringExtra(Login.EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(message);



    }

    public void GetRoom(View view)
    {
        EditText editText = (EditText) findViewById(R.id.editText10);
        String r = editText.getText().toString();

        FirebaseDatabase db1 = FirebaseDatabase.getInstance();
        DatabaseReference rootref = db1.getReference();

        if(r.equals(""))
        {
            Toast.makeText(getApplicationContext(),"Invalid Room Number", Toast.LENGTH_SHORT).show();
            editText.setText("");
        }
        else {
            DatabaseReference testref = rootref.child("Room").child(r);


            testref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    if (dataSnapshot.exists()) {

                        Intent intent = new Intent(ProfActivity.this, ClassView.class);
                        String message = dataSnapshot.child("0").getValue().toString();
                        String message2 = dataSnapshot.child("1").getValue().toString();
                        message = message+" "+message2+" "+r ;
                        intent.putExtra(EXTRA_MESSAGE1, message);
                        startActivity(intent);





                    } else {
                        Log.e("t", "PROB");
                        editText.setText("");
                        Toast.makeText(getApplicationContext(),"Invalid Room Number",Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {


                }
            });

        }



    }
}
