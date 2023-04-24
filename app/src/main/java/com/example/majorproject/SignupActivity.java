package com.example.majorproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignupActivity extends AppCompatActivity {

    EditText signupName, signupEmail, signupUsername, signupPassword;
    TextView loginRedirectText;

    Button signupButton;
    FirebaseDatabase database;
    DatabaseReference reference;

    String[] item = {"Hindi(हिंदी)", "Marathi(मराठी)", "Telugu(తెలుగు)", "Tamil(தமிழ்)", "Bengali(বাঙ্গালি)"};

    AutoCompleteTextView selectLanguage;

    ArrayAdapter adapterItems;

    private String getPasswordStrengthEmoji(String password) {
        if (password.length() < 8) {
            return "🔴"; // red circle emoji for weak passwords
        } else if (password.matches(".*[a-zA-Z].*") && password.matches(".*[0-9].*") && !password.matches("^[a-zA-Z0-9]*$")) {
            return "🟢"; // orange circle emoji for medium strength passwords
        } else {
            return "🟠"; // green circle emoji for strong passwords
        }
    }

    CheckBox showPasswordCheckBox;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        showPasswordCheckBox = findViewById(R.id.show_password_checkbox);

        signupName = findViewById(R.id.signup_name);
        signupEmail = findViewById(R.id.signup_email);
        signupUsername = findViewById(R.id.signup_username);
        signupPassword = findViewById(R.id.signup_password);
        signupButton = findViewById(R.id.signup_button);
        loginRedirectText = findViewById(R.id.loginRedirectText);
        selectLanguage = findViewById(R.id.auto_complete_txt);

        adapterItems = new ArrayAdapter<String>(this,R.layout.language_item, item);

        selectLanguage.setAdapter(adapterItems);

        selectLanguage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();


            }
        });

        showPasswordCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    signupPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance()); // Show password
                } else {
                    signupPassword.setTransformationMethod(PasswordTransformationMethod.getInstance()); // Hide password
                }
            }
        });


        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                database = FirebaseDatabase.getInstance();
                reference = database.getReference("users");

                final String name = signupName.getText().toString();
                final String email = signupEmail.getText().toString();
                final String language = selectLanguage.getText().toString();
                final String username = signupUsername.getText().toString();
                final String password = signupPassword.getText().toString();

                String passwordStrengthEmoji = getPasswordStrengthEmoji(password);

                Toast.makeText(SignupActivity.this, "Password strength: " + passwordStrengthEmoji, Toast.LENGTH_SHORT).show();


                reference.child(username).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            // Show error message for username already exists
                            signupUsername.setError("Username already exists");
                            signupUsername.requestFocus();
                        }
                        else{
                            reference.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if(snapshot.exists()){
                                        // Show error message for email ID already exists
                                        signupEmail.setError("Email ID already exists");
                                        signupEmail.requestFocus();
                                    }
                                    else{
                                        reference.orderByChild("password").equalTo(password).addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                if(snapshot.exists()){
                                                    // Show error message for password already exists
                                                    signupPassword.setError("Password already exists");
                                                    signupPassword.requestFocus();
                                                }
                                                else{
                                                    HelperClass helperClass = new HelperClass(name, email, language, username, password);
                                                    reference.child(username).setValue(helperClass);

                                                    Intent intent =new Intent(SignupActivity.this, LoginActivity.class);
                                                    startActivity(intent);
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

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}