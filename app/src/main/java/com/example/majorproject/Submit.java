package com.example.majorproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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

public class Submit extends AppCompatActivity {


    EditText mainUsername;
    Button returnButton;
    String username, language, language_one, language_two, language_three;
    TextView question1, question2, question3, answer1, answer2, answer3,quiz_view;

    DatabaseReference quiz1Ref, quiz2Ref, quiz3Ref;// reference to the quiz data in Firebase

    FirebaseDatabase Reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);

        // Retrieve the username passed from LoginActivity
        String username = getIntent().getStringExtra("USERNAME");
        String language = getIntent().getStringExtra("LANGUAGE");

        String language_one = getIntent().getStringExtra("LANGUAGE1");
        String language_two = getIntent().getStringExtra("LANGUAGE2");
        String language_three = getIntent().getStringExtra("LANGUAGE3");

        question1 = findViewById(R.id.quiz_question1);
        question1.setEnabled(false);
        question2 = findViewById(R.id.quiz_question2);
        question2.setEnabled(false);
        question3 = findViewById(R.id.quiz_question3);
        question3.setEnabled(false);
        answer1 = findViewById(R.id.quiz_question11);
        answer2 = findViewById(R.id.quiz_question22);
        answer3 = findViewById(R.id.quiz_question33);
        mainUsername = findViewById(R.id.quiz_main_username);
        returnButton = findViewById(R.id.submit_return_button);
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
                    String answer11 = snapshot.child("question1/answer").getValue(String.class);

                    answer1.setText(answer11);

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
                    String answer22 = snapshot.child("question1/answer").getValue(String.class);

                    answer2.setText(answer22);


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
                    String answer33 = snapshot.child("question1/answer").getValue(String.class);

                    answer3.setText(answer33);

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
                Intent intent = new Intent(Submit.this, Quiz.class);
                intent.putExtra("USERNAME", username);
                intent.putExtra("LANGUAGE", language);
                intent.putExtra("LANGUAGE1", language_one);
                intent.putExtra("LANGUAGE2", language_two);
                intent.putExtra("LANGUAGE3", language_three);
                startActivity(intent);
            }
        });

    }
}