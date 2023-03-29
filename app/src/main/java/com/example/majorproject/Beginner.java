package com.example.majorproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class Beginner extends AppCompatActivity {

    EditText mainUsername;
    TextView question1, question2;
    Button mReturnButton;
    RadioButton answer1, answer2, answer3, answer4, answer5, answer6, answer7, answer8;
    String username;
    TextView logoutRedirectText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beginner);

        username = getIntent().getStringExtra("USERNAME");

        mainUsername = findViewById(R.id.beginner1_main_username);

        mainUsername.setText(username);
        mainUsername.setEnabled(false); // disable editing of the username field

        question1 = findViewById(R.id.beginner1_question1);
        question2 = findViewById(R.id.beginner1_question2);
        answer1 = findViewById(R.id.beginner1_answer1);
        answer2 = findViewById(R.id.beginner1_answer2);
        answer5 = findViewById(R.id.beginner1_answer5);
        answer6 = findViewById(R.id.beginner1_answer6);
        mReturnButton = findViewById(R.id.beginner1_return_button);

        mReturnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Beginner.this, Quiz.class);
                intent.putExtra("USERNAME", username);
                startActivity(intent);
            }
        });

        logoutRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Beginner.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}