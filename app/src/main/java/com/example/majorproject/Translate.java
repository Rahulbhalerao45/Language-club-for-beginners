package com.example.majorproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Translate extends AppCompatActivity {

    Button backButton;
    TextView Language1, Language2, Language3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);

        backButton = findViewById(R.id.back_button);
        Language1 = findViewById(R.id.language1_textview);
        Language2 = findViewById(R.id.language2_textview);
        Language3 = findViewById(R.id.language3_textview);

        // Retrieve the selected languages passed from MainActivity
        Intent intent = getIntent();
        String language1 = intent.getStringExtra("LANGUAGE1");
        String language2 = intent.getStringExtra("LANGUAGE2");
        String language3 = intent.getStringExtra("LANGUAGE3");

        // Set the selected languages in the TextViews
        Language1.setText(language1);
        Language2.setText(language2);
        Language3.setText(language3);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Translate.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
