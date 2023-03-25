package com.example.majorproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Learning extends AppCompatActivity {

    Button learningButton1, learningButton2, learningButton3, return3Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning);

        learningButton1 = findViewById(R.id.quiz_button);
        learningButton2 = findViewById(R.id.chat_button);
        learningButton3 = findViewById(R.id.profile_button);
        return3Button = findViewById(R.id.return3_button);

        learningButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(Learning.this, Quiz.class);
                startActivity(intent);
            }
        });

        learningButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(Learning.this, Chatgroup.class);
                startActivity(intent);
            }
        });

        learningButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(Learning.this, Profile.class);
                startActivity(intent);
            }
        });

        return3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(Learning.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}