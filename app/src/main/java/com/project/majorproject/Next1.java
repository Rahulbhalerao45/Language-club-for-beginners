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

public class Next1 extends AppCompatActivity {



    Button returnButton, submit1Button;
    RadioButton quizOption7A, quizOption7B, quizOption8A, quizOption8B, quizOption9A, quizOption9B;

    RadioGroup group1, group2, group3;

    String username, language, language_one, language_two, language_three, str3;
    TextView logoutRedirectText, quizQuestion7, quizQuestion8, quizQuestion9, quiz_view, mainUsername;

    DatabaseReference quiz1Ref, quiz2Ref, quiz3Ref;// reference to the quiz data in Firebase

    FirebaseDatabase Reference;

    FirebaseDatabase database2;
    DatabaseReference reference2;


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

                    DatabaseReference quiz2Ref = Reference.getReference("quiz").child(language_three);

                    quiz2Ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                // Retrieve the questions and options
                                String question1 = snapshot.child("question7/text").getValue(String.class);
                                String option1A = snapshot.child("question7/A").getValue(String.class);
                                String option1B = snapshot.child("question7/B").getValue(String.class);

                                quizQuestion7.setText("Q7 : " + question1);
                                quizOption7A.setText(option1A);
                                quizOption7B.setText(option1B);

                                String question2 = snapshot.child("question8/text").getValue(String.class);
                                String option2A = snapshot.child("question8/A").getValue(String.class);
                                String option2B = snapshot.child("question8/B").getValue(String.class);

                                quizQuestion8.setText("Q8 : " + question2);
                                quizOption8A.setText(option2A);
                                quizOption8B.setText(option2B);

                                String question3 = snapshot.child("question9/text").getValue(String.class);
                                String option3A = snapshot.child("question9/A").getValue(String.class);
                                String option3B = snapshot.child("question9/B").getValue(String.class);

                                quizQuestion9.setText("Q9 : " + question3);
                                quizOption9A.setText(option3A);
                                quizOption9B.setText(option3B);

                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                } else  if (currentLearningPoint <= 25) {

                    FirebaseDatabase Reference = FirebaseDatabase.getInstance();

                    DatabaseReference quiz1Ref = Reference.getReference("quiz").child(language_three);

                    quiz1Ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                // Retrieve the questions and options
                                String question1 = snapshot.child("question16/text").getValue(String.class);
                                String option1A = snapshot.child("question16/A").getValue(String.class);
                                String option1B = snapshot.child("question16/B").getValue(String.class);

                                quizQuestion7.setText("Q16 : " + question1);
                                quizOption7A.setText(option1A);
                                quizOption7B.setText(option1B);

                                String question2 = snapshot.child("question17/text").getValue(String.class);
                                String option2A = snapshot.child("question17/A").getValue(String.class);
                                String option2B = snapshot.child("question17/B").getValue(String.class);

                                quizQuestion8.setText("Q17 : " + question2);
                                quizOption8A.setText(option2A);
                                quizOption8B.setText(option2B);

                                String question3 = snapshot.child("question18/text").getValue(String.class);
                                String option3A = snapshot.child("question18/A").getValue(String.class);
                                String option3B = snapshot.child("question18/B").getValue(String.class);

                                quizQuestion9.setText("Q18 : " + question3);
                                quizOption9A.setText(option3A);
                                quizOption9B.setText(option3B);

                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                } else if (currentLearningPoint > 25) {
                    FirebaseDatabase Reference = FirebaseDatabase.getInstance();

                    DatabaseReference quiz1Ref = Reference.getReference("quiz").child(language_three);

                    quiz1Ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                // Retrieve the questions and options
                                String question1 = snapshot.child("question25/text").getValue(String.class);
                                String option1A = snapshot.child("question25/A").getValue(String.class);
                                String option1B = snapshot.child("question25/B").getValue(String.class);

                                quizQuestion7.setText("Q25 : " + question1);
                                quizOption7A.setText(option1A);
                                quizOption7B.setText(option1B);

                                String question2 = snapshot.child("question26/text").getValue(String.class);
                                String option2A = snapshot.child("question26/A").getValue(String.class);
                                String option2B = snapshot.child("question26/B").getValue(String.class);

                                quizQuestion8.setText("Q26 : " + question2);
                                quizOption8A.setText(option2A);
                                quizOption8B.setText(option2B);

                                String question3 = snapshot.child("question27/text").getValue(String.class);
                                String option3A = snapshot.child("question27/A").getValue(String.class);
                                String option3B = snapshot.child("question27/B").getValue(String.class);

                                quizQuestion9.setText("Q27 : " + question3);
                                quizOption9A.setText(option3A);
                                quizOption9B.setText(option3B);

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
                // Handle any errors that occur
            }
        });

        submit1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedOption1 = group1.getCheckedRadioButtonId();
                int selectedOption2 = group2.getCheckedRadioButtonId();
                int selectedOption3 = group3.getCheckedRadioButtonId();

                String selectedOption1String = selectedOption1 == -1 ? "N/A" : (selectedOption1 == R.id.qanswer13 ? "A" : "B");
                String selectedOption2String = selectedOption2 == -1 ? "N/A" : (selectedOption2 == R.id.qanswer15 ? "A" : "B");
                String selectedOption3String = selectedOption3 == -1 ? "N/A" : (selectedOption3 == R.id.qanswer17 ? "A" : "B");

                DatabaseReference userQuizRef = FirebaseDatabase.getInstance().getReference()
                        .child("quiz_results")
                        .child(username);

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