package com.example.majorproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Chatgroup extends AppCompatActivity {

    Button return1Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatgroup);

        return1Button = findViewById(R.id.return1_button);

        return1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(Chatgroup.this, Learning.class);
                startActivity(intent);
            }
        });
    }
}