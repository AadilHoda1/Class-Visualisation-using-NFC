package com.learn2crack.nfc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    public void Student_View(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void Professor_View(View view) {
        Intent intent1 = new Intent(this, Login.class);
        startActivity(intent1);
    }

    public void Admin_View(View view) {
        Intent intent2 = new Intent(this, Login_Admin.class);
        startActivity(intent2);
    }
}
