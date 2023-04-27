package com.example.majorproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ranking extends AppCompatActivity {

    String username, userpoints;

    FirebaseDatabase database2;
    DatabaseReference reference2;

    RadioButton top1, top2, top3, top4, top5;

    TextView points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        username = getIntent().getStringExtra("USERNAME");
        userpoints = getIntent().getStringExtra("RANK");


        top1 = findViewById(R.id.top1);
        top2 = findViewById(R.id.top2);
        top3 = findViewById(R.id.top3);
        top4 = findViewById(R.id.top4);
        top5 = findViewById(R.id.top5);
        points = findViewById(R.id.points1);

        points.setText("Your Learning Points is : " + userpoints + "👍");


        database2 = FirebaseDatabase.getInstance();
        reference2 = database2.getReference("LearningPoints");

        reference2.orderByValue().limitToLast(5).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<String> topUsers = new ArrayList<>();
                List<Integer> topPoints = new ArrayList<>();
                List<String> topans=new ArrayList<>();

                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    String username = userSnapshot.getKey();
                    Integer points = userSnapshot.getValue(Integer.class);

                    // Add the username and points to their respective lists
                    topUsers.add(username);
                    topPoints.add(points);
                }

                // Reverse the lists to get them in descending order
                Collections.reverse(topUsers);
                Collections.reverse(topPoints);

                // Print out the top 5 highest points users
                for (int i = 0; i < topUsers.size(); i++) {
                    topans.add(i,(i + 1) + ". " + topUsers.get(i) + " - " + topPoints.get(i) + " points");

                }
                top1.setText(topans.get(0));
                top2.setText(topans.get(1));
                top3.setText(topans.get(2));
                top4.setText(topans.get(3));
                top5.setText(topans.get(4));

                if (topUsers.contains(username)) {
                    points.setVisibility(View.GONE);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle any errors that occur
            }
        });

    }
}