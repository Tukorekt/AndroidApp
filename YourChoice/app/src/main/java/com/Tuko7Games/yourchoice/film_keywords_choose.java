package com.Tuko7Games.yourchoice;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

public class film_keywords_choose extends AppCompatActivity {
    ToggleButton tog01,tog00,tog02;
    Button btn;
    ImageView ques;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.film_keyword_choose);
        Animation animationRotateCenter = AnimationUtils.loadAnimation(this, R.anim.rotate_center);
        tog00 = findViewById(R.id.tog00);
        tog01 = findViewById(R.id.tog01);
        tog02 = findViewById(R.id.tog02);
        ques = findViewById(R.id.qq);
        btn = findViewById(R.id.button_next);
        film_keywords_king.chsn[0]=0;
        film_keywords_king.chsn[1]=1;
        ques.setAnimation(animationRotateCenter);

        tog00.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (film_keywords_king.chsn[0]==16)
                tog01.setChecked(false);
            if (film_keywords_king.chsn[0]==32)
                tog02.setChecked(false);
            film_keywords_king.chsn[0]=8;
        });
        tog01.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (film_keywords_king.chsn[0]==8)
                tog00.setChecked(false);
            if (film_keywords_king.chsn[0]==32)
                tog02.setChecked(false);
            film_keywords_king.chsn[0]=16;
        });
        tog02.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (film_keywords_king.chsn[0]==8)
                tog00.setChecked(false);
            if (film_keywords_king.chsn[0]==16)
                tog01.setChecked(false);
            film_keywords_king.chsn[0]=32;

        });
        btn.setOnClickListener(v -> {
            if (film_keywords_king.chsn[0]!=0&&film_keywords_king.chsn[1]!=-1) {
                Intent intent = new Intent(film_keywords_choose.this, film_keywords_king.class);
                startActivity(intent);
            }
        });
    }
}
