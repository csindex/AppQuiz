package com.example.appquiz;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appquiz.adapter.ScoreAdapter;
import com.example.appquiz.classes.Score;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TryActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    String email="test";
    SimpleDateFormat simpleDateFormat;
    Date date;
    Score score;
    DatabaseReference databaseReference;
    private static ArrayList<Score> scoreArrayList = new ArrayList<>();
    ScoreAdapter scoreAdapter;
    ListView lvScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_try);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
//        email = firebaseUser.getEmail();
        lvScore=findViewById(R.id.lvTest);
        databaseReference=FirebaseDatabase.getInstance().getReference("scores");




    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                scoreArrayList.clear();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Score score = dataSnapshot.getValue(Score.class);

                    String ema = score.getScore();

                    scoreArrayList.add(score);
                }
                scoreAdapter=new ScoreAdapter(TryActivity.this,scoreArrayList);

                lvScore.setAdapter(scoreAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        Toast.makeText(getApplicationContext(), String.valueOf(scoreArrayList.size()), Toast.LENGTH_SHORT).show();
    }

}