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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Profile extends AppCompatActivity {

    EditText mainUsername, ranking;

    Spinner search;

    Button return2Button, save, updateButton;

    String[] item = {"Hindi(हिंदी)", "Marathi(मराठी)", "Telugu(తెలుగు)", "Tamil(தமிழ்)", "Bengali(বাঙ্গালি)"};

    AutoCompleteTextView selectLanguage4;

    ArrayAdapter<String> adapterItems4;

    String username, selectedLanguage4, language;

    DatabaseReference databaseReference;

    FirebaseDatabase database1;
    DatabaseReference reference1;

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
        String score = getIntent().getStringExtra("SCORE");

        mainUsername = findViewById(R.id.main_username5);
        ranking = findViewById(R.id.main_profile3);
        search = findViewById(R.id.main_profile4);
        return2Button = findViewById(R.id.return2_button);
        logoutRedirectText = findViewById(R.id.profile_logout);
        save = findViewById(R.id.save);
        updateButton = findViewById(R.id.update_button);
        selectLanguage4 = findViewById(R.id.auto_complete_txt4);

        mainUsername.setText("" + username + "--" + language + "");
        mainUsername.setEnabled(false); // disable editing of the username field

        ranking.setText("You Scored " +score + " out of 9");
        ranking.setEnabled(false);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("history").child(username);

        ArrayList<String> historyList = new ArrayList<>();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    HelperClass3 helperClass3 = dataSnapshot.getValue(HelperClass3.class);
                    String historyItem = helperClass3.getEntertext() + " , " + helperClass3.getResult11() + " , " + helperClass3.getResult22() + " , " + helperClass3.getResult33();
                    historyList.add(historyItem);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(Profile.this, android.R.layout.simple_spinner_dropdown_item, historyList);
                search.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<>(Profile.this, android.R.layout.simple_spinner_dropdown_item, historyList);
        search.setAdapter(adapter);


        adapterItems4 = new ArrayAdapter<>(this, R.layout.language4_item, item);
        selectLanguage4.setAdapter(adapterItems4);

        selectLanguage4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedLanguage4 = adapterView.getItemAtPosition(i).toString();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this, Profile.class);
                intent.putExtra("USERNAME", username);
                intent.putExtra("LANGUAGE", language);
                intent.putExtra("LANGUAGE1", selectedLanguage1);
                intent.putExtra("LANGUAGE2", selectedLanguage2);
                intent.putExtra("LANGUAGE3", selectedLanguage3);
                intent.putExtra("SCORE", score);
                startActivity(intent);

                database1 = FirebaseDatabase.getInstance();
                reference1 = database1.getReference("attempts").child(username);
                String Score = ranking.getText().toString();
                String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
                HelperClass2 helperClass2 = new HelperClass2(username, Score, currentDate, currentTime);

                reference1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        int attemptsCount = (int) snapshot.getChildrenCount();

                        String attemptskey = "attempts" + (attemptsCount + 1);

                        reference1.child(attemptskey).setValue(helperClass2);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                Toast.makeText(Profile.this, "Score Saved Successfully👍👍", Toast.LENGTH_SHORT).show();
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
                intent.putExtra("SCORE", score);
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