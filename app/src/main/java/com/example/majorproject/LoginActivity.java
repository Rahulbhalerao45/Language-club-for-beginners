package com.example.majorproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    EditText loginUsername, loginPassword;
    Button loginButton;
    TextView signupRedirectText;

    FirebaseDatabase database2;
    DatabaseReference reference2;

    CheckBox showPasswordCheckBox, rememberMeCheckBox;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        showPasswordCheckBox = findViewById(R.id.show_password_checkbox);
        rememberMeCheckBox = findViewById(R.id.remember_me_checkbox);

        loginUsername = findViewById(R.id.login_username);
        loginPassword = findViewById(R.id.login_password);
        signupRedirectText = findViewById(R.id.signupRedirectText);
        loginButton = findViewById(R.id.login_button);

        sharedPreferences = getSharedPreferences("loginPrefs", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        // check if remember me is checked
        if (sharedPreferences.getBoolean("rememberMe", false)) {
            loginUsername.setText(sharedPreferences.getString("username", ""));
            loginPassword.setText(sharedPreferences.getString("password", ""));
            rememberMeCheckBox.setChecked(true);
        }

        showPasswordCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    loginPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance()); // Show password
                } else {
                    loginPassword.setTransformationMethod(PasswordTransformationMethod.getInstance()); // Hide password
                }
            }
        });


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!validateUsername() | !validatePassword()) {
                    return;
                }

                if (rememberMeCheckBox.isChecked()) {
                    editor.putBoolean("rememberMe", true);
                    editor.putString("username", loginUsername.getText().toString().trim());
                    editor.putString("password", loginPassword.getText().toString().trim());
                    editor.apply();
                } else {
                    editor.clear();
                    editor.apply();
                }

                checkUser();
            }
        });

        signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

    }

    public Boolean validateUsername() {
        String val = loginUsername.getText().toString();
        if (val.isEmpty()) {
            loginUsername.setError("Username Cannot be empty");
            return false;
        } else {
            loginUsername.setError(null);
            return true;
        }
    }

    public Boolean validatePassword() {
        String val = loginPassword.getText().toString();
        if (val.isEmpty()) {
            loginPassword.setError("Password cannot be empty");
            return false;
        } else {
            loginPassword.setError(null);
            return true;
        }
    }

    public void checkUser() {
        String userIdentifier = loginUsername.getText().toString().trim();
        String userPassword = loginPassword.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUserDatabase = reference.orderByChild("username").equalTo(userIdentifier);
        Query checkEmailDatabase = reference.orderByChild("email").equalTo(userIdentifier);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                        String passwordFromDB = userSnapshot.child("password").getValue(String.class);
                        String languageFromDB = userSnapshot.child("language").getValue(String.class);
                        String usernameFromDB = userSnapshot.child("username").getValue(String.class);

                        if (passwordFromDB.equals(userPassword)) {
                            loginUsername.setError(null);
                            loginPassword.setError(null);
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra("USERNAME", usernameFromDB);
                            intent.putExtra("LANGUAGE", languageFromDB);
                            startActivity(intent);

                            // Update learning points
                            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("LearningPoints");

                            reference.child(usernameFromDB).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    Integer currentLearningPoint = dataSnapshot.getValue(Integer.class);

                                    if (currentLearningPoint == null) {
                                        currentLearningPoint = 0;
                                    }

                                    Integer newLearningPoint = currentLearningPoint + 1;

                                    reference.child(usernameFromDB).setValue(newLearningPoint);
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                }
                            });
                        } else {
                            loginPassword.setError("Invalid Password");
                            loginPassword.requestFocus();
                        }
                    }
                } else {
                    checkEmailDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                                    String passwordFromDB = userSnapshot.child("password").getValue(String.class);
                                    String languageFromDB = userSnapshot.child("language").getValue(String.class);
                                    String usernameFromDB = userSnapshot.child("username").getValue(String.class);

                                    if (passwordFromDB.equals(userPassword)) {
                                        loginUsername.setError(null);
                                        loginPassword.setError(null);
                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        intent.putExtra("USERNAME", usernameFromDB);
                                        intent.putExtra("LANGUAGE", languageFromDB);
                                        startActivity(intent);

                                        // Update learning points
                                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("LearningPoints");

                                        reference.child(usernameFromDB).addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                Integer currentLearningPoint = dataSnapshot.getValue(Integer.class);

                                                if (currentLearningPoint == null) {
                                                    currentLearningPoint = 0;
                                                }

                                                Integer newLearningPoint = currentLearningPoint + 1;

                                                reference.child(usernameFromDB).setValue(newLearningPoint);
                                            }

                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {
                                                // Handle any errors that occur
                                            }
                                        });
                                    } else {
                                        loginPassword.setError("Invalid Password");
                                        loginPassword.requestFocus();
                                    }
                                }
                            } else {
                                loginUsername.setError("User does not exist");
                                loginUsername.requestFocus();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
