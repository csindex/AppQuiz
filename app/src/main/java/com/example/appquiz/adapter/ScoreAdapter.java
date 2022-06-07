package com.example.appquiz.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.appquiz.R;
import com.example.appquiz.classes.Score;

import java.util.ArrayList;

public class ScoreAdapter extends ArrayAdapter<Score> {
    Context context;
    private static ArrayList<Score> scoreArrayList = new ArrayList<>();

    public ScoreAdapter(@NonNull Context context, ArrayList<Score> scoreArrayList) {
        super(context, R.layout.score_layout,scoreArrayList);
        this.context=context;
        this.scoreArrayList=scoreArrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(R.layout.score_layout,null,true);

        TextView tvScore = view.findViewById(R.id.tvScore);
        TextView tvDate = view.findViewById(R.id.tvDate);
        TextView tvEmail = view.findViewById(R.id.tvEmail);

        tvScore.setText(scoreArrayList.get(position).getScore());
        tvDate.setText(scoreArrayList.get(position).getDate());
        tvEmail.setText(scoreArrayList.get(position).getEmail());

        return view;
    }
}
