package com.Tuko7Games.yourchoice;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.sql.DriverManager;
import java.sql.SQLException;


@SuppressLint("StaticFieldLeak")
public class Connection extends AsyncTask<Void, Void, String> {
    private final String url;
    private final Context context;
    String req;

    public void request1(String str_url) {
        RequestQueue queue = Volley.newRequestQueue(context);
        @SuppressLint("SetTextI18n") StringRequest stringRequest =
                new StringRequest(Request.Method.GET, str_url, response ->
                {
                    req = response;
                    req = req.replaceFirst("<p>","");
                    while (req.contains("<p>")) {
                        String back = req.substring(0,
                                req.indexOf("<p>")).trim();
                        req = req.replace(back + "<p>", "");
                        film_keywords_king.keywords.add(back);
                    }
                }, error -> req = "Не удалось подключиться");
        queue.add(stringRequest);
    }


    public Connection(String url, Context context) {
        this.url = url+"?useSSL=false";
        this.context = context;
    }

    protected void onPreExecute() {
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection connection = DriverManager.getConnection(url, "dvJT5lFElg", "yUfJPPuRHj");
            java.sql.Statement state = connection.createStatement();
            java.sql.ResultSet res = state.executeQuery("SELECT * FROM dvJT5lFElg.2");
            while (res.next()) {
                    yar.names.add(res.getString(2)+"\n"+res.getString(3)+
                            "\nСтрана: "+ res.getString(4)+"\nРейтинг: "+res.getString(5)+
                            "\n"+ res.getString(8)+"\nВремя: "+ res.getString(10));
                    yar.kinds.add(res.getString(6));
                    yar.date.add(res.getString(3));
                    yar.rating.add(res.getString(5));
                    yar.sourses.add(res.getString(9));
                    yar.keywords.add(res.getString(7));
                }
            connection.close();
            film_kind_classic.names=yar.names;
            film_kind_classic.kinds=yar.kinds;
            film_kind_classic.date=yar.date;
            film_kind_classic.sourses=yar.sourses;
            film_kind_classic.rating=yar.rating;
            film_keywords_king.names=yar.names;
            film_keywords_king.sourses=yar.sourses;
            film_keywords_king.keywords_all=yar.keywords;
            request1("https://raken.000webhostapp.com/unique.php");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "Complete";
    }

    protected void onPostExecute(String result) {
    }
}
