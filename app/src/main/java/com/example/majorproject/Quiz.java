package com.example.majorproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Quiz extends AppCompatActivity {

    EditText mainUsername;
    Button return1Button, beginnerButton, mediumButton, advancedButton;

    String username, language, question1, option1A, option1B, question2, option2A, option2B, question3, option3A, option3B;
    TextView logoutRedirectText;

    DatabaseReference quizRef, ref;// reference to the quiz data in Firebase

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Retrieve the username and language passed from LoginActivity
        username = getIntent().getStringExtra("USERNAME");
        language = getIntent().getStringExtra("LANGUAGE");

        mainUsername = findViewById(R.id.quiz_main_username);
        return1Button = findViewById(R.id.quiz_return_button);
        beginnerButton = findViewById(R.id.quiz_beginner_button);
        mediumButton = findViewById(R.id.quiz_medium_button);
        advancedButton = findViewById(R.id.quiz_advanced_button);
        logoutRedirectText = findViewById(R.id.quiz_logout);

        mainUsername.setText("" + username + "--" + language + "");
        mainUsername.setEnabled(false); // disable editing of the username field

        // Get a reference to the quiz data in Firebase using the language key
        quizRef = FirebaseDatabase.getInstance().getReference().child("quiz").child(language);
        beginnerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Select the beginner level key from the quiz data using a switch statement
                quizRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String quizLevelKey = "";
                        switch (v.getId()) {
                            case R.id.quiz_beginner_button:
                                quizLevelKey = "beginner";
                                break;
                        }
                        Intent intent = new Intent(Quiz.this, Beginner.class);
                        intent.putExtra("USERNAME", username);
                        intent.putExtra("LANGUAGE", language);
                        intent.putExtra("LEVEL", quizLevelKey);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(Quiz.this, "Error accessing quiz data", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("quiz/language/beginner");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Retrieve the questions and options
                    String question1 = dataSnapshot.child("quiz/language/beginner/question1/question").getValue(String.class);
                    String option1A = dataSnapshot.child("quiz/language/beginner/question1/options/A").getValue(String.class);
                    String option1B = dataSnapshot.child("quiz/language/beginner/question1/options/B").getValue(String.class);

                    String question2 = dataSnapshot.child("quiz/language/beginner/question2/question").getValue(String.class);
                    String option2A = dataSnapshot.child("quiz/language/beginner/question2/options/A").getValue(String.class);
                    String option2B = dataSnapshot.child("quiz/language/beginner/question2/options/B").getValue(String.class);

                    String question3 = dataSnapshot.child("quiz/language/beginner/question3/question").getValue(String.class);
                    String option3A = dataSnapshot.child("quiz/language/beginner/question3/options/A").getValue(String.class);
                    String option3B = dataSnapshot.child("quiz/language/beginner/question3/options/B").getValue(String.class);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle the error
            }
        });

        mediumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Select the beginner level key from the quiz data using a switch statement
                quizRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String quizLevelKey = "";
                        switch (v.getId()) {
                            case R.id.quiz_medium_button:
                                quizLevelKey = "medium";
                                break;
                        }

                        Intent intent = new Intent(Quiz.this, Medium.class);
                        intent.putExtra("USERNAME", username);
                        intent.putExtra("LANGUAGE", language);
                        intent.putExtra("LEVEL", quizLevelKey);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(Quiz.this, "Error accessing quiz data", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        advancedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Select the beginner level key from the quiz data using a switch statement
                quizRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String quizLevelKey = "";
                        switch (v.getId()) {
                            case R.id.quiz_advanced_button:
                                quizLevelKey = "advanced";
                                break;
                        }

                        Intent intent = new Intent(Quiz.this, Advanced.class);
                        intent.putExtra("USERNAME", username);
                        intent.putExtra("LANGUAGE", language);
                        intent.putExtra("LEVEL", quizLevelKey);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(Quiz.this, "Error accessing quiz data", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        return1Button.setOnClickListener(new View.OnClickListener() {
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
