package com.example.majorproject;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Profile extends AppCompatActivity {

    EditText mainUsername, ranking;

    Spinner search;

    Button return2Button, logoutButton, updateButton;

    String[] item = {"Hindi(हिंदी)", "Marathi(मराठी)", "Telugu(తెలుగు)", "Tamil(தமிழ்)", "Bengali(বাঙ্গালি)"};

    AutoCompleteTextView selectLanguage4;

    ArrayAdapter<String> adapterItems4;

    String username, selectedLanguage4, language;

    DatabaseReference databaseReference;

    TextView logoutRedirectText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        username = getIntent().getStringExtra("USERNAME");
        language = getIntent().getStringExtra("LANGUAGE");
        String selectedLanguage1 = getIntent().getStringExtra("LANGUAGE1");
        String selectedLanguage2 = getIntent().getStringExtra("LANGUAGE2");
        String selectedLanguage3 = getIntent().getStringExtra("LANGUAGE3");

        mainUsername = findViewById(R.id.main_username5);
        ranking = findViewById(R.id.main_profile3);
        search = findViewById(R.id.main_profile4);
        return2Button = findViewById(R.id.return2_button);
        logoutRedirectText = findViewById(R.id.profile_logout);
        updateButton = findViewById(R.id.update_button);
        selectLanguage4 = findViewById(R.id.auto_complete_txt4);

        mainUsername.setText("" + username + "--" + language + "");
        mainUsername.setEnabled(false); // disable editing of the username field

        adapterItems4 = new ArrayAdapter<>(this, R.layout.language4_item, item);
        selectLanguage4.setAdapter(adapterItems4);

        selectLanguage4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedLanguage4 = adapterView.getItemAtPosition(i).toString();
            }
        });

        return2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this, Learning.class);
                intent.putExtra("USERNAME", username);
                intent.putExtra("LANGUAGE", language);
                intent.putExtra("LANGUAGE1", selectedLanguage1);
                intent.putExtra("LANGUAGE2", selectedLanguage2);
                intent.putExtra("LANGUAGE3", selectedLanguage3);
                startActivity(intent);
            }
        });

        logoutRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedLanguage4 != null && !selectedLanguage4.isEmpty()) {
                    // Update language in Firebase
                    databaseReference = FirebaseDatabase.getInstance().getReference().child("users").child(username);
                    databaseReference.child("language").setValue(selectedLanguage4);

                    // Update language in current activity
                    mainUsername.setText("" + username + "--" + selectedLanguage4 + "");
                    language = selectedLanguage4;
                    Toast.makeText(Profile.this, "Language updated successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Profile.this, "Please select a language", Toast.LENGTH_SHORT).show();
                }

            }

        });
    }
}
