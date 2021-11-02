package com.Tuko7Games.yourchoice;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class yar extends AppCompatActivity {

    Button classic, king;
    public static List<String> keywords = new ArrayList();
    public static List<String> names = new ArrayList();
    public static List<String> kinds = new ArrayList();
    public static List<String> date = new ArrayList();
    public static List<String> rating = new ArrayList();
    public static List<String> sourses = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yar);
        new initi_yar().execute();
    }

    @SuppressLint("StaticFieldLeak")
    public class initi_yar extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            classic = findViewById(R.id.films_classic);
            king = findViewById(R.id.films_king);
            ImageButton movie_btn = findViewById(R.id.Movie_btn);
            movie_btn.setOnClickListener(v -> {
                classic.setVisibility(View.VISIBLE);
                king.setVisibility(View.VISIBLE);
            });
            ImageButton que = findViewById(R.id.yar_question);
            que.setOnClickListener(v -> {
                Intent intent = new Intent(yar.this, nocity.class);
                startActivity(intent);
            });
            classic.setOnClickListener(v -> {
                Intent intent = new Intent(yar.this, film_kind_classic.class);
                startActivity(intent);
            });
            king.setOnClickListener(v -> {
                Intent intent = new Intent(yar.this, film_keywords_choose.class);
                startActivity(intent);
            });
            return "Complete";
        }
    }

}
