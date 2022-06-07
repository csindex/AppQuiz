package com.example.appquiz;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appquiz.adapter.NumbersAdapter;
import com.example.appquiz.classes.GlobalVariables;
import com.example.appquiz.classes.Lesson;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {
    private static ArrayList<Lesson> alphabetsArrayList = new ArrayList<>();
    private static ArrayList<Lesson> numbersArrayList = new ArrayList<>();
    private static ArrayList<Lesson> sensesArrayList = new ArrayList<>();

    RecyclerView recyclerView;
    String lesson;
    int span = 0;
    ProgressDialog progressDialog;
    private NumbersAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Getting data");
        progressDialog.setMessage("Loading please wait...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                progressDialog.dismiss();
            }
        }, 1000);

        Intent intent = getIntent();
        String lesson = intent.getStringExtra("lesson");
        GlobalVariables globalVariables = (GlobalVariables) this.getApplication();

        if (lesson.equalsIgnoreCase("numbers")) {
            numbersArrayList = globalVariables.getNumbers();
        } else {
            numbersArrayList = globalVariables.getAlphabets();
        }

        recyclerView = findViewById(R.id.rv);
        myAdapter = new NumbersAdapter(this, numbersArrayList);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);

        recyclerView.setAdapter(myAdapter);
    }
}