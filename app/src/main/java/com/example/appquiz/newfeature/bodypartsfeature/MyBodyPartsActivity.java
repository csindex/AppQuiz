package com.example.appquiz.newfeature.bodypartsfeature;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appquiz.R;
import com.example.appquiz.newfeature.bodypartsfeature.adapter.BodyPartsAdapter2;
import com.example.appquiz.newfeature.bodypartsfeature.data.BodyPart2;

import java.util.ArrayList;

public class MyBodyPartsActivity extends AppCompatActivity {

    private RecyclerView recyclerviewBodyParts;
    private BodyPartsAdapter2 bodyPartsAdapter;
    private final ArrayList<BodyPart2> bodyPartArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_body_parts_2);
        recyclerviewBodyParts = findViewById(R.id.recyclerview_body_parts);
        recyclerviewBodyParts.setLayoutManager(new LinearLayoutManager(this));
        bodyPartArrayList.add(new BodyPart2("1", R.drawable.head, "Head", "The head is the part of most animals, located at one end of the body, which contains the highest density of neurons, and often other sense organs. In humans, the head contains the brain, which is encased in a protective skull, and the eyes, ears, nose, and mouth."));
        bodyPartArrayList.add(new BodyPart2("12", R.drawable.eyebrow, "Eyebrow", "The first, and most obvious, function of eyebrows is to keep our eyes clear of liquid, such as sweat or rain. The brow’s arch shape diverts liquid around the eyes and to the sides of our face, keeping any water or salty sweat out of our eyes. It’s not something you think about all the time, but the next time you work out and end up sweating buckets, just be grateful that your eyebrows are keeping all of that out of your eyes."));
        bodyPartArrayList.add(new BodyPart2("2", R.drawable.eye, "Eye", "Your eyes are organs that allow you to see. Many parts of your eye work together to bring objects into focus and send visual information to your brain. Several conditions and injures can cause changes in eyesight. Some conditions can lead to permanent vision loss. To keep your eyes healthy, get regular eye exams and stay healthy overall."));
        bodyPartArrayList.add(new BodyPart2("11", R.drawable.nose2, "Nose", "Your nose is part of your respiratory system. It allows air to enter your body, then filters debris and warms and moistens the air. Your nose gives you a sense of smell and helps shape your appearance."));
        bodyPartArrayList.add(new BodyPart2("3", R.drawable.ear_2, "Ear", "Your ears transmit sound waves to the brain, and having an ear on each side of the head makes it easier for us to determine where the sound is coming from."));
        bodyPartArrayList.add(new BodyPart2("10", R.drawable.mouth, "Mouth and Teeth", "The mouth is essential for speech. With the lips and tongue, teeth help form words by controlling airflow out of the mouth. The tongue strikes the teeth or the roof of the mouth as some sounds are made. Our mouths and teeth let us make different facial expressions, form words, eat, drink, and begin the process of digestion."));
        bodyPartArrayList.add(new BodyPart2("4", R.drawable.hand2, "Hand", "Our hands can grasp and move objects in two different ways: with a power grip or precision grip. The object's size, shape, weight and ease of handling determines which of these two approaches is used. The power grip is better suited for large, heavy objects, and the precision grip is used for small, delicate objects."));
        bodyPartArrayList.add(new BodyPart2("9", R.drawable.lip, "Lip", "Lips allow us to chew and swallow with our mouth closed; to hold onto things like nails and clothes pegs, and to suckle at the breast. But even more importantly, our lips are used in communication. They allow us to smile, to bare our teeth and to kiss. They are also required for about half of the phonemes we use in normal speech, as any ventriloquist will attest."));
        bodyPartArrayList.add(new BodyPart2("5", R.drawable.arm, "Arm", "Your arms contain many muscles that work together to allow you to perform all sorts of motions and tasks. Each of your arms is composed of your upper arm and forearm. Your upper arm extends from your shoulder to your elbow. Your forearm runs from your elbow to your wrist."));
        bodyPartArrayList.add(new BodyPart2("8", R.drawable.tongue2, "Tongue", "The tongue is a muscular organ in the mouth. The tongue is covered with moist, pink tissue called mucosa. Tiny bumps called papillae give the tongue its rough texture. Thousands of taste buds cover the surfaces of the papillae. Taste buds are collections of nerve-like cells that connect to nerves running into the brain. The tongue is vital for chewing and swallowing food, as well as for speech."));
        bodyPartArrayList.add(new BodyPart2("6", R.drawable.leg, "Leg", "Our legs – with their bones, muscles and joints – have to work together to help us stand, walk, run and jump while also supporting our weight and maintaining balance."));
        bodyPartArrayList.add(new BodyPart2("7", R.drawable.foot, "Foot", "The foot (plural: feet) is an anatomical structure found in many vertebrates. It is the terminal portion of a limb which bears weight and allows locomotion. In many animals with feet, the foot is a separate organ at the terminal part of the leg made up of one or more segments or bones, generally including claws or nails."));
        bodyPartsAdapter = new BodyPartsAdapter2(this, bodyPartArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplication(), LinearLayoutManager.VERTICAL, false);
        recyclerviewBodyParts.setAdapter(bodyPartsAdapter);
//        swipeToDelete(recyclerview_body_parts);
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//    }

//    public static FirebaseRecyclerOptions<BodyPart> getBodyParts(){
//        Query query = FirebaseDatabase.getInstance().getReference().child("bodyparts");
//        return new FirebaseRecyclerOptions.Builder<BodyPart>()
//                .setQuery(query, BodyPart.class)
//                .build();
//    }

//    public void contributeBodyParts(View view) {
//        AddBodyPartsFragment addBodyPartsFragment = new AddBodyPartsFragment();
//        FragmentTransaction ft =getSupportFragmentManager().beginTransaction();
//        //TODO: check if parentFragment added the dialog
//        if(!addBodyPartsFragment.isAdded()) {
//            //or return false/true, based on where you are calling from
//            addBodyPartsFragment.show(ft, "dialog");
//        }
//    }
//    public void takeQuiz(View view) {
//        BodyPartQuizFragment bodyPartQuizFragment = BodyPartQuizFragment.newInstance(bodyPartsAdapter.getItemCount());
//        FragmentTransaction ft =getSupportFragmentManager().beginTransaction();
//        //TODO: check if parentFragment added the dialog
//        if (bodyPartsAdapter.getItemCount() < 5){
//            Toast.makeText(this,"Not enough lessons.",Toast.LENGTH_SHORT).show();
//        } else {
//            if(!bodyPartQuizFragment.isAdded()) {
//                //or return false/true, based on where you are calling from
//                bodyPartQuizFragment.show(ft, "dialog");
//            }
//        }
//
//    }
//    public void swipeToDelete(final RecyclerView recyclerView) {
//        ItemTouchHelper callback = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
//            @Override
//            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//                return false;
//            }
//
//            @Override
//            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//                int pos = viewHolder.getBindingAdapterPosition();
//                deleteBodyPart(bodyPartsAdapter.getItem(pos).getBodyPartID());
//                bodyPartsAdapter.notifyItemRemoved(pos);
//
//            }
//        });
//        callback.attachToRecyclerView(recyclerView);
//    }
//    private void deleteBodyPart(String id){
//        FirebaseDatabase.getInstance().getReference().child("bodyparts").child(id).removeValue().addOnCompleteListener(task -> {
//            if (task.isSuccessful()){
//                Toast.makeText(this,"Successfully Deleted!",Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(this,"Failed to Deleted!",Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
}