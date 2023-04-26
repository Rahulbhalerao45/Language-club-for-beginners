package com.example.majorproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Next1 extends AppCompatActivity {


    EditText mainUsername;
    Button returnButton, submit1Button;
    RadioButton quizOption7A, quizOption7B, quizOption8A, quizOption8B, quizOption9A, quizOption9B;

    RadioGroup group1, group2, group3;

    String username, language, language_one, language_two, language_three;
    TextView logoutRedirectText, quizQuestion7, quizQuestion8, quizQuestion9, quiz_view;

    DatabaseReference quiz1Ref, quiz2Ref, quiz3Ref;// reference to the quiz data in Firebase

    FirebaseDatabase Reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next1);

        // Retrieve the username passed from LoginActivity
        String username = getIntent().getStringExtra("USERNAME");
        String language = getIntent().getStringExtra("LANGUAGE");

        String language_one = getIntent().getStringExtra("LANGUAGE1");
        String language_two = getIntent().getStringExtra("LANGUAGE2");
        String language_three = getIntent().getStringExtra("LANGUAGE3");

        quizQuestion7 = findViewById(R.id.quiz_question1);
        quizQuestion8 = findViewById(R.id.quiz_question2);
        quizQuestion9 = findViewById(R.id.quiz_question3);
        group1 = findViewById(R.id.qradio7);
        group2 = findViewById(R.id.qradio8);
        group3 = findViewById(R.id.qradio9);
        quizOption7A = findViewById(R.id.qanswer13);
        quizOption7B = findViewById(R.id.qanswer14);
        quizOption8A = findViewById(R.id.qanswer15);
        quizOption8B = findViewById(R.id.qanswer16);
        quizOption9A = findViewById(R.id.qanswer17);
        quizOption9B = findViewById(R.id.qanswer18);
        mainUsername = findViewById(R.id.quiz_main_username);
        returnButton = findViewById(R.id.quiz_return_button);
        submit1Button = findViewById(R.id.submit);
        logoutRedirectText = findViewById(R.id.quiz_logout);
        quiz_view = findViewById(R.id.quiz_view);

        mainUsername.setText("" + username + "--" + language + "");
        mainUsername.setEnabled(false); // disable editing of the username field

        quiz_view.setText("" + language_one + "-" + language_two + "-" + language_three + "");
        quiz_view.setEnabled(false);
        quiz_view.setVisibility(View.INVISIBLE);

        FirebaseDatabase Reference = FirebaseDatabase.getInstance();

        DatabaseReference quiz3Ref = Reference.getReference("quiz").child(language_three);

        if (language_three.equals(language_one)){
            quiz3Ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        // Retrieve the questions and options

                        String question8 = snapshot.child("question8/text").getValue(String.class);
                        String option8A = snapshot.child("question8/A").getValue(String.class);
                        String option8B = snapshot.child("question8/B").getValue(String.class);

                        quizQuestion8.setText("Q8 : " + question8);
                        quizOption8A.setText(option8A);
                        quizOption8B.setText(option8B);

                        String question7 = snapshot.child("question7/text").getValue(String.class);
                        String option7A = snapshot.child("question7/A").getValue(String.class);
                        String option7B = snapshot.child("question7/B").getValue(String.class);

                        quizQuestion7.setText("Q7 : " + question7);
                        quizOption7A.setText(option7A);
                        quizOption7B.setText(option7B);

                        String question9 = snapshot.child("question9/text").getValue(String.class);
                        String option9A = snapshot.child("question9/A").getValue(String.class);
                        String option9B = snapshot.child("question9/B").getValue(String.class);

                        quizQuestion9.setText("Q9 : " + question9);
                        quizOption9A.setText(option9A);
                        quizOption9B.setText(option9B);

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    // Handle the error
                }
            });
        } else {
            quiz3Ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        // Retrieve the questions and options

                        String question8 = snapshot.child("question2/text").getValue(String.class);
                        String option8A = snapshot.child("question2/A").getValue(String.class);
                        String option8B = snapshot.child("question2/B").getValue(String.class);

                        quizQuestion8.setText("Q8." + question8);
                        quizOption8A.setText(option8A);
                        quizOption8B.setText(option8B);

                        String question7 = snapshot.child("question1/text").getValue(String.class);
                        String option7A = snapshot.child("question1/A").getValue(String.class);
                        String option7B = snapshot.child("question1/B").getValue(String.class);

                        quizQuestion7.setText("Q7." + question7);
                        quizOption7A.setText(option7A);
                        quizOption7B.setText(option7B);

                        String question9 = snapshot.child("question3/text").getValue(String.class);
                        String option9A = snapshot.child("question3/A").getValue(String.class);
                        String option9B = snapshot.child("question3/B").getValue(String.class);

                        quizQuestion9.setText("Q9." + question9);
                        quizOption9A.setText(option9A);
                        quizOption9B.setText(option9B);

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    // Handle the error
                }
            });
        }

        submit1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the selected options
                int selectedOption1 = group1.getCheckedRadioButtonId();
                int selectedOption2 = group2.getCheckedRadioButtonId();
                int selectedOption3 = group3.getCheckedRadioButtonId();

// Convert the option IDs to strings
                String selectedOption1String = selectedOption1 == -1 ? "N/A" : (selectedOption1 == R.id.qanswer13 ? "A" : "B");
                String selectedOption2String = selectedOption2 == -1 ? "N/A" : (selectedOption2 == R.id.qanswer15 ? "A" : "B");
                String selectedOption3String = selectedOption3 == -1 ? "N/A" : (selectedOption3 == R.id.qanswer17 ? "A" : "B");

// Create a Firebase reference to the user's quiz results
                DatabaseReference userQuizRef = FirebaseDatabase.getInstance().getReference()
                        .child("quiz_results")
                        .child(username);

// Store the selected options in Firebase
                userQuizRef.child("question7").setValue(selectedOption1String);
                userQuizRef.child("question8").setValue(selectedOption2String);
                userQuizRef.child("question9").setValue(selectedOption3String);


                    Intent intent = new Intent(Next1.this, Submit1.class);
                    intent.putExtra("USERNAME", username);
                    intent.putExtra("LANGUAGE", language);
                    intent.putExtra("LANGUAGE1", language_one);
                    intent.putExtra("LANGUAGE2", language_two);
                    intent.putExtra("LANGUAGE3", language_three);
                    startActivity(intent);

            }
        });

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Next1.this, Next.class);
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
                Intent intent =new Intent(Next1.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}