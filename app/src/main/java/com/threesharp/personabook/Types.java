package com.threesharp.personabook;

import android.content.Context;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public final class Types {
    public static ArrayList<Type> types = new ArrayList<>() ;
    public class Type {
        String name;
        int color;
        int sColor;
        int[] relation;
        Type(String name, int color, int sColor, int[] relation) {
            this.name = name;
            this.color = color;
            this.sColor = sColor;
            this.relation = relation;
        }
    }
    Types(Context context) {
        types.add(new Type("ISTJ-A/T", context.getColor(R.color.ISTJ), context.getColor(R.color.sISTJ), new int[]{1, 1, -1, 0, 0, 0, -1, 0, 2, 2, -1, 0, 1, 1, -1, 0}));
        types.add(new Type("ISFJ-A/T", context.getColor(R.color.ISFJ), context.getColor(R.color.sISFJ), new int[]{1, 1, -1, 0, 0, 0, -1, 0, 2, 2, -1, 0, 1, 1, -1, 0}));
        types.add(new Type("INFJ-A/T", context.getColor(R.color.INFJ), context.getColor(R.color.sINFJ), new int[]{-1, -1, 1, 1, -1, -1, 1, 1, -1, -1, 2, 2, -1, -1, 1, 1}));
        types.add(new Type("INTJ-A/T", context.getColor(R.color.INTJ), context.getColor(R.color.sINTJ), new int[]{0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 2, 2, 0, 0, 1, 1}));
        types.add(new Type("ISTP-A/T", context.getColor(R.color.ISTP), context.getColor(R.color.sISTP), new int[]{0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 2, 2, -1, 0}));
        types.add(new Type("ISFP-A/T", context.getColor(R.color.ISFP), context.getColor(R.color.sISFP), new int[]{0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 2, 2, -1, 0}));
        types.add(new Type("INFP-A/T", context.getColor(R.color.INFP), context.getColor(R.color.sINFP), new int[]{-1, -1, 1, 1, -1, -1, 1, 1, -1, -1, 1, 1, -1, -1, 2, 2}));
        types.add(new Type("INTP-A/T", context.getColor(R.color.INTP), context.getColor(R.color.sINTP), new int[]{0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 2, 0, 1, 2}));
        types.add(new Type("ESTP-A/T", context.getColor(R.color.ESTP), context.getColor(R.color.sESTP), new int[]{2, 2, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0}));
        types.add(new Type("ESFP-A/T", context.getColor(R.color.ESFP), context.getColor(R.color.sESFP), new int[]{2, 2, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0}));
        types.add(new Type("ENFP-A/T", context.getColor(R.color.ENFP), context.getColor(R.color.sENFP), new int[]{-1, -1, 2, 2, -1, -1, 1, 1, -1, -1, 1, 1, -1, -1, 1, 1}));
        types.add(new Type("ENTP-A/T", context.getColor(R.color.ENTP), context.getColor(R.color.sENTP), new int[]{0, 0, 2, 2, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1}));
        types.add(new Type("ESTJ-A/T", context.getColor(R.color.ESTJ), context.getColor(R.color.sESTJ), new int[]{1, 1, -1, 0, 2, 2, -1, 0, 0, 0, -1, 0, 1, 1, -1, 0}));
        types.add(new Type("ESFJ-A/T", context.getColor(R.color.ESFJ), context.getColor(R.color.sESFJ), new int[]{1, 1, -1, 0, 2, 2, -1, 0, 0, 0, -1, 0, 1, 1, -1, 0}));
        types.add(new Type("ENFJ-A/T", context.getColor(R.color.ENFJ), context.getColor(R.color.sENFJ), new int[]{-1, -1, 1, 1, -1, 2, 2, 1, -1, -1, 1, 1, -1, -1, 1, 1}));
        types.add(new Type("ENTJ-A/T", context.getColor(R.color.ENTJ), context.getColor(R.color.sENTJ), new int[]{0, 0, 1, 1, 0, 0, 2, 2, 0, 0, 1, 1, 0, 0, 1, 1}));
    }
    public static Type get(int pos) {
        return types.get(pos);
    }
    public static int getBinary(int type) {
        int bin;
        switch (type) {
            case 0:
                bin = 0;
                break;
            case 1:
                bin = 10;
                break;
            case 2:
                bin = 110;
                break;
            case 3:
                bin = 100;
                break;
            case 4:
                bin = 1;
                break;
            case 5:
                bin = 11;
                break;
            case 6:
                bin = 111;
                break;
            case 7:
                bin = 101;
                break;
            case 8:
                bin = 1001;
                break;
            case 9:
                bin = 1011;
                break;
            case 10:
                bin = 1111;
                break;
            case 11:
                bin = 1101;
                break;
            case 12:
                bin = 1000;
                break;
            case 13:
                bin = 1010;
                break;
            case 14:
                bin = 1110;
                break;
            case 15:
                bin = 1100;
                break;
            default:
                bin = -1;
                break;
        }
        return bin;
    }
    public static int getType(int binary) {
        int type;
        switch (binary) {
            case 0:
                type = 0;
                break;
            case 10:
                type = 1;
                break;
            case 110:
                type = 2;
                break;
            case 100:
                type = 3;
                break;
            case 1:
                type = 4;
                break;
            case 11:
                type = 5;
                break;
            case 111:
                type = 6;
                break;
            case 101:
                type = 7;
                break;
            case 1001:
                type = 8;
                break;
            case 1011:
                type = 9;
                break;
            case 1111:
                type = 10;
                break;
            case 1101:
                type = 11;
                break;
            case 1000:
                type = 12;
                break;
            case 1010:
                type = 13;
                break;
            case 1110:
                type = 14;
                break;
            case 1100:
                type = 15;
                break;
            default:
                type = -1;
                break;
        }
        return type;
    }
    public static String getRelation(int relation) {
        String ans;
        switch (relation) {
            case -1 :
                ans = "최악의 궁합";
                break;
            case 0 :
                ans = "무난한 관계";
                break;
            case 1 :
                ans = "좋은 관계";
                break;
            case 2 :
                ans = "천생연분";
                break;
            default:
                ans = "";
                break;
        }
        return ans;
    }
}
