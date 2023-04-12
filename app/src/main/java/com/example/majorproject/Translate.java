package com.example.majorproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Translate extends AppCompatActivity {

    EditText mainUsername, mainEnterText;
    Button backButton;
    TextView Language1, Language2, Language3;

    String username, language;

    TextView logoutRedirectText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);

        // Retrieve the username passed from LoginActivity
        String username = getIntent().getStringExtra("USERNAME");
        String language = getIntent().getStringExtra("LANGUAGE");
        mainUsername = findViewById(R.id.main_username1);
        backButton = findViewById(R.id.back_button);
        Language1 = findViewById(R.id.language1_textview);
        Language2 = findViewById(R.id.language2_textview);
        Language3 = findViewById(R.id.language3_textview);
        logoutRedirectText = findViewById(R.id.logout2);
        mainEnterText = findViewById(R.id.main_entertext1);

        // Retrieve the selected languages passed from MainActivity
        Intent intent = getIntent();
        String language1 = intent.getStringExtra("LANGUAGE1");
        String language2 = intent.getStringExtra("LANGUAGE2");
        String language3 = intent.getStringExtra("LANGUAGE3");

        // Set the selected languages in the TextViews
        Language1.setText(language1);
        Language2.setText(language2);
        Language3.setText(language3);

        mainUsername.setText("" + username + "--" + language + "");
        mainUsername.setEnabled(false); // disable editing of the username field

        String enteredText = intent.getStringExtra("ENTERED_TEXT");
        mainEnterText.setText(enteredText);
        mainEnterText.setEnabled(false);



        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Translate.this, MainActivity.class);
                intent.putExtra("USERNAME", username);
                intent.putExtra("LANGUAGE", language);
                startActivity(intent);
            }
        });

        logoutRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Translate.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
