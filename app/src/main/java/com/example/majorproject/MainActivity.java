package com.example.majorproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.google.mlkit.nl.translate.Translation;
import com.google.mlkit.nl.translate.Translator;
import com.google.mlkit.nl.translate.TranslatorOptions;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity {



    EditText mainUsername, mainEnterText;
    Button mainButton, learningButton;
    FirebaseDatabase database;
    DatabaseReference reference;
    String[] item = {"Hindi(हिंदी)", "Marathi(मराठी)", "Telugu(తెలుగు)", "Tamil(தமிழ்)", "Bengali(বাঙ্গালি)"};
    AutoCompleteTextView selectLanguage1, selectLanguage2, selectLanguage3;
    ArrayAdapter adapterItems1, adapterItems2, adapterItems3;
    String username, selectedLanguage1, selectedLanguage2, selectedLanguage3, language;
    String str1, str2, str3;

    String rus1 = "", rus2 = "", rus3 = "";

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
                if(selectedLanguage1=="Hindi(हिंदी)"){
                    str1 = "hi";
                }else if (selectedLanguage1=="Bengali(বাঙ্গালি)") {
                    str1 = "bn";
                } else if (selectedLanguage1=="Tamil(தமிழ்)") {
                    str1 = "ta";
                }else if (selectedLanguage1=="Telugu(తెలుగు)") {
                    str1 = "te";
                }else if (selectedLanguage1=="Marathi(मराठी)") {
                    str1 = "mr";
                }else {
                    str1 = "en";
                }
            }
        });

        selectLanguage2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedLanguage2 = adapterView.getItemAtPosition(i).toString();
                if(selectedLanguage2=="Hindi(हिंदी)"){
                    str2 = "hi";
                }else if (selectedLanguage2=="Bengali(বাঙ্গালি)") {
                    str2 = "bn";
                } else if (selectedLanguage2=="Tamil(தமிழ்)") {
                    str2 = "ta";
                }else if (selectedLanguage2=="Telugu(తెలుగు)") {
                    str2 = "te";
                }else if (selectedLanguage2=="Marathi(मराठी)") {
                    str2 = "mr";
                }else {
                    str2 = "en";
                }
            }
        });

        selectLanguage3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedLanguage3 = adapterView.getItemAtPosition(i).toString();
                if(selectedLanguage3=="Hindi(हिंदी)"){
                    str3 = "hi";
                }else if (selectedLanguage3=="Bengali(বাঙ্গালি)") {
                    str3 = "bn";
                } else if (selectedLanguage3=="Tamil(தமிழ்)") {
                    str3 = "ta";
                }else if (selectedLanguage3=="Telugu(తెలుగు)") {
                    str3 = "te";
                }else if (selectedLanguage3=="Marathi(मराठी)") {
                    str3 = "mr";
                }else {
                    str3 = "en";
                }
            }
        });

        // Set the username in the mainUsername EditText view

        mainUsername.setText("" + username + "--" + language + "");
        mainUsername.setEnabled(false); // disable editing of the username field


        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(selectedLanguage1) || TextUtils.isEmpty(selectedLanguage2) || TextUtils.isEmpty(selectedLanguage3)) {
                    Toast.makeText(MainActivity.this, "Please select all three languages", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(mainEnterText.getText().toString())) {
                    Toast.makeText(MainActivity.this, "Please Enter text....", Toast.LENGTH_SHORT).show();
                }else{
                    TranslatorOptions options = new TranslatorOptions.Builder()
                            .setTargetLanguage(str1)
                            .setSourceLanguage("en")
                            .build();
                    Translator translator = Translation.getClient(options);
                    translator.downloadModelIfNeeded();
                    String sourceText = mainEnterText.getText().toString();

                    translator.downloadModelIfNeeded().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });

                    Task<String> result = translator.translate(sourceText).addOnSuccessListener(new OnSuccessListener<String>() {
                        @Override
                        public void onSuccess(String s) {
                            rus1 = s;
                            Toast.makeText(MainActivity.this, rus1, Toast.LENGTH_SHORT);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MainActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }

                if(TextUtils.isEmpty(mainEnterText.getText().toString())) {
                    Toast.makeText(MainActivity.this, "Please Enter Text...", Toast.LENGTH_SHORT).show();
                }else{
                    TranslatorOptions options = new TranslatorOptions.Builder()
                            .setTargetLanguage(str2)
                            .setSourceLanguage("en")
                            .build();
                    Translator translator = Translation.getClient(options);
                    translator.downloadModelIfNeeded();
                    String sourceText = mainEnterText.getText().toString();

                    translator.downloadModelIfNeeded().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });
                    Task<String> result = translator.translate(sourceText).addOnSuccessListener(new OnSuccessListener<String>() {
                        @Override
                        public void onSuccess(String s) {
                            rus2 = s;
                            Toast.makeText(MainActivity.this, rus2, Toast.LENGTH_SHORT);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MainActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }

                if(TextUtils.isEmpty(mainEnterText.getText().toString())) {
                    Toast.makeText(MainActivity.this, "Please Enter Text...", Toast.LENGTH_SHORT).show();
                }else{
                    TranslatorOptions options = new TranslatorOptions.Builder()
                            .setTargetLanguage(str3)
                            .setSourceLanguage("en")
                            .build();
                    Translator translator = Translation.getClient(options);
                    translator.downloadModelIfNeeded();
                    String sourceText = mainEnterText.getText().toString();

                    translator.downloadModelIfNeeded().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });
                    Task<String> result = translator.translate(sourceText).addOnSuccessListener(new OnSuccessListener<String>() {
                        @Override
                        public void onSuccess(String s) {
                            rus3 = s;
                            Toast.makeText(MainActivity.this, rus3, Toast.LENGTH_SHORT);
                            Intent intent = new Intent(MainActivity.this, Translate.class);
                            intent.putExtra("LANGUAGE1", selectedLanguage1);
                            intent.putExtra("LANGUAGE2", selectedLanguage2);
                            intent.putExtra("LANGUAGE3", selectedLanguage3);
                            intent.putExtra("USERNAME", username);
                            intent.putExtra("LANGUAGE", language);
                            intent.putExtra("ENTERED_TEXT",sourceText);
                            intent.putExtra("RUS1", rus1);
                            intent.putExtra("RUS2", rus2);
                            intent.putExtra("RUS3", rus3);
                            startActivity(intent);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MainActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
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

