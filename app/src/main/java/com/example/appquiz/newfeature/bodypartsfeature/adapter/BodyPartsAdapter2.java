package com.example.appquiz.newfeature.bodypartsfeature.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appquiz.R;
import com.example.appquiz.newfeature.bodypartsfeature.data.BodyPart2;

import java.util.ArrayList;

public class BodyPartsAdapter2 extends RecyclerView.Adapter<BodyPartsAdapter2.BodyPartViewHolder> {
    /*
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    private final Context context;
    private final ArrayList<BodyPart2> bodyPartArrayList = new ArrayList<>();


    public BodyPartsAdapter2(Context context, ArrayList<BodyPart2> list) {
        this.context = context;
        bodyPartArrayList.clear();
        bodyPartArrayList.addAll(list);
    }

    @Override
    public void onBindViewHolder(@NonNull BodyPartViewHolder holder, int position) {
        BodyPart2 part = bodyPartArrayList.get(position);
        holder.imageBodyPart.setImageDrawable(AppCompatResources.getDrawable(context, part.getBodyPartImageResource()));
        holder.bodyPartDesc.setText(part.getBodyPartDescription());
    }

    @Override
    public int getItemCount() {
        return bodyPartArrayList.size();
    }

    @NonNull
    @Override
    public BodyPartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_body_parts_2,parent,false);
        return new BodyPartViewHolder(view);
    }

    public static class BodyPartViewHolder extends RecyclerView.ViewHolder {
        ImageView imageBodyPart;
        TextView bodyPartDesc;

        public BodyPartViewHolder(@NonNull View itemView) {
            super(itemView);
            imageBodyPart = itemView.findViewById(R.id.image_body_part);
            bodyPartDesc = itemView.findViewById(R.id.text_body_desc);
        }
    }

}
