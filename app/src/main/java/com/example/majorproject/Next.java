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

public class Next extends AppCompatActivity {


    EditText mainUsername;
    Button returnButton, next1Button;
    RadioButton quizOption1A, quizOption1B, quizOption2A, quizOption2B, quizOption3A, quizOption3B;

    RadioGroup group1, group2, group3;

    String username, language, language_one, language_two, language_three;
    TextView logoutRedirectText, quizQuestion1, quizQuestion2, quizQuestion3, quiz_view;

    DatabaseReference quiz1Ref, quiz2Ref, quiz3Ref;// reference to the quiz data in Firebase

    FirebaseDatabase Reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        // Retrieve the username passed from LoginActivity
        String username = getIntent().getStringExtra("USERNAME");
        String language = getIntent().getStringExtra("LANGUAGE");


        String language_one = getIntent().getStringExtra("LANGUAGE1");
        String language_two = getIntent().getStringExtra("LANGUAGE2");
        String language_three = getIntent().getStringExtra("LANGUAGE3");

        quizQuestion1 = findViewById(R.id.quiz_question1);
        quizQuestion2 = findViewById(R.id.quiz_question2);
        quizQuestion3 = findViewById(R.id.quiz_question3);
        group1 = findViewById(R.id.qradio4);
        group2 = findViewById(R.id.qradio5);
        group3 = findViewById(R.id.qradio6);
        quizOption1A = findViewById(R.id.qanswer7);
        quizOption1B = findViewById(R.id.qanswer8);
        quizOption2A = findViewById(R.id.qanswer9);
        quizOption2B = findViewById(R.id.qanswer10);
        quizOption3A = findViewById(R.id.qanswer11);
        quizOption3B = findViewById(R.id.qanswer12);
        mainUsername = findViewById(R.id.quiz_main_username);
        returnButton = findViewById(R.id.quiz_return_button);
        next1Button = findViewById(R.id.quiz_next1_button);
        logoutRedirectText = findViewById(R.id.quiz_logout);
        quiz_view = findViewById(R.id.quiz_view);

        mainUsername.setText("" + username + "--" + language + "");
        mainUsername.setEnabled(false); // disable editing of the username field

        quiz_view.setText("" + language_one + "-" + language_two + "-" + language_three + "");
        quiz_view.setEnabled(false);
        quiz_view.setVisibility(View.INVISIBLE);

        FirebaseDatabase Reference = FirebaseDatabase.getInstance();

        DatabaseReference quiz1Ref = Reference.getReference("quiz").child(language_one);
        DatabaseReference quiz2Ref = Reference.getReference("quiz").child(language_two);
        DatabaseReference quiz3Ref = Reference.getReference("quiz").child(language_three);

        quiz1Ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    // Retrieve the questions and options
                    String question1 = snapshot.child("question2/text").getValue(String.class);
                    String option1A = snapshot.child("question2/A").getValue(String.class);
                    String option1B = snapshot.child("question2/B").getValue(String.class);

                    quizQuestion1.setText("Q4." + question1);
                    quizOption1A.setText(option1A);
                    quizOption1B.setText(option1B);


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

                    quizQuestion2.setText("Q5." + question2);
                    quizOption2A.setText(option2A);
                    quizOption2B.setText(option2B);


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

                    String question3 = snapshot.child("question2/text").getValue(String.class);
                    String option3A = snapshot.child("question2/A").getValue(String.class);
                    String option3B = snapshot.child("question2/B").getValue(String.class);

                    quizQuestion3.setText("Q6." + question3);
                    quizOption3A.setText(option3A);
                    quizOption3B.setText(option3B);

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle the error
            }
        });

        next1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (group1.getCheckedRadioButtonId() != -1 && group2.getCheckedRadioButtonId() != -1 && group3.getCheckedRadioButtonId() != -1) {
                Intent intent = new Intent(Next.this, Next1.class);
                intent.putExtra("USERNAME", username);
                intent.putExtra("LANGUAGE", language);
                intent.putExtra("LANGUAGE1", language_one);
                intent.putExtra("LANGUAGE2", language_two);
                intent.putExtra("LANGUAGE3", language_three);
                startActivity(intent);
                } else {
                    Toast.makeText(Next.this, "Please select an option for each question.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Next.this, Quiz.class);
                intent.putExtra("USERNAME", username);
                intent.putExtra("LANGUAGE", language);
                intent.putExtra("LANGUAGE1", language_one);
                intent.putExtra("LANGUAGE2", language_two);
                intent.putExtra("LANGUAGE3", language_three);
                startActivity(intent);
            }
        });

        logoutRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Next.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}