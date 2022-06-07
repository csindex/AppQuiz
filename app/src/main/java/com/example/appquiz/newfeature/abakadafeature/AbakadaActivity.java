package com.example.appquiz.newfeature.abakadafeature;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;

import com.example.appquiz.R;
import com.example.appquiz.classes.Lesson;
import com.example.appquiz.newfeature.abakadafeature.adapter.AbakadaAdapter;
import com.example.appquiz.newfeature.abakadafeature.adapter.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AbakadaActivity extends AppCompatActivity implements AbakadaAdapter.AbakadaInterface {
    private List<Lesson> abakadaList;
    private RecyclerView recyclerview_abakada;
    private AbakadaAdapter adapter;
    private static TextToSpeech textToSpeech;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abakada);
        initViews();

        textToSpeech = new TextToSpeech(this, i -> {
            if (i == TextToSpeech.SUCCESS) {
                textToSpeech.setLanguage(Locale.ENGLISH);
            }
        });
        recyclerview_abakada.setLayoutManager(new GridLayoutManager(this, 5,GridLayoutManager.VERTICAL, false));
        adapter = new AbakadaAdapter(this, getAbakadaList(),this);
        recyclerview_abakada.setAdapter(adapter);
    }

    //TODO: initialize views
    private void initViews(){
        recyclerview_abakada = findViewById(R.id.recyclerview_abakada);
    }

    //TODO: get List of lessons (abakada lesson)
    private List<Lesson> getAbakadaList(){
        abakadaList = new ArrayList<>();
        for (String name : Constants.getAbacada()){
            abakadaList.add(new Lesson(name));
        }
        return abakadaList;
    }

    @Override
    public void onAbakadaClick(int position) {
        String name = abakadaList.get(position).getName();
        textToSpeech.speak(name,TextToSpeech.QUEUE_FLUSH,null);
    }
    public void onPause(){
        if(textToSpeech !=null){
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onPause();
    }
}