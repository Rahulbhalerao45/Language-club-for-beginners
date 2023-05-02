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

public class Submit1 extends AppCompatActivity {



    Button returnButton;

    String username, language, language_one, language_two, language_three;


    RadioButton answer1, answer2, answer3, answer4, answer5, answer6, answer7, answer8, answer9;

    TextView scoreTextView, quiz_view, mainUsername;

    DatabaseReference quiz1Ref, quiz2Ref, quiz3Ref;// reference to the quiz data in Firebase

    FirebaseDatabase Reference;

    FirebaseDatabase database2;
    DatabaseReference reference2;

    FirebaseDatabase database3;
    DatabaseReference reference3;
    String str1="";
    String str2="";
    String str3="";
    String str4="";
    String str5="";
    String str6="";
    String str7="";
    String str8="";
    String str9="";
    String str11="";
    String str22="";
    String str33="";
    String str44="";
    String str55="";
    String str66="";
    String str77="";
    String str88="";
    String str99="";

    String S1;


    int score1=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit1);

        // Retrieve the username passed from LoginActivity
        String username = getIntent().getStringExtra("USERNAME");
        String language = getIntent().getStringExtra("LANGUAGE");

        String language_one = getIntent().getStringExtra("LANGUAGE1");
        String language_two = getIntent().getStringExtra("LANGUAGE2");
        String language_three = getIntent().getStringExtra("LANGUAGE3");

        String selectedLanguage1 = getIntent().getStringExtra("LANGUAGE1");
        String selectedLanguage2 = getIntent().getStringExtra("LANGUAGE2");
        String selectedLanguage3 = getIntent().getStringExtra("LANGUAGE3");

        scoreTextView = findViewById(R.id.correct_answer);

        answer1 = findViewById(R.id.answer111);
        answer2 = findViewById(R.id.answer222);
        answer3 = findViewById(R.id.answer333);
        answer4 = findViewById(R.id.answer444);
        answer5 = findViewById(R.id.answer555);
        answer6 = findViewById(R.id.answer666);
        answer7 = findViewById(R.id.answer777);
        answer8 = findViewById(R.id.answer888);
        answer9 = findViewById(R.id.answer999);
        mainUsername = findViewById(R.id.quiz_main_username);
        returnButton = findViewById(R.id.submit1_return_button);
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
        DatabaseReference quiz4Ref = Reference.getReference("quiz_results").child(username);

        quiz4Ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    // Retrieve the questions and options
                    String question1 = snapshot.child("question1").getValue(String.class);
                    String question2 = snapshot.child("question2").getValue(String.class);
                    String question3 = snapshot.child("question3").getValue(String.class);
                    String question4 = snapshot.child("question4").getValue(String.class);
                    String question5 = snapshot.child("question5").getValue(String.class);
                    String question6 = snapshot.child("question6").getValue(String.class);
                    String question7 = snapshot.child("question7").getValue(String.class);
                    String question8 = snapshot.child("question8").getValue(String.class);
                    String question9 = snapshot.child("question9").getValue(String.class);

                    str1=question1;
                    str2=question2;
                    str3=question3;
                    str4=question4;
                    str5=question5;
                    str6=question6;
                    str7=question7;
                    str8=question8;
                    str9=question9;

                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle the error
            }
        });

        database3 = FirebaseDatabase.getInstance();
        reference3 = database3.getReference("LearningPoints");

        reference3.child(username).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Integer currentLearningPoint = dataSnapshot.getValue(Integer.class);

                if (currentLearningPoint == null) {
                    currentLearningPoint = 0;
                }


                if (currentLearningPoint <= 15) {
                    FirebaseDatabase Reference = FirebaseDatabase.getInstance();

                    DatabaseReference quiz1Ref = Reference.getReference("quiz").child(language_one);
                    DatabaseReference quiz2Ref = Reference.getReference("quiz").child(language_two);
                    DatabaseReference quiz3Ref = Reference.getReference("quiz").child(language_three);

                    quiz1Ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                // Retrieve the questions and options
                                String question11 = snapshot.child("question1/answer").getValue(String.class);
                                String question22 = snapshot.child("question2/answer").getValue(String.class);
                                String question33 = snapshot.child("question3/answer").getValue(String.class);

                                str11=question11;
                                str22=question22;
                                str33=question33;

                                answer1.setText("Correct answer for question1 is " + question11);
                                answer1.setClickable(false);
                                answer1.setChecked(true);
                                answer2.setText( "Correct answer for question2 is " + question22);
                                answer2.setClickable(false);
                                answer2.setChecked(true);
                                answer3.setText("Correct answer for question3 is " + question33);
                                answer3.setClickable(false);
                                answer3.setChecked(true);

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

                                String question44 = snapshot.child("question4/answer").getValue(String.class);
                                String question55 = snapshot.child("question5/answer").getValue(String.class);
                                String question66 = snapshot.child("question6/answer").getValue(String.class);

                                str44=question44;
                                str55=question55;
                                str66=question66;

                                answer4.setText( "Correct answer for question4 is " + question44);
                                answer4.setClickable(false);
                                answer4.setChecked(true);
                                answer5.setText( "Correct answer for question5 is " + question55);
                                answer5.setClickable(false);
                                answer5.setChecked(true);
                                answer6.setText( "Correct answer for question6 is " + question66);
                                answer6.setClickable(false);
                                answer6.setChecked(true);


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

                                String question77 = snapshot.child("question7/answer").getValue(String.class);
                                String question88 = snapshot.child("question8/answer").getValue(String.class);
                                String question99 = snapshot.child("question9/answer").getValue(String.class);

                                str77 = question77;
                                str88 = question88;
                                str99 = question99;

                                answer7.setText("Correct answer for question7 is " + question77);
                                answer7.setClickable(false);
                                answer7.setChecked(true);
                                answer8.setText("Correct answer for question8 is " + question88);
                                answer8.setClickable(false);
                                answer8.setChecked(true);
                                answer9.setText("Correct answer for question9 is " + question99);
                                answer9.setClickable(false);
                                answer9.setChecked(true);

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            // Handle the error
                        }
                    });
                    
                    
                } else if (currentLearningPoint <= 25) {
                    FirebaseDatabase Reference = FirebaseDatabase.getInstance();

                    DatabaseReference quiz1Ref = Reference.getReference("quiz").child(language_one);
                    DatabaseReference quiz2Ref = Reference.getReference("quiz").child(language_two);
                    DatabaseReference quiz3Ref = Reference.getReference("quiz").child(language_three);

                    quiz1Ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                // Retrieve the questions and options
                                String question11 = snapshot.child("question10/answer").getValue(String.class);
                                String question22 = snapshot.child("question11/answer").getValue(String.class);
                                String question33 = snapshot.child("question12/answer").getValue(String.class);

                                str11=question11;
                                str22=question22;
                                str33=question33;

                                answer1.setText("Correct answer for question10 is " + question11);
                                answer1.setClickable(false);
                                answer1.setChecked(true);
                                answer2.setText( "Correct answer for question11 is " + question22);
                                answer2.setClickable(false);
                                answer2.setChecked(true);
                                answer3.setText("Correct answer for question12 is " + question33);
                                answer3.setClickable(false);
                                answer3.setChecked(true);

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

                                String question44 = snapshot.child("question13/answer").getValue(String.class);
                                String question55 = snapshot.child("question14/answer").getValue(String.class);
                                String question66 = snapshot.child("question15/answer").getValue(String.class);

                                str44=question44;
                                str55=question55;
                                str66=question66;

                                answer4.setText( "Correct answer for question13 is " + question44);
                                answer4.setClickable(false);
                                answer4.setChecked(true);
                                answer5.setText( "Correct answer for question14 is " + question55);
                                answer5.setClickable(false);
                                answer5.setChecked(true);
                                answer6.setText( "Correct answer for question15 is " + question66);
                                answer6.setClickable(false);
                                answer6.setChecked(true);


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

                                String question77 = snapshot.child("question16/answer").getValue(String.class);
                                String question88 = snapshot.child("question17/answer").getValue(String.class);
                                String question99 = snapshot.child("question18/answer").getValue(String.class);

                                str77 = question77;
                                str88 = question88;
                                str99 = question99;

                                answer7.setText("Correct answer for question16 is " + question77);
                                answer7.setClickable(false);
                                answer7.setChecked(true);
                                answer8.setText("Correct answer for question17 is " + question88);
                                answer8.setClickable(false);
                                answer8.setChecked(true);
                                answer9.setText("Correct answer for question18 is " + question99);
                                answer9.setClickable(false);
                                answer9.setChecked(true);

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            // Handle the error
                        }
                    });
                } else if (currentLearningPoint > 25) {
                    FirebaseDatabase Reference = FirebaseDatabase.getInstance();

                    DatabaseReference quiz1Ref = Reference.getReference("quiz").child(language_one);
                    DatabaseReference quiz2Ref = Reference.getReference("quiz").child(language_two);
                    DatabaseReference quiz3Ref = Reference.getReference("quiz").child(language_three);

                    quiz1Ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                // Retrieve the questions and options
                                String question11 = snapshot.child("question19/answer").getValue(String.class);
                                String question22 = snapshot.child("question20/answer").getValue(String.class);
                                String question33 = snapshot.child("question21/answer").getValue(String.class);

                                str11=question11;
                                str22=question22;
                                str33=question33;

                                answer1.setText("Correct answer for question19 is " + question11);
                                answer1.setClickable(false);
                                answer1.setChecked(true);
                                answer2.setText( "Correct answer for question20 is " + question22);
                                answer2.setClickable(false);
                                answer2.setChecked(true);
                                answer3.setText("Correct answer for question21 is " + question33);
                                answer3.setClickable(false);
                                answer3.setChecked(true);

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

                                String question44 = snapshot.child("question22/answer").getValue(String.class);
                                String question55 = snapshot.child("question23/answer").getValue(String.class);
                                String question66 = snapshot.child("question24/answer").getValue(String.class);

                                str44=question44;
                                str55=question55;
                                str66=question66;

                                answer4.setText( "Correct answer for question22 is " + question44);
                                answer4.setClickable(false);
                                answer4.setChecked(true);
                                answer5.setText( "Correct answer for question23 is " + question55);
                                answer5.setClickable(false);
                                answer5.setChecked(true);
                                answer6.setText( "Correct answer for question24 is " + question66);
                                answer6.setClickable(false);
                                answer6.setChecked(true);


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

                                String question77 = snapshot.child("question25/answer").getValue(String.class);
                                String question88 = snapshot.child("question26/answer").getValue(String.class);
                                String question99 = snapshot.child("question27/answer").getValue(String.class);

                                str77 = question77;
                                str88 = question88;
                                str99 = question99;

                                answer7.setText("Correct answer for question25 is " + question77);
                                answer7.setClickable(false);
                                answer7.setChecked(true);
                                answer8.setText("Correct answer for question26 is " + question88);
                                answer8.setClickable(false);
                                answer8.setChecked(true);
                                answer9.setText("Correct answer for question27 is " + question99);
                                answer9.setClickable(false);
                                answer9.setChecked(true);

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            // Handle the error
                        }
                    });
                    
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(str1.equals(str11)){
                    score1++;
                }
                if (str2.equals(str22)) {
                    score1++;
                }
                if (str3.equals(str33)) {
                    score1++;
                }

                if (str4.equals(str44)) {
                    score1++;
                }
                if (str5.equals(str55)) {
                    score1++;
                }
                if (str6.equals(str66)) {
                    score1++;
                }
                if (str7.equals(str77)) {
                    score1++;
                }
                if (str8.equals(str88)) {
                    score1++;
                }
                if (str9.equals(str99)) {
                    score1++;
                }
                S1 = String.valueOf(score1);
                Toast.makeText(Submit1.this, "You scored " + score1 + " out of 9", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Submit1.this, Profile.class);
                intent.putExtra("USERNAME", username);
                intent.putExtra("LANGUAGE", language);
                intent.putExtra("LANGUAGE1", language_one);
                intent.putExtra("LANGUAGE2", language_two);
                intent.putExtra("LANGUAGE3", language_three);
                intent.putExtra("LANGUAGE1", selectedLanguage1);
                intent.putExtra("LANGUAGE2", selectedLanguage2);
                intent.putExtra("LANGUAGE3", selectedLanguage3);
                intent.putExtra("SCORE", S1);
                startActivity(intent);

                database2 = FirebaseDatabase.getInstance();
                reference2 = database2.getReference("LearningPoints");

// Assuming `username` is the variable that holds the user's username
                reference2.child(username).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Retrieve the current value of the learning point
                        Integer currentLearningPoint = dataSnapshot.getValue(Integer.class);

                        // If the user has no learning points yet, the current value will be null, so set it to 0
                        if (currentLearningPoint == null) {
                            currentLearningPoint = 0;
                        }

                        // Increment the learning point by 1
                        Integer newLearningPoint = currentLearningPoint + score1;

                        // Set the updated value to the database
                        reference2.child(username).setValue(newLearningPoint);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Handle any errors that occur
                    }
                });
            }
        });

    }
}