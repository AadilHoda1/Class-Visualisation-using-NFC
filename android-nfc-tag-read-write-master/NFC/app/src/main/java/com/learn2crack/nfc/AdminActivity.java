package com.learn2crack.nfc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
    }

    public void Student_Data(View view) {
        Intent intent = new Intent(this, AddStudent.class);
        startActivity(intent);
    }

    public void Professor_Data(View view) {
        Intent intent = new Intent(this, AddProfessor.class);
        startActivity(intent);
    }

    public void Room_Data(View view) {
        Intent intent = new Intent(this, AddRoom.class);
        startActivity(intent);
    }

    public void Admin_Add(View view) {
        Intent intent = new Intent(this, AddAdmin.class);
        startActivity(intent);
    }
}
