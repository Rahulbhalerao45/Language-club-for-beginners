package com.example.majorproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Beginner extends AppCompatActivity {


    EditText mainUsername;
    Button returnButton;

    String username, language;

    RadioGroup mradio1, mradio2, mradio3;

    RadioButton manswer1, manswer2, manswer3, manswer4, manswer5, manswer6;

    TextView logoutRedirectText, question1, question2, question3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beginner);

        // Retrieve the username passed from LoginActivity
        String username = getIntent().getStringExtra("USERNAME");
        String language = getIntent().getStringExtra("LANGUAGE");

        mainUsername = findViewById(R.id.beginner_main_username);
        question1 = findViewById(R.id.beginner_question1);
        question2 = findViewById(R.id.beginner_question2);
        question3 = findViewById(R.id.beginner_question3);
        mradio1 = findViewById(R.id.radio1);
        mradio2 = findViewById(R.id.radio2);
        mradio3 = findViewById(R.id.radio3);
        manswer1 = findViewById(R.id.answer1);
        manswer2 = findViewById(R.id.answer2);
        manswer3 = findViewById(R.id.answer3);
        manswer4 = findViewById(R.id.answer4);
        manswer5 = findViewById(R.id.answer5);
        manswer6 = findViewById(R.id.answer6);
        returnButton = findViewById(R.id.beginner_return_button);
        logoutRedirectText = findViewById(R.id.beginner_logout);

        mainUsername.setText("" + username + "--" + language + "");
        mainUsername.setEnabled(false); // disable editing of the username field

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Beginner.this, Quiz.class);
                intent.putExtra("USERNAME", username);
                intent.putExtra("LANGUAGE", language);
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