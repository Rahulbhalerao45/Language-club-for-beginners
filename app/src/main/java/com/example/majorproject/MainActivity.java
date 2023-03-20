package com.example.majorproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText mainUsername, mainEnterText, mainSelectLanguage;

    Button mainButton, learningButton;

    FirebaseDatabase database;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainUsername = findViewById(R.id.main_username);
        mainEnterText = findViewById(R.id.main_entertext);
        mainSelectLanguage = findViewById(R.id.main_selectlanguage);
        mainButton = findViewById(R.id.main_button);
        learningButton = findViewById(R.id.more_button);


        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                database = FirebaseDatabase.getInstance();
                reference = database.getReference("history");

                String username = mainUsername.getText().toString();
                String entertext = mainEnterText.getText().toString();
                String selectlanguage = mainSelectLanguage.getText().toString();


                HelperClass1 helperClass1 = new HelperClass1(entertext, selectlanguage, username);
                reference.child(username).setValue(helperClass1);

                Toast.makeText(MainActivity.this, "Translating!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, Translate.class);
                startActivity(intent);
            }
        });


        learningButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "GREAT!!!!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, Learning.class);
                startActivity(intent);
            }
        });
    }
}