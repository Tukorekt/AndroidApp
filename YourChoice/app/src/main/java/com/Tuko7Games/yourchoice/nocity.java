package com.Tuko7Games.yourchoice;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class nocity extends AppCompatActivity {


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nocity);

        Button back_btn = findViewById(R.id.back_btn);
        back_btn.setOnClickListener(v -> {
            Intent intent = new Intent(nocity.this, yar.class);
            startActivity(intent);
        });

        TextView textView = findViewById(R.id.textView);
        textView.setText("Вберите категорию, которая Вас интересует\n" +
                "Затем выбирайте инетесующие Вас категории, \n" +
                "Чтобы в итоге получить для себя лучший вариант!");
    }
}
