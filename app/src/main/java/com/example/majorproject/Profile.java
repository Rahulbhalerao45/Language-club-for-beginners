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

    EditText  ranking;

    Spinner search, attempts;

    Button return2Button, updateButton, ranking1;

    String[] item = {"Hindi(हिंदी)", "Marathi(मराठी)", "Telugu(తెలుగు)", "Tamil(தமிழ்)", "Bengali(বাঙ্গালি)"};

    AutoCompleteTextView selectLanguage4;

    ArrayAdapter<String> adapterItems4;

    String username, selectedLanguage4, language, str0;

    DatabaseReference databaseReference;

    FirebaseDatabase database1;
    DatabaseReference reference1;

    FirebaseDatabase database2;
    DatabaseReference reference2;
 
    TextView logoutRedirectText, mainUsername;

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
        attempts = findViewById(R.id.attempts);
        return2Button = findViewById(R.id.return2_button);
        logoutRedirectText = findViewById(R.id.profile_logout);
        ranking1 = findViewById(R.id.ranking1);
        updateButton = findViewById(R.id.update_button);
        selectLanguage4 = findViewById(R.id.auto_complete_txt4);

        mainUsername.setText("" + username + "--" + language + "");
        mainUsername.setEnabled(false);

        ranking.setText("You Scored " +score + " out of 9");
        ranking.setEnabled(false);

        database2 = FirebaseDatabase.getInstance();
        reference2 = database2.getReference("LearningPoints");

        reference2.child(username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Integer currentLearningPoint = dataSnapshot.getValue(Integer.class);

                if (currentLearningPoint == null) {
                    currentLearningPoint = 0;
                }
                 str0=currentLearningPoint.toString();

                ranking1.setText(str0);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


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

         database1 = FirebaseDatabase.getInstance();
         reference1 = database.getReference("attempts").child(username);

        ArrayList<String> attemptsList = new ArrayList<>();

        reference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    HelperClass2 helperClass2 = dataSnapshot.getValue(HelperClass2.class);
                    String attemptsItem = helperClass2.getScore() + " , " + helperClass2.getCurrentDate() + " , " + helperClass2.getCurrentTime();
                    attemptsList.add(attemptsItem);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(Profile.this, android.R.layout.simple_spinner_dropdown_item, attemptsList);
                attempts.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(Profile.this, android.R.layout.simple_spinner_dropdown_item, attemptsList);
        attempts.setAdapter(adapter1);


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
                intent.putExtra("SCORE", score);
                startActivity(intent);


            }
        });

        ranking1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this, Ranking.class);
                intent.putExtra("USERNAME", username);
                intent.putExtra("RANK", str0);
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