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

import java.util.HashMap;

public class Learning extends AppCompatActivity {

    Button learningButton1, learningButton2, learningButton3, return3Button;

    EditText mainUsername;

    String username, language;

    TextView logoutRedirectText;

    DatabaseReference databaseReference, quizReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning);

        String username = getIntent().getStringExtra("USERNAME");
        String language = getIntent().getStringExtra("LANGUAGE");

        mainUsername = findViewById(R.id.main_username2);
        learningButton1 = findViewById(R.id.quiz_button);
        learningButton2 = findViewById(R.id.chat_button);
        learningButton3 = findViewById(R.id.profile_button);
        return3Button = findViewById(R.id.return3_button);
        logoutRedirectText = findViewById(R.id.logout3);

        mainUsername.setText("" + username + "--" + language + "");
        mainUsername.setEnabled(false); // disable editing of the username field

        learningButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                            // Start the quiz activity and pass the questions and options to it
                            Intent intent = new Intent(Learning.this, Quiz.class);
                            intent.putExtra("USERNAME", username);
                            intent.putExtra("LANGUAGE", language);
                            startActivity(intent);
                        }
                    });


        learningButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Learning.this, Chatgroup.class);
                intent.putExtra("USERNAME", username);
                intent.putExtra("LANGUAGE", language);
                startActivity(intent);
            }
        });

        learningButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Learning.this, Profile.class);
                intent.putExtra("USERNAME", username);
                intent.putExtra("LANGUAGE", language);
                startActivity(intent);
            }
        });

        mainUsername.setText("" + username + "--" + language + "");
        mainUsername.setEnabled(false); // disable editing of the username field

        return3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(Learning.this, MainActivity.class);
                intent.putExtra("USERNAME", username);
                intent.putExtra("LANGUAGE", language);
                startActivity(intent);
            }
        });

        logoutRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Learning.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}