package com.example.majorproject;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Links extends AppCompatActivity {

    FirebaseDatabase database2;
    DatabaseReference reference2;

    String username, language, str0;

    Button back;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_links);

        username = getIntent().getStringExtra("USERNAME");
        language = getIntent().getStringExtra("LANGUAGE");
        String selectedLanguage1 = getIntent().getStringExtra("LANGUAGE1");
        String selectedLanguage2 = getIntent().getStringExtra("LANGUAGE2");
        String selectedLanguage3 = getIntent().getStringExtra("LANGUAGE3");
        String score = getIntent().getStringExtra("SCORE");

        // Get a reference to the TextView
        TextView linkTextView = findViewById(R.id.linkTextView);
        TextView points = findViewById(R.id.linkTextView1);
        back = findViewById(R.id.backkk);


// Get a reference to the Firebase database


        database2 = FirebaseDatabase.getInstance();
        reference2 = database2.getReference("LearningPoints");

        reference2.child(username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Integer currentLearningPoint = dataSnapshot.getValue(Integer.class);

                if (currentLearningPoint == null) {
                    currentLearningPoint = 0;
                }

                if (currentLearningPoint <= 15) {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference ref = database.getReference("hyperlink").child(language);

                    ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            // Get the hyperlink from the database
                            String hyperlink = snapshot.child("1").getValue(String.class);

                            // Set the hyperlink as the text of the TextView
                            linkTextView.setText("LINK  : " + hyperlink);

                            // Make the text clickable and open the webpage in a browser
                            linkTextView.setMovementMethod(LinkMovementMethod.getInstance());
                            linkTextView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(hyperlink));
                                    startActivity(browserIntent);
                                    database2 = FirebaseDatabase.getInstance();
                                    reference2 = database2.getReference("LearningPoints");

                                    reference2.child(username).addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            Integer currentLearningPoint = dataSnapshot.getValue(Integer.class);

                                            if (currentLearningPoint == null) {
                                                currentLearningPoint = 0;
                                            }

                                            Integer newLearningPoint = currentLearningPoint + 2;

                                            reference2.child(username).setValue(newLearningPoint);
                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {
                                        }
                                    });
                                }
                            });
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Log.d(TAG, "Error reading value: " + databaseError.getMessage());
                        }
                    });
                } else if (currentLearningPoint <= 25) {

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference ref = database.getReference("hyperlink").child(language);

                    ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            // Get the hyperlink from the database
                            String hyperlink = snapshot.child("2").getValue(String.class);

                            // Set the hyperlink as the text of the TextView
                            linkTextView.setText("LINK  : " + hyperlink);

                            // Make the text clickable and open the webpage in a browser
                            linkTextView.setMovementMethod(LinkMovementMethod.getInstance());
                            linkTextView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(hyperlink));
                                    startActivity(browserIntent);
                                    database2 = FirebaseDatabase.getInstance();
                                    reference2 = database2.getReference("LearningPoints");

                                    reference2.child(username).addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            Integer currentLearningPoint = dataSnapshot.getValue(Integer.class);

                                            if (currentLearningPoint == null) {
                                                currentLearningPoint = 0;
                                            }

                                            Integer newLearningPoint = currentLearningPoint + 2;

                                            reference2.child(username).setValue(newLearningPoint);
                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {
                                        }
                                    });
                                }
                            });
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Log.d(TAG, "Error reading value: " + databaseError.getMessage());
                        }
                    });
                } else if (currentLearningPoint > 25) {

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference ref = database.getReference("hyperlink").child(language);

                    ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            // Get the hyperlink from the database
                            String hyperlink = snapshot.child("3").getValue(String.class);

                            // Set the hyperlink as the text of the TextView
                            linkTextView.setText("LINK  : " + hyperlink);

                            // Make the text clickable and open the webpage in a browser
                            linkTextView.setMovementMethod(LinkMovementMethod.getInstance());
                            linkTextView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(hyperlink));
                                    startActivity(browserIntent);
                                    database2 = FirebaseDatabase.getInstance();
                                    reference2 = database2.getReference("LearningPoints");

                                    reference2.child(username).addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            Integer currentLearningPoint = dataSnapshot.getValue(Integer.class);

                                            if (currentLearningPoint == null) {
                                                currentLearningPoint = 0;
                                            }

                                            Integer newLearningPoint = currentLearningPoint + 2;

                                            reference2.child(username).setValue(newLearningPoint);
                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {
                                        }
                                    });
                                }
                            });
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Log.d(TAG, "Error reading value: " + databaseError.getMessage());
                        }
                    });

                }
                str0=currentLearningPoint.toString();

                points.setText("Your Points : " + str0);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Links.this, Profile.class);
                intent.putExtra("USERNAME", username);
                intent.putExtra("LANGUAGE", language);
                intent.putExtra("LANGUAGE1", selectedLanguage1);
                intent.putExtra("LANGUAGE2", selectedLanguage2);
                intent.putExtra("LANGUAGE3", selectedLanguage3);
                intent.putExtra("SCORE", score);
                startActivity(intent);


            }
        });

    }
}