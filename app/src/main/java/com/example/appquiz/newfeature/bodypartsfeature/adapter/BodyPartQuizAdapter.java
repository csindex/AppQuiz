package com.example.appquiz.newfeature.bodypartsfeature.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appquiz.R;
import com.example.appquiz.newfeature.bodypartsfeature.data.BodyPart;
import com.firebase.ui.database.FirebaseRecyclerAdapter;

import java.util.List;
import java.util.Random;

public class BodyPartQuizAdapter extends RecyclerView.Adapter<BodyPartQuizAdapter.BodyPartQuizViewHolder> {
    Context context;
    List<BodyPart> bodyPartList;
    BodyPartInterface bodyPartInterface;
    public BodyPartQuizAdapter(Context context, List<BodyPart> bodyPartList,BodyPartInterface bodyPartInterface) {
        this.context = context;
        this.bodyPartList = bodyPartList;
        this.bodyPartInterface = bodyPartInterface;
    }

    @NonNull
    @Override
    public BodyPartQuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_quiz,parent,false);
        return new BodyPartQuizViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BodyPartQuizViewHolder holder, int position) {
        holder.text_name.setText(bodyPartList.get(position).getBodyPartName());
        holder.itemView.setOnClickListener(v -> {
            bodyPartInterface.onBodyPartClick(position);
        });
    }

    @Override
    public int getItemCount() {
        return bodyPartList.size();
    }

    public class BodyPartQuizViewHolder  extends RecyclerView.ViewHolder{
        TextView text_name;
        public BodyPartQuizViewHolder(@NonNull View itemView) {
            super(itemView);
            text_name = itemView.findViewById(R.id.tv_test);
        }
    }
    public interface BodyPartInterface{
        void onBodyPartClick(int position);
    }

}
