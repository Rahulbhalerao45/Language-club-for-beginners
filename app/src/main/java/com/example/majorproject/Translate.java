package com.example.majorproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Translate extends AppCompatActivity {

    EditText mainUsername, mainEnterText, result1, result2, result3;
    Button backButton;
    TextView Language1, Language2, Language3;

    String username, language;

    FirebaseDatabase database;
    DatabaseReference reference;

    TextView logoutRedirectText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);

        String username = getIntent().getStringExtra("USERNAME");
        String language = getIntent().getStringExtra("LANGUAGE");

        String score = getIntent().getStringExtra("SCORE");


        mainUsername = findViewById(R.id.main_username1);
        backButton = findViewById(R.id.back_button);
        Language1 = findViewById(R.id.language1_textview);
        Language2 = findViewById(R.id.language2_textview);
        Language3 = findViewById(R.id.language3_textview);
        result1 = findViewById(R.id.main_translation1);
        result2 = findViewById(R.id.main_translation2);
        result3 = findViewById(R.id.main_translation3);
        logoutRedirectText = findViewById(R.id.logout2);
        mainEnterText = findViewById(R.id.main_entertext1);

        Intent intent = getIntent();
        String language1 = intent.getStringExtra("LANGUAGE1");
        String language2 = intent.getStringExtra("LANGUAGE2");
        String language3 = intent.getStringExtra("LANGUAGE3");

        Language1.setText(language1);
        Language2.setText(language2);
        Language3.setText(language3);

        mainUsername.setText("" + username + "--" + language + "");
        mainUsername.setEnabled(false);

        String enteredText = intent.getStringExtra("ENTERED_TEXT");
        mainEnterText.setText(enteredText);
        mainEnterText.setEnabled(false);

        String result11 = intent.getStringExtra("RUS1");
        result1.setText(result11);
        result1.setEnabled(false);

        String result22 = intent.getStringExtra("RUS2");
        result2.setText(result22);
        result2.setEnabled(false);

        String result33 = intent.getStringExtra("RUS3");
        result3.setText(result33);
        result3.setEnabled(false);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Translate.this, MainActivity.class);
                intent.putExtra("USERNAME", username);
                intent.putExtra("LANGUAGE", language);
                intent.putExtra("SCORE", score);
                startActivity(intent);

                database = FirebaseDatabase.getInstance();
                reference = database.getReference("history").child(username);
                String entertext = mainEnterText.getText().toString();
                String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
                HelperClass3 helperClass3 = new HelperClass3(username, entertext, language1, language2, language3, result11, result22, result33, currentDate, currentTime);
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        int historyCount = (int) snapshot.getChildrenCount();

                        String historykey = "history" + (historyCount + 1);

                        reference.child(historykey).setValue(helperClass3);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
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
