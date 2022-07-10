package com.example.appquiz;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.appquiz.adapter.ScoreAdapter;
import com.example.appquiz.classes.GlobalVariables;
import com.example.appquiz.classes.Score;
import com.example.appquiz.classes.Test;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import pl.droidsonroids.gif.GifImageView;

public class TestActivity extends AppCompatActivity {
    private static ArrayList<Test> testArrayList;
    private static ArrayList<Score> scoreArrayList = new ArrayList<>();
    TextView tvQuestion;
    TextView tvAnswer, tvStudentAnswer, tvScore, tvNumber;
    ImageView ivQuestion;
    Button btn1, btn2;
    int position = 0;
    int mScore = 0;
    ProgressDialog progressDialog;
//    FirebaseAuth firebaseAuth;
//    FirebaseUser firebaseUser;
    String email;
    SimpleDateFormat simpleDateFormat;
    Date date;
    Score score;
//    DatabaseReference databaseReference;
    ScoreAdapter scoreAdapter;
    ListView lvScore;
    Dialog dialog1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        GlobalVariables globalVariables = (GlobalVariables) this.getApplication();
        testArrayList = globalVariables.getTest();

        dialog1 = new Dialog(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Getting data");
        progressDialog.setMessage("Loading please wait...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();


        Handler handler = new Handler();
        handler.postDelayed(() -> {
            progressDialog.dismiss();
            getQuestion(position);
        }, 1000);
        tvQuestion = findViewById(R.id.tvQuestion);
        tvAnswer = findViewById(R.id.tvAnswer);
        ivQuestion = findViewById(R.id.ivQuestion);
        tvStudentAnswer = findViewById(R.id.tvStudentAnswer);
        tvScore = findViewById(R.id.tvScore);
        tvNumber = findViewById(R.id.tvNumber);
        btn1 = findViewById(R.id.btnChoice1);
        btn2 = findViewById(R.id.btnChoice2);

//        firebaseAuth = FirebaseAuth.getInstance();
//        firebaseUser = firebaseAuth.getCurrentUser();
        email = "sample@email.com";//firebaseUser.getEmail();
        lvScore = findViewById(R.id.lvTest);
//        databaseReference = FirebaseDatabase.getInstance().getReference("scores");


    }

    private void getQuestion(int position) {
        tvQuestion.setText(testArrayList.get(position).getQuestion());
        tvScore.setText("Score: " + String.valueOf(mScore));
        tvAnswer.setText(testArrayList.get(position).getAnswer());

        if (testArrayList.get(position).getQuestion_image() == 0) {
            ivQuestion.setVisibility(View.GONE);
        } else {
            ivQuestion.setVisibility(View.VISIBLE);
            Glide.with(getApplication()).load(testArrayList.get(position).getQuestion_image()).into(ivQuestion);
        }

        tvNumber.setText("Question " + (position + 1) + "/" + testArrayList.size());
        btn1.setText(testArrayList.get(position).getChoice1());
        btn2.setText(testArrayList.get(position).getChoice2());
    }

    @SuppressLint({"SetTextI18n", "UseCompatLoadingForDrawables", "SimpleDateFormat"})
    public void submit(View view) {
        if (tvStudentAnswer.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Choose an answer", Toast.LENGTH_SHORT).show();
        } else {
//            if (tvStudentAnswer.getText().toString().equalsIgnoreCase(tvAnswer.getText().toString())) {
//                mScore++;
//            }
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            LayoutInflater inflater = this.getLayoutInflater();
            View content = inflater.inflate(R.layout.layout_dialog_i1, null);
            builder.setView(content);
            AlertDialog dialog = builder.create();
            GifImageView emoji = content.findViewById(R.id.img);
            TextView message = content.findViewById(R.id.label_message);
            if (tvStudentAnswer.getText().toString().equalsIgnoreCase(tvAnswer.getText().toString())) {
                mScore++;
                emoji.setImageResource(R.drawable.smile_emoji);
                message.setText("Your Answer is CORRECT!");
            } else {
                emoji.setImageResource(R.drawable.cry_emoji);
                message.setText("Your Answer is Wrong.");
                message.setTextColor(getResources().getColor(R.color.baby_squid_red));
            }
            TextView pBtn = content.findViewById(R.id.btn_positive);
            pBtn.setText("Okay");
            pBtn.setOnClickListener(v -> {
                if (position + 1 >= testArrayList.size()) {
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.win);
                    mediaPlayer.start();
                    dialog1.setContentView(R.layout.dialog_score);
                    dialog1.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                    TextView tvScore = dialog1.findViewById(R.id.tvScore);
                    tvScore.setText("Your score is " + String.valueOf(mScore) + "/" + testArrayList.size());
                    Button btnClose = dialog1.findViewById(R.id.btnClose);
                    btnClose.setOnClickListener(view1 -> {
                        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy ");
                        date = new Date();

//                        String id = databaseReference.push().getKey();

                        score = new Score(email, simpleDateFormat.format(date), mScore + "/" + testArrayList.size());

                        SharedPreferences prefs = this.getSharedPreferences("score_prefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("score", mScore + "/" + testArrayList.size());
                        editor.apply();

//                        databaseReference.child(id).setValue(score);
                        finish();
                        mediaPlayer.stop();
                        dialog1.dismiss();
                    });
                    try {
                        dialog1.show();
                    } catch (WindowManager.BadTokenException e) {
                        // TODO: do something
                    }
                } else {
                    position++;
                    getQuestion(position);
                    tvStudentAnswer.setText("");
                    btn2.setBackgroundColor(btn2.getContext().getResources().getColor(R.color.purple_200));
                    btn1.setBackgroundColor(btn1.getContext().getResources().getColor(R.color.purple_200));
                }
                dialog.dismiss();
            });
            try {
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
            } catch (Exception e) {
                // TODO: do something
            }

        }
    }

    public void choice2(View view) {
        tvStudentAnswer.setText("b");
        btn2.setBackgroundColor(btn2.getContext().getResources().getColor(R.color.purple_700));
        btn1.setBackgroundColor(btn1.getContext().getResources().getColor(R.color.purple_200));

    }

    public void choice1(View view) {
        tvStudentAnswer.setText("a");
        btn2.setBackgroundColor(btn2.getContext().getResources().getColor(R.color.purple_200));
        btn1.setBackgroundColor(btn1.getContext().getResources().getColor(R.color.purple_700));
    }
}