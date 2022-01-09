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
//        int[] relation;
        Type(String name, int color, int sColor) {
            this.name = name;
            this.color = color;
            this.sColor = sColor;
        }
    }
    Types(Context context) {
        types.add(new Type("ISTJ-A/T", context.getColor(R.color.ISTJ), context.getColor(R.color.sISTJ)));
        types.add(new Type("ISFJ-A/T", context.getColor(R.color.ISFJ), context.getColor(R.color.sISFJ)));
        types.add(new Type("INFJ-A/T", context.getColor(R.color.INFJ), context.getColor(R.color.sINFJ)));
        types.add(new Type("INTJ-A/T", context.getColor(R.color.INTJ), context.getColor(R.color.sINTJ)));
        types.add(new Type("ISTP-A/T", context.getColor(R.color.ISTP), context.getColor(R.color.sISTP)));
        types.add(new Type("ISFP-A/T", context.getColor(R.color.ISFP), context.getColor(R.color.sISFP)));
        types.add(new Type("INFP-A/T", context.getColor(R.color.INFP), context.getColor(R.color.sINFP)));
        types.add(new Type("INTP-A/T", context.getColor(R.color.INTP), context.getColor(R.color.sINTP)));
        types.add(new Type("ESTP-A/T", context.getColor(R.color.ESTP), context.getColor(R.color.sESTP)));
        types.add(new Type("ESFP-A/T", context.getColor(R.color.ESFP), context.getColor(R.color.sESFP)));
        types.add(new Type("ENFP-A/T", context.getColor(R.color.ENFP), context.getColor(R.color.sENFP)));
        types.add(new Type("ENTP-A/T", context.getColor(R.color.ENTP), context.getColor(R.color.sENTP)));
        types.add(new Type("ESTJ-A/T", context.getColor(R.color.ESTJ), context.getColor(R.color.sESTJ)));
        types.add(new Type("ESFJ-A/T", context.getColor(R.color.ESFJ), context.getColor(R.color.sESFJ)));
        types.add(new Type("ENFJ-A/T", context.getColor(R.color.ENFJ), context.getColor(R.color.sENFJ)));
        types.add(new Type("ENTJ-A/T", context.getColor(R.color.ENTJ), context.getColor(R.color.sENTJ)));
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
}
