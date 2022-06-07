package com.example.appquiz.newfeature.abakadafeature.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appquiz.R;
import com.example.appquiz.classes.Lesson;

import java.util.List;

public class AbakadaAdapter extends RecyclerView.Adapter<AbakadaAdapter.AbakadaViewHolder> {
    private Context context;
    private List<Lesson> lessonList;
    private AbakadaInterface abakadaInterface;
    public AbakadaAdapter(Context context, List<Lesson> lessonList,AbakadaInterface abakadaInterface) {
        this.context = context;
        this.lessonList = lessonList;
        this.abakadaInterface = abakadaInterface;
    }

    @NonNull
    @Override
    public AbakadaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_abakada,parent,false);
        return new AbakadaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AbakadaViewHolder holder, int position) {
        holder.text_letter.setText(lessonList.get(position).getName());
        holder.itemView.setOnClickListener(v -> {
            abakadaInterface.onAbakadaClick(position);
        });
    }

    @Override
    public int getItemCount() {
        return lessonList.size();
    }

    public class AbakadaViewHolder extends RecyclerView.ViewHolder{
        TextView text_letter;
        public AbakadaViewHolder(@NonNull View itemView) {
            super(itemView);
            text_letter = itemView.findViewById(R.id.tv_test);
        }
    }
    public interface AbakadaInterface {
        void onAbakadaClick(int position);
    }
}
