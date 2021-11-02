package com.Tuko7Games.yourchoice;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        new Connection("jdbc:mysql://remotemysql.com:3306/dvJT5lFElg", this).execute();
        setTheme(R.style.Theme_YourChoice_Launcher);
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
