package com.example.appquiz.newfeature.bodypartsfeature;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.appquiz.R;
import com.example.appquiz.newfeature.bodypartsfeature.adapter.BodyPartsAdapter;
import com.example.appquiz.newfeature.bodypartsfeature.data.BodyPart;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.List;

public class MyBodyPartsActivity extends AppCompatActivity {
    private RecyclerView recyclerview_body_parts;
    private BodyPartsAdapter bodyPartsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_body_parts);
        initViews();
        recyclerview_body_parts.setLayoutManager(new LinearLayoutManager(this));
        bodyPartsAdapter = new BodyPartsAdapter(this,getBodyParts());
        recyclerview_body_parts.setAdapter(bodyPartsAdapter);
        swipeToDelete(recyclerview_body_parts);
    }

    private void initViews() {
        recyclerview_body_parts = findViewById(R.id.recyclerview_body_parts);
    }

    @Override
    protected void onStart() {
        super.onStart();
        bodyPartsAdapter.startListening();
    }

    public static FirebaseRecyclerOptions<BodyPart> getBodyParts(){
        Query query = FirebaseDatabase.getInstance().getReference().child("bodyparts");
        return new FirebaseRecyclerOptions.Builder<BodyPart>()
                .setQuery(query, BodyPart.class)
                .build();
    }

    public void contributeBodyParts(View view){
        AddBodyPartsFragment addBodyPartsFragment = new AddBodyPartsFragment();
        FragmentTransaction ft =getSupportFragmentManager().beginTransaction();
        //TODO: check if parentFragment added the dialog
        if(!addBodyPartsFragment.isAdded()) {
            //or return false/true, based on where you are calling from
            addBodyPartsFragment.show(ft, "dialog");
        }
    }
    public void takeQuiz(View view){
        BodyPartQuizFragment bodyPartQuizFragment = BodyPartQuizFragment.newInstance(bodyPartsAdapter.getItemCount());
        FragmentTransaction ft =getSupportFragmentManager().beginTransaction();
        //TODO: check if parentFragment added the dialog
        if (bodyPartsAdapter.getItemCount() < 5){
            Toast.makeText(this,"Not enough lessons.",Toast.LENGTH_SHORT).show();
        } else {
            if(!bodyPartQuizFragment.isAdded()) {
                //or return false/true, based on where you are calling from
                bodyPartQuizFragment.show(ft, "dialog");
            }
        }

    }
    public void swipeToDelete(final RecyclerView recyclerView) {
        ItemTouchHelper callback = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int pos = viewHolder.getBindingAdapterPosition();
                deleteBodyPart(bodyPartsAdapter.getItem(pos).getBodyPartID());
                bodyPartsAdapter.notifyItemRemoved(pos);

            }
        });
        callback.attachToRecyclerView(recyclerView);
    }
    private void deleteBodyPart(String id){
        FirebaseDatabase.getInstance().getReference().child("bodyparts").child(id).removeValue().addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                Toast.makeText(this,"Successfully Deleted!",Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this,"Failed to Deleted!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}