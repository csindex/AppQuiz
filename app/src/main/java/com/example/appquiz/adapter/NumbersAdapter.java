package com.example.appquiz.adapter;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appquiz.R;
import com.example.appquiz.classes.Lesson;
import com.example.appquiz.R;
import com.example.appquiz.classes.Lesson;

import java.util.ArrayList;
import java.util.Locale;

public class NumbersAdapter extends RecyclerView.Adapter<NumbersAdapter.MyViewHolder> {
    private static TextToSpeech textToSpeech;
    private static ArrayList<Lesson> lessonArrayList;
    private Context context;

    public NumbersAdapter(Context context, ArrayList<Lesson> lessonArrayList) {
        this.context = context;
        this.lessonArrayList = lessonArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_number_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.mTextView.setText(lessonArrayList.get(position).getName().toUpperCase());
    }

    @Override
    public int getItemCount() {
        return lessonArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mTextView = itemView.findViewById(R.id.tv_test);

            textToSpeech = new TextToSpeech(itemView.getContext(), i -> {
                if (i == TextToSpeech.SUCCESS) {
                    int lang = textToSpeech.setLanguage(Locale.ENGLISH);
                }
            });
            itemView.setOnClickListener(view -> {
                String text = lessonArrayList.get(getAdapterPosition()).getName();
                int speech = textToSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null);
            });
        }
    }
}
