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
    RadioButton quizOption4A, quizOption4B, quizOption5A, quizOption5B, quizOption6A, quizOption6B;

    RadioGroup group1, group2, group3;

    String username, language, language_one, language_two, language_three;
    TextView logoutRedirectText, quizQuestion4, quizQuestion5, quizQuestion6, quiz_view;

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

        quizQuestion4 = findViewById(R.id.quiz_question1);
        quizQuestion5 = findViewById(R.id.quiz_question2);
        quizQuestion6 = findViewById(R.id.quiz_question3);
        group1 = findViewById(R.id.qradio4);
        group2 = findViewById(R.id.qradio5);
        group3 = findViewById(R.id.qradio6);
        quizOption4A = findViewById(R.id.qanswer7);
        quizOption4B = findViewById(R.id.qanswer8);
        quizOption5A = findViewById(R.id.qanswer9);
        quizOption5B = findViewById(R.id.qanswer10);
        quizOption6A = findViewById(R.id.qanswer11);
        quizOption6B = findViewById(R.id.qanswer12);
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


        DatabaseReference quiz2Ref = Reference.getReference("quiz").child(language_two);

        if (language_two.equals(language_one)){
            quiz2Ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        // Retrieve the questions and options

                        String question5 = snapshot.child("question5/text").getValue(String.class);
                        String option5A = snapshot.child("question5/A").getValue(String.class);
                        String option5B = snapshot.child("question5/B").getValue(String.class);

                        quizQuestion5.setText("Q5." + question5);
                        quizOption5A.setText(option5A);
                        quizOption5B.setText(option5B);

                        String question44 = snapshot.child("question4/text").getValue(String.class);
                        String option44A = snapshot.child("question4/A").getValue(String.class);
                        String option44B = snapshot.child("question4/B").getValue(String.class);

                        quizQuestion4.setText("Q4." + question44);
                        quizOption4A.setText(option44A);
                        quizOption4B.setText(option44B);

                        String question6 = snapshot.child("question6/text").getValue(String.class);
                        String option6A = snapshot.child("question6/A").getValue(String.class);
                        String option6B = snapshot.child("question6/B").getValue(String.class);

                        quizQuestion6.setText("Q6." + question6);
                        quizOption6A.setText(option6A);
                        quizOption6B.setText(option6B);


                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    // Handle the error
                }
            });
        }else {

            quiz2Ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        // Retrieve the questions and options

                        String question5 = snapshot.child("question2/text").getValue(String.class);
                        String option5A = snapshot.child("question2/A").getValue(String.class);
                        String option5B = snapshot.child("question2/B").getValue(String.class);

                        quizQuestion5.setText("Q5." + question5);
                        quizOption5A.setText(option5A);
                        quizOption5B.setText(option5B);

                        String question4 = snapshot.child("question1/text").getValue(String.class);
                        String option4A = snapshot.child("question1/A").getValue(String.class);
                        String option4B = snapshot.child("question1/B").getValue(String.class);

                        quizQuestion4.setText("Q4." + question4);
                        quizOption4A.setText(option4A);
                        quizOption4B.setText(option4B);

                        String question6 = snapshot.child("question3/text").getValue(String.class);
                        String option6A = snapshot.child("question3/A").getValue(String.class);
                        String option6B = snapshot.child("question3/B").getValue(String.class);

                        quizQuestion6.setText("Q6." + question6);
                        quizOption6A.setText(option6A);
                        quizOption6B.setText(option6B);


                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    // Handle the error
                }
            });
        }
        next1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the selected options
                int selectedOption1 = group1.getCheckedRadioButtonId();
                int selectedOption2 = group2.getCheckedRadioButtonId();
                int selectedOption3 = group3.getCheckedRadioButtonId();

// Convert the option IDs to strings
                String selectedOption1String = selectedOption1 == R.id.qanswer7 ? "A" : "B";
                String selectedOption2String = selectedOption2 == R.id.qanswer9 ? "A" : "B";
                String selectedOption3String = selectedOption3 == R.id.qanswer11 ? "A" : "B";

// Create a Firebase reference to the user's quiz results
                DatabaseReference userQuizRef = FirebaseDatabase.getInstance().getReference()
                        .child("quiz_results")
                        .child(username);

// Store the selected options in Firebase
                userQuizRef.child("question4").setValue(selectedOption1String);
                userQuizRef.child("question5").setValue(selectedOption2String);
                userQuizRef.child("question6").setValue(selectedOption3String);

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