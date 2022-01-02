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
        Type(String name, int color) {
            this.name = name;
            this.color = color;
        }
    }
    Types(Context context) {
        types.add(new Type("ISTJ-A/T", context.getColor(R.color.ISTJ)));
        types.add(new Type("ISFJ-A/T", context.getColor(R.color.ISFJ)));
        types.add(new Type("INFJ-A/T", context.getColor(R.color.INFJ)));
        types.add(new Type("INTJ-A/T", context.getColor(R.color.INTJ)));
        types.add(new Type("ISTP-A/T", context.getColor(R.color.ISTP)));
        types.add(new Type("ISFP-A/T", context.getColor(R.color.ISFP)));
        types.add(new Type("INFP-A/T", context.getColor(R.color.INFP)));
        types.add(new Type("INTP-A/T", context.getColor(R.color.INTP)));
        types.add(new Type("ESTP-A/T", context.getColor(R.color.ESTP)));
        types.add(new Type("ESFP-A/T", context.getColor(R.color.ESFP)));
        types.add(new Type("ENFP-A/T", context.getColor(R.color.ENFP)));
        types.add(new Type("ENTP-A/T", context.getColor(R.color.ENTP)));
        types.add(new Type("ESTJ-A/T", context.getColor(R.color.ESTJ)));
        types.add(new Type("ESFJ-A/T", context.getColor(R.color.ESFJ)));
        types.add(new Type("ENFJ-A/T", context.getColor(R.color.ENFJ)));
        types.add(new Type("ENTJ-A/T", context.getColor(R.color.ENTJ)));
    }
    public static Type get(int pos) {
        return types.get(pos);
    }
}
