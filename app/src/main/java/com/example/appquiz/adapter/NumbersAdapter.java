package com.example.appquiz.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.widget.ImageViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appquiz.NumbersActivity;
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
    private boolean isNumbers;
    private TextToSpeech tts;

    public NumbersAdapter(Context context, ArrayList<Lesson> lessonArrayList, boolean isNumbers, TextToSpeech tTS) {
        this.context = context;
        this.lessonArrayList = lessonArrayList;
        this.isNumbers = isNumbers;
        tts = tTS;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_number_layout, parent, false);
        return new MyViewHolder(view);
    }

    @SuppressLint({"UseCompatLoadingForDrawables", "SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if (isNumbers) {
            holder.mTextView.setText(lessonArrayList.get(position).getName().toUpperCase());
        } else {
            holder.mTextView.setText(lessonArrayList.get(position).getName().substring(0, 1).toUpperCase());
        }
        holder.l.setOnClickListener(v -> {
            Lesson l = lessonArrayList.get(position);
            if (!isNumbers) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater = ((NumbersActivity) context).getLayoutInflater();
                View content = inflater.inflate(R.layout.layout_dialog_i, null);
                builder.setView(content);
                AlertDialog dialog = builder.create();
                AppCompatImageView img = content.findViewById(R.id.img_letter);
                img.setImageDrawable(context.getResources().getDrawable(l.getPicture()));
                TextView pBtn = content.findViewById(R.id.btn_positive);
                pBtn.setText("Okay");
                pBtn.setOnClickListener(view -> {
                    dialog.dismiss();
                });
                try {
                    dialog.show();
                } catch (Exception e) {
                    // TODO: do something
                }
            }

            textToSpeech.speak(l.getName(), TextToSpeech.QUEUE_FLUSH, null);
        });
    }

    @Override
    public int getItemCount() {
        return lessonArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;
        LinearLayout l;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mTextView = itemView.findViewById(R.id.tv_test);
            l = itemView.findViewById(R.id.ll_item);

            textToSpeech = new TextToSpeech(itemView.getContext(), i -> {
                if (i == TextToSpeech.SUCCESS) {
                    int lang = textToSpeech.setLanguage(Locale.ENGLISH);
                }
            });
            itemView.setOnClickListener(view -> {
//                String text = lessonArrayList.get(getAdapterPosition()).getName();
//                int speech = textToSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null);
//                Toast.makeText(this.mTextView.getContext(), "SAMPLE" + lessonArrayList.get(getAbsoluteAdapterPosition()).getName(), Toast.LENGTH_LONG).show();

            });
        }
    }
}
