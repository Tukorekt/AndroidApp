package com.Tuko7Games.yourchoice;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import java.util.concurrent.Future;

public class film_keywords_king extends AppCompatActivity {

    Button btn_left, btn_right,next_btn;
    TextView text_left,text_right,endText;
    ImageView img_left, img_right,endImg;
    Sort sort = new Sort();
    private boolean ad_check = true;
    private int c = 0, c2 = 0, endc = 0;
    private static InterstitialAd interstitialAd;
    private static final String AD_UNIT_ID = "ca-app-pub-5921977233188947/9274999147", TAG = "film_keywords_king";
    public static List<String> names = new ArrayList();
    public static List<String> sourses = new ArrayList();
    public static List<String> chosens = new ArrayList();
    public static List<String> chosens2 = new ArrayList();
    public static List<String> chosens3 = new ArrayList();
    public static List<String> keywords = new ArrayList();
    public static List<String> keywords_all = new ArrayList();
    public static List<Integer> end_list = new ArrayList();
    public static int[] chsn = {0,-1};

    public void Update(){
        if (c<chsn[0]) {
            if (c2==0) {
                text_left.setText(keywords.get(c * 2));
                text_right.setText(keywords.get((c * 2) + 1));
            }
            if (c2==1) {
                text_left.setText(chosens.get(c * 2));
                text_right.setText(chosens.get((c * 2) + 1));
            }
            if (c2==2) {
                text_left.setText(chosens2.get(c * 2));
                text_right.setText(chosens2.get((c * 2) + 1));
            }
        }
        else {
            if (chsn[0]>8) {
                c = 0;
                chsn[0] /= 2;
                c2++;
            }
            else
                end();
        }
        c++;
    }

    public void end(){
        if (c2==0) {
            end_list = sort.srt_ing(chosens,keywords_all);
        }
        if (c2==1) {
            end_list = sort.srt_ing(chosens2,keywords_all);
        }
        if (c2==2) {
            end_list = sort.srt_ing(chosens3,keywords_all);
        }
        getEnd();
        btn_left.setVisibility(View.INVISIBLE);
        btn_right.setVisibility(View.INVISIBLE);
        text_left.setVisibility(View.INVISIBLE);
        text_right.setVisibility(View.INVISIBLE);
        img_left.setVisibility(View.INVISIBLE);
        img_right.setVisibility(View.INVISIBLE);
        endText.setVisibility(View.VISIBLE);
        endImg.setVisibility(View.VISIBLE);
        next_btn.setVisibility(View.VISIBLE);
    }

    public void getEnd(){
        if (ad_check) {
            showInterstitial();
            ad_check = false;
        }
        endText.setText(names.get(end_list.get(endc)));
        new DownloadImageTask(endImg).execute(sourses.get(end_list.get(endc)));
        endc++;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.film_keywords_king);
        new initi_king(this).execute();
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
                film_keywords_king.interstitialAd = interstitialAd;
                interstitialAd.setFullScreenContentCallback(
                        new FullScreenContentCallback() {
                            @Override
                            public void onAdDismissedFullScreenContent() {
                                film_keywords_king.interstitialAd = null;
                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(AdError adError) {
                                film_keywords_king.interstitialAd = null;
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

    @SuppressLint("StaticFieldLeak")
    public class initi_king extends AsyncTask<Void, Void, String> {
        Context context;

        public initi_king(Context context) {
            this.context = context;
        }

        @Override
        protected String doInBackground(Void... voids) {
            btn_left = findViewById(R.id.button4);
            btn_right = findViewById(R.id.button5);
            next_btn = findViewById(R.id.king_next_btn);
            text_left = findViewById(R.id.textView4);
            text_right = findViewById(R.id.textView5);
            img_left = findViewById(R.id.imageView);
            img_right = findViewById(R.id.imageView2);
            endText = findViewById(R.id.king_end_text);
            endImg = findViewById(R.id.king_end_img);

            next_btn.setOnClickListener(v -> getEnd());

            btn_left.setOnClickListener(v -> {
                if (c2==0) {
                    chosens.add(text_left.getText().toString());
                }
                if (c2==1) {
                    chosens2.add(text_left.getText().toString());
                }
                if (c2==2) {
                    chosens3.add(text_left.getText().toString());
                }

                Update();
            });

            btn_right.setOnClickListener(v -> {
                if (c2==0) {
                    chosens.add(text_left.getText().toString());
                }
                if (c2==1) {
                    chosens2.add(text_right.getText().toString());
                }
                if (c2==2) {
                    chosens3.add(text_right.getText().toString());
                }
                Update();
            });
            Update();
            return "Complete";
        }
    }

}


