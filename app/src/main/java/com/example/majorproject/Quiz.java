package com.example.majorproject;

import androidx.annotation.NonNull;
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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Quiz extends AppCompatActivity {


    EditText mainUsername;
    Button returnButton;
    RadioButton beginnerOption1A, beginnerOption1B, beginnerOption2A, beginnerOption2B, beginnerOption3A, beginnerOption3B;

    RadioGroup group1, group2, group3;

    String username, language;
    TextView logoutRedirectText, beginnerQuestion1, beginnerQuestion2, beginnerQuestion3;

    DatabaseReference ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Retrieve the username passed from LoginActivity
        String username = getIntent().getStringExtra("USERNAME");
        String language = getIntent().getStringExtra("LANGUAGE");

        beginnerQuestion1 = findViewById(R.id.quiz_question1);
        beginnerQuestion2 = findViewById(R.id.quiz_question2);
        beginnerQuestion3 = findViewById(R.id.quiz_question3);
        group1 = findViewById(R.id.qradio1);
        group2 = findViewById(R.id.qradio2);
        group3 = findViewById(R.id.qradio3);
        beginnerOption1A = findViewById(R.id.qanswer1);
        beginnerOption1B = findViewById(R.id.qanswer2);
        beginnerOption2A = findViewById(R.id.qanswer3);
        beginnerOption2B = findViewById(R.id.qanswer4);
        beginnerOption3A = findViewById(R.id.qanswer5);
        beginnerOption3B = findViewById(R.id.qanswer6);
        mainUsername = findViewById(R.id.quiz_main_username);
        returnButton = findViewById(R.id.quiz_return_button);
        logoutRedirectText = findViewById(R.id.quiz_logout);

        mainUsername.setText("" + username + "--" + language + "");
        mainUsername.setEnabled(false); // disable editing of the username field

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Quiz.this, Learning.class);
                intent.putExtra("USERNAME", username);
                intent.putExtra("LANGUAGE", language);
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