package com.learn2crack.nfc;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AddRoom extends AppCompatActivity {
    private InputMethodManager imm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_room);
        imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        EditText editText = (EditText) findViewById(R.id.editText5);
        editText.requestFocus();
    }

    public void add_room(View view) {

        EditText editText = (EditText) findViewById(R.id.editText5);
        editText.requestFocus();

        String r = editText.getText().toString();
        editText.setText("");
        EditText editText1 = (EditText) findViewById(R.id.editText6);
        String ro = editText1.getText().toString();
        editText1.setText("");
        EditText editText2 = (EditText) findViewById(R.id.editText4);
        String col = editText2.getText().toString();
        editText2.setText("");

        if (null != this.getCurrentFocus())
            imm.hideSoftInputFromWindow(this.getCurrentFocus()
                    .getApplicationWindowToken(), 0);

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference Ref = db.getReference();

        if(r.equals("") | ro.equals("") | col.equals("") | !isInteger(ro) | !isInteger(col))
        {
            Toast.makeText(getApplicationContext(),"Invalid Details",Toast.LENGTH_SHORT).show();
        }
        else {
            ArrayList<String> jl = new ArrayList<String>();
            jl.add(ro);
            jl.add(col);

            if(Integer.parseInt(ro)<=0 | Integer.parseInt(col)<=0 )
                Toast.makeText(getApplicationContext(),"Row and Column Number should be positive.",Toast.LENGTH_SHORT).show();
            else {
                Ref.child("Room").child(r).setValue(jl);
                Toast.makeText(getApplicationContext(), "Room Added Successfully!!", Toast.LENGTH_SHORT).show();
            }
        }






    }
    public static boolean isInteger(Object object) {
        if(object instanceof Integer) {
            return true;
        } else {
            String string = object.toString();

            try {
                Integer.parseInt(string);
            } catch(Exception e) {
                return false;
            }
        }

        return true;
    }
}
