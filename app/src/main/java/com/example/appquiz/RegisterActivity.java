package com.example.appquiz;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appquiz.classes.Score;
import com.example.appquiz.classes.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    EditText etEmail, etPassword, etFname, etLname;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    ProgressDialog progressDialog;
    DatabaseReference databaseReference;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        progressDialog = new ProgressDialog(this);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etFname = findViewById(R.id.etFName);
        etLname = findViewById(R.id.etLname);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
    }

    public void register(View view) {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String fname = etFname.getText().toString();
        String lname = etLname.getText().toString();

        if (email.isEmpty()) {
            etEmail.setError("Enter email");
            etEmail.requestFocus();
        } else if (password.isEmpty()) {
            etPassword.setError("Enter password");
            etPassword.requestFocus();
        } else if (password.length() < 6) {
            etPassword.setError("Check length of password");
            etPassword.requestFocus();
        } else if (fname.isEmpty()) {
            etFname.setError("Enter first name");
            etLname.requestFocus();
        } else if (lname.isEmpty()) {
            etLname.setError("Enter last name");
            etLname.requestFocus();
        } else {
            progressDialog.setMessage("Please wait...");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        //add user
                        databaseReference = FirebaseDatabase.getInstance().getReference("users");

                        String _email = email.replace(".com","");

                        user = new User(fname,lname,email);

                        databaseReference.child(_email).setValue(user);

                        progressDialog.dismiss();
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    public void login(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}