package com.example.appquiz.newfeature.bodypartsfeature;

import android.app.Dialog;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appquiz.R;
import com.example.appquiz.classes.Score;
import com.example.appquiz.newfeature.bodypartsfeature.adapter.BodyPartQuizAdapter;
import com.example.appquiz.newfeature.bodypartsfeature.data.BodyPart;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


public class BodyPartQuizFragment extends DialogFragment implements BodyPartQuizAdapter.BodyPartInterface {

    private static final String QUIZ_SIZE = "quiz_size";
    private int quiz_size;
    private RecyclerView recyclerview_quiz;
    private BodyPartQuizAdapter adapter;
    private ImageView image_bodypart,button_back;
    private List<BodyPart> bodyPartList;
    private FirebaseDatabase database;
    private int currentChoice;
    private int quiz_counter = 0,score_counter;
    private TextView text_quiz_size,
            text_score;
    private Dialog dialog;
    // TODO: Rename and change types and number of parameters
    public static BodyPartQuizFragment newInstance(int quiz_size) {
        BodyPartQuizFragment fragment = new BodyPartQuizFragment();
        Bundle args = new Bundle();
        args.putInt(QUIZ_SIZE, quiz_size);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL,R.style.ShapeAppearanceOverlay_MaterialComponents_MaterialCalendar_Window_Fullscreen);
        if (getArguments() != null) {
            quiz_size = getArguments().getInt(QUIZ_SIZE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_body_part_quiz, container, false);
        initViews(view);
        text_quiz_size.setText(String.valueOf(quiz_size));
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        database = FirebaseDatabase.getInstance();

        dialog = new Dialog(requireContext());
        bodyPartList = new ArrayList<>();
        adapter = new BodyPartQuizAdapter(requireContext(),bodyPartList,this);
        recyclerview_quiz.setLayoutManager(new GridLayoutManager(requireContext(),3));
        recyclerview_quiz.setAdapter(adapter);

        database.getReference().child("bodyparts").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    BodyPart bodyPart = dataSnapshot.getValue(BodyPart.class);
                    bodyPartList.add(bodyPart);
                    currentChoice = generateNumber();
                    Picasso.get().load(bodyPartList.get(currentChoice).getBodyPartImageURL()).into(image_bodypart);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    button_back.setOnClickListener(v -> {
        dismiss();
    });
    }

    private void initViews(View view) {
        recyclerview_quiz = view.findViewById(R.id.recyclerview_quiz);
        image_bodypart = view.findViewById(R.id.image_body);
        text_quiz_size = view.findViewById(R.id.text_quiz_size);
        text_score = view.findViewById(R.id.text_score);
        button_back = view.findViewById(R.id.button_back);
    }

    @Override
    public void onBodyPartClick(int position) {
        quiz_counter++;
        if (quiz_counter <= quiz_size){
            if (position == currentChoice){
                Toast.makeText(requireContext(),"You win!",Toast.LENGTH_SHORT).show();
                score_counter++;
                text_score.setText(String.valueOf(score_counter));
            } else {
                Toast.makeText(requireContext(),"You lose!",Toast.LENGTH_SHORT).show();
            }
            currentChoice = generateNumber();
            Picasso.get().load(bodyPartList.get(currentChoice).getBodyPartImageURL()).into(image_bodypart);
        } else {
            dialog.show();
            MediaPlayer mediaPlayer = MediaPlayer.create(requireContext(),R.raw.win);
            mediaPlayer.start();
            dialog.setContentView(R.layout.dialog_score);
            dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            TextView tvScore = dialog.findViewById(R.id.tvScore);
            tvScore.setText("Your score is " + score_counter + "/" + quiz_size);
            Button btnClose = dialog.findViewById(R.id.btnClose);
            btnClose.setOnClickListener(v -> {
                DateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy ");
                Date date = new Date();
                String id = database.getReference().push().getKey();
                Score score = new Score(FirebaseAuth.getInstance().getCurrentUser().getEmail(), simpleDateFormat.format(date), score_counter + "/" + quiz_size);
                addScore(score,id);
                mediaPlayer.stop();
                dialog.dismiss();
                dismiss();
            });

        }
    }



    private int generateNumber(){
        Random generateNumber = new Random();
        return generateNumber.nextInt(bodyPartList.size());
    }
    private void addScore(Score score,String id){
        database.getReference().child("scores").child(id).setValue(score);
    }

}