package com.example.appquiz;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.Locale;

public class DictionaryActivity extends AppCompatActivity {
    StringRequest stringRequest;
    RequestQueue requestQueue;
    String word;
    DecimalFormat df = new DecimalFormat("#.##");
    EditText etWord;
    Dialog dialog;
    ProgressDialog progressDialog;
    TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);
        etWord = findViewById(R.id.etWord);
        dialog = new Dialog(this);
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i == TextToSpeech.SUCCESS) {
                    int lang = textToSpeech.setLanguage(Locale.ENGLISH);
                }
            }
        });
    }

    public void search(View view) {
        final String url = "https://api.dictionaryapi.dev/api/v2/entries/en/";
        word = etWord.getText().toString();
        if (word.isEmpty()) {
            etWord.setError("Enter word to search");
            etWord.requestFocus();
        } else {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Please wait...");
            progressDialog.setTitle("Getting meaning");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            String urlQuery = url + word;
            stringRequest = new StringRequest(Request.Method.GET, urlQuery, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    word = "";
                    etWord.getText().clear();
                    Log.d("response", urlQuery);
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        JSONObject jsonObject = jsonArray.getJSONObject(0);

                        String word = jsonObject.getString("word");

                        JSONArray meanings = jsonObject.getJSONArray("meanings");

                        JSONObject meaning1 = meanings.getJSONObject(0);
                        String partOfSpeech = meaning1.getString("partOfSpeech");

                        JSONArray definitions = meaning1.getJSONArray("definitions");

                        JSONObject definition = definitions.getJSONObject(0);

                        String _definition = definition.getString("definition");
                        progressDialog.dismiss();
                        openDialog(word, _definition);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if (error.networkResponse.statusCode == 404) {
                        AlertDialog.Builder alert = new AlertDialog.Builder(DictionaryActivity.this);
                        alert.setMessage(word + " not found!");
                        alert.setTitle("ERROR");
                        alert.setCancelable(true);
                        alert.show();
                        word = "";
                        etWord.getText().clear();
                        progressDialog.dismiss();
                    } else {
                        AlertDialog.Builder alert = new AlertDialog.Builder(DictionaryActivity.this);
                        alert.setMessage("Something went wrong, try again!");
                        alert.setTitle("ERROR");
                        alert.setCancelable(true);
                        alert.show();
                        word = "";
                        etWord.getText().clear();
                        progressDialog.dismiss();
                    }
                }
            });
            requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }
    }

    private void openDialog(String word, String definition) {
        dialog.setContentView(R.layout.dialog_dictionary);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        TextView tvWord = dialog.findViewById(R.id.tvWord);
        TextView tvMeaning = dialog.findViewById(R.id.tvMeaning);
        Button btnClose = dialog.findViewById(R.id.btnClose);
        Button btnPlay = dialog.findViewById(R.id.btnPlay);

        tvWord.setText(word.toUpperCase());
        tvMeaning.setText(definition);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int speech = textToSpeech.speak(word+","+definition, TextToSpeech.QUEUE_FLUSH, null);
            }
        });
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textToSpeech.stop();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}