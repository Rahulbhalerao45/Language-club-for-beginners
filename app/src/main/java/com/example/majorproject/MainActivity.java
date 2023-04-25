package com.example.majorproject;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
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

    FirebaseDatabase database2;
    DatabaseReference reference2;
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

        String score = getIntent().getStringExtra("SCORE");

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
                switch (selectedLanguage1) {
                    case "Hindi(हिंदी)":
                        str1 = "hi";
                        break;
                    case "Bengali(বাঙ্গালি)":
                        str1 = "bn";
                        break;
                    case "Tamil(தமிழ்)":
                        str1 = "ta";
                        break;
                    case "Telugu(తెలుగు)":
                        str1 = "te";
                        break;
                    case "Marathi(मराठी)":
                        str1 = "mr";
                        break;
                    default:
                        str1 = "en";
                        break;
                }
            }
        });

        selectLanguage2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedLanguage2 = adapterView.getItemAtPosition(i).toString();
                switch (selectedLanguage2) {
                    case "Hindi(हिंदी)":
                        str2 = "hi";
                        break;
                    case "Bengali(বাঙ্গালি)":
                        str2 = "bn";
                        break;
                    case "Tamil(தமிழ்)":
                        str2 = "ta";
                        break;
                    case "Telugu(తెలుగు)":
                        str2 = "te";
                        break;
                    case "Marathi(मराठी)":
                        str2 = "mr";
                        break;
                    default:
                        str2 = "en";
                        break;
                }
            }
        });

        selectLanguage3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedLanguage3 = adapterView.getItemAtPosition(i).toString();
                switch (selectedLanguage3) {
                    case "Hindi(हिंदी)":
                        str3 = "hi";
                        break;
                    case "Bengali(বাঙ্গালি)":
                        str3 = "bn";
                        break;
                    case "Tamil(தமிழ்)":
                        str3 = "ta";
                        break;
                    case "Telugu(తెలుగు)":
                        str3 = "te";
                        break;
                    case "Marathi(मराठी)":
                        str3 = "mr";
                        break;
                    default:
                        str3 = "en";
                        break;
                }
            }
        });

        // Set the username in the mainUsername EditText view

        mainUsername.setText("" + username + "--" + language + "");
        mainUsername.setEnabled(false); // disable editing of the username field


        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (TextUtils.isEmpty(selectedLanguage1)) {
                    Toast.makeText(MainActivity.this, "Please select at least one  languages", Toast.LENGTH_SHORT).show();
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
                            Intent intent = new Intent(MainActivity.this, Translate.class);
                            intent.putExtra("LANGUAGE1", selectedLanguage1);
                            intent.putExtra("USERNAME", username);
                            intent.putExtra("LANGUAGE", language);
                            intent.putExtra("ENTERED_TEXT",sourceText);
                            intent.putExtra("RUS1", rus1);
                            intent.putExtra("SCORE", score);
                            startActivity(intent);

                            database2 = FirebaseDatabase.getInstance();
                            reference2 = database2.getReference("LearningPoints");

// Assuming `username` is the variable that holds the user's username
                            reference2.child(username).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    // Retrieve the current value of the learning point
                                    Integer currentLearningPoint = dataSnapshot.getValue(Integer.class);

                                    // If the user has no learning points yet, the current value will be null, so set it to 0
                                    if (currentLearningPoint == null) {
                                        currentLearningPoint = 0;
                                    }

                                    // Increment the learning point by 1
                                    Integer newLearningPoint = currentLearningPoint + 1;

                                    // Set the updated value to the database
                                    reference2.child(username).setValue(newLearningPoint);
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                    // Handle any errors that occur
                                }
                            });
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MainActivity.this, "Downloading Language Module...", Toast.LENGTH_SHORT).show();
                        }
                    });

                }

                if (TextUtils.isEmpty(selectedLanguage1) || TextUtils.isEmpty(selectedLanguage2)) {
                    Toast.makeText(MainActivity.this, "Please select all three languages", Toast.LENGTH_SHORT);
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
                            Toast.makeText(MainActivity.this, "Downloading Language Module...", Toast.LENGTH_SHORT).show();
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
                            Intent intent = new Intent(MainActivity.this, Translate.class);
                            intent.putExtra("LANGUAGE1", selectedLanguage1);
                            intent.putExtra("LANGUAGE2", selectedLanguage2);
                            intent.putExtra("USERNAME", username);
                            intent.putExtra("LANGUAGE", language);
                            intent.putExtra("ENTERED_TEXT",sourceText);
                            intent.putExtra("RUS1", rus1);
                            intent.putExtra("RUS2", rus2);
                            intent.putExtra("SCORE", score);
                            startActivity(intent);
                            database2 = FirebaseDatabase.getInstance();
                            reference2 = database2.getReference("LearningPoints");

// Assuming `username` is the variable that holds the user's username
                            reference2.child(username).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    // Retrieve the current value of the learning point
                                    Integer currentLearningPoint = dataSnapshot.getValue(Integer.class);

                                    // If the user has no learning points yet, the current value will be null, so set it to 0
                                    if (currentLearningPoint == null) {
                                        currentLearningPoint = 0;
                                    }

                                    // Increment the learning point by 1
                                    Integer newLearningPoint = currentLearningPoint + 1;

                                    // Set the updated value to the database
                                    reference2.child(username).setValue(newLearningPoint);
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                    // Handle any errors that occur
                                }
                            });
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MainActivity.this, "Downloading Language Module...", Toast.LENGTH_SHORT).show();
                        }
                    });

                }

                if (TextUtils.isEmpty(selectedLanguage1) || TextUtils.isEmpty(selectedLanguage2) || TextUtils.isEmpty(selectedLanguage3)) {
                    Toast.makeText(MainActivity.this, "Please select all three languages", Toast.LENGTH_SHORT);
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
                            Toast.makeText(MainActivity.this, "Downloading Language Module...", Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(MainActivity.this, "Downloading Language Module...", Toast.LENGTH_SHORT).show();
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
                            intent.putExtra("SCORE", score);
                            startActivity(intent);

                            database2 = FirebaseDatabase.getInstance();
                            reference2 = database2.getReference("LearningPoints");

// Assuming `username` is the variable that holds the user's username
                            reference2.child(username).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    // Retrieve the current value of the learning point
                                    Integer currentLearningPoint = dataSnapshot.getValue(Integer.class);

                                    // If the user has no learning points yet, the current value will be null, so set it to 0
                                    if (currentLearningPoint == null) {
                                        currentLearningPoint = 0;
                                    }

                                    // Increment the learning point by 1
                                    Integer newLearningPoint = currentLearningPoint + 1;

                                    // Set the updated value to the database
                                    reference2.child(username).setValue(newLearningPoint);
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                    // Handle any errors that occur
                                }
                            });
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MainActivity.this, "Downloading Language Module...", Toast.LENGTH_SHORT).show();
                        }
                    });

                }

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
                intent.putExtra("SCORE", score);
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

