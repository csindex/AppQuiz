package com.example.appquiz;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;

import java.util.concurrent.atomic.AtomicReference;

public class MainActivity2 extends AppCompatActivity {
    TextView tvName;
//    FirebaseUser firebaseUser;
//    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    DatabaseReference databaseReference;
//    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tvName = findViewById(R.id.tvName);
        tvName.setText("Young Einstein");

//        firebaseAuth = FirebaseAuth.getInstance();
//        firebaseUser = firebaseAuth.getCurrentUser();
//        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        progressDialog = new ProgressDialog(this);
        progressDialog.dismiss();
//        email = firebaseUser.getEmail();

//        getUser();
    }

    public void goToQuiz(View view) {
        startActivity(new Intent(this, TestActivity.class));
    }

    public void goToLectures(View view) {
        startActivity(new Intent(this, LessonListActivity.class));
    }

    public void goToScores(View view) {
        startActivity(new Intent(this, ScoresActivity.class));
    }

    public void goToDictionary(View view) {
        startActivity(new Intent(this, DictionaryActivity.class));
    }

    /*public void getUser() {

        progressDialog.setMessage("Please wait...");
        progressDialog.setTitle("Getting data...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        Query checkUser = databaseReference.orderByChild("email");

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String _email = email.replace(".com", "");
                String gEmail = snapshot.child(_email).child("email").getValue(String.class);
                String fname = snapshot.child(_email).child("fname").getValue(String.class);
                String lname = snapshot.child(_email).child("lname").getValue(String.class);

                String fullName = fname + " " + lname;
                tvName.setText(fullName.toUpperCase());
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressDialog.dismiss();
            }
        });
    }*/

    @SuppressLint({"InflateParams", "SetTextI18n"})
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View content = inflater.inflate(R.layout.layout_dialog, null);
        builder.setView(content);
        AlertDialog dialog = builder.create();
        TextView title = content.findViewById(R.id.label_title);
        title.setText("Confirm Exit");
        TextView message = content.findViewById(R.id.label_message);
        message.setText("Are you sure you want to exit?");
        TextView pBtn = content.findViewById(R.id.btn_positive);
        pBtn.setText("Yes");
        pBtn.setOnClickListener(view -> {
            this.finishAndRemoveTask();
            System.exit(0);
        });
        TextView nBtn = content.findViewById(R.id.btn_negative);
        nBtn.setText("No");
        nBtn.setOnClickListener(view -> {
            try {
                dialog.dismiss();
            } catch (Exception e) {
                // TODO: do something
            }
        });
        try {
            dialog.show();
        } catch (Exception e) {
            // TODO: do something
        }
//        super.onBackPressed();
    }

    public void goToProfile(View view) {
        startActivity(new Intent(this, ProfileActivity.class));
    }
}