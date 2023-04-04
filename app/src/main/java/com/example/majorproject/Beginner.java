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

public class Beginner extends AppCompatActivity {


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
        setContentView(R.layout.activity_beginner);

        // Retrieve the username passed from LoginActivity
        String username = getIntent().getStringExtra("USERNAME");
        String language = getIntent().getStringExtra("LANGUAGE");

        beginnerQuestion1 = findViewById(R.id.beginner_question1);
        beginnerQuestion2 = findViewById(R.id.beginner_question2);
        beginnerQuestion3 = findViewById(R.id.beginner_question3);
        group1 = findViewById(R.id.radio1);
        group2 = findViewById(R.id.radio2);
        group3 = findViewById(R.id.radio3);
        beginnerOption1A = findViewById(R.id.answer1);
        beginnerOption1B = findViewById(R.id.answer2);
        beginnerOption2A = findViewById(R.id.answer3);
        beginnerOption2B = findViewById(R.id.answer4);
        beginnerOption3A = findViewById(R.id.answer5);
        beginnerOption3B = findViewById(R.id.answer6);
        mainUsername = findViewById(R.id.beginner_main_username);
        returnButton = findViewById(R.id.beginner_return_button);
        logoutRedirectText = findViewById(R.id.beginner_logout);

        mainUsername.setText("" + username + "--" + language + "");
        mainUsername.setEnabled(false); // disable editing of the username field

        beginnerQuestion1.setText("QUESTION1");
        beginnerQuestion2.setText("QUESTION2");
        beginnerQuestion3.setText("QUESTION3");
        beginnerOption1A.setText("OPTION1A");
        beginnerOption1B.setText("OPTION1B");
        beginnerOption2A.setText("OPTION2A");
        beginnerOption2B.setText("OPTION2B");
        beginnerOption3A.setText("OPTION3A");
        beginnerOption3B.setText("OPTION3B");

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