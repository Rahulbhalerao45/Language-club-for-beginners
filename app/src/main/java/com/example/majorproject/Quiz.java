package com.example.majorproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class Quiz extends AppCompatActivity {


    EditText mainUsername;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mQuizRef, quizRef, question1Ref, question2Ref;
    private Button mReturnButton;
    private RadioButton mAnswer1RadioButton, mAnswer2RadioButton, mAnswer3RadioButton, mAnswer4RadioButton;
    private int mScore = 0;
    private Map<String, String> mCorrectAnswers;

    String username;

    TextView logoutRedirectText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Retrieve the username passed from LoginActivity
        username = getIntent().getStringExtra("USERNAME");
        mainUsername = findViewById(R.id.main_username4);
        mReturnButton = findViewById(R.id.return_button);
        logoutRedirectText = findViewById(R.id.logout4);
        mAnswer1RadioButton = findViewById(R.id.answer1);
        mAnswer2RadioButton = findViewById(R.id.answer2);
        mAnswer3RadioButton = findViewById(R.id.answer3);
        mAnswer4RadioButton = findViewById(R.id.answer4);


        mainUsername.setText(username);
        mainUsername.setEnabled(false); // disable editing of the username field


        mReturnButton.setOnClickListener(new View.OnClickListener() {
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
