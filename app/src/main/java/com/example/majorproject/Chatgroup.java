package com.example.majorproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Chatgroup extends AppCompatActivity {


    EditText mainUsername, enterText;
    Button return1Button, sendButton;

    String username, language;

    TextView logoutRedirectText, sendersText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatgroup);

        // Retrieve the username passed from LoginActivity
        String username = getIntent().getStringExtra("USERNAME");
        String language = getIntent().getStringExtra("LANGUAGE");

        enterText = findViewById(R.id.enter_chat);
        sendersText = findViewById(R.id.sender_chat);
        mainUsername = findViewById(R.id.main_username3);
        sendButton = findViewById(R.id.send_button);
        return1Button = findViewById(R.id.return1_button);
        logoutRedirectText = findViewById(R.id.logout5);

        mainUsername.setText("" + username + "--" + language + "");
        mainUsername.setEnabled(false); // disable editing of the username field

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Chatgroup.this, Chatgroup.class);
                intent.putExtra("USERNAME", username);
                intent.putExtra("LANGUAGE", language);
                startActivity(intent);
            }
        });


        return1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Chatgroup.this, Learning.class);
                intent.putExtra("USERNAME", username);
                intent.putExtra("LANGUAGE", language);
                startActivity(intent);
            }
        });

        logoutRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Chatgroup.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}