package com.example.majorproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText mainUsername, mainEnterText;

    Button mainButton, learningButton;

    FirebaseDatabase database;

    DatabaseReference reference;

    String[] item = {"Hindi", "Marathi", "Telugu", "Tamil", "Malayalam"};

    AutoCompleteTextView selectLanguage1, selectLanguage2, selectLanguage3;

    ArrayAdapter adapterItems1, adapterItems2, adapterItems3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainUsername = findViewById(R.id.main_username);
        mainEnterText = findViewById(R.id.main_entertext);
        mainButton = findViewById(R.id.main_button);
        learningButton = findViewById(R.id.more_button);
        selectLanguage1 = findViewById(R.id.auto_complete_txt1);
        selectLanguage2 = findViewById(R.id.auto_complete_txt2);
        selectLanguage3 = findViewById(R.id.auto_complete_txt3);


        adapterItems1 = new ArrayAdapter<String>(this,R.layout.language1_item, item);
        adapterItems2 = new ArrayAdapter<String>(this,R.layout.language2_item, item);
        adapterItems3 = new ArrayAdapter<String>(this,R.layout.language3_item, item);


        selectLanguage1.setAdapter(adapterItems1);

        selectLanguage2.setAdapter(adapterItems2);

        selectLanguage3.setAdapter(adapterItems3);

        selectLanguage1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();


            }
        });

        selectLanguage2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();


            }
        });

        selectLanguage3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();


            }
        });




        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                database = FirebaseDatabase.getInstance();
                reference = database.getReference("history");

                String username = mainUsername.getText().toString();
                String entertext = mainEnterText.getText().toString();
                String selectlanguage1 = selectLanguage1.getText().toString();
                String selectlanguage2 = selectLanguage2.getText().toString();
                String selectlanguage3 = selectLanguage3.getText().toString();

                HelperClass1 helperClass1 = new HelperClass1(username, entertext, selectlanguage1, selectlanguage2, selectlanguage3);
                reference.child(username).setValue(helperClass1);


                Intent intent = new Intent(MainActivity.this, Translate.class);
                startActivity(intent);
            }
        });




        learningButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, Learning.class);
                startActivity(intent);
            }
        });
    }
}