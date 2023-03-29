package com.example.majorproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Quiz extends AppCompatActivity {


    EditText mainUsername;
    Button return1Button, beginnerButton, mediumButton, advancedButton;

    String username;

    TextView logoutRedirectText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Retrieve the username passed from LoginActivity
        username = getIntent().getStringExtra("USERNAME");

        mainUsername = findViewById(R.id.quiz_main_username);
        return1Button = findViewById(R.id.quiz_return_button);
        beginnerButton = findViewById(R.id.quiz_beginner_button);
        mediumButton = findViewById(R.id.quiz_medium_button);
        advancedButton = findViewById(R.id.quiz_advanced_button);
        logoutRedirectText = findViewById(R.id.quiz_logout);

        mainUsername.setText(username);
        mainUsername.setEnabled(false); // disable editing of the username field

        beginnerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Quiz.this, Beginner.class);
                intent.putExtra("USERNAME", username);
                startActivity(intent);
            }
        });

        mediumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Quiz.this, Medium.class);
                intent.putExtra("USERNAME", username);
                startActivity(intent);
            }
        });

        advancedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Quiz.this, Advanced.class);
                intent.putExtra("USERNAME", username);
                startActivity(intent);
            }
        });

        return1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Quiz.this, Learning.class);
                intent.putExtra("USERNAME", username);
                startActivity(intent);
            }
        });

        logoutRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Quiz.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}