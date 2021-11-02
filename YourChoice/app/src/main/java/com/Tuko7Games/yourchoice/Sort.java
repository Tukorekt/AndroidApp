package com.Tuko7Games.yourchoice;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
public class Sort {
    private int value;
    public static final List<Integer> sorted_ind = new ArrayList();
    public static final List<String> sorted_str = new ArrayList();
    Sort() {

    }

    public List<Integer> srt(List<String> chosen, List<String> kinds, List<String> rating, List<String> date) {
        String[][] help = new String[kinds.size()][];
        String[][] help2 = new String[kinds.size()][3];
        int date_all = 0, date_c = 0, date_m = 0;
        double rating_all = 0, rating_c = 0, raring_m = 0;
        int i = -1;
        for (String kind : kinds) {
            i++;
            help[i] = kind.split(", ");
            help2[i][0] = "0";
            help2[i][1] = rating.get(i);
            help2[i][2] = date.get(i);
            for (int j = 0; j < help[i].length; j++)
                for (int k = 0; k < value; k++) {
                    if (help[i][j].trim().equals(chosen.get(k))) {
                        help2[i][0] = String.valueOf(Integer.parseInt(help2[i][0]) + 1);
                    }
                }
        }
        i = 0;
        for (int y = value; y >= 0; y--) {
            for (int j = 0; j < help.length; j++)
                if (Integer.parseInt(help2[j][0]) == y) {
                    date_all += Integer.parseInt(date.get(j));
                    date_c++;
                    rating_all += Double.parseDouble(rating.get(j));
                    rating_c++;
                }
            if (date_c!=0||rating_c!=0) {
                if(date_c!=0)
                date_m = date_all / date_c;
                if(rating_c!=0)
                raring_m = rating_all / rating_c;
                for (int j = 0; j < help.length; j++)
                    if (Integer.parseInt(help2[j][0]) == y) {
                        if ((date_c!=0)&&((Double.parseDouble(help2[j][1]) > raring_m && chosen.get(value).equals("Выше рейтинг"))
                                || (Double.parseDouble(help2[j][1]) < raring_m && chosen.get(value).equals("Ниже рейтинг"))))
                            help2[i][0] = String.valueOf(Integer.parseInt(help2[i][0]) + 1);
                        if ((rating_c!=0)&&((Integer.parseInt(help2[j][2]) > date_m && chosen.get(value).equals("Более новый"))
                                || (Integer.parseInt(help2[j][2]) < date_m && chosen.get(value).equals("Более старый"))))
                            help2[i][0] = String.valueOf(Integer.parseInt(help2[i][0]) + 1);
                    }
            }
        }
        for (int y = value; y >= 0; y--)
            for (int j = 0; j < help.length; j++)
                if (Integer.parseInt(help2[j][0])==y)
                    sorted_ind.add(j);

        return sorted_ind;
    }

    public List<String> srt2(List<String> list){
        String [][] srdt=new String[list.size()][];
        List<String>helplist=new ArrayList();
        List<Integer>helplist2=new ArrayList();
        int max=0;
        for (int i=0;i<list.size();i++) {
            srdt[i]=list.get(i).split(",");
            for(int j=0;j<srdt[i].length;j++)
                if (!helplist.contains(srdt[i][j].trim())){
                    helplist.add(srdt[i][j].trim());
                    helplist2.add(1);
                } else {
                    int l = helplist2.set(helplist.indexOf(srdt[i][j].trim()), helplist2.get(helplist.indexOf(srdt[i][j].trim()))+1);
                }
        }
        for (int i=0;i<helplist2.size();i++)
            if (helplist2.get(i)>max)
                max=helplist2.get(i);
        for (int i=max;i>=0;i--)
            for(int j =0;j<helplist2.size();j++){
                if (helplist2.get(j).equals(i)){
                    sorted_str.add(helplist.get(j));
                }
            }
        return sorted_str;
    }

    public List<Integer> srt_ing(List<String> chosen,List<String> all){
        String [][] srdt=new String[all.size()][];
        List<Integer> helplist = new ArrayList();
        List<Integer> srtd = new ArrayList();
        for (int i=0;i<all.size();i++)
            helplist.add(0);
        for (int i=0;i<all.size();i++) {
            srdt[i]=all.get(i).split(",");
            for(int j=0;j<srdt[i].length;j++)
                if (chosen.contains(srdt[i][j].trim())) {
                    int l = helplist.set(i, helplist.get(i)+1);
                }
        }
        for (int i=8;i>=0;i--)
            for (int j=0;j<helplist.size();j++)
                if (helplist.get(j).equals(i))
                    srtd.add(j);
        return srtd;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
