package com.example.appquiz;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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

public class ScoresActivity extends AppCompatActivity {
    private static ArrayList<Score> scoreArrayList = new ArrayList<>();
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    String email;
    SimpleDateFormat simpleDateFormat;
    Date date;
    Score score;
    DatabaseReference databaseReference;
    ScoreAdapter scoreAdapter;
    ListView lvScore;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        email = firebaseUser.getEmail();
        lvScore = findViewById(R.id.lvScores);
        databaseReference = FirebaseDatabase.getInstance().getReference("scores");
        scoreAdapter = new ScoreAdapter(this, scoreArrayList);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");
        progressDialog.setTitle("Getting scores");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                scoreArrayList.clear();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Score score = dataSnapshot.getValue(Score.class);

                    String _email = score.getEmail();

                    if (_email.equalsIgnoreCase(email)) {
                        scoreArrayList.add(score);
                    } else {
                    }

                }
                scoreAdapter = new ScoreAdapter(ScoresActivity.this, scoreArrayList);

                lvScore.setAdapter(scoreAdapter);
                if (scoreArrayList.size() == 0) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(ScoresActivity.this);
                    alert.setTitle("NO DATA FOUND!");
                    alert.setCancelable(false);

                    alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    });
                    alert.show();
                }
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}