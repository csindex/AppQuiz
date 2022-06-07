package com.example.appquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appquiz.newfeature.abakadafeature.AbakadaActivity;
import com.example.appquiz.newfeature.bodypartsfeature.MyBodyPartsActivity;

public class LessonListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_list);

    }

    public void goToSenses(View view) {
        startActivity(new Intent(this, MainActivity.class).putExtra("lesson", "senses"));
    }

    public void goToAlphabets(View view) {
        startActivity(new Intent(this, NumbersActivity.class).putExtra("lesson", "alphabet"));
    }

    public void goToNumbers(View view) {
        startActivity(new Intent(this, NumbersActivity.class).putExtra("lesson","numbers"));
    }

    /**
     * LessonListActivity to AbakadaActivity
     * @param view
     */
    public void gotoAbakadaActivity(View view) {
        startActivity(new Intent(this, AbakadaActivity.class));
    }
    /**
     * LessonListActivity to MyBodyPartsActivity
     * @param view
     */
    public void gotoBodyParts(View view) {
        startActivity(new Intent(this, MyBodyPartsActivity.class));
    }
}