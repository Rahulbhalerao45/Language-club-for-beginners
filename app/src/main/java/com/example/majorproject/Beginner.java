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
    RadioButton option1, option2, option3, option4, option5, option6;

    RadioGroup group1, group2, group3;

    String username, language;
    TextView logoutRedirectText, question1, question2, question3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beginner);

        // Retrieve the username passed from LoginActivity
        String username = getIntent().getStringExtra("USERNAME");
        String language = getIntent().getStringExtra("LANGUAGE");

        question1 = findViewById(R.id.beginner_question1);
        question2 = findViewById(R.id.beginner_question2);
        question3 = findViewById(R.id.beginner_question3);
        group1 = findViewById(R.id.radio1);
        group2 = findViewById(R.id.radio2);
        group3 = findViewById(R.id.radio3);
        option1 = findViewById(R.id.answer1);
        option2 = findViewById(R.id.answer2);
        option3 = findViewById(R.id.answer3);
        option4 = findViewById(R.id.answer4);
        option5 = findViewById(R.id.answer5);
        option6 = findViewById(R.id.answer6);
        mainUsername = findViewById(R.id.beginner_main_username);
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