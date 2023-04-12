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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Chatgroup extends AppCompatActivity {

    EditText mainUsername, enterText;
    Button return1Button, sendButton;
    TextView logoutRedirectText, sendersText;

    String username, language;

    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatgroup);

        // Retrieve the username and language passed from LoginActivity
        username = getIntent().getStringExtra("USERNAME");
        language = getIntent().getStringExtra("LANGUAGE");

        enterText = findViewById(R.id.enter_chat);
        sendersText = findViewById(R.id.sender_chat);
        mainUsername = findViewById(R.id.main_username3);
        sendButton = findViewById(R.id.send_button);
        return1Button = findViewById(R.id.return1_button);
        logoutRedirectText = findViewById(R.id.logout5);

        mainUsername.setText(username + "--" + language);
        mainUsername.setEnabled(false); // disable editing of the username field

        // Get a reference to the chat messages of the user's language
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("chat_" + language);

        // Add a child event listener to the reference to listen for new chat messages
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, String previousChildName) {
                // Get the chat message and its sender
                String message = snapshot.getValue(String.class);
                String sender = snapshot.getKey();

                // If the sender is not the current user, update the sendersText TextView
                if (!sender.equals(username)) {
                    sendersText.setText(sender + ": " + message);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, String previousChildName) {
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, String previousChildName) {
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Chatgroup.this, Chatgroup.class);
                // Get the text entered by the user and add it to the chat messages of the user's language
                String message = enterText.getText().toString();
                reference.child(username).setValue(message);

                intent.putExtra("USERNAME", username);
                intent.putExtra("LANGUAGE", language);

                // Clear the enterText EditText
                enterText.setText("");

                startActivity(intent);
            }
        });

        return1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Chatgroup.this, Learning.class);
                intent.putExtra("USERNAME", username);
                intent.putExtra("LANGUAGE", language);
                startActivity(intent);
            }
        });

        logoutRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(Chatgroup.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
