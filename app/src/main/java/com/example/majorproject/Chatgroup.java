package com.example.majorproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Chatgroup extends AppCompatActivity {


    EditText mainUsername;
    Button return1Button;

    String username;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatgroup);

        // Retrieve the username passed from LoginActivity
        username = getIntent().getStringExtra("USERNAME");

        mainUsername = findViewById(R.id.main_username3);
        return1Button = findViewById(R.id.return1_button);

        mainUsername.setText(username);
        mainUsername.setEnabled(false); // disable editing of the username field

        return1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Chatgroup.this, Learning.class);
                intent.putExtra("USERNAME", username);
                startActivity(intent);
            }
        });
    }
}