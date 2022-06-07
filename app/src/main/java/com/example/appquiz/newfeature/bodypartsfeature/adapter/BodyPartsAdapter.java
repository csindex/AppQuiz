package com.example.appquiz.newfeature.bodypartsfeature.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appquiz.R;
import com.example.appquiz.newfeature.bodypartsfeature.data.BodyPart;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

public class BodyPartsAdapter extends FirebaseRecyclerAdapter<BodyPart,BodyPartsAdapter.BodyPartViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    private Context context;
    public BodyPartsAdapter(Context context,@NonNull FirebaseRecyclerOptions<BodyPart> options) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull BodyPartViewHolder holder, int position, @NonNull BodyPart model) {
        Picasso.get().load(model.getBodyPartImageURL()).into(holder.image_body_part);
        holder.body_part_name.setText(model.getBodyPartName());
        holder.body_part_desc.setText(model.getBodyPartDescription());
    }

    @NonNull
    @Override
    public BodyPartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_body_parts,parent,false);
        return new BodyPartViewHolder(view);
    }

    public class BodyPartViewHolder  extends RecyclerView.ViewHolder {
        ImageView image_body_part;
        TextView body_part_name,body_part_desc;
        public BodyPartViewHolder(@NonNull View itemView) {
            super(itemView);
            image_body_part = itemView.findViewById(R.id.image_bosy_part);
            body_part_name = itemView.findViewById(R.id.text_body_name);
            body_part_desc = itemView.findViewById(R.id.text_body_desc);
        }
    }
}
