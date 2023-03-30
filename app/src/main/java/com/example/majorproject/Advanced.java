package com.example.majorproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Advanced extends AppCompatActivity {


    EditText mainUsername;
    Button returnButton;

    String username, language;

    TextView logoutRedirectText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced);

        // Retrieve the username passed from LoginActivity
        String username = getIntent().getStringExtra("USERNAME");
        String language = getIntent().getStringExtra("LANGUAGE");

        mainUsername = findViewById(R.id.advanced_main_username);
        returnButton = findViewById(R.id.advanced_return_button);
        logoutRedirectText = findViewById(R.id.advanced_logout);

        mainUsername.setText("" + username + "--" + language + "");
        mainUsername.setEnabled(false); // disable editing of the username field

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override



            public void onClick(View view) {
                Intent intent = new Intent(Advanced.this, Quiz.class);
                intent.putExtra("USERNAME", username);
                intent.putExtra("LANGUAGE", language);
                startActivity(intent);
            }
        });

        logoutRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Advanced.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}