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
    private DatabaseReference mQuizRef;
    private Button mReturnButton;
    private Button mSubmitButton;
    private RadioButton mAnswer1RadioButton, mAnswer2RadioButton;
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
        mSubmitButton = findViewById(R.id.submit_button);
        logoutRedirectText = findViewById(R.id.logout4);
        mAnswer1RadioButton = findViewById(R.id.answer1);
        mAnswer2RadioButton = findViewById(R.id.answer2);


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

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Check the user's answer and update the score
                String selectedAnswer = "";
                if (mAnswer1RadioButton.isChecked()) {
                    selectedAnswer = mAnswer1RadioButton.getText().toString();
                } else if (mAnswer2RadioButton.isChecked()) {
                    selectedAnswer = mAnswer2RadioButton.getText().toString();
                }

                String correctAnswer = mCorrectAnswers.get("Q1");
                if (selectedAnswer.equals(correctAnswer)) {
                    mScore++;
                }

                // Display the score
                Toast.makeText(Quiz.this, "Your score is " + mScore, Toast.LENGTH_SHORT).show();
            }
        });

        logoutRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Quiz.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        mDatabase = FirebaseDatabase.getInstance();
        mQuizRef = mDatabase.getReference("Quiz");

        mCorrectAnswers = new HashMap<>();
        mCorrectAnswers.put("Q1", "Ans");

        ValueEventListener valueEventListener = mQuizRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Get the Question1 node from the dataSnapshot
                DataSnapshot questionSnapshot = dataSnapshot.child("Q1");

                // Get the question text and possible answers from the child nodes
                String questionText = questionSnapshot.child("Q1Text").getValue(String.class);
                String answer1 = questionSnapshot.child("A1").getValue(String.class);
                String answer2 = questionSnapshot.child("A2").getValue(String.class);

                // Update the UI widgets with the question text and possible answers
                TextView questionTextView = findViewById(R.id.question_text);
                mAnswer1RadioButton.setText(answer1);
                mAnswer2RadioButton.setText(answer2);

                // Set the text of the RadioButtons to the possible answers
                mAnswer1RadioButton.setText(answer1);
                mAnswer2RadioButton.setText(answer2);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
