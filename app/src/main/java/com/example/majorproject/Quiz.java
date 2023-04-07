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
    RadioButton quizOption1A, quizOption1B, quizOption2A, quizOption2B, quizOption3A, quizOption3B;

    RadioGroup group1, group2, group3;

    String username, language, language_one, language_two, language_three;
    TextView logoutRedirectText, quizQuestion1, quizQuestion2, quizQuestion3, quiz_view;

    DatabaseReference quiz1Ref, quiz2Ref, quiz3Ref;// reference to the quiz data in Firebase


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Retrieve the username passed from LoginActivity
        String username = getIntent().getStringExtra("USERNAME");
        String language = getIntent().getStringExtra("LANGUAGE");

        String language_one = getIntent().getStringExtra("LANGUAGE1");
        String language_two = getIntent().getStringExtra("LANGUAGE2");
        String language_three = getIntent().getStringExtra("LANGUAGE3");

        quizQuestion1 = findViewById(R.id.quiz_question1);
        quizQuestion2 = findViewById(R.id.quiz_question2);
        quizQuestion3 = findViewById(R.id.quiz_question3);
        group1 = findViewById(R.id.qradio1);
        group2 = findViewById(R.id.qradio2);
        group3 = findViewById(R.id.qradio3);
        quizOption1A = findViewById(R.id.qanswer1);
        quizOption1B = findViewById(R.id.qanswer2);
        quizOption2A = findViewById(R.id.qanswer3);
        quizOption2B = findViewById(R.id.qanswer4);
        quizOption3A = findViewById(R.id.qanswer5);
        quizOption3B = findViewById(R.id.qanswer6);
        mainUsername = findViewById(R.id.quiz_main_username);
        returnButton = findViewById(R.id.quiz_return_button);
        logoutRedirectText = findViewById(R.id.quiz_logout);
        quiz_view = findViewById(R.id.quiz_view);

        mainUsername.setText("" + username + "--" + language + "");
        mainUsername.setEnabled(false); // disable editing of the username field

        quiz_view.setText("" + language_one + "-" + language_two + "-" + language_three + "");
        quiz_view.setEnabled(false);
        quiz_view.setVisibility(View.INVISIBLE);

        DatabaseReference quiz1Ref = FirebaseDatabase.getInstance().getReference().child("quiz").child(language_one);
        DatabaseReference quiz2Ref = FirebaseDatabase.getInstance().getReference().child("quiz").child(language_two);
        DatabaseReference quiz3Ref = FirebaseDatabase.getInstance().getReference().child("quiz").child(language_three);

        quiz1Ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    // Retrieve the questions and options
                    String question1 = snapshot.child("question1/text").getValue(String.class);
                    String option1A = snapshot.child("question1/A").getValue(String.class);
                    String option1B = snapshot.child("question1/B").getValue(String.class);


                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle the error
            }
        });

        quiz2Ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    // Retrieve the questions and options

                    String question2 = snapshot.child("question2/text").getValue(String.class);
                    String option2A = snapshot.child("question2/A").getValue(String.class);
                    String option2B = snapshot.child("question2/B").getValue(String.class);


                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle the error
            }
        });

        quiz3Ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    // Retrieve the questions and options

                    String question3 = snapshot.child("question3/text").getValue(String.class);
                    String option3A = snapshot.child("question3/A").getValue(String.class);
                    String option3B = snapshot.child("question3/B").getValue(String.class);

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle the error
            }
        });

        quizQuestion1.setText("question1");
        quizQuestion2.setText("question2");
        quizQuestion3.setText("question3");
        quizOption1A.setText("option1A");
        quizOption1B.setText("option1B");
        quizOption2A.setText("option2A");
        quizOption2B.setText("option2B");
        quizOption3A.setText("option3A");
        quizOption3B.setText("option3B");


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