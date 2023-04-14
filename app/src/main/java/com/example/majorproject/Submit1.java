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


    EditText mainUsername;
    Button returnButton;

    String username, language, language_one, language_two, language_three;

    RadioGroup group10;
    RadioButton answer1, answer2, answer3, answer4, answer5, answer6, answer7, answer8, answer9;

    TextView scoreTextView, quiz_view;

    DatabaseReference quiz1Ref, quiz2Ref, quiz3Ref;// reference to the quiz data in Firebase

    FirebaseDatabase Reference;

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
        group10 = findViewById(R.id.group10);
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

        quiz1Ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    // Retrieve the questions and options
                    String question1 = snapshot.child("question1/answer").getValue(String.class);
                    String option1 = snapshot.child("question2/answer").getValue(String.class);
                    String option2 = snapshot.child("question3/answer").getValue(String.class);

                    answer1.setText("Correct answer for question1 is " + question1);
                    answer1.setClickable(false);
                    answer4.setText( "Correct answer for question4 is " + option1);
                    answer4.setClickable(false);
                    answer7.setText("Correct answer for question7 is " + option2);
                    answer7.setClickable(false);

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

                    String question1 = snapshot.child("question1/answer").getValue(String.class);
                    String option1 = snapshot.child("question2/answer").getValue(String.class);
                    String option2 = snapshot.child("question3/answer").getValue(String.class);

                    answer2.setText( "Correct answer for question2 is " + question1);
                    answer2.setClickable(false);
                    answer5.setText( "Correct answer for question5 is " + option1);
                    answer5.setClickable(false);
                    answer8.setText( "Correct answer for question8 is " + option2);
                    answer8.setClickable(false);


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

                    String question1 = snapshot.child("question1/answer").getValue(String.class);
                    String option1 = snapshot.child("question2/answer").getValue(String.class);
                    String option2 = snapshot.child("question3/answer").getValue(String.class);

                    answer3.setText( "Correct answer for question3 is " + question1);
                    answer3.setClickable(false);
                    answer6.setText( "Correct answer for question6 is " + option1);
                    answer6.setClickable(false);
                    answer9.setText( "Correct answer for question9 is " + option2);
                    answer9.setClickable(false);

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle the error
            }
        });

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Submit1.this, Learning.class);
                intent.putExtra("USERNAME", username);
                intent.putExtra("LANGUAGE", language);
                intent.putExtra("LANGUAGE1", language_one);
                intent.putExtra("LANGUAGE2", language_two);
                intent.putExtra("LANGUAGE3", language_three);
                intent.putExtra("LANGUAGE1", selectedLanguage1);
                intent.putExtra("LANGUAGE2", selectedLanguage2);
                intent.putExtra("LANGUAGE3", selectedLanguage3);
                startActivity(intent);
            }
        });

    }
}