package com.example.appquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.appquiz.adapter.MyAdapter;
import com.example.appquiz.classes.GlobalVariables;
import com.example.appquiz.classes.Lesson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static ArrayList<Lesson> alphabetsArrayList = new ArrayList<>();
    private static ArrayList<Lesson> numbersArrayList = new ArrayList<>();
    private static ArrayList<Lesson> sensesArrayList = new ArrayList<>();

    RecyclerView recyclerView;
    private MyAdapter myAdapter;

    String lesson;
    int span = 0;

    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Getting data");
        progressDialog.setMessage("Loading please wait...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        Handler handler = new Handler();

        handler.postDelayed(() -> progressDialog.dismiss(),1000);
        GlobalVariables globalVariables = (GlobalVariables) this.getApplication();
        alphabetsArrayList= GlobalVariables.getAlphabets();
        numbersArrayList=globalVariables.getNumbers();
        sensesArrayList=globalVariables.getSenses();

        Intent intent = getIntent();

        lesson = intent.getStringExtra("lesson");

        if (lesson.equalsIgnoreCase("alphabet")){
            myAdapter=new MyAdapter(this,alphabetsArrayList);
            span=3;
        }else if (lesson.equalsIgnoreCase("numbers")){
            myAdapter=new MyAdapter(this,numbersArrayList);
            span=2;
        }else {
            myAdapter=new MyAdapter(this,sensesArrayList);
            span=1;
        }

        recyclerView=findViewById(R.id.rv);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,span,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);

        recyclerView.setAdapter(myAdapter);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}