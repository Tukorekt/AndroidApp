package com.Tuko7Games.yourchoice;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"NullableProblems", "unchecked", "rawtypes"})
public class film_kind_classic extends AppCompatActivity {

    private InterstitialAd interstitialAd;
    private static final String AD_UNIT_ID = "ca-app-pub-5921977233188947/9274999147", TAG = "film_kind_classic";
    public static Sort sort;
    public Button cat1_btn, cat2_btn, cat3_btn, cat4_btn, other_btn, btn2, btn3;
    public TextView title, end_text, choose_title;
    private int c = 0;
    private int loc = 0;
    private boolean ad_check = true;
    private ImageView ques, img1;
    private Animation animationRotateCenter;
    public static List<Integer> sorted_ind = new ArrayList();
    public static List<String> names = new ArrayList();
    public static  List<String> kinds = new ArrayList();
    public static  List<String> date = new ArrayList();
    public static  List<String> sourses = new ArrayList();
    public static  List<String> chosens = new ArrayList();
    public static  List<String> rating = new ArrayList();

    private void initi() {
        cat1_btn = findViewById(R.id.choose_1);
        cat2_btn = findViewById(R.id.choose_2);
        cat3_btn = findViewById(R.id.choose_3);
        cat4_btn = findViewById(R.id.choose_4);
        other_btn = findViewById(R.id.other);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        ques = findViewById(R.id.question);
        img1 = findViewById(R.id.img);
        animationRotateCenter = AnimationUtils.loadAnimation(this, R.anim.rotate_center);

    }

    private void ButtonsClick() {
        other_btn.setOnClickListener(v -> getEnd());

        cat1_btn.setOnClickListener(v -> {
            chosens.add(cat1_btn.getText().toString().trim().toLowerCase());
            Update();
        });

        cat2_btn.setOnClickListener(v -> {
            chosens.add(cat2_btn.getText().toString().trim().toLowerCase());
            Update();
        });

        cat3_btn.setOnClickListener(v -> {
            chosens.add(cat3_btn.getText().toString().trim().toLowerCase());
            Update();
        });

        cat4_btn.setOnClickListener(v -> {
            chosens.add(cat4_btn.getText().toString().trim().toLowerCase());
            Update();
        });

        btn2.setOnClickListener(v -> {
            c = 6;
            sort.setValue(2);
            Update();
        });

        btn3.setOnClickListener(v -> {
            sort.setValue(2);
            Update();
        });
    }

    private void Update() {
        switch (c) {
            case 0:
                cat1_btn.setText("Комедия");
                cat2_btn.setText("Драма");
                cat3_btn.setText("Мультфильм");
                cat4_btn.setText("Ужасы");
                break;
            case 1:
                cat1_btn.setText("Боевик");
                cat2_btn.setText("Криминал");
                cat3_btn.setText("Фантастика");
                cat4_btn.setText("Фэнтези");
                break;
            case 2:
                cat1_btn.setVisibility(View.INVISIBLE);
                cat2_btn.setVisibility(View.INVISIBLE);
                cat3_btn.setVisibility(View.INVISIBLE);
                cat4_btn.setVisibility(View.INVISIBLE);
                btn2.setVisibility(View.VISIBLE);
                btn3.setVisibility(View.VISIBLE);
                ques.setAnimation(animationRotateCenter);
                ques.setVisibility(View.VISIBLE);

                break;
            case 3:
                cat1_btn.setVisibility(View.VISIBLE);
                cat2_btn.setVisibility(View.VISIBLE);
                cat3_btn.setVisibility(View.VISIBLE);
                cat4_btn.setVisibility(View.VISIBLE);
                btn2.setVisibility(View.INVISIBLE);
                btn3.setVisibility(View.INVISIBLE);
                ques.setVisibility(View.INVISIBLE);
                cat1_btn.setText("Приключения");
                cat2_btn.setText("Триллер");
                cat3_btn.setText("Военный");
                cat4_btn.setText("Детектив");
                break;
            case 4:
                cat1_btn.setText("Семейный");
                cat2_btn.setText("Аниме");
                cat3_btn.setText("Мелодрама");
                cat4_btn.setText("Мюзикл");
                break;
            case 5:
                cat1_btn.setVisibility(View.VISIBLE);
                cat2_btn.setVisibility(View.VISIBLE);
                cat3_btn.setVisibility(View.INVISIBLE);
                cat4_btn.setVisibility(View.INVISIBLE);
                btn2.setVisibility(View.INVISIBLE);
                btn3.setVisibility(View.INVISIBLE);
                cat1_btn.setText("Выше рейтинг");
                cat2_btn.setText("Ниже рейтинг");
                ques.setVisibility(View.INVISIBLE);
                ques.getAnimation().cancel();
                ques.setVisibility(View.INVISIBLE);
                break;
            case 6:
                cat1_btn.setText("Более старый");
                cat2_btn.setText("Более новый");
                break;
            case 7:

                title = findViewById(R.id.end_text_title);
                end_text = findViewById(R.id.end_text);
                choose_title = findViewById(R.id.choose_title);
                cat1_btn.setVisibility(View.INVISIBLE);
                cat2_btn.setVisibility(View.INVISIBLE);
                cat3_btn.setVisibility(View.INVISIBLE);
                cat4_btn.setVisibility(View.INVISIBLE);
                choose_title.setVisibility(View.INVISIBLE);
                end_text.setVisibility(View.VISIBLE);
                title.setVisibility(View.VISIBLE);
                other_btn.setVisibility(View.VISIBLE);
                img1.setVisibility(View.VISIBLE);
                sorted_ind = sort.srt(chosens, kinds, rating, date);
                System.out.println(sorted_ind);
                getEnd();
                break;
        }
        c++;
    }

    @SuppressLint("SetTextI18n")
    private void getEnd() {
        if (ad_check) {
            showInterstitial();
            ad_check = false;
        }

        end_text.setText("Вам лучше всего подходит:\n" + names.get(sorted_ind.get(loc)));

        if (sourses.get(sorted_ind.get(loc)) != null) {
            img1.setVisibility(View.VISIBLE);
            new DownloadImageTask(img1).execute(sourses.get(sorted_ind.get(loc)));
        } else
            img1.setVisibility(View.INVISIBLE);
        loc++;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.film_kind_classic);
        initi();
        ButtonsClick();
        Update();
        sort = new Sort();
        MobileAds.initialize(this, initializationStatus -> {
        });
        loadAd();
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    private void loadAd() {
        AdRequest adRequest1 = new AdRequest.Builder().build();
        InterstitialAd.load(this, AD_UNIT_ID, adRequest1, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                film_kind_classic.this.interstitialAd = interstitialAd;
                interstitialAd.setFullScreenContentCallback(
                        new FullScreenContentCallback() {
                            @Override
                            public void onAdDismissedFullScreenContent() {
                                film_kind_classic.this.interstitialAd = null;
                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(AdError adError) {
                                film_kind_classic.this.interstitialAd = null;
                            }

                            @Override
                            public void onAdShowedFullScreenContent() {
                            }
                        });
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                Log.i(TAG, loadAdError.getMessage());
                interstitialAd = null;
            }
        });
    }

    private void showInterstitial() {
        if (interstitialAd != null) {
            interstitialAd.show(this);
        }
    }
}
