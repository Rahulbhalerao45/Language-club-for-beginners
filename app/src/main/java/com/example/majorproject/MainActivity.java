package com.example.majorproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText mainUsername, mainEnterText;
    Button mainButton, learningButton;
    FirebaseDatabase database;
    DatabaseReference reference;
    String[] item = {"Hindi(हिंदी)", "Marathi(मराठी)", "Telugu(తెలుగు)", "Tamil(தமிழ்)", "Malayalam(മലയാളം)"};
    AutoCompleteTextView selectLanguage1, selectLanguage2, selectLanguage3;
    ArrayAdapter adapterItems1, adapterItems2, adapterItems3;
    String username, selectedLanguage1, selectedLanguage2, selectedLanguage3, language;

    TextView logoutRedirectText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Retrieve the username passed from LoginActivity
         String username = getIntent().getStringExtra("USERNAME");
         String language = getIntent().getStringExtra("LANGUAGE");

        // Initialize the views
        mainUsername = findViewById(R.id.main_username);
        mainEnterText = findViewById(R.id.main_entertext);
        mainButton = findViewById(R.id.main_button);
        learningButton = findViewById(R.id.more_button);
        selectLanguage1 = findViewById(R.id.auto_complete_txt1);
        selectLanguage2 = findViewById(R.id.auto_complete_txt2);
        selectLanguage3 = findViewById(R.id.auto_complete_txt3);
        logoutRedirectText = findViewById(R.id.logout1);

        adapterItems1 = new ArrayAdapter<String>(this, R.layout.language1_item, item);
        adapterItems2 = new ArrayAdapter<String>(this, R.layout.language2_item, item);
        adapterItems3 = new ArrayAdapter<String>(this, R.layout.language3_item, item);

        selectLanguage1.setAdapter(adapterItems1);
        selectLanguage2.setAdapter(adapterItems2);
        selectLanguage3.setAdapter(adapterItems3);


        selectLanguage1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedLanguage1 = adapterView.getItemAtPosition(i).toString();
            }
        });

        selectLanguage2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedLanguage2 = adapterView.getItemAtPosition(i).toString();
            }
        });

        selectLanguage3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedLanguage3 = adapterView.getItemAtPosition(i).toString();
            }
        });

        // Set the username in the mainUsername EditText view

        mainUsername.setText("" + username + "--" + language + "");
        mainUsername.setEnabled(false); // disable editing of the username field

        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("history").child(username);
                String entertext = mainEnterText.getText().toString();
                String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
                HelperClass2 helperClass2 = new HelperClass2(username, entertext, selectedLanguage1, selectedLanguage2, selectedLanguage3, currentDate, currentTime);
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        int historyCount = (int) snapshot.getChildrenCount();

                        String historykey = "history" + (historyCount + 1);

                        reference.child(historykey).setValue(helperClass2);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                Intent intent = new Intent(MainActivity.this, Translate.class);
                intent.putExtra("LANGUAGE1", selectedLanguage1);
                intent.putExtra("LANGUAGE2", selectedLanguage2);
                intent.putExtra("LANGUAGE3", selectedLanguage3);
                intent.putExtra("USERNAME", username);
                intent.putExtra("LANGUAGE", language);
                intent.putExtra("ENTERED_TEXT", entertext);
                startActivity(intent);
            }
        });

        learningButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Learning.class);
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
                Intent intent =new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}

