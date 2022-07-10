package com.example.appquiz;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DictionaryActivity extends AppCompatActivity {
    StringRequest stringRequest;
    RequestQueue requestQueue;
    String word;
//    DecimalFormat df = new DecimalFormat("#.##");
    EditText etWord;
/*
    ProgressDialog progressDialog;
    TextToSpeech textToSpeech;
*/

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);
        etWord = findViewById(R.id.etWord);
/*
        dialog = new Dialog(this);
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i == TextToSpeech.SUCCESS) {
                    int lang = textToSpeech.setLanguage(Locale.ENGLISH);
                }
            }
        });
*/
        Button btnSearch = findViewById(R.id.btn_search);
        RelativeLayout loadingLayout = findViewById(R.id.layout_loading);
        btnSearch.setOnClickListener(view -> {
            View v = this.getCurrentFocus();
            if (v != null) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
            final String url = "https://api.dictionaryapi.dev/api/v2/entries/en/";
            word = etWord.getText().toString();
            if (word.isEmpty()) {
                etWord.setError("Enter word to search");
                etWord.requestFocus();
            } else {
/*
                progressDialog = new ProgressDialog(this);
                progressDialog.setMessage("Please wait...");
                progressDialog.setTitle("Getting meaning");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
*/
                runOnUiThread(() -> {
                    btnSearch.setVisibility(View.GONE);
                    loadingLayout.setVisibility(View.VISIBLE);
                });
                String urlQuery = url + word;
                stringRequest = new StringRequest(Request.Method.GET, urlQuery, response -> {
                    word = "";
                    etWord.getText().clear();
                    Log.d("response", urlQuery);
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        JSONObject jsonObject = jsonArray.getJSONObject(0);

                        String word = jsonObject.getString("word");

                        JSONArray meanings = jsonObject.getJSONArray("meanings");

                        JSONObject meaning1 = meanings.getJSONObject(0);
//                            String partOfSpeech = meaning1.getString("partOfSpeech");

                        JSONArray definitions = meaning1.getJSONArray("definitions");

                        JSONObject definition = definitions.getJSONObject(0);

                        String _definition = definition.getString("definition");

                        runOnUiThread(() -> {
                            loadingLayout.setVisibility(View.GONE);
                            btnSearch.setVisibility(View.VISIBLE);
                            openDialog(word, _definition);
                        });

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    LayoutInflater inflater = this.getLayoutInflater();
                    @SuppressLint("InflateParams") View content = inflater.inflate(R.layout.layout_dialog, null);
                    builder.setView(content);
                    AlertDialog dialog = builder.create();

                    TextView title = content.findViewById(R.id.label_title);
                    TextView message = content.findViewById(R.id.label_message);
                    TextView pBtn = content.findViewById(R.id.btn_positive);
                    pBtn.setText("Okay");
                    pBtn.setOnClickListener(view1 -> dialog.dismiss());
                    if (error.networkResponse.statusCode == 404) {
                        message.setText(word + " not found!");
                    } else {
                        message.setText("Something went wrong, try again!");
                    }
                    title.setText("ERROR");
                    builder.setCancelable(true);
                    dialog.show();
                    word = "";
                    runOnUiThread(() -> {
                        loadingLayout.setVisibility(View.GONE);
                        btnSearch.setVisibility(View.VISIBLE);
                        etWord.getText().clear();
                    });
                });
                requestQueue = Volley.newRequestQueue(this);
                requestQueue.add(stringRequest);
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void openDialog(String word, String definition) {
/*
        dialog.setContentView(R.layout.dialog_dictionary);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
*/
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        @SuppressLint("InflateParams") View content = inflater.inflate(R.layout.layout_dialog, null);
        builder.setView(content);

        TextView title = content.findViewById(R.id.label_title);
        TextView message = content.findViewById(R.id.label_message);
        TextView pBtn = content.findViewById(R.id.btn_positive);

        title.setText(word.toUpperCase());
        message.setText(definition);
        AlertDialog dialog = builder.create();
/*
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int speech = textToSpeech.speak(word+","+definition, TextToSpeech.QUEUE_FLUSH, null);
            }
        });
*/
        pBtn.setText("Okay");
        pBtn.setOnClickListener(view -> {
//                textToSpeech.stop();
            dialog.dismiss();
        });
        try {
            dialog.show();
        } catch (Exception e) {
            // TODO: do something
        }
    }

}