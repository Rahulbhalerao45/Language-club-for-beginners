package com.project.majorproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.project.majorproject.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Next extends AppCompatActivity {



    Button returnButton, next1Button;
    RadioButton quizOption4A, quizOption4B, quizOption5A, quizOption5B, quizOption6A, quizOption6B;

    RadioGroup group1, group2, group3;

    String username, language, language_one, language_two, language_three, str3;
    TextView logoutRedirectText, quizQuestion4, quizQuestion5, quizQuestion6, quiz_view, mainUsername;

    DatabaseReference quiz1Ref, quiz2Ref, quiz3Ref;// reference to the quiz data in Firebase

    FirebaseDatabase Reference;

    FirebaseDatabase database2;
    DatabaseReference reference2;

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
        mainUsername.setEnabled(false);

        quiz_view.setText("" + language_one + "-" + language_two + "-" + language_three + "");
        quiz_view.setEnabled(false);
        quiz_view.setVisibility(View.INVISIBLE);

        database2 = FirebaseDatabase.getInstance();
        reference2 = database2.getReference("LearningPoints");

        reference2.child(username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Integer currentLearningPoint = dataSnapshot.getValue(Integer.class);

                if (currentLearningPoint == null) {
                    currentLearningPoint = 0;
                }


                if (currentLearningPoint <= 15) {

                    FirebaseDatabase Reference = FirebaseDatabase.getInstance();

                    DatabaseReference quiz2Ref = Reference.getReference("quiz").child(language_two);

                    quiz2Ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                // Retrieve the questions and options
                                String question1 = snapshot.child("question4/text").getValue(String.class);
                                String option1A = snapshot.child("question4/A").getValue(String.class);
                                String option1B = snapshot.child("question4/B").getValue(String.class);

                                quizQuestion4.setText("Q4 : " + question1);
                                quizOption4A.setText(option1A);
                                quizOption4B.setText(option1B);

                                String question2 = snapshot.child("question5/text").getValue(String.class);
                                String option2A = snapshot.child("question5/A").getValue(String.class);
                                String option2B = snapshot.child("question5/B").getValue(String.class);

                                quizQuestion5.setText("Q5 : " + question2);
                                quizOption5A.setText(option2A);
                                quizOption5B.setText(option2B);

                                String question3 = snapshot.child("question6/text").getValue(String.class);
                                String option3A = snapshot.child("question6/A").getValue(String.class);
                                String option3B = snapshot.child("question6/B").getValue(String.class);

                                quizQuestion6.setText("Q6 : " + question3);
                                quizOption6A.setText(option3A);
                                quizOption6B.setText(option3B);

                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                } else  if (currentLearningPoint <= 25) {

                    FirebaseDatabase Reference = FirebaseDatabase.getInstance();

                    DatabaseReference quiz1Ref = Reference.getReference("quiz").child(language_two);

                    quiz1Ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                // Retrieve the questions and options
                                String question1 = snapshot.child("question13/text").getValue(String.class);
                                String option1A = snapshot.child("question13/A").getValue(String.class);
                                String option1B = snapshot.child("question13/B").getValue(String.class);

                                quizQuestion4.setText("Q13 : " + question1);
                                quizOption4A.setText(option1A);
                                quizOption4B.setText(option1B);

                                String question2 = snapshot.child("question14/text").getValue(String.class);
                                String option2A = snapshot.child("question14/A").getValue(String.class);
                                String option2B = snapshot.child("question14/B").getValue(String.class);

                                quizQuestion5.setText("Q14 : " + question2);
                                quizOption5A.setText(option2A);
                                quizOption5B.setText(option2B);

                                String question3 = snapshot.child("question15/text").getValue(String.class);
                                String option3A = snapshot.child("question15/A").getValue(String.class);
                                String option3B = snapshot.child("question15/B").getValue(String.class);

                                quizQuestion6.setText("Q15 : " + question3);
                                quizOption6A.setText(option3A);
                                quizOption6B.setText(option3B);

                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                } else if (currentLearningPoint > 25) {
                    FirebaseDatabase Reference = FirebaseDatabase.getInstance();

                    DatabaseReference quiz1Ref = Reference.getReference("quiz").child(language_two);

                    quiz1Ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                // Retrieve the questions and options
                                String question1 = snapshot.child("question22/text").getValue(String.class);
                                String option1A = snapshot.child("question22/A").getValue(String.class);
                                String option1B = snapshot.child("question22/B").getValue(String.class);

                                quizQuestion4.setText("Q22 : " + question1);
                                quizOption4A.setText(option1A);
                                quizOption4B.setText(option1B);

                                String question2 = snapshot.child("question23/text").getValue(String.class);
                                String option2A = snapshot.child("question23/A").getValue(String.class);
                                String option2B = snapshot.child("question23/B").getValue(String.class);

                                quizQuestion5.setText("Q23 : " + question2);
                                quizOption5A.setText(option2A);
                                quizOption5B.setText(option2B);

                                String question3 = snapshot.child("question24/text").getValue(String.class);
                                String option3A = snapshot.child("question24/A").getValue(String.class);
                                String option3B = snapshot.child("question24/B").getValue(String.class);

                                quizQuestion6.setText("Q24 : " + question3);
                                quizOption6A.setText(option3A);
                                quizOption6B.setText(option3B);

                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


        next1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedOption1 = group1.getCheckedRadioButtonId();
                int selectedOption2 = group2.getCheckedRadioButtonId();
                int selectedOption3 = group3.getCheckedRadioButtonId();

                String selectedOption1String = selectedOption1 == -1 ? "N/A" : (selectedOption1 == R.id.qanswer7 ? "A" : "B");
                String selectedOption2String = selectedOption2 == -1 ? "N/A" : (selectedOption2 == R.id.qanswer9 ? "A" : "B");
                String selectedOption3String = selectedOption3 == -1 ? "N/A" : (selectedOption3 == R.id.qanswer11 ? "A" : "B");

                DatabaseReference userQuizRef = FirebaseDatabase.getInstance().getReference()
                        .child("quiz_results")
                        .child(username);

                userQuizRef.child("question4").setValue(selectedOption1String);
                userQuizRef.child("question5").setValue(selectedOption2String);
                userQuizRef.child("question6").setValue(selectedOption3String);


                Intent intent = new Intent(Next.this, Next1.class);
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