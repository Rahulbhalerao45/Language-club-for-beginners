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

public class Profile extends AppCompatActivity {

    EditText mainUsername;

    Button return2Button, logoutButton;

    String[] item = {"Hindi(हिंदी)", "Marathi(मराठी)", "Telugu(తెలుగు)", "Tamil(தமிழ்)", "Malayalam(മലയാളം)"};

    AutoCompleteTextView selectLanguage4;

    ArrayAdapter adapterItems4;


    String username, selectedLanguage4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        username = getIntent().getStringExtra("USERNAME");


        mainUsername = findViewById(R.id.main_username5);
        return2Button = findViewById(R.id.return2_button);
        logoutButton = findViewById(R.id.logout_button);
        selectLanguage4 = findViewById(R.id.auto_complete_txt4);

        mainUsername.setText(username);
        mainUsername.setEnabled(false); // disable editing of the username field

        adapterItems4 = new ArrayAdapter<String>(this,R.layout.language4_item, item);

        selectLanguage4.setAdapter(adapterItems4);


        selectLanguage4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();


            }
        });

        return2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(Profile.this, Learning.class);
                intent.putExtra("USERNAME", username);
                startActivity(intent);
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(Profile.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}